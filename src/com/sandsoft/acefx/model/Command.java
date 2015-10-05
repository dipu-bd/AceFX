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
package com.sandsoft.acefx.model;

import com.sandsoft.acefx.util.Commons;
import netscape.javascript.JSObject;

/**
 *
 * @author dipu
 */
public class Command {

    private String mName;
    private String mBindKeyWin;
    private String mBindKeyMac;

    public void Command() {
    }

    public Command(JSObject dat) {
        mName = (String) dat.getMember("name");
        Object key = dat.getMember("bindKey");
        if (key != null && key instanceof JSObject) {
            mBindKeyWin = (String) ((JSObject) key).getMember("win");
            mBindKeyMac = (String) ((JSObject) key).getMember("mac");
        }
    }

    @Override
    public String toString() {
        return String.format(
                "{name:'%s', bindKey:{win:'%s', mac:'%s'}}",
                mName, mBindKeyWin, mBindKeyMac);
    }

    /**
     * Gets the Name property.
     *
     * @return value of the Name property.
     */
    public String getName() {
        return mName;
    }

    /**
     * Sets the Name property.
     *
     * @param val value of Name property.
     */
    public void setName(String val) {
        mName = val;
    }

    /**
     * Gets the BindKeyWin property.
     *
     * @return value of the BindKeyWin property.
     */
    public String getBindKeyWin() {
        return mBindKeyWin;
    }

    /**
     * Sets the BindKeyWin property.
     *
     * @param val value of BindKeyWin property.
     */
    public void setBindKeyWin(String val) {
        mBindKeyWin = val;
    }

    /**
     * Gets the BindKeyMac property.
     *
     * @return value of the BindKeyMac property.
     */
    public String getBindKeyMac() {
        return mBindKeyMac;
    }

    /**
     * Sets the BindKeyMac property.
     *
     * @param val value of BindKeyMac property.
     */
    public void setBindKeyMac(String val) {
        mBindKeyMac = val;
    }


}
