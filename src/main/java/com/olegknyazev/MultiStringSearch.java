package com.olegknyazev;

import java.util.*;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toList;

public class MultiStringSearch {
    // Returns true or false for every string in `smallStrings`, indicating whether this
    // string encounters in `bigString`.
    // O(bs + ns) runtime, O(ns) memory
    // where b - the length of `bigString`
    //       s - the length of the longest of `smallStrings`
    //       n - the number of `smallStrings`
    public static List<Boolean> multiStringSearch(String bigString, String[] smallStrings) {
        if (smallStrings.length == 0)
            return List.of();
        final var result = Arrays.stream(smallStrings).map(x -> false).collect(toList());
        final var searchIndex = new Trie();
        for (int i = 0; i < smallStrings.length; ++i)
            searchIndex.add(smallStrings[i], i);
        final var maxSmallLength = Arrays.stream(smallStrings).max(comparingInt(String::length)).orElseThrow().length();
        for (int i = 0; i < bigString.length(); ++i) {
            final var potentialSubstring = bigString.substring(i, Math.min(i + maxSmallLength, bigString.length()));
            for (int index : searchIndex.findAll(potentialSubstring))
                result.set(index, true);
        }
        return result;
    }

    private static class Trie {
        private final Node root = new Node();

        private void add(String s, int index) {
            var current = root;
            for (int i = 0; i < s.length(); ++i)
                current = current.intern(s.charAt(i));
            current.assign(index);
        }

        private List<Integer> findAll(String s) {
            final var result = new ArrayList<Integer>();
            var current = root;
            for (int i = 0; i < s.length(); ++i) {
                current = current.next(s.charAt(i));
                if (current == null)
                    break;
                if (current.index != -1)
                    result.add(current.index);
            }
            return result;
        }

        private static class Node {
            private int index = -1;
            private final Map<Character, Node> edges = new HashMap<>();

            private Node intern(char c) {
                return edges.computeIfAbsent(c, (a) -> new Node());
            }

            private void assign(int index) {
                this.index = index;
            }

            private Node next(char c) {
                return edges.get(c);
            }
        }
    }

    private MultiStringSearch() {
        throw new AssertionError();
    }
}
