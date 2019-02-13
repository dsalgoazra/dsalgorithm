/**
 * This is my implementation of Travelling sales man problem, The problems says that in a given undirected graph string
 * from any vertex find a path that gives a miniumum cost when you reach bak to the source vertex.
 *
 *
 *  NP-hard problem in combinatorial optimization, important in operations research and theoretical computer science.
 * @author Azra Irshad Rabbani, dsalgoazra@gmail.com
 */

package algorithm.DP;

import java.util.Arrays;
//TODO: Complete implementation
public class TravellingSalesmanProblem {

    private int[][] dp ;
    private int M = 0;


    public TravellingSalesmanProblem(int[][] edges) {
       if(edges == null || edges.length ==0) return;
        initCostMetrics(edges);
    }

    private void initCostMetrics(int[][] edges) {
        M = edges.length;
        if(edges[0].length < 3) return; //expected input is {from  ,to , weight}
        dp = new int[M][M];
        Arrays.fill(dp ,Integer.MAX_VALUE );
        for(int i = 0 ; i < M; i++){
            int from = edges[i][0];
            int to = edges[i][1];
            if(from == to) {
                dp[from][to] = 0;
            } else {
                int weight = edges[i][2];
                dp[from][to] = weight;
            }
        }
    }

}
