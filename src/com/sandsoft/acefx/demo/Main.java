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
package com.sandsoft.acefx.demo;

import com.sandsoft.acefx.*;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.control.Button;

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

        final CodeEditor root = new CodeEditor(defAce);
        root.setTop(button);
        Scene scene = new Scene(root, 800, 600);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();

        button.setOnAction((event) -> {
            runTests(root);
        });

        root.readyProperty().addListener((event) -> {
            root.getEditor().setFontSize(18);
            button.setVisible(true);
        });
    }

    public void runTests(final CodeEditor editor) {
        System.out.println("Running test for editor...");
        Editor edit = editor.getEditor();
        //System.out.println(
        try {
            edit.forEachSelection("cut", "");
        } catch (Exception ex) {
            System.out.println("ERROR");
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
