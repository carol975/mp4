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
 * The RabbitlAI evaluates the surrounding of the Rabbit passed
 * and provides corresponding commands.
 * If the Rabbit is dead, it returns a WaitCommand so the Rabbit does nothing
 * Else it searches all edible adjacent animals and returns EatCommand
 * If found edible animals in view range but not in adjacent place, move towards the edible animal
 * If animals in the surrounding have greater strength, it returns a MoveCommand that
 * moves the Rabbit to a valid and empty adjacent location in the opposite direction of the 
 * animals. 
 * The Rabbit breeds when its MinimumBreedingEnergy is reached.
 * Else the Rabbit moves to an valid and empty adjacent location or idles in the current location
 * 
 * @param world     Subset of the world visible to {@link ArenaAnimal}s.
 * @param animal    The Rabbit waiting for a command
 * @return Command  The corresponding command for the animal
 *
 */
public class RabbitAI extends AbstractAI {

    public RabbitAI() {
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
            if(item.getStrength() < animal.getStrength() && current.getDistance(item.getLocation())>1){
                Direction chaseDir = Util.getDirectionTowards(animal.getLocation(), item.getLocation());
                Location chase = new Location(animal.getLocation(),chaseDir);
                if (Util.isValidLocation(world, chase) && this.isLocationEmpty(world, animal, chase)) {
                    return new MoveCommand(animal, chase);
                }
            }

        }
        if (Util.isValidLocation(world, targetLocation) && this.isLocationEmpty(world, animal, targetLocation)) {
            if (animal.getEnergy() >= animal.getMinimumBreedingEnergy() * 24 / 8) {
                return new BreedCommand(animal, targetLocation);
            }
            return new MoveCommand(animal, targetLocation);
        }

        return new WaitCommand();
    }
}
