package ds.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph{

    private List<Edge> edges;
    private Map<String, Vertex> vertices;

    public Graph(){
        this.edges = new ArrayList<>();
        this.vertices = new HashMap<>();
    }

    /**
     * *       4        6
     *  0 --------> 1 ------> 4
     *  | \       Y
     *2 | 3\   1 /
     *  V   V  /`
     *  3--> 2
     *    5
     *
     * {
     *      from,   to,     weight
     *     {0,      1,      4},
     *     {0,      3,       2},
     *     {0,      2 ,     3},
     *     {2,      1,      1},
     *     {1,      4,      6},
     *     {3,      2,      5}
     * }
     *
     */
    public Graph(int[][] edgeList){
        this();
        if(edgeList == null || edgeList.length == 0) return;
        int m = edgeList.length;
        int n = edgeList[0].length;
        for(int i = 0; i < m; i++){
            String from = edgeList[i][0]+"";
            String to = edgeList[i][1]+"";
            int weight = 0;
            if(n > 2) {
                weight =  edgeList[i][2];
            }
            addEdge(from, to, weight);
        }
    }

    public void addEdge(String from, String to, int weight) {
        Vertex fromV = this.vertices.get(from);
        if(fromV == null) {
            fromV = new Vertex(from);
            this.vertices.put(from, fromV);
        }
        Vertex toV = this.vertices.get(to);
        if(toV == null) {
            toV = new Vertex(to);
            this.vertices.put(to, toV);
        }
        Edge e = new Edge(fromV, toV, weight);//TODO: check for duplicates
        fromV.addEdge(e);
        this.edges.add(e);
    }

    public List<Edge> getEdges() {
        return this.edges ;
    }

    public List<Vertex> getVertices() {
        return new ArrayList<>(vertices.values());
    }

    public void resetVertices() {
        for (Vertex v : this.vertices.values()) {
            v.reset();
        }
    }

    public static void main(String[] args) {
        int[][] edges =  {
                {0,      1,      4},
                {0,      3,       2},
                {0,      2 ,     3},
                {2,      1,      1},
                {1,      4,      6}
        };

        Graph g = new Graph(edges);
        System.out.println("Edges count : " + g.getEdges().size());
        System.out.println("Vertices count : " + g.getVertices().size());

        for(Edge e :  g.getEdges()){
            System.out.println("Edge : "+e.getFrom().getId() +" ------ "+e.getTo().getId()+ ", weight : "+e.getWeight());
        }


    }//End Of Graph


    /**
     * Edge
     */
    public class Edge implements Comparable<Edge>{
        private Vertex from;
        private Vertex to;
        private int weight;

        public Edge(Vertex from , Vertex to) {
            this.from = from;
            this.to = to;
        }

        public Edge(Vertex from , Vertex to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public Vertex getFrom() {
            return from;
        }

        public Vertex getTo() {
            return to;
        }

        public int getWeight() {
            return weight;
        }

        public int compareTo(Edge e){
            if(e == null) return 1;
            return Integer.compare(this.weight,e.weight);
        }

        public boolean equals (Edge e) {
            if(e == null) return false;
            boolean equals = !((e.getFrom() == null && this.getFrom() != null) || (e.getFrom() != null && this.getFrom() == null));
            equals &= !((e.getTo() == null && this.getTo() != null) || (e.getTo() != null && this.getTo() == null));
            equals = equals  && this.getFrom().equals(e.getFrom()) && this.getTo().equals(e.getTo());
            return equals;
        }

        public String toString(){
            String s = "";
            if(from != null) {
                s += from.getId() +" ---> ";
            } else {
                s +=  " null  ---> ";
            }

            if(to != null) {
                s += to.getId() +"  ";
            } else {
                s +=  " null  ";
            }

            s += "weight: "+weight;
            return s;
        }

    }



    /**
     * Vertex
     */


    public class Vertex implements Comparable<Vertex>{

        private String id;
        private int distance;
        private boolean visited;
        private List<Edge> edges;

        public Vertex(String id){
            this.id = id;
            this.distance = Integer.MAX_VALUE;
            this.edges = new ArrayList<>();
        }

        public String getId() {
            return id;
        }

        public List<Edge> getEdges() {
            return edges;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public void addEdge(Edge e){//TODO : avoid duplicates
            if(e == null) {
                return;
            }
            this.edges.add(e);
        }

        public boolean isVisited() {
            return visited;
        }

        public void setVisited(boolean visited) {
            this.visited = visited;
        }

        public void reset() {
            this.distance = Integer.MAX_VALUE;
            this.visited = false;
        }

        public int compareTo(Vertex x) {
            if(x == null) return -1;
            return Integer.compare(this.distance, x.distance);
        }
    }

}
