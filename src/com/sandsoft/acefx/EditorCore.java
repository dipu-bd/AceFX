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

import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 *
 * @author Sudipto Chandra
 */
public class EditorCore extends BorderPane {
 
    public EditorCore() {
        initialize();
    }

    //web view to use to show ace editor
    private WebView webView;
    private WebEngine webEngine;

    private void initialize() {
        //create design
        webView = new WebView();
        webView.setMaxWidth(Double.MAX_VALUE);
        webView.setMaxHeight(Double.MAX_VALUE);

        BorderPane.setAlignment(webView, Pos.CENTER);
        this.setCenter(webView);

        //init editor
        webEngine = webView.getEngine();
 
    } 
}
