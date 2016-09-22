package com.dumbpug.crossbowknight;

public class GameMath {
	
	/**
	 * Determines the angle of a straight line drawn between point one and two. 
	 * @param aX
	 * @param aY
	 * @param bX
	 * @param bY
	 * @return
	 */
	public static double GetAngleOfLineBetweenTwoPoints(int aX, int aY, int bX, int bY) { 
		double xDiff = bX - aX; 
		double yDiff = bY - aY; 
		return Math.toDegrees(Math.atan2(yDiff, xDiff)); 
	} 
}
