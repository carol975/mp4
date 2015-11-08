package ca.ubc.ece.cpen221.mp4.vehicles;

import java.util.Iterator;
import java.util.Set;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Direction;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.commands.CrushCommand;
import ca.ubc.ece.cpen221.mp4.commands.MoveCommand;
import ca.ubc.ece.cpen221.mp4.commands.WaitCommand;
import ca.ubc.ece.cpen221.mp4.items.Item;

public class Bike implements ArenaVehicles {

	private static final int INITIAL_HP = 80;
	private static final int DAMAGE = 20;                   //the damage a vehicle can deal to others
	private static final int COOLDOWN = 2;
	private static final ImageIcon bikeImage = Util.loadImage("bike.gif");
	
	private Location location;
	private int currHp;  //the health of the vehicle

	public Bike(Location initialLocation){
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
		return 5;  //can only move one step
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
		
		Set<Item> items = world.searchSurroundings(targetLocation, getMovingRange());
		Iterator<Item> it = items.iterator();
		
		if (Util.isValidLocation(world, targetLocation) && Util.isLocationEmpty(world, targetLocation)) {
			return new MoveCommand(this, targetLocation);
		}
		
		while(it.hasNext()){
			Item item = it.next();
			System.out.println("Curr item = " + item.getName());
			if(item.getStrength() <= this.getStrength() && (this.getLocation().getDistance(item.getLocation()) <= this.getMovingRange())){
				return new CrushCommand(item, this);
			}
		}
		
		
		return new WaitCommand();
	}

	@Override
	public void turn() {
		
	}
	
}
