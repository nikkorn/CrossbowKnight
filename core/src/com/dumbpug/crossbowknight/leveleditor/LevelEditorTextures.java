package com.dumbpug.crossbowknight.leveleditor;

import com.badlogic.gdx.graphics.Texture;

public class LevelEditorTextures {
	private Texture gridTile;
	private Texture menuBg;
	private Texture activeTileMarker;
	
	/** buttons. */
	private Texture backgroundBtn;
	private Texture decorationBtn;
	private Texture blockBtn;
	private Texture clearBtn;
	
	public LevelEditorTextures() {
		gridTile         = new Texture("graphics/misc/LEVELEDITOR_TILE_BG.png");
		menuBg           = new Texture("graphics/misc/LEVELEDITOR_MENU_BG.png");
		activeTileMarker = new Texture("graphics/misc/LEVELEDITOR_ACTIVETILEMARKER.png");
		backgroundBtn    = new Texture("graphics/misc/LEVELEDITOR_BG_BTN.png");
		decorationBtn    = new Texture("graphics/misc/LEVELEDITOR_DC_BTN.png");
		blockBtn         = new Texture("graphics/misc/LEVELEDITOR_BLK_BTN.png");
		clearBtn         = new Texture("graphics/misc/LEVELEDITOR_CL_BTN.png");
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
	
	public Texture getBackgroundButtonTexture() {
		return this.backgroundBtn;
	}
	
	public Texture getDecorationButtonTexture() {
		return this.decorationBtn;
	}
	
	public Texture getBlockButtonTexture() {
		return this.blockBtn;
	}
	
	public Texture getClearButtonTexture() {
		return this.clearBtn;
	}
}
