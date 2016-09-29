package com.dumbpug.crossbowknight.leveleditor;

import java.util.Scanner;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.input.DesktopPlayerInput;
import com.dumbpug.crossbowknight.input.PlayerInput;
import com.dumbpug.crossbowknight.tiles.Tile;
import com.dumbpug.crossbowknight.tiles.TileTextures;

/**
 * Editor for building levels.
 * @author nikolas.howard
 */
public class CrossbowKnightLevelEditor extends ApplicationAdapter {
	/** The name of the level. */
	private String levelName;
	/** The input processor. */
	private static PlayerInput playerInput;
	/** The games sprite batch. */
	private SpriteBatch batch;
	/** The level editor textures */
	private LevelEditorTextures levelEditorTextures;
	/** The editor tile offset. */
	private int editorTilePositionX = 0;
	private int editorTilePositionY = 0;
	/** The editable level. */
	private EditableLevel level;
	/** The scanner used to read command line input */
	private Scanner inputScanner; 

	@Override
	public void create () {
		batch               = new SpriteBatch();
		levelEditorTextures = new LevelEditorTextures();
		playerInput         = new DesktopPlayerInput();
		inputScanner        = new Scanner(System.in);
		Gdx.input.setInputProcessor(CrossbowKnightLevelEditor.playerInput);
		// Try to read the level from disk. If this fails, create a blank level.
		try {
			level = EditableLevelFactory.getEditableLevelFromDisk(levelName);
		} catch(Exception e) {
			// We were not able to read the level from disk. So we should
			// assume that we are making a new level.
			System.out.println("No existing level '" + levelName + "', creating new level.");
			level = new EditableLevel();
		}
	}
	
	/**
	 * Creates a new instance of CrossbowKnightLevelEditor.
	 * @param levelName
	 */
	public CrossbowKnightLevelEditor(String levelName) {
		this.levelName = levelName;
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// For now, lets force exit on press of Q.
		if(Gdx.input.isKeyPressed(Input.Keys.Q)) { Gdx.app.exit(); }
		
		// For now, lets output our level on whatever is the cancel button in-game
		if(playerInput.isCancelButtonPressed()) { outputRawLevel(); }
		
		// Do we want to move our editor tile offset?
		if(playerInput.isUpButtonPressed()) { editorTilePositionY++; }
		if(playerInput.isDownButtonPressed()) { editorTilePositionY--; }
		if(playerInput.isLeftButtonPressed()) { editorTilePositionX--; }
		if(playerInput.isRightButtonPressed()) { editorTilePositionX++; }
		
		// Has a grid tile been selected?
		if(playerInput.isJumpButtonPressed()) {
			int posX = ((int) (Gdx.input.getX() / C.TILE_SIZE)) + editorTilePositionX;
			int posY = ((int) ((Gdx.graphics.getHeight() - Gdx.input.getY()) / C.TILE_SIZE)) + editorTilePositionY;
			onClickOnGridTile(posX, posY);
		}
	
		// Draw the editor.
		batch.begin();
		// Draw grid tiles.
		for(int x = 0; x < (C.TILE_GRID_SIZE*2); x++) {
			for(int y = 0; y < C.TILE_GRID_SIZE; y++) {
				batch.draw(levelEditorTextures.getGridTileTexture(), x*C.TILE_SIZE, y*C.TILE_SIZE, C.TILE_SIZE, C.TILE_SIZE);
			}
		}
		// Draw the level.
		level.draw(batch, editorTilePositionX, editorTilePositionY);
		batch.end();
	}
	
	/**
	 * Called when we click on a grid tile.
	 * @param x
	 * @param y
	 */
	public void onClickOnGridTile(int x, int y) {
		// Get the target tile.
		Tile targetTile = level.getTileAt(x, y);

		System.out.print("Selected ");
		System.out.print("X: " + x);
		System.out.println(" Y: " + y);
		
		System.out.println("options:");
		System.out.println(" 1. clear tile");
		System.out.println(" 2. set background texture");
		System.out.println(" 3. clear background texture");
		System.out.println(" 4. set decoration texture");
		System.out.println(" 5. clear decoration texture");
		System.out.println(" 6. set block");
		System.out.println(" 7. clear block");
		System.out.println(" 8. resume");

		System.out.print("option? : ");
		int selection = inputScanner.nextInt();
		int textureId;

		switch(selection) {
			case 1:
				// ------ Clear tile. ------
				if(targetTile != null) {
					level.removeTile(targetTile);
					System.out.println("tile cleared.");
				} else {
					System.out.println("no tile to clear.");
				}
				break;
			case 2:
				// ------ Set background texture. ------
				System.out.print("texture id? : ");
				textureId = inputScanner.nextInt();
				// Do we have a matching texture?
				if(textureId >= TileTextures.BackgroundTile.values().length) {
					System.out.println("no matching texture.");
				} else {
					Texture texture = TileTextures.getTileTextures().getBackgroundTileTexture(TileTextures.BackgroundTile.values()[textureId]);
					// Do we actually have an existing tile at this position?
					if(targetTile == null) {
						targetTile = level.addNewTileAt(x, y);
					}
					// Set the texture.
					targetTile.setBackgroundTexture(texture);
				}
				break;
			case 3:
				// ------ Clear background texture. ------
				if(targetTile != null) {
					// Get rid of the tile background texture.
					targetTile.setBackgroundTexture(null);
					System.out.println("texture cleared.");
					// If our tile has nothing on it, then remove it.
					if(level.isTileBlank(targetTile)) {
						level.removeTile(targetTile);
					}
				} else {
					System.out.println("no tile.");
				}
				break;
			case 4:
				// ------ Set decoration texture. ------
				System.out.print("texture id? : ");
				textureId = inputScanner.nextInt();
				// Do we have a matching texture?
				if(textureId >= TileTextures.DecorationTile.values().length) {
					System.out.println("no matching texture.");
				} else {
					Texture texture = TileTextures.getTileTextures().getDecorationTileTexture(TileTextures.DecorationTile.values()[textureId]);
					// Do we actually have an existing tile at this position?
					if(targetTile == null) {
						targetTile = level.addNewTileAt(x, y);
					}
					// Set the texture.
					targetTile.setDecorationTexture(texture);
				}
				break;
			case 5:
				// ------ Clear decoration texture. ------
				if(targetTile != null) {
					// Get rid of the tile decoration texture.
					targetTile.setDecorationTexture(null);
					System.out.println("texture cleared.");
					// If our tile has nothing on it, then remove it.
					if(level.isTileBlank(targetTile)) {
						level.removeTile(targetTile);
					}
				} else {
					System.out.println("no tile.");
				}
				break;
			case 6:
				break;
			case 7:
				break;
			case 8:
				break;
		}

		System.out.println();
	}
	
	/**
	 * Spit out the raw level JSON.
	 */
	private void outputRawLevel() {
		System.out.println("Output");
	}

	@Override
	public void dispose () { batch.dispose(); }
}
