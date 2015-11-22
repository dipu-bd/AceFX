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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.concurrent.Worker;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import netscape.javascript.JSException;
import netscape.javascript.JSObject;
import org.sandsoft.acefx.model.Command;
import org.sandsoft.acefx.model.Editor;
import org.sandsoft.acefx.model.UndoManager;
import org.sandsoft.acefx.model.EditSession;
import org.sandsoft.acefx.model.ThemeData;
import org.sandsoft.acefx.util.Commons;
import org.apache.commons.io.FileUtils;
import org.sandsoft.acefx.model.ModeData;

/**
 * A fully functional self-sufficient code editor based on ACE. <br/><br/>
 * <b>Hints</b>:
 * <ul>
 * <li>To create a new editor: <code>AceEditor.createNew()</code></li>
 * <li>To listen to events:
 * <code>addEventHandler(AceEvents.YOUR_EVENT, YOUR_EVENT_HANDLER)</code></li>
 * </ul>
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

    //web view where editor is loaded
    @FXML
    private WebView webView;
    //web engine to process java script
    private WebEngine mWebEngine;

    @FXML
    private ComboBox themeListBox;
    @FXML
    private ComboBox modeListBox;
    @FXML
    private Button undoButton;
    @FXML
    private Button redoButton;

    /**
     * Constructor
     */
    public AceEditor() {
    }

    /**
     * Creates a new instance of the ace editor.
     *
     * @return
     * @throws java.io.IOException
     */
    public static AceEditor createNew() throws IOException {
        //init loader           
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(AceEditor.class.getResource(
                AceEditor.class.getSimpleName() + ".fxml"));

        //attach node
        Node node = (Node) loader.load();
        BorderPane.setAlignment(node, Pos.CENTER);
        AceEditor ace = (AceEditor) loader.getController();
        ace.setCenter(node);
        ace.setMinSize(0, 0);
        ace.setPrefSize(600, 400);
        ace.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        //post load work  
        ace.initialize();

        return ace;
    }

    /**
     * Initializes view and controls after FXML is loaded.
     */
    public void initialize() {
        //setup view   
        webView.setContextMenuEnabled(false);
        mWebEngine = webView.getEngine();
        loadAceEditor();
        loadModeList();
        loadThemeList();

        // process page loading
        mWebEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>() {
            @Override
            public void changed(ObservableValue<? extends Worker.State> ov, Worker.State t, Worker.State t1) {
                if (mWebEngine.getLoadWorker().getState() == Worker.State.SUCCEEDED) {
                    //extract javascript objects
                    mAce = (JSObject) mWebEngine.executeScript("ace");
                    JSObject editor = (JSObject) mAce.call("edit", "editor");
                    mEditor = new Editor(editor);

                    setEventCatchers(editor);
                    setTheme(Themes.Chrome);
                    setMode(Modes.Text);

                    fireEvent(new Event(AceEvents.onLoadEvent));
                }
            }
        });
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

        addEventHandler(AceEvents.onChangeEvent, new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                undoButton.setDisable(!getUndoManager().hasUndo());
                redoButton.setDisable(!getUndoManager().hasRedo());
            }
        });
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
        return mEditor.getValue();

    }

    /**
     * Sets the given content to the editor.
     *
     * @param text the content to display.
     */
    public void setText(String text) {
        getEditor().setValue(text, 1);
    }

    /**
     * Reloads the whole editor in WebView.
     */
    public void doReload() {
        loadAceEditor();
    }

    /**
     * Performs an undo operation. Reverts the changes.
     */
    public void doUndo() {
        getEditor().undo();
        undoButton.setDisable(!getUndoManager().hasUndo());
        redoButton.setDisable(!getUndoManager().hasRedo());
    }

    /**
     * Performs an redo operation. Re-implements the changes.
     */
    public void doRedo() {
        getEditor().redo();
        undoButton.setDisable(!getUndoManager().hasUndo());
        redoButton.setDisable(!getUndoManager().hasRedo());
    }

    /**
     * Paste text from clipboard after the cursor.
     */
    public void doPaste() {
        getEditor().insert(Clipboard.getSystemClipboard().getString());
    }

    /**
     * Copies the selected text to clipboard.
     *
     * @return True if performed successfully.
     */
    public boolean doCopy() {
        String copy = mEditor.getCopyText();
        if (copy != null && !copy.isEmpty()) {
            ClipboardContent content = new ClipboardContent();
            content.putString(copy);
            Clipboard.getSystemClipboard().setContent(content);
            return true;
        }
        return false;
    }

    /**
     * Removes the selected text and copy it to clipboard.
     */
    public void doCut() {
        if (doCopy()) {
            getEditor().insert("");
        }
    }

    /**
     * Shows the find dialog.
     */
    public void showFind() {
        getEditor().execCommand("find");
    }

    /**
     * Shows the replace dialog.
     */
    public void showReplace() {
        getEditor().execCommand("replace");
    }

    /**
     * Shows the options pane.
     */
    public void showOptions() {
        getEditor().execCommand("showSettingsMenu");
    }

    /**
     * Loads a content from a file.
     *
     * @param file File path to load.
     * @throws java.io.FileNotFoundException thrown if file could not be found.
     * @throws java.io.IOException thrown if file could no be read.
     */
    public void openFile(File file) throws FileNotFoundException, IOException {
        mFilePath = file;
        setText(FileUtils.readFileToString(file));
        setMode(Modes.getModeFromFile(file.getName()));
        //getUndoManager().reset(); 
        undoButton.setDisable(true);
        redoButton.setDisable(true);
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
        setMode(Modes.getModeFromFile(file.getName()));
    }

    /**
     * Select the syntax highlighting mode for ace-editor. Some pre-defined
     * supported mode can be found in <code>Modes</code> class.
     *
     * @see Modes
     * @param mode Mode like "ace/mode/java".
     */
    public void setMode(ModeData mode) {
        modeListBox.getSelectionModel().select(mode);
    }

    /**
     * Currently enabled language mode.
     *
     * @see EditSession.getMode()
     * @return the current mode.
     */
    public ModeData getMode() {
        return Modes.getModeByAlias(getSession().getMode());
    }

    /**
     * Sets a theme to the editor. Some pre-defined can be found in
     * <code>Themes</code> class.
     *
     * @see Themes
     * @param theme Theme to set (must contain valid alias).
     */
    public void setTheme(ThemeData theme) {
        themeListBox.getSelectionModel().select(theme);
    }

    /**
     * Gets the current theme.
     *
     * @return
     */
    public ThemeData getTheme() {
        return Themes.getThemeByAlias(getEditor().getTheme());
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

    //loads the list of themes in the themeListBox combobox in toolbar
    private void loadThemeList() {
        themeListBox.setItems(FXCollections.observableArrayList(Themes.SUPPORTED_THEMES));
        themeListBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (newValue != null && oldValue != newValue) {
                    Object data = themeListBox.getSelectionModel().getSelectedItem();
                    getEditor().setTheme(((ThemeData) data).getAlias());
                }
            }
        });
    }

    //loads the list of modes in the modeListBox combobox in toolbar
    private void loadModeList() {
        modeListBox.setItems(FXCollections.observableArrayList(Modes.SUPPORTED_MODES));
        modeListBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (newValue != null && oldValue != newValue) {
                    Object data = modeListBox.getSelectionModel().getSelectedItem();
                    getEditor().getSession().setMode(((ModeData) data).getAlias());
                }
            }
        });
    }

    //load file extension filters
    private void attachFilters(FileChooser fileChooser) {
        FileChooser.ExtensionFilter def
                = new FileChooser.ExtensionFilter("All Files", "*.*");
        fileChooser.getExtensionFilters().add(def);
        fileChooser.setSelectedExtensionFilter(def);
        for (ModeData md : Modes.SUPPORTED_MODES) {
            fileChooser.getExtensionFilters().add(md.getExtensionFilter());
        }
    }

    //toolbar buttons on action
    @FXML
    private void openButtonOnAction() {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open file");
            attachFilters(fileChooser);
            if (mFilePath != null) {
                fileChooser.setInitialFileName(mFilePath.getName());
                fileChooser.setInitialDirectory(mFilePath.getParentFile());
            }
            File file = fileChooser.showOpenDialog(this.getScene().getWindow());
            if (file != null) {
                openFile(file);
            }
        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void saveButtonOnAction() {
        try {
            if (mFilePath != null) {
                saveFile();
                return;
            }
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save file");
            attachFilters(fileChooser);
            File file = fileChooser.showSaveDialog(this.getScene().getWindow());
            if (file != null) {
                saveAs(file);
            }
        } catch (IOException | NullPointerException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void reloadButtonOnAction() {
        doReload();
    }

    @FXML
    private void cutButtonOnAction() {
        doCut();
    }

    @FXML
    private void copyButtonOnAction() {
        doCopy();
    }

    @FXML
    private void pasteButtonOnAction() {
        doPaste();
    }

    @FXML
    private void undoButtonOnAction() {
        doUndo();
    }

    @FXML
    private void redoButtonOnAction() {
        doRedo();
    }

    @FXML
    private void findButtonOnAction() {
        showFind();
    }

    @FXML
    private void replaceButtonOnAction() {
        showReplace();
    }

    @FXML
    private void optionsButtonOnAction() {
        showOptions();
    }

}
