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

package com.gabstudios.collection;

import java.util.List;

import com.gabstudios.collection.LinkedHashMapTree.Node;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class LinkedHashMapTreeTest
{
    
    public static final void main(final String[] args)
    {
        final LinkedHashMapTreeTest test = new LinkedHashMapTreeTest();
        try
        {
            test.initialize();
        }
        finally
        {
            test.close();
        }
    }
    
    @After
    public void close()
    {
        
    }
    
    @Before
    public void initialize()
    {
        
    }
    
    @Test
    public void testRoot()
    {
        final LinkedHashMapTree<String> tree = new LinkedHashMapTree<String>(
                "root");
        final Node<String> node = tree.getRoot();
        org.junit.Assert.assertNotNull("The tree root should not be null.",
                node);
        final boolean isRoot = node.isRoot();
        org.junit.Assert.assertTrue("The tree is not the root.", isRoot);
        final boolean isLeaf = node.isLeaf();
        org.junit.Assert.assertTrue(
                "The tree has children when it should not.", isLeaf);
        final boolean isEmpty = node.isEmpty();
        org.junit.Assert.assertTrue("The tree should have data.", !isEmpty);
        
        String data = node.getData();
        org.junit.Assert.assertTrue("The tree should have data that equals 'root'.", "root".equals(data));
        
        final List<Node<String>> rootChildren = node.getChildren();
        org.junit.Assert.assertNotNull(
                "The tree's child list should not be a null object.",
                rootChildren);
        
        org.junit.Assert.assertEquals("The tree's child list should be empty.",
                0, rootChildren.size());
    }
    
    @Test
    public void testRootAddChild()
    {
        final LinkedHashMapTree<String> tree = new LinkedHashMapTree<String>(
                "root");
        final Node<String> root = tree.getRoot();
        root.addChild("helloworld");
        final List<Node<String>> children = root.getChildren();
        org.junit.Assert.assertEquals("The root's child list should be empty.",
                1, children.size());
        
        final Node<String> child = children.get(0);
        org.junit.Assert.assertEquals(
                "The child's data does not equal what is expected",
                "helloworld", child.getData());
        
        org.junit.Assert.assertEquals(
                "The tree's height does not equal what is expected", 1,
                tree.getHeight());
        
    }
    
    @Test
    public void testTreeHeight()
    {
        final LinkedHashMapTree<String> tree = new LinkedHashMapTree<String>(
                "root");
        final Node<String> root = tree.getRoot();
        root.addChild("H").addChild("E").addChild("L").addChild("L")
                .addChild("O").addChild("W").addChild("O").addChild("R")
                .addChild("L").addChild("D").addChild("HELLOWORLD");
        
        org.junit.Assert.assertEquals(
                "The tree's height does not equal what is expected", 11,
                tree.getHeight());
    }
    
    @Test
    public void testTreeHeight2()
    {
        final LinkedHashMapTree<String> tree = new LinkedHashMapTree<String>(
                "root");
        final Node<String> root = tree.getRoot();
        root.addChild("H").addChild("E").addChild("L").addChild("L")
                .addChild("O");
        root.addChild("W");
        root.addChild("O").addChild("R").addChild("L").addChild("D");
        
        org.junit.Assert.assertEquals(
                "The tree's height does not equal what is expected", 5,
                tree.getHeight());
    }
    
    @Test
    public void testTreeNodeCount()
    {
        final LinkedHashMapTree<String> tree = new LinkedHashMapTree<String>(
                "root");
        final Node<String> root = tree.getRoot();
        root.addChild("H").addChild("E").addChild("L").addChild("L")
                .addChild("O").addChild("W").addChild("O").addChild("R")
                .addChild("L").addChild("D").addChild("HELLOWORLD");
        
        // root + 11 child nodes = 12
        org.junit.Assert.assertEquals(
                "The tree's count does not equal what is expected", 12,
                tree.getNodeCount());
    }
    
    @Test
    public void testNodeParent()
    {
        final LinkedHashMapTree<String> tree = new LinkedHashMapTree<String>(
                "root");
        final Node<String> root = tree.getRoot();
        root.addChild("H").addChild("E").addChild("L").addChild("L")
                .addChild("O");
        root.addChild("W");
        
        Node<String> expectedParent = root.addChild("O").addChild("R")
                .addChild("L");
        
        Node<String> child = expectedParent.addChild("D");
        
        Node<String> parent = child.getParent();
        
        org.junit.Assert.assertEquals("The nodes parents are not equal",
                expectedParent, parent);
    }
    
}
