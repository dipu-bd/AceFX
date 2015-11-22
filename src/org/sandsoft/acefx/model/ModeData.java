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
package org.sandsoft.acefx.model;

import java.util.Arrays;
import javafx.stage.FileChooser;

/**
 *
 * @author dipu
 */
public class ModeData {

    private String mName;
    private String mAlias;
    private String mRegex;

    public ModeData(String name, String alias, String regex) {
        mName = name;
        mAlias = alias;
        mRegex = regex;
    }

    public String getName() {
        return mName;
    }

    public String getAlias() {
        return mAlias;
    }

    public String getSupportedExtensions() {
        return mRegex;
    }

    public boolean supportsFile(String fileName) {
        fileName = fileName.substring(fileName.lastIndexOf(".") + 1);
        fileName = fileName.toLowerCase();
        return fileName.matches(mRegex);
    }

    public FileChooser.ExtensionFilter getExtensionFilter() {
        String[] data = mRegex.split("\\|");
        for (int i = 0; i < data.length; ++i) {
            data[i] = "*." + data[i];
        }
        return new FileChooser.ExtensionFilter(mName, Arrays.asList(data));
    }

    @Override
    public String toString() {
        return String.format("%s", mName);
    }
}
