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
 
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.concurrent.Worker;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.BorderPane; 
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;

/**
 *
 * @author Sudipto Chandra.
 */
public final class AceFXEditor extends BorderPane {

    //where ace.js file is saved
    private static final String ACE_PATH = "ace/ace.js";

    /**
     * web engine
     */
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
    public AceFXEditor() {
        //setup webview
        mWebView = new WebView();
        mWebView.setMaxWidth(Double.MAX_VALUE);
        mWebView.setMaxHeight(Double.MAX_VALUE);
        mWebView.setPrefWidth(600.0);
        mWebView.setPrefHeight(400.0);
        mWebView.setMinWidth(0.0);
        mWebView.setMinHeight(0.0);
        mWebView.setContextMenuEnabled(false);
        this.setCenter(mWebView);

        //load ace 
        mWebEngine = mWebView.getEngine();
        mReady = new SimpleBooleanProperty(false);
        mWebEngine.loadContent(getHTML());

        // process page loading
        mWebEngine.getLoadWorker().stateProperty().addListener((event) -> {
            if (mWebEngine.getLoadWorker().getState() == Worker.State.SUCCEEDED) {
                mEditor = new Editor((JSObject) mWebEngine.executeScript("ace.edit('editor');"));
                mReady.set(true);
            }
        });
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
                + "  <title>Editor</title>\n"
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
                + "<pre id=\"editor\">function foo(items) {\n"
                + "    var i;\n"
                + "    for (i = 0; i &lt; items.length; i++) {\n"
                + "        alert(\"Ace Rocks \" + items[i]);\n"
                + "    }\n"
                + "}</pre>\n"
                + "<script src=\"%s\" type=\"text/javascript\" charset=\"utf-8\"></script>\n"
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
    public EditSession getEditSession() {
        return mEditor.getSession();
    }

    /**
     * Gets the wrapper class for undo manger that is associated with the
     * editor. It contains methods for undo or redo operations.
     *
     * @return the undo manager for the edit session.
     */
    public UndoManager getUndoManager() {
        return getEditSession().getUndoManager();
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
}
