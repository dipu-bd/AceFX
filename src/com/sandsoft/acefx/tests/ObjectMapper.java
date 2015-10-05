/*
 * Copyright 2015 dipu.
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
package com.sandsoft.acefx.tests;

import java.util.ArrayList;
import java.util.Comparator;
import netscape.javascript.JSObject;

/**
 *
 * @author dipu
 */
public class ObjectMapper {

    public static final int MAX_LEVEL = 5;

    public static void MapObject(int level, Object obj) {

        String pre = "";
        for (int i = 0; i < level; ++i) {
            pre += "    ";
        }

        if (!(obj instanceof JSObject)) {
            System.out.printf("%s%s\n", pre, obj);
            return;
        }
        if (level >= MAX_LEVEL) {
            return;
        }

        if (level == 0) {
            System.out.println("========================================================");
        }

        JSObject dat = (JSObject) obj;
        //dat = (JSObject) dat.eval("Object.getPrototypeOf(this)");

        ArrayList<String> als = new ArrayList<>();
        JSObject properties = (JSObject) dat.eval("Object.getOwnPropertyNames(this);");
        for (int i = 0; i < (int) properties.eval("this.length"); ++i) {
            als.add((String) properties.getSlot(i));
        }
        als.sort((String t, String t1) -> {
            return t.compareTo(t1);
        });

        for (String str : als) {
            System.out.printf("%s[%d]%s\n", pre, level + 1, str);
            MapObject(level + 1, dat.getMember(str));
        }

        if (level == 0) {
            System.out.println("========================================================");
            
            dat = (JSObject) dat.eval("Object.getPrototypeOf(this)");
            MapObject(MAX_LEVEL - 1, dat);
                         
            System.out.println("========================================================");
        }
    }
}
