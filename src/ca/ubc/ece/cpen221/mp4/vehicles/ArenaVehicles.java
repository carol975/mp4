package ca.ubc.ece.cpen221.mp4.vehicles;

import ca.ubc.ece.cpen221.mp4.Actor;
import ca.ubc.ece.cpen221.mp4.Direction;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.items.MoveableItem;

public interface ArenaVehicles extends MoveableItem, Actor {



	/**
	 * Change direction when cooldown of item is less than 1, else remain same direction
	 * 
	 * @param world
	 *           the current world
	 * @return Direction
	 *           the new direction in which item should move
	 */

	Direction turn(World world);
	
	/**
	 * Returns the current direction of the vehicle that is moving
	 * 
	 * @return Direction
	 *            the direction in which item is moving
	 */
	Direction getDirection();
	
	/**
	 * Returns the acceleration of the vehicle. This represents the amount of cooldown
	 * that a vehicle will decrease each step
	 * 
	 * @return int
	 *           acceleration of the vehicle
	 */
	int getAcceleration();
	
}