package ca.ubc.ece.cpen221.mp4.ai;

import java.util.Iterator;
import java.util.Set;

import ca.ubc.ece.cpen221.mp4.ArenaWorld;
import ca.ubc.ece.cpen221.mp4.Direction;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.commands.BreedCommand;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.commands.EatCommand;
import ca.ubc.ece.cpen221.mp4.commands.MoveCommand;
import ca.ubc.ece.cpen221.mp4.commands.WaitCommand;
import ca.ubc.ece.cpen221.mp4.items.Item;
import ca.ubc.ece.cpen221.mp4.items.animals.*;

/**
 * Your Fox AI.
 */
public class FoxAI extends AbstractAI {
    private int closest = 2; // max number; greater than fox's view range

    public FoxAI() {

    }

    @Override
    public Command getNextAction(ArenaWorld world, ArenaAnimal animal) {
        if (animal.isDead()) {
            return new WaitCommand();
        }
        // location, otherwise it waits.
        Direction dir = Util.getRandomDirection();
        Location targetLocation = new Location(animal.getLocation(), dir);
        Set<Item> possibleEats = world.searchSurroundings(animal);
        Location current = animal.getLocation();
        Iterator<Item> it = possibleEats.iterator();

        while (it.hasNext()) {
            Item item = it.next();
            if (item.getName()!= "grass" && item.getStrength() < animal.getStrength() && (current.getDistance(item.getLocation()) == 1)) {
                return new EatCommand(animal, item); // arena animals eat gnats
                                                     // and rabbits
            }

        }

        if (Util.isValidLocation(world, targetLocation) && this.isLocationEmpty(world, animal, targetLocation)) {
            if (animal.getEnergy() >= animal.getMinimumBreedingEnergy()*4) {
                return new BreedCommand(animal, targetLocation);
            }
            return new MoveCommand(animal, targetLocation);
        }

        return new WaitCommand();
    }

}
