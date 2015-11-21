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
public class SearchOptions {

    private boolean mWrap;
    private boolean mBackwards;
    private boolean mCaseSensitive;
    private String mNeedle;
    private boolean mUseRE;
    private boolean mSkipCurrent;
    private boolean mWholeWord;
    private RegExp mRE;
    private Range mStart;

    public SearchOptions() {
        mNeedle = "";
        mWrap = true;
        mBackwards = false;
        mCaseSensitive = false;
        mSkipCurrent = false;
        mUseRE = false;
        mRE = new RegExp();
        mStart = new Range();
    }

    public SearchOptions(JSObject dat) {
        
        Object start = dat.getMember("start");
        Object re = dat.getMember("re");
        Object caseSens = dat.getMember("caseSensitive");
        Object backward = dat.getMember("backwards");
        Object wholeWord = dat.getMember("wholeWord");
        Object skipcurrent = dat.getMember("skipCurrent");
        Object wrap = dat.getMember("wrap");
        Object regExp = dat.getMember("regExp");
        
        mNeedle = (String) dat.getMember("needle");
        mWrap = (wrap instanceof Boolean) ? (boolean) wrap : false;
        mUseRE = (regExp instanceof Boolean) ? (boolean) regExp : false;
        mBackwards = (backward instanceof Boolean) ? (boolean) backward : false;
        mWholeWord = (wholeWord instanceof Boolean) ? (boolean) wholeWord : false;
        mCaseSensitive = (caseSens instanceof Boolean) ? (boolean) caseSens : false;
        mSkipCurrent = (skipcurrent instanceof Boolean) ? (boolean) skipcurrent : false;
        mStart = (start instanceof JSObject) ? new Range((JSObject) start) : null;
        mRE = (re instanceof JSObject) ? new RegExp((JSObject) re) : null;
    }

    @Override
    public String toString() {
        return String.format(
                "{backwards:%s, caseSensitive:%s, needle:'%s', re:%s,"
                + "regExp:%s, skipCurrent:%s, start:%s, wholeWord:%s, wrap:%s }",
                mBackwards, mCaseSensitive, mNeedle, mRE,
                mUseRE, mSkipCurrent, mStart, mWholeWord, mWrap);
    }

    /**
     * Gets the Wrap property.
     *
     * @return value of the Wrap property.
     */
    public boolean getWrap() {
        return mWrap;
    }

    /**
     * Sets the Wrap property.
     *
     * @param val value of Wrap property.
     */
    public void setWrap(boolean val) {
        mWrap = val;
    }

    /**
     * Gets the Backwards property.
     *
     * @return value of the Backwards property.
     */
    public boolean getBackwards() {
        return mBackwards;
    }

    /**
     * Sets the Backwards property.
     *
     * @param val value of Backwards property.
     */
    public void setBackwards(boolean val) {
        mBackwards = val;
    }

    /**
     * Gets the CaseSensitive property.
     *
     * @return value of the CaseSensitive property.
     */
    public boolean getCaseSensitive() {
        return mCaseSensitive;
    }

    /**
     * Sets the CaseSensitive property.
     *
     * @param val value of CaseSensitive property.
     */
    public void setCaseSensitive(boolean val) {
        mCaseSensitive = val;
    }

    /**
     * Gets the Needle property.
     *
     * @return value of the Needle property.
     */
    public String getNeedle() {
        return mNeedle;
    }

    /**
     * Sets the Needle property.
     *
     * @param val value of Needle property.
     */
    public void setNeedle(String val) {
        mNeedle = val;
    }

    /**
     * Gets the UseRE property.
     *
     * @return value of the UseRE property.
     */
    public boolean getUseRE() {
        return mUseRE;
    }

    /**
     * Sets the UseRE property.
     *
     * @param val value of UseRE property.
     */
    public void setUseRE(boolean val) {
        mUseRE = val;
    }

    /**
     * Gets the SkipCurrent property.
     *
     * @return value of the SkipCurrent property.
     */
    public boolean getSkipCurrent() {
        return mSkipCurrent;
    }

    /**
     * Sets the SkipCurrent property.
     *
     * @param val value of SkipCurrent property.
     */
    public void setSkipCurrent(boolean val) {
        mSkipCurrent = val;
    }

    /**
     * Gets the WholeWord property.
     *
     * @return value of the WholeWord property.
     */
    public boolean getWholeWord() {
        return mWholeWord;
    }

    /**
     * Sets the WholeWord property.
     *
     * @param val value of WholeWord property.
     */
    public void setWholeWord(boolean val) {
        mWholeWord = val;
    }

    /**
     * Gets the RE property.
     *
     * @return value of the RE property.
     */
    public RegExp getRE() {
        return mRE;
    }

    /**
     * Sets the RE property.
     *
     * @param val value of RE property.
     */
    public void setRE(RegExp val) {
        mRE = val;
    }

    /**
     * Gets the Start property.
     *
     * @return value of the Start property.
     */
    public Range getStart() {
        return mStart;
    }

    /**
     * Sets the Start property.
     *
     * @param val value of Start property.
     */
    public void setStart(Range val) {
        mStart = val;
    }

}
