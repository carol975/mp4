package ca.ubc.ece.cpen221.mp4.items.animals;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.ai.AI;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.items.LivingItem;

/**
 * The {@link Cat} is an {@link AbstractArenaAnimal} that eats grass and any animal that
 * has smaller strength than itself, except Gnat
 * it can be eaten by any animal that has a greater strength than itself.
 */
public class Cat extends AbstractArenaAnimal {
    private static final ImageIcon catImage = Util.loadImage("cat.gif");

    private final AI ai;

    /**
     * Create a new {@link Cats} with an {@link AI} at
     * <code> initialLocation </code>. The <code> initialLoation
     * </code> must be valid and empty.
     *
     * @param catAI
     *            : The AI designed for cats
     * @param initialLocation
     *            : the location where this cat will be created
     */
    public Cat(AI catAI, Location initialLocation) {
        this.ai = catAI;
        super.setINITIAL_ENERGY(40);
        super.setEnergy(40);
        super.setMAX_ENERGY(60);
        super.setSTRENGTH(60);
        super.setVIEW_RANGE(3);
        super.setMIN_BREEDING_ENERGY(10);
        super.setCOOLDOWN(2);
        super.setLocation(initialLocation);

    }

    @Override
    public LivingItem breed() {
        Cat child = new Cat(ai, this.getLocation());
        child.setEnergy(this.getEnergy() / 2);
        this.setEnergy(this.getEnergy() / 2);
        return child;
    }

    @Override
    public String getName() {
        return "Cat";
    }

    @Override
    public ImageIcon getImage() {
        return catImage;
    }

    @Override
    public Command getNextAction(World world) {
        Command nextAction = this.ai.getNextAction(world, this);
        this.setEnergy(this.getEnergy() - 1); // Loses 1 energy regardless of
                                              // action.
        return nextAction;
    }
}
