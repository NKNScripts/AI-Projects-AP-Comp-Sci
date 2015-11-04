
package searchpuzzle.framework;

/**
 * This class holds the declaration for the tile used in the 3x3 grid
 * 
 * @author Christopher.Shafer
 */
public class Tile{
    private Location location;
    private Location goalLocation;
    private boolean blank = false;
    private int tileNumber;
    
    public Tile(Tile tile){
        this.location = tile.getLocation();
        this.goalLocation = tile.getGoalLocation();
        this.blank = tile.isBlank();
        this.tileNumber = tile.getTileNumber();
    }
    
    
    public Tile(Location location, Location goalLocation, int tileNumber){
        this.location = location;
        this.goalLocation = goalLocation;
        this.tileNumber = tileNumber;
    }
    
    public Tile(Location location, int tileNumber){
        this.location = location;
        this.goalLocation = location;
        this.tileNumber = tileNumber;
    }
    
    
    public Location getLocation(){return location;}
   
    public Location getGoalLocation() {return goalLocation;}
    
    public int getTileNumber() {return tileNumber;}
    
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
    
    public boolean adjacent(Tile tile){
        if(location.x == tile.getLocation().x){
            return Math.abs(location.y - tile.getLocation().y)<=1;
        }
        if(location.y == tile.getLocation().y){
            return Math.abs(location.x - tile.getLocation().x)<=1;
        }
        return false;
    }
    
    public int distanceToGoal(){
        return Math.abs(goalLocation.getX() - location.getX()) + Math.abs(goalLocation.getY() - location.getY());
    }
    
    @Override
    public boolean equals(Object o){
        Tile t = (Tile) o;
        return t.getLocation() == this.getLocation() && t.getGoalLocation() == t.getGoalLocation();
    }
    
    
    
    
    
}
