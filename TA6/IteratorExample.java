package TA6;

import java.util.NoSuchElementException;
import java.util.Iterator;
import java.util.Stack;

// TreeNode class to represent each node in the binary tree
class TreeNode {
    int value;
    TreeNode left, right;

    public TreeNode(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }
}

// TreeIterator class for in-order traversal of the binary tree
class TreeIterator {
    private Stack<TreeNode> stack;

    public TreeIterator(TreeNode root) {
        stack = new Stack<>();
        traverseLeft(root);
    }

    private void traverseLeft(TreeNode node) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }

    public Integer next() {
        if (!hasNext()) throw new NoSuchElementException();
        TreeNode currentNode = stack.pop();
        traverseLeft(currentNode.right);
        return currentNode.value;
    }
}

// BinaryTree class to manage the tree structure and insertion logic
class BinaryTree {
    private TreeNode root;

    public BinaryTree() {
        this.root = null;
    }

    public void insert(int value) {
        if (root == null) {
            root = new TreeNode(value);
        } else {
            insertRecursive(root, value);
        }
    }

    private void insertRecursive(TreeNode node, int value) {
        if (value < node.value) {
            if (node.left == null) {
                node.left = new TreeNode(value);
            } else {
                insertRecursive(node.left, value);
            }
        } else {
            if (node.right == null) {
                node.right = new TreeNode(value);
            } else {
                insertRecursive(node.right, value);
            }
        }
    }

    public TreeIterator createIterator() {
        return new TreeIterator(root);
    }

    public void printTree() {
        printTreeRecursive(root, 0);
    }

    private void printTreeRecursive(TreeNode node, int level) {
        if (node != null) {
            printTreeRecursive(node.right, level + 1);
            System.out.println("    ".repeat(level) + "-> " + node.value);
            printTreeRecursive(node.left, level + 1);
        }
    }

}

// Main class to demonstrate functionality
public class IteratorExample {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        // Insert values into the binary tree
        tree.insert(5);
        tree.insert(3);
        tree.insert(7);
        tree.insert(2);
        tree.insert(4);
        tree.insert(6);
        tree.insert(8);

        // Print the binary tree structure
        System.out.println("Binary Tree:");
        tree.printTree();

        // Traverse the tree using the iterator
        System.out.println("\nElements traversed using Iterator:");

        TreeIterator iterator = tree.createIterator();
//        tree.insert(1);
//        tree.printTree();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }


    }
}
