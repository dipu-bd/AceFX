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

import netscape.javascript.JSObject;

/**
 *
 * @author dipu
 */
public class RegExp {

    private boolean mGlobal;
    private boolean mIgnoreCase;
    private int mLastIndex;
    private boolean mMultiline;
    private String mSource;

    public RegExp() {
        mSource = "";
        mGlobal = true;
        mIgnoreCase = true;
        mLastIndex = 0;
        mMultiline = false;
    }

    public RegExp(boolean global, boolean ignoreCase,
            int lastIndex, boolean multiline, String source) {
        mGlobal = global;
        mIgnoreCase = ignoreCase;
        mLastIndex = lastIndex;
        mMultiline = multiline;
        mSource = source;
    }

    public RegExp(JSObject dat) {
        mGlobal = (boolean) dat.getMember("global");
        mIgnoreCase = (boolean) dat.getMember("ignoreCase");
        mLastIndex = (int) dat.getMember("lastIndex");
        mMultiline = (boolean) dat.getMember("multiline");
        mSource = (String) dat.getMember("source");
    }

    @Override
    public String toString() {
        return String.format("{global:%s, ignoreCase:%s, "
                + "lastIndex:%d, multiline:%s, source:'s'}",
                mGlobal, mIgnoreCase, mLastIndex, mMultiline, mSource);
    }

    /**
     * Gets the Global property. 
     * @return value of the Global property.
     */
    public boolean getGlobal() {
        return mGlobal;
    }

    /**
     * Sets the Global property.
     *
     * @param val value of Global property.
     */
    public void setGlobal(boolean val) {
        mGlobal = val;
    }

    /**
     * Gets the IgnoreCase property. 
     * @return value of the IgnoreCase property.
     */
    public boolean getIgnoreCase() {
        return mIgnoreCase;
    }

    /**
     * Sets the IgnoreCase property.
     *
     * @param val value of IgnoreCase property.
     */
    public void setIgnoreCase(boolean val) {
        mIgnoreCase = val;
    }

    /**
     * Gets the LastIndex property. 
     * @return value of the LastIndex property.
     */
    public int getLastIndex() {
        return mLastIndex;
    }

    /**
     * Sets the LastIndex property.
     *
     * @param val value of LastIndex property.
     */
    public void setLastIndex(int val) {
        mLastIndex = val;
    }

    /**
     * Gets the MultiLine property. 
     * @return value of the MultiLine property.
     */
    public boolean getMultiline() {
        return mMultiline;
    }

    /**
     * Sets the MultiLine property.
     *
     * @param val value of MultiLine property.
     */
    public void setMultiline(boolean val) {
        mMultiline = val;
    }

    /**
     * Gets the Source property.
     * @return value of the Source property.
     */
    public String getSource() {
        return mSource;
    }

    /**
     * Sets the Source property.
     *
     * @param val value of Source property.
     */
    public void setSource(String val) {
        mSource = val;
    }

}
