package ca.ubc.ece.cpen221.mp4.vehicles;

import ca.ubc.ece.cpen221.mp4.Actor;
import ca.ubc.ece.cpen221.mp4.items.MoveableItem;

public interface ArenaVehicles extends MoveableItem, Actor {

	//get current hp of vehicle 
	int getHp();
	
	//get damage of vehicle
	int getDamage();
	
	//change direction
	void turn();
	
}