package ca.ubc.ece.cpen221.mp4.vehicles;

import java.util.Iterator;
import java.util.Set;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Direction;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.ai.AbstractAI;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.commands.MoveCommand;
import ca.ubc.ece.cpen221.mp4.commands.WaitCommand;
import ca.ubc.ece.cpen221.mp4.items.Item;

public class AbstractArenaVehicles extends AbstractAI implements ArenaVehicles {
	private int STRENGTH;
	private int COOLDOWN;
	private ImageIcon image;
	private int MOVING_RANGE;
	private int ACCELERATION;
	
	private Location location;
	private int cooldown = this.COOLDOWN;
	private Direction direction;
	
	protected void setStrength(int i){
		this.STRENGTH = i;
	}
	
	protected void setCoolDown(int i){
		this.COOLDOWN = i;
	}
	
	protected void setMovingRange(int i){
		this.MOVING_RANGE = i;
	}
	
	protected void setDirection(Direction i){
		this.direction = i;
	}
	
	protected void setLocation(Location i){
		this.location = i;
	}
	
	protected void setAcceleration(int i){
		this.ACCELERATION = i;
	}
	
	@Override
	public void moveTo(Location targetLocation) {
		this.location = targetLocation;
		
	}
	@Override
	public int getMovingRange() {
		return this.MOVING_RANGE;
	}
	
	@Override
	public Direction getDirection() {
		return this.direction;
	}
	
	@Override
	public int getAcceleration() {
		return this.ACCELERATION;
	}
	
	@Override
	public ImageIcon getImage() {
		return this.image;
	}
	@Override
	public String getName(){
		return null;
	}
	
	@Override
	public Location getLocation() {
		return this.location;
	}
	@Override
	public int getStrength() {
		return this.STRENGTH;
	}
	@Override
	public void loseEnergy(int energy) {		
	}
	
	@Override
	public boolean isDead() {
		return this.STRENGTH <= 0;
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
		return this.COOLDOWN;
	}
	@Override
	public Command getNextAction(World world) {
		
		Direction dir = direction;
		Location targetLocation = new Location(this.getLocation(), dir);
		
		Set<Item> items = world.searchSurroundings(targetLocation, 1);
		Iterator<Item> it = items.iterator();
		
		if(cooldown > 1) {
			cooldown -= this.getAcceleration();
		}
		
		if (Util.isValidLocation(world, targetLocation) && Util.isLocationEmpty(world, targetLocation)) {
			return new MoveCommand(this, targetLocation);
		}

		while(it.hasNext()){
			Item item = it.next();

			//turn if bumps into high strength item or animal
			if(item.getStrength() > this.getStrength() && (this.getLocation().getDistance(item.getLocation()) == 1)){
				dir = this.turn(world);
				targetLocation = new Location(this.getLocation(), dir);
				this.setStrength(this.getStrength()/5);;
				return new MoveCommand(this, targetLocation);
			}
			
			//destroy item or animal that has lower strength
			else if(item.getStrength() <= this.getStrength() && (this.getLocation().getDistance(item.getLocation()) == 1)){	
				item.loseEnergy(Integer.MAX_VALUE);
			}
			
		}	
			
		return new WaitCommand();
		
		
	}
	
	@Override
	public Direction turn(World world) {
		
		if(cooldown > 1) {
			return direction;
		}
		
		
		direction = Util.getRandomDirection();
		
		//targetLocation = Util.getRandomEmptyAdjacentLocation(world, this.getLocation());
		 
		while(!Util.isLocationEmpty(world, new Location(this.getLocation(), direction))){
		   direction = Util.getRandomDirection();
		}
		cooldown = this.COOLDOWN;
		return direction;
	}

	
}
