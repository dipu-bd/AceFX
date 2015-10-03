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

import com.sandsoft.acefx.model.DocPos;
import netscape.javascript.JSException;
import netscape.javascript.JSObject;

/**
 *
 * @author Sudipto Chandra
 */
//<editor-fold defaultstate="collapsed" desc="Editor -> Editor Session Methods ">
public class EditSession {

    private JSObject mSession;
    private UndoManager mUndoManager;

    /**
     * Creates a new wrapper for EditorSession in ace editor.
     *
     * @param session Main object to wrap.
     */
    public EditSession(JSObject session) throws JSException {
        mSession = session;
        mUndoManager = new UndoManager((JSObject) session.call("getUndoManager"));
    }

    /**
     * Adds a dynamic marker to the session.
     *
     * @param marker Required. object with update method
     * @param inFront Required. Set to true to establish a front marker
     * @return
     */
    @Deprecated
    public Object addDynamicMarker(JSObject marker, Boolean inFront) throws JSException {
        return mSession.call("addDynamicMarker", marker, inFront);
    }

    /**
     * Adds className to the row, to be used for CSS styling and whatnot.
     *
     * @param row Required. The row number
     * @param className Required. The class to add
     */
    public void addGutterDecoration(Integer row, String className) throws JSException {
        mSession.call("addGutterDecoration", row, className);
    }

    /**
     * Adds a new marker to the given Range. If inFront is true, a front marker
     * is defined, and the 'changeFrontMarker' event fires; otherwise, the
     * 'changeBackMarker' event fires.
     *
     * @param range Required. Define the range of the marker
     * @param clazz Required. Set the CSS class for the marker
     * @param type Required. Identify the type of the marker
     * @param inFront Required. Set to true to establish a front marker
     * @return
     */
    @Deprecated
    public int addMarker(JSObject range, String clazz, String type, Boolean inFront) throws JSException {
        return (int) mSession.call("addMarker", range.toString(), clazz, type, inFront);
    }

    /**
     * Clears all the annotations for this session. This function also triggers
     * the 'changeAnnotation' event.
     */
    public void clearAnnotations() throws JSException {
        mSession.call("clearAnnotations");
    }

    /**
     * Removes a breakpoint on the row number given by rows. This function also
     * emits the 'changeBreakpoint' event.
     *
     * @param row Required. A row index
     */
    public void clearBreakpoint(Integer row) throws JSException {
        mSession.call("clearBreakpoint", row);
    }

    /**
     * Removes all breakpoints on the rows. This function also emits the
     * 'changeBreakpoint' event.
     */
    public void clearBreakpoints() throws JSException {
        mSession.call("clearBreakpoints");
    }

    /**
     * For the given document row and column, returns the screen column.
     *
     * @param docRow Required. The document row to check
     * @param docColumn Required. The document column to check
     * @return the screen column.
     */
    public int documentToScreenColumn(Integer docRow, Integer docColumn) throws JSException {
        return (int) mSession.call("documentToScreenColumn", docRow, docColumn);
    }

    /**
     * Converts document coordinates to screen coordinates. This takes into
     * account code folding, word wrap, tab size, and any other visual
     * modifications.
     *
     * @param docRow Required. The document row to check
     * @param docColumn Required. The document column to check
     * @return screen coordinates.
     */
    public DocPos documentToScreenPosition(Integer docRow, Integer docColumn) throws JSException {
        return new DocPos((JSObject) mSession.call("documentToScreenPosition", docRow, docColumn));
    }

    /**
     * For the given document row and column, returns the screen row.
     *
     * @param docRow Required. The document row to check
     * @param docColumn Required. The document column to check
     * @return the screen row.
     */
    public int documentToScreenRow(Integer docRow, Integer docColumn) throws JSException {
        return (int) mSession.call("documentToScreenRow", docRow, docColumn);
    }

    /**
     * Duplicates all the text between firstRow and lastRow.
     *
     * @param firstRow Required. The starting row to duplicate
     * @param lastRow Required. The final row to duplicate
     * @return number of lines copied.
     */
    public int duplicateLines(Integer firstRow, Integer lastRow) throws JSException {
        return (int) mSession.call("duplicateLines", firstRow, lastRow);
    }

    /**
     * Gets the annotations for the EditSession.
     *
     * @return the annotations for the EditSession.
     */
    @Deprecated
    public JSObject getAnnotations() throws JSException {
        return (JSObject) mSession.call("getAnnotations");
    }

    /**
     * Gets the range of a word, including its right whitespace.
     *
     * @param row Required. The row number to start from
     * @param column Required. The column number to start from
     * @return the range of a word, including its right whitespace.
     */
    @Deprecated
    public JSObject getAWordRange(Integer row, Integer column) throws JSException {
        return (JSObject) mSession.call("getAWordRange", row, column);
    }

    /**
     * Gets an array of numbers, indicating which rows have breakpoints.
     *
     * @return an array of numbers, indicating which rows have breakpoints.
     */
    public int getBreakpoints() throws JSException {
        return (int) mSession.call("getBreakpoints");
    }

    /**
     * Gets the underlying document object.
     *
     * @return Document object.
     */
    @Deprecated
    private JSObject getDocument() throws JSException {
        return (JSObject) mSession.call("getDocument");
    }

    /**
     * For the given document row and column, this returns the column position
     * of the last screen row.
     *
     * @param docRow Required. The document row to check
     * @param docColumn Required. The document column to check
     * @return the column position of the last screen row.
     */
    public int getDocumentLastRowColumn(Integer docRow, Integer docColumn) throws JSException {
        return (int) mSession.call("getDocumentLastRowColumn", docRow, docColumn);
    }

    /**
     * For the given document row and column, this returns the document position
     * of the last row.
     *
     * @param docRow Required. The document row to check
     * @param docColumn Required. The document column to check
     * @return the document position of the last row.
     */
    public int getDocumentLastRowColumnPosition(Integer docRow, Integer docColumn) throws JSException {
        return (int) mSession.call("getDocumentLastRowColumnPosition", docRow, docColumn);
    }

    /**
     * Returns the number of rows in the document.
     *
     * @return the number of rows in the document.
     */
    public int getLength() throws JSException {
        return (int) mSession.call("getLength");
    }

    /**
     * Returns a verbatim copy of the given line as it is in the document
     *
     * @param row Required. The row to retrieve from
     * @return a verbatim copy of the given line as it is in the document
     */
    public String getLine(Integer row) throws JSException {
        return (String) mSession.call("getLine", row);
    }

    /**
     * Returns an array of strings of the rows between firstRow and lastRow.
     * This function is inclusive of lastRow.
     *
     * @param firstRow Required. The first row index to retrieve
     * @param lastRow Required. The final row index to retrieve
     * @return a verbatim copy of the given lines as it is in the document
     */
    @Deprecated
    public JSObject getLines(Integer firstRow, Integer lastRow) throws JSException {
        return (JSObject) mSession.call("getLines", firstRow, lastRow);
    }

    /**
     * Returns an array containing the IDs of all the markers, either front or
     * back.
     *
     * @param inFront Required. If true, indicates you only want front markers;
     * false indicates only back markers
     * @return an array containing the IDs of all the markers
     */
    @Deprecated
    public JSObject getMarkers(Boolean inFront) throws JSException {
        return (JSObject) mSession.call("getMarkers", inFront);
    }

    /**
     * @return the current text mode.
     */
    @Deprecated
    public JSObject getMode() throws JSException {
        return (JSObject) mSession.call("getMode");
    }

    /**
     * @return the current new line mode.
     */
    public String getNewLineMode() throws JSException {
        return (String) mSession.call("getNewLineMode");
    }

    /**
     * @return true if overwrites are enabled; false otherwise.
     */
    public boolean getOverwrite() throws JSException {
        return (boolean) mSession.call("getOverwrite");
    }

    /**
     * @return number of screen rows in a wrapped line.
     * @param row Required. The row number to check
     */
    public int getRowLength(Integer row) throws JSException {
        return (int) mSession.call("getRowLength", row);
    }

    /**
     * For the given row, this returns the split data.
     *
     * @param row Required.
     * @return the split data.
     */
    @Deprecated
    public String getRowSplitData(Integer row) throws JSException {
        return (String) mSession.call("getRowSplitData", row);
    }

    /**
     * Returns the position (on screen) for the last character in the provided
     * screen row.
     *
     * @param screenRow Required. The screen row to check.
     * @return the position (on screen) for the last character in the provided
     * screen row.
     */
    public int getScreenLastRowColumn(Integer screenRow) throws JSException {
        return (int) mSession.call("getScreenLastRowColumn", screenRow);
    }

    /**
     * Returns the length of the screen.
     *
     * @return the length of the screen.
     */
    public int getScreenLength() throws JSException {
        return (int) mSession.call("getScreenLength");
    }

    /**
     * The distance to the next tab stop at the specified screen column.
     *
     * @param screenColumn Required. The screen column to check
     * @return the distance to the next tab stop at the specified screen column.
     */
    public int getScreenTabSize(Integer screenColumn) throws JSException {
        return (int) mSession.call("getScreenTabSize", screenColumn);
    }

    /**
     * Returns the width of the screen.
     *
     * @return the width of the screen.
     */
    public int getScreenWidth() throws JSException {
        return (int) mSession.call("getScreenWidth");
    }

    /**
     * Returns the value of the distance between the left of the editor and the
     * leftmost part of the visible content.
     *
     * @return the value of the distance
     */
    public int getScrollLeft() throws JSException {
        return (int) mSession.call("getScrollLeft");
    }

    /**
     * Returns the value of the distance between the top of the editor and the
     * topmost part of the visible content.
     *
     * @return the value of the distance
     */
    public int getScrollTop() throws JSException {
        return (int) mSession.call("getScrollTop");
    }

    /**
     * Returns the string of the current selection.
     *
     * @return the current selection.
     */
    @Deprecated
    public JSObject getSelection() throws JSException {
        return (JSObject) mSession.call("getSelection");
    }

    /**
     * Returns the state of tokenization at the end of a row.
     *
     * @param row Required. The row to start at
     * @return
     */
    @Deprecated
    public JSObject getState(Integer row) throws JSException {
        return (JSObject) mSession.call("getState", row);
    }

    /**
     * Gets the current tab size.
     *
     * @return the current tab size.
     */
    public int getTabSize() throws JSException {
        return (int) mSession.call("getTabSize");
    }

    /**
     * Gets the current value for tabs. If the user is using soft tabs, this
     * will be a series of spaces (defined by getTabSize()); otherwise it's
     * simply '\t'.
     *
     * @return the current value for tabs.
     */
    public String getTabString() throws JSException {
        return (String) mSession.call("getTabString");
    }

    /**
     * Given a range within the document, this function returns all the text
     * within that range as a single string.
     *
     * @param range Required. The range to work with.
     * @return all the text within the range
     */
    @Deprecated
    public String getTextRange(JSObject range) throws JSException {
        return (String) mSession.call("getTextRange", range.toString());
    }

    /**
     * Returns an object indicating the token at the current row. The object has
     * two properties: index and start.
     *
     * @param row Required. The row number to retrieve from
     * @param column Required. The column number to retrieve from
     * @return the token at the current row
     */
    @Deprecated
    public JSObject getTokenAt(Integer row, Integer column) throws JSException {
        return (JSObject) mSession.call("getTokenAt", row, column);
    }

    /**
     * Starts tokenizing at the row indicated. Returns a list of objects of the
     * tokenized rows.
     *
     * @returna list of objects of the tokenized rows.
     */
    @Deprecated
    public JSObject getTokens(Integer row) throws JSException {
        return (JSObject) mSession.call("getTokens", row);
    }

    /**
     * Returns the current undo manager.
     *
     * @return the current undo manager.
     */
    public UndoManager getUndoManager() throws JSException {
        return mUndoManager;
    }

    /**
     * Returns true if soft tabs are being used, false otherwise.
     *
     * @return true if soft tabs are being used, false otherwise.
     */
    public boolean getUseSoftTabs() throws JSException {
        return (boolean) mSession.call("getUseSoftTabs");
    }

    /**
     * Returns true if workers are being used.
     *
     * @return true if workers are being used.
     */
    public boolean getUseWorker() throws JSException {
        return (boolean) mSession.call("getUseWorker");
    }

    /**
     * Returns true if wrap mode is being used; false otherwise.
     *
     * @return true if wrap mode is being used; false otherwise.
     */
    public boolean getUseWrapMode() throws JSException {
        return (boolean) mSession.call("getUseWrapMode");
    }

    /**
     * Returns the current Document as a string.
     *
     * @return the current Document as a string.
     */
    public String getValue() throws JSException {
        return (String) mSession.call("getValue");
    }

    /**
     * Given a starting row and column, this method returns the Range of the
     * first word boundary it finds.
     *
     * @param row Required. The row number to retrieve from
     * @param column Required. The column number to retrieve from
     * @return the Range of the first word boundary it finds.
     */
    @Deprecated
    public JSObject getWordRange(Integer row, Integer column) throws JSException {
        return (JSObject) mSession.call("getWordRange", row, column);
    }

    /**
     * Returns the value of wrap limit.
     *
     * @return the value of wrap limit.
     */
    public int getWrapLimit() throws JSException {
        return (int) mSession.call("getWrapLimit");
    }

    /**
     * Returns an object that defines the minimum and maximum of the wrap limit;
     * it looks something like this:<br/>
     * <code> { min: wrapLimitRange_min, max: wrapLimitRange_max } </code>
     *
     * @return n object that defines the minimum and maximum of the wrap limit
     */
    @Deprecated
    public JSObject getWrapLimitRange() throws JSException {
        return (JSObject) mSession.call("getWrapLimitRange");
    }

    /**
     * Undocumented.
     */
    @Deprecated
    public void highlight() throws JSException {
        mSession.call("highlight");
    }

    /**
     * Highlight lines
     *
     * @param startRow Required. Starting row
     * @param endRow Required. Ending row
     * @param clazz Required. Class name
     * @param inFront Required. true to select front marker
     * @return range of lines that has been highlighted.
     */
    @Deprecated
    public JSObject highlightLines(Integer startRow, Integer endRow, String clazz, Boolean inFront) throws JSException {
        return (JSObject) mSession.call("highlightLines", startRow, endRow, clazz, inFront);
    }

    /**
     * Indents all the rows, from startRow to endRow (inclusive), by prefixing
     * each row with the token in indentString. If indentString contains the
     * '\t' character, it's replaced by whatever is defined by getTabString().
     *
     * @param startRow Required. Starting row
     * @param endRow Required. Ending row
     * @param indentString Required. The indent token
     */
    public void indentRows(int startRow, int endRow, String indentString) throws JSException {
        mSession.call("indentRows", startRow, endRow, indentString);
    }

    /**
     * Inserts a block of text and the indicated position.
     *
     * @param position Required. The position {row, column} to start inserting
     * at
     * @param text Required. A chunk of text to insert
     */
    @Deprecated
    public void insert(JSObject position, String text) throws JSException {
        mSession.call("insert", position, text);
    }

    /**
     * Returns true if the character at the position is a soft tab.
     *
     * @param position
     */
    public boolean isTabStop(DocPos position) throws JSException {
        return (boolean) mSession.call("isTabStop", position.toString());
    }

    /**
     * Shifts all the lines in the document down one, starting from firstRow and
     * ending at lastRow.
     *
     * @param firstRow Required. The starting row to move down
     * @param lastRow Required. The final row to move down
     * @return number of lines moved.
     */
    public int moveLinesDown(Integer firstRow, Integer lastRow) throws JSException {
        return (int) mSession.call("moveLinesDown", firstRow, lastRow);
    }

    /**
     * Shifts all the lines in the document up one, starting from firstRow and
     * ending at lastRow.
     *
     * @param firstRow Required. The starting row to move down
     * @param lastRow Required. The final row to move down
     * @return number of lines moved.
     */
    public int moveLinesUp(Integer firstRow, Integer lastRow) throws JSException {
        return (int) mSession.call("moveLinesUp", firstRow, lastRow);
    }

    /**
     * Moves a range of text from the given range to the given position.
     * toPosition is an object that looks like this: <br/>
     * <code>{ row: newRowLocation, column: newColumnLocation }</code>
     *
     * @param fromRange Required. The range of text you want moved within the
     * document
     * @param toPosition Required. The location (row and column) where you want
     * to move the text to
     * @return new range
     */
    @Deprecated
    public JSObject moveText(JSObject fromRange, DocPos toPosition) throws JSException {
        return (JSObject) mSession.call("moveText", fromRange.toString(), toPosition.toString());
    }

    // onChange() Undocumented
    // onChangeFold() Undocumented
    // Reloads all the tokens on the current session. This function calls BackgroundTokenizer.start () to all the rows; it also emits the 'tokenizerUpdate' event.
    // onReloadTokenizer(Object e)
    /**
     * Outdents all the rows defined by the start and end properties of range.
     *
     * @param range Required. A range of rows
     */
    @Deprecated
    public void outdentRows(JSObject range) throws JSException {
        mSession.call("outdentRows", range.toString());
    }

    /**
     * Same as getUndoManager().redo(true)
     */
    public void redo() throws JSException {
        mUndoManager.redo(true);
    }

    /**
     * Re-implements a previously undone change to your document.
     *
     * @param deltas Array of deltas
     */
    @Deprecated
    private JSObject redoChanges(JSObject deltas, Boolean dontSelect) {
        return (JSObject) mSession.call("redoChanges", deltas, dontSelect);
    }

    /**
     * Removes the range from the document.
     *
     * @param range Required. A specified Range to remove
     * @return the range from the document.
     */
    @Deprecated
    public JSObject remove(JSObject range) throws JSException {
        return (JSObject) mSession.call("remove", range.toString());
    }

    /**
     * Removes className from the row.
     *
     * @param row Required. The row number
     * @param className Required. The class to add
     */
    public void removeGutterDecoration(Integer row, String className) throws JSException {
        mSession.call("removeGutterDecoration", row, className);
    }

    /**
     * Removes the marker with the specified ID. If this marker was in front,
     * the 'changeFrontMarker' event is emitted. If the marker was in the back,
     * the 'changeBackMarker' event is emitted.
     *
     * @param markerId Required. A number representing a marker
     */
    public void removeMarker(Integer markerId) throws JSException {
        mSession.call("removeMarker", markerId);
    }

    /**
     * Replaces a range in the document with the new text.
     *
     * @param range Required. A specified Range to replace
     * @param text Required. The new text to use as a replacement
     * @return
     */
    @Deprecated
    public JSObject replace(JSObject range, String text) throws JSException {
        return (JSObject) mSession.call("replace", range.toString(), text);
    }

    /**
     * Undocumented
     */
    @Deprecated
    public void reset() throws JSException {
        mSession.call("reset");
    }

    /**
     * Clear caches. Clears wrap data, row caches, tokenizer etc.
     */
    public void resetCaches() throws JSException {
        mSession.call("resetCaches");
    }

    /**
     * Converts characters coordinates on the screen to characters coordinates
     * within the document. This takes into account code folding, word wrap, tab
     * size, and any other visual modifications.
     *
     * @param screenRow Required. The screen row to check
     * @param screenColumn Required. The screen column to check
     * @return column of the converted coordinate.
     */
    public int screenToDocumentColumn(Integer screenRow, Integer screenColumn) throws JSException {
        return screenToDocumentPosition(screenRow, screenColumn).getColumn();
    }

    /**
     * Converts characters coordinates on the screen to characters coordinates
     * within the document. This takes into account code folding, word wrap, tab
     * size, and any other visual modifications.
     *
     * @param screenRow Required. The screen row to check
     * @param screenColumn Required. The screen column to check
     * @return row of the converted coordinate.
     */
    public int screenToDocumentRow(Integer screenRow, Integer screenColumn) throws JSException {
        return screenToDocumentPosition(screenRow, screenColumn).getRow();
    }

    /**
     * Converts characters coordinates on the screen to characters coordinates
     * within the document. This takes into account code folding, word wrap, tab
     * size, and any other visual modifications.
     *
     * @param screenRow Required. The screen row to check
     * @param screenColumn Required. The screen column to check
     * @return the converted coordinate.
     */
    public DocPos screenToDocumentPosition(Integer screenRow, Integer screenColumn) throws JSException {
        return new DocPos((JSObject) mSession.call("screenToDocumentPosition", screenRow, screenColumn));
    }

    /**
     * Sets annotations for the EditSession. This functions emits the
     * 'changeAnnotation' event.
     *
     * @param annotations Required. A list of annotations
     */
    @Deprecated
    public void setAnnotations(JSObject annotations) throws JSException {
        mSession.call("setAnnotations", annotations);
    }

    /**
     * Sets a breakpoint on the row number given by rows. This function also
     * emites the 'changeBreakpoint' event.
     *
     * @param row Required. A row index
     * @param className Required. Class of the breakpoint
     */
    public void setBreakpoint(Integer row, String className) throws JSException {
        mSession.call("setBreakpoint", row, className);
    }

    /**
     * Sets a breakpoint on every row number given by rows. This function also
     * emites the 'changeBreakpoint' event.
     *
     * @param rows Required. An array of row indices
     */
    @Deprecated
    public void setBreakpoints(JSObject rows) throws JSException {
        mSession.call("setBreakpoints", rows);
    }

    //Sets the EditSession to point to a new Document. If a BackgroundTokenizer exists, it also points to doc.
    //setDocument(Document doc)
    //public void setMode() 
    /**
     * Sets the new line mode.
     *
     * @param newLineMode Required. The newline mode to use; can be either
     * windows, unix, or auto
     */
    public void setNewLineMode(String newLineMode) throws JSException {
        mSession.call("setNewLineMode", newLineMode);
    }

    /**
     * Pass in true to enable overwrites in your session, or false to disable.
     *
     * @param overwrite Pass in true to enable overwrites in your session, or
     * false to disable.
     */
    public void setOverwrite(Boolean overwrite) throws JSException {
        mSession.call("setOverwrite", overwrite);
    }

    /**
     * Sets the value of the distance between the left of the editor and the
     * leftmost part of the visible content.
     *
     * @param scrollLeft Required. The new scroll left value.
     */
    public void setScrollLeft(Integer scrollLeft) throws JSException {
        mSession.call("setScrollLeft", scrollLeft);
    }

    /**
     * This function sets the scroll top value. It also emits the
     * 'changeScrollTop' event.
     *
     * @param scrollTop Required. The new scroll top value.
     */
    public void setScrollTop(Integer scrollTop) throws JSException {
        mSession.call("setScrollTop", scrollTop);
    }

    /**
     * Set the number of spaces that define a soft tab; for example, passing in
     * 4 transforms the soft tabs to be equivalent to four spaces. This function
     * also emits the changeTabSize event.
     *
     * @param tabSize Required. The new scroll top value
     */
    public void setTabSize(Integer tabSize) throws JSException {
        mSession.call("setTabSize", tabSize);
    }

    //Sets the undo manager
    //setUndoManager(UndoManager undoManager)
    /**
     * Enables or disables highlighting of the range where an undo occurred.
     *
     * @param enable Required. If true, selects the range of the reinserted
     * change
     */
    public void setUndoSelect(Boolean enable) throws JSException {
        mSession.call("setUndoSelect", enable);
    }

    /**
     * Pass true to enable the use of soft tabs. Soft tabs means you're using
     * spaces instead of the tab character ('\t').
     *
     * @param useSoftTabs Required. Value indicating whether or not to use soft
     * tabs
     */
    public void setUseSoftTabs(Boolean useSoftTabs) throws JSException {
        mSession.call("setUseSoftTabs", useSoftTabs);
    }

    /**
     * Identifies if you want to use a worker for the EditSession.
     *
     * @param useWorker Required. Set to true to use a worker
     */
    public void setUseWorker(Boolean useWorker) throws JSException {
        mSession.call("setUseWorker", useWorker);
    }

    /**
     * Sets whether or not line wrapping is enabled. If useWrapMode is different
     * than the current value, the 'changeWrapMode' event is emitted.
     *
     * @param useWrapMode Required. Enable (or disable) wrap mode
     */
    public void setUseWrapMode(Boolean useWrapMode) throws JSException {
        mSession.call("setUseWrapMode", useWrapMode);
    }

    /**
     * Sets the session text.
     *
     * @param text Required. The new text to place
     */
    public void setValue(String text) throws JSException {
        mSession.call("setValue", text);
    }

    /**
     * Sets the boundaries of wrap. Either value can be null to have an
     * unconstrained wrap, or, they can be the same number to pin the limit. If
     * the wrap limits for min or max are different, this method also emits the
     * 'changeWrapMode' event.
     *
     * @param min Required. The minimum wrap value (the left side wrap)
     * @param max Required. The maximum wrap value (the right side wrap)
     */
    public void setWrapLimitRange(Integer min, Integer max) throws JSException {
        mSession.call("setWrapLimitRange", min, max);
    }

    /**
     * Sets the value of overwrite to the opposite of whatever it currently is.
     */
    public void toggleOverwrite() throws JSException {
        mSession.call("toggleOverwrite");
    }

    /**
     * Returns the current Document as a string.
     *
     * @return the current Document as a string.
     */
    @Override
    public String toString() throws JSException {
        return (String) mSession.call("toString");
    }

    /**
     * Same as getUndoManager.undo(true)
     */
    public void undo() throws JSException {
        mUndoManager.undo(true);
    }

    /**
     * Reverts previous changes to your document.
     *
     * @param deltas Array of deltas
     * @param dontSelect true to disable selection.
     * @return
     */
    @Deprecated
    public JSObject undoChanges(JSObject deltas, Boolean dontSelect) {
        return (JSObject) mSession.call("undoChanges", deltas, dontSelect);
    }
}
