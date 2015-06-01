/*****************************************************************************************
 * 
 * Copyright 2015 Gregory Brown. All Rights Reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 * 
 ***************************************************************************************** 
 */

package org.gabsocial.collection;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import org.gabsocial.gabdev.validate.Validate;


/**
 * 
 * This is a Binary Tree data structure that holds a value. The value may be
 * null. A node can have one left child and one right child. A child may be
 * null.
 * 
 * This tree may hold onto multiple nodes with the same data.
 * 
 * 
 * @author Gregory Brown (sysdevone)
 * 
 * @param <T>
 *            This defines the class type of the data.
 */
public class BinaryTree<T>
{
    
    /**
     * The node within a Tree.
     * 
     * 
     * @author Gregory Brown (sysdevone)
     * 
     * @param <T>
     *            This defines the class type of the data.
     */
    public static class Node<T>
    {
        
        /*
         * The data held in this node. May be null.
         */
        private final T             _data;
        
        /*
         * Holds the right child;
         */
        private Node<T>             _leftChild;
        
        /*
         * The parent node to this node. May be null if this node is the root.
         */
        private Node<T>             _parent;
        
        /*
         * Holds the right child;
         */
        private Node<T>             _rightChild;
        
        /*
         * The tree that this node belongs too.
         */
        private final BinaryTree<T> _tree;
        
        /**
         * Constructor to create a node in the Tree.
         * 
         * @param tree
         *            The <code>Tree</code> instance that created the node.
         * @param parent
         *            The parent bound to the node.
         * @param data
         *            The data bound to the node.
         */
        protected Node(final BinaryTree<T> tree, final Node<T> parent,
                final T data)
        {
            assert (tree != null) : "Not able to create Node.  The parameter 'tree' should not be null.";
            //assert (parent != null) : "Not able to create Node.  The parameter 'parent' should not be null.";
            assert (data != null) : "Not able to create Node.  The parameter 'data' should not be null.";
            
            this._tree = tree;
            this._parent = parent;
            this._data = data;
        }
        
        /**
         * Adds a left child to the node. Can only be added if the left child is
         * null. If it is not null, then remove the previous child before
         * adding.
         * 
         * @param data
         *            Is an object instance held in the Node.
         * @return A <code>Node</code> instance that was created. If the data is
         *         already a child of this node, then null is returned.
         */
        public Node<T> setLeftChild(final T data)
        {
            Validate.isNotNull(this.getClass(),
                    "The parameter 'data' should not be null.", data);
            
            if (this._leftChild != null)
            {
                throw (new IllegalStateException(
                        "The left child needs to be removed before you can set a new child."));
            }
            else
            {
                final Node<T> node = new Node<T>(this._tree, this, data);
                this._leftChild = node;
                return (node);
            }
        }
        
        /**
         * Adds a right child to the node. Can only be added if the right child
         * is null. If it is not null, then remove the previous child before
         * adding.
         * 
         * @param data
         *            Is an object instance held in the Node.
         * @return A <code>Node</code> instance that was created. If the data is
         *         already a child of this node, then null is returned.
         */
        public Node<T> setRightChild(final T data)
        {
            Validate.isNotNull(this.getClass(),
                    "The parameter 'data' should not be null.", data);
            if (this._rightChild != null)
            {
                throw (new IllegalStateException(
                        "The right child needs to be removed before you can set a new child."));
            }
            else
            {
                final Node<T> node = new Node<T>(this._tree, this, data);
                this._rightChild = node;
                return (node);
            }
        }
        
        /*
         * Gets the count count from this node down.
         * 
         * @return An integer number of 0 to n.
         */
        private int getChildCount()
        {
            int count = 1;
            
            if (this.isLeaf())
            {
                count = 1;
            }
            else
            {
                if (this._leftChild != null)
                {
                    count += this._leftChild.getChildCount();
                }
                
                if (this._rightChild != null)
                {
                    count += this._rightChild.getChildCount();
                }
            }
            
            return (count);
        }
        
        /**
         * Gets the data bound to the node.
         * 
         * @return The data held in the node.
         */
        public T getData()
        {
            return (this._data);
        }
        
        /*
         * Gets the height of the tree based on this node. Uses recursion.
         */
        private int getHeight()
        {
            int height = 0;
            
            if (this.isLeaf())
            {
                height = 0;
            }
            else
            {
                int leftHeight = 0;
                int rightHeight = 0;
                if (this._leftChild != null)
                {
                    leftHeight = 1 + this._leftChild.getHeight();
                }
                
                if (this._rightChild != null)
                {
                    rightHeight = 1 + this._rightChild.getHeight();
                }
                
                if (rightHeight > leftHeight)
                {
                    height = rightHeight;
                }
                else
                {
                    height = leftHeight;
                }
            }
            return (height);
            
        }
        
        /*
         * A helper method to walk through the nodes finding the leaf data.
         */
        List<T> getLeafData(final List<T> data)
        {
            if (this.isLeaf())
            {
                data.add(this._data);
            }
            else
            {
                if (this._leftChild != null)
                {
                    this._leftChild.getLeafData(data);
                }
                if (this._rightChild != null)
                {
                    this._rightChild.getLeafData(data);
                }
            }
            return (data);
        }
        
        /**
         * Gets the left node associated with the data.
         * 
         * @return A <code>Node</code> instance.
         */
        public Node<T> getLeftChild()
        {
            return (this._leftChild);
        }
        
        /**
         * Gets the parent of this node.
         * 
         * @return A <code>Node</code> instance that is the parent of this node.
         *         May be null if this node is the root of the tree.
         */
        public Node<T> getParent()
        {
            return (this._parent);
        }
        
        /**
         * Gets the left node associated with the data.
         * 
         * @return A <code>Node</code> instance.
         */
        public Node<T> getRightChild()
        {
            return (this._rightChild);
        }
        
        /**
         * A method to determine if the node is empty. A node is empty if it
         * does not have any ata.
         * 
         * @return A boolean value. Will return true if the node is empty,
         *         otherwise it will be false.
         */
        public boolean isEmpty()
        {
            final boolean isEmpty = (this._data == null);
            return (isEmpty);
        }
        
        /**
         * A method to determine if the node is a leaf. A node is a leaf if it
         * does not have any children.
         * 
         * @return A boolean value. Will return true if the node is a leaf,
         *         otherwise it will be false.
         */
        public boolean isLeaf()
        {
            final boolean isLeaf = (this._leftChild == null)
                    && (this._rightChild == null);
            return (isLeaf);
        }
        
        /**
         * A method to determine if the node is the root. A node is the root if
         * it does not have a parent. A Tree can have only one root node.
         * 
         * @return A boolean value. Will return true if the node is the root.,
         *         otherwise it will be false.
         */
        public boolean isRoot()
        {
            final Node<T> root = this._tree.getRoot();
            return (root.equals(this));
        }
        
        /**
         * Removes a child from the node. The complete subtree is removed. The
         * node that was removed is returned so that additional processing can
         * be performed.
         * 
         * @return The <code>Node</code> instance that was removed. May be null
         *         if not found.
         */
        public Node<T> removeLeftChild()
        {
            
            final Node<T> removedNode = this._leftChild;
            this._leftChild = null;
            
            // clear parent on node that is removed.
            if (removedNode != null)
            {
                removedNode.setParent(null);
            }
            
            // TODO FIXME
            // remove the node then get child successor/predessor based on
            // inorder
            // hook in a strategy object?
            // the strategy implementation will determine the course of action.
            
            return (removedNode);
            
        }
        
        /*
         * Sets the parent of the node. This is called when the addChild method
         * is called.
         */
        private void setParent(final Node<T> parent)
        {
            assert (parent != null) : "The parameter 'parent' should not be null.";
            assert (parent.getData() != null) : "The parameter parent's data should not be null.";
            this._parent = parent;
        }
    }
    
    /*
     * The root of the tree. Should not be null.
     */
    private final Node<T> _root;
    
    /**
     * Constructor of the Tree. A Tree must have one <code>Node</code> instance
     * that is the root.
     * 
     * @param data
     *            The data of the root <code>Node</code> instance. May be null.
     */
    public BinaryTree(final T data)
    {
        final Node<T> root = this.createNode(data);
        this._root = root;
    }
    
    /*
     * A factory helper method that creates the <code>Node</code>
     * implementation.
     * 
     * @return The <code>Node</code> instance that was created.
     */
    protected Node<T> createNode(final T data)
    {
        final Node<T> node = new Node<T>(this, null, data);
        return (node);
    }
    
    /**
     * Gets the height of the Tree.
     * 
     * @return An integer of 0 to n.
     */
    public int getHeight()
    {
        return (this._root.getHeight());
    }
    
    /**
     * Gets all of the leaf data sorted by in ordering. A leaf is a Node that
     * does not have children.
     * 
     * @return A <code>List</code> instance containing the leaf node data.
     */
    public List<T> getInOrderLeafData()
    {
        
        // travel the left
        // visit ( node )
        // travel the right
        
        final LinkedList<T> data = new LinkedList<T>();
        Node<T> node = this._root;
        final Stack<Node<T>> stack = new Stack<Node<T>>();
        while (!stack.isEmpty() || (node != null))
        {
            if (node != null)
            {
                stack.push(node);
                node = node.getLeftChild();
            }
            else
            {
                node = stack.pop();
                data.add(node.getData());
                node = node.getRightChild();
            }
        }
        
        return (data);
    }
    
    /**
     * Gets all of the data within the leaf nodes. A leaf is a Node that does
     * not have children.
     * 
     * @return A <code>List</code> instance containing the leaf node data.
     */
    public List<T> getLeafData()
    {
        final LinkedList<T> data = new LinkedList<T>();
        this._root.getLeafData(data);
        return (data);
    }
    
    /**
     * Gets all of the leaf data sorted by level ordering. A leaf is a Node that
     * does not have children.
     * 
     * @return A <code>List</code> instance containing the leaf node data.
     */
    public List<T> getLevelOrderLeafData()
    {
        final LinkedList<T> data = new LinkedList<T>();
        final Queue<Node<T>> queue = new LinkedList<Node<T>>();
        queue.add(this._root);
        while (!queue.isEmpty())
        {
            final Node<T> node = queue.remove();
            data.add(node.getData());
            final Node<T> leftChild = node.getLeftChild();
            if (leftChild != null)
            {
                queue.add(leftChild);
            }
            
            final Node<T> rightChild = node.getRightChild();
            if (rightChild != null)
            {
                queue.add(rightChild);
            }
        }
        return (data);
    }
    
    /**
     * Gets the number of nodes contained in this tree. A Tree instance will
     * always have one node that is the root;
     * 
     * @return An integer value from 1 to n.
     */
    public int getNodeCount()
    {
        return (this._root.getChildCount());
    }
    
    /**
     * Gets all of the leaf data sorted by Post ordering. A leaf is a Node that
     * does not have children.
     * 
     * @return A <code>List</code> instance containing the leaf node data.
     */
    public List<T> getPostOrderLeafData()
    {
        // travel the left
        // travel the right
        // visit ( node )
        
        final LinkedList<T> data = new LinkedList<T>();
        Node<T> node = this._root;
        Node<T> lastVisitedNode = null;
        final Stack<Node<T>> stack = new Stack<Node<T>>();
        
        while (!stack.isEmpty() || (node != null))
        {
            if (node != null)
            {
                stack.push(node);
                node = node.getLeftChild();
            }
            else
            {
                final Node<T> peekNode = stack.peek();
                if ((peekNode.getRightChild() != null)
                        && (peekNode.getRightChild() != lastVisitedNode))
                {
                    node = peekNode.getRightChild();
                }
                else
                {
                    lastVisitedNode = stack.pop();
                    if (!lastVisitedNode.isEmpty())
                    {
                        data.add(lastVisitedNode.getData());
                    }
                }
            }
        }
        
        return (data);
    }
    
    /**
     * Gets all of the leaf data sorted by pre ordering. A leaf is a Node that
     * does not have children.
     * 
     * @return A <code>List</code> instance containing the leaf node data.
     */
    public List<T> getPreOrderLeafData()
    {
        // visit ( node );
        // travel to the left
        // travel to the right.
        final LinkedList<T> data = new LinkedList<T>();
        final Stack<Node<T>> stack = new Stack<Node<T>>();
        
        stack.push(this._root);
        
        while (!stack.isEmpty())
        {
            final Node<T> node = stack.pop();
            if (!node.isEmpty())
            {
                data.add(node.getData());
            }
            
            final Node<T> leftChild = node.getLeftChild();
            if (leftChild != null)
            {
                stack.push(leftChild);
            }
            
            final Node<T> rightChild = node.getRightChild();
            if (rightChild != null)
            {
                stack.push(rightChild);
            }
            
        }
        
        return (data);
    }
    
    /**
     * Gets the root node of the Tree. A Tree can only have one root.
     * 
     * @return The <code>Node</code> instance that is the root.
     */
    public Node<T> getRoot()
    {
        return (this._root);
    }
    
}
