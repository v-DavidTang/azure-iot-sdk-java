// Copyright (c) Microsoft. All rights reserved.
// Licensed under the MIT license. See LICENSE file in the project root for full license information.

package com.microsoft.azure.sdk.iot.deps.serializer;

import com.google.gson.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

/**
 * Twin Representation including the twin database and Json serializer and deserializer.
 */
public class Twin
{

    private TwinPropertiesChangeCallback onDesiredCallback = null;
    private TwinPropertiesChangeCallback onReportedCallback = null;
    private static TwinTagsChangeCallback onTagsCallback = null;

    private static final String TAGS_TAG = "tags";
    private static final String PROPERTIES_TAG = "properties";
    private static final String DESIRED_TAG = "desired";
    private static final String REPORTED_TAG = "reported";
    private static final String DATEFORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'";
    private static final String TIMEZONE = "UTC";

    protected TwinTags tags = null;
    protected TwinProperties properties = new TwinProperties();
    protected TwinManager manager = new TwinManager();

    /**
     * CONSTRUCTOR
     * Create a Twin instance with default values.
     *      set OnDesiredCallback as null
     *      set OnReportedCallback as null
     *      set Tags as null
     *
     */
    public Twin()
    {
        /* Codes_SRS_TWIN_21_001: [The constructor shall create an instance of the properties.] */
        /* Codes_SRS_TWIN_21_002: [The constructor shall set OnDesiredCallback as null.] */
        /* Codes_SRS_TWIN_21_003: [The constructor shall set OnReportedCallback as null.] */
        /* Codes_SRS_TWIN_21_004: [The constructor shall set Tags as null.] */
    }

    /**
     * CONSTRUCTOR
     * Create a Twin instance with default values.
     *      set OnReportedCallback as null
     *      set Tags as null
     *
     * @param onDesiredCallback - Callback function to report changes on the `Desired` database.
     */
    public Twin(TwinPropertiesChangeCallback onDesiredCallback)
    {
        /* Codes_SRS_TWIN_21_005: [The constructor shall call the standard constructor.] */
        /* Codes_SRS_TWIN_21_007: [The constructor shall set OnReportedCallback as null.] */
        /* Codes_SRS_TWIN_21_008: [The constructor shall set Tags as null.] */
        this();

        /* Codes_SRS_TWIN_21_006: [The constructor shall set OnDesiredCallback with the provided Callback function.] */
        setDesiredCallback(onDesiredCallback);
    }

    /**
     * CONSTRUCTOR
     * Create a Twin instance with default values.
     *      set Tags as null
     *
     * @param onDesiredCallback - Callback function to report changes on the `Desired` database.
     * @param onReportedCallback - Callback function to report changes on the `Reported` database.
     */
    public Twin(TwinPropertiesChangeCallback onDesiredCallback, TwinPropertiesChangeCallback onReportedCallback)
    {
        /* Codes_SRS_TWIN_21_009: [The constructor shall call the standard constructor.] */
        /* Codes_SRS_TWIN_21_012: [The constructor shall set Tags as null.] */
        this();

        /* Codes_SRS_TWIN_21_010: [The constructor shall set OnDesiredCallback with the provided Callback function.] */
        /* Codes_SRS_TWIN_21_011: [The constructor shall set OnReportedCallback with the provided Callback function.] */
        setDesiredCallback(onDesiredCallback);
        setReportedCallback(onReportedCallback);
    }

    /**
     * Set the callback function to report changes on the `Desired` database when `Twin`
     * receives a new json.
     *
     * @param onDesiredCallback - Callback function to report changes on the `Desired` database.
     */
    public void setDesiredCallback(TwinPropertiesChangeCallback onDesiredCallback)
    {
        /* Codes_SRS_TWIN_21_013: [The setDesiredCallback shall set OnDesiredCallback with the provided Callback function.] */
        /* Codes_SRS_TWIN_21_053: [The setDesiredCallback shall keep only one instance of the callback.] */
        /* Codes_SRS_TWIN_21_054: [If the OnDesiredCallback is already set, the setDesiredCallback shall replace the first one.] */
        /* Codes_SRS_TWIN_21_055: [If callback is null, the setDesiredCallback will set the OnDesiredCallback as null.] */
        this.onDesiredCallback = onDesiredCallback;
    }

    /**
     * Set the callback function to report changes on the `Reported` database when `Twin`
     * receives a new json.
     *
     * @param onReportedCallback - Callback function to report changes on the `Reported` database.
     */
    public void setReportedCallback(TwinPropertiesChangeCallback onReportedCallback)
    {
        /* Codes_SRS_TWIN_21_014: [The setReportedCallback shall set OnReportedCallback with the provided Callback function.] */
        /* Codes_SRS_TWIN_21_056: [The setReportedCallback shall keep only one instance of the callback.] */
        /* Codes_SRS_TWIN_21_057: [If the OnReportedCallback is already set, the setReportedCallback shall replace the first one.] */
        /* Codes_SRS_TWIN_21_058: [If callback is null, the setReportedCallback will set the OnReportedCallback as null.] */
        this.onReportedCallback = onReportedCallback;
    }

    /**
     * Set the callback function to report changes on the `tags` database when `Twin`
     * receives a new json.
     *
     * @param onTagsCallback - Callback function to report changes on the `Reported` database.
     */
    public void setTagsCallback(TwinTagsChangeCallback onTagsCallback)
    {
        /* Codes_SRS_TWIN_21_099: [The setTagsCallback shall set onTagsCallback with the provided callback function.] */
        /* Codes_SRS_TWIN_21_100: [The setTagsCallback shall keep only one instance of the callback.] */
        /* Codes_SRS_TWIN_21_101: [If the onTagsCallback is already set, the setTagsCallback shall replace the first one.] */
        /* Codes_SRS_TWIN_21_102: [If callback is null, the setTagsCallback will set the onTagsCallback as null.] */
        this.onTagsCallback = onTagsCallback;
    }

    /**
     * Create a String with a json content that represents all the information in the Twin class and innerClasses.
     *
     * @return String with the json content.
     */
    public String toJson()
    {
        /* Codes_SRS_TWIN_21_015: [The toJson shall create a String with information in the Twin using json format.] */
        /* Codes_SRS_TWIN_21_016: [The toJson shall not include null fields.] */
        return toJsonElement().toString();
    }

    /**
     * Create a JsonElement that represents all the information in the Twin class and innerClasses.
     *
     * @return JsonElement with the Twin information.
     */
    public JsonElement toJsonElement()
    {
        /* Codes_SRS_TWIN_21_017: [The toJsonElement shall return a JsonElement with information in the Twin using json format.] */
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        JsonObject twinJson = gson.toJsonTree(manager).getAsJsonObject();

        /* Codes_SRS_TWIN_21_018: [The toJsonElement shall not include null fields.] */
        if(tags != null)
        {
            /* Codes_SRS_TWIN_21_085: [If `tags` is enable, the toJsonElement shall include the tags in the json even if it has no content.] */
            twinJson.add(TAGS_TAG, tags.toJsonElement());
        }

        /* Codes_SRS_TWIN_21_086: [The toJsonElement shall include the `properties` in the json even if it has no content.] */
        /* Codes_SRS_TWIN_21_087: [The toJsonElement shall include the `desired` property in the json even if it has no content.] */
        /* Codes_SRS_TWIN_21_088: [The toJsonElement shall include the `reported` property in the json even if it has no content.] */
        twinJson.add(PROPERTIES_TAG, this.properties.toJsonElement());

        return (JsonElement) twinJson;
    }

    /**
     * Enable tags in the Twin database and in the Json.
     *
     */
    public void enableTags()
    {
        /* Codes_SRS_TWIN_21_019: [The enableTags shall enable tags in the twin database.] */
        this.tags = new TwinTags();
    }

    /**
     * Enable metadata report in the Json.
     *
     */
    public void enableMetadata()
    {
        /* Codes_SRS_TWIN_21_020: [The enableMetadata shall enable report metadata in Json for the Desired and for the Reported Properties.] */
        properties.enableDesiredMetadata();
        properties.enableReportedMetadata();
    }

    /**
     * Update the `desired` properties information in the database, and return a string with a json that contains a
     * collection of added properties, or properties with new value.
     *
     * @param desiredPropertyMap - Map of `desired` property to change the database.
     * @param reportedPropertyMap - Map of `reported` property to change the database.
     * @param tagsMap - Map of `tags` to change the database.
     * @return Json with added or changed properties and tags
     * @throws IllegalArgumentException This exception is thrown if the properties or tags in the maps do not fits the requirements.
     */
    public String updateTwin(Map<String, Object> desiredPropertyMap,
                             Map<String, Object> reportedPropertyMap,
                             Map<String, Object> tagsMap)
            throws IllegalArgumentException
    {
        JsonObject jsonProperty = new JsonObject();
        JsonObject jsonTwin;

        /* Codes_SRS_TWIN_21_080: [If one of the maps is invalid, the updateTwin shall not change the database and throw IllegalArgumentException.] */
        validateMap(desiredPropertyMap);
        validateMap(reportedPropertyMap);
        validateMap(tagsMap);

        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        jsonTwin = gson.toJsonTree(manager).getAsJsonObject();

        /* Codes_SRS_TWIN_21_075: [If Tags is not enable and `tagsMap` is not null, the updateTwin shall throw IllegalArgumentException.] */
        if((tags == null) && (tagsMap != null))
        {
            throw new IllegalArgumentException("Update not enabled Tags");
        }

        /* Codes_SRS_TWIN_21_116: [The updateTwin shall add all provided properties and tags to the database.] */
        /* Codes_SRS_TWIN_21_118: [If one of the provided map is null, the updateTwin shall not change that part of the database.] */
        /* Codes_SRS_TWIN_21_126: [The updateTwin shall only change properties and tags in the map, keep the others as is.] */
        /* Codes_SRS_TWIN_21_127: [The `key` and `value` in the maps shall be case sensitive.] */
        /* Codes_SRS_TWIN_21_128: [If one of the provided map is empty, the updateTwin shall not change its the database.] */
        /* Codes_SRS_TWIN_21_081: [If any `key` already exists, the updateTwin shall replace the existed value by the new one.] */
        /* Codes_SRS_TWIN_21_082: [If any `value` is null, the updateTwin shall store it but do not report on Json.] */
        JsonElement jsonDesiredProperty = innerUpdateDesiredProperty(desiredPropertyMap);
        if(jsonDesiredProperty != null)
        {
            jsonProperty.add(DESIRED_TAG, jsonDesiredProperty);
        }
        else
        {
            jsonProperty.add(DESIRED_TAG, new JsonObject());
        }

        /* Codes_SRS_TWIN_21_116: [The updateTwin shall add all provided properties and tags to the database.] */
        /* Codes_SRS_TWIN_21_118: [If one of the provided map is null, the updateTwin shall not change that part of the database.] */
        /* Codes_SRS_TWIN_21_126: [The updateTwin shall only change properties and tags in the map, keep the others as is.] */
        /* Codes_SRS_TWIN_21_127: [The `key` and `value` in the maps shall be case sensitive.] */
        /* Codes_SRS_TWIN_21_128: [If one of the provided map is empty, the updateTwin shall not change its the database.] */
        /* Codes_SRS_TWIN_21_081: [If any `key` already exists, the updateTwin shall replace the existed value by the new one.] */
        /* Codes_SRS_TWIN_21_082: [If any `value` is null, the updateTwin shall store it but do not report on Json.] */
        JsonElement jsonReportedProperty = innerUpdateReportedProperty(reportedPropertyMap);
        if(jsonReportedProperty != null)
        {
            jsonProperty.add(REPORTED_TAG, jsonReportedProperty);
        }
        else
        {
            jsonProperty.add(REPORTED_TAG,  new JsonObject());
        }

        JsonElement jsonTags = null;
        if(tags != null)
        {
            /* Codes_SRS_TWIN_21_116: [The updateTwin shall add all provided properties and tags to the database.] */
            /* Codes_SRS_TWIN_21_118: [If one of the provided map is null, the updateTwin shall not change that part of the database.] */
            /* Codes_SRS_TWIN_21_126: [The updateTwin shall only change properties and tags in the map, keep the others as is.] */
            /* Codes_SRS_TWIN_21_127: [The `key` and `value` in the maps shall be case sensitive.] */
            /* Codes_SRS_TWIN_21_128: [If one of the provided map is empty, the updateTwin shall not change its the database.] */
            /* Codes_SRS_TWIN_21_081: [If any `key` already exists, the updateTwin shall replace the existed value by the new one.] */
            /* Codes_SRS_TWIN_21_082: [If any `value` is null, the updateTwin shall store it but do not report on Json.] */
            jsonTags = innerUpdateTags(tagsMap);
            if(jsonTags != null)
            {
                jsonTwin.add(TAGS_TAG, jsonTags);
            }
            else
            {
                jsonTwin.add(TAGS_TAG,  new JsonObject());
            }
        }

        if((jsonDesiredProperty!=null) || (jsonReportedProperty!=null) || (jsonTags!=null))
        {
            jsonTwin.add(PROPERTIES_TAG, jsonProperty);
        }
        else
        {
            /* Codes_SRS_TWIN_21_119: [If no property or tags changed its value, the updateTwin shall return null.] */
            return null;
        }

        /* Codes_SRS_TWIN_21_117: [The updateTwin shall return a string with json representing the properties and tags with changes.] */
        return jsonTwin.toString();
    }


    /**
     * Update the `desired` properties information in the database, and return a string with a json that contains a
     * collection of added properties, or properties with new value.
     *
     * @param propertyMap - Map of `desired` property to change the database.
     * @return Json with added or changed properties
     * @throws IllegalArgumentException This exception is thrown if the properties in the map do not fits the requirements.
     */
    public String updateDesiredProperty(Map<String, Object> propertyMap) throws IllegalArgumentException
    {
        JsonElement jsonElement = innerUpdateDesiredProperty(propertyMap);
        if(jsonElement == null)
        {
            /* Codes_SRS_TWIN_21_023: [If the provided `property` map is null, the updateDesiredProperty shall not change the database and return null.] */
            /* Codes_SRS_TWIN_21_024: [If no Desired property changed its value, the updateDesiredProperty shall return null.] */
            /* Codes_SRS_TWIN_21_063: [If the provided `property` map is empty, the updateDesiredProperty shall not change the database and return null.] */
            return null;
        }
        return jsonElement.toString();
    }

    private JsonElement innerUpdateDesiredProperty(Map<String, Object> propertyMap) throws IllegalArgumentException
    {
        JsonElement updatedElements;

        if(propertyMap != null)
        {
            /* Codes_SRS_TWIN_21_073: [If the map is invalid, the updateDesiredProperty shall throw IllegalArgumentException.] */
            validateMap(propertyMap);

            /* Codes_SRS_TWIN_21_021: [The updateDesiredProperty shall add all provided properties to the Desired property.] */
            /* Codes_SRS_TWIN_21_059: [The updateDesiredProperty shall only change properties in the map, keep the others as is.] */
            /* Codes_SRS_TWIN_21_061: [All `key` and `value` in property shall be case sensitive.] */
            /* Codes_SRS_TWIN_21_077: [If any `key` already exists, the updateDesiredProperty shall replace the existed value by the new one.] */
            /* Codes_SRS_TWIN_21_078: [If any `value` is null, the updateDesiredProperty shall store it but do not report on Json.] */
            updatedElements = properties.updateDesired(propertyMap);
        }
        else
        {
            /* Codes_SRS_TWIN_21_023: [If the provided `property` map is null, the updateDesiredProperty shall not change the database and return null.] */
            updatedElements = null;
        }

        return updatedElements;
    }

    /**
     * Update the `reported` properties information in the database, and return a string with a json that contains a
     * collection of added properties, or properties with new value.
     *
     * @param propertyMap - Map of `reported` property to change the database.
     * @return Json with added or changed properties
     * @throws IllegalArgumentException This exception is thrown if the properties in the map do not fits the requirements.
     */
    public String updateReportedProperty(Map<String, Object> propertyMap) throws IllegalArgumentException
    {
        JsonElement jsonElement = innerUpdateReportedProperty(propertyMap);
        if(jsonElement == null)
        {
            /* Codes_SRS_TWIN_21_027: [If the provided `property` map is null, the updateReportedProperty shall not change the database and return null.] */
            /* Codes_SRS_TWIN_21_028: [If no Reported property changed its value, the updateReportedProperty shall return null.] */
            return null;
        }
        return jsonElement.toString();
    }

    private JsonElement innerUpdateReportedProperty(Map<String, Object> propertyMap) throws IllegalArgumentException
    {
        JsonElement updatedElements;

        if(propertyMap != null)
        {
            /* Codes_SRS_TWIN_21_079: [If the map is invalid, the updateReportedProperty shall throw IllegalArgumentException.] */
            validateMap(propertyMap);

            /* Codes_SRS_TWIN_21_025: [The updateReportedProperty shall add all provided properties to the Reported property.] */
            /* Codes_SRS_TWIN_21_060: [The updateReportedProperty shall only change properties in the map, keep the others as is.] */
            /* Codes_SRS_TWIN_21_062: [All `key` and `value` in property shall be case sensitive.] */
            /* Codes_SRS_TWIN_21_083: [If any `key` already exists, the updateReportedProperty shall replace the existed value by the new one.] */
            /* Codes_SRS_TWIN_21_084: [If any `value` is null, the updateReportedProperty shall store it but do not report on Json.] */
            updatedElements = properties.updateReported(propertyMap);
        }
        else
        {
            /* Codes_SRS_TWIN_21_027: [If the provided `property` map is null, the updateReportedProperty shall not change the database and return null.] */
            updatedElements = null;
        }

        return updatedElements;
    }
    
    /**
     * Update the `tags` information in the database, and return a string with a json that contains a
     * collection of added tags, or tags with new value.
     *
     * @param tagsMap - Map of `tags` to change the database.
     * @return Json with added or changed tags
     * @throws IllegalArgumentException This exception is thrown if the tags in the map do not fits the requirements.
     */
    public String updateTags(Map<String, Object> tagsMap) throws IllegalArgumentException 
    {
        JsonElement jsonElement = innerUpdateTags(tagsMap);
        if(jsonElement == null)
        {
            /* Codes_SRS_TWIN_21_109: [If the provided `tagsMap` is empty, the updateTags shall not change the database and return null.] */
            /* Codes_SRS_TWIN_21_104: [The updateTags shall return a string with json representing the tags with changes.] */
            return null;
        }
        return jsonElement.toString();
    }

    private JsonElement innerUpdateTags(Map<String, Object> tagsMap) throws IllegalArgumentException
    {
        JsonElement updatedElements;

        if(tags == null)
        {
            /* Codes_SRS_TWIN_21_111: [If Tags is not enable, the updateTags shall throw IllegalArgumentException.] */
            throw new IllegalArgumentException("Update not enabled Tags");
        }

        if(tagsMap != null)
        {
            /* Codes_SRS_TWIN_21_110: [If the map is invalid, the updateTags shall throw IllegalArgumentException.] */
            validateMap(tagsMap);

            /* Codes_SRS_TWIN_21_103: [The updateTags shall add all provided tags to the database.] */
            /* Codes_SRS_TWIN_21_106: [If no tags changed its value, the updateTags shall return null.] */
            /* Codes_SRS_TWIN_21_107: [The updateTags shall only change tags in the map, keep the others as is.] */
            /* Codes_SRS_TWIN_21_108: [All `key` and `value` in tags shall be case sensitive.] */
            /* Codes_SRS_TWIN_21_114: [If any `key` already exists, the updateTags shall replace the existed value by the new one.] */
            /* Codes_SRS_TWIN_21_115: [If any `value` is null, the updateTags shall store it but do not report on Json.] */
            updatedElements = tags.update(tagsMap);
        }
        else
        {
        /* Codes_SRS_TWIN_21_105: [If the provided `tagsMap` is null, the updateTags shall not change the database and return null.] */
            updatedElements = null;
        }

        return updatedElements;
    }

    /**
     * Reset the `desired` properties information in the database, deleting all old properties and add all the new provided ones.
     * Return a string with a json that contains a collection of added properties.
     *
     * @param propertyMap - Map of `desired` property to change the database.
     * @return Json with added properties
     * @throws IllegalArgumentException This exception is thrown if the properties in the map do not fits the requirements.
     */
    public String resetDesiredProperty(Map<String, Object> propertyMap) throws IllegalArgumentException
    {
        String json;

        if(propertyMap != null)
        {
            /* Codes_SRS_TWIN_21_125: [If the map is invalid, the resetDesiredProperty shall not change the database and throw IllegalArgumentException.] */
            validateMap(propertyMap);

            /* Codes_SRS_TWIN_21_120: [The resetDesiredProperty shall cleanup the desired database and add all provided properties to the Desired property.] */
            /* Codes_SRS_TWIN_21_123: [The `key` and `value` in property shall be case sensitive.] */
            /* Codes_SRS_TWIN_21_124: [If the provided `propertyMap` is empty, the resetDesiredProperty shall cleanup the desired database and return `{}`.] */
            /* Codes_SRS_TWIN_21_129: [If any `value` is null, the resetDesiredProperty shall store it but do not report on Json.] */
            JsonElement updatedElements = properties.resetDesired(propertyMap);

            if (updatedElements == null)
            {
                json = "{}";
            }
            else
            {
                /* Codes_SRS_TWIN_21_121: [The resetDesiredProperty shall return a string with json representing the added desired properties.] */
                json = updatedElements.toString();
            }
        }
        else
        {
            /* Codes_SRS_TWIN_21_122: [If the provided `propertyMap` is null, the resetDesiredProperty shall cleanup the desired database and return `{}`.] */
            properties.clearDesired();
            json = "{}";
        }

        return json;
    }

    /**
     * Reset the `reported` properties information in the database, deleting all old properties and add all the new provided ones.
     * Return a string with a json that contains a collection of added properties.
     *
     * @param propertyMap - Map of `reported` property to change the database.
     * @return Json with added properties
     * @throws IllegalArgumentException This exception is thrown if the properties in the map do not fits the requirements.
     */
    public String resetReportedProperty(Map<String, Object> propertyMap) throws IllegalArgumentException
    {
        String json;

        if(propertyMap != null)
        {
            /* Codes_SRS_TWIN_21_135: [If the map is invalid, the resetReportedProperty shall not change the database and throw IllegalArgumentException.] */
            validateMap(propertyMap);

            /* Codes_SRS_TWIN_21_130: [The resetReportedProperty shall cleanup the reported database and add all provided properties to the Reported property.] */
            /* Codes_SRS_TWIN_21_133: [The `key` and `value` in property shall be case sensitive.] */
            /* Codes_SRS_TWIN_21_134: [If the provided `propertyMap` is empty, the resetReportedProperty shall cleanup the reported database and return `{}`.] */
            /* Codes_SRS_TWIN_21_139: [If any `value` is null, the resetReportedProperty shall store it but do not report on Json.] */
            JsonElement updatedElements = properties.resetReported(propertyMap);

            if (updatedElements == null)
            {
                json = "{}";
            }
            else
            {
                /* Codes_SRS_TWIN_21_131: [The resetReportedProperty shall return a string with json representing the added reported properties.] */
                json = updatedElements.toString();
            }
        }
        else
        {
            /* Codes_SRS_TWIN_21_132: [If the provided `propertyMap` is null, the resetReportedProperty shall cleanup the reported database and return `{}`.] */
            properties.clearReported();
            json = "{}";
        }

        return json;
    }

    /**
     * Reset the `tags` information in the database, deleting all old tags and add all the new provided ones.
     * Return a string with a json that contains a collection of added tags.
     *
     * @param tagsMap - Map of `tags` to change the database.
     * @return Json with added tags
     * @throws IllegalArgumentException This exception is thrown if the tags in the map do not fits the requirements.
     */
    public String resetTags(Map<String, Object> tagsMap) throws IllegalArgumentException
    {
        String json;

        if(tags == null)
        {
            /* Codes_SRS_TWIN_21_146: [If Tags is not enable, the resetTags shall throw IllegalArgumentException.] */
             throw new IllegalArgumentException("Update not enabled Tags");
        }

        if(tagsMap != null)
        {
            /* Codes_SRS_TWIN_21_145: [If the map is invalid, the resetTags shall not change the database and throw IllegalArgumentException.] */
            validateMap(tagsMap);

            /* Codes_SRS_TWIN_21_140: [The resetTags shall cleanup the tags database and add all provided tags to the tags.] */
            /* Codes_SRS_TWIN_21_143: [The `key` and `value` in tags shall be case sensitive.] */
            /* Codes_SRS_TWIN_21_144: [If the provided `tagsMap` is empty, the resetTags shall cleanup the tags database and return `{}`.] */
            /* Codes_SRS_TWIN_21_149: [If any `value` is null, the resetTags shall store it but do not report on Json.] */
            tags = new TwinTags();
            JsonElement updatedElements = this.tags.update(tagsMap);

            if (updatedElements == null)
            {
                json = "{}";
            }
            else
            {
                /* Codes_SRS_TWIN_21_141: [The resetTags shall return a string with json representing the added tags.] */
                json = updatedElements.toString();
            }
        }
        else
        {
            /* Codes_SRS_TWIN_21_142: [If the provided `tagsMap` is null, the resetTags shall cleanup the tags database and return `{}`.] */
            tags = new TwinTags();
            json = "{}";
        }

        return json;
    }

    /**
     * Update the properties information in the database, using the information parsed from the provided json.
     * It will fire a callback if any property was added, excluded, or had its value updated.
     *
     * @param json - Json with property to change the database.
     * @throws IllegalArgumentException This exception is thrown if the Json is not well formed.
     */
    public void updateTwin(String json) throws IllegalArgumentException
    {
        /* Codes_SRS_TWIN_21_043: [If the provided json is not valid, the updateTwin shall throws IllegalArgumentException.] */
        validateJson(json);

        /* Codes_SRS_TWIN_21_071: [If the provided json is empty, the updateTwin shall not change the database and not call the OnDesiredCallback or the OnReportedCallback.] */
        /* Codes_SRS_TWIN_21_072: [If the provided json is null, the updateTwin shall not change the database and not call the OnDesiredCallback or the OnReportedCallback.] */
        if((json != null) && (!json.isEmpty()))
        {
            Map<String, Object> jsonTree;
            try
            {
                /* Codes_SRS_TWIN_21_097: [If the provided json have any duplicated `properties` or `tags`, the updateTwin shall throw IllegalArgumentException.] */
                /* Codes_SRS_TWIN_21_098: [If the provided json is properties only and contains duplicated `desired` or `reported`, the updateTwin shall throws IllegalArgumentException.] */
                /* Codes_SRS_TWIN_21_094: [If the provided json have any duplicated `key`, the updateTwin shall use the content of the last one in the String.] */
                Gson gson = new GsonBuilder().disableInnerClassSerialization().disableHtmlEscaping().create();
                jsonTree = (Map<String, Object>) gson.fromJson(json, HashMap.class);
                manager = gson.fromJson(json, TwinManager.class);
            }
            catch (Exception e)
            {
                /* Codes_SRS_TWIN_21_043: [If the provided json is not valid, the updateTwin shall throws IllegalArgumentException.] */
                throw new IllegalArgumentException("Malformed Json: " + e);
            }

            boolean propertiesLevel = false;
            for (Map.Entry<String, Object> entry : jsonTree.entrySet())
            {
                if (entry.getKey().equals(PROPERTIES_TAG))
                {
                    /* Codes_SRS_TWIN_21_039: [The updateTwin shall fill the fields the properties in the Twin class with the keys and values provided in the json string.] */
                    /* Codes_SRS_TWIN_21_040: [The updateTwin shall not change fields that is not reported in the json string.] */
                    /* Codes_SRS_TWIN_21_041: [The updateTwin shall create a list with all properties that was updated (new key or value) by the new json.] */
                    /* Codes_SRS_TWIN_21_042: [If a valid key has a null value, the updateTwin shall delete this property.] */
                    /* Codes_SRS_TWIN_21_044: [If OnDesiredCallback was provided, the updateTwin shall create a new map with a copy of all pars key values updated by the json in the Desired property, and OnDesiredCallback passing this map as parameter.] */
                    /* Codes_SRS_TWIN_21_045: [If OnReportedCallback was provided, the updateTwin shall create a new map with a copy of all pars key values updated by the json in the Reported property, and OnReportedCallback passing this map as parameter.] */
                    /* Codes_SRS_TWIN_21_046: [If OnDesiredCallback was not provided, the updateTwin shall not do anything with the list of updated desired properties.] */
                    /* Codes_SRS_TWIN_21_047: [If OnReportedCallback was not provided, the updateTwin shall not do anything with the list of updated reported properties.] */
                    /* Codes_SRS_TWIN_21_069: [If there is no change in the Desired property, the updateTwin shall not change the reported database and not call the OnReportedCallback.] */
                    /* Codes_SRS_TWIN_21_070: [If there is no change in the Reported property, the updateTwin shall not change the reported database and not call the OnReportedCallback.] */
                    properties.update((Map<String, Object>) entry.getValue(), onDesiredCallback, onReportedCallback);
                    propertiesLevel = true;
                }
                else if ((entry.getKey().equals(DESIRED_TAG)) || (entry.getKey().equals(REPORTED_TAG)))
                {
                    /* Codes_SRS_TWIN_21_089: [If the provided json contains `desired` or `reported` in its first level, the updateTwin shall parser the json as properties only.] */
                    if (!propertiesLevel)
                    {
                        /* Codes_SRS_TWIN_21_090: [If the provided json is properties only and contains other tag different than `desired` or `reported`, the updateTwin shall throws IllegalArgumentException.] */
                        properties.update(jsonTree, onDesiredCallback, onReportedCallback);
                    }
                    else
                    {
                        /* Codes_SRS_TWIN_21_091: [If the provided json is NOT properties only and contains `desired` or `reported` in its first level, the updateTwin shall throws IllegalArgumentException.] */
                        throw new IllegalArgumentException("Invalid Entry");
                    }
                    break;
                }
                else if (entry.getKey().equals(TAGS_TAG))
                {
                    if(tags != null)
                    {
                        tags.update((Map<String, Object>) entry.getValue(), onTagsCallback);
                    }
                    propertiesLevel = true;
                }
            }
        }
    }

    /**
     * Update the `desired` properties information in the database, using the information parsed from the provided json.
     * It will fire a callback if any property was added, excluded, or had its value updated.
     *
     * @param json - Json with `desired` property to change the database.
     * @throws IllegalArgumentException This exception is thrown if the Json is not well formed.
     */
    public void updateDesiredProperty(String json) throws IllegalArgumentException
    {
        /* Codes_SRS_TWIN_21_065: [If the provided json is empty, the updateDesiredProperty shall not change the database and not call the OnDesiredCallback.] */
        /* Codes_SRS_TWIN_21_066: [If the provided json is null, the updateDesiredProperty shall not change the database and not call the OnDesiredCallback.] */
        if((json != null) && (!json.isEmpty()))
        {
            /* Codes_SRS_TWIN_21_029: [The updateDesiredProperty shall update the Desired property using the information provided in the json.] */
            /* Codes_SRS_TWIN_21_030: [The updateDesiredProperty shall generate a map with all pairs key value that had its content changed.] */
            /* Codes_SRS_TWIN_21_031: [The updateDesiredProperty shall send the map with all changed pairs to the upper layer calling onDesiredCallback (TwinPropertiesChangeCallback).] */
            /* Codes_SRS_TWIN_21_032: [If the OnDesiredCallback is set as null, the updateDesiredProperty shall discard the map with the changed pairs.] */
            /* Codes_SRS_TWIN_21_033: [If there is no change in the Desired property, the updateDesiredProperty shall not change the database and not call the OnDesiredCallback.] */
            /* Codes_SRS_TWIN_21_092: [If the provided json is not valid, the updateDesiredProperty shall throws IllegalArgumentException.] */
            try
            {
                properties.updateDesired(json, onDesiredCallback);
            }
            catch (com.google.gson.JsonSyntaxException e)
            {
                /* Codes_SRS_TWIN_21_096: [If the provided json have any duplicated `key`, the updateDesiredProperty shall throws IllegalArgumentException.] */
                throw new IllegalArgumentException("Malformed json: " + e);
            }
        }
    }

    /**
     * Update the `reported` properties information in the database, using the information parsed from the provided json.
     * It will fire a callback if any property was added, excluded, or had its value updated.
     *
     * @param json - Json with `reported` property to change the database.
     * @throws IllegalArgumentException This exception is thrown if the Json is not well formed.
     */
    public void updateReportedProperty(String json) throws IllegalArgumentException
    {
        /* Codes_SRS_TWIN_21_067: [If the provided json is empty, the updateReportedProperty shall not change the database and not call the OnReportedCallback.] */
        /* Codes_SRS_TWIN_21_068: [If the provided json is null, the updateReportedProperty shall not change the database and not call the OnReportedCallback.] */
        if((json != null) && (!json.isEmpty()))
        {
            /* Codes_SRS_TWIN_21_034: [The updateReportedProperty shall update the Reported property using the information provided in the json.] */
            /* Codes_SRS_TWIN_21_035: [The updateReportedProperty shall generate a map with all pairs key value that had its content changed.] */
            /* Codes_SRS_TWIN_21_036: [The updateReportedProperty shall send the map with all changed pairs to the upper layer calling onReportedCallback (TwinPropertiesChangeCallback).] */
            /* Codes_SRS_TWIN_21_037: [If the OnReportedCallback is set as null, the updateReportedProperty shall discard the map with the changed pairs.] */
            /* Codes_SRS_TWIN_21_038: [If there is no change in the Reported property, the updateReportedProperty shall not change the database and not call the OnReportedCallback.] */
            /* Codes_SRS_TWIN_21_093: [If the provided json is not valid, the updateReportedProperty shall throws IllegalArgumentException.] */
            try
            {
                properties.updateReported(json, onReportedCallback);
            }
            catch (com.google.gson.JsonSyntaxException e)
            {
                /* Codes_SRS_TWIN_21_095: [If the provided json have any duplicated `key`, the updateReportedProperty shall throws IllegalArgumentException.] */
                throw new IllegalArgumentException("Malformed json: " + e);
            }
        }
    }

    /**
     * Update the device manager information in the database, and return a string with a json that contains a
     * the new device manager description, including new and old values.
     *
     * @param deviceId - Device name
     * @param generationId - Device generation Id
     * @param eTag - A string representing a weak ETAG version of the resulted JSON description.
     * @param status - Device status("enabled", "disabled")
     * @param statusReason - A 128 char long string storing the reason of suspension (for status="disabled").
     * @return Json with the manager description. Null if nothing change.
     * @throws IllegalArgumentException This exception is thrown if there are any inconsistent in the provided description.
     */
    public String updateDeviceManager(String deviceId, String generationId, String eTag, TwinStatus status, String statusReason) throws IllegalArgumentException
    {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        boolean change = false;

        /* Codes_SRS_TWIN_21_162: [The updateDeviceManager shall replace the `status` by the provided one.] */
        if(status == null)
        {
            if(manager.status != null)
            {
                change = true;
            }
            manager.status = null;
            manager.statusReason = null;
            manager.statusUpdatedTime = null;
        }
        else
        {
            if(statusReason == null)
            {
                /* Codes_SRS_TWIN_21_165: [If the provided `status` is different than the previous one, and the `statusReason` is null, The updateDeviceManager shall throw IllegalArgumentException.] */
                throw new IllegalArgumentException("Change status without statusReason");
            }
            else if((manager.status == null) || (!manager.status.equals(status)))
            {
                manager.status = status;

                /* Codes_SRS_TWIN_21_163: [If the provided `status` is different than the previous one, The updateDeviceManager shall replace the `statusReason` by the provided one.] */
                manager.statusReason = statusReason;

                /* Codes_SRS_TWIN_21_164: [If the provided `status` is different than the previous one, The updateDeviceManager shall set the `statusUpdatedTime` with the current date and time.] */
                SimpleDateFormat dateFormat = new SimpleDateFormat(DATEFORMAT);
                dateFormat.setTimeZone(TimeZone.getTimeZone(TIMEZONE));
                manager.statusUpdatedTime = dateFormat.format(new Date());
                change = true;
            }
        }

        /* Codes_SRS_TWIN_21_159: [The updateDeviceManager shall replace the `deviceId` by the provided one.] */
        if((manager.deviceId == null) || (deviceId == null) || (!manager.deviceId.equals(deviceId)))
        {
            manager.deviceId = deviceId;
            if(manager.deviceId != null)
            {
                change = true;
            }
        }

        /* Codes_SRS_TWIN_21_160: [The updateDeviceManager shall replace the `generationId` by the provided one.] */
        if((manager.generationId == null) || (generationId == null) || (!manager.generationId.equals(generationId)))
        {
            manager.generationId = generationId;
            if(manager.generationId!= null)
            {
                change = true;
            }
        }

        /* Codes_SRS_TWIN_21_161: [The updateDeviceManager shall replace the `eTag` by the provided one.] */
        if((manager.eTag == null) || (eTag == null) || (!manager.eTag.equals(eTag)))
        {
            manager.eTag = eTag;
            if(manager.eTag != null)
            {
                change = true;
            }
        }

        if(!change)
        {
            /* Codes_SRS_TWIN_21_167: [If nothing change in the management database, The updateDeviceManager shall return null.] */
            return null;
        }

        /* Codes_SRS_TWIN_21_166: [The updateDeviceManager shall return a json with the new device management information.] */
        return toJson();
    }

    /**
     * Return the `desired` property version.
     *
     * @return Integer that contains the `desired` property version (it can be null).
     */
    public Integer getDesiredPropertyVersion()
    {
        /* Codes_SRS_TWIN_21_048: [The getDesiredPropertyVersion shall return the desired property version.] */
        return properties.getDesiredVersion();
    }

    /**
     * Return the `reported` property version.
     *
     * @return Integer that contains the `reported` property version (it can be null).
     */
    public Integer getReportedPropertyVersion()
    {
        /* Codes_SRS_TWIN_21_049: [The getReportedPropertyVersion shall return the reported property version.] */
        return properties.getReportedVersion();
    }

    /**
     * Return a map with all `desired` properties in the database.
     *
     * @return A map with all `desired` properties in the database (it can be null).
     */
    public Map<String, Object> getDesiredPropertyMap()
    {
        /* Codes_SRS_TWIN_21_050: [The getDesiredPropertyMap shall return a map with all desired property key value pairs.] */
        return properties.getDesiredPropertyMap();
    }

    /**
     * Return a map with all `reported` properties in the database.
     *
     * @return A map with all `reported` properties in the database (it can be null).
     */
    public Map<String, Object> getReportedPropertyMap()
    {
        /* Codes_SRS_TWIN_21_051: [The getReportedPropertyMap shall return a map with all reported property key value pairs.] */
        return properties.getReportedPropertyMap();
    }

    /**
     * Return a map with all `tags` in the database.
     *
     * @return A map with all `tags` in the database (it can be null).
     */
    public Map<String, Object> getTagsMap()
    {
        if(this.tags == null)
        {
            /* Codes_SRS_TWIN_21_074: [If Tags is not enable, the getTagsMap shall throw IllegalArgumentException.] */
             throw new IllegalArgumentException("Update not enabled Tags");
        }

        /* Codes_SRS_TWIN_21_052: [The getTagsMap shall return a map with all tags in the database.] */
         return this.tags.getMap();
    }

    /**
     * Getter for device name
     *
     * @return Device name
     * A case-sensitive string (up to 128 char long)
     * of ASCII 7-bit alphanumeric chars
     * + {'-', ':', '.', '+', '%', '_', '#', '*', '?', '!', '(', ')', ',', '=', '@', ';', '$', '''}.
     */
    public String getDeviceId()
    {
        /* Codes_SRS_TWIN_21_112: [The `getDeviceId` shall return the device name.] */
        return this.manager.deviceId;
    }

    /**
     * Getter for device generation name
     *
     * @return Device generation Id
     */
    public String getGenerationId()
    {
        /* Codes_SRS_TWIN_21_150: [The `getGenerationId` shall return the device generation name.] */
        return this.manager.generationId;
    }

    /**
     * Getter for ETag
     *
     * @return A string representing a weak ETAG version
     * of this JSON description. This is a hash.
     */
    public String getETag()
    {
        /* Codes_SRS_TWIN_21_113: [The `getETag` shall return the string representing a weak ETAG version.] */
        return this.manager.eTag;
    }

    /**
     * Getter for device status
     *
     * @return "enabled", "disabled".
     * If "enabled", this device is authorized to connect.
     * If "disabled" this device cannot receive or send messages, and statusReason must be set.
     */
    public TwinStatus getStatus()
    {
        /* Codes_SRS_TWIN_21_136: [The `getStatus` shall return the device status.] */
        return this.manager.status;
    }

    /**
     * Getter for status reason
     *
     * @return A 128 char long string storing the reason of suspension.
     * (all UTF-8 chars allowed).
     */
    public String getStatusReason()
    {
        /* Codes_SRS_TWIN_21_137: [The `getStatusReason` shall return the device status reason.] */
        return this.manager.statusReason;
    }

    /**
     * Getter for status updated date and time
     *
     * @return Datetime of last time the state was updated.
     */
    public String getStatusUpdatedTime()
    {
        /* Codes_SRS_TWIN_21_138: [The `getStatusUpdatedTime` shall return the device status update date and time.] */
        return this.manager.statusUpdatedTime;
    }

    /**
     * Getter for connection state
     *
     * @return The connectionState string
     */
    public TwinConnectionState getConnectionState()
    {
        /* Codes_SRS_TWIN_21_147: [The `getConnectionState` shall return the connection state.] */
        return this.manager.connectionState;
    }

    /**
     * Getter for connection state updated date and time
     *
     * @return The string containing the time when the connectionState parameter was updated
     */
    public String getConnectionStateUpdatedTime()
    {
        /* Codes_SRS_TWIN_21_148: [The `getConnectionStateUpdatedTime` shall return the connection state update date and time.] */
        return this.manager.connectionStateUpdatedTime;
    }

    /**
     * Getter for last activity time
     *
     * @return The string containing the time when the lastActivity parameter was updated
     */
    public String getLastActivityTime()
    {
        /* Codes_SRS_TWIN_21_151: [The `getLastActivityTime` shall return the last activity date and time.] */
        return this.manager.lastActivityTime;
    }

    private void validateJson(String json) throws IllegalArgumentException
    {
        Map<String, Object> map;
        try
        {
            Gson gson = new GsonBuilder().disableInnerClassSerialization().disableHtmlEscaping().create();
            map = (Map<String, Object>) gson.fromJson(json, HashMap.class);
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException("Malformed Json: " + e);
        }

        if(map != null)
        {
            validateMap(map, 0, 6, true);
        }
    }

    private void validateMap(Map<String, Object> map) throws IllegalArgumentException
    {
        if(map != null)
        {
            validateMap(map, 0, 5, false);
        }
    }

    private void validateMap(Map<String, Object> map, int level, int maxLevel, boolean allowDollar) throws IllegalArgumentException
    {
        level ++;
        
        for(Map.Entry<String, Object> entry : map.entrySet())
        {
            String key = entry.getKey();
            Object value = entry.getValue();

            /* Codes_SRS_TWIN_21_152: [A valid `key` shall not be null.] */
            if((key == null) ||
                    /* Codes_SRS_TWIN_21_153: [A valid `key` shall not be empty.] */
                    (key.isEmpty()) ||
                    /* Codes_SRS_TWIN_21_154: [A valid `key` shall be less than 128 characters long.] */
                    (key.length() > 128) ||
                    /* Codes_SRS_TWIN_21_155: [A valid `key` shall not have an illegal character (`$`,`.`, space).] */
                    (key.contains(".") || key.contains(" ") || (key.contains("$") && ! allowDollar) ) )
            {
                throw new IllegalArgumentException("Malformed Json: illegal key");
            }

            /* Codes_SRS_TWIN_21_156: [A valid `value` shall contains types of boolean, number, or string.] */
            if((value != null) && !(value instanceof String) && !(value instanceof Boolean) && !(value instanceof Number)&& !(value instanceof Map))
            {
                throw new IllegalArgumentException("Malformed Json: illegal value type");
            }

            /* Codes_SRS_TWIN_21_157: [A valid `value` can contains sub-maps.] */
            if(value instanceof Map)
            {
                /* Codes_TWIN_21_158: [A valid `value` shall contains less than 5 levels of sub-maps.] */
                if(level <= maxLevel)
                {
                    validateMap((Map<String, Object>) value, level, maxLevel, allowDollar);
                }
                else
                {
                    throw new IllegalArgumentException("Malformed Json: exceed 5 levels");
                }
            }
        }
    }
}
