/*
 * Copyright (c) Microsoft. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */

package com.microsoft.azure.sdk.iot.service.DeviceTwin;

public class Pair
{
    private String key;
    private Object value;

    public Pair(String key, Object value) throws IllegalArgumentException
    {
        if (key == null || key.isEmpty())
        {

            /*
            **Codes_SRS_Property_25_002: [**If the key is null or empty, the constructor shall throw an IllegalArgumentException.**]
             */
            throw new IllegalArgumentException("Key cannot be null or empty");
        }
        if (key.contains(" ") || key.contains("$") || key.contains("."))
        {
            /*
            **Codes_SRS_Property_25_006: [**If the key contains illegal unicode control characters i.e ' ', '.', '$', the constructor shall throw an IllegalArgumentException.**]**
             */
            throw new IllegalArgumentException("Key cannot contain illegal unicode control characters '.', '$', ' '");
        }
        /*
        **Codes_SRS_Property_25_001: [**The constructor shall save the key and value representing this property.**]**
         */
        this.key = key;
        this.value = value;
    }

    public Object getValue()
    {
        return value;
    }

    public String getKey()
    {
        return key;
    }

    public Object setValue(Object value)
    {
        Object oldValue = this.value;

        this.value = value;

        return oldValue;
    }
}
