/*
 * Copyright (c) Microsoft. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */

package com.microsoft.azure.sdk.iot.service.DeviceTwin;

import com.microsoft.azure.sdk.iot.deps.serializer.Twin;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DeviceTwinDevice
{
    /**
     *Codes_SRS_DEVICETWINDEVICE_25_001: [** The DeviceTwinDevice class has the following properties: deviceId, a container for tags, desired and reported properties, and a twin object. **]**
     */
    private String deviceId;
    private Map<String, Object> tag = null;
    private Map<String, Object> reportedProperties = null;
    private Map<String, Object> desiredProperties = null;
    private Twin twinObject = null;

    public DeviceTwinDevice(String deviceId) throws IllegalArgumentException
    {
        if (deviceId == null || deviceId.length() == 0)
        {
            /*
            **Codes_SRS_DEVICETWINDEVICE_25_002: [** The constructor shall throw IllegalArgumentException if the input string is empty or null.**]**
             */
            throw new IllegalArgumentException("Device ID cannot be null or empty");
        }
        /*
        **Codes_SRS_DEVICETWINDEVICE_25_003: [** The constructor shall create a new instance of twin object for this device and store the device id.**]**
         */
        this.deviceId = deviceId;
        this.twinObject = new Twin();
        this.twinObject.enableTags();
    }

    public String getDeviceId()
    {
        /*
        **Codes_SRS_DEVICETWINDEVICE_25_004: [** This method shall return the device id **]**
         */
        return this.deviceId;
    }


    public void setTags(Set<Pair> tags) throws IllegalArgumentException
    {
        if (tags == null)
        {
            /*
            **Codes_SRS_DEVICETWINDEVICE_25_008: [** If the tags Set is null then this method shall throw IllegalArgumentException.**]**
             */
            throw new IllegalArgumentException("tags cannot be null");
        }
        /*
        **Codes_SRS_DEVICETWINDEVICE_25_007: [** This method shall convert the set of pairs of tags to a map and save it. **]**
         */
        this.tag = this.setToMap(tags);
    }

    public Set<Pair> getTags()
    {
        /*
        **Codes_SRS_DEVICETWINDEVICE_25_009: [** This method shall convert the tags map to a set of pairs and return with it. **]**
        **Codes_SRS_DEVICETWINDEVICE_25_010: [** If the tags map is null then this method shall return empty set of pairs.**]**
         */
        return this.mapToSet(this.tag);
    }


    public Set<Pair> getDesiredProperties()
    {
        /*
        **Codes_SRS_DEVICETWINDEVICE_25_013: [** This method shall convert the desiredProperties map to a set of pairs and return with it. **]**
        **Codes_SRS_DEVICETWINDEVICE_25_014: [** If the desiredProperties map is null then this method shall return empty set of pairs.**]**
         */
        return this.mapToSet(this.desiredProperties);
    }

    public void setDesiredProperties(Set<Pair> desiredProperties) throws IllegalArgumentException
    {
        if (desiredProperties == null)
        {
            /*
            **Codes_SRS_DEVICETWINDEVICE_25_012: [** If the desiredProperties Set is null then this method shall throw IllegalArgumentException.**]**
             */
            throw new IllegalArgumentException("desiredProperties cannot be null");
        }
        /*
        **Codes_SRS_DEVICETWINDEVICE_25_011: [** This method shall convert the set of pairs of desiredProperties to a map and save it. **]**
         */
        this.desiredProperties = this.setToMap(desiredProperties);
    }


    public Set<Pair> getReportedProperties()
    {
        /*
        **Codes_SRS_DEVICETWINDEVICE_25_005: [** This method shall convert the reported properties map to a set of pairs and return with it. **]**
         */
        /*
        **Codes_SRS_DEVICETWINDEVICE_25_006: [** If the reported properties map is null then this method shall return empty set of pairs.**]**
         */
        return this.mapToSet(this.reportedProperties);
    }


    protected void setReportedProperties(Map<String, Object> reportedProperties)
    {
        /*
        **Codes_SRS_DEVICETWINDEVICE_25_022: [** This method shall save the reportedProperties map**]**
         */
        this.reportedProperties = reportedProperties;
    }

    protected void setDesiredProperties(Map<String, Object> desiredProperties)
    {
        /*
        **Codes_SRS_DEVICETWINDEVICE_25_023: [** This method shall save the desiredProperties map**]**
         */
        this.desiredProperties = desiredProperties;
    }

    protected void setTags(Map<String, Object> tag)
    {
        /*
        **Codes_SRS_DEVICETWINDEVICE_25_024: [** This method shall save the tags map**]**
         */
        this.tag = tag;
    }

    protected Map<String, Object> getTagsMap()
    {
        /*
        **Codes_SRS_DEVICETWINDEVICE_25_025: [** This method shall return the tags map**]**
         */
        return this.tag;
    }

    protected Map<String, Object> getDesiredMap()
    {
        /*
        **Codes_SRS_DEVICETWINDEVICE_25_027: [** This method shall return the desiredProperties map**]**
         */
        return this.desiredProperties;
    }

    protected Map<String, Object> getReportedMap()
    {
        /*
        **Codes_SRS_DEVICETWINDEVICE_25_026: [** This method shall return the reportedProperties map**]**
         */
        return this.reportedProperties;
    }

    protected Twin getTwinObject()
    {
        /*
        **Codes_SRS_DEVICETWINDEVICE_25_028: [** This method shall return the twinObject for this device**]**
         */
        return twinObject;
    }

    public String toString()
    {
        /*
        **Codes_SRS_DEVICETWINDEVICE_25_015: [** This method shall append device id, tags, desired and reported properties to string (if present) and return **]**
         */
        StringBuilder thisDevice = new StringBuilder();

        thisDevice.append("Device ID: " + this.getDeviceId() + "\n");
        thisDevice.append(tagsToString());
        thisDevice.append(reportedPropertiesToString());
        thisDevice.append(desiredPropertiesToString());

        return thisDevice.toString();
    }

    public String tagsToString()
    {
        /*
        **Codes_SRS_DEVICETWINDEVICE_25_016: [** This method shall convert the tags map to string (if present) and return **]**
         */
        StringBuilder thisDeviceTags = new StringBuilder();
        if (tag != null)
        {
            thisDeviceTags.append("Tags:" + this.tag.toString() + "\n");
        }
        /*
        **Codes_SRS_DEVICETWINDEVICE_25_017: [** This method shall return an empty string if tags map is empty or null and return **]**
         */
        return thisDeviceTags.toString();

    }

    public String desiredPropertiesToString()
    {
        StringBuilder thisDeviceRepProp = new StringBuilder();
        if (this.desiredProperties != null)
        {
            /*
            **Codes_SRS_DEVICETWINDEVICE_25_018: [** This method shall convert the desiredProperties map to string (if present) and return **]**
             */
            thisDeviceRepProp.append("Desired Properties: " + this.desiredProperties.toString() + "\n");
        }
        /*
        **Codes_SRS_DEVICETWINDEVICE_25_019: [** This method shall return an empty string if desiredProperties map is empty or null and return **]**
         */
        return thisDeviceRepProp.toString();
    }

    public String reportedPropertiesToString()
    {
        StringBuilder thisDeviceDesProp = new StringBuilder();
        if (this.reportedProperties != null)
        {
            /*
            **Codes_SRS_DEVICETWINDEVICE_25_020: [** This method shall convert the reportedProperties map to string (if present) and return **]**
             */
            thisDeviceDesProp.append("Reported Properties" + this.reportedProperties.toString() + "\n");
        }
        /*
        **Codes_SRS_DEVICETWINDEVICE_25_021: [** This method shall return an empty string if reportedProperties map is empty or null and return **]**
         */
        return thisDeviceDesProp.toString();
    }

    private Set<Pair> mapToSet(Map<String, Object> map)
    {
        Set<Pair> setPair = new HashSet<>();

        if (map != null)
        {
            for (Map.Entry<String, Object> setEntry : map.entrySet())
            {
                setPair.add(new Pair(setEntry.getKey(), setEntry.getValue()));
            }
        }

        return setPair;

    }

    private Map<String, Object> setToMap(Set<Pair> set)
    {
        Map<String, Object> map = new HashMap<>();

        if (set != null)
        {
            for (Pair p : set)
            {
                map.put(p.getKey(), p.getValue());
            }
        }

        return map;
    }
}
