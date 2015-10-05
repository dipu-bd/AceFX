/*
 * Copyright 2015 Sudipto Chandra.
 *
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
package com.sandsoft.acefx.util;

import java.util.Collection;
import netscape.javascript.JSObject;

/**
 *
 * @author Sudipto Chandra
 */
public class JSUtils {

    /**
     * Creates a new JSObject from string representation of a valid JavaScript
     * object.
     *
     * @param parent Parent JSObject to use to convert the string.
     * @param object String representation of a valid JavaScript object.
     * @return JSObject created from the given object string.
     */
    public static JSObject getObject(JSObject parent, Object object) {
        return (JSObject) parent.eval(String.format("(function() { return %s; })()", object));
    }

    /**
     * Creates a new JSObject from string representation of a valid JavaScript
     * object.
     *
     * @param parent Parent JSObject to use to convert the string.
     * @param object String representation of a valid JavaScript object.
     * @return JSObject created from the given object string.
     */
    public static JSObject getObjectByList(JSObject parent, Collection<?> object) {
        String[] data = object.toArray(new String[0]);
        return (JSObject) parent.eval(String.format("(function() { return [%s]; })()", String.join(",", data)));
    }
}
