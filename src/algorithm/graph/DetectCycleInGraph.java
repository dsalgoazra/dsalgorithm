/**
 *
 * This is my implementation of detecting a cycle in a directed graph
 *
 * @author Azra Irshad Rabbani, dsalgoazra@gmail.com
 */
package algorithm.graph;

import ds.graph.Graph;

import java.util.*;

/**
 * Given a directed graph and a node , find the shortest cycle in a graph with given node .
 */
public class DetectCycleInGraph {
    private int shortestCycleSize = Integer.MAX_VALUE;
    private Graph.Vertex[] shortestCycle;

    Graph.Vertex[] findSmallestCycleDirectedGraph(Graph g) {
       for(Graph.Vertex v :g.getVertices()) {//O(V)
           Stack<Graph.Vertex> vertexStack = new Stack<>();
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

    public boolean hasCycle(Graph.Vertex source) {
        return hasCycleDirectedGraph(source, new Stack<Graph.Vertex>());
    }

    private boolean hasCycleDirectedGraph(Graph.Vertex source,  Stack<Graph.Vertex> adjVertex) {
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
        DetectCycleInGraph da = new DetectCycleInGraph();
        Graph.Vertex[] cycle = da.findSmallestCycleDirectedGraph(g);
        System.out.println("\n Shortest cycle : ");
        for (Graph.Vertex v : cycle) {
            System.out.print(v.getId() + " -> ");
        }
    }
}
