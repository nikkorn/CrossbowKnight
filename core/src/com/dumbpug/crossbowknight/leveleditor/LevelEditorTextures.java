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
	private Texture blockFillFullBtn;
	private Texture blockFillLeftBtn;
	private Texture blockFillRightBtn;
	private Texture blockFillTopBtn;
	private Texture blockFillBottomBtn;
	
	public LevelEditorTextures() {
		gridTile           = new Texture("graphics/leveleditor/LEVELEDITOR_TILE_BG.png");
		menuBg             = new Texture("graphics/leveleditor/LEVELEDITOR_MENU_BG.png");
		activeTileMarker   = new Texture("graphics/leveleditor/LEVELEDITOR_ACTIVETILEMARKER.png");
		backgroundBtn      = new Texture("graphics/leveleditor/LEVELEDITOR_BG_BTN.png");
		decorationBtn      = new Texture("graphics/leveleditor/LEVELEDITOR_DC_BTN.png");
		blockBtn           = new Texture("graphics/leveleditor/LEVELEDITOR_BLK_BTN.png");
		clearBtn           = new Texture("graphics/leveleditor/LEVELEDITOR_CL_BTN.png");
		blockFillFullBtn   = new Texture("graphics/leveleditor/LEVELEDITOR_BLK_FILL_FULL.png");
		blockFillLeftBtn   = new Texture("graphics/leveleditor/LEVELEDITOR_BLK_FILL_LEFT.png");
		blockFillRightBtn  = new Texture("graphics/leveleditor/LEVELEDITOR_BLK_FILL_RIGHT.png");
		blockFillTopBtn    = new Texture("graphics/leveleditor/LEVELEDITOR_BLK_FILL_TOP.png");
		blockFillBottomBtn = new Texture("graphics/leveleditor/LEVELEDITOR_BLK_FILL_BOTTOM.png");
	}
	
	public Texture getGridTileTexture() { return gridTile; }
	
	public Texture getMenuBackground() { return menuBg; }
	
	public Texture getActiveTileMarker() { return activeTileMarker; }
	
	public Texture getBackgroundButtonTexture() { return this.backgroundBtn; }
	public Texture getDecorationButtonTexture() { return this.decorationBtn; }
	public Texture getBlockButtonTexture() { return this.blockBtn; }
	public Texture getClearButtonTexture() { return this.clearBtn; }

	public Texture getBlockFillFullButtonTexture() { return this.blockFillFullBtn; }
	public Texture getBlockFillLeftButtonTexture() { return this.blockFillLeftBtn; }
	public Texture getBlockFillRightButtonTexture() { return this.blockFillRightBtn; }
	public Texture getBlockFillTopButtonTexture() { return this.blockFillTopBtn; }
	public Texture getBlockFillBottomButtonTexture() { return this.blockFillBottomBtn; }
}
