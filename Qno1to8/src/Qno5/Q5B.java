package Qno5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class Q5B {

    public int numBatteryRep(int[][] serviceCenters, int targetMiles, int startCgCap) {
        int c = 0;
        int currMile = startCgCap;
        ArrayList<Integer> distances = new ArrayList<>();
        ArrayList<Integer> capacities = new ArrayList<>();

        for (int[] serviceCenter : serviceCenters) {
            distances.add(serviceCenter[0]);
            capacities.add(serviceCenter[1]);
        }

        for (int i = 0; i < distances.size(); i++) {
            if (distances.get(i) > currMile) {
                currMile = capacities.get(i - 1);
                c++;
            }
        }

        if (currMile < targetMiles) {
            c++;
        }

        return c;
    }


    public static void main(String[] args) {
        int [][] serCentre={{10,60},{20,30},{30,30},{60,40}};
        Q5B qs=new Q5B();
        int ans=qs.numBatteryRep(serCentre,100,10);
        System.out.println("The no of times the car's batteries need to be replaced is : "+ans +" times.");

    }
}
