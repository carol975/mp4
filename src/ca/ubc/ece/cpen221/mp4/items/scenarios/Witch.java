package ca.ubc.ece.cpen221.mp4.items.scenarios;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;

public class Witch extends EnchantedAnimate {
    /**
     * Create a new Witch at <code>initialLocation</code>. The
     * <code>initialLocation</code> must be valid and empty.
     *
     * @param initialLocation
     *            the location where the Witch will be created
     */

    private static final ImageIcon Witch = Util.loadImage("witch.gif");

    public Witch(Location initialLocation, int movingRange) {
        super.setSTRENGTH(1000);
        super.setLOCATION(initialLocation);
        super.setISDEAD(false);
        super.setENERGY(super.getStrength());
        super.setMOVINGRANGE(movingRange);

    }

    @Override
    public ImageIcon getImage() {
        return Witch;
    }

    @Override
    public String getName() {
        return "Witch";
    }

}
