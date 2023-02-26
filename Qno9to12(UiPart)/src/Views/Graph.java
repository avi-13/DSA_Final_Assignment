package Views;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Graph {
    private int numVertices;
    private List<List<Integer>> adjList;

    public Graph(int numVertices) {
        this.numVertices = numVertices;
        adjList = new ArrayList<>(numVertices);

        for (int i = 0; i < numVertices; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    public void addEdge(int u, int v) {
        adjList.get(u).add(v);
    }

    public List<Integer> topologicalSort() {
        List<Integer> result = new ArrayList<>();

        // Calculate the in-degree of each vertex
        int[] inDegree = new int[numVertices];
        for (int u = 0; u < numVertices; u++) {
            for (int v : adjList.get(u)) {
                inDegree[v]++;
            }
        }

        // Add all vertices with in-degree 0 to a queue
        Queue<Integer> queue = new LinkedList<>();
        for (int u = 0; u < numVertices; u++) {
            if (inDegree[u] == 0) {
                queue.add(u);
            }
        }

        // Process the queue, adding vertices to the result in the order they are dequeued
        while (!queue.isEmpty()) {
            int u = queue.poll();
            result.add(u);

            for (int v : adjList.get(u)) {
                inDegree[v]--;
                if (inDegree[v] == 0) {
                    queue.add(v);
                }
            }
        }

        // If not all vertices were visited, there must be a cycle
        if (result.size() != numVertices) {
            throw new IllegalArgumentException("The graph has a cycle Please choose another !");
        }

        return result;
    }
}
