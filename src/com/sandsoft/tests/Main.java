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
package com.sandsoft.tests;

import com.sandsoft.acefx.AceEditor;
import com.sandsoft.acefx.model.AceEventProcessor;
import com.sandsoft.acefx.util.Commons;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import netscape.javascript.JSObject;

/**
 *
 * @author Sudipto Chandra
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        final Button button1 = new Button();
        button1.setMaxWidth(1e08);
        button1.setText("RUN TESTS 1st");
        button1.setVisible(false);

        final Button button2 = new Button();
        button2.setMaxWidth(1e08);
        button2.setText("RUN TESTS 2nd");
        button2.setVisible(false);

        final AceEditor root = new AceEditor();
        root.setTop(button1);
        root.setBottom(button2);
        Scene scene = new Scene(root, 800, 400);

        primaryStage.setTitle("Ace Editor Test");
        primaryStage.setScene(scene);
        primaryStage.show();

        button1.setOnAction((event) -> {
            runTest1(root);
        });
        button2.setOnAction((event) -> {
            runTest2(root);
        });
 
        root.addEventHandler(AceEventProcessor.onChangeEvent, event -> {
            System.out.println("Hello there!");
        });

        root.readyProperty().addListener((event) -> {
            root.getEditor().setFontSize(16);
            root.getSession().setMode("ace/mode/java");
            root.setText(GetterSetter.getterAndSetter(
                    "boolean mGlobal;boolean mIgnoreCase;int mLastIndex;boolean mMultiline;String mSource;"
                    + "boolean mWrap;boolean mBackwards;private boolean mCaseSensitive;\nString mNeedle;"
                    + "boolean mUseRE;boolean mSkipCurrent;\nboolean mWholeWord;RegExp mRE;\nRange mStart;"
                    + "int mRow; int mColumn;"));

            button1.setVisible(true);
            button2.setVisible(true);
        });
    }

    public static final int MAX_LEVEL = 2;

    public void runTest1(final AceEditor editor) {
        try {

            System.out.println("=================  1st test finished. ================= ");
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void runTest2(final AceEditor editor) {
        try {
            editor.setText(GetterSetter.getterAndSetter(
                    "String mName;\n"
                    + "    private String mBindKeyWin;\n"
                    + "    private String mBindKeyMac;\n"
                    + "    private String mMultiSelectAction;\n"
                    + "    private boolean mReadOnly;\n"
                    + "    private String mScrollIntoView;\n"
                    + "    private String mAceCommandGroup;"));
            editor.getEditor().selectAll();
            editor.Copy();

            System.out.println("=================  2nd test finished. ================= ");
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

    public static void MapObject(Object obj) {
        //print all objects
        MapObject(0, MAX_LEVEL, "this", obj);
        //prints all the methods
        if (obj instanceof JSObject) {
            MapObject(0, MAX_LEVEL, "this", ((JSObject) obj).eval("Object.getPrototypeOf(this)"));
        }
    }

    public static void MapObject(int level, int maxlev, String pre, Object obj) {
        if (level == 0) {
            System.out.println("========================================================");
        }
        //get tab size
        String tab = "";
        for (int i = 0; i < level; ++i) {
            tab += "    ";
        }
        //check if bottom object reached
        if (!(obj instanceof JSObject)) {
            System.out.printf("%s%s\n", tab, obj);
            return;
        }
        //check if limit reached.
        if (level >= maxlev) {
            return;
        }
        //gets and sorts all the property members
        JSObject dat = (JSObject) obj;
        ArrayList<String> als = Commons.getAllProperties(dat);
        //print all members recursively
        for (String str : als) {
            System.out.printf("%s[%d]%s.%s", tab, level + 1, pre, str);
            Object val = dat.getMember(str);
            if (val instanceof JSObject) {
                System.out.println();
                MapObject(level + 1, maxlev, pre + "." + str, dat.getMember(str));
            } else {
                System.out.printf(" = %s\n", val);
            }
        }
        if (level == 0) {
            System.out.println("========================================================");
        }
    }
}
