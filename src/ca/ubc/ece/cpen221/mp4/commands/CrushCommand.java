package ca.ubc.ece.cpen221.mp4.commands;

import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.items.Item;
import ca.ubc.ece.cpen221.mp4.items.LivingItem;
import ca.ubc.ece.cpen221.mp4.items.MoveableItem;

public final class CrushCommand implements Command {

	
	private final Item animal;
	private final MoveableItem vehicle;
	
	
	/**
	 * Construct a {@link CrushCommand}, where <code> item </code> is the animal
	 * and <code> vehicle </code> is the vehicle. Remember that the animal must be
	 * adjacent to the vehicle, and the vihecle must have greater strength than the
	 * animal.
	 *
	 * @param item
	 *            the animal
	 * @param vehicle
	 *            : the vehicle
	 */
	public CrushCommand(Item item, MoveableItem vehicle) {
		this.animal = item;
		this.vehicle = vehicle;
	}
	
	@Override
	public void execute(World world) throws InvalidCommandException {
		//if animal is stronger than vehicle
		if(animal.getStrength() > vehicle.getStrength()) {
			throw new InvalidCommandException("Invalid CrushCommand 1");
		}
		
		//if animal is out of vehicle's moving range
		if(animal.getLocation().getDistance(vehicle.getLocation()) > vehicle.getMovingRange()){
			throw new InvalidCommandException("Invalid CrushCommand 2");
		}
		
		//animal dies
		animal.loseEnergy(Integer.MAX_VALUE);

	}

}
