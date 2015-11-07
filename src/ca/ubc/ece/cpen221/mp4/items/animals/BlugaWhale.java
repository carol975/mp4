package ca.ubc.ece.cpen221.mp4.items.animals;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Food;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.ai.AI;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.items.Grass;
import ca.ubc.ece.cpen221.mp4.items.LivingItem;


/**
 * The {@link BlugaWhale} is an {@link ArenaAnimal} that eats {@link Grass} and can
 * be eaten by {@link Fox}.
 */

public class BlugaWhale implements ArenaAnimal{
    private static final int INITIAL_ENERGY = 30;
    private static final int MAX_ENERGY = 250;
    private static final int STRENGTH = 250;
    private static final int MIN_BREEDING_ENERGY = 180;
    private static final int VIEW_RANGE = 5;
    private static final int COOLDOWN = 10;
    private static final ImageIcon blugawhaleImage = Util.loadImage("blugawhale.gif");

    private final AI ai;

    private Location location;
    private int energy;

    /**
     * Create a new {@link BlugaWhale} with an {@link AI} at
     * <code> initialLocation </code>. The <code> initialLoation
     * </code> must be valid and empty.
     *
     * @param blugawhaleAI
     *            : The AI designed for blugawhales
     * @param initialLocation
     *            : the location where this blugawhale will be created
     */
    public BlugaWhale(AI blugawhaleAI, Location initialLocation) {
        ai = blugawhaleAI;
        location = initialLocation;
        energy = INITIAL_ENERGY;
    }

    @Override
    public LivingItem breed() {
        BlugaWhale child = new BlugaWhale(ai, location);
        child.energy = energy / 2;
        this.energy = energy / 2;
        return child;
    }

    @Override
    public void eat(Food food) {
        // Note that energy does not exceed energy limit.
        energy = Math.min(MAX_ENERGY, energy + food.getPlantCalories());
    }

    @Override
    public int getCoolDownPeriod() {
        return COOLDOWN;
    }

    @Override
    public int getEnergy() {
        return energy;
    }

    @Override
    public ImageIcon getImage() {
        return blugawhaleImage;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public int getMaxEnergy() {
        return MAX_ENERGY;
    }

    @Override
    public int getMeatCalories() {
        // The amount of meat calories it provides is equal to its current
        // energy.
        return energy;
    }

    @Override
    public int getMinimumBreedingEnergy() {
        return MIN_BREEDING_ENERGY;
    }

    @Override
    public int getMovingRange() {
        return 1; // Can only move to adjacent locations.
    }

    @Override
    public String getName() {
        return "BlugaWhale";
    }

    @Override
    public Command getNextAction(World world) {
        Command nextAction = ai.getNextAction(world, this);
        this.energy--; // Loses 1 energy regardless of action.
        return nextAction;
    }

    @Override
    public int getPlantCalories() {
        // This BLugaWhale is not a plant.
        return 0;
    }

    @Override
    public int getStrength() {
        return STRENGTH;
    }

    @Override
    public int getViewRange() {
        return VIEW_RANGE;
    }

    @Override
    public boolean isDead() {
        return energy <= 0;
    }

    @Override
    public void loseEnergy(int energyLoss) {
        this.energy -= energyLoss;
    }

    @Override
    public void moveTo(Location targetLocation) {
        location = targetLocation;

    }
}
