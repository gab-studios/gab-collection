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
 *            This defines the type of key.
 * @param <V>
 *            This defines the type of value bound to the key.
 */
public interface TreeMap<K, V>
{
    
    /**
     * Adds a <code>Tree</code> instance to this <code>Tree</code>. That newly
     * added tree's parent will be this tree.
     * 
     * @param tree
     *            A <code>Tree</code> instance.
     * @return The key of the tree that is being added.
     */
    public abstract K add(TreeMap<K, V> tree);
    
    /**
     * Adds a child node to this node.
     * 
     * @param key
     *            The key that is used to reference the child node.
     * @return A subtree <code>Tree</code> instance with the child node as the
     *         root.
     */
    public abstract TreeMap<K, V> addChild(K key);
    
    /**
     * Adds a child node to this node with a value.
     * 
     * @param key
     *            The key that is used to reference the child node.
     * @param value
     *            The value that is bound to the node.
     * @return A subtree <code>Tree</code> instance with the child node as the
     *         root.
     */
    public abstract TreeMap<K, V> addChild(K key, V value);
    
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
     * Finds all of the leaf values of this tree by visiting all of the children
     * of each node. A leaf is a node without children. If a leaf node has a
     * value that is not null, then it will be returned.
     * 
     * @param keys
     *            A <code>List</code> that will have the new keys added too.
     * 
     * @return A <code>List</code>instance containing non null keys from leaf
     *         nodes.
     */
    public abstract List<K> findLeafKeys(List<K> keys);
    
    /**
     * Finds all of the leaf values of this tree by visiting all of the children
     * of each node. A leaf is a node without children. If a leaf node has a
     * value that is not null, then it will be returned.
     * 
     * @return A <code>List</code>instance containing non null values from leaf
     *         nodes.
     */
    public abstract List<V> findLeafValues();
    
    /**
     * Finds all of the leaf values of this tree by visiting all of the children
     * of each node. A leaf is a node without children. If a leaf node has a
     * value that is not null, then it will be returned.
     * 
     * @param values
     *            A <code>List</code> that will have this trees leaf values
     *            added too.
     * 
     * @return A <code>List</code>instance containing non null values from leaf
     *         nodes.
     */
    public abstract List<V> findLeafValues(List<V> values);
    
    /**
     * Gets a child node referenced by the key. May be null if that key is not
     * found.
     * 
     * @param key
     *            The key that is used to reference the child node.
     * @return A subtree <code>Tree</code> instance with the child node as the
     *         root.
     */
    public abstract TreeMap<K, V> getChild(K key);
    
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
    public abstract TreeMap<K, V> getParent();
    
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
    public abstract TreeMap<K, V> getRoot();
    
    /**
     * Gets the value bound to this node.
     * 
     * @return A value bound to this node, may be null.
     */
    public abstract V getValue();
    
    /**
     * Determines if a node is a leaf. A node is a leaf if it has not children.
     * 
     * @return boolean, true if this node is a leaf. Otherwise false is
     *         returned.
     */
    public abstract boolean isLeaf();
    
    /**
     * Removes a child <code>TreeMap</code> from this <code>TreeMap</code>. The
     * removed <code>TreeMap</code> will have its parent set to null.
     * 
     * @param key
     *            The key that is used to reference the child node.
     * 
     * @return The subtree <code>Tree</code> instance of the child that is being
     *         removed.
     */
    public abstract TreeMap<K, V> removeChild(K key);
    
    /**
     * Sets the parent of this <code>TreeMap</code> to another
     * <code>TreeMap</code>. The previous parent will be removed and the parent
     * tree will remove this tree from its children.
     * 
     * @param tree
     *            A <code>TreeMap</code> instance that will be the parent of
     *            this node.
     */
    public abstract void setParent(TreeMap<K, V> tree);
    
}
