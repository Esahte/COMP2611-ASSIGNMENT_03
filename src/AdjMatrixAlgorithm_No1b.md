1. Create a new instance of the AdjMatrix_No1a class, passing the number of vertices as a parameter.
2. Use the addEdge method to add edges between vertices, specifying the vertices and the weight of the edge.
3. Call the dfs method on the AdjMatrix_No1a object, passing the start and end vertices as parameters.
    - The dfs method initializes an array called visited to keep track of visited vertices. Set the start vertex as
      visited.
    - Create a new array called path to store the path from the start vertex to the current vertex. Initialize it with
      the start vertex.
    - Call the private dfs method, passing the start vertex, end vertex, visited array, and path array as parameters.
        - In the private dfs method, iterate through the adjacency matrix of the current vertex.
        - If there is an edge between the current vertex and the next vertex, and the next vertex has not been visited,
          check if it is the end vertex.
            - If the next vertex is the end vertex, create a new array called newPath with a length one greater than the
              path array. Set the first element of newPath as the next vertex.
            - Copy the elements of the path array to the newPath array starting from index 1.
            - Return the newPath array.
        - If the next vertex is not the end vertex, mark it as visited and create a new newPath array with a length one
          greater than the path array. Set the first element of newPath as the next vertex.
        - Copy the elements of the path array to the newPath array starting from index 1.
        - Call the private dfs method recursively, passing the next vertex, end vertex, visited array, and newPath array
          as parameters.
            - If the result of the recursive call is not null, return the result.
4. If no path is found, return null.
