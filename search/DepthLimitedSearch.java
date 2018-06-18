/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package search;


import graph.Graph;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeSet;

/**
 *
 * @author AhmedSajjad
 */
public class DepthLimitedSearch{
    int cutoff;
    LinkedList<String> result;
    TreeSet<String> explored;
    int[] parents;
    DepthLimitedSearch(Graph G,String initial, int limit, String goal){
        this.cutoff = limit;
        explored = new TreeSet<>();
        parents = new int[G.V];
        result = RecursiveDLS(G,G.vert.indexOf(initial),limit,G.vert.indexOf(goal));
        //System.out.println(result);
    }
    LinkedList<String> RecursiveDLS(Graph G, int initial, int limit, int goal){
        if(initial == goal)
        {
//            System.out.println(G.vert.get(goal));
            return PrintPath(G,initial,goal);
        }
        else if(limit==0)
        {
            //System.out.println("No solution within CUTOFF");
            LinkedList<String> temp = new LinkedList<>();
            temp.add(Integer.toString(cutoff));
            return temp;
        }
        else{
            Boolean cutOff_occured=false;
            String curr = G.vert.get(initial);
            explored.add(curr);
//            System.out.print(curr+"=>");
            Queue<String> tempQ = G.expand(curr);
            while(!tempQ.isEmpty()){
                String child = tempQ.remove();
                if(!explored.contains(child)){
                    LinkedList<String> result = RecursiveDLS(G,G.vert.indexOf(child),limit-1,goal);
                    if(result != null)
                        if(result.size()==1 && result.element().equals(Integer.toString(cutoff))) cutOff_occured=true;
                    else{
                        result.addFirst(curr);
                        return result;
                    }
                }
            }
            return cutOff_occured ? result : null;
        }
    }
    LinkedList<String> PrintPath(Graph G,int start,int goal){
        LinkedList<String> Path = new LinkedList<>();
        Path.add(G.vert.get(goal));
        boolean found = false;
        int vertex = goal;
        while (!found)
        {
            found = (vertex == start);
            
            if(!found){
                Path.add(G.vert.get(parents[vertex]));
                vertex = parents[vertex];
            }
        }
        Collections.reverse(Path);
        return Path;
    }
}
