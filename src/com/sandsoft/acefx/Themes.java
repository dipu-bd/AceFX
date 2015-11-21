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
package com.sandsoft.acefx;

import com.sandsoft.acefx.model.ThemeData;

/**
 * Contains some pre-defined themes for ace editor.
 *
 * @author Sudipto Chandra.
 */
public final class Themes {

    public static final ThemeData Chrome = new ThemeData("Chrome", "ace/theme/chrome", false);
    public static final ThemeData Clouds = new ThemeData("Clouds", "ace/theme/clouds", false);
    public static final ThemeData Crimson_Editor = new ThemeData("Crimson Editor", "ace/theme/crimson_editor", false);
    public static final ThemeData Dawn = new ThemeData("Dawn", "ace/theme/dawn", false);
    public static final ThemeData Dreamweaver = new ThemeData("Dreamweaver", "ace/theme/dreamweaver", false);
    public static final ThemeData Eclipse = new ThemeData("Eclipse", "ace/theme/eclipse", false);
    public static final ThemeData GitHub = new ThemeData("GitHub", "ace/theme/github", false);
    public static final ThemeData IPlastic = new ThemeData("IPlastic", "ace/theme/iplastic", false);
    public static final ThemeData Solarized_Light = new ThemeData("Solarized Light", "ace/theme/solarized_light", false);
    public static final ThemeData TextMate = new ThemeData("TextMate", "ace/theme/textmate", false);
    public static final ThemeData Tomorrow = new ThemeData("Tomorrow", "ace/theme/tomorrow", false);
    public static final ThemeData XCode = new ThemeData("XCode", "ace/theme/xcode", false);
    public static final ThemeData Kuroir = new ThemeData("Kuroir", "ace/theme/kuroir", false);
    public static final ThemeData KatzenMilch = new ThemeData("KatzenMilch", "ace/theme/katzenmilch", false);
    public static final ThemeData SQL_Server = new ThemeData("SQL Server", "ace/theme/sqlserver", false);
    public static final ThemeData Ambiance = new ThemeData("Ambiance", "ace/theme/ambiance", true);
    public static final ThemeData Chaos = new ThemeData("Chaos", "ace/theme/chaos", true);
    public static final ThemeData Clouds_Midnight = new ThemeData("Clouds Midnight", "ace/theme/clouds_midnight", true);
    public static final ThemeData Cobalt = new ThemeData("Cobalt", "ace/theme/cobalt", true);
    public static final ThemeData idle_Fingers = new ThemeData("idle Fingers", "ace/theme/idle_fingers", true);
    public static final ThemeData krTheme = new ThemeData("krTheme", "ace/theme/kr_theme", true);
    public static final ThemeData Merbivore = new ThemeData("Merbivore", "ace/theme/merbivore", true);
    public static final ThemeData Merbivore_Soft = new ThemeData("Merbivore Soft", "ace/theme/merbivore_soft", true);
    public static final ThemeData Mono_Industrial = new ThemeData("Mono Industrial", "ace/theme/mono_industrial", true);
    public static final ThemeData Monokai = new ThemeData("Monokai", "ace/theme/monokai", true);
    public static final ThemeData Pastel_on_dark = new ThemeData("Pastel on dark", "ace/theme/pastel_on_dark", true);
    public static final ThemeData Solarized_Dark = new ThemeData("Solarized Dark", "ace/theme/solarized_dark", true);
    public static final ThemeData Terminal = new ThemeData("Terminal", "ace/theme/terminal", true);
    public static final ThemeData Tomorrow_Night = new ThemeData("Tomorrow Night", "ace/theme/tomorrow_night", true);
    public static final ThemeData Tomorrow_Night_Blue = new ThemeData("Tomorrow Night Blue", "ace/theme/tomorrow_night_blue", true);
    public static final ThemeData Tomorrow_Night_Bright = new ThemeData("Tomorrow Night Bright", "ace/theme/tomorrow_night_bright", true);
    public static final ThemeData Tomorrow_Night_80s = new ThemeData("Tomorrow Night 80s", "ace/theme/tomorrow_night_eighties", true);
    public static final ThemeData Twilight = new ThemeData("Twilight", "ace/theme/twilight", true);
    public static final ThemeData Vibrant_Ink = new ThemeData("Vibrant Ink", "ace/theme/vibrant_ink", true);

    public static final ThemeData[] SUPPORTED_THEMES = {
        Ambiance, Chaos, Chrome, Clouds, Clouds_Midnight, Cobalt, Crimson_Editor, Dawn,
        Dreamweaver, Eclipse, GitHub, IPlastic, KatzenMilch, Kuroir, Merbivore, Merbivore_Soft,
        Mono_Industrial, Monokai, Pastel_on_dark, SQL_Server, Solarized_Dark, Solarized_Light, Terminal, TextMate,
        Tomorrow, Tomorrow_Night, Tomorrow_Night_80s, Tomorrow_Night_Blue, Tomorrow_Night_Bright, Twilight, Vibrant_Ink, XCode,
        idle_Fingers, krTheme
    };

    public static final String[] ALIAS_LIST = {
        "ace/theme/ambiance",
        "ace/theme/chaos",
        "ace/theme/chrome",
        "ace/theme/clouds",
        "ace/theme/clouds_midnight",
        "ace/theme/cobalt",
        "ace/theme/crimson_editor",
        "ace/theme/dawn",
        "ace/theme/dreamweaver",
        "ace/theme/eclipse",
        "ace/theme/github",
        "ace/theme/idle_fingers",
        "ace/theme/iplastic",
        "ace/theme/katzenmilch",
        "ace/theme/kr_theme",
        "ace/theme/kuroir",
        "ace/theme/merbivore",
        "ace/theme/merbivore_soft",
        "ace/theme/mono_industrial",
        "ace/theme/monokai",
        "ace/theme/pastel_on_dark",
        "ace/theme/solarized_dark",
        "ace/theme/solarized_light",
        "ace/theme/sqlserver",
        "ace/theme/terminal",
        "ace/theme/textmate",
        "ace/theme/tomorrow",
        "ace/theme/tomorrow_night",
        "ace/theme/tomorrow_night_blue",
        "ace/theme/tomorrow_night_bright",
        "ace/theme/tomorrow_night_eighties",
        "ace/theme/twilight",
        "ace/theme/vibrant_ink",
        "ace/theme/xcode"
    };

    /**
     * Gets the ThemeData object by theme name.
     *
     * @param name Name of the theme
     * @return A null value is returned if no theme found by the given name.
     */
    public static ThemeData getThemeByName(String name) {
        for (ThemeData td : SUPPORTED_THEMES) {
            if (td.getAlias().equals(name)) {
                return td;
            }
        }
        return null;
    }

    /**
     * Gets the ThemeData object by theme alias.
     *
     * @param alias Alias of the theme like <code>"ace/theme/ambiance"</code>
     * @return A null value is returned if no theme found by the given alias.
     */
    public static ThemeData getThemeByAlias(String alias) {
        for (ThemeData td : SUPPORTED_THEMES) {
            if (td.getAlias().equals(alias)) {
                return td;
            }
        }
        return null;
    }

    /**
     * Checks whether the theme is of dark background.
     *
     * @param alias Full alias of the theme.
     * @return
     */
    public static boolean isDark(String alias) {
        ThemeData td = getThemeByName(alias);
        if (td == null) {
            return false;
        } else {
            return td.isDark();
        }
    }

    /**
     * Checks whether the theme is of light background.
     *
     * @param alias Full alias of the theme.
     * @return
     */
    public static boolean isLight(String alias) {
        ThemeData td = getThemeByName(alias);
        if (td == null) {
            return false;
        } else {
            return !td.isDark();
        }
    }

}
