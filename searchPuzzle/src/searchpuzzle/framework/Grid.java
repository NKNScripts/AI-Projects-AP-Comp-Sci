
package searchpuzzle.framework;

import java.util.ArrayList;
import java.util.Random;

/**
 * The container of the 8 tile pieces and one blank
 * @author Christopher.Shafer
 * 
 * 
    * todo:
    *  Implement search algorithms
         
 */
public class Grid implements Comparable<Grid> {
    
    public Tile[] tiles = new Tile[9];
    private ArrayList<Location> locations = new ArrayList<>();
    private int distance = 0;
    
    public Grid(){
        
        
    }
    
    public Grid(Tile[] tiles){
        for (int i = 0; i < 9; i++){
            this.tiles[i] = new Tile(tiles[i]);
        }
    }
    
    
    public void init(){
        for(int k = 0; k < 9; k++) {
            for(int i = 1; i <= 3; i++){
                for(int j = 1; j <= 3; j++){
                    locations.add(new Location(i,j));
                    tiles[k] = new Tile(locations.get(k), k+1);
                    if(k==8){
                        tiles[k].setBlank(true);
                    }
                    k++;
                }
            }
        }
        //Fisher-Yates shuffle
        System.out.println("here");
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
        int n = tiles.length;
        int j;
        for(int k = n; k >= 0; k--){
            for(int i = 0; i < n - 1; i++){
                j = i + 1;
                if(tiles[i].getLocation().getY() > tiles[j].getLocation().getY()){
                    Tile temp;
                    temp = new Tile(tiles[i]);
                    tiles[i] = new Tile(tiles[j]);
                    tiles[j] = temp;
                } else if((tiles[i].getLocation().getY() == tiles[j].getLocation().getY())&&(tiles[i].getLocation().getX() > tiles[j].getLocation().getX())){
                    Tile temp;
                    temp = new Tile(tiles[i]);
                    tiles[i] = new Tile(tiles[j]);
                    tiles[j] = temp;
                }
            }
        }
        
    }
        
    public void displayGrid(){
        orderTiles();
        int count = 0;
        System.out.println("------LOCATIONS------");
        for(int i = 1; i <= 3; i++){
            for(int j = 1; j <= 3; j++){
                System.out.print(tiles[count++].getGoalLocation() + "\t");
            }
            System.out.println();
        }

        



    }
    
    public boolean goalTest(){
        for(Tile t : tiles){
            if(!t.isAtGoal())
                return false;
        }
        return true;
        
    }
    
    public int getDistance(){return distance;}
    
    public void setDistance(int distance){
        this.distance = distance;
    }
    
    public void swapTiles(int tileLocation, int desiredLocation){
        if(tiles[desiredLocation].isBlank() || tiles[tileLocation].isBlank()){
            System.out.println("Swapping");
            System.out.println(tiles[tileLocation].getGoalLocation());
            System.out.println(tiles[desiredLocation].getGoalLocation());
            
            Tile t = new Tile(tiles[desiredLocation]);
            this.tiles[desiredLocation] = new Tile(tiles[tileLocation]);
            this.tiles[tileLocation] = t;
            System.out.println(tiles[tileLocation].getGoalLocation());
            System.out.println(tiles[desiredLocation].getGoalLocation());
        }
    }
    

        
    
    
    public int getIndex(Tile tile){
        for(int i = 0; i < 9; i++){
            if(tile.equals(tiles[i]))
                return i;
        }
        return -1;
    }
    
    public Grid[] getChildBoards(){
        //Tiles are not swapping
        ArrayList<Grid> children = new ArrayList<Grid>();
        
        for(Tile t : tiles){
            if(!t.isBlank()) continue;
            Tile[] neighbors = getNeighbors(t);
            for(Tile ti : neighbors){
                
                Grid newChild = new Grid(tiles);
                newChild.displayGrid();
                System.out.println(newChild.getIndex(ti));
                newChild.swapTiles(newChild.getIndex(t), newChild.getIndex(ti));
                System.out.println(newChild.getIndex(ti));
                newChild.displayGrid();
                newChild.setDistance(distance + 1);
                children.add(newChild);
            }
        }
        return children.toArray(new Grid[0]);
    }
    
    public Tile[] getNeighbors(Tile tile){
        ArrayList<Tile> neighbors = new ArrayList<Tile>();
        
        for(Tile testTile : tiles){
            if(tile.adjacent(testTile) && !tile.equals(testTile)){
                neighbors.add(testTile);
            }
        }

        return neighbors.toArray(new Tile[0]);
        
    }
    
    public boolean checkLocation(Location location){
        for(Tile tile : tiles){
            if(tile.getLocation().equals(location))
                return true;
        }
        return false;
        
    }
    
    public int getManhattanDistance(){
        int mDistance = 0;
        for(Tile t : tiles){
            if(t.isBlank()) continue;
            mDistance += t.distanceToGoal();
        }
        return mDistance;
    }

    @Override
    public int compareTo(Grid o) {
        int totalDistance = distance + getManhattanDistance();
        int totalCompareDistance = o.getDistance() + o.getManhattanDistance();
        return totalCompareDistance > totalDistance ? -1 : 1;
    }
    
    @Override
    public boolean equals(Object o){
        Grid g = (Grid) o;
        g.orderTiles();
        this.orderTiles();
        for(int i = 0; i < tiles.length; i++){
            if(tiles[i].getGoalLocation() != g.tiles[i].getGoalLocation())
                return false;
        }
        return true;
        
    }
}
