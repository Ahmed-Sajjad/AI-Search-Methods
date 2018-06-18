/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package search;

import graph.Graph;
import java.util.Collections;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 *
 * @author Ahmed
 */
public class Search {
    public static int[] parents;
    public static class CityNode implements Comparable<CityNode>{
        String cityName;
        int cost;
        CityNode(String cityName,int cost){
            this.cityName = cityName;
            this.cost = cost;
        }
        @Override
        public int compareTo(CityNode C) {
            return Integer.compare(this.cost, C.cost);
        }
    }
    public static TreeSet<String> explored;
    public static int spaceComplexity;
    public static LinkedList<String> PrintPath(Graph G,String start,String goal){
        LinkedList<String> Path = new LinkedList<>();
        Path.add(goal);
        boolean found = false;
        int vertex = G.vert.indexOf(goal);
        while (!found)
        {
            found = (vertex == G.vert.indexOf(start));
            
            if(!found){
                Path.add(G.vert.get(parents[vertex]));
                vertex = parents[vertex];
            }
        }
        Collections.reverse(Path);
        return Path;
    }
    
}
