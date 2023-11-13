import java.util.Arrays;

public class AdjMatrix_No2 {
    private final int[][] adj;

    // Constructor to initialize the adjacency matrix with V vertices
    public AdjMatrix_No2(int V) {
        adj = new int[V][V];
    }

    // Method to add an edge between two vertices v and n with weight w
    public void addEdge(int v, int n, int w) {
        adj[v][n] = w;
        adj[n][v] = w;
    }

    // Method to print the graph
    public void print() {
        for (int i = 0; i < adj.length; i++) {
            System.out.print(i + ": ");
            for (int j = 0; j < adj[i].length; j++) {
                if (adj[i][j] != 0) {
                    System.out.print(j + "(" + adj[i][j] + ") ");
                }
            }
            System.out.println();
        }
    }

    // Method to perform depth-first search (DFS) from start to end
    public Integer[] dfs(int start, int end) {
        int[] visited = new int[adj.length];
        visited[start] = 1;
        Integer[] path = new Integer[1];
        path[0] = start;
        return dfs(start, end, visited, path);
    }

    // Recursive helper method for DFS
    private Integer[] dfs(int start, int end, int[] visited, Integer[] path) {
        for (int i = 0; i < adj[start].length; i++) {
            if (adj[start][i] != 0 && visited[i] == 0) {
                if (i == end) {
                    Integer[] newPath = new Integer[path.length + 1];
                    newPath[0] = i;
                    System.arraycopy(path, 0, newPath, 1, path.length);
                    return newPath;
                }
                visited[i] = 1;
                Integer[] newPath = new Integer[path.length + 1];
                newPath[0] = i;
                System.arraycopy(path, 0, newPath, 1, path.length);
                Integer[] result = dfs(i, end, visited, newPath);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }

    // Create method that takes a path and calculates the weight of the path
    public int pathWeight(Integer[] path) {
        int weight = 0;
        for (int i = 0; i < path.length - 1; i++) {
            weight += adj[path[i]][path[i + 1]];
        }
        return weight;
    }

    // Main method to test the code
    public static void main(String[] args) {
        AdjMatrix_No2 g1 = new AdjMatrix_No2(9);
        g1.addEdge(1, 2, 3);
        g1.addEdge(2, 3, 12);
        g1.addEdge(2, 4, 2);
        g1.addEdge(2, 5, 5);
        g1.addEdge(4, 5, 4);
        g1.addEdge(4, 6, 3);
        g1.addEdge(6, 7, 8);
        g1.addEdge(6, 8, 3);
        System.out.println(Arrays.toString(g1.dfs(1, 6)));
        System.out.println("Weight: " + g1.pathWeight(g1.dfs(1, 6)));
    }
}

