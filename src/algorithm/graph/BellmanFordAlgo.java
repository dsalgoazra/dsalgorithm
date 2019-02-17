package algorithm.graph;

import ds.graph.Graph;

import java.util.*;

public class BellmanFordAlgo {

    private Graph g;

    public BellmanFordAlgo(Graph g){
        this.g = g;
    }

    public List<Graph.Vertex> bellmanFordShortestPath() {
        List<Graph.Vertex> path = new ArrayList<>();
       for(Graph.Vertex v : g.getVertices()) {
            v.setDistance(0);
            for (Graph.Edge edge : g.getEdges()) {
                int distance = edge.getFrom().getDistance() + edge.getWeight();
                if (distance < edge.getTo().getDistance()) {
                    edge.getTo().setDistance(distance);
                    if(!path.contains(edge.getTo())) {
                        path.add(edge.getTo());
                    }
                }
            }

        }
        //find negative cycle
        for (Graph.Edge edge : g.getEdges()) {
           if(edge.getFrom().getDistance() + edge.getWeight() < edge.getTo().getDistance() ) {
               System.out.println("Negative cycle found.");
               return null;
           }
        }

        return path;
    }

    public static void main(String[] args) {


        int[][] edges =  {
                {0,      1,      4},
                {0,      3,      2},
                {0,      2 ,     3},
                {2,      1,      1},
                {1,      4,      -6},
               // {4,      2,      5},
                {3,      2,      5}
        };

        Graph g = new Graph(edges);
        BellmanFordAlgo da = new BellmanFordAlgo(g);
        List<Graph.Vertex> shortestPathByVertex = da.bellmanFordShortestPath();
        if(shortestPathByVertex !=null) {
            System.out.println("\n Shortest path from Graph.Vertex : ");
            for (Graph.Vertex v : shortestPathByVertex) {
                System.out.print(v.getId() + " -> ");
            }
        }

    }
}
