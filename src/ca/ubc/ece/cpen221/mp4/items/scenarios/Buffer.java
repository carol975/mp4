package ca.ubc.ece.cpen221.mp4.items.scenarios;

import java.util.Iterator;
import java.util.Set;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.commands.MoveCommand;
import ca.ubc.ece.cpen221.mp4.commands.WaitCommand;
import ca.ubc.ece.cpen221.mp4.items.Item;

public class Buffer extends EnchantedAnimate {
    /**
     * Create a new Buffer at <code>initialLocation</code>. The
     * <code>initialLocation</code> must be valid and empty.
     *
     * @param initialLocation
     *            the location where the Buffer will be created.
     */

    private static final ImageIcon Buffer = Util.loadImage("buffer.gif");

    public Buffer(Location initialLocation, int movingRange) {
        super.setSTRENGTH(1000);
        super.setLOCATION(initialLocation);
        super.setISDEAD(false);
        super.setENERGY(super.getStrength());
        super.setMOVINGRANGE(movingRange);

    }

    @Override
    public ImageIcon getImage() {
        return Buffer;
    }

    @Override
    public String getName() {
        return "Buffer";
    }

    @Override
    public Command getNextAction(World world) {
        /*
         * The Buffer first searches all adjacent animals and add 10 points to
         * their energy
         * 
         * Then Buffer selects a random empty location in the world and check if
         * it is valid if valid, Buffer moves to a random empty location in the
         * world according to its cooldown period, otherwise it waits
         * 
         */
        Location targetLocation = Util.getRandomEmptyLocation(world);
        // PITF possible items to proliferate
        Set<Item> PITF = world.searchSurroundings(this.getLocation(), 1);
        Location current = this.getLocation();
        Iterator<Item> it = PITF.iterator();
        while (it.hasNext()) {
            Item item = it.next();

            if (current.getDistance(item.getLocation()) == 1 
                    && item.getName() != "grass" && item.getName() != "Witch"
                    && item.getName() != "FortuneTeller" 
                    && item.getName() != "Bike" && item.getName() != "Car"
                    && item.getName() != "Tank") {
                item.loseEnergy(-10);
            }

        }

        if (Util.isValidLocation(world, targetLocation) && Util.isLocationEmpty(world, targetLocation)) {
            return new MoveCommand(this, targetLocation);
        }

        return new WaitCommand();
    }
}
