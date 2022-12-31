package com.olegknyazev;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

// The task is to write a function that returns a list of linked lists each of which
// represents all the nodes at a specific depth.
public class ListOfDepths {
    // O(n) runtime, O(n) memory
    public static List<List<Integer>> sliceByDepth(Tree tree) {
        if (tree == null)
            return List.of();
        var result = new ArrayList<List<Integer>>();
        var queue = new ArrayDeque<TreeAndDepth>();
        var currentSlice = new ArrayList<Integer>();
        result.add(currentSlice);
        var currentDepth = 0;
        queue.add(new TreeAndDepth(tree, currentDepth));
        while (!queue.isEmpty()) {
            var treeAndDepth = queue.pop();
            if (treeAndDepth.tree == null)
                continue;
            if (treeAndDepth.depth > currentDepth) {
                currentDepth = treeAndDepth.depth;
                currentSlice = new ArrayList<>();
                result.add(currentSlice);
            }
            currentSlice.add(treeAndDepth.tree.value);
            queue.add(new TreeAndDepth(treeAndDepth.tree.left, currentDepth + 1));
            queue.add(new TreeAndDepth(treeAndDepth.tree.right, currentDepth + 1));
        }
        return result;
    }

    record TreeAndDepth(Tree tree, int depth) {
    }

    record Tree(int value, Tree left, Tree right) {
    }
}
