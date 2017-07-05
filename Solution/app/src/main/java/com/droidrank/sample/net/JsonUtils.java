package com.droidrank.sample.net;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* @author Pragati Singh
 *   *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at 
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 */

public final class JsonUtils {


    /**
     * @param json
     * @param key
     * @return
     * @throws JSONException
     */
    public static String getStringFromJSON(JSONObject json, String key) throws JSONException {
        if (isValidJson(json, key)) {
            return json.getString(key);
        }
        return "";
    }

    public static JSONArray getJsonArrayFromJSON(JSONObject json, String key) throws JSONException {
        if (isValidJson(json, key)) {
            return json.getJSONArray(key);
        }
        return null;
    }

    public static JSONObject getJsonObjectFromJSON(JSONObject json, String key) throws JSONException {
        if (isValidJson(json, key)) {
            return json.getJSONObject(key);
        }
        return null;
    }

    /**
     * @param json
     * @param key
     */
    private static boolean isValidJson(JSONObject json, String key) {
        return !(json == null || !json.has(key));
    }

    /**
     * @param json
     * @param key
     * @return
     * @throws JSONException
     */
    public static Long getLongFromJSON(JSONObject json, String key) throws JSONException {
        if (isValidJson(json, key)) {
            return json.getLong(key);
        }
        return 0l;
    }

    /**
     * @param json
     * @param key
     * @return
     * @throws JSONException
     */
    public static Integer getIntFromJSON(JSONObject json, String key) throws JSONException {
        if (isValidJson(json, key)) {
            return json.getInt(key);
        }
        return 0;
    }


}
