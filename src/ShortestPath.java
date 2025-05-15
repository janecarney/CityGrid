import java.util.*;

class GfG {

    static List<int[]> shortestPath(int[][] grid) {
        int r = grid.length, c = grid[0].length;
        
        int[][] dp = new int[r][c]; // dp will store shortest path
        int[][][] parent = new int[r][c][2]; // stores the previous cell

        for (int[] row : dp)
            Arrays.fill(row, Integer.MAX_VALUE);

        //starting point with no obstacle
        if (grid[0][0] == 0) dp[0][0] = 0;
        
        // goes through all cells in grid
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == 1) continue; // skip obstacle

                if (i == 0 && j == 0) continue; // skip starting cell

                // checking from top cell if reachalbe & path is shorter
                if (i > 0 && dp[i - 1][j] != Integer.MAX_VALUE) {
                    // subprolem to minimize path length
                    if (dp[i - 1][j] + 1 < dp[i][j]) {
                        dp[i][j] = dp[i - 1][j] + 1;
                        parent[i][j] = new int[] {i - 1, j}; // stores the path source
                    }
                }
                // checking from left call if reachable & path is shorter
                if (j > 0 && dp[i][j - 1] != Integer.MAX_VALUE) {
                    if (dp[i][j - 1] + 1 < dp[i][j]) {
                        dp[i][j] = dp[i][j - 1] + 1; //stores the shortest path to reach the cell
                        parent[i][j] = new int[] {i, j - 1};
                    }
                }
            }
        }
        // if end is unreachable
        if (dp[r - 1][c - 1] == Integer.MAX_VALUE) return null;

        // reconstructs the path from bottom-right to top-left
        List<int[]> path = new ArrayList<>();
        int i = r - 1, j = c - 1;
        while (!(i == 0 && j == 0)) {
            path.add(new int[] {i, j});
            int[] prev = parent[i][j];
            i = prev[0];
            j = prev[1];
        }
        path.add(new int[] {0, 0});
        Collections.reverse(path);
        return path;
    }
    public static void main(String[] args) {
        int[][] grid = { { 0, 0, 1, 0 },
                         { 1, 0, 0, 0 },
                         { 0, 0, 1, 0 },
                         { 0, 0, 0, 0 } 
                        };

        List<int[]> path = shortestPath(grid);

        if (path == null) {
            System.out.println("No path found.");
        } else {
            System.out.println("Shortest path:");
            for (int[] cell : path) {
                System.out.println(Arrays.toString(cell));
            }
        }
    }
}