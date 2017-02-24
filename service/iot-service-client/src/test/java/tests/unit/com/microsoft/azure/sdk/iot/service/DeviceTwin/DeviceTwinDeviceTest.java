/*
 * Copyright (c) Microsoft. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */

package com.microsoft.azure.sdk.iot.service.DeviceTwin;

import com.microsoft.azure.sdk.iot.deps.serializer.Twin;
import mockit.Deencapsulation;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

public class DeviceTwinDeviceTest
{
    /*
    **Tests_SRS_DEVICETWINDEVICE_25_003: [** The constructor shall create a new instance of twin object for this device and store the device id.**]**
     */
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

    /*
    **Tests_SRS_DEVICETWINDEVICE_25_002: [** The constructor shall throw IllegalArgumentException if the input string is empty or null.**]**
     */
    @Test (expected = IllegalArgumentException.class)
    public void constructorThrowsOnNullDeviceID()
    {
        //act
        DeviceTwinDevice testDevice = new DeviceTwinDevice(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void constructorThrowsOnEmptyDeviceID()
    {
        //act
        DeviceTwinDevice testDevice = new DeviceTwinDevice("");
    }

    /*
    **Tests_SRS_DEVICETWINDEVICE_25_004: [** This method shall return the device id **]**
     */
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
    public void getTwinObjectGets()
    {
        //arrange
        DeviceTwinDevice testDevice = new DeviceTwinDevice("testDevice");

        //act
        Twin testDeviceTwinObject = testDevice.getTwinObject();

        //assert
        assertNotNull(testDeviceTwinObject);
    }

    @Test
    public void getTwinObjectUniquePerDevice()
    {
        //arrange
        DeviceTwinDevice testDevice1 = new DeviceTwinDevice("testDevice1");
        DeviceTwinDevice testDevice2 = new DeviceTwinDevice("testDevice2");

        //act
        Twin testDeviceTwinObject1 = testDevice1.getTwinObject();
        Twin testDeviceTwinObject2 = testDevice2.getTwinObject();

        //assert
        assertNotEquals(testDeviceTwinObject1, testDeviceTwinObject2);
    }

    /*
    **Tests_SRS_DEVICETWINDEVICE_25_009: [** This method shall convert the tags map to a set of pairs and return with it. **]**
     */
    @Test
    public void getTagsGets()
    {
        //arrange
        DeviceTwinDevice testDevice = new DeviceTwinDevice("testDevice");
        Set<Pair> testTags = new HashSet<>();
        testTags.add(new Pair("testTag", "tagObject"));
        testDevice.setTags(testTags);

        //act
        Set<Pair> actualTags = testDevice.getTags();

        //assert
        assertEquals(testTags.size(), actualTags.size());
        for(Pair test : actualTags)
        {
            assertTrue(test.getKey().equals("testTag"));
            assertTrue(test.getValue().equals("tagObject"));
        }
    }

    /*
    **Tests_SRS_DEVICETWINDEVICE_25_010: [** If the tags map is null then this method shall return empty set of pairs.**]**
     */
    @Test
    public void getTagsGetsEmptyIfNotPresent()
    {
        //arrange
        DeviceTwinDevice testDevice = new DeviceTwinDevice("testDevice");

        //act
        Set<Pair> actualTags = testDevice.getTags();

        //assert
        assertNotNull(actualTags);
        assertTrue(actualTags.size() == 0);
    }

    /*
    **Tests_SRS_DEVICETWINDEVICE_25_013: [** This method shall convert the desiredProperties map to a set of pairs and return with it. **]**
     */
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
    public void getDesiredReturnsEmptyIfNullMap()
    {
        //arrange
        DeviceTwinDevice testDevice = new DeviceTwinDevice("testDevice");

        //act
        Set<Pair> actualDesProp = testDevice.getDesiredProperties();

        //assert
        assertNotNull(actualDesProp);
        assertTrue(actualDesProp.size() == 0);
    }

    /*
    **Tests_SRS_DEVICETWINDEVICE_25_005: [** This method shall convert the reported properties map to a set of pairs and return with it. **]**
     */
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

    /*
    **Tests_SRS_DEVICETWINDEVICE_25_006: [** If the reported properties map is null then this method shall return empty set of pairs.**]**
     */
    @Test
    public void getReportedPropGetsEmptyIfNotPresent()
    {
        //arrange
        DeviceTwinDevice testDevice = new DeviceTwinDevice("testDevice");

        //act
        Set<Pair> actualRepProp = testDevice.getReportedProperties();

        //assert
        assertNotNull(actualRepProp);
        assertTrue(actualRepProp.size() == 0);
    }

    /*
    **Tests_SRS_DEVICETWINDEVICE_25_007: [** This method shall convert the set of pairs of tags to a map and save it. **]**
     */
    @Test
    public void setTagsSets()
    {
        //arrange
        DeviceTwinDevice testDevice = new DeviceTwinDevice("testDevice");
        Set<Pair> testTags = new HashSet<>();
        testTags.add(new Pair("testTag", "tagObject"));

        //act
        testDevice.setTags(testTags);

        //assert
        Set<Pair> actualTags = testDevice.getTags();
        assertEquals(testTags.size(), actualTags.size());
        for(Pair test : actualTags)
        {
            assertTrue(test.getKey().equals("testTag"));
            assertTrue(test.getValue().equals("tagObject"));
        }

    }

    /*
    **Tests_SRS_DEVICETWINDEVICE_25_008: [** If the tags Set is null then this method shall throw IllegalArgumentException.**]**
     */
    @Test (expected = IllegalArgumentException.class)
    public void setTagsThrowsIsNullInput()
    {
        //arrange
        DeviceTwinDevice testDevice = new DeviceTwinDevice("testDevice");
        Set<Pair> testTags = null;

        //act
        testDevice.setTags(testTags);
    }

    /*
    **Tests_SRS_DEVICETWINDEVICE_25_011: [** This method shall convert the set of pairs of desiredProperties to a map and save it. **]**
     */
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

    /*
    **Tests_SRS_DEVICETWINDEVICE_25_012: [** If the desiredProperties Set is null then this method shall throw IllegalArgumentException.**]**
     */
    @Test (expected = IllegalArgumentException.class)
    public void setDesiredThrowsIfNullInput()
    {
        //arrange
        DeviceTwinDevice testDevice = new DeviceTwinDevice("testDevice");
        Set<Pair> testDesProp = null;


        //act
        testDevice.setDesiredProperties(testDesProp);

    }

    @Test
    public void setReportedPropSets()
    {
        //arrange
        DeviceTwinDevice testDevice = new DeviceTwinDevice("testDevice");
        Map<String, Object> testRepProp = new HashMap<>();
        testRepProp.put("testRep", "repObject");

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

    /*
    **Tests_SRS_DEVICETWINDEVICE_25_025: [** This method shall return the tags map**]**
     */
    @Test
    public void getterGetsMapsTags()
    {
        //arrange
        DeviceTwinDevice testDevice = new DeviceTwinDevice("testDevice");
        Map<String, Object> testTags = new HashMap<>();
        testTags.put("testTag", "tagObject");
        testDevice.setTags(testTags);

        //act
        Map<String, Object> actualTags = testDevice.getTagsMap();

        //assert
        assertEquals(testTags.size(), actualTags.size());

        for(Map.Entry<String, Object> test : actualTags.entrySet())
        {
            assertTrue(test.getKey().equals("testTag"));
            assertTrue(test.getValue().equals("tagObject"));
        }
    }

    /*
    **Tests_SRS_DEVICETWINDEVICE_25_024: [** This method shall save the tags map**]**
     */
    @Test
    public void setterSetsMapsTags()
    {
        //arrange
        DeviceTwinDevice testDevice = new DeviceTwinDevice("testDevice");
        Map<String, Object> testTags = new HashMap<>();
        testTags.put("testTag", "tagObject");

        //act
        testDevice.setTags(testTags);

        //assert
        Map<String, Object> actualTags = testDevice.getTagsMap();
        assertEquals(testTags.size(), actualTags.size());

        for(Map.Entry<String, Object> test : actualTags.entrySet())
        {
            assertTrue(test.getKey().equals("testTag"));
            assertTrue(test.getValue().equals("tagObject"));
        }
    }

    /*
    **Tests_SRS_DEVICETWINDEVICE_25_026: [** This method shall return the reportedProperties map**]**
     */
    @Test
    public void getterGetsMapsRep()
    {
        //arrange
        DeviceTwinDevice testDevice = new DeviceTwinDevice("testDevice");
        Map<String, Object> testRep = new HashMap<>();
        testRep.put("testRep", "repObject");
        testDevice.setReportedProperties(testRep);

        //act
        Map<String, Object> actualTags = testDevice.getReportedMap();

        //assert
        assertEquals(testRep.size(), actualTags.size());

        for(Map.Entry<String, Object> test : actualTags.entrySet())
        {
            assertTrue(test.getKey().equals("testRep"));
            assertTrue(test.getValue().equals("repObject"));
        }
    }

    /*
    **Tests_SRS_DEVICETWINDEVICE_25_022: [** This method shall save the reportedProperties map**]**
     */
    @Test
    public void setterSetsMapsRep()
    {
        //arrange
        DeviceTwinDevice testDevice = new DeviceTwinDevice("testDevice");
        Map<String, Object> testRep = new HashMap<>();
        testRep.put("testRep", "repObject");

        //act
        testDevice.setReportedProperties(testRep);

        //assert
        Map<String, Object> actualTags = testDevice.getReportedMap();

        assertEquals(testRep.size(), actualTags.size());

        for(Map.Entry<String, Object> test : actualTags.entrySet())
        {
            assertTrue(test.getKey().equals("testRep"));
            assertTrue(test.getValue().equals("repObject"));
        }
    }

    /*
    **Tests_SRS_DEVICETWINDEVICE_25_027: [** This method shall return the desiredProperties map**]**
     */
    @Test
    public void getterGetsMapsDes()
    {
        //arrange
        DeviceTwinDevice testDevice = new DeviceTwinDevice("testDevice");
        Map<String, Object> testRep = new HashMap<>();
        testRep.put("testRep", "repObject");
        testDevice.setDesiredProperties(testRep);

        //act
        Map<String, Object> actualTags = testDevice.getDesiredMap();

        //assert
        assertEquals(testRep.size(), actualTags.size());

        for(Map.Entry<String, Object> test : actualTags.entrySet())
        {
            assertTrue(test.getKey().equals("testRep"));
            assertTrue(test.getValue().equals("repObject"));
        }
    }

    /*
    **Tests_SRS_DEVICETWINDEVICE_25_023: [** This method shall save the desiredProperties map**]**
     */
    @Test
    public void setterSetsMapsDes()
    {
        //arrange
        DeviceTwinDevice testDevice = new DeviceTwinDevice("testDevice");
        Map<String, Object> testRep = new HashMap<>();
        testRep.put("testRep", "repObject");

        //act
        testDevice.setDesiredProperties(testRep);

        //assert
        Map<String, Object> actualTags = testDevice.getDesiredMap();

        assertEquals(testRep.size(), actualTags.size());

        for(Map.Entry<String, Object> test : actualTags.entrySet())
        {
            assertTrue(test.getKey().equals("testRep"));
            assertTrue(test.getValue().equals("repObject"));
        }
    }

    /*
    **Tests_SRS_DEVICETWINDEVICE_25_015: [** This method shall append device id, tags, desired and reported properties to string (if present) and return **]**
    */
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
        testDevice.setTags(testTags);

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

    /*
    **Tests_SRS_DEVICETWINDEVICE_25_016: [** This method shall convert the tags map to string (if present) and return **]**
     */
    @Test
    public void tagsToStringReturnsTags()
    {
        //arrange
        DeviceTwinDevice testDevice = new DeviceTwinDevice("testDevice");

        Set<Pair> testTags = new HashSet<>();
        testTags.add(new Pair("testTag1", "tagObject1"));
        testTags.add(new Pair("testTag2", "tagObject2"));
        testDevice.setTags(testTags);

        //act
        String testDeviceString = testDevice.tagsToString();

        //assert
        assertTrue(testDeviceString.contains("testTag1"));
        assertTrue(testDeviceString.contains("tagObject1"));
        assertTrue(testDeviceString.contains("testTag2"));
        assertTrue(testDeviceString.contains("tagObject2"));
    }

    /*
    **Tests_SRS_DEVICETWINDEVICE_25_017: [** This method shall return an empty string if tags map is empty or null and return **]**
     */
    @Test
    public void tagsToStringReturnsEmptyIfTagsEmpty()
    {
        //arrange
        DeviceTwinDevice testDevice = new DeviceTwinDevice("testDevice");

        //act
        String testDeviceString = testDevice.tagsToString();

        //assert
        assertTrue(testDeviceString.length() == 0);
    }

    /*
    **Tests_SRS_DEVICETWINDEVICE_25_018: [** This method shall convert the desiredProperties map to string (if present) and return **]**
     */
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

    /*
    **Tests_SRS_DEVICETWINDEVICE_25_019: [** This method shall return an empty string if desiredProperties map is empty or null and return **]**
     */
    @Test
    public void desToStringReturnsEmptyIfTagsEmpty()
    {
        //arrange
        DeviceTwinDevice testDevice = new DeviceTwinDevice("testDevice");

        //act
        String testDeviceString = testDevice.desiredPropertiesToString();

        //assert
        assertTrue(testDeviceString.length() == 0);
    }

    /*
    **Tests_SRS_DEVICETWINDEVICE_25_020: [** This method shall convert the reportedProperties map to string (if present) and return **]**
     */
    @Test
    public void reportedToStringReturnsReported()
    {
        //arrange
        DeviceTwinDevice testDevice = new DeviceTwinDevice("testDevice");

        Map<String, Object> testRepProp = new HashMap<>();
        testRepProp.put("testRep1", "repObject1");
        testRepProp.put("testRep2", "repObject2");
        testDevice.setReportedProperties(testRepProp);

        //act
        String testDeviceString = testDevice.reportedPropertiesToString();

        //assert
        assertTrue(testDeviceString.contains("testRep1"));
        assertTrue(testDeviceString.contains("repObject1"));
        assertTrue(testDeviceString.contains("testRep2"));
        assertTrue(testDeviceString.contains("repObject2"));

    }

    /*
    **Tests_SRS_DEVICETWINDEVICE_25_021: [** This method shall return an empty string if reportedProperties map is empty or null and return **]**
     */
    @Test
    public void repToStringReturnsEmptyIfTagsEmpty()
    {
        //arrange
        DeviceTwinDevice testDevice = new DeviceTwinDevice("testDevice");

        //act
        String testDeviceString = testDevice.reportedPropertiesToString();

        //assert
        assertTrue(testDeviceString.length() == 0);
    }

}
