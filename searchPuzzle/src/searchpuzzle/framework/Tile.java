
package searchpuzzle.framework;

/**
 * This class holds the declaration for the tile used in the 3x3 grid
 * 
 * @author Christopher.Shafer
 */
public class Tile {
    private Location location;
    private Location goalLocation;
    private boolean blank = false;
    
    public Tile(Location location, Location goalLocation){
        this.location = location;
        this.goalLocation = goalLocation;
    }
    
    public Tile(Location location){
        this.location = location;
        this.goalLocation = location;
    }
    
    
    public Location getLocation(){return location;}
    
    public Location getGoalLocation() {return goalLocation;}
    
    public boolean isAtGoal() {return goalLocation == location;}
    
    public boolean isBlank(){return blank;}
    
    public void setBlank(boolean blank){
        this.blank = blank;
    }
    
    public void setLocation(int x, int y){
        location.x = x;
        location.y = y;
    }
    
    public void setLocation(Location location){
        this.location = location;
    }
    
    public void moveX(int movement){
        location.x += movement;
        if(location.x > 3) location.x = 3;
        if(location.x < 0) location.x = 0;
    }
    
    public void moveY(int movement){
        
        location.y += movement;
        if(location.y > 3) location.y = 3;
        if(location.y < 0) location.y = 0;
    }
    
    
    
    
}
