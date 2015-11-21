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
package com.sandsoft.tests;

import com.sandsoft.acefx.model.ThemeData;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 *
 * @author dipu
 */
public class TesterFunc {

    final static Comparator<String> STRING_COMP = new Comparator<String>() {
        @Override
        public int compare(String t, String t1) {
            return t.compareTo(t1);
        }
    };

    public static String ModeListGenerator() {
        try {
            StringBuilder sb = new StringBuilder();
            ArrayList<String> support = new ArrayList<>();
            ArrayList<String> modenames = new ArrayList<>();
            String whole = org.apache.commons.io.IOUtils.toString(
                    Main.class.getResourceAsStream("modelist.txt"));
            for (String tt : whole.split(",")) {
                String line = tt.trim().replace("\t", "").replace(" ", "");
                if (line.isEmpty() || !line.contains(":") || !line.contains("[")) {
                    continue;
                }
                String pre = "public static final String";
                String name = line.substring(0, line.indexOf(":"));
                modenames.add(name);
                String data = line.substring(line.indexOf("\"") + 1, line.lastIndexOf("\""));
                String comment = "";
                for (String term : data.split("\\|")) {
                    support.add(term);
                    sb.append(comment + pre + " " + name + " = \"ace/mode/" + term + "\";\n");
                    comment = "//";
                }
            }

            modenames.sort(STRING_COMP);
            support.sort(STRING_COMP);

            int cnt = 1;
            sb.append("\npublic static final String[] SUPPORTED_MODES = { \n");
            for (String term : modenames) {
                sb.append("\"" + term + "\", ");
                if (cnt++ % 8 == 0) {
                    sb.append("\n");
                }
            }
            sb.append("};\n");

            cnt = 1;
            sb.append("\n\npublic static final String[] SUPPORTED_EXTS = { \n");
            for (String term : support) {
                sb.append("\"" + term + "\", ");
                if (cnt++ % 8 == 0) {
                    sb.append("\n");
                }
            }
            sb.append("};\n");

//            System.out.println("-------------");
//            System.out.println(sb);
            return sb.toString();

        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

    public static String ThemeListGenerator(ArrayList<ThemeData> list) {
        try {
            StringBuilder sb = new StringBuilder();
            ArrayList<String> names = new ArrayList<>();
            ArrayList<String> alias = new ArrayList<>();
            for (ThemeData td : list) {
                String name = td.getName().replace(" ", "_");
                sb.append(" public static final ThemeData " + name
                        + " = new ThemeData(\"" + td.getName() + "\", "
                        + "\"" + td.getAlias() + "\", "
                        + (td.isDark() ? "true" : "false")
                        + ");\n");
                names.add(name);
                alias.add(td.getAlias());
            }

            names.sort(STRING_COMP);
            alias.sort(STRING_COMP);

            int cnt = 1;
            sb.append("\npublic static final ThemeData[] SUPPORTED_THEMES = { \n");
            for (String term : names) {
                sb.append(term + ", ");
                if (cnt++ % 8 == 0) {
                    sb.append("\n");
                }
            }
            sb.append("};\n");

            sb.append("\npublic static final String[] ALIAS_LIST = { \n");
            for (String term : alias) {
                sb.append("\"" + term + "\", \n");
            }
            sb.append("};\n");

//            System.out.println("-------------");
//            System.out.println(sb);
            return sb.toString();

        } catch (Exception ex) {
            return ex.getMessage();
        }
    }
}
