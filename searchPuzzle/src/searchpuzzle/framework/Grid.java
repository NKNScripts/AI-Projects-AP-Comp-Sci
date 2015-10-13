/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package searchpuzzle.framework;

import java.util.ArrayList;
import java.util.Random;

/**
 * The container of the 8 tile pieces and one blank
 * @author Christopher.Shafer
 * 
 * 
    * todo:
    *  Fix grid display.  Not showing all 9 elements
    *  Implement grid display
    *  Implement search algorithms
         
 */
public class Grid {
    
    public Tile[] tiles = new Tile[9];
    private ArrayList<Location> locations = new ArrayList<>();
    
    
    public void init(){
        System.out.println("Initializing nine tiles");
        for(int k = 0; k < 9; k++) {
            for(int i = 1; i <= 3; i++){
                for(int j = 1; j <= 3; j++){
                    locations.add(new Location(i,j));
                    tiles[k] = new Tile(locations.get(k));
                    if(k==8){
                        tiles[k].setBlank(true);
                    }
                    k++;
                }
            }
        }
        System.out.println("Shuffling nine tiles");
        //Fisher-Yates shuffle
        Random rand = new Random();
        for(int i = 0; i < tiles.length; i++){
            int index = rand.nextInt(locations.size());
            tiles[i].setLocation(locations.get(index));
            locations.remove(index);
            
            
        }
        
        
        
        
        



    }
    /*
     * Bubble sort algorithm to realign tiles to their correct array positions
     */  
    public void orderTiles(){
        System.out.println("Re-ordering tile array indexes");
        int n = tiles.length;
        int j;
        for(int k = n; k >= 0; k--){
            for(int i = 0; i < n - 1; i++){
                j = i + 1;
                if(tiles[i].getLocation().getY() > tiles[j].getLocation().getY()){
                    Tile temp;
                    temp = tiles[i];
                    tiles[i] = tiles[j];
                    tiles[j] = temp;
                } else if((tiles[i].getLocation().getY() == tiles[j].getLocation().getY())&&(tiles[i].getLocation().getX() > tiles[j].getLocation().getX())){
                    Tile temp;
                    temp = tiles[i];
                    tiles[i] = tiles[j];
                    tiles[j] = temp;
                }
            }
        }
        
    }
        
    public void displayGrid(){
        
        int count = 0;
        System.out.println("------LOCATIONS------");
        for(int i = 1; i <= 3; i++){
            for(int j = 1; j <= 3; j++){
                System.out.print(tiles[count++].getLocation() + "\t");
            }
            System.out.println();
        }
        
        System.out.println("------BLANK------");
        count = 0;
        for(int i = 1; i <= 3; i++){
            for(int j = 1; j <= 3; j++){
                System.out.print(tiles[count++].isBlank() + "\t");    
            }
            System.out.println();
        }
        
        System.out.println("------GOAL LOCATIONS------");
        count = 0;
        for(int i = 1; i <= 3; i++){
            for(int j = 1; j <= 3; j++){
                System.out.print(tiles[count++].getGoalLocation()+ "\t");
            }
            System.out.println();
        }
    }
    
    public void swapTiles(int tileLocation, int desiredLocation){
        if(tiles[desiredLocation].isBlank()){
            Tile t = tiles[desiredLocation];
            tiles[desiredLocation] = tiles[tileLocation];
            tiles[tileLocation] = t;
        }
    }
    
    public boolean checkLocation(Location location){
        for(Tile tile : tiles){
            if(tile.getLocation().equals(location))
                return true;
        }
        return false;
    }
    
}
