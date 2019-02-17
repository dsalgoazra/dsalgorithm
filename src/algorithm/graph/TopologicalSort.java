/**
 * This is my implementation of topological sort
 * It works on Directed Acyclic graph (DAG), if a graph is not acyclic then it returns null.
 * It''s implementation is on many
 *
 * @author Azra Irshad Rabbani, dsalgoazra@gmail.com
 */

package algorithm.graph;

import ds.graph.Graph;

import java.util.Stack;

public class TopologicalSort {
    private Graph g;

    public TopologicalSort(Graph g){
        this.g = g;
    }

    public String[] topologicalSort(){
        if(g == null ) return null;
        int size = g.getVertices().size();
        if(size == 0) return null;
        String[] data = new String[size];
        Stack<Graph.Vertex> s = new Stack<>();
        Graph.Vertex source = g.getVertices().get(0);
        s.push(source);
        TarjansCycleDetectionForStronglyConnectedGraph d = new TarjansCycleDetectionForStronglyConnectedGraph();
        if(d.hasCycle(source)) {
            System.out.println("Graph has cycle cannot do topological sort.");
            return null;
        }
        g.resetVertices();
        // it deals with dependencies
        while(!s.isEmpty() && size >= 0) {
            Graph.Vertex n = s.peek(); //O(logV)
            if(!n.isVisited() && !n.getEdges().isEmpty()) {
                n.setVisited(true);
                for(Graph.Edge toAdj : n.getEdges()) {
                    if(!toAdj.getTo().isVisited() && !s.contains(toAdj.getTo())){
                        s.push(toAdj.getTo());
                    }
                }
            } else {
                 n = s.pop();
                 n.setVisited(true);
                 size--;
                 data[size] = n.getId();
            }
        }
        return data;
    }

    /**
     *                  0
     *                /    \
     *              1       3
     *              \      /
     *               \ 2  /
     *                 |
     *                 4
     *
     *
     */
    public static void main(String[] args) {
        int[][] edes = {
                {0,1},
                {0,3},
                {3,2},
                //{2,0},//add cycle
                {1,2},
                {2,4}
        };
        Graph g = new Graph(edes);
        TopologicalSort ts = new TopologicalSort(g);
        String[]  s =  ts.topologicalSort();
        if(s != null) {
            System.out.println("Topologically Sorted ");
            for (int i = 0; i < s.length; i++) {
                System.out.println(s[i]);
            }
        }
    }

}
