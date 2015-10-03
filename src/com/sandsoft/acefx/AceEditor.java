/*
 * Copyright 2015 Dipu.
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
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Dipu
 */
public class AceEditor extends BorderPane {

    public static AceEditor createNew(String acePath) {
        try {
            //init loader
            Class resourceClass = AceEditor.class;
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(resourceClass.getResource("AceEditor.fxml"));

            //load fxml
            Node node = (Node) loader.load();
            AceEditor ace = (AceEditor) loader.getController();

            //add to layout  
            ace.setCenter(node);

            //post initialization
            ace.initialize(acePath);
            return ace;

        } catch (Exception ex) {
            return null;
        }
    }

    @FXML
    private WebView webView;

    private EditorCore editor;

    /**
     * Initializes the controller class.
     *
     * @param acePath Path to ACE editor's HTML file.
     */
    public void initialize(String acePath) {
        editor = new EditorCore(webView.getEngine());
        editor.load(acePath);
    }

    /**
     * Gets the ace editor currently in use.
     *
     * @return
     */
    public EditorCore getEditor() {
        return editor;
    }

    @FXML
    private void handleOpenButton(ActionEvent event) throws IOException {
        FileChooser fc = new FileChooser();
        File file = fc.showOpenDialog(this.getScene().getWindow());
        editor.openFile(file);
    }

    @FXML
    private void handleSaveButton(ActionEvent event) throws IOException {
        FileChooser fc = new FileChooser();
        File file = fc.showSaveDialog(this.getScene().getWindow());
        editor.saveAs(file);
    }

    @FXML
    private void handleUndoButton(ActionEvent event) {
        editor.undo();
    }

    @FXML
    private void handleRedoButton(ActionEvent event) {
        editor.redo();
    }

    @FXML
    private void handleCutButton(ActionEvent event) {
        editor.Cut();

    }

    @FXML
    private void handleCopyButton(ActionEvent event) {
        editor.Copy();
    }

    @FXML
    private void handlePasteButton(ActionEvent event) {
        editor.Paste();
    }

    @FXML
    private void handleFindButton(ActionEvent event) {
        editor.Find();
    }

    @FXML
    private void handleReplaceButton(ActionEvent event) {
        editor.Replace();
    }

    @FXML
    private void handleOptionsButton(ActionEvent event) {
        editor.Options();
    }

    @FXML
    private void handleShortcutsButton(ActionEvent event) {
    }

}
