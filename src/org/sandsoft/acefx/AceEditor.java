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
package org.sandsoft.acefx;

import org.sandsoft.acefx.model.Command;
import org.sandsoft.acefx.model.Editor;
import org.sandsoft.acefx.model.UndoManager;
import org.sandsoft.acefx.model.EditSession;
import org.sandsoft.acefx.model.ModeData;
import org.sandsoft.acefx.model.ThemeData;
import org.sandsoft.acefx.util.Commons;

import java.io.File;
import java.io.FileNotFoundException;
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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSException;
import netscape.javascript.JSObject;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * A fully functional self-sufficient code editor based on ACE.
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
    private final BooleanProperty mReady;

    //web view where editor is loaded
    @FXML
    private WebView webView;
    //web engine to process java script
    private WebEngine mWebEngine;

    /**
     * Constructor a new editor.
     *
     * @throws java.io.IOException
     */
    public AceEditor() throws IOException {
        //set default to not ready state
        mReady = new SimpleBooleanProperty(false);

        //init loader           
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(
                getClass().getSimpleName() + ".fxml"));
        loader.setController(this);

        //attach node
        Node node = (Node) loader.load();
        BorderPane.setAlignment(node, Pos.CENTER);
        this.setCenter(node);
        this.setMinSize(0, 0);
        this.setPrefSize(600, 400);
        this.setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);

        //post load work  
        this.initialize();
    }

    private void initialize() {
        //setup webview        
        webView.setContextMenuEnabled(false);
        webView.visibleProperty().bind(mReady);

        //setup WebEngine
        mWebEngine = webView.getEngine();
        loadAceEditor();

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
     * Loads the ACE editor in the web engine.
     */
    private void loadAceEditor() {
        //get ace.js path
        String acepath = getClass().getResource(ACE_PATH).toExternalForm();
        //html wrapper
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
        //load html
        mWebEngine.loadContent(String.format(html, acepath));
    }

    /**
     * Creates event listener. <br/>
     * This uses the 'upcall' feature from java-script to java.
     *
     * @param editor
     */
    private void setEventCatchers(JSObject editor) {
        //set interface object
        editor.setMember("mAceEvent", new AceEvents(this));

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
     * The property indicates if the editor is loaded and ready for
     * interactions.
     *
     * @return the Ready property.
     */
    public BooleanProperty readyProperty() {
        return mReady;
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
     * Executes a script on the current web engine.
     *
     * @param script Script to execute.
     * @return
     */
    public Object executeScript(String script) throws JSException {
        return mWebEngine.executeScript(script);
    }

    /**
     * Reloads the whole editor in WebView.
     */
    public void reload() {
        mReady.set(false);
        loadAceEditor();
    }

    /**
     * Gets the wrapper class for editor that is associated with this control.
     * It contains various methods to interact with the editor.
     *
     * @return the editor attached to this control.
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
    public void openFile(File file) throws FileNotFoundException, IOException {
        setText(FileUtils.readFileToString(file));
        setMode(file.getName());
    }

    /**
     * Saves the previously opened file.
     *
     * @throws java.io.IOException thrown if file could no be read.
     */
    public void saveFile() throws IOException, NullPointerException {
        FileUtils.writeStringToFile(mFilePath, getText());
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
        setMode(file.getName());
    }

    /**
     * Select the syntax highlighting mode for ace-editor. Some pre-defined
     * supported mode can be found in <code>Modes</code> class.
     *
     * @see Modes
     * @param mode Mode like "ace/mode/java".
     */
    public void setMode(String mode) {
        getSession().setMode(mode);
    }

    /**
     * Currently enabled language mode.
     *
     * @see EditSession.getMode()
     * @return the current mode.
     */
    public String getMode() {
        return getSession().getMode();
    }

    /**
     * Sets a theme to the editor. Some pre-defined can be found in
     * <code>Themes</code> class.
     *
     * @see Themes
     * @param theme Theme to set (must contain valid alias).
     */
    public void setTheme(ThemeData theme) {
        getEditor().setTheme(theme.getAlias());
    }

    /**
     * Sets a theme to the editor. Some pre-defined can be found in
     * <code>Themes</code> class.
     *
     * @see Themes
     * @param alias Theme alias to set.
     */
    public void setTheme(String alias) {
        getEditor().setTheme(alias);
    }
 
    /**
     * Gets a list of all available command and keyboard shortcuts
     *
     * @deprecated for internal usage only.
     * @return list of available commands
     */
    @Deprecated
    public ArrayList<Command> getCommandList() {
        JSObject names = (JSObject) mEditor.getModel().eval("this.commands.byName");
        ArrayList<Command> arr = new ArrayList<>();
        for (String str : Commons.getAllProperties(names)) {
            arr.add(new Command((JSObject) names.getMember(str)));
        }
        return arr;
    } 
}
