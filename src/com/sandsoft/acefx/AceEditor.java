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

import com.sandsoft.acefx.model.AceEventProcessor;
import com.sandsoft.acefx.model.Command;
import com.sandsoft.acefx.model.Editor;
import com.sandsoft.acefx.model.UndoManager;
import com.sandsoft.acefx.model.EditSession;
import com.sandsoft.acefx.model.ModeData;
import com.sandsoft.acefx.model.ThemeData;
import com.sandsoft.acefx.util.Commons;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSException;
import netscape.javascript.JSObject;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Sudipto Chandra.
 */
public final class AceEditor extends BorderPane {

    //where ace.js file is saved
    private static final String ACE_PATH = "ace/ace.js";

    //ace controller
    private JSObject mAce;
    //current editor
    private Editor mEditor;
    //file path to save code
    private File mFilePath;
    //if the editor is ready for interaction
    private BooleanProperty mReady;

    //web view where editor is loaded
    private final WebView mWebView;
    //web engine to process java script
    private final WebEngine mWebEngine;

    /**
     * Constructor a new editor.
     */
    public AceEditor() {
        //set default to not ready state
        mReady = new SimpleBooleanProperty(false);

        //setup webview
        mWebView = new WebView();
        mWebView.setMaxWidth(Double.MAX_VALUE);
        mWebView.setMaxHeight(Double.MAX_VALUE);
        mWebView.setPrefWidth(600.0);
        mWebView.setPrefHeight(400.0);
        mWebView.setMinWidth(0.0);
        mWebView.setMinHeight(0.0);
        mWebView.setContextMenuEnabled(false);
        mWebView.visibleProperty().bind(mReady);
        this.setCenter(mWebView);

        //load ace 
        mWebEngine = mWebView.getEngine();
        mWebEngine.loadContent(getHTML());

        // process page loading
        mWebEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>() {
            @Override
            public void changed(ObservableValue<? extends Worker.State> ov, Worker.State t, Worker.State t1) {
                if (mWebEngine.getLoadWorker().getState() == Worker.State.SUCCEEDED) {
                    mAce = (JSObject) mWebEngine.executeScript("ace");
                    JSObject editor = (JSObject) mAce.call("edit", "editor");
                    mEditor = new Editor(editor);
                    setEventCatchers(editor);
                    mReady.set(true);
                }
            }
        }
        );
    }

    /**
     * Gets the HTML content that loads the editor.
     *
     * @return HTML content that loads the editor.
     */
    private String getHTML() {
        String acepath
                = getClass().getResource(ACE_PATH).toExternalForm();
        String html
                = "<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "<head>\n"
                + "  <meta charset=\"UTF-8\">\n"
                + "  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\">\n"
                + "  <title>Ace Editor In JavaFX</title>\n"
                + "  <style type=\"text/css\" media=\"screen\">\n"
                + "    body {\n"
                + "        overflow: hidden;\n"
                + "    }\n"
                + "    #editor {\n"
                + "        margin: 0;\n"
                + "        position: absolute;\n"
                + "        top: 0;\n"
                + "        bottom: 0;\n"
                + "        left: 0;\n"
                + "        right: 0;\n"
                + "    }\n"
                + "  </style>\n"
                + "</head>\n"
                + "<body>\n"
                + "<pre id=\"editor\"></pre>\n" //text to diplay in the editor
                + "<script src=\"%s\" " // %s should be replaced by the path where the ace.js is located.
                + "type=\"text/javascript\" charset=\"utf-8\"></script>\n"
                + "<script>\n"
                + "    var editor = ace.edit(\"editor\"); \n"
                + "    editor.setTheme(\"ace/theme/twilight\");\n"
                + "    editor.getSession().setMode(\"ace/mode/java\");\n"
                + "</script>\n"
                + "</body>\n"
                + "</html>\n";
        return String.format(html, acepath);
    }

    /**
     * Creates event listener. <br/>
     * This uses the 'upcall' feature from java-script to java.
     *
     * @param editor
     */
    private void setEventCatchers(JSObject editor) {
        //set interface object
        editor.setMember("mAceEvent", new AceEventProcessor(this));

        //on editor events
        editor.eval("this.on('blur', function() { editor.mAceEvent.onBlur(); });");
        editor.eval("this.on('change', function(e) { editor.mAceEvent.onChange(e); });");
        editor.eval("this.on('changeSelectionStyle', function(e) { editor.mAceEvent.onChangeSelectionStyle(e); });");
        editor.eval("this.on('changeSession', function(e) { editor.mAceEvent.onChangeSession(e); });");
        editor.eval("this.on('copy', function(e) { editor.mAceEvent.onCopy(e); });");
        editor.eval("this.on('focus', function() { editor.mAceEvent.onFocus(); });");
        editor.eval("this.on('paste', function(e) { editor.mAceEvent.onPaste(e); });");

        //on edit session events
        editor.eval("this.getSession().on('changeAnnotation', function() { editor.mAceEvent.onChangAnnotation(); });");
        editor.eval("this.getSession().on('changeBackMarker', function() { editor.mAceEvent.onChangeBackMarker(); });");
        editor.eval("this.getSession().on('changeBreakpoint', function() { editor.mAceEvent.onChangeBreakpoint(); });");
        editor.eval("this.getSession().on('changeFold', function() { editor.mAceEvent.onChangeFold(); });");
        editor.eval("this.getSession().on('changeFrontMarker', function() { editor.mAceEvent.onChangeFrontMarker(); });");
        editor.eval("this.getSession().on('changeMode', function() { editor.mAceEvent.onChangeMode(); });");
        editor.eval("this.getSession().on('changeOverwrite', function() { editor.mAceEvent.onChangeOverwrite(); });");
        editor.eval("this.getSession().on('changeScrollLeft', function(e) { editor.mAceEvent.onChangeScrollLeft(e); });");
        editor.eval("this.getSession().on('changeScrollTop', function(e) { editor.mAceEvent.onChangeScrollTop(e); });");
        editor.eval("this.getSession().on('changeTabSize', function() { editor.mAceEvent.onChangeTabSize(); });");
        editor.eval("this.getSession().on('changeWrapLimit', function() { editor.mAceEvent.onChangeWrapLimit(); });");
        editor.eval("this.getSession().on('changeWrapMode', function() { editor.mAceEvent.onChangeWrapMode(); });");
        editor.eval("this.getSession().on('tokenizerUpdate', function(e) { editor.mAceEvent.onTokenizerUpadate(e); });");
    }

    /**
     * Checks if the editor is ready for interaction.
     *
     * @return true if ready; false otherwise.
     */
    public boolean isReady() {
        return (mReady.get());
    }

    /**
     * Reloads the whole editor in WebView.
     */
    public void reload() {
        mReady.set(false);
        mWebEngine.loadContent(getHTML());
    }

    /**
     * The property indicates if the editor is loaded and ready for
     * interactions.
     *
     * @return the Ready property.
     */
    public BooleanProperty readyProperty() {
        return mReady;
    }

    /**
     * Executes a script under the current web engine..
     *
     * @param script Script to execute.
     * @return Result of execution
     */
    public Object executeScript(String script) throws JSException {
        return mWebEngine.executeScript(script);
    }

    /**
     * Gets the wrapper class for editor that is associated with this control.
     * It contains various methods to interact with the editor.
     *
     * @return the editor from this control.
     */
    public Editor getEditor() {
        return mEditor;
    }

    /**
     * Gets the wrapper class for edit session that is associated with the
     * editor. It contains various methods to interact with the document under
     * edit.
     *
     * @return the edit session for the editor.
     */
    public EditSession getSession() {
        return mEditor.getSession();
    }

    /**
     * Gets the wrapper class for undo manger that is associated with the
     * editor. It contains methods for undo or redo operations.
     *
     * @return the undo manager for the edit session.
     */
    public UndoManager getUndoManager() {
        return getSession().getUndoManager();
    }

    /**
     * Gets the current content from the editor. If the editor is not ready an
     * empty text is returned.
     *
     * @return Current content in the editor.
     */
    public String getText() {
        return isReady() ? mEditor.getValue() : "";

    }

    /**
     * Sets the given content to the editor.
     *
     * @param text the content to display.
     */
    public void setText(String text) {
        if (isReady()) {
            mEditor.setValue(text, 1);
        }
    }

    /**
     * Performs an undo operation. Reverts the changes.
     */
    public void Undo() {
        mEditor.undo();
    }

    /**
     * Performs an redo operation. Re-implements the changes.
     */
    public void Redo() {
        mEditor.redo();
    }

    /**
     * Removes the selected text and copy it to clipboard.
     */
    public void Cut() {
        if (Copy()) {
            mEditor.insert("");
        }
    }

    /**
     * Copies the selected text to clipboard.
     *
     * @return True if performed successfully.
     */
    public boolean Copy() {
        if (isReady()) {
            String copy = mEditor.getCopyText();
            if (copy != null && !copy.isEmpty()) {
                ClipboardContent content = new ClipboardContent();
                content.putString(copy);
                Clipboard.getSystemClipboard().setContent(content);
                return true;
            }
        }
        return false;
    }

    /**
     * Paste text from clipboard after the cursor.
     */
    public void Paste() {
        if (isReady()) {
            mEditor.insert(Clipboard.getSystemClipboard().getString());
        }
    }

    /**
     * Shows the find dialog.
     */
    public void ShowFind() {
        mEditor.execCommand("find");
    }

    /**
     * Shows the replace dialog.
     */
    public void ShowReplace() {
        mEditor.execCommand("replace");
    }

    /**
     * Shows the options pane.
     */
    public void ShowOptions() {
        mEditor.execCommand("showSettingsMenu");
    }

    /**
     * Loads a content from a file.
     *
     * @param file File path to load.
     * @throws java.io.FileNotFoundException thrown if file could not be found.
     * @throws java.io.IOException thrown if file could no be read.
     */
    public void openFile(File file) throws FileNotFoundException, IOException, NullPointerException {
        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] data = new byte[(int) file.length()];
            fis.read(data);
            setText(new String(data));
        }
    }

    /**
     * Saves the previously opened file.
     *
     * @throws java.io.IOException thrown if file could no be read.
     */
    public void saveFile() throws IOException, NullPointerException {
        try (FileOutputStream fos = new FileOutputStream(mFilePath)) {
            fos.write(getText().getBytes());
        }
    }

    /**
     * Change or set new save location and saves the file.
     *
     * @param file new location to save.
     * @throws java.io.IOException thrown if file could no be read.
     */
    public void saveAs(File file) throws IOException, NullPointerException {
        mFilePath = file;
        saveFile();
    }

    /**
     * Returns the list of available themes. An empty list is returned on
     * failure.
     *
     * @return the list of themes.
     */
    public ArrayList<ThemeData> getThemeList() {
        ArrayList<ThemeData> list = new ArrayList<>();
        try {
            InputStream is = getClass().getResourceAsStream("resource/themelist");
            String data = IOUtils.toString(is);

            JSONParser parser = new JSONParser();
            JSONArray array = (JSONArray) parser.parse(data);

            for (int i = 0; i < array.size(); ++i) {
                JSONArray item = (JSONArray) array.get(i);
                int siz = item.size();
                if (siz == 0) {
                    continue;
                }

                ThemeData theme = new ThemeData((String) item.get(0));
                if (siz >= 2) {
                    theme.setAlias("ace/theme/" + (String) item.get(1));
                }
                if (siz >= 3) {
                    theme.setDark("dark".equals((String) item.get(2)));
                }
                list.add(theme);
            }

        } catch (IOException | ParseException ex) {
            Logger.getLogger(getClass().getSimpleName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    /**
     * Gets a list of all available command and keyboard shortcuts
     *
     * @return List of available commands
     */
    public ArrayList<Command> getCommandList() {
        JSObject names = (JSObject) mEditor.getModel().eval("this.commands.byName");
        ArrayList<Command> arr = new ArrayList<>();
        for (String str : Commons.getAllProperties(names)) {
            arr.add(new Command((JSObject) names.getMember(str)));
        }
        return arr;
    }

    /**
     * Gets the list of available language modes. returns an empty array on
     * failure.
     *
     * @return list of available language modes.
     */
    public ArrayList<ModeData> getModeList() {
        return null;
    }
}
