/**
 *
 * This is my implementation of detecting a cycle in a directed graph
 * Time complexity is O(V + E)
 * @author Azra Irshad Rabbani, dsalgoazra@gmail.com
 */
package algorithm.graph;

import ds.graph.Graph;

import java.util.*;

/**
 * Given a directed graph and a node , find the shortest cycle in a graph with given node .
 */
public class TarjansCycleDetectionForStronglyConnectedGraph {
    private int shortestCycleSize = Integer.MAX_VALUE;
    private int longestCycleSize = Integer.MIN_VALUE;
    private Graph.Vertex[] shortestCycle;
    private Graph.Vertex[] longestCycle;

    Graph.Vertex[] findSmallestCycle(Graph g) {
       for(Graph.Vertex v :g.getVertices()) {//O(V)
           ArrayDeque<Graph.Vertex> vertexStack = new ArrayDeque<>();
           v.setVisited(false);//marking it unvisited so it can be traversed
           boolean hasCycle = hasCycleDirectedGraph(v, vertexStack);
           if(hasCycle) {
               if(vertexStack.size() < shortestCycleSize){
                   shortestCycleSize = vertexStack.size();
                   shortestCycle = new Graph.Vertex[shortestCycleSize];
                   vertexStack.toArray(shortestCycle);
               }
           }
       }
       return shortestCycle;
    }


    Graph.Vertex[] findLongestCycle(Graph g) {
        for(Graph.Vertex v :g.getVertices()) {//O(V)
            ArrayDeque<Graph.Vertex> vertexStack = new ArrayDeque<>();
            v.setVisited(false);//marking it unvisited so it can be traversed
            boolean hasCycle = hasCycleDirectedGraph(v, vertexStack);
            if(hasCycle) {
                if(vertexStack.size() > longestCycleSize){
                    longestCycleSize = vertexStack.size();
                    longestCycle = new Graph.Vertex[longestCycleSize];
                    vertexStack.toArray(longestCycle);
                }
            }
        }
        return shortestCycle;
    }


    public boolean hasCycle(Graph.Vertex source) {
        boolean hasCycle =  hasCycleDirectedGraph(source, new ArrayDeque<Graph.Vertex>());
        source.reset();
        return hasCycle;
    }

    //O(V + E)
    private boolean hasCycleDirectedGraph(Graph.Vertex source,  ArrayDeque<Graph.Vertex> adjVertex) {
        if(source.isVisited()) return false;
        source.setVisited(true);
        adjVertex.push(source);
        for(Graph.Edge adj : source.getEdges()) {//O(A) , where A is adjacent edges to source
            if(adj.getTo().isVisited() && adjVertex.contains(adj.getTo())) {//contains causes O(M) where M is the number of vertices on a path from the source node
                return true;
            } else if(!adj.getTo().isVisited() && hasCycleDirectedGraph(adj.getTo(), adjVertex)) {
                return true;
            }
        }
        adjVertex.pop();
        return false;
    }


    public static void main(String[] args) {
        int[][] edges =  {
                {0,      1},
                {0,      2},
                {2,      1},
                {3,      0},
                {1,      3},//shortest cycle
                {1,      4},
                {4,      5},
                {5,      6},
                {6,      7},
                {7,      1}//cycle

        };
        Graph g = new Graph(edges);
        TarjansCycleDetectionForStronglyConnectedGraph da = new TarjansCycleDetectionForStronglyConnectedGraph();
        Graph.Vertex[] cycle = da.findSmallestCycle(g);
        System.out.println("\n Shortest cycle : ");
        for (Graph.Vertex v : cycle) {
            System.out.print(v.getId() + " -> ");
        }
        g = new Graph(edges);
        cycle = da.findLongestCycle(g);
        System.out.println("\n Longest cycle : ");
        for (Graph.Vertex v : cycle) {
            System.out.print(v.getId() + " -> ");
        }
    }
}
