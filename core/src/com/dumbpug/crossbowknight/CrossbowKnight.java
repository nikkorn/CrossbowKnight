package com.dumbpug.crossbowknight;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.game.LevelFactory;
import com.dumbpug.crossbowknight.tiles.TileTextures;
import com.dumbpug.crossbowknight.tiles.TileTextures.EntityTile;

public class CrossbowKnight extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = TileTextures.getTileTextures().getEntityTileTexture(EntityTile.DOOR_3);
		
		LevelFactory.getLevelFromDisk("MAIN_HALL");
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0, C.LAYOUT_TILE_SIZE, C.LAYOUT_TILE_SIZE);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
