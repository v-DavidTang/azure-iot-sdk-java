/*
 * Copyright (c) Microsoft. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */

package com.microsoft.azure.sdk.iot.service.DeviceTwin;

import java.util.Map;

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
        return deviceId;
    }

    public void setTag(Map<String, Object> tag)
    {
        this.tag = tag;
    }

    public Map<String, Object> getTag()
    {
        return tag;
    }

    public Map<String, Object> getDesiredProperties()
    {
        return desiredProperties;
    }

    public void setDesiredProperties(Map<String, Object> desiredProperties)
    {
        this.desiredProperties = desiredProperties;
    }

    public Map<String, Object> getReportedProperties()
    {
        return reportedProperties;
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
        thisDeviceTags.append("Tags:" + this.getTag().toString());
        return thisDeviceTags.toString();

    }

    public String desiredPropertiesToString()
    {
        StringBuilder thisDeviceRepProp = new StringBuilder();
        thisDeviceRepProp.append("Desired Properties: " + this.getDesiredProperties().toString());
        return thisDeviceRepProp.toString();
    }

    public String reportedPropertiesToString()
    {
        StringBuilder thisDeviceDesProp = new StringBuilder();
        thisDeviceDesProp.append("Reported Properties" + this.getReportedProperties().toString());
        return thisDeviceDesProp.toString();
    }

}
