package ca.ubc.ece.cpen221.mp4.items.scenarios;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.items.Item;

public class Earthquake implements Item{
    private final static ImageIcon earthquakeImage = Util.loadImage("bear.gif");
    private Location location;
    private boolean isDead;
    private int EffectRange = 3;
    public Earthquake(Location location){
        this.location = location;
        this.isDead = false;
    }
    
    public int getEffectRange(){
        return this.EffectRange;
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
    public ImageIcon getImage() {
        return earthquakeImage;
    }

    @Override
    public String getName() {
        return "earthquake";
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public int getStrength() {
        return 1000;
    }

    @Override
    public void loseEnergy(int energy) {
       isDead = true;
        
    }

    @Override
    public boolean isDead() {
        return isDead;
    }

}
