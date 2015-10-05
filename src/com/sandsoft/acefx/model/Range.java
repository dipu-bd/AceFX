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
package com.sandsoft.acefx.model;

import netscape.javascript.JSObject;

/**
 *
 * @author Sudipto Chandra
 */
public class Range {

    private DocPos mEnd;
    private DocPos mStart;

    public Range(int startRow, int startColumn, int endRow, int endColumn) {
        this.mStart = new DocPos(startRow, startColumn);
        this.mEnd = new DocPos(endRow, endColumn);
    }

    public Range(JSObject arg) {
        this.mStart = new DocPos((JSObject) arg.getMember("start"));
        this.mEnd = new DocPos((JSObject) arg.getMember("end"));
    }

    public DocPos getStart() {
        return mStart;
    }

    public DocPos getEnd() {
        return mEnd;
    }

    public void setStart(DocPos start) {
        this.mStart = start;
    }

    public void setEnd(DocPos end) {
        this.mEnd = end;
    }

    @Override
    public String toString() {
        return String.format("{ start=%s; end=%s; }", mStart, mEnd);
    }

}
