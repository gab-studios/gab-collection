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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * 
 * 
 * 
 * @author Gregory Brown (sysdevone)
 * 
 */
public class WordSuggestionTest
{
    
    public static final void main(final String[] args)
    {
        final WordSuggestionTest test = new WordSuggestionTest();
        try
        {
            test.initialize();
            test.testSuggestion();
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
    public void testSuggestion()
    {
        WordSuggestion suggestion = new WordSuggestion();
        suggestion.add("helloworld");
        suggestion.add("hello");
        suggestion.add("hey");
        suggestion.add("world");
        
        List<String> words = suggestion.getSuggestions("hell012222222");
        org.junit.Assert.assertTrue(words.contains("hello"));
        org.junit.Assert.assertTrue(words.contains("helloworld"));
        org.junit.Assert.assertEquals(2, words.size());
        
        // for (String word : words)
        // {
        // System.out.println("Suggestion Word: " + word);
        // }
        //
        // words = suggestion.getWords();
        // for (String word : words)
        // {
        // System.out.println("Word: " + word);
        // }
    }
    
    @Test
    public void testWords()
    {
        WordSuggestion suggestion = new WordSuggestion();
        suggestion.add("helloworld");
        suggestion.add("hello");
        suggestion.add("hey");
        suggestion.add("world");
        
        List<String> words = suggestion.getWords();
        org.junit.Assert.assertTrue(words.contains("hello"));
        org.junit.Assert.assertTrue(words.contains("helloworld"));
        org.junit.Assert.assertTrue(words.contains("hey"));
        org.junit.Assert.assertTrue(words.contains("world"));
        org.junit.Assert.assertEquals(4, words.size());
    }
    
}
