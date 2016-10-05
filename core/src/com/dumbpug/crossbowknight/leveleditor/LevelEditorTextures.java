package com.dumbpug.crossbowknight.leveleditor;

import com.badlogic.gdx.graphics.Texture;

public class LevelEditorTextures {
	private Texture gridTile;
	private Texture menuBg;
	private Texture activeTileMarker;
	
	public LevelEditorTextures() {
		gridTile         = new Texture("graphics/misc/LEVELEDITOR_TILE_BG.png");
		menuBg           = new Texture("graphics/misc/LEVELEDITOR_MENU_BG.png");
		activeTileMarker = new Texture("graphics/misc/LEVELEDITOR_ACTIVETILEMARKER.png");
	}
	
	public Texture getGridTileTexture() {
		return gridTile;
	}
	
	public Texture getMenuBackground() {
		return menuBg;
	}
	
	public Texture getActiveTileMarker() {
		return activeTileMarker;
	}
}
