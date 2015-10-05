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

/**
 *
 * @author dipu
 */
public class GetterSetter {

    public static final String data
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
     * Pass the following data as parameter, <br/>
     * <code>
     * private boolean mGlobal;
     * boolean mIgnoreCase;
     *
     * </code> <br/>
     * Output will be auto generated getter and setter method. To make it work
     * put a dummy character in front of the variable name.
     *
     * @param param variables
     */
    public static String getterAndSetter(String param) {
        String result = "";
        for (String s : param.split(";")) {
            String[] str = s.trim().split(" ");
            String var = str[str.length - 1];
            String type = str[str.length - 2];
            String out = data.replace("$TYPE", type).replace("$VAR0", var).replace("$VAR", var.substring(1)); 
            result += out;
        }
        return result;
    }
}
