/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package search;

import graph.Graph;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeSet;

public class InformedSearch extends Search{
    static int[][] SLD;
    
    static void initialize(){
        SLD = new int[2][20];
        
        SLD[0][0] = 366; SLD[0][1] = 253;
        SLD[0][2] = 329; SLD[0][3] = 176;
        SLD[0][4] = 193; SLD[0][5] = 244;
        SLD[0][6] = 241; SLD[0][7] = 242;
        SLD[0][8] = 160; SLD[0][9] = 98;
        SLD[0][10] = 0;  SLD[0][11] = 374;
        SLD[0][12] = 380;SLD[0][13] = 77;
        SLD[0][14] = 80; SLD[0][15] = 199;
        SLD[0][16] = 226;SLD[0][17] = 234;
        SLD[0][18] = 151;SLD[0][19] = 161;
        
        SLD[1][0] = 366; SLD[1][1] = 253;
        SLD[1][2] = 329; SLD[1][3] = 176;
        SLD[1][4] = 193; SLD[1][5] = 244;
        SLD[1][6] = 241; SLD[1][7] = 242;
        SLD[1][8] = 0; SLD[1][9] = 98;
        SLD[1][10] = 160;  SLD[1][11] = 374;
        SLD[1][12] = 380;SLD[1][13] = 77;
        SLD[1][14] = 80; SLD[1][15] = 199;
        SLD[1][16] = 226;SLD[1][17] = 234;
        SLD[1][18] = 151;SLD[1][19] = 161;
        
    }
    public static LinkedList<String> bestFS(Graph G, String start, String goal){
        initialize();
        return bestFS(G,G.vert.indexOf(start),G.vert.indexOf(goal));
    }
    static LinkedList<String> bestFS(Graph G, int start, int goal){
        ArrayList<String> vert = G.vert;
        parents = new int[vert.size()];
        int node = start;
        parents[node] = Integer.MIN_VALUE;
        explored = new TreeSet();
        if(node == goal){
            //System.out.println(vert.get(node));
            return PrintPath(G,G.vert.get(start),G.vert.get(goal));
        }
        
        int goalDecider=0;
        
        if(vert.get(goal).equals("Craiova"))
            goalDecider = 1;
        
        spaceComplexity = 1;
        PriorityQueue<Integer> frontier = new PriorityQueue<>();
        frontier.add(SLD[goalDecider][start]);
        while(true){
            if(frontier.isEmpty()){
                System.out.println("FAILURE!");
                return null;
            }
            int temp = frontier.remove();
            int i=0;
            
            for(i=0;i<G.V;i++)
                if(SLD[goalDecider][i] == temp)
                    break;
            
            String tempS = G.vert.get(i);
            
            if(temp==0)
                return PrintPath(G,G.vert.get(start),tempS);
            
            explored.add(tempS);
            Queue<String> tempQ = G.expand(tempS);
            while(!tempQ.isEmpty()){
                String child = tempQ.remove();
                if(!explored.contains(child) && !frontier.contains(SLD[goalDecider][vert.indexOf(child)]))
                {
                    parents[vert.indexOf(child)] = vert.indexOf(tempS);
                    frontier.add(SLD[goalDecider][vert.indexOf(child)]);
                    spaceComplexity++;
                }
            }
        }
    }
    
    public static LinkedList<String> A_STAR(Graph G, String start, String goal){
        initialize();
        //return 
        return A_STAR(G,G.vert.indexOf(start),G.vert.indexOf(goal));
    }
    static LinkedList<String> A_STAR(Graph G, int start, int goal){
        ArrayList<String> vert = G.vert;
        parents = new int[vert.size()];
        parents[start] = Integer.MIN_VALUE;
        explored = new TreeSet();
        if(start == goal){
            //System.out.println(vert.get(start));return ;
            return PrintPath(G,G.vert.get(start),G.vert.get(goal));
        }
        
        int goalDecider=0;
        
        if(vert.get(goal).equals("Craiova"))
            goalDecider = 1;
        
        int gn = 0;
        
        spaceComplexity = 1;
        PriorityQueue<CityNode> frontier = new PriorityQueue<>();
        frontier.add(new CityNode(vert.get(start),gn+SLD[goalDecider][start]));
        while(true){
            if(frontier.isEmpty()){
                System.out.println("FAILURE!");
                return null;
            }
            CityNode tempCN = frontier.remove();
            
            String tempS = tempCN.cityName;
            gn = tempCN.cost-SLD[goalDecider][vert.indexOf(tempS)];
            if(vert.indexOf(tempS)==goal){
                //System.out.println(tempS);
                return PrintPath(G,G.vert.get(start),tempS);
            }
//            System.out.println(tempS+"=>"+gn+"=>"+tempCN.cost);
            explored.add(tempS);
            
            int bookKeeper = gn;
            
            Queue<String> tempQ = G.expand(tempS);
            while(!tempQ.isEmpty()){
                String child = tempQ.remove();
                gn = bookKeeper + G.adj[vert.indexOf(tempS)][vert.indexOf(child)];
                CityNode X = new CityNode(child,gn+SLD[goalDecider][vert.indexOf(child)]);
                if(!explored.contains(child) && !frontier.contains(X))
                {
                    parents[vert.indexOf(child)] = vert.indexOf(tempS);
                    frontier.add(X);
                    spaceComplexity++;
                }
                gn = bookKeeper;
            }
        }
    }
}
