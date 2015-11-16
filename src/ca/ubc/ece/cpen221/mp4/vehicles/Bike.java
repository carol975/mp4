package ca.ubc.ece.cpen221.mp4.vehicles;

import javax.swing.ImageIcon;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;

public class Bike extends AbstractArenaVehicles {

	private static final ImageIcon bikeImage = Util.loadImage("bike.gif");

	public Bike(Location initialLocation){		
		super.setStrength(50);
		super.setCoolDown(2);
		super.setMovingRange(4);
		super.setDirection(Util.getRandomDirection());
		super.setLocation(initialLocation);
		super.setAcceleration(1);
	}


	@Override
	public ImageIcon getImage() {
		return bikeImage;
	}

	@Override
	public String getName() {
		return "Bike";
	}


}
