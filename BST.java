/*
Victoria Nerecina
practice assignment 8

BST.java use Practice08Test.java

help from:
https://www.baeldung.com/java-binary-tree
for delete fucntion and removeSmallest

BST - for any node in the tree, all elememts in the left subtree have a value less tha or equal to the value stored in the node
    - all elements in the right subtree are greater than the value stored in the root

find(int) : boolean
    start with node = root
    if key == node.key, return true
    recursivley call "find" on left if key is smaller than node.key; right otherwise
    return false if node is false (base case)

insert(int) : void
    start with node = root
    if the node == null, thne create a node with the key and return it
    recursivley call insert on the left or right

delete(int) : void
find the first node matching item
if the node <= 1 child, promote the child to the parent location
if the node has two children && the right node's left child is a leaf, promote it to parent location
else: recursivley descend the right child's left subtree to a leaf, promote it

 */

import java.util.*;


public class BST<T extends Comparable<T>>
{
    public BSTNode<T> root;



    public BST()
    {
        this.root = null;
    }



    public class BSTNode<T>
    {
        public T data;

        public BSTNode<T> left;
        public BSTNode<T> right;

        public BSTNode(T item)
        {
            this.data = item;
            this.left = null;
            this.right = null;
        }
    }



    public boolean find(T item)
    {
        //return true if item is found in the BST; false otherwise
        return find(root, item); //root node
    }

    private boolean find(BSTNode<T> node, T item)
    {
        //base case: node not found
        if(node ==  null)
        {
            return false;
        }
        //checking current node
        if(item.compareTo(node.data) == 0) //if item matches current data
        {
            //found it
            return true;
        }
        //go to the left or right node dependingn on the item's value
            //relative to the current node
        else if(item.compareTo(node.data) < 0)
        {
            return find(node.left, item);
        }
        else //(item.compareTo(node.data) > 0)
        {
            return find(node.right, item);
        }
    }



    public void insert(T item) //always inserts a leaf node
    {
        //insert item into BST, keeping duplicates in their own nodes
        root = insert(this.root, item); //start recursion from root
    }

    private BSTNode<T> insert(BSTNode<T> node, T item)
    {
        //base case: inset leaf node
        if(node == null)
        {
            //BST newTree = new BST(); //new leaf node
            //return new BST(item);
            return new BSTNode<T>(item);
        }
        //insert to the left or right depending in the item or node.data
        //rebuild the tree in case the child is where the node was inserted
        if(item.compareTo(node.data) < 0)
        {
            node.left = insert(node.left, item);
            return node;
        }
        else if(item.compareTo(node.data) > 0)
        {
            node.right = insert(node.right, item);
            return node;
        }
        else //value already exists
        {
            return node;
        }
        //return node;
    }




    public void print()
    {
        //usimf println, output each item in the BST, in order
        print(this.root);
    }

    private void print(BSTNode node)
    {
        if(node != null)
        {
            print(node.left);
            System.out.println(node.data);
            print(node.right);
        }
    }



    public void delete(T item)
    {
        //delte first instance of item from the BST
        root = delete(this.root, item); //root node
    }

    private BSTNode<T> delete(BSTNode<T> node, T item)
    {
        //base case: item not found
        if(node == null)
        {
            //return null;
            return node;
        }
        //delete to the left or right depending on the item or node.data
        //and rebulid the tree
        if(item.compareTo(node.data) > 0)
        {
            node.right = delete(node.right, item);
            return node;
        }
        else if(item.compareTo(node.data) < 0)
        {
            node.left = delete(node.left, item);
            return node;
        }
        else //(item.compareTo(node.data) == 0)
        {
            //if the node <= 1 child, then promote child to parent position in BST
            if(node.left == null)
            {
                return node.right;
            }
            else if(node.right == null)
            {
                return node.left;
            }
            else //there are 2 children
            {
                //promote the in-order successor(which may be the grandchild)
                if(node.right.left == null)
                {
                    node.data = node.right.data;
                    node.right = node.right.right;
                }
                else //if not. use the "removeSmallest" function to help
                {
                    BSTNode<T> smallest = removeSmallest(node);
                    node = smallest;
                    node = delete(node.right, item);
                }
            }
            return node;
        }
    }

    public BSTNode<T> removeSmallest(BSTNode<T> root)
    {
        if(root.left != null)
        {
            root = root.left;
        }
        return root;
    }


    //helper function for delete()
//    public int removeSmallest(BSTNode<T> node)
////    {
////        //this node is grandparent to a leaf
////        if(node.left.left == null)
////        {
////            //copy data, remove data
////            BSTNode<T> smallest = node.left.data;
////            node.left = node.left.right;
////            //return the data
////            return smallest;
////        }
////        else //recursively try to find a leaf
////        {
////            return removeSmallest(node.left);
////        }
////    }


}