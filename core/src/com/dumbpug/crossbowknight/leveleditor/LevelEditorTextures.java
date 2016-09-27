package com.dumbpug.crossbowknight.leveleditor;

import com.badlogic.gdx.graphics.Texture;

public class LevelEditorTextures {
	private Texture gridTile;
	
	public LevelEditorTextures() {
		gridTile = new Texture("graphics/misc/LEVELEDITOR_TILE_BG.png");
	}
	
	public Texture getGridTileTexture() {
		return gridTile;
	}
}
