package ca.ubc.ece.cpen221.mp4.vehicles;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Direction;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.commands.MoveCommand;
import ca.ubc.ece.cpen221.mp4.commands.WaitCommand;

public class Car implements ArenaVehicles {

	private static final int INITIAL_HP = 120;
	private static final int DAMAGE = 50;                   //the damage a vehicle can deal to others
	private static final int COOLDOWN = 5;
	private static final ImageIcon bikeImage = Util.loadImage("cars.gif");
	
	private Location location;
	private int currHp;  //the health of the vehicle
	
	
	public Car (Location initialLocation) {
		this.location = initialLocation;
		this.currHp = INITIAL_HP;
	}
	
	@Override
	public int getHp() {
		return currHp;
	}

	@Override
	public int getDamage() {
		return DAMAGE;
	}

	@Override
	public void moveTo(Location targetLocation) {
		this.location = targetLocation;
		
	}

	@Override
	public int getMovingRange() {
		return 3;  //can only move one step
	}

	@Override
	public ImageIcon getImage() {
		return bikeImage;
	}

	@Override
	public String getName() {
		return "Bike";
	}

	@Override
	public Location getLocation() {
		return location;
	}

	@Override
	public int getStrength() {
		return 0;
	}

	@Override
	public void loseEnergy(int energy) {		
	}

	@Override
	public boolean isDead() {
		if(currHp < 0)
			return true;
		
		return false;
	}

	@Override
	public int getPlantCalories() {
		return 0;
	}

	@Override
	public int getMeatCalories() {
		return 0;
	}

	@Override
	public int getCoolDownPeriod() {
		return COOLDOWN;
	}

	@Override
	public Command getNextAction(World world) {
		Direction dir = Util.getRandomDirection();
		Location targetLocation = new Location(this.getLocation(), dir);
		if (Util.isValidLocation(world, targetLocation) && Util.isLocationEmpty(world, targetLocation)) {
			return new MoveCommand(this, targetLocation);
		}

		return new WaitCommand();
	}
	
	@Override
	public void turn() {
		
	}
}
