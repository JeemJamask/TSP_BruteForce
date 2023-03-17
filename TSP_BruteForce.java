import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class TSP_BruteForce {

    // 2D array to store the graph representation
    static int[][] graph;
    
    // Array to store the best path found so far
    static int[] bestPath;
    
    // variable to store the best distance found so far
    static int bestDistance = Integer.MAX_VALUE;
    
    // variable to store the best path as a string
    static String bestPathString = "";
    
    // list to store all the possible paths
    static List<String> allPaths = new ArrayList<>();

    public static void main(String[] args) {

        // Variables to store start time, end time and total time taken by the program
        long startTime = 0, endTime = 0, totalTime;

        // Creating a random number generator with a seed value of 72
        Random rand = new Random(72);

        int n = 5;
        // Initializing the graph 2D array
        graph = new int[n][n];

        // Generating the graph array with random values
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    graph[i][j] = 0; // setting the diagonal elements to 0
                } else {
                    graph[i][j] = 10 + rand.nextInt(100); // setting the other elements to a random value between 10 and 110
                }
            }
        }

        startTime = System.currentTimeMillis();
        // looping over all the nodes to start the path from
        for (int i = 0; i < graph.length; i++) {
            // creating a new path list and adding the starting node
            List<Integer> path = new ArrayList<>();
            path.add(i);
            // initializing the distance to 0
            int distance = 0;
            // calling the tsp function to find all the possible paths
            tsp_BF(path, distance);
        }
        endTime = System.currentTimeMillis();

        // Calculates the total time taken by the program
        totalTime = endTime - startTime;

        // Printing all the possible paths
        System.out.println("========== Welcome to The Travlling SalesMan (TSM) System! ==========\n");
        System.out.println("\t\t>> Here is all the possible paths: ");
        System.out.println("_____________________________________________________________________\n");
        allPaths.forEach(System.out::println);
        System.out.println("_____________________________________________________________________\n");

        // Printing the best path and its distance
        System.out.println("The Best path: " + bestPathString);
        System.out.println("This path have distance of: " + bestDistance + " km");
        System.out.println("The total time: " + totalTime + " ms");
    }

    // tsp_BF Method implements the brute force algorithm for the Traveling Salesman Problem (TSP)
    static void tsp_BF(List<Integer> path, int distance) {
        // If the path list size is equal to the number of nodes, it means we have found a complete path
        if (path.size() == graph.length) {
            // Adding the distance from the last node to the starting node
            distance += graph[path.get(path.size() - 1)][path.get(0)];

            // If the distance of the current path is less than the best distance found so far, update the best distance and the best path
            if (distance < bestDistance) {
                bestDistance = distance;
                bestPathString = path.stream().map(Object::toString).collect(Collectors.joining(" -> ")) + " -> " + path.get(0);
            }
            allPaths.add(path.stream().map(Object::toString).collect(Collectors.joining(" -> ")) + " -> " + path.get(0) + " , This path cost: " + distance + " km");
            return;
        }

        // For each unvisited city
        for (int i = 0; i < graph.length; i++) {
            if (!path.contains(i)) {
                List<Integer> newPath = new ArrayList<>(path);
                newPath.add(i);
                int newDistance = distance + graph[path.get(path.size() - 1)][i];
                // Recursively call the method with updated path, used, distance, and pos
                tsp_BF(newPath, newDistance);
            }
        }
    }
}
