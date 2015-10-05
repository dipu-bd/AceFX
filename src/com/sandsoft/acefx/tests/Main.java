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

import com.sandsoft.acefx.AceEditor;
import com.sandsoft.acefx.model.DocPos;
import com.sandsoft.acefx.model.Range;
import com.sandsoft.acefx.model.SearchOptions;
import com.sandsoft.acefx.model.ThemeData;
import com.sandsoft.acefx.util.JSUtils;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.control.Button;
import netscape.javascript.JSObject;

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

        final AceEditor root = new AceEditor();
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
            root.getSession().setMode("ace/mode/java");
            button.setVisible(true);

            System.out.println("Scrool speed " + root.getEditor().getScrollSpeed());

            root.setText(GetterSetter.getterAndSetter(
                    "boolean mGlobal;boolean mIgnoreCase;int mLastIndex;boolean mMultiline;String mSource;"
                    + "boolean mWrap;boolean mBackwards;private boolean mCaseSensitive;\nString mNeedle;"
                    + "boolean mUseRE;boolean mSkipCurrent;\nboolean mWholeWord;RegExp mRE;\nRange mStart;"
                    + "int mRow; int mColumn;"));
        });
    }

    ArrayList<ThemeData> list;

    public void runTests(final AceEditor editor) {
        try {
            //Range range = new Range(0, 0, 5, 0);
            //ObjectMapper.MapObject(0, JSUtils.getObject(editor.getEditor().getEditorObject(), range));

            editor.getEditor().moveText(new Range(0, 0, 5, 10), new DocPos(12, 12), Boolean.TRUE);

            //editor.getEditor().getSession().getBracketRange(new DocPos(4, 0));
            //editor.getEditor().moveCursorTo(3, 14);
            //+ "modelist.getModeForPath(path)"));
//            if (list == null || list.isEmpty()) {
//                list = editor.getThemeList();
//            }
//            
//            System.out.println("Applying " + list.get(0));
//            editor.getEditor().setTheme(list.get(0).getAlias());
//            list.remove(0);
        } catch (Exception ex) {
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
