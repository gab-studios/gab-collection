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


public class Tree<K, V>
{
	private Tree<K, V> _parent;
	private K _name;
	private V _value;

	private java.util.TreeMap<K, Tree<K, V>> _children;

	public Tree(K key) 
	{
		this(key, null);
	}
	
	public Tree(K key, V value) 
	{
		this._name = key;
		this._value = value;
		this._children = new java.util.TreeMap<K, Tree<K, V>>();
	}

	public K getKey() 
	{
		return (this._name);
	}

	public V getValue() 
	{
		return (this._value);
	}

	public Tree<K, V> getParent() 
	{
		return (this._parent);
	}

	public Tree<K, V> getRoot() 
	{
		Tree<K, V> root = null;
		Tree<K, V> parent = getParent();
		if (parent != null) {
			root = parent.getRoot();
		} else {
			root = this;
		}
		return (root);
	}
	
	public List<V> findLeafValues()
	{
		List<V> values = new ArrayList<V>();
		findLeafValues( values );
		return( values );
	}

	public Tree<K, V> addChild(K key, V value) 
	{
		Tree<K, V> node = new Tree<K, V>(key, value);
		this._children.put(key, node);
		return (node);
	}
	
	public Tree<K, V> addChild(K key) 
	{
		Tree<K, V> node = new Tree<K, V>(key);
		this._children.put(key, node);
		return (node);
	}
	
	public Tree<K, V> removeChild( K key )
	{
		Tree<K, V> child = this._children.remove(key);
		return( child );
	}
	
	public boolean containsChild( K key )
	{
		return( this._children.containsKey(key));
	}
	
	public Tree<K, V> getChild( K key )
	{
		Tree<K, V> child = this._children.get(key);
		return( child );
	}

	public boolean isLeaf() 
	{
		return (this._children.isEmpty());
	}
	
	/*
	 * Walks through the children, adding non null values to the list.
	 */
	private void findLeafValues(List<V> values)
	{
		if( isLeaf() )
		{
			if( this._value != null )
			{
				values.add( this._value);
			}
		}
		else
		{
			Collection<Tree<K, V>> childNodes = this._children.values();
			for( Tree<K,V> childNode: childNodes)
			{
				childNode.findLeafValues(values);
			}
		}
	}
}
