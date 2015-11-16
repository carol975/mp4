package ca.ubc.ece.cpen221.mp4;

import javax.swing.SwingUtilities;

import ca.ubc.ece.cpen221.mp4.ai.*;
import ca.ubc.ece.cpen221.mp4.items.Gardener;
import ca.ubc.ece.cpen221.mp4.items.Grass;
import ca.ubc.ece.cpen221.mp4.items.animals.*;
import ca.ubc.ece.cpen221.mp4.items.scenarios.*;
import ca.ubc.ece.cpen221.mp4.staff.WorldImpl;
import ca.ubc.ece.cpen221.mp4.staff.WorldUI;
import ca.ubc.ece.cpen221.mp4.vehicles.*;

/**
 * The Main class initialize a world with some {@link Grass}, {@link Rabbit}s,
 * {@link Fox}es, {@link Gnat}s, {@link Gardener}, etc.
 *
 * You may modify or add Items/Actors to the World.
 *
 */
public class Main {

    static final int X_DIM = 40;
    static final int Y_DIM = 40;
    static final int SPACES_PER_GRASS = 7;
    static final int INITIAL_GRASS = X_DIM * Y_DIM / SPACES_PER_GRASS;
    
    static final int INITIAL_GNATS = INITIAL_GRASS / 4;
    static final int INITIAL_RABBITS = INITIAL_GRASS / 4;
    static final int INITIAL_FOXES = INITIAL_GRASS / 32;

    static final int INITIAL_HAMSTERS = INITIAL_GRASS / 32;
    static final int INITIAL_CATS = INITIAL_GRASS / 32;
    static final int INITIAL_BLUGA_WHALES = INITIAL_GRASS / 32;

    static final int INITIAL_BIKES = INITIAL_GRASS / 32;
    //static final int INITIAL_BIKES = 1;
    static final int INITIAL_CARS = INITIAL_GRASS / 32;
    static final int INITIAL_TANKS = INITIAL_GRASS / 32;
    
    static final int INITIAL_WITCHES = 15;
    static final int INITIAL_FORTUNETELLERS = 25;
    static final int INITIAL_BUFFERS = 5;
    
    static final int ENCHANTED_ANIMATE_MOVING_RANGE = X_DIM*X_DIM + Y_DIM*Y_DIM;

    // static final int INITIAL_E
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().createAndShowWorld();
            }
        });
    }

    public void createAndShowWorld() {
        World world = new WorldImpl(X_DIM, Y_DIM);
        initialize(world);
        new WorldUI(world).show();
    }

    public void initialize(World world) {

        addGrass(world);
        world.addActor(new Gardener());

        addGnats(world);
        addRabbits(world);
        addFoxes(world);

        addHamsters(world);
        addCats(world);
        addBlugaWhales(world);

        addBike(world);
        addCar(world);
        addTank(world);

        addWitch(world);
        addFortuneTeller(world);
        addBuffer(world);

    }

    private void addWitch(World world) {
        for (int i = 0; i < INITIAL_WITCHES; i++) {
            Location loc = Util.getRandomEmptyLocation(world);
            Witch witch = new Witch(loc, ENCHANTED_ANIMATE_MOVING_RANGE);
            world.addItem(witch);
            world.addActor(witch);
        }
    }

    private void addFortuneTeller(World world) {

        for (int i = 0; i < INITIAL_FORTUNETELLERS; i++) {
            Location loc = Util.getRandomEmptyLocation(world);
            FortuneTeller fortuneteller = new FortuneTeller(loc, ENCHANTED_ANIMATE_MOVING_RANGE);
            world.addItem(fortuneteller);
            world.addActor(fortuneteller);
        }
    }

    private void addBuffer(World world) {
        for (int i = 0; i < INITIAL_BUFFERS; i++) {
            Location loc = Util.getRandomEmptyLocation(world);
            Buffer buffer = new Buffer(loc, ENCHANTED_ANIMATE_MOVING_RANGE);
            world.addItem(buffer);
            world.addActor(buffer);
        }
    }

    private void addGrass(World world) {
        for (int i = 0; i < INITIAL_GRASS; i++) {
            Location loc = Util.getRandomEmptyLocation(world);
            world.addItem(new Grass(loc));
        }
    }

    private void addGnats(World world) {
        for (int i = 0; i < INITIAL_GNATS; i++) {
            Location loc = Util.getRandomEmptyLocation(world);
            Gnat gnat = new Gnat(loc);
            world.addItem(gnat);
            world.addActor(gnat);
        }
    }

    private void addFoxes(World world) {
        FoxAI foxAI = new FoxAI();
        for (int i = 0; i < INITIAL_FOXES; i++) {
            Location loc = Util.getRandomEmptyLocation(world);
            Fox fox = new Fox(foxAI, loc);
            world.addItem(fox);
            world.addActor(fox);
        }
    }

    private void addRabbits(World world) {
        RabbitAI rabbitAI = new RabbitAI();
        for (int i = 0; i < INITIAL_RABBITS; i++) {
            Location loc = Util.getRandomEmptyLocation(world);
            Rabbit rabbit = new Rabbit(rabbitAI, loc);
            world.addItem(rabbit);
            world.addActor(rabbit);
        }
    }

    private void addHamsters(World world) {
        HamsterAI hamsterAI = new HamsterAI();
        for (int i = 0; i < INITIAL_HAMSTERS; i++) {
            Location loc = Util.getRandomEmptyLocation(world);
            Hamster hamster = new Hamster(hamsterAI, loc);
            world.addItem(hamster);
            world.addActor(hamster);
        }
    }

    private void addCats(World world) {
        CatAI catAI = new CatAI();
        for (int i = 0; i < INITIAL_HAMSTERS; i++) {
            Location loc = Util.getRandomEmptyLocation(world);
            Cat cat = new Cat(catAI, loc);
            world.addItem(cat);
            world.addActor(cat);
        }
    }

    private void addBlugaWhales(World world) {
        BlugaWhaleAI blugawhaleAI = new BlugaWhaleAI();
        for (int i = 0; i < INITIAL_HAMSTERS; i++) {
            Location loc = Util.getRandomEmptyLocation(world);
            BlugaWhale blugawhale = new BlugaWhale(blugawhaleAI, loc);
            world.addItem(blugawhale);
            world.addActor(blugawhale);
        }
    }

    private void addBike(World world) {

        for (int i = 0; i < INITIAL_BIKES; i++) {
            Location loc = Util.getRandomEmptyLocation(world);
            Bike bike = new Bike(loc);
            world.addItem(bike);
            world.addActor(bike);
        }
    }

    private void addCar(World world) {
        for (int i = 0; i < INITIAL_CARS; i++) {
            Location loc = Util.getRandomEmptyLocation(world);
            Car car = new Car(loc);
            world.addItem(car);
            world.addActor(car);
        }
    }

    private void addTank(World world) {
        for (int i = 0; i < INITIAL_TANKS; i++) {
            Location loc = Util.getRandomEmptyLocation(world);
            Tank tank = new Tank(loc);
            world.addItem(tank);
            world.addActor(tank);
        }
    }

}