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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import netscape.javascript.JSObject;

/**
 *
 * @author Sudipto Chandra
 */
public class Commons {

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
        Iterator<?> it = object.iterator();                
        StringBuilder sb = new StringBuilder();
        while(it.hasNext())
        {
            if(sb.length() > 0) sb.append(",");
            sb.append(it.next());
        }
        return (JSObject) parent.eval(String.format("(function() { return [%s]; })()", sb.toString()));
    }
 
    /**
     * Gets a list of all properties of a JavaScript object.
     *
     * @param data The object to get properties.
     * @return List of all properties of the object.
     */
    public static ArrayList<String> getAllProperties(JSObject data) {
        //gets the list of properties.
        ArrayList<String> propList = new ArrayList<>();
        JSObject properties = (JSObject) data.eval("Object.getOwnPropertyNames(this);");
        for (int i = 0; i < (int) properties.eval("this.length"); ++i) {
            propList.add((String) properties.getSlot(i));
        }
        return propList;
    }

}
