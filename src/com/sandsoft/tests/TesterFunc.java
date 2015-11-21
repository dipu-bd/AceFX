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

    public static void ModeListGenerator() {
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

            Comparator<String> comp = new Comparator<String>() {
                @Override
                public int compare(String t, String t1) {
                    return t.compareTo(t1);
                }
            };
            modenames.sort(comp);
            support.sort(comp);

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

            System.out.println("-------------");
            System.out.println(sb);

        } catch (Exception ex) {
        }
    }
}
