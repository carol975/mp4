package ca.ubc.ece.cpen221.mp4.vehicles;

import javax.swing.ImageIcon;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;

public class Tank extends AbstractArenaVehicles {

	private static final ImageIcon tankImage = Util.loadImage("tank.gif");

	public Tank(Location initialLocation){		
		super.setStrength(300);
		super.setCoolDown(6);
		super.setMovingRange(2);
		super.setDirection(Util.getRandomDirection());
		super.setLocation(initialLocation);
		super.setAcceleration(1);
	}

	@Override
	public ImageIcon getImage() {
		return tankImage;
	}

	@Override
	public String getName() {
		return "Tank";
	}


}
