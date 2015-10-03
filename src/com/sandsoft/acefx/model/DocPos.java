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
package com.sandsoft.acefx.model;

import netscape.javascript.JSObject;

/**
 *
 * @author Dipu
 */
public class DocPos {

    private int mRow;
    private int mColumn;

    public DocPos(int row, int column) {
        this.mRow = row;
        this.mColumn = column;
    }

    public DocPos(JSObject arg) {
        this.mRow = (int) arg.eval("this['row']");
        this.mColumn = (int) arg.eval("this['column']");
    }

    @Override
    public String toString() {
        return String.format("{row: %d, column: %d}", mRow, mColumn);
    }

    public int getRow() {
        return mRow;
    }

    public int getColumn() {
        return mColumn;
    }

    public void setRow(int row) {
        this.mRow = row;
    }

    public void setColumn(int col) {
        this.mColumn = col;
    }
}
