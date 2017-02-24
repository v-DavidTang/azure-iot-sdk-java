/*
 * Copyright (c) Microsoft. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */

package samples.com.microsoft.azure.sdk.iot.service.sdk;

import com.microsoft.azure.sdk.iot.service.DeviceTwin.DeviceTwin;
import com.microsoft.azure.sdk.iot.service.DeviceTwin.DeviceTwinDevice;
import com.microsoft.azure.sdk.iot.service.DeviceTwin.Pair;
import com.microsoft.azure.sdk.iot.service.exceptions.IotHubException;
import com.microsoft.azure.sdk.iot.service.sdk.Device;
import com.microsoft.azure.sdk.iot.service.sdk.RegistryManager;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/** Manages device on IotHub - CRUD operations */
public class DeviceTwinSample
{
    public static final String iotHubConnectionString = "HostName=aziot-prmathur.azure-devices.net;SharedAccessKeyName=iothubowner;SharedAccessKey=m81kcZh4/3bjZyla+ChvFHxeezVgiyYK9iguWWAK7jg=";
    public static final String deviceId = "homeKit";

    /**
     * @param args
     * @throws IOException
     * @throws URISyntaxException
     */
    public static void main(String[] args) throws Exception
    {
        System.out.println("Starting sample...");
        DeviceTwin twinClient = DeviceTwin.createFromConnectionString(iotHubConnectionString);

        DeviceTwinDevice device = new DeviceTwinDevice(deviceId);

        try
        {
            // Manage complete twin
            System.out.println("Getting device twin");
            twinClient.getTwin(device);
            System.out.println("Device : " + device.getDeviceId() + "contains : " + device);

            Set<Pair> tagsSet = new HashSet<Pair>();
            tagsSet.add(new Pair("Building", 101));
            device.setTags(tagsSet);

            twinClient.updateTwin(device);
            System.out.println("Device : " + device.getDeviceId() + "contains following tags \n" + device.tagsToString());


            // Manage desired properties
            Set<Pair> desiredSet = new HashSet<Pair>();
            desiredSet.add(new Pair("Temp(F)", 65));
            desiredSet.add(new Pair("Pressure", 165));
            device.setDesiredProperties(desiredSet);

            twinClient.updateDesiredProperties(device);

            Set<Pair> desiredProperties = device.getReportedProperties();

        }
        catch (IotHubException e)
        {
            System.out.println(e.getMessage());
        }
        
        System.out.println("Shutting down sample...");
    }
    

}
