package Practice;

import java.util.PriorityQueue;
import java.util.Scanner;

class Solution {
    static Scanner input = new Scanner(System.in);
    static final int mod = (int)(1e7);

    public static void main(String[] args) {

        int n = input.nextInt(), m = input.nextInt(), k = input.nextInt();

        char[][] a = new char[n][m];
        for (int i = 0; i < n; ++i)  {
            String s = input.next();
            a[i] = s.toCharArray();
        }

        int[][] grid = new int[n][m];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                grid[i][j] = a[i][j] == '#' ? 1 : 0;
            }
        }

        Solution s = new Solution();
        System.out.println(s.shortestPath(grid, k));
    }

    public static class FourPair implements Comparable<FourPair> {
        int i;
        int j;
        int dist;
        int k;

        public FourPair(int i, int j, int dist, int k) {
            this.i = i;
            this.j = j;
            this.dist = dist;
            this.k = k;
        }

        public int compareTo(FourPair o) {
            return this.dist - o.dist;
        }
    }

    public int shortestPath(int[][] grid, int k) {
        int[][] dirs = { { 1, 0 },
                         { 0, 1 },
                         { -1, 0 },
                         { 0, -1 }
                        };

        int n = grid.length;
        int m = grid[0].length;

        boolean[][][] visited = new boolean[n][m][k + 1];
        PriorityQueue<FourPair> q = new PriorityQueue<>();

        q.add(new FourPair(0, 0, 0, k));

        while (!q.isEmpty()) {
            FourPair r = q.poll();
            if (r.i == grid.length - 1 && r.j == grid[0].length - 1)
                return r.dist;
            if (visited[r.i][r.j][r.k])
                continue;
            visited[r.i][r.j][r.k] = true;
            int obs = r.k;
            for (int d = 0; d < 4; d++) {

                int i = r.i + dirs[d][0];
                int j = r.j + dirs[d][1];

                if (i >= 0 && j >= 0 && i <= grid.length - 1 && j <= grid[0].length - 1) {
                    if (grid[i][j] == 1) {
                        if (obs > 0) {
                            if (!visited[i][j][obs - 1])
                                q.add(new FourPair(i, j, r.dist + 1, obs - 1));
                        }
                    } else {
                        if (!visited[i][j][obs])
                            q.add(new FourPair(i, j, r.dist + 1, obs));
                    }
                }
            }

        }
        return -1;

    }
}