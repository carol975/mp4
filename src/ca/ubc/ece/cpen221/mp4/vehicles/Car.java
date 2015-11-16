package ca.ubc.ece.cpen221.mp4.vehicles;

import javax.swing.ImageIcon;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;

public class Car extends AbstractArenaVehicles {


	private static final ImageIcon carImage = Util.loadImage("cars.gif");

	public Car(Location initialLocation){		
		super.setStrength(150);
		super.setCoolDown(1);
		super.setMovingRange(4);
		super.setDirection(Util.getRandomDirection());
		super.setLocation(initialLocation);
		super.setAcceleration(1);
	}


	@Override
	public ImageIcon getImage() {
		return carImage;
	}

	@Override
	public String getName() {

		return "Car";
	}
}
