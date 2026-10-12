package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        int[][] grid = {
                {0, 0, 0},
                {1, 1, 0},
                {1, 1, 0}
        };

        int sr = 0;
        int sc = 0;

        int tr = 3;
        int tc = 3;

        var bfs = new Bfs();

        try {
            int result = bfs.shortestPath(grid, sr, sc, tr, tc);
            System.out.println(result);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}