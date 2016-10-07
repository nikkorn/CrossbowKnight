package com.dumbpug.crossbowknight.leveleditor;

import java.util.InputMismatchException;
import java.util.Scanner;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.input.DesktopPlayerInput;
import com.dumbpug.crossbowknight.input.PlayerInput;
import com.dumbpug.crossbowknight.level.Block;
import com.dumbpug.crossbowknight.level.Block.TileBlockFillType;
import com.dumbpug.crossbowknight.leveleditor.menu.MainMenu;
import com.dumbpug.crossbowknight.tiles.IndexedTileTexture;
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
	/** The level editor menu */
	private MainMenu levelEditorMenu;
	/** The editor tile offset. */
	private int editorTilePositionX = 0;
	private int editorTilePositionY = 0;
	/** The editable level. */
	private EditableLevel level;
	/** The active tile. */
	private Tile activeTile = null;
	/** The scanner used to read command line input */
	private Scanner inputScanner; 
	/** The last time a click was registered. We care about this
	 * because we don't want to go to great depths to handle 
	 * individual mouse clicks, but we also don't want 
	 * lots of hits on each click. */
	private long lastClick = System.currentTimeMillis(); 

	@Override
	public void create () {
		batch               = new SpriteBatch();
		levelEditorTextures = new LevelEditorTextures();
		playerInput         = new DesktopPlayerInput();
		inputScanner        = new Scanner(System.in);
		levelEditorMenu     = new MainMenu(levelEditorTextures);
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
		if(playerInput.isCancelButtonPressed()) { writeLevelToDisk(); }
		
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
		
		// Check for mouse click.
		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)) { 
			// Can we register this as a click? (click cool-down over)
			if((System.currentTimeMillis() - this.lastClick) > C.CLICK_COOLDOWN) {
				if(Gdx.input.getX() > (Gdx.graphics.getWidth()/2)) {
					// The mouse click happened within the menu, let the menu handle it.
					levelEditorMenu.onMouseClick(Gdx.input.getX() , Gdx.input.getY());
				} else {
					// The mouse click happened on the grid. Set the clicked tile as active.
					int tilePosX = ((int) (Gdx.input.getX() / C.TILE_SIZE)) + editorTilePositionX;
					int tilePosY = ((int) ((Gdx.graphics.getHeight() - Gdx.input.getY()) / C.TILE_SIZE)) + editorTilePositionY;
					// Get the target tile.
					Tile targetTile = level.getTileAt(tilePosX, tilePosY);
					// If this tile does not exist, add it.
					if(targetTile == null) {
						targetTile = level.addNewTileAt(tilePosX, tilePosY);
					}
					// Set the target tile as the active one.
					this.activeTile = targetTile;
					// Let the menu know that a new tile is now the active one.
					levelEditorMenu.onTileSelect(targetTile);
				}
				// Reset the click cool-down.
				this.lastClick = System.currentTimeMillis();
			}
		} 
		
		// Draw the editor.
		batch.begin();
		// Draw grid tiles.
		for(int x = 0; x < (C.TILE_GRID_SIZE); x++) {
			for(int y = 0; y < C.TILE_GRID_SIZE; y++) {
				batch.draw(levelEditorTextures.getGridTileTexture(), x*C.TILE_SIZE, y*C.TILE_SIZE, C.TILE_SIZE, C.TILE_SIZE);
			}
		}
		// Draw the level.
		level.draw(batch, editorTilePositionX, editorTilePositionY);
		// Draw a marker around the active tile (if there is one).
		if(this.activeTile != null) {
			batch.draw(levelEditorTextures.getActiveTileMarker(), (activeTile.getX() - editorTilePositionX) * C.TILE_SIZE, (activeTile.getY() - editorTilePositionY) * C.TILE_SIZE, C.TILE_SIZE, C.TILE_SIZE);
		}
		// Draw the menu.
		levelEditorMenu.draw(batch);
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

		int selection = 0;
		int textureId = 0;

		try {
			selection = inputScanner.nextInt();
		} catch (InputMismatchException ime) {
			System.out.println("error: not a valid input.");
			inputScanner.next();
			return;
		}

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
				try {
					textureId = inputScanner.nextInt();
				} catch (InputMismatchException ime) {
					System.out.println("error: not a valid input.");
					inputScanner.next();
					return;
				}
				// Do we have a matching texture?
				if(textureId >= TileTextures.BackgroundTile.values().length) {
					System.out.println("no matching texture.");
				} else {
					IndexedTileTexture texture = TileTextures.getTileTextures().getBackgroundTileTexture(TileTextures.BackgroundTile.values()[textureId]);
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
				try {
					textureId = inputScanner.nextInt();
				} catch (InputMismatchException ime) {
					System.out.println("error: not a valid input.");
					inputScanner.next();
					return;
				}
				// Do we have a matching texture?
				if(textureId >= TileTextures.DecorationTile.values().length) {
					System.out.println("no matching texture.");
				} else {
					IndexedTileTexture texture = TileTextures.getTileTextures().getDecorationTileTexture(TileTextures.DecorationTile.values()[textureId]);
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
				// ------ Set block. ------
				TileBlockFillType fillType = null;
				int fillTypeInput          = 0;
				System.out.print("texture id? : ");
				try {
					textureId = inputScanner.nextInt();
				} catch (InputMismatchException ime) {
					System.out.println("error: not a valid input.");
					inputScanner.next();
					return;
				}
				System.out.print("tile fill type? (1.top 2.bottom 3.left 4.right 5.full) : ");
				try {
					fillTypeInput = inputScanner.nextInt();
				} catch (InputMismatchException ime) {
					System.out.println("error: not a valid input.");
					inputScanner.next();
					return;
				}
				// Convert the fill type option to can actual fill type.
				switch(fillTypeInput) {
					case 1:
						fillType = TileBlockFillType.TOP_HALF;
						break;
					case 2:
						fillType = TileBlockFillType.BOTTOM_HALF;
						break;
					case 3:
						fillType = TileBlockFillType.LEFT_HALF;
						break;
					case 4:
						fillType = TileBlockFillType.RIGHT_HALF;
						break;
					case 5:
						fillType = TileBlockFillType.FULL;
						break;
					default:
						System.out.println("not a valid fill type, defaulting to FULL.");
						fillType = TileBlockFillType.FULL;
				}
				// We have everything we need to create our new block.
				// We don't need to specify x/y/width/height at this moment 
				// as this is only calculated when the block is generated
				// using the games level factory.
				Block newBlock = new Block(0, 0, 0, 0, fillType);
				// Do we have a matching texture?
				if(textureId >= TileTextures.BlockTile.values().length) {
					System.out.println("no matching texture.");
					break;
				}
				// Get the texture.
				IndexedTileTexture texture = TileTextures.getTileTextures().getBlockTileTexture(TileTextures.BlockTile.values()[textureId]);
				// Do we actually have an existing tile at this position?
				if(targetTile == null) {
					targetTile = level.addNewTileAt(x, y);
				}
				// Set the texture on the block.
				newBlock.setBlockTexture(texture);
				// Attach our block to the target tile.
				targetTile.setPhysicsBlock(newBlock);
				System.out.println("block set!");
				break;
			case 7:
				// ------ Clear block. ------
				if(targetTile != null) {
					if(targetTile.getPhysicsBlock() != null) {
						// Get rid of the block attached to this tile.
						targetTile.setPhysicsBlock(null);
						System.out.println("block cleared.");
						// If our tile has nothing on it, then remove it.
						if(level.isTileBlank(targetTile)) {
							level.removeTile(targetTile);
						}
					} else {
						System.out.println("no block attached to tile.");
					}
				} else {
					System.out.println("no tile.");
				}
				break;
			case 8:
				// Do nothing!
				break;
			default:
				System.out.println("not a valid option!");
		}

		System.out.println();
	}
	
	/**
	 * Write the current level to disk.
	 */
	private void writeLevelToDisk() {
		EditableLevelWriter levelWriter = new EditableLevelWriter(this.level);
		levelWriter.writeLevel(this.levelName);
	}

	@Override
	public void dispose () { batch.dispose(); }
}
