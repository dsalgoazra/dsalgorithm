/**
 *
 * This is my implementation of a question found on career cups using Manber and Mayer Algorithm
     Given a string that contains lowercase alphabet, find the length of the Longest common Prefix of all substrings in O(n)
     Manber and Mayer Algo:
     LCP Array algorithm was created by Manber and Mayers in 1990
     Space complexity is O(n)
     Time complexity is O(n)
     This Algorithms that use an already constructed and sorted lexicographically suffix array in order to compute the LCP values.

 * @author Azra Irshad Rabbani, dsalgoazra@gmail.com
 */

package algorithm.string;

import java.util.Arrays;

public class LongestCommonPrefixManberAndMayerAlgo {
    /**
     * Suffix array: Represents the lexicographic rank of each suffix of an array.
     * banana -> banana , anana , nana, ana, na, a
     * sort it -> a, ana, anana, banana, na, nana
     * LCP -> 0 - length
     * count matching characters in the  prefix of adjacent elements sufx[i-1] withh sufx[i]
     * LCP -> 0, 1, 3 , 0 , 0,  2
     */

    //O(n)
    public String findLongestCommonPrefix(String str) {
        String[] suffixArray = createSuffixArray(str);//O(n)
        Arrays.sort(suffixArray);//O(nlogn)
        int[] LCP = new int[suffixArray.length];
        int max = 0; //because first element is 0
        String maxStr = "";
        if(suffixArray.length > 0) {
            LCP[0] = 0;
            int i = 1;
            while(i < suffixArray.length) { //O(n)
                LCP[i] = countMatchingChar(suffixArray[i-1], suffixArray[i]);//O(M) matching chars
                if(LCP[i] > max) {
                    maxStr = suffixArray[i-1].substring(0,LCP[i]);
                    max = Math.max(LCP[i], max);
                }
                i++;
            }
        }
        return maxStr;

    }

    private String[] createSuffixArray (String s) {
        String[] arr = new String[s.length()];//
        for(int  i = 0 ; i < s.length() ; i++) {
            arr[i] = s.substring(i, s.length());
        }
        return arr;
    }

    private int countMatchingChar(String s1 , String s2){
        int i = 0 ;
        while(i < s1.length() && i < s2.length() && s1.charAt(i) == s2.charAt(i)){
            i++;
        }
        return i;
    }

    private int[] createLCPArray(String[] sufxArr) {
        int[] LCP = new int[sufxArr.length];
        if(sufxArr.length > 0) {
            LCP[0] = 0;
            int i = 1;
            while(i < sufxArr.length) {
                LCP[i] = countMatchingChar(sufxArr[i-1], sufxArr[i]);
            }
        }
        return LCP;
    }


    public static void main(String[] args) {
        LongestCommonPrefixManberAndMayerAlgo lcp = new LongestCommonPrefixManberAndMayerAlgo();
        String str = "abcbbccaabcaabc";
        System.out.println("Longest Common Prefix : " + lcp.findLongestCommonPrefix(str));

    }
}
