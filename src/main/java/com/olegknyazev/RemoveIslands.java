package com.olegknyazev;

import java.util.ArrayDeque;
import java.util.Queue;

// The task is:
// Remove all the islands from the input bitmap where bitmap is a group of pixels each
// of which is connected to another either horizontally or vertically. See tests for an
// example data.
public class RemoveIslands {
    private final boolean[][] input;
    private final boolean[][] output;
    private final boolean[][] visited;
    private final Queue<Point> toConsider = new ArrayDeque<>();
    private final int width;
    private final int height;

    private RemoveIslands(boolean[][] input) {
        this.width = input.length;
        this.height = input[0].length;
        this.input = input;
        this.output = new boolean[width][height];
        this.visited = new boolean[width][height];
    }

    private boolean[][] run() {
        for (int x = 0; x < width; ++x) {
            visit(x, 0);
            visit(x, height - 1);
        }
        for (int y = 1; y < height - 1; ++y) {
            visit(0, y);
            visit(width - 1, y);
        }
        while (!toConsider.isEmpty()) {
            var next = toConsider.poll();
            visit(next.x, next.y);
        }
        return output;
    }

    private void visit(int x, int y) {
        if (x < 0 || x >= width) return;
        if (y < 0 || y >= height) return;
        if (visited[x][y]) return;
        if (input[x][y]) {
            output[x][y] = true;
            toConsider.add(new Point(x - 1, y));
            toConsider.add(new Point(x + 1, y));
            toConsider.add(new Point(x, y - 1));
            toConsider.add(new Point(x, y + 1));
        }
        visited[x][y] = true;
    }

    // O(n) runtime, O(n) memory
    public static boolean[][] removeIslands(boolean[][] bitmap) {
        if (bitmap == null || bitmap.length == 0)
            return bitmap;
        return new RemoveIslands(bitmap).run();
    }

    private record Point(int x, int y) {}
}
