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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
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
public class CodeEditor extends BorderPane {

    /**
     * web engine
     */
    private Editor mEditor;

    private String mAcePath;

    private File mFilePath;

    private BooleanProperty mReady;

    private final WebView mWebView;

    private final WebEngine mWebEngine;

    /**
     * Constructor.
     *
     * @param acePath Ace path location.
     */
    public CodeEditor(String acePath) {
        //setup webview
        mWebView = new WebView();
        mWebView.setMaxWidth(Double.MAX_VALUE);
        mWebView.setMaxHeight(Double.MAX_VALUE);
        mWebView.setPrefWidth(600.0);
        mWebView.setPrefHeight(400.0);
        mWebView.setMinWidth(0.0);
        mWebView.setMinHeight(0.0);
        this.setCenter(mWebView);

        //load ace
        mAcePath = acePath;
        mWebEngine = mWebView.getEngine();
        mWebEngine.load(acePath);
        mReady = new SimpleBooleanProperty(false);

        // process page loading
        mWebEngine.getLoadWorker().stateProperty().addListener(
                (ObservableValue<? extends Worker.State> ov, Worker.State oldState, Worker.State newState) -> {
                    if (newState == Worker.State.SUCCEEDED) {
                        mEditor = new Editor((JSObject) mWebEngine.executeScript("ace.edit('editor');"));
                        mReady.set(true);
                    }
                });
    }

    /**
     * Loads ace editor from a new path.
     *
     * @param acePath Ace-path to load from.
     */
    public void load(String acePath) {
        mReady.set(false);
        this.mAcePath = acePath;
        mWebEngine.load(acePath);
    }

    /**
     * Gets the ace path
     */
    public String getAcePath() {
        return mAcePath;
    }

    /**
     * Sets the ace path
     *
     * @param acePath Ace path to set
     */
    public void setAcePath(String acePath) {
        load(acePath);
    }

    /**
     * Checks if the editor is ready for interaction.
     *
     * @return True if worker is successfully loaded.
     */
    public boolean isReady() {
        return (mReady.get());
    }

    /**
     * Returns if the web view is ready to execute commands.
     *
     * @return
     */
    public BooleanProperty readyProperty() {
        return mReady;
    }

    /**
     * Gets the editor associated with this control.
     *
     * @return
     */
    public Editor getEditor() {
        return mEditor;
    }

    /**
     * Gets the editor session associated with this control.
     *
     * @return
     */
    public EditorSession getEditorSession() {
        return mEditor.getSession();
    }

    /**
     * Gets the undo manager associated with this control.
     *
     * @return
     */
    public UndoManager getUndoManager() {
        return getEditorSession().getUndoManager();
    }

    /**
     * Loads a content from a file.
     *
     * @param file File path to load.
     * @throws java.io.FileNotFoundException Throws if file could not be found.
     * @throws java.io.IOException Throws if file could no be read.
     */
    public void openFile(File file) throws FileNotFoundException, IOException {
        try (FileReader fr = new FileReader(file)) {
            char[] chars = new char[(int) file.length()];
            fr.read(chars, 0, (int) file.length());
            setText(new String(chars));
        }
    }

    /**
     * Saves the previously opened file.
     *
     * @throws java.io.IOException Throws if output could not be saved.
     */
    public void saveFile() throws IOException {
        try (FileWriter fw = new FileWriter(mFilePath)) {
            fw.write(getText());
        }
    }

    /**
     * Change or set new save location and saves the file.
     *
     * @param file new location to save.
     * @throws java.io.IOException Throws if output could not be saved.
     */
    public void saveAs(File file) throws IOException {
        mFilePath = file;
        saveFile();
    }

    /**
     * Return the current text in the editor. <i>Same as getValue()</i>
     *
     * @return Current content in the editor.
     */
    public String getText() {
        return isReady() ? mEditor.getValue() : "";

    }

    /**
     * Sets the document display text. <i>Same as setValue()</i>
     *
     * @param text The new text to set for the document.
     */
    public void setText(String text) {
        if (isReady()) {
            mEditor.setValue(text, -1);
        }
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
     * Shows the find dialogue.
     */
    public void Find() {
        mEditor.execCommand("find");
    }

    /**
     * Show the replace dialogue.
     */
    public void Replace() {
        mEditor.execCommand("replace");
    }

    /**
     * Shows the options pane.
     */
    public void Options() {
        mEditor.execCommand("showSettingsMenu");
    }

}
