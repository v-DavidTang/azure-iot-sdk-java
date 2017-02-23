/*
 * Copyright (c) Microsoft. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */

package com.microsoft.azure.sdk.iot.service.DeviceTwin;

import com.microsoft.azure.sdk.iot.deps.serializer.Twin;
import com.microsoft.azure.sdk.iot.service.auth.IotHubServiceSasToken;
import com.microsoft.azure.sdk.iot.service.exceptions.IotHubException;
import com.microsoft.azure.sdk.iot.service.exceptions.IotHubExceptionManager;
import com.microsoft.azure.sdk.iot.service.sdk.IotHubConnectionString;
import com.microsoft.azure.sdk.iot.service.sdk.IotHubConnectionStringBuilder;
import com.microsoft.azure.sdk.iot.service.sdk.RegistryManager;
import com.microsoft.azure.sdk.iot.service.transport.TransportUtils;
import com.microsoft.azure.sdk.iot.service.transport.http.HttpMethod;
import com.microsoft.azure.sdk.iot.service.transport.http.HttpRequest;
import com.microsoft.azure.sdk.iot.service.transport.http.HttpResponse;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;


public class DeviceTwin
{
    private IotHubConnectionString iotHubConnectionString = null;
    private final Integer DEFAULT_HTTP_TIMOUT_MS = 24000;
    private Integer requestId = 0;
    private Twin twinObject = new Twin();

    public DeviceTwin(RegistryManager registryManager)
    {
        if (registryManager == null)
        {
            throw new IllegalArgumentException("Registry manager cannot be null");
        }
        else
        {
            //registryManager.getConnectionString();
        }

    }

    public DeviceTwin()
    {

    }

    public static DeviceTwin createFromConnectionString(String connectionString) throws Exception
    {
        if (connectionString == null || connectionString.length() == 0)
        {
            throw new IllegalArgumentException("Connection string cannot be null or empty");
        }

        DeviceTwin deviceTwin = new DeviceTwin();
        deviceTwin.iotHubConnectionString = IotHubConnectionStringBuilder.createConnectionString(connectionString);
        return deviceTwin;
    }

    private HttpRequest createTwinRequest(URL url, HttpMethod method, byte[] payload, String sasToken, String requestId) throws IOException
    {
        HttpRequest request = new HttpRequest(url, method, payload);
        request.setReadTimeoutMillis(DEFAULT_HTTP_TIMOUT_MS);
        request.setHeaderField("authorization", sasToken);
        request.setHeaderField("Request-Id", requestId);
        request.setHeaderField("User-Agent", TransportUtils.getJavaServiceClientIdentifier() + "/" + TransportUtils.getServiceVersion());
        request.setHeaderField("Accept", "application/json");
        request.setHeaderField("Content-Type", "application/json");
        request.setHeaderField("charset", "utf-8");
        request.setHeaderField("If-Match", "'*'");
        return request;
    }

    public void getTwin(DeviceTwinDevice device) throws IotHubException, IOException
    {
        /*
            1. Call get twin on HTTP
            2. Use the HTTP payload and call updateTwin(String)
            3. getTags, getDesired, getReported on serializer and set it on device
            4. If any of the callbacks are set on this device call
        */

        if (device == null)
        {
            throw new IllegalArgumentException("Instantiate a device and set device id to be used");
        }

        URL url = this.iotHubConnectionString.getUrlTwin(device.getDeviceId());

        String sasTokenString = new IotHubServiceSasToken(this.iotHubConnectionString).toString();

        HttpRequest request = createTwinRequest(url, HttpMethod.GET, new byte[0], sasTokenString, String.valueOf(requestId++));

        HttpResponse response = request.send();

        IotHubExceptionManager.httpResponseVerification(response);

        // Codes_SRS_SERVICE_SDK_JAVA_REGISTRYMANAGER_12_011: [The function shall create a new Device object from the response and return with it]
        String twin = new String(response.getBody(), StandardCharsets.UTF_8);

        twinObject.updateTwin(twin);

        //device.setTag(twinObject.getTags());
        device.setDesiredProperties(device.mapToSet(twinObject.getDesiredPropertyMap()));
        device.setReportedProperties(device.mapToSet(twinObject.getReportedPropertyMap()));

    }

    public void updateTwin(DeviceTwinDevice device) throws IotHubException, IOException
    {
        /*
            1. Convert to map
            2. Serialize by calling updateTags
            3. Use the string as payload and send it via http
         */
        if (device == null)
        {
            throw new IllegalArgumentException("Instantiate a device and set device id to be used");
        }

        URL url = this.iotHubConnectionString.getUrlTwin(device.getDeviceId());

        String sasTokenString = new IotHubServiceSasToken(this.iotHubConnectionString).toString();

        String tags = null;//twinObject.updateTwin(device.getTag(), device.getDesiredProperties(), null);

        if (tags == null)
        {
            return;
        }

        HttpRequest request = createTwinRequest(url, HttpMethod.PATCH, tags.getBytes(), sasTokenString, String.valueOf(requestId++));

        HttpResponse response = request.send();

        IotHubExceptionManager.httpResponseVerification(response);


    }

    public void updateDesiredProperties(DeviceTwinDevice device) throws IotHubException, IOException
    {
        if (device == null)
        {
            throw new IllegalArgumentException("Instantiate a device and set device id to be used");
        }

        URL url = this.iotHubConnectionString.getUrlTwinDesired(device.getDeviceId());

        String sasTokenString = new IotHubServiceSasToken(this.iotHubConnectionString).toString();

        String tags = twinObject.updateDesiredProperty(device.setToMap(device.getDesiredProperties()));

        if (tags == null)
        {
            return;
        }

        HttpRequest request = createTwinRequest(url, HttpMethod.PATCH, tags.getBytes(), sasTokenString, String.valueOf(requestId++));

        HttpResponse response = request.send();

        IotHubExceptionManager.httpResponseVerification(response);

    }

    public void replaceDesired(DeviceTwinDevice device) throws IotHubException, IOException
    {
        if (device == null)
        {
            throw new IllegalArgumentException("Instantiate a device and set device id to be used");
        }

        URL url = this.iotHubConnectionString.getUrlTwinDesired(device.getDeviceId());

        String sasTokenString = new IotHubServiceSasToken(this.iotHubConnectionString).toString();

        String tags = twinObject.updateDesiredProperty(device.setToMap(device.getDesiredProperties())); // change to replace

        if (tags == null)
        {
            return;
        }

        HttpRequest request = createTwinRequest(url, HttpMethod.PUT, tags.getBytes(), sasTokenString, String.valueOf(requestId++));

        HttpResponse response = request.send();

        IotHubExceptionManager.httpResponseVerification(response);

    }

    public void replaceTags(DeviceTwinDevice device) throws IotHubException, IOException
    {
        if (device == null)
        {
            throw new IllegalArgumentException("Instantiate a device and set device id to be used");
        }

        URL url = this.iotHubConnectionString.getUrlTwinTags(device.getDeviceId());

        String sasTokenString = new IotHubServiceSasToken(this.iotHubConnectionString).toString();

        String tags = null ;//= twinObject.replaceTags(device.getTag());

        if (tags == null)
        {
            return;
        }

        HttpRequest request = createTwinRequest(url, HttpMethod.PUT, tags.getBytes(), sasTokenString, String.valueOf(requestId++));

        HttpResponse response = request.send();

        IotHubExceptionManager.httpResponseVerification(response);

    }

}
