/* 
 * Copyright 2015 Sudipto Chandra. 
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

import java.util.HashMap;
import java.util.Map;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 *
 * Author Sudipto Chandra
 */
public class AceFX extends BorderPane {

    private static final String DEFAULT_ACE_PATH = "http://dipu-bd.github.io/AceFX";

    //<editor-fold defaultstate="collapsed" desc="Useful AceFX Commands">
    public enum EditorCommand {

        /**
         * Input: theme name
         */
        SetTheme,
        /**
         * Output: string
         */
        GetTheme,
        /**
         * Input: language mode name
         */
        SetMode,
        /**
         * Input: formated content
         */
        SetContent,
        /**
         * Output: String
         */
        GetContent,
        /**
         * Output: String
         */
        GetSelectedText,
        /**
         * Input: formated content
         */
        InsertAtCursor,
        /**
         * Output: ?
         */
        GetCursor,
        /**
         * Input: integer
         */
        SetTabSize,
        /**
         * Input: integer
         */
        GoToLine,
        /**
         * Output: integer
         */
        GetNumberOfLines,
        /**
         * Input true or false
         */
        SetUseSoftTab,
        /**
         * Input: integer
         */
        SetFontSize,
        /**
         * Input true or false
         */
        SetUseWrapMode,
        /**
         * Input true or false
         */
        SetHighlightMode,
        /**
         * Input true or false
         */
        SetMargin,
        /**
         * Input true or false
         */
        SetReadOnly,
        /**
         * Input: <br/>
         * needle, backwards, wrap, caseSenitive, wholeWord, regExp. <br/>
         * needle: formated string.<br/>
         * rest of parameters: true or false.
         */
        Find,
        /**
         * No input output
         */
        FindNext,
        /**
         * No input output
         */
        FindPrevious,
        /**
         * Only use after a find command. Input: formated string.
         */
        Replace,
        /**
         * Only use after a find command. Input: formated string.
         */
        ReplaceAll,
        /**
         * No input output
         */
        Resize,
        /**
         * Empty Command
         */
        DUMMY
    }

    private static final Map<EditorCommand, String> mEditorScripts;

    static {
        Map<EditorCommand, String> map = mEditorScripts = new HashMap<>();
        map.put(EditorCommand.SetTheme,
                "editor.setTheme(\"ace/theme/%s\");"); //theme name
        map.put(EditorCommand.GetTheme,
                "editor.getTheme();");
        map.put(EditorCommand.SetMode,
                "editor.getSession().setMode(\"ace/mode/%s\");"); //language mode
        map.put(EditorCommand.SetContent,
                "editor.setValue(\"%s\", 1);"); //formated string content
        map.put(EditorCommand.GetContent,
                "editor.getValue();");
        map.put(EditorCommand.GetSelectedText,
                "editor.session.getTextRange(editor.getSelectionRange());");
        map.put(EditorCommand.InsertAtCursor,
                "editor.insert(\"%s\");"); //formated string content
        map.put(EditorCommand.GetCursor,
                "editor.selection.getCursor();");
        map.put(EditorCommand.SetTabSize,
                "editor.getSession().setTabSize(%d);"); //tab size
        map.put(EditorCommand.GoToLine,
                "editor.gotoLine(%d);"); //line number
        map.put(EditorCommand.GetNumberOfLines,
                "editor.session.getLength();");
        map.put(EditorCommand.SetUseSoftTab,
                "editor.getSession().setUseSoftTabs(%s);"); // true or false
        map.put(EditorCommand.SetFontSize,
                "document.getElementById('editor').style.fontSize='%dpx';"); //size in pixel
        map.put(EditorCommand.SetUseWrapMode,
                "editor.getSession().setUseWrapMode(%s);"); //true or false
        map.put(EditorCommand.SetHighlightMode,
                "editor.setHighlightActiveLine(%s);"); //true or false
        map.put(EditorCommand.SetMargin,
                "editor.setShowPrintMargin(%s);"); //true or false
        map.put(EditorCommand.SetReadOnly,
                "editor.setReadOnly(%s);");  // true or false
        map.put(EditorCommand.Find,
                "editor.find(\"%s\",{n" //formated needle string
                + "    backwards: %s,\n" //true or false
                + "    wrap: %s,\n" //true or false
                + "    caseSensitive: %s,\n" //true or false
                + "    wholeWord: %s,\n" // true or false
                + "    regExp: %s\n" //true or false
                + "});\n");
        map.put(EditorCommand.FindNext,
                "editor.findNext();");
        map.put(EditorCommand.FindPrevious,
                "editor.findPrevious();");
        map.put(EditorCommand.Replace,
                "editor.replace('%s');"); //formated string
        map.put(EditorCommand.ReplaceAll,
                "editor.replaceAll('%s');"); //formated string
        map.put(EditorCommand.Resize, "editor.resize();"); //dummy
        map.put(EditorCommand.DUMMY, ""); //dummy
    }
//</editor-fold>

    /**
     * Creates a new editor with default AcePath
     */
    public AceFX() {
        initialize();
    }

    /**
     * Constructs a new Editor with given AcePath
     *
     * @param acepath where the <code>editor.html</code> is.
     */
    public AceFX(String acepath) {
        AcePath = acepath;
        initialize();
    }

    //web view to use to show ace editor
    private WebView webView;
    private WebEngine webEngine;
    private EditorCore editor;
    private String AcePath = DEFAULT_ACE_PATH;

    private void initialize() {
        //create design
        webView = new WebView();
        webView.setMaxWidth(Double.MAX_VALUE);
        webView.setMaxHeight(Double.MAX_VALUE);

        BorderPane.setAlignment(webView, Pos.CENTER);
        this.setCenter(webView);
        
        //init toolbar
        

        //init editor
        webEngine = webView.getEngine();
        webEngine.load(AcePath);
        
        editor = new EditorCore(webEngine);
        webEngine.getLoadWorker().stateProperty().addListener(
                (ObservableValue<? extends State> observable, State oldValue, State newValue) -> {
                    if (newValue == State.SUCCEEDED) {
                        editor.setFontSize(16);
                        editor.setTheme("twilight");
                    }
                });
    }

    /**
     * Sets the given path as AcePath location. If illegal default path is used.
     *
     * @param path Path where <code>editor.html</code> file is located.
     */
    public void setAcePath(String path) {
        try {
            webEngine.load(path);
            AcePath = path;
        } catch (Exception ex) {
            webEngine.load(AcePath);
        }
    }

    /**
     * formats a content into a string passable inside quote.
     *
     * @param content text to format
     * @return formated content
     */
    public String formatText(String content) {
        return content
                .replace("\\", "\\\\") //replace backslash
                .replace("\"", "\\\"") //replace quote
                .replace("\r", "") //replace return
                .replace("\n", "\\n") //replace new line
                .replace("\t", "\\t") //replace tab 
                ;
    }

    /**
     * Checks if the editor is ready for interaction.
     *
     * @return True if worker is successfully loaded.
     */
    public boolean isReady() {
        return (webEngine.getLoadWorker().getState() == State.SUCCEEDED);
    }

    /**
     * Executes a java-script command.
     *
     * @param script command to execute.
     * @return returned object by execution.
     */
    public Object executeScript(String script) {
        if (isReady()) {
            return webEngine.executeScript(script);
        }
        return null;
    }

    /**
     * Set a theme in the editor.
     *
     * @param themeName Name of the theme to set.
     */
    public void setTheme(String themeName) {
        executeScript(String.format(mEditorScripts.get(EditorCommand.SetTheme), themeName));
    }

    /**
     * Get the current cursor line and column.
     *
     * @return
     */
    public String getTheme() {
        String res = (String) executeScript(String.format(mEditorScripts.get(EditorCommand.GetTheme)));
        return res.substring(res.lastIndexOf("/") + 1);
    }

    /**
     * Setting the Programming Language Mode.
     *
     * @param mode
     */
    public void setLanguage(String mode) {
        executeScript(String.format(mEditorScripts.get(EditorCommand.SetMode), mode));
    }

    /**
     * Set content.
     *
     * @param content
     */
    public void setContent(String content) {
        executeScript(String.format(mEditorScripts.get(EditorCommand.SetContent), formatText(content)));
    }

    /**
     * Get content.
     *
     * @return
     */
    public String getContent() {
        return (String) executeScript(String.format(mEditorScripts.get(EditorCommand.GetContent)));
    }

    /**
     * Get selected text.
     *
     * @return
     */
    public String getSelectedText() {
        return (String) executeScript(String.format(mEditorScripts.get(EditorCommand.GetSelectedText)));
    }

    /**
     * Insert at cursor.
     *
     * @param content
     */
    public void insertAtCursor(String content) {
        executeScript(String.format(mEditorScripts.get(EditorCommand.InsertAtCursor), formatText(content)));
    }

    /**
     * Go to a line.
     *
     * @param lineNumber
     */
    public void goToLine(int lineNumber) {
        executeScript(String.format(mEditorScripts.get(EditorCommand.GoToLine), lineNumber));
    }

    /**
     * Get total number of lines.
     *
     * @return total number of lines.
     */
    public int getNumberOfLines() {
        if (isReady()) {
            return (int) executeScript(String.format(mEditorScripts.get(EditorCommand.GetNumberOfLines)));
        }
        return -1;
    }

    /**
     * Set the default tab size.
     *
     * @param siz Tab size in pixels
     */
    public void setTabSize(int siz) {
        executeScript(String.format(mEditorScripts.get(EditorCommand.SetTabSize), siz));
    }

    /**
     * Use soft tabs
     *
     * @param val true or false
     */
    public void setUseSoftTab(boolean val) {
        String p = val ? "true" : "false";
        executeScript(String.format(mEditorScripts.get(EditorCommand.SetUseSoftTab), p));
    }

    /**
     * Set the font size
     *
     * @param siz Font size in pixels
     */
    public void setFontSize(int siz) {
        executeScript(String.format(mEditorScripts.get(EditorCommand.SetFontSize), siz));
    }

    /**
     * Toggle word wrapping
     *
     * @param val true or false
     */
    public void setToggleWordWrapping(boolean val) {
        String p = val ? "true" : "false";
        executeScript(String.format(mEditorScripts.get(EditorCommand.SetUseWrapMode), p));
    }

    /**
     * Set line highlighting
     *
     * @param val true or false
     */
    public void setLineHighlighting(boolean val) {
        String p = val ? "true" : "false";
        executeScript(String.format(mEditorScripts.get(EditorCommand.SetHighlightMode), p));
    }

    /**
     * Set the print margin visibility
     *
     * @param val true or false
     */
    public void setPrintMarginVisibility(boolean val) {
        String p = val ? "true" : "false";
        executeScript(String.format(mEditorScripts.get(EditorCommand.SetMargin), p));
    }

    /**
     * Set the editor to read-only
     *
     * @param val true or false
     */
    public void setReadOnly(boolean val) {
        String p = val ? "true" : "false";
        executeScript(String.format(mEditorScripts.get(EditorCommand.SetReadOnly), p));
    }

    /**
     * Ace only resizes itself on window events. If you resize the editor div in
     * another manner, and need Ace to resize, use this.
     */
    public void resize() {
        executeScript(String.format(mEditorScripts.get(EditorCommand.Resize)));
    }

    /**
     *
     * @param needle
     * @param backwards
     * @param wrap
     * @param caseSenitive
     * @param wholeWord
     * @param regExp
     */
    public void find(String needle, boolean backwards, boolean wrap,
            boolean caseSenitive, boolean wholeWord, boolean regExp) {
        executeScript(String.format(
                mEditorScripts.get(EditorCommand.Find),
                formatText(needle),
                (backwards ? "true" : "false"),
                (wrap ? "true" : "false"),
                (caseSenitive ? "true" : "false"),
                (wholeWord ? "true" : "false"),
                (regExp ? "true" : "false")
        ));
    }

    public void findNext() {
        executeScript(String.format(mEditorScripts.get(EditorCommand.FindNext)));
    }

    public void findPrevious() {
        executeScript(String.format(mEditorScripts.get(EditorCommand.FindPrevious)));
    }

    /**
     * Replace current text matches with find task. find() must be called before
     * this.
     *
     * @param text Text to find.
     */
    public void replace(String text) {
        executeScript(String.format(mEditorScripts.get(EditorCommand.Replace), formatText(text)));
    }

    /**
     * Replace all text found by find() task. find() must be called before this.
     *
     * @param text text to replace by.
     */
    public void replaceAll(String text) {
        executeScript(String.format(mEditorScripts.get(EditorCommand.ReplaceAll), formatText(text)));
    }
}
