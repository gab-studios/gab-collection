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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.gabsocial.gabdev.validate.Validate;


/**
 * 
 * This is a Tree data structure that uses a key to reference a node. A node may
 * hold onto a data value. This allows for a key to reference a value.
 * 
 * A tree may hold onto multiple nodes with the same key.
 * 
 * 
 * @author Gregory Brown (sysdevone)
 * 
 * @param <K>
 *            This defines the class type of the key.
 * @param <V>
 *            This defines the class type of the value bound to the key.
 */
public class ValueTreeMap<K, V> implements TreeMap<K, V>
{
    /*
     * Holds the child nodes of this node.
     */
    private java.util.TreeMap<K, TreeMap<K, V>> _children;
    
    /*
     * The name of this node. Should not be null.
     */
    private K                                   _name;
    
    /*
     * The parent node to this node. May be null if this node is the root.
     */
    private TreeMap<K, V>                       _parent;
    
    /*
     * A value bound to the node's key. May be null.
     */
    private V                                   _value;
    
    /**
     * Constructor that defines the key bound to this node.
     * 
     * @param key
     *            A key that is used to reference this node.
     */
    public ValueTreeMap(final K key)
    {
        this(key, null);
    }
    
    /**
     * Constructor that defines a key bound to this node that references a
     * value.
     * 
     * @param key
     *            A key that is used to reference this node.
     * @param value
     *            The value within the node.
     */
    public ValueTreeMap(final K key, final V value)
    {
        Validate.isNotNull(this.getClass(),
                "The parameter 'key' should not be null.", key);
        this._name = key;
        this._value = value;
        this._children = new java.util.TreeMap<K, TreeMap<K, V>>();
    }
    
    /**
     * Adds a <code>Tree</code> instance to this <code>Tree</code>. That newly
     * added tree's parent will be this tree.
     * 
     * @param tree
     *            A <code>Tree</code> instance.
     * @return The key of the tree that is being added.
     */
    @Override
    public K add(final TreeMap<K, V> tree)
    {
        Validate.isNotNull(this.getClass(),
                "The parameter 'tree' must not be null.", tree);
        
        tree.setParent(this);
        final K key = this.getKey();
        this._children.put(key, tree);
        return (key);
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.gabsocial.collection.Tree#addChild(K)
     */
    @Override
    public TreeMap<K, V> addChild(final K key)
    {
        Validate.isNotNull(this.getClass(),
                "The parameter 'key' should not be null.", key);
        final ValueTreeMap<K, V> node = new ValueTreeMap<K, V>(key);
        node.setParent(this);
        this._children.put(key, node);
        return (node);
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.gabsocial.collection.Tree#addChild(K, V)
     */
    @Override
    public TreeMap<K, V> addChild(final K key, final V value)
    {
        Validate.isNotNull(this.getClass(),
                "The parameter 'key' should not be null.", key);
        Validate.isNotNull(this.getClass(),
                "The parameter 'value' should not be null.", value);
        final ValueTreeMap<K, V> node = new ValueTreeMap<K, V>(key, value);
        node.setParent(this);
        this._children.put(key, node);
        return (node);
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.gabsocial.collection.Tree#containsChild(K)
     */
    @Override
    public boolean containsChild(final K key)
    {
        return (this._children.containsKey(key));
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.gabsocial.collection.Tree#findLeafKeys()
     */
    @Override
    public List<K> findLeafKeys()
    {
        final List<K> keys = new ArrayList<K>();
        this.findLeafKeys(keys);
        return (keys);
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.gabsocial.collection.Tree#findLeafKeys(List<K> keys)
     */
    @Override
    public List<K> findLeafKeys(final List<K> keys)
    {
        if (this.isLeaf())
        {
            keys.add(this._name);
        }
        else
        {
            final Collection<TreeMap<K, V>> childNodes = this._children
                    .values();
            for (final TreeMap<K, V> childNode : childNodes)
            {
                childNode.findLeafKeys(keys);
            }
        }
        return (keys);
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.gabsocial.collection.Tree#findLeafValues()
     */
    @Override
    public List<V> findLeafValues()
    {
        final List<V> values = new ArrayList<V>();
        this.findLeafValues(values);
        return (values);
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.gabsocial.collection.Tree#findLeafValues(List<V> values)
     */
    @Override
    public List<V> findLeafValues(final List<V> values)
    {
        if (this.isLeaf())
        {
            if (this._value != null)
            {
                values.add(this._value);
            }
        }
        else
        {
            final Collection<TreeMap<K, V>> childNodes = this._children
                    .values();
            for (final TreeMap<K, V> childNode : childNodes)
            {
                childNode.findLeafValues(values);
            }
        }
        return (values);
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.gabsocial.collection.Tree#getChild(K)
     */
    @Override
    public TreeMap<K, V> getChild(final K key)
    {
        final TreeMap<K, V> child = this._children.get(key);
        return (child);
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.gabsocial.collection.Tree#getKey()
     */
    @Override
    public K getKey()
    {
        return (this._name);
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.gabsocial.collection.Tree#getParent()
     */
    @Override
    public TreeMap<K, V> getParent()
    {
        return (this._parent);
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.gabsocial.collection.Tree#getRoot()
     */
    @Override
    public TreeMap<K, V> getRoot()
    {
        TreeMap<K, V> root = null;
        final TreeMap<K, V> parent = this.getParent();
        if (parent != null)
        {
            root = parent.getRoot();
        }
        else
        {
            root = this;
        }
        return (root);
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.gabsocial.collection.Tree#getValue()
     */
    @Override
    public V getValue()
    {
        return (this._value);
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.gabsocial.collection.Tree#isLeaf()
     */
    @Override
    public boolean isLeaf()
    {
        return (this._children.isEmpty());
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.gabsocial.collection.Tree#removeChild(K)
     */
    @Override
    public TreeMap<K, V> removeChild(final K key)
    {
        final TreeMap<K, V> child = this._children.remove(key);
        child.setParent(null);
        return (child);
    }
    
    /*
     * Sets the parent of this <code>Tree</code> instance.
     */
    @Override
    public void setParent(final TreeMap<K, V> parent)
    {
        this._parent = parent;
    }
    
}
