package com.dumbpug.crossbowknight.tiles;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dumbpug.crossbowknight.C;
import com.dumbpug.crossbowknight.entities.characters.player.Player;

/**
 * Facilitates interactions between the player and interactive tiles such as doors and shops.
 * @author nikolas.howard
 *
 */
public class TileInteractionFacilitator {
	/** The player which can interact with interactive tiles. */
	private Player player;
	/** The world tiles. */
	private ArrayList<Tile> tiles;
	
	/**
	 * Create a new instance of the TileInteractionFacilitator class.
	 * @param player
	 * @param tiles
	 */
	public TileInteractionFacilitator(Player player, ArrayList<Tile> tiles) {
		this.player = player;
		this.tiles  = tiles;
		// Create the interaction resolver for the player.
		createInteractionResolverForPlayer();
	}
	
	/**
	 * Create the interaction resolver for the player.
	 */
	private void createInteractionResolverForPlayer() {
		// Set the tile interaction resolver on our player. This will mean that 
		// we can intercept presses of buttons which are also used to interact 
		// with tiles and carry out actions on tiles we are in position with.
		player.setTileInteractionResolver(new ITileInteractionResolver() {
			@Override
			public boolean cycleLeft() {
				return onCycleLeft();
			}

			@Override
			public boolean cycleRight() {
				return onCycleRight();
			}

			@Override
			public boolean select() {
				return onSelect();
			}
		});
	}
	
	/**
	 * Called when the player has cycled left.
	 * @return whether we have resolved this action at the tile level.
	 */
	public boolean onCycleLeft() {
		return false;
	}
	
	/**
	 * Called when the player has cycled right.
	 * @return whether we have resolved this action at the tile level.
	 */
	public boolean onCycleRight() {
		return false;
	}
	
	/**
	 * Called when the presses a button which represents select in regards to tile interactivity.
	 * @return whether we have resolved this action at the tile level.
	 */
	public boolean onSelect() {
		return false;
	}
	
	/**
	 * Update anything related to tile interactions.
	 */
	public void update() {
		
	}
	
	/**
	 * Draw anything related to tile interactions.
	 * @param batch
	 */
	public void draw(SpriteBatch batch) {
		
	}
	
	/**
	 * Get the tile that the player is in front of.
	 * @return tile at player position.
	 */
	public Tile getTileAtPlayerPosition() {
		int tilePosX = (int) (player.getPhysicsBox().getCurrentOriginPoint().getX() / C.LAYOUT_TILE_SIZE);
		int tilePosY = (int) (player.getPhysicsBox().getCurrentOriginPoint().getY() / C.LAYOUT_TILE_SIZE);
		for(Tile tile : tiles) {
			if(tile.getX() == tilePosX && tile.getY() == tilePosY) {
				return tile;
			}
		}
		// We never found the tile we were after.
		return null; 
	}
}
