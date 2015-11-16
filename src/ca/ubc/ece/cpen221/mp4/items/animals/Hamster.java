package ca.ubc.ece.cpen221.mp4.items.animals;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.ai.AI;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.items.LivingItem;

/**
 * The {@link Hamster} is an {@link AbstractArenaAnimal} that eats grass and any animal that
 * has smaller strength than itself, except Gnat
 * it can be eaten by any animal that has a greater strength than itself.
 */

public class Hamster extends AbstractArenaAnimal {
    private static final ImageIcon hamsterImage = Util.loadImage("hamster.gif");

    private final AI ai;

    /**
     * Create a new {@link Hamster} with an {@link AI} at
     * <code> initialLocation </code>. The <code> initialLoation
     * </code> must be valid and empty.
     *
     * @param hamsterAI
     *            : The AI designed for hamsters
     * @param initialLocation
     *            : the location where this hamster will be created
     */
    public Hamster(AI hamsterAI, Location initialLocation) {
        this.ai = hamsterAI;
        super.setINITIAL_ENERGY(30);
        super.setEnergy(30);
        super.setMAX_ENERGY(45);
        super.setSTRENGTH(50);
        super.setVIEW_RANGE(2);
        super.setMIN_BREEDING_ENERGY(7);
        super.setCOOLDOWN(2);
        super.setLocation(initialLocation);

    }

    @Override
    public LivingItem breed() {
        Hamster child = new Hamster(ai, this.getLocation());
        child.setEnergy(this.getEnergy() / 2);
        this.setEnergy(this.getEnergy() / 2);
        return child;
    }

    @Override
    public String getName() {
        return "Hamster";
    }

    @Override
    public ImageIcon getImage() {
        return hamsterImage;
    }

    @Override
    public Command getNextAction(World world) {
        Command nextAction = this.ai.getNextAction(world, this);
        this.setEnergy(this.getEnergy() - 1); // Loses 1 energy regardless of
                                              // action.
        return nextAction;
    }

}
