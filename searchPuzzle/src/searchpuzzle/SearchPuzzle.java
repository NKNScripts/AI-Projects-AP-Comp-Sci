/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package searchpuzzle;

import searchpuzzle.framework.Grid;

/**
 *
 * @author Christopher.Shafer
 */
public class SearchPuzzle {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Grid grid = new Grid();
        grid.init();
        grid.orderTiles();
        grid.displayGrid();
        
    }
}
