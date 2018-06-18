/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import graph.Graph;
import search.Search;
import search.UninformedSearch;
import search.InformedSearch;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author Ahmed
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String[] vert = {"Arad","Sibiu","Timisoara","Fagaras",
                        "Rimnicu Vilcea","Lugoj","Mehadia","Drobeta",
                        "Craiova","Pitesti","Bucharest","Zerind",
                        "Oradea","Giurgiu","Urziceni","Vaslui",
                        "Iasi","Neamt","Hirsova","Eforie"};
        Graph G = new Graph(vert);
        G.addEdge("Arad", "Sibiu", 140);G.addEdge("Arad", "Timisoara", 118);G.addEdge("Sibiu", "Fagaras", 99);G.addEdge("Rimnicu Vilcea", "Sibiu", 80);
        G.addEdge("Timisoara", "Lugoj", 111);G.addEdge("Lugoj", "Mehadia", 70);G.addEdge("Rimnicu Vilcea", "Pitesti", 97);G.addEdge("Drobeta", "Craiova", 120);
        G.addEdge("Rimnicu Vilcea", "Craiova", 146);G.addEdge("Craiova", "Pitesti", 138);G.addEdge("Fagaras", "Bucharest", 211);G.addEdge("Mehadia", "Drobeta", 75);
        G.addEdge("Arad", "Zerind", 75);G.addEdge("Zerind", "Oradea", 71);G.addEdge("Vaslui", "Iasi", 92);G.addEdge("Oradea", "Sibiu", 151);
        G.addEdge("Giurgiu", "Bucharest", 90);G.addEdge("Urziceni", "Bucharest", 85);G.addEdge("Urziceni", "Hirsova", 98);G.addEdge("Pitesti", "Bucharest", 101);
        G.addEdge("Urziceni", "Vaslui", 142);G.addEdge("Neamt", "Iasi", 87);G.addEdge("Hirsova", "Eforie", 86);
        
        Scanner in = new Scanner(System.in);     
        String startCity="";
        String goalCity="";
        
        System.out.print("Enter Start City :");
        startCity = in.next();
        
        System.out.print("Enter Goal City (Craiova or Bucharest) :");
        goalCity = in.next();
        
        int X=0;
        Boolean FailureChecker = true;
        
        LinkedList<String> bfs = UninformedSearch.BFS(G, startCity, goalCity);
        FailureChecker = (bfs != null);
        System.out.print("\n1 - Breadth First Search = ");
        if(FailureChecker)
        {
            X = G.displayPath(bfs);
            System.out.println("Cost = "+X);
            System.out.println("Time Taken (nodes traversed): "+UninformedSearch.explored.size());
            System.out.println("Memory Used: "+UninformedSearch.spaceComplexity);
        }
        else
            System.out.println("Goal Not Found");
        
        LinkedList<String> dfs = UninformedSearch.DFS(G, startCity, goalCity);
            System.out.print("\n2 - Depth First Search = ");
            FailureChecker = (dfs != null);
            if(FailureChecker)
            {
                X = G.displayPath(dfs);
                System.out.println("Cost = "+X);
                System.out.println("Time Taken (nodes traversed): "+UninformedSearch.explored.size());
                System.out.println("Memory Used: "+UninformedSearch.spaceComplexity);
            }
            else
                System.out.println("Goal Not Found");

            LinkedList<String> ucs = UninformedSearch.uniformCostSearch(G, startCity, goalCity);
            System.out.print("\n3 - Uniform Cost Search = ");
            FailureChecker = (ucs != null);
            if(FailureChecker)
            {
                X = G.displayPath(ucs);
                System.out.println("Cost = "+X);
                System.out.println("Time Taken (nodes traversed): "+UninformedSearch.explored.size());
                System.out.println("Memory Used: "+UninformedSearch.spaceComplexity);
            }
            else
                System.out.println("Goal Not Found");

            LinkedList<String> itd = UninformedSearch.IterativeDeepening(G, startCity, goalCity);
            System.out.print("\n4 - Iterative Deepening = ");
            FailureChecker = (itd != null);
            if(FailureChecker)
            {
                X = G.displayPath(itd);
                System.out.println("Cost = "+X);
                System.out.println("Time Taken (nodes traversed): "+UninformedSearch.explored.size());
                System.out.println("Memory Used: "+UninformedSearch.spaceComplexity);
            }
            else
                System.out.println("Goal Not Found");

            LinkedList<String> BestFS = InformedSearch.bestFS(G, startCity, goalCity);
            System.out.print("\n5 - Greedy Best First search = ");
            FailureChecker = (BestFS != null);
            if(FailureChecker)
            {
                X = G.displayPath(BestFS);
                System.out.println("Cost = "+X);
                System.out.println("Time Taken (nodes traversed): "+InformedSearch.explored.size());
                System.out.println("Memory Used: "+InformedSearch.spaceComplexity);
            }
            else
                System.out.println("Goal Not Found");


            LinkedList<String> A_star = InformedSearch.A_STAR(G, startCity, goalCity);
            System.out.print("\n6 - A* search = ");
            FailureChecker = (A_star != null);
            if(FailureChecker)
            {
                X = G.displayPath(A_star);
                System.out.println("Cost = "+X);
                System.out.println("Time Taken (nodes traversed): "+InformedSearch.explored.size());
                System.out.println("Memory Used: "+InformedSearch.spaceComplexity);
            }
            else
                System.out.println("Goal Not Found");

    }
    
}
