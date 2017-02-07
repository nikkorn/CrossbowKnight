package com.dumbpug.crossbowknight.gamemenu.tabs.character;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.Align;
import com.dumbpug.crossbowknight.C;
import com.dumbpug.crossbowknight.resources.FontPack;

/**
 * Represents the XP bar on the Charater Stats tab.
 * @author nikolas.howard
 */
public class XpBar {
	private float barMargin        = C.MENU_CHARACTER_XP_BAR_HEIGHT/4;
	private float barWidth         = C.INGAME_MENU_WIDTH - (barMargin*2);
	private float barFillableWidth = barWidth - (C.MENU_CHARACTER_XP_BAR_HEIGHT*2);
	/** The position of the xp bar. */
	private float posX = C.INGAME_MENU_POS_X + barMargin;
	private float posY = C.INGAME_MENU_POS_Y + barMargin;
	/** The font to use for this tab. */
	private BitmapFont font;
	private BitmapFont secondaryFont;
	/** The resources required to draw the xp bar. */
	private Texture levelBoxLeft;
	private Texture levelBoxRight;
	private Texture sectionEmpty;
	private Texture sectionFull;
	
	/**
	 * Create a new instance of the XpBar class.
	 */
	public XpBar() {
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = C.FONT_SIZE_SMALL;
		this.font      = FontPack.getFontPack().getFont(FontPack.FontType.MAIN_FONT, parameter);
		parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = C.FONT_SIZE_XSMALL;
		this.secondaryFont = FontPack.getFontPack().getFont(FontPack.FontType.MAIN_FONT, parameter);
		levelBoxLeft   = new Texture("graphics/gamemenu/character/xp_bar_level_box_left.png");
		levelBoxRight  = new Texture("graphics/gamemenu/character/xp_bar_level_box_right.png");
		sectionEmpty   = new Texture("graphics/gamemenu/character/xp_bar_empty_section.png");
		sectionFull    = new Texture("graphics/gamemenu/character/xp_bar_full_section.png");
	}
	
	/**
	 * Draw the XP bar with the specified level and XP.
	 * @param batch
	 * @param level
	 * @param currentXp
	 * @param currentLevelXp
	 * @param nextLevelXp
	 */
	public void draw(SpriteBatch batch, int level, int currentXp, int currentLevelXp, int nextLevelXp) {
		batch.draw(sectionEmpty, posX, posY, barWidth, C.MENU_CHARACTER_XP_BAR_HEIGHT);
		batch.draw(levelBoxLeft, posX, posY, C.MENU_CHARACTER_XP_BAR_HEIGHT, C.MENU_CHARACTER_XP_BAR_HEIGHT);
		batch.draw(levelBoxRight, posX + (barWidth - C.MENU_CHARACTER_XP_BAR_HEIGHT), posY, C.MENU_CHARACTER_XP_BAR_HEIGHT, C.MENU_CHARACTER_XP_BAR_HEIGHT);
		// Draw the filled part of the XP bar.
		float barFillPercentage = currentXp == 0 ? 0f : ((float) currentXp - currentLevelXp) / ((float) (nextLevelXp - currentLevelXp));
		batch.draw(sectionFull, posX + C.MENU_CHARACTER_XP_BAR_HEIGHT, posY, barFillableWidth * barFillPercentage, C.MENU_CHARACTER_XP_BAR_HEIGHT);
		// Draw the XP info.
		secondaryFont.draw(batch, "xp: " + currentXp + " / " + nextLevelXp, posX, posY + C.MENU_CHARACTER_XP_BAR_HEIGHT, barWidth, Align.center, true);
		// Draw the level info.
		font.draw(batch, level+"", posX, posY + (C.MENU_CHARACTER_XP_BAR_HEIGHT*0.63f), C.MENU_CHARACTER_XP_BAR_HEIGHT, Align.center, true);
		font.draw(batch, (level + 1)+"", posX + (barWidth - C.MENU_CHARACTER_XP_BAR_HEIGHT), posY + (C.MENU_CHARACTER_XP_BAR_HEIGHT*0.63f), C.MENU_CHARACTER_XP_BAR_HEIGHT, Align.center, true);
	}
}
