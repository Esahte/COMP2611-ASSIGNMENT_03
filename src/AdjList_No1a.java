import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class AdjList_No1a {
    private final LinkedList<Integer>[] adj;

    AdjList_No1a(int v) {
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
        AdjList_No1a g = new AdjList_No1a(9);

        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(2, 4);
        g.addEdge(2, 5);
        g.addEdge(4, 5);
        g.addEdge(4, 6);
        g.addEdge(6, 7);
        g.addEdge(6, 8);

        System.out.println("Following is Depth First Traversal " +
                "(starting from vertex 1)");

        Integer[] path = g.dfs(1, 6);
        for (Integer integer : path) {
            System.out.print(integer + " ");
        }

        System.out.println("\nFollowing is Breadth First Traversal " +
                "(starting from vertex 1)");

        Integer[] path1 = g.bfs(1, 6).toArray(new Integer[0]);
        for (Integer integer : path1) {
            System.out.print(integer + " ");
        }
    }
}
