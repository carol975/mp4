package ca.ubc.ece.cpen221.mp4.items.scenarios;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.commands.MoveCommand;
import ca.ubc.ece.cpen221.mp4.commands.WaitCommand;

public class FortuneTeller extends EnchantedAnimate {
    /**
     * Create a new FortuneTeller at <code>initialLocation</code>. The
     * <code>initialLocation</code> must be valid and empty.
     *
     * @param initialLocation
     *            the location where the FortuneTeller will be created
     */

    private static final ImageIcon FortuneTeller = Util.loadImage("fortuneteller.gif");

    public FortuneTeller(Location initialLocation, int movingRange) {
        super.setSTRENGTH(30);
        super.setLOCATION(initialLocation);
        super.setISDEAD(false);
        super.setENERGY(60);
        super.setMOVINGRANGE(movingRange);

    }

    @Override
    public ImageIcon getImage() {
        return FortuneTeller;
    }

    @Override
    public String getName() {
        return "FortuneTeller";
    }

    @Override
    public Command getNextAction(World world) {
        /*
         * The FortuneTeller selects a random empty location in the world and
         * check if it is valid. If valid, fortuneteller moves to a random empty
         * location in the world according to its cooldown period otherwise it
         * waits
         */

        Location targetLocation = Util.getRandomEmptyLocation(world);
        if (Util.isValidLocation(world, targetLocation) && Util.isLocationEmpty(world, targetLocation)) {
            return new MoveCommand(this, targetLocation);
        }

        return new WaitCommand();
    }

}
