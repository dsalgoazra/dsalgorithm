/**
 *
 * This is my implementation of the question found at https://www.careercup.com/question?id=5705185389707264
     There are N countries, each country has Ai players. You need to form teams of size K such that each player
     in the team is from a different country.  Given N and number of players from each country and size K.
     Find the maximum number of teams you can form.

 */

package algorithm.DP;

import java.util.ArrayList;
import java.util.List;

public class FindMaximumTeams {

    public int finMaximumNumberOfTeamsOfSizeK(int[] countryPlayers, int sizeOfTeam){
        if(countryPlayers == null && countryPlayers.length == 0) return 0;
        if(countryPlayers.length < sizeOfTeam) {
            return 0;
        }
        int totalNumPlayers = 0;
        for(int i = 0 ; i <  countryPlayers.length; i++) { //O(C)
            if(countryPlayers[i] > 0) {
                totalNumPlayers += countryPlayers[i];
            }
        }
        if(totalNumPlayers <= 0 || totalNumPlayers < sizeOfTeam) {
            return 0;
        }

        List<Integer> teams = new ArrayList<>();
        while(totalNumPlayers > 0) {
            int team= 0;
            int j = 0;
             while(j < countryPlayers.length &&  team < sizeOfTeam){
                 if(countryPlayers[j] > 0) {
                     team += 1;
                     countryPlayers[j] = countryPlayers[j] - 1;
                 }
                 j++;
             }
             if(team < sizeOfTeam) return teams.size();

             totalNumPlayers -= team;
             teams.add(team);//first team got its players
        }
        return teams.size();

    }

    public static void main(String[] args) {
        int[] countryPlayers = {3, 4, 5};
        test(2, 4, 3,4,5);
        test(2, 10, -3, 10, 10);
        test(1, 10, -3, 10, -5);
        test(2, 1, 1, 10, -5);
        test(2, 6, 1, 7, 5);
        test(7, 0, 3,4,5);
        test(3, 5, 10,9,5);
        test(3, 10000, 1000000,10000,100000);
        test(3, 1000, 1000,1000,1000, 1000);
    }

    private static void test(int sizeOfTeam, int expectedTeamCount, int...countryplayers) {

        StringBuilder message = new StringBuilder();
        message.append("Country Players: ");
        for(int i = 0 ; i < countryplayers.length; i++) {
           message.append(countryplayers[i] +" , ");
        }
        message.append(" | size: ").append(sizeOfTeam);
        FindMaximumTeams t= new FindMaximumTeams();
        int teamCount = t.finMaximumNumberOfTeamsOfSizeK(countryplayers, sizeOfTeam);
        message.append(" | Actual Count ").append(teamCount);
        message.append(" | Expected Count ").append(expectedTeamCount);

        if(teamCount != expectedTeamCount) {
            System.out.println("\n Test Case Failed >> "+message);
        } else {
            System.out.println("\n Test Case Passed >> "+message);
        }

    }
}
