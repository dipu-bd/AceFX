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
public class DocPoint {

    private int row;
    private int column;

    public DocPoint(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public DocPoint(JSObject arg) {
        this.row = (int) arg.eval("this['row']");
        this.column = (int) arg.eval("this['column']");
    }

    @Override
    public String toString() {
        return String.format("{'row': %d, 'column': %d}", row, column);
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int col) {
        this.column = col;
    }
}
