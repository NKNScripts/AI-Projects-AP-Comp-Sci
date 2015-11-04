/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package searchpuzzle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
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

        //System.out.println();
        solveAStar();
        
        
                
    }
    
    public static void solveAStar(){
        Queue<Grid> frontier = new PriorityQueue<>();
        ArrayList<Grid> explored = new ArrayList<>();
        ArrayList<Grid> path = new ArrayList<>();
        Grid start = new Grid();
        start.init();
        frontier.offer(start);
        while(!frontier.isEmpty()){
            Grid current = frontier.poll();
            if(current.goalTest()){
                System.out.println("hello");
                return;
            }
            explored.add(current);
            Grid[] neighbors = current.getChildBoards();
            for(Grid g : neighbors){
                
                //g.displayGrid();
                //current.displayGrid();
                int newCost = g.getDistance() + g.getManhattanDistance();
                int priorCost = current.getDistance() + current.getManhattanDistance();
                
                if(newCost > priorCost) continue;
                System.out.println("here1");
                if(frontier.contains(g)) continue;
                System.out.println("here2");
                if(explored.contains(g)) continue;
                System.out.println("here3");
                frontier.add(g);
                path.add(g);
                explored.add(current);
                
                
            }
            System.out.println(path.size());
        }
        System.out.println("done");
    }
}
