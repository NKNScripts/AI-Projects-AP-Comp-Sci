/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package searchpuzzle.framework;

/**
 *
 * @author Christopher.Shafer
 */
public class Location {
    public int x,y;
    
    public Location(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public int getX(){return x;}
    
    public int getY(){return y;}
    
    
    
    
    public boolean equals(Location location){
        return location. x == x && location.y == y;
    }
    
    public String toString(){
        return x + "," + y;
    }
    
}
