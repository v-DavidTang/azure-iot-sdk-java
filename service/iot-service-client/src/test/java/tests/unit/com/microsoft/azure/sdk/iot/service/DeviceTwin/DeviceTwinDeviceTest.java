/*
 * Copyright (c) Microsoft. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */

package com.microsoft.azure.sdk.iot.service.DeviceTwin;

import mockit.Deencapsulation;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

public class DeviceTwinDeviceTest
{
    @Test
    public void constructorCreatesNewDevice()
    {
        //act
        DeviceTwinDevice testDevice = new DeviceTwinDevice("testDevice");

        //assert
        assertNotNull(testDevice);
        Map<String, Object> tagsMap = Deencapsulation.getField(testDevice, "tag");
        assertNull(tagsMap);
        Map<String, Object> repPropMap = Deencapsulation.getField(testDevice, "reportedProperties");
        assertNull(repPropMap);
        Map<String, Object> desPropMap = Deencapsulation.getField(testDevice, "reportedProperties");
        assertNull(desPropMap);
    }

    @Test
    public void getDeviceIdGets()
    {
        //arrange
        DeviceTwinDevice testDevice = new DeviceTwinDevice("testDevice");

        //act
        String devId = testDevice.getDeviceId();

        //assert
        assertTrue(devId.equals("testDevice"));

    }

    @Test
    public void getTagGets()
    {
        //arrange
        DeviceTwinDevice testDevice = new DeviceTwinDevice("testDevice");
        Set<Pair> testTags = new HashSet<>();
        testTags.add(new Pair("testTag", "tagObject"));
        testDevice.setTag(testTags);

        //act
        Set<Pair> actualTags = testDevice.getTag();

        //assert
        assertEquals(testTags.size(), actualTags.size());
        for(Pair test : actualTags)
        {
            assertTrue(test.getKey().equals("testTag"));
            assertTrue(test.getValue().equals("tagObject"));
        }
    }

    @Test
    public void getDesiredPropGets()
    {
        //arrange
        DeviceTwinDevice testDevice = new DeviceTwinDevice("testDevice");
        Set<Pair> testDesProp = new HashSet<>();
        testDesProp.add(new Pair("testDes", "desObject"));
        testDevice.setDesiredProperties(testDesProp);

        //act
        Set<Pair> actualDesProp = testDevice.getDesiredProperties();

        //assert
        assertEquals(testDesProp.size(), actualDesProp.size());
        for(Pair test : actualDesProp)
        {
            assertTrue(test.getKey().equals("testDes"));
            assertTrue(test.getValue().equals("desObject"));
        }
    }

    @Test
    public void getReportedPropGets()
    {
        //arrange
        DeviceTwinDevice testDevice = new DeviceTwinDevice("testDevice");

        Map<String, Object> repMap = new HashMap<>();
        repMap.put("testRep", "repObject");
        Deencapsulation.setField(testDevice, "reportedProperties", repMap);

        //act
        Set<Pair> actualRepProp = testDevice.getReportedProperties();

        //assert
        assertEquals(repMap.size(), actualRepProp.size());
        for(Pair test : actualRepProp)
        {
            assertTrue(test.getKey().equals("testRep"));
            assertTrue(test.getValue().equals("repObject"));
        }
    }

    @Test
    public void setTagSets()
    {
        //arrange
        DeviceTwinDevice testDevice = new DeviceTwinDevice("testDevice");
        Set<Pair> testTags = new HashSet<>();
        testTags.add(new Pair("testTag", "tagObject"));

        //act
        testDevice.setTag(testTags);

        //assert
        Set<Pair> actualTags = testDevice.getTag();
        assertEquals(testTags.size(), actualTags.size());
        for(Pair test : actualTags)
        {
            assertTrue(test.getKey().equals("testTag"));
            assertTrue(test.getValue().equals("tagObject"));
        }

    }

    @Test
    public void setDesiredPropSets()
    {
        //arrange
        DeviceTwinDevice testDevice = new DeviceTwinDevice("testDevice");
        Set<Pair> testDesProp = new HashSet<>();
        testDesProp.add(new Pair("testDes", "desObject"));

        //act
        testDevice.setDesiredProperties(testDesProp);

        //assert
        Set<Pair> actualDesProp = testDevice.getDesiredProperties();
        assertEquals(testDesProp.size(), actualDesProp.size());
        for(Pair test : actualDesProp)
        {
            assertTrue(test.getKey().equals("testDes"));
            assertTrue(test.getValue().equals("desObject"));
        }

    }

    @Test
    public void setReportedPropSets()
    {
        //arrange
        DeviceTwinDevice testDevice = new DeviceTwinDevice("testDevice");
        Set<Pair> testRepProp = new HashSet<>();
        testRepProp.add(new Pair("testRep", "repObject"));

        //act
        testDevice.setReportedProperties(testRepProp);

        //assert
        Set<Pair> actualRepProp = testDevice.getReportedProperties();
        assertEquals(testRepProp.size(), actualRepProp.size());
        for(Pair test : actualRepProp)
        {
            assertTrue(test.getKey().equals("testRep"));
            assertTrue(test.getValue().equals("repObject"));
        }

    }

    @Test
    public void settersAlwaysCreatesNewMaps()
    {
        //arrange
        DeviceTwinDevice testDevice = new DeviceTwinDevice("testDevice");
        Set<Pair> testDesProp = new HashSet<>();
        testDesProp.add(new Pair("testDes", "desObject"));

        //act
        testDevice.setDesiredProperties(testDesProp);

        //assert
        Set<Pair> actualDesProp = testDevice.getDesiredProperties();
        assertEquals(testDesProp.size(), actualDesProp.size());
        assertNotEquals(testDesProp, actualDesProp);

    }

    @Test
    public void gettersAlwaysCreatesNewSets()
    {
        //arrange
        DeviceTwinDevice testDevice = new DeviceTwinDevice("testDevice");
        Set<Pair> testDesProp = new HashSet<>();
        testDesProp.add(new Pair("testDes", "desObject"));
        testDevice.setDesiredProperties(testDesProp);

        //act
        Set<Pair> actualDesProp = testDevice.getDesiredProperties();

        //assert
        assertEquals(testDesProp.size(), actualDesProp.size());
        assertNotEquals(testDesProp, actualDesProp);

    }

    @Test
    public void toStringReturnsAll()
    {
        //arrange
        DeviceTwinDevice testDevice = new DeviceTwinDevice("testDevice");

        Set<Pair> testDesProp = new HashSet<>();
        testDesProp.add(new Pair("testDes1", "desObject1"));
        testDesProp.add(new Pair("testDes2", "desObject2"));
        testDevice.setDesiredProperties(testDesProp);

        Set<Pair> testTags = new HashSet<>();
        testTags.add(new Pair("testTag1", "tagObject1"));
        testTags.add(new Pair("testTag2", "tagObject2"));
        testDevice.setTag(testTags);

        //act
        String testDeviceString = testDevice.toString();

        //assert
        assertTrue(testDeviceString.contains("testDes1"));
        assertTrue(testDeviceString.contains("desObject1"));
        assertTrue(testDeviceString.contains("testDes2"));
        assertTrue(testDeviceString.contains("desObject2"));
        assertTrue(testDeviceString.contains("testTag1"));
        assertTrue(testDeviceString.contains("tagObject1"));
        assertTrue(testDeviceString.contains("testTag2"));
        assertTrue(testDeviceString.contains("tagObject2"));
    }

    @Test
    public void tagsToStringReturnsTags()
    {
        //arrange
        DeviceTwinDevice testDevice = new DeviceTwinDevice("testDevice");

        Set<Pair> testTags = new HashSet<>();
        testTags.add(new Pair("testTag1", "tagObject1"));
        testTags.add(new Pair("testTag2", "tagObject2"));
        testDevice.setTag(testTags);

        //act
        String testDeviceString = testDevice.tagsToString();

        //assert
        assertTrue(testDeviceString.contains("testTag1"));
        assertTrue(testDeviceString.contains("tagObject1"));
        assertTrue(testDeviceString.contains("testTag2"));
        assertTrue(testDeviceString.contains("tagObject2"));
    }

    @Test
    public void desiredToStringReturnsDesired()
    {
        //arrange
        DeviceTwinDevice testDevice = new DeviceTwinDevice("testDevice");

        Set<Pair> testDesProp = new HashSet<>();
        testDesProp.add(new Pair("testDes1", "desObject1"));
        testDesProp.add(new Pair("testDes2", "desObject2"));
        testDevice.setDesiredProperties(testDesProp);

        //act
        String testDeviceString = testDevice.desiredPropertiesToString();

        //assert
        assertTrue(testDeviceString.contains("testDes1"));
        assertTrue(testDeviceString.contains("desObject1"));
        assertTrue(testDeviceString.contains("testDes2"));
        assertTrue(testDeviceString.contains("desObject2"));

    }

    @Test
    public void reportedToStringReturnsReported()
    {
        //arrange
        DeviceTwinDevice testDevice = new DeviceTwinDevice("testDevice");

        Set<Pair> testRepProp = new HashSet<>();
        testRepProp.add(new Pair("testRep1", "repObject1"));
        testRepProp.add(new Pair("testRep2", "repObject2"));
        testDevice.setReportedProperties(testRepProp);

        //act
        String testDeviceString = testDevice.reportedPropertiesToString();

        //assert
        assertTrue(testDeviceString.contains("testRep1"));
        assertTrue(testDeviceString.contains("repObject1"));
        assertTrue(testDeviceString.contains("testRep2"));
        assertTrue(testDeviceString.contains("repObject2"));

    }

    @Test
    public void mapToSetCreatesNewSet()
    {
        //arrange
        DeviceTwinDevice testDevice = new DeviceTwinDevice("testDevice");
        Map<String, Object> testMap = new HashMap<>();
        testMap.put("Key1", "Object1");
        testMap.put("Key2", "Object2");

        //act
        Set<Pair> testSet = testDevice.mapToSet(testMap);

        assertNotNull(testSet);
    }

    @Test
    public void mapToSetCopiesAllPairsToNewSet()
    {
        //arrange
        DeviceTwinDevice testDevice = new DeviceTwinDevice("testDevice");
        Map<String, Object> testMap = new HashMap<>();
        testMap.put("Key1", "Object1");
        testMap.put("Key2", "Object2");

        //act
        Set<Pair> testSet = testDevice.mapToSet(testMap);

        //assert
        assertNotNull(testSet);
        for (Pair p : testSet)
        {
            if (p.getKey().equals("Key1"))
                 assertEquals(p.getValue(), "Object1");
            if (p.getKey().equals("Key2"))
                assertEquals(p.getValue(), "Object2");
        }

    }

    @Test
    public void setToMapCreatesNewMap()
    {
        //arrange
        DeviceTwinDevice testDevice = new DeviceTwinDevice("testDevice");
        Set<Pair> testSet = new HashSet<>();
        testSet.add(new Pair("Key1", "Object1"));
        testSet.add(new Pair("Key2", "Object2"));

        //act
        Map<String, Object> testMap = testDevice.setToMap(testSet);

        assertNotNull(testMap);

    }

    @Test
    public void setToMapCopiesAllPairsToNewMap()
    {
        //arrange
        DeviceTwinDevice testDevice = new DeviceTwinDevice("testDevice");
        Set<Pair> testSet = new HashSet<>();
        testSet.add(new Pair("Key1", "Object1"));
        testSet.add(new Pair("Key2", "Object2"));

        //act
        Map<String, Object> testMap = testDevice.setToMap(testSet);

        assertNotNull(testMap);
    }
}
