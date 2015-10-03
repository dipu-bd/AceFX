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

import netscape.javascript.JSObject;

/**
 *
 * @author Dipu
 */
class TokenPoint {

    private int index;
    private int start;

    public TokenPoint(int index, int start) {
        this.index = index;
        this.start = start;
    }

    public TokenPoint(JSObject arg) {
        this.index = (int) arg.getMember("index");
        this.start = (int) arg.getMember("start");
    }

    public String getJsString() {
        return String.format("(function()"
                + "{ this.index=%d; this.start=%d; }", index, start);
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int row) {
        this.index = row;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int col) {
        this.start = col;
    }
}
