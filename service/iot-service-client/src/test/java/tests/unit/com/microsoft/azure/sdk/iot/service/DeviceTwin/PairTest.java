/*
 * Copyright (c) Microsoft. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */

package tests.unit.com.microsoft.azure.sdk.iot.service.DeviceTwin;

import com.microsoft.azure.sdk.iot.service.DeviceTwin.Pair;
import mockit.Deencapsulation;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class PairTest
{

    @Test
    public void constructorCreatesNewPair()
    {
        //act
        Pair testPair = new Pair("TestKey", "TestObject");

        //assert
        assertNotNull(testPair);
        assertTrue(testPair.getKey().equals("TestKey"));
        assertTrue(testPair.getValue().equals("TestObject"));
    }

    @Test (expected = IllegalArgumentException.class)
    public void constructorThrowsIfKeyNull()
    {
        //act
        Pair testPair = new Pair(null, "TestObject");

    }

    @Test (expected = IllegalArgumentException.class)
    public void constructorThrowsIfKeyEmpty()
    {
        //act
        Pair testPair = new Pair("", "TestObject");
    }

    @Test (expected = IllegalArgumentException.class)
    public void constructorThrowsIfKeyIllegalCharacters_1()
    {
        //act
        Pair testPair = new Pair("Key With Space", "TestObject");
    }

    @Test (expected = IllegalArgumentException.class)
    public void constructorThrowsIfKeyIllegalCharacters_2()
    {
        //act
        Pair testPair = new Pair("Key With $", "TestObject");
    }

    @Test (expected = IllegalArgumentException.class)
    public void constructorThrowsIfKeyIllegalCharacters_3()
    {
        //act
        Pair testPair = new Pair("Key With .", "TestObject");
    }

    @Test
    public void getValueGets()
    {
        //arrange
        Pair testPair = new Pair("TestKey", "TestObject");

        //act
        Object value = testPair.getValue();

        //assert
        assertNotNull(testPair);
        assertTrue(value.equals("TestObject"));

    }

    @Test
    public void setValueSets()
    {
        //arrange
        Pair testPair = new Pair("TestKey", "TestObject");

        //act
        testPair.setValue("newTestObject");

        //assert
        Object value = testPair.getValue();
        assertNotNull(testPair);
        assertTrue(value.equals("newTestObject"));

    }

    @Test
    public void getKeyGets()
    {
        //arrange
        Pair testPair = new Pair("TestKey", "TestObject");

        //act
        String key = testPair.getKey();

        //assert
        assertNotNull(testPair);
        assertTrue(key.equals("TestKey"));

    }


}
