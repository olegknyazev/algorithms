package com.olegknyazev;

import java.util.HashMap;

public class LineThroughPoints {
    // Returns the maximum number of points any single line may cross in the provided array of points.
    // O(n^2) runtime, O(n) memory
    public static int lineThroughPoints(Point[] points) {
        if (points.length == 0)
            return 0;
        var best = 0;
        for (int i = 0; i < points.length; ++i) {
            final var crossings = new HashMap<Ratio, Integer>();
            for (int j = 0; j < points.length; ++j)
                if (i != j) {
                    final var angle = new Ratio(points[j].x - points[i].x, points[j].y - points[i].y);
                    crossings.compute(angle, (key, current) -> current != null ? current + 1 : 1);
                }
            final var maxCrossings = crossings.values().stream().mapToInt(x -> x).max();
            if (maxCrossings.isPresent())
                if (maxCrossings.getAsInt() > best)
                    best = maxCrossings.getAsInt();
        }
        return best + 1;
    }

    public record Point(int x, int y) {
    }

    private record Ratio(int numerator, int denominator) {
        Ratio(int numerator, int denominator) {
            final var gcd = gcd(numerator, denominator);
            this.numerator = numerator / gcd;
            this.denominator = denominator / gcd;
        }

        private static int gcd(int a, int b) {
            while (b != 0) {
                final var t = b;
                b = a % b;
                a = t;
            }
            return a;
        }
    }
}
