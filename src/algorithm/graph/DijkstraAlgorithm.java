/**
 *
 * This is my implementation of Dijkstra Algorithm using priority queue to find a shortest path from each vertex in the
 * graph.
 * Time Complexity : O(V) * O(ElogV)
 * @author Azra Irshad Rabbani, dsalgoazra@gmail.com
 */

package algorithm.graph;

import ds.graph.Graph;

import java.util.*;


public class DijkstraAlgorithm {

    //O(V * ElogV) for all vertices in the graph
    public Map<String, List<Graph.Vertex>> findShortestPathsFromEachVertexInTheGraph(Graph g){
        Map<String, List<Graph.Vertex>> shortestPathFromEachVertex = new HashMap<>();
        for (Graph.Vertex v : g.getVertices()) { //O(V), all vertices distances are already initialized with infinity
            shortestPathFromEachVertex.put(v.getId(),findShortestPathsFromSourceVertex(v));
            g.resetVertices();
        }
        return shortestPathFromEachVertex;
    }

    //O(ElogV) for single source
    public List<Graph.Vertex> findShortestPathsFromSourceVertex(Graph.Vertex v){
        List<Graph.Vertex> path = new ArrayList<>();
        PriorityQueue<Graph.Vertex> q = new PriorityQueue<>((a, b) -> { return a.compareTo(b) ; });//implement comparator
        v.setDistance(0);//initialize with 0 for the source node, other are set to infinity
        q.offer(v);
        while(!q.isEmpty()) {
            Graph.Vertex min = q.poll();//O(ElogV)
            if(!min.isVisited()) {
                min.setVisited(true);
                for(Graph.Edge adj : min.getEdges()) {
                    if(!adj.getTo().isVisited()) {//update distance if required
                        int newDistance = min.getDistance() + adj.getWeight();
                        if(newDistance < adj.getTo().getDistance()) {
                            adj.getTo().setDistance(newDistance);
                        }
                        q.offer(adj.getTo());
                    }
                }
                path.add(min);
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
                {1,      4,      6},
                {3,      2,      5}
        };

        Graph g = new Graph(edges);
        DijkstraAlgorithm da = new DijkstraAlgorithm();
        Map<String, List<Graph.Vertex>> shortestPathFromEachVertex = da.findShortestPathsFromEachVertexInTheGraph(g);
        for(Map.Entry<String, List<Graph.Vertex>> shortestPathByVertex : shortestPathFromEachVertex.entrySet()) {
            System.out.println("\n Shortest path from Graph.Vertex : "+shortestPathByVertex.getKey());
            for(Graph.Vertex v : shortestPathByVertex.getValue()){
                System.out.print(v.getId() + " -> ");
            }
        }
    }
}
