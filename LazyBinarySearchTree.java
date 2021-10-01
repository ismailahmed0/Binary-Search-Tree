/*
Lazy Binary Search Tree Program

A java program that implements a lazy binary search tree.

UML Diagram
****************************************
+LazyBinarySearchTree
****************************************
-root: TreeNode
****************************************
    -TreeNode
****************************************
    +key: int
    +leftChild: TreeNode
    +rightChild: TreeNode
    +deleted: boolean
****************************************
+insert(key: int): boolean {throws IllegalArgumentException}
+insert2(key: int): boolean
+double(key: int): boolean {throws IllegalArgumentException}
+findMin(): int
+findMax(): int
+contains(key: int): boolean {throws IllegalArgumentException}
+toString(): String
+toString2(treeNode: TreeNode): void
+height(): int
+height2(treeNode: TreeNode): int
+size(): int
****************************************

Ismail Ahmed
UT Dallas - CS 3345.005
 */


public class LazyBinarySearchTree
{
    private TreeNode root;
    private int size;
    private String printString;

    // nested tree node class - creates nodes for BST
    private class TreeNode
    {
        public int key;
        public TreeNode leftChild; // pointer to child
        public TreeNode rightChild;
        public boolean deleted; // true if node has been "deleted"; else false
    }

    public boolean insert(int key) throws IllegalArgumentException
    {
        if (key >= 1 && key <= 99) // valid range
        {
            if (root == null) // first time insert - root
            {
                root =  new TreeNode(); // initialize root node/BST
                root.key = key;
                root.deleted = false; // root exists, has not been deleted

                size++; // increment number of nodes in BST
                return true;
            }
            else
            {
                return insert2(key); // insert non-root node into BST
            }
        }
        else
        {
            throw new IllegalArgumentException(); // exception
        }
    }

    public boolean insert2(int key)
    {
        TreeNode treeNode = new TreeNode();
        TreeNode prevTreeNode = root;

        while (true)
        {
            if (prevTreeNode.key > key) // key is less than node key
            {
                //prevTreeNode = treeNode;
                if (prevTreeNode.leftChild != null) // if left child node exists
                {
                    prevTreeNode = prevTreeNode.leftChild; // save what will be the previous(parent) node of node to be inserted
                }
                else
                {
                    // insert node
                    treeNode.key = key;
                    prevTreeNode.leftChild = treeNode;
                    treeNode.deleted = false;

                    size++; // increment count of number of nodes in BST
                    return true;
                }
            }
            else if (prevTreeNode.key < key) // key is greater than node key
            {
                if (prevTreeNode.rightChild != null)
                {
                    prevTreeNode = prevTreeNode.rightChild;
                }
                else
                {
                    // insert node
                    treeNode.key = key;
                    prevTreeNode.rightChild = treeNode;
                    treeNode.deleted = false;

                    size++;
                    return true;
                }
            }
            else
            {
                if (prevTreeNode.deleted == true) // if node was "deleted"
                {
                    prevTreeNode.deleted = false; // "insert" node - node desired in BST now exists in the BST
                }
                return true;
            }
        }
    }

    public boolean delete(int key) throws IllegalArgumentException
    {
        TreeNode treeNode = root;

        if (key >= 1 && key <= 99) // valid range
        {
            while (true)
            {
                if (key < treeNode.key) // key less than node key
                {
                    treeNode = treeNode.leftChild;

                    if (treeNode == null) // if key doesn't exist in BST to be deleted
                    {
                        return false;
                    }
                    else
                    {
                        if (key == treeNode.key && treeNode.deleted == false) // if key exists and hasn't been "deleted"
                        {
                            treeNode.deleted = true; // lazy delete node
                            return true;
                        }
                        else if (key == treeNode.key && treeNode.deleted == true)
                        {
                            return false; // if key already "deleted" - i.e. doesn't "exist" to be deleted
                        }
                    }
                }
                else if (key > treeNode.key) // key greater than node key
                {
                    treeNode = treeNode.rightChild;

                    if (treeNode == null) // if key doesn't exist in BST to be deleted
                    {
                        return false;
                    }
                    else
                    {
                        if (key == treeNode.key && treeNode.deleted == false) // if key exists and hasn't been "deleted"
                        {
                            treeNode.deleted = true; // lazy delete node
                            return true;
                        }
                        else if (key == treeNode.key && treeNode.deleted == true) // can't delete what already "deleted"
                        {
                            return false;
                        }
                    }
                }
                else if (key == treeNode.key && treeNode.deleted == false) // if key exists and hasn't been "deleted"
                {
                    treeNode.deleted = true; // lazy delete node
                    return true;
                }
            }
        }
        else
        {
            throw new IllegalArgumentException(); // exception
        }
    }

    public boolean contains(int key) throws IllegalArgumentException
    {
        TreeNode treeNode = root;

        if (key >= 1 && key <= 99) // valid range input
        {
            while (true)
            {
                if (key < treeNode.key)
                {
                    treeNode = treeNode.leftChild;

                    if (treeNode == null) // if key does not exist in BST
                    {
                        return false;
                    }
                    else
                    {
                        if (key == treeNode.key && treeNode.deleted == false) // if key "exists"
                        {
                            return true;
                        }
                    }
                }
                else if (key > treeNode.key)
                {
                    treeNode = treeNode.rightChild;

                    if (treeNode == null) // if key does not exist
                    {
                        return false;
                    }
                    else
                    {
                        if (key == treeNode.key && treeNode.deleted == false) // if key "exists"
                        {
                            return true;
                        }
                    }
                }
                else if (key == treeNode.key && treeNode.deleted == false) // if key "exists"
                {
                    return true;
                }
                else
                {
                    return false; // if key not in BST
                }
            }
        }
        else
        {
            throw new IllegalArgumentException(); // exception
        }
    }

    public int findMin()
    {
        TreeNode treeNode = root;
        int save = -1;

        while (treeNode != null) // run so long as there is a node in a BST
        {
            if (treeNode.deleted == false) // if node "exists"
            {
                save = treeNode.key;
            }
            treeNode = treeNode.leftChild;
        }

        return save; // return min key in BST; if no node in BST return -1
    }

    public int findMax()
    {
        TreeNode treeNode = root;
        int save = -1;

        while (treeNode != null)
        {
            if (treeNode.deleted == false) // if node "exists"
            {
                save = treeNode.key;
            }
            treeNode = treeNode.rightChild;
        }

        return save; // return max key in BST; if no node in BST return -1
    }

    public String toString()
    {
        TreeNode treeNode = root;
        printString = ""; // initialize string so it can be appended to

        toString2(treeNode); // go through BST pre-order way and append keys to string

        return printString;
    }

    public void toString2(TreeNode treeNode)
    {
        if (treeNode == null) // base case
            return;

        if (treeNode.deleted == true) // if node has been "deleted" add an asterisk before the number
        {
            printString = printString + "*" + treeNode.key + " ";

        }
        else // for non-deleted node - no asterisk before number
        {
            printString = printString + treeNode.key + " ";
        }

        toString2(treeNode.leftChild);
        toString2(treeNode.rightChild);
    }

    public int height()
    {
        TreeNode treeNode = root;

        return height2(treeNode);
    }

    public int height2(TreeNode treeNode)
    {
        if (treeNode == null) // base case
        {
            return -1; // since we need to add one at end
        }
        else
        {
            // find max height of the tree
            int leftChildHeight = height2(treeNode.leftChild);
            int rightChildHeight = height2(treeNode.rightChild);
            int maxTreeHeight = Math.max(leftChildHeight, rightChildHeight); // max height from the left/right child

            return (maxTreeHeight + 1); // increment height of the tree including "deleted" nodes
        }
    }

    public int size()
    {
        return size; // return number of nodes in the BST including "deleted" ones
    }
}