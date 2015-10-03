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
package com.sandsoft.acefx.tests;

import com.sandsoft.acefx.AceFXEditor;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;

/**
 *
 * @author Sudipto Chandra
 */
public class Main extends Application {

    String defAce = "file:/Z:/Projects/GitHub/AceFX/ace-builds/editor.html";

    @Override
    public void start(Stage primaryStage) {
        final Button button = new Button();
        button.setMaxWidth(1e08);
        button.setText("RUN TESTS");
        button.setVisible(false);

        final AceFXEditor root = new AceFXEditor();
        root.setTop(button);
        Scene scene = new Scene(root, 800, 400);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();

        button.setOnAction((event) -> {
            runTests(root);

        });

        root.readyProperty().addListener((event) -> {
            root.getEditor().setFontSize(16);
            root.getSession().setMode("ace/mode/javascript");
            button.setVisible(true);
            
            root.setText(
                    "function foo(items) {\n"
                    + "    var i;\n"
                    + "    for (i = 0; i &lt; items.length; i++) {\n"
                    + "        alert(\"Ace Rocks \" + items[i]);\n"
                    + "}\n");
        });
    }

    public void runTests(final AceFXEditor editor) {
        try {
            FileChooser chooser = new FileChooser();
            File file = chooser.showOpenDialog(null);
            editor.openFile(file);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
