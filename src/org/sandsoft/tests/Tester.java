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
package org.sandsoft.tests;

import java.util.ArrayList;
import java.util.Comparator;
import netscape.javascript.JSObject;
import org.sandsoft.acefx.util.Commons;

/**
 *
 * @author dipu
 */
public class Tester {

    static final Comparator<String> STRING_COMP = new Comparator<String>() {
        @Override
        public int compare(String t, String t1) {
            return t.compareTo(t1);
        }
    };

    static final String GETTER_SETTER_DATA
            = "\n/**\n"
            + " * Gets the $VAR property.\n"
            + " * @return value of the $VAR property.\n"
            + " */\n"
            + "public $TYPE get$VAR() {\n"
            + "    return $VAR0;\n"
            + "}\n"
            + "/**\n"
            + " * Sets the $VAR property.\n"
            + " * @param val value of $VAR property.\n"
            + " */\n"
            + "public void set$VAR($TYPE val) {\n"
            + "	   $VAR0 = val;\n"
            + "}\n";

    /**
     * Pass the following GETTER_SETTER_DATA as parameter, <br/>
     * <code>
     * private boolean mGlobal;
     * boolean mIgnoreCase;
     *
     * </code> <br/>
     * Output will be auto generated getter and setter method. To make it work
     * put a dummy character in front of the variable name.
     *
     * @param param variables
     * @return
     */
    public static String getterAndSetter(String param) {
        try {
            StringBuilder sb = new StringBuilder();
            for (String s : param.split(";")) {
                String[] str = s.trim().split(" ");
                String var = str[str.length - 1];
                String type = str[str.length - 2];
                String out = GETTER_SETTER_DATA
                        .replace("$TYPE", type)
                        .replace("$VAR0", var)
                        .replace("$VAR", var.substring(1));
                sb.append(out).append("\n");
            }

            return sb.toString();
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

    public static String ModeListGenerator() {
        try {
            StringBuilder sb = new StringBuilder();
            ArrayList<String> support = new ArrayList<>();
            ArrayList<String> modenames = new ArrayList<>();

            String text = org.apache.commons.io.IOUtils.toString(
                    org.sandsoft.acefx.AceEditor.class.getResourceAsStream("ace/ext-modelist.js"));
            int ind = text.indexOf("var supportedModes");
            String whole = text.substring(text.indexOf("{", ind) + 1, text.indexOf("};", ind));
            whole = whole.replace("\r", " ").replace("\n", " ").replace("  ", " ");

            for (String line : whole.split(",")) {
                if (line.isEmpty() || !line.contains(":") || !line.contains("[")) {
                    continue;
                }

                String name = line.substring(0, line.indexOf(":")).trim();
                String data = line.substring(line.indexOf("\"") + 1, line.lastIndexOf("\""));

                modenames.add(name);
                sb.append("public static final ModeData ").append(name);
                sb.append(" = new ModeData(\"").append(name.replace("_", " ")).append("\", ");
                sb.append("\"ace/mode/").append(name.toLowerCase()).append("\""); //alias                
                sb.append(", \"").append(data).append("\");\n");
            }

            support.sort(STRING_COMP);

            int cnt = 1;
            sb.append("\npublic static final ModeData[] SUPPORTED_MODES = {\n");
            for (String key : modenames) {
                if (cnt++ % 8 == 0) {
                    sb.append("\n");
                }
                sb.append(key).append(", ");
            }
            sb.append("\n};\n");

            return sb.toString();

        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

    public static String ThemeListGenerator() {
        try {
            StringBuilder sb = new StringBuilder();
            ArrayList<String> names = new ArrayList<>();
            ArrayList<String> alias = new ArrayList<>();

            String text = org.apache.commons.io.IOUtils.toString(
                    org.sandsoft.acefx.AceEditor.class.getResourceAsStream("ace/ext-themelist.js"));
            int ind = text.indexOf("var themeData");
            String whole = text.substring(text.indexOf("[", ind) + 1, text.indexOf("];", ind));
            whole = whole.replace("\r", " ").replace("\n", " ").replace("  ", " ");

            for (String line : whole.split("],")) {
                if (line.isEmpty() || !line.contains("[")) {
                    continue;
                }
                String tt = line.replace("[", "").replace("]", "").replace("\"", "").trim();
                String[] data = tt.split(",");

                String name;
                String als = "ace/theme/";
                boolean dark = false;
                if (data.length > 1) {
                    name = data[0].trim();
                    als += data[1].trim();
                    dark = data[2].trim().contains("dark");
                } else {
                    name = data[0].trim();
                    als += name.toLowerCase().replace(" ", "_");
                }

                String var = name.replace(" ", "_");
                sb.append(" public static final ThemeData " + var
                        + " = new ThemeData(\"" + name + "\", "
                        + "\"" + als + "\", "
                        + (dark ? "true" : "false")
                        + ");\n");
                names.add(var);
                alias.add(als);
            }

            names.sort(STRING_COMP);
            alias.sort(STRING_COMP);

            int cnt = 1;
            sb.append("\npublic static final ThemeData[] SUPPORTED_THEMES = { \n");
            for (String term : names) {
                sb.append(term).append(", ");
                if (cnt++ % 8 == 0) {
                    sb.append("\n");
                }
            }
            sb.append("};\n");

            sb.append("\npublic static final String[] ALIAS_LIST = { \n");
            for (String term : alias) {
                sb.append("\"").append(term).append("\", \n");
            }
            sb.append("};\n");

            return sb.toString();

        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

    public static final int MAX_LEVEL = 2;

    public static String MapObject(Object obj) {
        try {
            StringBuilder sb = new StringBuilder();
            //print all objects
            MapObject(sb, 0, MAX_LEVEL, "this", obj);
            //prints all the methods
            if (obj instanceof JSObject) {
                MapObject(sb, 0, MAX_LEVEL, "this", ((JSObject) obj).eval("Object.getPrototypeOf(this)"));
            }
            return sb.toString();
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

    public static void MapObject(StringBuilder sb, int level, int maxlev, String pre, Object obj) {
        if (level == 0) {
            sb.append("========================================================\n");
        }
        //get tab size
        String tab = "";
        for (int i = 0; i < level; ++i) {
            tab += "    ";
        }
        //check if bottom object reached
        if (!(obj instanceof JSObject)) {
            sb.append(tab).append(obj).append("\n");
            return;
        }
        //check if limit reached.
        if (level >= maxlev) {
            return;
        }
        //gets and sorts all the property members
        JSObject dat = (JSObject) obj;
        ArrayList<String> als = Commons.getAllProperties(dat);
        //print all members recursively
        for (String str : als) {
            sb.append(String.format("%s[%d]%s.%s", tab, level + 1, pre, str));
            Object val = dat.getMember(str);
            if (val instanceof JSObject) {
                sb.append("\n");
                MapObject(sb, level + 1, maxlev, pre + "." + str, dat.getMember(str));
            } else {
                sb.append(" = ").append(val).append("\n");
            }
        }
        if (level == 0) {
            sb.append("========================================================\n");
        }
    }
}
