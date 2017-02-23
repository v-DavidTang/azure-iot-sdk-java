// Copyright (c) Microsoft. All rights reserved.
// Licensed under the MIT license. See LICENSE file in the project root for full license information.

package com.microsoft.azure.sdk.iot.deps.serializer;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 * INNER TWIN CLASS
 *
 * Twin management representation
 *
 * This class is part of the Twin. It contains the Device identity management.
 */
public class TwinManager
{
    /**
     * Device name
     * A case-sensitive string (up to 128 char long)
     * of ASCII 7-bit alphanumeric chars
     * + {'-', ':', '.', '+', '%', '_', '#', '*', '?', '!', '(', ')', ',', '=', '@', ';', '$', '''}.
     */
    @SerializedName("deviceId")
    protected String deviceId = null;

    /**
     * Device generation Id
     */
    @SerializedName("generationId")
    protected String generationId = null;

    /**
     * A string representing a weak ETAG version
     * of this JSON description. This is a hash.
     */
    @SerializedName("etag")
    protected String eTag = null;

    /**
     * "Enabled", "Disabled".
     * If "enabled", this device is authorized to connect.
     * If "disabled" this device cannot receive or send messages, and statusReason must be set.
     */
    @SerializedName("status")
    protected TwinStatus status = null;

    /**
     * A 128 char long string storing the reason of suspension.
     * (all UTF-8 chars allowed).
     */
    @SerializedName("statusReason")
    protected String statusReason = null;

    /**
     * Datetime of last time the state was updated.
     */
    @SerializedName("statusUpdatedTime")
    protected String statusUpdatedTime = null;

    /**
     * Status of the device:
     * {"connected" | "disconnected"}
     */
    @SerializedName("connectionState")
    protected TwinConnectionState connectionState = null;

    /**
     * Datetime of last time the connection state was updated.
     */
    @SerializedName("connectionStateUpdatedTime")
    protected String connectionStateUpdatedTime = null;

    /**
     * Datetime of last time the device authenticated, received, or sent a message.
     */
    @SerializedName("lastActivityTime")
    protected String lastActivityTime = null;

}
