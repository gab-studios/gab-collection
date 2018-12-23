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

import com.gabstudios.collection.BinaryTree.Node;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class BinaryTreeTest
{
    
    public static final void main(final String[] args)
    {
        final BinaryTreeTest test = new BinaryTreeTest();
        try
        {
            test.initialize();
            test.testLevelOrderNode();
            test.testPreOrderNode();
            test.testInOrderNode();
            test.testPostOrderNode();
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
        final BinaryTree<String> tree = new BinaryTree<String>(
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
//        org.junit.Assert.assertFalse(
//                "The tree has data when it should not.", isEmpty);
//        final List<Node<String>> rootChildren = node.getChildren();
//        org.junit.Assert.assertNotNull(
//                "The tree's child list should not be a null object.",
//                rootChildren);
//        org.junit.Assert.assertEquals("The tree's child list should be empty.",
//                0, rootChildren.size());
    }
    
    @Test
    public void testRootAddChild()
    {
        final BinaryTree<String> tree = new BinaryTree<String>(
                "root");
        final Node<String> root = tree.getRoot();
        root.setLeftChild("hello");
        root.setRightChild("world");
//        final List<Node<String>> children = root.getChildren();
//        org.junit.Assert.assertEquals("The root's child list should be empty.",
//                1, children.size());
//        
//        final Node<String> child = children.get(0);
//        org.junit.Assert.assertEquals(
//                "The child's data does not equal what is expected",
//                "helloworld", child.getData());
        
        org.junit.Assert.assertEquals(
                "The tree's height does not equal what is expected", 1,
                tree.getHeight());
        
    }
    
    @Test
    public void testTreeHeight()
    {
        final BinaryTree<String> tree = new BinaryTree<String>(
                "root");
        final Node<String> root = tree.getRoot();
        root.setLeftChild("H").setLeftChild("E").setLeftChild("L").setLeftChild("L")
                .setLeftChild("O").setLeftChild("W").setLeftChild("O").setLeftChild("R")
                .setLeftChild("L").setLeftChild("D").setLeftChild("HELLOWORLD");
        
        org.junit.Assert.assertEquals(
                "The tree's height does not equal what is expected", 11,
                tree.getHeight());
    }
    
    @Test
    public void testTreeHeight2()
    {
        final BinaryTree<String> tree = new BinaryTree<String>(
                "root");
        final Node<String> root = tree.getRoot();
        root.setLeftChild("H").setRightChild("E").setLeftChild("L").setRightChild("L")
                .setLeftChild("O");
        root.setRightChild("O").setLeftChild("R").setRightChild("L").setLeftChild("D");
        
        org.junit.Assert.assertEquals(
                "The tree's height does not equal what is expected", 5,
                tree.getHeight());
    }
    
    @Test
    public void testTreeNodeCount()
    {
        final BinaryTree<String> tree = new BinaryTree<String>(
                "root");
        final Node<String> root = tree.getRoot();
        root.setLeftChild("H").setLeftChild("E").setLeftChild("L").setLeftChild("L")
                .setLeftChild("O").setLeftChild("W").setLeftChild("O").setLeftChild("R")
                .setLeftChild("L").setLeftChild("D").setLeftChild("HELLOWORLD");
        
        // root + 11 child nodes = 12
        org.junit.Assert.assertEquals(
                "The tree's count does not equal what is expected", 12,
                tree.getNodeCount());
    }
    
    @Test
    public void testNodeParent()
    {
        final BinaryTree<String> tree = new BinaryTree<String>(
                "root");
        final Node<String> root = tree.getRoot();
        root.setLeftChild("H").setRightChild("E").setLeftChild("L").setRightChild("L")
                .setLeftChild("O");
        
        Node<String> expectedParent = root.setRightChild("O").setLeftChild("R")
                .setLeftChild("L");
        
        Node<String> child = expectedParent.setLeftChild("D");
        
        Node<String> parent = child.getParent();
        
        org.junit.Assert.assertEquals("The nodes parents are not equal",
                expectedParent, parent);
    }
    
    @Test
    public void testLevelOrderNode()
    {
        final BinaryTree<String> tree = new BinaryTree<String>(
                "1");
        
        final Node<String> root = tree.getRoot();
        final Node<String> child1 = root.setLeftChild("2");
        final Node<String> child2 = root.setRightChild("3");
        
        child1.setLeftChild("4");
        child1.setRightChild("5");
        
        child2.setLeftChild("6");
        child2.setRightChild("7");
        
        List<String> levelOrderList = tree.getLevelOrderLeafData();
        
//        System.out.println("Level: ");
//        for (String data : levelOrderList)
//        {
//            System.out.println("Data: " + data);
//        }
    }
    
    @Test
    public void testPreOrderNode()
    {
        final BinaryTree<String> tree = new BinaryTree<String>(
                "1");
        
        final Node<String> root = tree.getRoot();
        final Node<String> child1 = root.setLeftChild("2");
        final Node<String> child2 = root.setRightChild("3");
        
        child1.setLeftChild("4");
        child1.setRightChild("5");
        
        child2.setLeftChild("6");
        child2.setRightChild("7");
        
//        System.out.println("Pre: ");
//        List<String> preOrderList = tree.getLevelOrderLeafData();
//        for (String data : preOrderList)
//        {
//            System.out.println("Data: " + data);
//        }
    }
    
    @Test
    public void testInOrderNode()
    {
        final BinaryTree<String> tree = new BinaryTree<String>(
                "1");
        
        final Node<String> root = tree.getRoot();
        final Node<String> child1 = root.setLeftChild("2");
        final Node<String> child2 = root.setRightChild("3");
        
        child1.setLeftChild("4");
        child1.setRightChild("5");
        
        child2.setLeftChild("6");
        child2.setRightChild("7");
        
//        System.out.println("In: ");
//        List<String> inOrderList = tree.getInOrderLeafData();
//        for (String data : inOrderList)
//        {
//            System.out.println("Data: " + data);
//        }
    }
    
    @Test
    public void testPostOrderNode()
    {
        final BinaryTree<String> tree = new BinaryTree<String>(
                "1");
        
        final Node<String> root = tree.getRoot();
        final Node<String> child1 = root.setLeftChild("2");
        final Node<String> child2 = root.setRightChild("3");
        
        child1.setLeftChild("4");
        child1.setRightChild("5");
        
        child2.setLeftChild("6");
        child2.setRightChild("7");
        
//        System.out.println("Post: ");
//        List<String> postOrderList = tree.getPostOrderLeafData();
//        for (String data : postOrderList)
//        {
//            System.out.println("Data: " + data);
//        }
    }
}
