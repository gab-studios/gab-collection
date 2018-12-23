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

import org.gabsocial.collection.tree.LinkedHashMapTree;
import org.gabsocial.gabdev.validate.Validate;


/**
 * This is a word container. It is designed to help get suggestions for
 * misspellings.
 * 
 * @author Gregory Brown (sysdevone)
 * 
 */
public class WordSuggestion
{
    /*
     * Uses a tree to help determine suggestions.
     */
    private final LinkedHashMapTree<String> _tree;
    
    /**
     * Default constructor.
     */
    public WordSuggestion()
    {
        this._tree = new LinkedHashMapTree<String>("root");
    }
    
    /**
     * Add a word to the container that will be used as suggestions.
     * 
     * @param word
     *            A <code>String</code> instance. May not be null or empty.
     */
    public void add(final String word)
    {
        Validate.isNotNullOrEmpty(this.getClass(),
                "The parameter 'word' should not be null.", word);
        
        final int count = word.length();
        LinkedHashMapTree.Node<String> node = this._tree.getRoot();
        for (int i = 0; i < count; ++i)
        {
            // break the word into characters and add each character to the
            // tree.
            // A character will be a child of the previous character.
            // The complete word is the final leaf.
            final String character = Character.toString(word.charAt(i));
            if (node.containsChild(character))
            {
                // if the character is contained, get that node so that further
                // characters can be added to it.
                node = node.getChild(character);
            }
            else
            {
                // if the character is not contained, then create a new node so
                // that further characters can be added to it.
                node = node.addChild(character);
            }
        }
        node = node.addChild(word);
    }
    
    /**
     * Gets suggestions for a word.
     * 
     * @param word
     *            A <code>String</code> instance. May not be null or empty.
     * @return A <code>List</code> instance containing zero to many
     *         <code>String</code> instances.
     */
    public List<String> getSuggestions(final String word)
    {
        Validate.isNotNullOrEmpty(this.getClass(),
                "The parameter 'word' should not be null.", word);
        
        //
        // Converts the word to characters.
        // Tests to see if the character exists in the tree.
        // -------------------
        final LinkedList<String> data = new LinkedList<String>();
        final int count = word.length();
        LinkedHashMapTree.Node<String> node = this._tree.getRoot();
        for (int i = 0; i < count; ++i)
        {
            final String character = Character.toString(word.charAt(i));
            if (node.containsChild(character))
            {
                // if the character exists, then get that node.
                // continue walking down the tree character by character.
                node = node.getChild(character);
            }
            else
            {
                // if the character is not found. STOP.
                break;
            }
        }
        
        // if the node is not the root, get all the leaf nodes as a
        // suggestion.
        if (!node.isRoot())
        {
            while (data.isEmpty())
            {
                node.getLeafData(data);
                
                // if leaf data was not found, climb up to the parent node and
                // check again.
                if (data.isEmpty())
                {
                    node = node.getParent();
                    if (node.isRoot())
                    {
                        // if the node is the root, then stop.
                        // we do not want to return every word in this
                        // container.
                        break;
                    }
                }
            }
        }
        
        return (data);
    }
    
    /**
     * Gets the words that were added.
     * 
     * @return A <code>List</code> instance containing zero to many
     *         <code>String</code> instances.
     */
    public List<String> getWords()
    {
        return (this._tree.getLeafData());
    }
}
