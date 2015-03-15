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

import java.util.List;


/**
 * 
 * 
 * @author Gregory Brown (sysdevone)
 * 
 * @param <K>
 *            This defines the type of the key.
 */
public interface Tree<K>
{
    
    /**
     * Adds a child node to this node.
     * 
     * @param key
     *            The key that is used to reference the child node.
     * @return A subtree <code>Tree</code> instance with the child node as the
     *         root.
     */
    public abstract Tree<K> addChild(K key);
    
    /**
     * Determines if the key is contained with in the children of this node. It
     * will not grand children.
     * 
     * @param key
     *            The key to look for.
     * @return A boolean - true, if the key is contained within the child of
     *         this node. Otherwise false is returned.
     */
    public abstract boolean containsChild(K key);
    
    /**
     * Finds all of the leaf values of this tree by visiting all of the children
     * of each node. A leaf is a node without children. If a leaf node has a
     * value that is not null, then it will be returned.
     * 
     * @return A <code>List</code>instance containing non null values from leaf
     *         nodes.
     */
    public abstract List<K> findLeafKeys();
    
    /**
     * Gets a child node referenced by the key. May be null if that key is not
     * found.
     * 
     * @param key
     *            The key that is used to reference the child node.
     * @return A subtree <code>Tree</code> instance with the child node as the
     *         root.
     */
    public abstract Tree<K> getChild(K key);
    
    /**
     * Gets the key of this node.
     * 
     * @return The key of this node.
     */
    public abstract K getKey();
    
    /**
     * Gets the parent tree of this node. That tree will contain this node as a
     * child.
     * 
     * @return A <code>Tree</code> instance with this node as a child. The root
     *         of this tree is the parent.
     */
    public abstract Tree<K> getParent();
    
    /**
     * Gets the root of a tree by visiting the parent trees until the parent is
     * null.
     * 
     * @return A <code>Tree</code> instance with this node as a child. The root
     *         of this tree is the parent. If the parent is null, then the
     *         current node is returned as root. The method getKey() will return
     *         the root's key value if the parent is null.
     * @see #getKey()
     */
    public abstract Tree<K> getRoot();
    
    /**
     * Determines if a node is a leaf. A node is a leaf if it has not children.
     * 
     * @return boolean, true if this node is a leaf. Otherwise false is
     *         returned.
     */
    public abstract boolean isLeaf();
    
    /**
     * Removes a child node from this node.
     * 
     * @param key
     *            The key that is used to reference the child node.
     * 
     * @return The subtree <code>Tree</code> instance of the child that is being
     *         removed.
     */
    public abstract Tree<K> removeChild(K key);
    
    /**
     * Adds a <code>Tree</code> instance to this <code>Tree</code>. That newly
     * added tree's parent will be this tree.
     * 
     * @param tree
     *            A <code>Tree</code> instance.
     * @return The key of the tree that is being added.
     */
    public abstract K add(Tree<K> tree);
    
}
