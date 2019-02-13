/**
 * This is my implementation of Kruskal's algorithm to find minimum spanning tree.
 * It uses union-find data structure, it tries to find min spanning tree for the number of total vertices -1.
 * To validate MST check it should contain E-1 adj.
 *
 * Time Complexity : making set ->  O(V)
 *                   Sorting Edged - > O(ElogE)
 *                   For Each Edge  O(E)
 *                         Finding Set/Root and union -> O(logV)
 * O (E log E)
 *
 * @author Azra Irshad Rabbani, dsalgoazra@gmail.com
 */
package algorithm.graph;

import ds.graph.Graph;

import java.util.*;

public class KruskalsAlgorithmMinSpanningTree {

    public List<Graph.Edge> findMinimumSpanningTree(Graph g){
        List<Graph.Edge> mst = new ArrayList<>();
        makeSets(g.getVertices());//O(V)
        Comparator<Graph.Edge> c = (e1, e2) -> { return e1.compareTo(e2);};
        List<Graph.Edge> edges = g.getEdges();
        Collections.sort(edges, c );//O(ElogE)
        int cost = 0;
        int index = 0;
        //O(E log V) i.e. for  each edge O(E) union O(logV)
        while(mst.size() < g.getVertices().size() && index < edges.size()) {//O(E) for each  edge
            Graph.Edge e = edges.get(index++);
            Graph.Vertex fromVertexParent = findRoot(e.getFrom());
            Graph.Vertex toVertexParent = findRoot(e.getTo());
            if(!fromVertexParent.equals(toVertexParent)) {
                mst.add(e);
                cost += e.getWeight();
                union(fromVertexParent , toVertexParent); //O(logV)
            }
        }
        System.out.println("MST Cost : "+cost);
        return mst;
    }

    private Map<Graph.Vertex, Graph.Vertex> parent = new HashMap<>();

    private void makeSets(List<Graph.Vertex> vertexList) {
        for (Graph.Vertex v : vertexList) {//O(V)
            parent.put(v,v);
        }
    }

    private Graph.Vertex findRoot(Graph.Vertex child){
        Graph.Vertex p =  parent.get(child);
        if(p.equals(child)) {
            return child;
        }
        return findRoot(p);
    }

    private void union(Graph.Vertex a, Graph.Vertex b){
        Graph.Vertex pA = findRoot(a);
        Graph.Vertex pB = findRoot(b);
        if(!pA.equals(pB)) {
            parent.put(pA, pB);
        }
    }

    public static void main(String[] args){
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
        KruskalsAlgorithmMinSpanningTree k = new KruskalsAlgorithmMinSpanningTree();
        List<Graph.Edge> mst = k.findMinimumSpanningTree(new Graph(edges, false));
        System.out.println("Miniumum Spanning Tree ");
        for(int i = 0; i < mst.size(); i ++){
            System.out.println(mst.get(i).toString());
        }

    }

}
