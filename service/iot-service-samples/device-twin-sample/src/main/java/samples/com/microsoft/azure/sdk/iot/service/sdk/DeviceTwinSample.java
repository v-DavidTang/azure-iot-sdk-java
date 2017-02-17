/*
 * Copyright (c) Microsoft. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */

package samples.com.microsoft.azure.sdk.iot.service.sdk;

import com.microsoft.azure.sdk.iot.service.DeviceTwin.DeviceTwin;
import com.microsoft.azure.sdk.iot.service.DeviceTwin.DeviceTwinDevice;
import com.microsoft.azure.sdk.iot.service.exceptions.IotHubException;
import com.microsoft.azure.sdk.iot.service.sdk.Device;
import com.microsoft.azure.sdk.iot.service.sdk.RegistryManager;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/** Manages device on IotHub - CRUD operations */
public class DeviceTwinSample
{
    public static final String iotHubConnectionString = "[sample iot hub connection string goes here]";
    public static final String deviceId = "[sample iot hub connection string goes here]";

    /**
     * @param args
     * @throws IOException
     * @throws URISyntaxException
     */
    public static void main(String[] args) throws Exception
    {
        System.out.println("Starting sample...");
        DeviceTwin twinClient = new DeviceTwin(iotHubConnectionString);

        DeviceTwinDevice device = new DeviceTwinDevice(deviceId);

        try
        {
            System.out.println("Getting device twin");
            twinClient.getTwin(device);
            System.out.println("Device : " + device.getDeviceId() + "contains : " + device);

            Map<String, Object> tagsMap = new HashMap<String, Object>();
            tagsMap.put("Building", 101);
            device.setTag(tagsMap);

            twinClient.updateTags(device);
            System.out.println("Device : " + device.getDeviceId() + "contains following tags \n" + device.tagsToString());


            Map<String, Object> desiredProperties = device.getReportedProperties();
        }
        catch (IotHubException e)
        {
            System.out.print(e.getMessage());
        }
        
        System.out.println("Shutting down sample...");
    }
    

}
