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

import org.sandsoft.acefx.AceEditor;

/**
 *
 * @author dipu
 */
public class ThemeData {

    private String mName;
    private String mAlias;
    private boolean mDark;

    /**
     * Creates a new theme data with default value.
     */
    public ThemeData() {
        mName = "Ambiance";
        mAlias = "ace/theme/ambiance";
        mDark = true;
    }

    /**
     * Creates a new theme data. Alias and dark are auto assigned by the name.
     *
     * @param name name of the theme.
     */
    public ThemeData(String name) {
        mName = name;
        mAlias = "ace/theme/" + name.toLowerCase().replace(" ", "_");
        mDark = false;
    }

    /**
     * Creates a new theme data.
     *
     * @param name name of the theme.
     * @param alias the theme path or data required by the editor
     * @param dark true if the theme is of dark; false if light background.
     */
    public ThemeData(String name, String alias, boolean dark) {
        mName = name;
        mAlias = alias;
        mDark = dark;
    }

    /**
     * Sets this theme to an editor.
     *
     * @param editor Editor to set this theme.
     */
    public void setThemeTo(AceEditor editor) {
        editor.getEditor().setTheme(mAlias);
    }

    /**
     * Gets the name of the theme.
     *
     * @return name of the theme.
     */
    public String getName() {
        return mName;
    }

    /**
     * Gets the theme path or data required by the editor to set this theme.
     * Usually it is like this- <code>ace/theme/name</code>
     *
     * @return alias of the theme.
     */
    public String getAlias() {
        return mAlias;
    }

    /**
     * Checks if this theme is of dark or light background.
     *
     * @return true if dark; false otherwise.
     */
    public boolean isDark() {
        return mDark;
    }

    /**
     * Sets the name of the theme.
     *
     * @param name name of the theme.
     */
    public void setName(String name) {
        mName = name;
    }

    /**
     * Sets the theme path or data required by the editor to set this theme.
     * Usually it is like this- <code>ace/theme/name</code>
     *
     * @param alias alias of the theme.
     */
    public void setAlias(String alias) {
        mAlias = alias;
    }

    /**
     * Sets whether the theme is of dark or light background.
     *
     * @param dark true of Dark; false otherwise.
     */
    public void setDark(boolean dark) {
        mDark = dark;
    }

    /**
     * Gets an string representing this theme data.
     *
     * @return string representing this object.
     */
    @Override
    public String toString() {
        return String.format("%s (%s)", mName, (mDark ? "dark" : "light"));
    }
}
