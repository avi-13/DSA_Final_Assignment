package Qno1;

import java.util.*;

public class Q1B {

    static void addEdge(ArrayList<ArrayList<Integer>> am, int s, int d) {
        am.get(s).add(d);
        am.get(d).add(s);
    }


    static void removeEdge(ArrayList<ArrayList<Integer>> am, int s, int d) {
        if (am.get(s).contains(d)) {
            am.get(s).remove(Integer.valueOf(d));
            System.out.println("removed node " + s);
        }
        if (am.get(d).contains(s)) {
            am.get(d).remove(Integer.valueOf(s));
            System.out.println("removed node " + d);
        }
    }



    private static void printDisconnectedNodes(ArrayList<ArrayList<Integer>> am, int disconnectedNode) {
        for (int i = am.get(disconnectedNode).size() - 1; i >= 0; i--) {
            int neighbor = am.get(disconnectedNode).get(i);
            System.out.println("Removing edge between " + disconnectedNode + " and " + neighbor);
            removeEdge(am, disconnectedNode, neighbor);
        }
    }
    static boolean isReachable(ArrayList<ArrayList<Integer>> am, int s, int d) {
        boolean[] visited = new boolean[am.size()];
        return dfs(am, visited, s, d);
    }

    static boolean dfs(ArrayList<ArrayList<Integer>> am, boolean[] visited, int s, int d) {
        visited[s] = true;
        if (s == d) {
            return true;
        }
        for (int i = 0; i < am.get(s).size(); i++) {
            int v = am.get(s).get(i);
            if (!visited[v] && dfs(am, visited, v, d)) {
                return true;
            }
        }
        return false;
    }



    public static void main(String[] args) {

        int V = 8;
        ArrayList<ArrayList<Integer>> am = new ArrayList<ArrayList<Integer>>(V);

        for (int i = 0; i < V; i++)
            am.add(new ArrayList<Integer>());
        addEdge(am, 0, 1);
        addEdge(am, 0, 2);
        addEdge(am, 1, 3);
        addEdge(am, 2, 4);
        addEdge(am, 1, 6);
        addEdge(am, 4, 6);
        addEdge(am, 4, 5);
        addEdge(am, 5, 7);
        int disconnectedNode=4;
        printDisconnectedNodes(am,disconnectedNode);

        int nodes[] ={0,1,2,3,4,5,6,7};
        int destination = 0;
        for(int i=0; i<nodes.length;i++){
            int source = nodes[i];
            boolean disconn = isReachable(am,source,destination);
            if(disconn==false){
                System.out.println("disconnected nodes are "+nodes[i]);
            }

        }
    }
}