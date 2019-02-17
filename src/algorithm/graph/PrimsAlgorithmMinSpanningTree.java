/**
 * This is my implementation of finding minimum spanning tree using Prim's Algo.
 * Prim's algorithm works on undirected graph.
 * This is lazy version, using priority queue and adjacency list
 * It calculates the cost and construct the MST as well.
 *
 * Time Complexity O(ElogE)
 *
 * @author Azra Irshad Rabbani, dsalgoazra@gmail.com
 */

package algorithm.graph;

import ds.graph.Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class PrimsAlgorithmMinSpanningTree {

    public List<Graph.Edge> primMinimumSpanningCost(Graph g){
        List<Graph.Edge> minSpanningTreeNodes = new ArrayList<>();
        List<Graph.Edge> edges = g.getEdges();
        List<Graph.Edge> edge1 = edges.get(0).getFrom().getEdges();//it can be random or minimum based on overall edges in the graph
        PriorityQueue<Graph.Edge> pq = new PriorityQueue<Graph.Edge>((e1, e2) -> {return e1.compareTo(e2);});
        for (int i = 0 ; i < edge1.size(); i++) {
            pq.offer(edge1.get(i));
        }
        edges.get(0).getFrom().setVisited(true);
        int cost = 0;
        while(minSpanningTreeNodes.size() <= g.getVertices().size() && !pq.isEmpty()){
            Graph.Edge min = pq.poll(); //O(ElogE)
            Graph.Vertex minTo = min.getTo();
            if(!minTo.isVisited()) {
                minSpanningTreeNodes.add(min);
                cost += min.getWeight();
                minTo.setVisited(true);
                for (Graph.Edge e : minTo.getEdges()) {
                    if (!e.getFrom().isVisited() || !e.getTo().isVisited()) {
                        pq.offer(e);
                    }
                }
            }
        }
        System.out.println("MST Cost : "+cost);
        return minSpanningTreeNodes;
    }

    public static void main(String[] args) {

        int edges[][] = new int[][] {{0, 1, 2},
                {0,2, 3 },
                {0,3, 3},
                {1,2, 4},
                {1,4,3},
                {2,4,1},
                {2,5,6},
                {4,5,8},
                {5,6,9},
                {3, 5, 7}

        };
        PrimsAlgorithmMinSpanningTree p = new PrimsAlgorithmMinSpanningTree();
        List<Graph.Edge> mst = p.primMinimumSpanningCost(new Graph(edges, false));
        System.out.println("Miniumum Spanning Tree ");
        for(int i = 0; i < mst.size(); i ++){
            System.out.println(mst.get(i).toString());
        }
    }

}
