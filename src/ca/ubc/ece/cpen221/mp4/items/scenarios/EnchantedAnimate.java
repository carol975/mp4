package ca.ubc.ece.cpen221.mp4.items.scenarios;

import java.util.Iterator;
import java.util.Set;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Direction;
import ca.ubc.ece.cpen221.mp4.Food;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.commands.MoveCommand;
import ca.ubc.ece.cpen221.mp4.commands.WaitCommand;
import ca.ubc.ece.cpen221.mp4.items.Item;
import ca.ubc.ece.cpen221.mp4.items.LivingItem;

public class EnchantedAnimate implements LivingItem {
    /**
     * Create a new EnchantedAnimate at <code>initialLocation</code>. The
     * <code>initialLocation</code> must be valid and empty.
     *
     * @param initialLocation
     *            the location where the EnchantedAnimate will be created
     */

    private static final ImageIcon EnchantedAnimate = Util.loadImage("unknown.gif");

    private int STRENGTH;
    private Location location;
    private boolean isDead;
    private int energy;
    private int movingrange;

    protected void setSTRENGTH(int i) {
        this.STRENGTH = i;
    }

    protected void setLOCATION(Location initialLocation) {
        this.location = initialLocation;
    }

    protected void setISDEAD(boolean isDead) {
        this.isDead = isDead;
    }

    protected void setENERGY(int i) {
        this.energy = i;
    }

    protected void setMOVINGRANGE(int i) {
        this.movingrange = i;

    }

    @Override
    public ImageIcon getImage() {
        return EnchantedAnimate;
    }

    @Override
    public String getName() {
        return "EnchantedAnimate";
    }

    @Override
    public Location getLocation() {
        return location;
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
    public void loseEnergy(int energy) {
        this.energy -= energy;
        if (this.energy <= 0) {
            isDead = true;
        }

    }

    @Override
    public boolean isDead() {
        return isDead;
    }

    @Override
    public void moveTo(Location targetLocation) {
        location = targetLocation;
    }

    @Override
    public int getCoolDownPeriod() {
        // Each EnchantedAnimate acts every 1-10 steps randomly.
        return Util.RAND.nextInt(10) + 1;
    }

    @Override
    public Command getNextAction(World world) {
        if(this.isDead){
            return new WaitCommand();
        }
        /*
         * The EnchantedAnimate first searches all adjacent animals (possibleVictims) and minus 15 points to their
         * energy
         * 
         * Then EnchantedAnimate selects a random empty adjacent location and check if
         * it is valid if valid, EnchantedAnimate moves to the location 
         * according to its cooldown period, otherwise it waits
         * 
         */
        Set<Item> possibleVictims = world.searchSurroundings(this.getLocation(), 1);
        Location current = this.getLocation();
        Iterator<Item> it = possibleVictims.iterator();
        while (it.hasNext()) {
            Item item = it.next();

            if (current.getDistance(item.getLocation()) == 1
                    && item.getName() != "grass"
                    && item.getName() != "Witch"
                    && item.getName() != "FortuneTeller" 
                    && item.getName() != "Bike" 
                    && item.getName() != "Car"
                    && item.getName() != "Tank") {
                item.loseEnergy(15);
                this.loseEnergy(this.getEnergy() / 5);
            }

        }


        Direction dir = Util.getRandomDirection();
        Location targetLocation = new Location(this.getLocation(), dir);
        if (Util.isValidLocation(world, targetLocation) && Util.isLocationEmpty(world, targetLocation)) {
            return new MoveCommand(this, targetLocation);
        }

        return new WaitCommand();
    }

    @Override
    public int getStrength() {
        return STRENGTH;
    }

    @Override
    public int getEnergy() {
        return this.energy;
    }

    @Override
    public LivingItem breed() {
        return null; // Never breed.
    }

    @Override
    public void eat(Food food) {
        // Never eats.
    }

    @Override
    public int getMovingRange() {
        return this.movingrange; 
    }

}
