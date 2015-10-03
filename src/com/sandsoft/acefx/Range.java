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
public class Range {

    private DocPoint start;
    private DocPoint end;

    /**
     *
     * @param range
     */
    public Range(final JSObject range) {
        start = new DocPoint((JSObject) range.getMember("start"));
        end = new DocPoint((JSObject) range.getMember("end"));
    }

    public Range(int startRow, int startColumn, int endRow, int endColumn) {
        start = new DocPoint(startRow, startColumn);
        end = new DocPoint(endRow, endColumn);
    }

    public String getJSString() {
        return String.format(
                "(function() {"
                + "  this.start = %s;\n"
                + "  this.end = %s;"
                + "})",
                start.getJsString(),
                end.getJsString());
    }

    public DocPoint getStart() {
        return start;
    }

    public DocPoint getEnd() {
        return end;
    }

    public void setStart(DocPoint start) {
        this.start = start;
    }

    public void setEnd(DocPoint end) {
        this.end = end;
    }
}
