/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author AhmedSajjad
 */
public class Graph {
    public ArrayList<String> vert;
    public int V;
    public int E;
    public int[][] adj;
    public Graph(String[] vert){
        this.V = vert.length;
        this.E = 0;
        this.adj = new int[V][V];
        this.vert = new ArrayList<>(Arrays.asList(vert));
    }
    public void addEdge(String v,String w,int cost)
    {
        addEdge(vert.indexOf(v),vert.indexOf(w),cost);
    }
    private void addEdge(int v,int w,int cost)
    {
        adj[v][w] = cost;
        adj[w][v] = cost;
        E++;
    }
    public void display(){
        for(int i=0;i<V;i++)
            System.out.print("\t"+vert.get(i));
        
        System.out.println("");
        for(int i=0;i<V;i++){
            System.out.print(vert.get(i));
                for(int j=0;j<V;j++)
                    System.out.print("\t"+adj[i][j]);
            System.out.println("");
        }
    }
    public Queue expand(String U){
        return expand(vert.indexOf(U));
    }
    private Queue expand(int u){
        Queue<String> front = new LinkedList();
        for(int i=0;i<V;i++)
            if(adj[u][i]!=0)
                front.add(vert.get(i));
        //Collections.shuffle((List)front);
        return front;
    }
    public int displayPath(LinkedList<String> list){
        Iterator<String> iter = list.iterator();
        String X = iter.next();
        String Y = "";
        System.out.print(X);
        int cost = 0;
        while(iter.hasNext()){
            Y = iter.next();
            cost += adj[vert.indexOf(X)][vert.indexOf(Y)];
            System.out.print("=>"+Y);
            X = Y;
        }
        System.out.println("");
        return cost;
    }
    public Boolean changeCost(String v, String w, int newCost){
        return changeCost(vert.indexOf(v),vert.indexOf(w),newCost);
    }
    private Boolean changeCost(int v, int w, int newCost){
        adj[v][w] = newCost;
        adj[w][v] = newCost;
        return true;
    }
}