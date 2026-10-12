package org.example;

import java.util.*;

public class Bfs {
    public int shortestPathBinaryMatrix(
        int[][] grid) {
            if(grid.length == 0 || grid[0][0] == 1) {
                return -1;
            }

            var destinationRow = grid.length - 1;
            var destinationColumn = grid[0].length - 1;
            int rows = grid.length;
            int cols = grid[0].length;

            Queue<Coordination> queue = new ArrayDeque<>();
            HashSet<Coordination> visited = new HashSet<>();

            queue.offer(new Coordination(0, 0, 1));

            while (!queue.isEmpty()) {
                var current = queue.poll();
                System.out.println("Hit the position: Row: "+ current.row + " Col:" + current.col);
                if (current.row == destinationRow && current.col == destinationColumn) {
                    return current.depth;
                }
                if (visited.contains(current)) {
                    System.out.println("Its visited"+current.toString());
                    continue;
                }
                if (!(current.row >= 0 && current.row <= rows-1 && current.col >= 0 && current.col <= cols-1)) {
                    System.out.println("Its outside of matrix");
                    continue;
                }
                if(grid[current.row][current.col] == 1) {
                    System.out.println("Its blocked"+current.toString());
                    System.out.println("Hit the position: Row: "+ current.row + " Col:" + current.col);
                    continue;
                }
                visited.add(current);
                queue.add(current.moveRightAndDown());
                queue.add(current.moveRight());
                queue.add(current.moveDown());
            }
            return -1;

    }


}


