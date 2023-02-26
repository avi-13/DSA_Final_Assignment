package Qno2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

class Q2A {
    public int GCD(int n1, int n2) {
        if (n2 == 0) {
            return n1;
        }
        return GCD(n2, n1 % n2);
    }


    public void DFS(int[] nums, LinkedList<Integer>[] tree, int depth, int node, boolean[] visited, int[] ans, Map<Integer,int[]> map, boolean[][] poss) {

        if(visited[node]) return;

        visited[node] = true;
        int ancestor = -1;

        int d = Integer.MAX_VALUE;

        for(int i = 1; i < 51; i++) {
            if(poss[nums[node]][i] && map.containsKey(i)) {
                if(depth - map.get(i)[0] <= d) {
                    d = depth - map.get(i)[0];
                    ancestor = map.get(i)[1];
                }
            }
        }

        ans[node] = ancestor;
        int[] exist = (map.containsKey(nums[node])) ? map.get(nums[node]) :  new int[]{-1,-1};
        map.put(nums[node],new int[]{depth,node});
        for(int child : tree[node]) {
            if(visited[child]) continue;
            DFS(nums, tree, depth+1, child, visited, ans, map, poss);
        }

        if(exist[0] != -1) map.put(nums[node], exist);
        else map.remove(nums[node]);
    }

    public int[] getCommPrime(int[] nums, int[][] edges) {

        boolean[][] poss = new boolean[51][51];
        for(int i = 1; i < 51; i++) {
            for(int j = 1; j < 51; j++) {
                if(GCD(i,j) == 1) {
                    poss[i][j] = true;
                    poss[j][i] = true;
                }
            }
        }
        int n = nums.length;

        LinkedList<Integer>[] tree = new LinkedList[n];

        for(int i =0 ; i < tree.length; i++) tree[i] = new LinkedList<>();
        for(int[] edge : edges) {
            tree[edge[0]].add(edge[1]);
            tree[edge[1]].add(edge[0]);
        }

        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        ans[0] = -1;
        Map<Integer,int[]> map = new HashMap<>();

        boolean[] visited = new boolean[n];
        DFS(nums, tree, 0, 0, visited, ans, map, poss);
        return ans;
    }

    //driver method
    public static void main(String[] args) {
        Q2A nearestAncestor = new Q2A();
        System.out.println(
                Arrays.toString(nearestAncestor.getCommPrime(new int[]{3, 2, 6, 6, 4, 7, 12}, new int[][]{{0, 1}, {0, 2}, {1, 3}, {1, 4}, {2, 5}, {2, 6}}))
        );
    }
}