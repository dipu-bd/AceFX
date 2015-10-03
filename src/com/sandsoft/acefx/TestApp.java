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

import java.lang.reflect.Method;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;

/**
 *
 * @author Dipu
 */
public class TestApp extends BorderPane {

    private final WebView mWebView;
    private final WebEngine mWebEngine;
    private String mAcePath;

    public class Editor {

        public void on(String type, Object run) {
            System.out.println("found : " + type + " run = " + run.toString());
        }

        public void onDocumentChange() {
            System.out.println("event: doc changed.");
        }

        public void onCut() {
            System.out.println("Cut happened");
        }

    }

    public TestApp(String acePath) {
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

        // process page loading
        mWebEngine.getLoadWorker().stateProperty().addListener(
                (ObservableValue<? extends State> ov, State oldState, State newState) -> {
                    if (newState == State.SUCCEEDED) {
                        JSObject win = (JSObject) mWebEngine.executeScript("ace.edit('editor');");
                        win.call("setTheme", "ace/theme/chrome");
                        win.call("setValue", "Hello World!");
                    }
                });
    }

    public String getAcePath() {
        return mAcePath;
    }

    public void setAcePath(String path) {
        mAcePath = path;
        mWebEngine.load(path);
    }

}
