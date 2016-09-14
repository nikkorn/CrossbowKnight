package com.dumbpug.crossbowknight;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.game.Level;
import com.dumbpug.crossbowknight.game.LevelFactory;
import com.dumbpug.crossbowknight.tiles.Tile;
import com.dumbpug.crossbowknight.tiles.TileTextures;
import com.dumbpug.crossbowknight.tiles.TileTextures.EntityTile;

public class CrossbowKnight extends ApplicationAdapter {
	SpriteBatch batch;
	Level testLevel;
	Texture img;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = TileTextures.getTileTextures().getEntityTileTexture(EntityTile.DOOR_1);
		testLevel = LevelFactory.getLevelFromDisk("MAIN_HALL");
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// For now, lets force exit on press of Q.
		if(Gdx.input.isKeyPressed(Input.Keys.Q)) { Gdx.app.exit(); }

		batch.begin();
		// Draw test level.
		for(Tile tile : testLevel.getLevelTiles()) {
			// Draw tile background.
			batch.draw(tile.getBackgroundTexture(), tile.getX() * C.LAYOUT_TILE_SIZE,
					tile.getY() * C.LAYOUT_TILE_SIZE, C.LAYOUT_TILE_SIZE, C.LAYOUT_TILE_SIZE);
		}
		batch.draw(img, 0, 0, C.LAYOUT_TILE_SIZE, C.LAYOUT_TILE_SIZE);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
