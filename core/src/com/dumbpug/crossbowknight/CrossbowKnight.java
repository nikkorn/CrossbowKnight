package com.dumbpug.crossbowknight;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.level.Level;
import com.dumbpug.crossbowknight.level.LevelFactory;
import com.dumbpug.crossbowknight.tiles.TileTextures;
import com.dumbpug.crossbowknight.tiles.TileTextures.EntityTile;

public class CrossbowKnight extends ApplicationAdapter {
	SpriteBatch batch;
	Level testLevel;
	Texture img;
	
	// Tester offsets
	float offsetx = 0f;
	float offsety = 0f;
	
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
		
		// Update the level.
		testLevel.update();
		
		// Draw the level.
		batch.begin();
		testLevel.draw(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
