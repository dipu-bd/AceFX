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
package org.sandsoft.tests;

import org.sandsoft.acefx.AceEditor;
import org.sandsoft.acefx.Modes;
import org.sandsoft.acefx.Themes;
import org.sandsoft.acefx.AceEvents;
import java.io.IOException;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;

/**
 *
 * @author Sudipto Chandra
 */
public class Main extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        final AceEditor ace = AceEditor.createNew();
//        root.setTop(button1);
//        root.setBottom(button2);

        Scene scene = new Scene(ace, 800, 400);
        primaryStage.setTitle("Ace Editor Test");
        primaryStage.setScene(scene);
        primaryStage.show();

        ace.addEventHandler(AceEvents.onLoadEvent, new EventHandler<Event>() {
            @Override
            public void handle(Event t) {
                ace.getEditor().setFontSize(16);
                ace.setTheme(Themes.Ambiance);
                ace.setMode(Modes.Java);

//                ace.setText(Tester.ThemeListGenerator());
                ace.setText(Tester.ModeListGenerator());
//                ace.setText(Tester.MapObject(ace.getThemeData()));  
            }
        });  

    }
}
