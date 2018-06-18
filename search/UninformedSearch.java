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

public class UninformedSearch extends Search{
    
    public static LinkedList<String> BFS(Graph G,String initial,String goal){
        return BFS(G,G.vert.indexOf(initial),G.vert.indexOf(goal));
    }
    private static LinkedList<String> BFS(Graph G,int initial,int goal){
        ArrayList<String> vert = G.vert;
        parents = new int[vert.size()];
        int node = initial;
        parents[node] = Integer.MIN_VALUE;
        explored = new TreeSet();
        if(node == goal){
            //System.out.println(vert.get(node));
            return PrintPath(G,G.vert.get(initial),G.vert.get(goal));
        }
        spaceComplexity = 1;
        Queue<String> frontier = new LinkedList();
        frontier.add(vert.get(node));
        while(true){
            if(frontier.isEmpty()){
                System.out.println("FAILURE!");
                return null;
            }
            
            String temp = frontier.remove();
            //System.out.print(temp+"=>");
            explored.add(temp);
            Queue<String> tempQ = G.expand(temp);
            while(!tempQ.isEmpty()){
                String child = tempQ.remove();
                if(!explored.contains(child) && !frontier.contains(child)){
                    parents[vert.indexOf(child)] = vert.indexOf(temp);
                    if(vert.indexOf(child) == goal){
                        //System.out.println(child);
                        return PrintPath(G,G.vert.get(initial),G.vert.get(goal));
                    }
                    frontier.add(child);
                    spaceComplexity++;
                }
            }
        }
    }
    
    public static LinkedList<String> uniformCostSearch(Graph G,String start,String Goal){
        return uniformCostSearch(G,G.vert.indexOf(start),G.vert.indexOf(Goal));
    }
    
    private static LinkedList<String> uniformCostSearch(Graph G,int start,int goal){
        ArrayList<String> vert = G.vert;
        parents = new int[vert.size()];
        int node = start;
        parents[node] = Integer.MIN_VALUE;
        explored = new TreeSet();
        if(node == goal){
            //System.out.println(vert.get(node));
            return PrintPath(G,G.vert.get(start),G.vert.get(goal));
        }
        int Cost = 0;
        spaceComplexity = 1;
        PriorityQueue<CityNode> frontier = new PriorityQueue<>();
        frontier.add(new CityNode(vert.get(node),0));
        while(true){
            if(frontier.isEmpty()){
                System.out.println("FAILURE!");
                return null;
            }
            CityNode temp = frontier.remove();
            
            if(vert.indexOf(temp.cityName)==goal){
                //System.out.println(temp.cityName);
                return PrintPath(G,G.vert.get(start),temp.cityName);
            }
            //System.out.print(temp.cityName+"=>");
            explored.add(temp.cityName);
            Queue<String> tempQ = G.expand(temp.cityName);
            while(!tempQ.isEmpty()){
                String t0 = tempQ.remove();
                int c0 = G.adj[vert.indexOf(temp.cityName)][vert.indexOf(t0)];
                CityNode temp1 = new CityNode(t0,temp.cost+c0);
                if(!explored.contains(t0) && !frontier.contains(temp1))
                {
                    parents[vert.indexOf(t0)] = vert.indexOf(temp.cityName);
                    frontier.add(temp1);
                    spaceComplexity++;
                }
                else
                {
                    Iterator<CityNode> it = frontier.iterator();
                    CityNode X;
                    while(it.hasNext()){
                        X = it.next();
                        if(X.cityName.equals(t0) && X.cost > c0)
                        {
                            frontier.remove(X);
                            break;
                        }
                    }
                    frontier.add(temp1);
                }
            }
        }
    }
    public static LinkedList<String> DFS(Graph G,String initial,String goal){
        return DFS(G,G.vert.indexOf(initial),G.vert.indexOf(goal));
    }
    
    private static LinkedList<String> DFS(Graph G, int initial, int goal){
        ArrayList<String> vert = G.vert;
        parents = new int[vert.size()];
        int node = initial;
        parents[node] = Integer.MIN_VALUE;
        explored = new TreeSet();
        if(node == goal){
            //System.out.println(vert.get(node));
            return PrintPath(G,vert.get(node),vert.get(goal));
        }
        spaceComplexity = 1;
        Stack<String> frontier = new Stack();
        frontier.push(vert.get(node));
        while(true){
            if(frontier.isEmpty()){
                System.out.println("FAILURE!");
                return null;
            }
            String temp = frontier.pop();
            //System.out.print(temp+"=>");
            explored.add(temp);
            Queue<String> tempQ = G.expand(temp);
            Collections.reverse((List<String>)tempQ);
            while(!tempQ.isEmpty()){
                String child = tempQ.remove();
                if(!explored.contains(child) && !frontier.contains(child)){
                    parents[vert.indexOf(child)] = vert.indexOf(temp);
                    if(vert.indexOf(child) == goal){
                        //System.out.println(child);
                        return PrintPath(G,vert.get(node),vert.get(goal));
                    }
                    frontier.push(child);
                    spaceComplexity++;
                }
            }
        }
    }
    
    public static LinkedList<String> IterativeDeepening(Graph G, String initial, String goal){
        if(initial.equals(goal))
            return PrintPath(G,initial,goal);
        int i=0;
        while(true){
            DepthLimitedSearch DLS = new DepthLimitedSearch(G,initial,i,goal);
            //explored = DLS.explored;
            if(DLS.result != null && DLS.result.size() > 1)
            {
                return DLS.result;
            }
            i++;
        }
    }
    
}