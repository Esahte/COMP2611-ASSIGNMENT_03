import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class AdjList_No2 {
    private final LinkedList<Integer>[] adj;

    AdjList_No2(int v) {
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adj[i] = new LinkedList<>();
    }

    void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
    }

    public Integer[] dfs(int start, int end) {
        int[] visited = new int[adj.length];
        visited[start] = 1;
        Integer[] path = new Integer[1];
        path[0] = start;
        return dfs(start, end, visited, path);
    }

    private Integer[] dfs(int start, int end, int[] visited, Integer[] path) {
        for (int i = 0; i < adj[start].size(); i++) {
            if (visited[adj[start].get(i)] == 0) {
                if (adj[start].get(i) == end) {
                    Integer[] newPath = new Integer[path.length + 1];
                    newPath[0] = adj[start].get(i);
                    System.arraycopy(path, 0, newPath, 1, path.length);
                    return newPath;
                }
                visited[adj[start].get(i)] = 1;
                Integer[] newPath = new Integer[path.length + 1];
                newPath[0] = adj[start].get(i);
                System.arraycopy(path, 0, newPath, 1, path.length);
                Integer[] result = dfs(adj[start].get(i), end, visited, newPath);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }

    public List<Integer> bfs(int start, int end) {
        boolean[] visited = new boolean[adj.length];
        List<List<Integer>> paths = new ArrayList<>();
        for (int i = 0; i < adj.length; i++) {
            paths.add(new ArrayList<>());
        }

        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;
        paths.get(start).add(start);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            if (current == end) {
                return paths.get(current);
            }

            for (int i = 0; i < adj[current].size(); i++) {
                int next = adj[current].get(i);
                if (!visited[next]) {
                    visited[next] = true;
                    paths.get(next).addAll(paths.get(current));
                    paths.get(next).add(next);
                    queue.add(next);
                }
            }
        }

        return null;  // Return null if no path is found
    }


    public static void main(String[] args) {
        AdjList_No2 g1 = new AdjList_No2(36);
        g1.addEdge(1, 2);
        g1.addEdge(2, 3);
        g1.addEdge(3, 4);
        g1.addEdge(4, 5);
        g1.addEdge(5, 6);
        g1.addEdge(5, 9);
        g1.addEdge(6, 7);
        g1.addEdge(7, 8);
        g1.addEdge(9, 11);
        g1.addEdge(9, 10);
        g1.addEdge(10, 24);
        g1.addEdge(10, 27);
        g1.addEdge(12, 13);
        g1.addEdge(13, 14);
        g1.addEdge(14, 15);
        g1.addEdge(15, 16);
        g1.addEdge(16, 17);
        g1.addEdge(17, 18);
        g1.addEdge(18, 19);
        g1.addEdge(19, 20);
        g1.addEdge(20, 23);
        g1.addEdge(21, 22);
        g1.addEdge(22, 23);
        g1.addEdge(22, 26);
        g1.addEdge(24, 25);
        g1.addEdge(25, 34);
        g1.addEdge(25, 26);
        g1.addEdge(26, 33);
        g1.addEdge(27, 28);
        g1.addEdge(28, 29);
        g1.addEdge(29, 30);
        g1.addEdge(31, 32);
        g1.addEdge(32, 33);
        g1.addEdge(34, 25);
        System.out.println("Following is Depth First Traversal " +
                "(starting from vertex 1)");

        Integer[] path = g1.dfs(1, 21);
        for (Integer integer : path) {
            System.out.print(integer + " ");
        }

        System.out.println("\nFollowing is Breadth First Traversal " +
                "(starting from vertex 1)");

        Integer[] path1 = g1.bfs(1, 21).toArray(new Integer[0]);
        for (Integer integer : path1) {
            System.out.print(integer + " ");
        }
    }
}
