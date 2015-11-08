package ca.ubc.ece.cpen221.mp4.items.animals;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.ai.AI;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.items.Grass;
import ca.ubc.ece.cpen221.mp4.items.LivingItem;

/**
 * The {@link BlugaWhale} is an {@link ArenaAnimal} that eats {@link Grass} and
 * can be eaten by {@link Fox}.
 */

public class BlugaWhale extends AbstractArenaAnimal {
    private static final ImageIcon blugawhaleImage = Util.loadImage("blugawhale.gif");

    private final AI ai;


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

        this.ai = blugawhaleAI;
        super.setINITIAL_ENERGY(100);
        super.setEnergy(100);
        super.setMAX_ENERGY(250);
        super.setSTRENGTH(250);
        super.setVIEW_RANGE(5);
        super.setMIN_BREEDING_ENERGY(180);
        super.setCOOLDOWN(10);
        super.setLocation(initialLocation);

    }

    @Override
    public LivingItem breed() {
        BlugaWhale child = new BlugaWhale(ai, this.getLocation());
        child.setEnergy(this.getEnergy()/ 2);
        this.setEnergy(this.getEnergy() / 2);
        return child;
    }

    @Override
    public String getName() {
        return "BlugaWhale";
    }
    
    @Override
    public ImageIcon getImage(){
        return blugawhaleImage;
    }
    
    @Override
    public boolean isDead() {
        return this.getEnergy() <= 0;
    }
    
    @Override
    public Command getNextAction(World world) {
        Command nextAction = this.ai.getNextAction(world, this);
        this.setEnergy(this.getEnergy()-1); // Loses 1 energy regardless of action.
        return nextAction;
    }
}
