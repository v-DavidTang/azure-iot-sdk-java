/*
 * Copyright (c) Microsoft. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */

package com.microsoft.azure.sdk.iot.service.DeviceTwin;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DeviceTwinDevice
{
    private String deviceId;
    private Map<String, Object> tag = null;
    private Map<String, Object> reportedProperties = null;
    private Map<String, Object> desiredProperties = null;

    public DeviceTwinDevice(String deviceId)
    {
        this.deviceId = deviceId;
    }

    public String getDeviceId()
    {
        return this.deviceId;
    }


    public void setTag(Set<Pair> tags)
    {
        this.tag = this.setToMap(tags);
    }

    public Set<Pair> getTag()
    {
        return this.mapToSet(this.tag);
    }


    public Set<Pair> getDesiredProperties()
    {
        return this.mapToSet(this.desiredProperties);
    }

    public void setDesiredProperties(Set<Pair> desiredProperties)
    {
        this.desiredProperties = this.setToMap(desiredProperties);
    }


    public Set<Pair> getReportedProperties()
    {
        return this.mapToSet(this.reportedProperties);
    }

    protected void setReportedProperties(Set<Pair> reportedProperties)
    {
        this.reportedProperties = this.setToMap(reportedProperties);
    }

    public String toString()
    {
        StringBuilder thisDevice = new StringBuilder();

        thisDevice.append("Device ID: " + this.getDeviceId() + "\n");
        thisDevice.append(tagsToString());
        thisDevice.append(reportedPropertiesToString());
        thisDevice.append(desiredPropertiesToString());

        return thisDevice.toString();
    }

    public String tagsToString()
    {
        StringBuilder thisDeviceTags = new StringBuilder();
        if (tag != null)
        {
            thisDeviceTags.append("Tags:" + this.tag.toString() + "\n");
        }
        return thisDeviceTags.toString();

    }

    public String desiredPropertiesToString()
    {
        StringBuilder thisDeviceRepProp = new StringBuilder();
        if (this.desiredProperties != null)
        {
            thisDeviceRepProp.append("Desired Properties: " + this.desiredProperties.toString() + "\n");
        }
        return thisDeviceRepProp.toString();
    }

    public String reportedPropertiesToString()
    {
        StringBuilder thisDeviceDesProp = new StringBuilder();
        if (this.reportedProperties != null)
        {
            thisDeviceDesProp.append("Reported Properties" + this.reportedProperties.toString() + "\n");
        }
        return thisDeviceDesProp.toString();
    }

    protected Set<Pair> mapToSet(Map<String, Object> map)
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

    protected Map<String, Object> setToMap(Set<Pair> set)
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
