/*
 * Copyright (c) Microsoft. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */

package com.microsoft.azure.sdk.iot.service.DeviceTwin;

import com.microsoft.azure.sdk.iot.service.exceptions.IotHubException;
import com.microsoft.azure.sdk.iot.service.sdk.IotHubConnectionString;


public class DeviceTwin
{
    private IotHubConnectionString connectionString;

    public DeviceTwin(String connectionString)
    {

    }

    private void createDeviceTwinHeader()
    {

    }

    public void getTwin(DeviceTwinDevice device) throws IotHubException
    {
        /*
            1. Call get twin on HTTP
            2. Use the HTTP payload and call updateTwin(String)
            3. getTags, getDesired, getReported on serializer and set it on device
            4. If any of the callbacks are set on this device call

        */
    }

    public void updateTags(DeviceTwinDevice device) throws IotHubException
    {
        /*
            1. Convert to map
            2. Serialize by calling updateTags
            3. Use the string as payload and send it via http
         */
    }

    public void updateDesiredProperties(DeviceTwinDevice device) throws IotHubException
    {

    }

    public void replaceDesired(DeviceTwinDevice device) throws IotHubException
    {

    }

    public void replaceTags(DeviceTwinDevice device) throws IotHubException
    {

    }

}
