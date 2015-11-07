package ca.ubc.ece.cpen221.mp4.ai;

import java.util.Iterator;
import java.util.Set;

import ca.ubc.ece.cpen221.mp4.ArenaWorld;
import ca.ubc.ece.cpen221.mp4.Direction;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.commands.BreedCommand;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.commands.EatCommand;
import ca.ubc.ece.cpen221.mp4.commands.MoveCommand;
import ca.ubc.ece.cpen221.mp4.commands.WaitCommand;
import ca.ubc.ece.cpen221.mp4.items.Item;
import ca.ubc.ece.cpen221.mp4.items.animals.ArenaAnimal;

/**
 * 
 * CatAI
 *
 */

public class CatAI extends AbstractAI {
    private int closest = 10; // max number; greater than rabbit's view range
    private int temp;
    private boolean foxFound;
    private int breedable = 10;

    public CatAI() {
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
            if ((item.getName().equals("Gnat") || item.getName().equals("grass"))
                    && (current.getDistance(item.getLocation()) == 1)) {
                return new EatCommand(animal, item); // arena animals eat gnats
                                                     // and rabbits
            }

            if (item.getStrength() > animal.getStrength()) {
                Direction opDir = this.oppositeDir(dir);
                Location escape = new Location(animal.getLocation(), opDir);
                if (Util.isValidLocation(world, escape) && this.isLocationEmpty(world, animal, escape)) {
                    return new MoveCommand(animal, escape);
                }

            }

        }
        if (Util.isValidLocation(world, targetLocation) && this.isLocationEmpty(world, animal, targetLocation)) {
            if (animal.getEnergy() >= animal.getMinimumBreedingEnergy()*2/3) {
                return new BreedCommand(animal, targetLocation);
            }
            return new MoveCommand(animal, targetLocation);
        }

        return new WaitCommand();
    }

}
