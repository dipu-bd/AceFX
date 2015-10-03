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

import netscape.javascript.JSException;
import netscape.javascript.JSObject;

/**
 *
 * @author Sudipto Chandra
 */
public class Editor {

    private final JSObject mEditor;
    private final EditSession mEditSession;

    public Editor(final JSObject editor) throws JSException {
        mEditor = editor;
        mEditSession = new EditSession((JSObject) editor.call("getSession"));
    }

    /**
     * Adds the selection and cursor.
     *
     * @param orientedRange Required. A range containing a cursor
     * @return
     */
    @Deprecated
    public JSObject addSelectionMarker(JSObject orientedRange) throws JSException {
        return null;
    }

    /**
     * Aligns the cursors or selected text.
     */
    public void alignCursors() throws JSException {
        mEditor.call("alignCursors");
    }

    /**
     * Out-dents the current line.
     */
    public void blockOutdent() throws JSException {
        mEditor.call("blockOutdent");
    }

    /**
     * Blurs the current textInput.
     */
    public void blur() throws JSException {
        mEditor.call("blur");
    }

    /**
     * Attempts to center the current selection on the screen.
     */
    public void centerSelection() throws JSException {
        mEditor.call("centerSelection");
    }

    /**
     * Empties the selection (by de-selecting it). This function also emits the
     * 'changeSelection' event.
     */
    public void clearSelection() throws JSException {
        mEditor.call("clearSelection");
    }

    /**
     * Copies all the selected lines down one row.
     */
    public void copyLinesDown() throws JSException {
        mEditor.call("copyLinesDown");
    }

    /**
     * Copies all the selected lines up one row.
     */
    public void copyLinesUp() throws JSException {
        mEditor.call("copyLinesUp");
    }

    /**
     * Cleans up the entire editor.
     */
    @Deprecated
    public void destroy() throws JSException {
        mEditor.call("destroy");
    }

    /**
     * Duplicate the selected text.
     */
    public void duplicateSelection() throws JSException {
        mEditor.call("duplicateSelection");
    }

    /**
     * Executes a command under editor. Here is the
     * <a href="https://github.com/ajaxorg/ace/blob/master/lib/ace/commands/default_commands.js#L171">link
     * to all commands</a>
     *
     * @param command
     * @return
     */
    public boolean execCommand(String command) throws JSException {
        return (boolean) mEditor.call("execCommand", command);
    }

    /**
     * Removes all the selections except the last added one.
     */
    public void exitMultiSelectMode() throws JSException {
        mEditor.call("exitMultiSelectMode");
    }

    /**
     * Attempts to find needle within the document. For more information on
     * options, see Search.
     *
     * @param needle Required. The text to search for (optional)
     * @param options An object defining various search properties. Leave null
     * if unsure.
     * @param animate If true animate scrolling. Leave null if unsure.
     */
    @Deprecated
    public void find(String needle, JSObject options, Boolean animate) throws JSException {
        mEditor.call("find", needle, options, animate);
    }

    /**
     * Finds and selects all the occurences of needle.
     *
     * @param needle Required. The text to search for (optional)
     * @param options Required. An object defining various search properties
     * @param keeps Required.
     * @return
     */
    @Deprecated
    public int findAll(String needle, JSObject options, Boolean keeps) throws JSException {
        return (int) mEditor.call("findAll", needle, options, keeps);
    }

    /**
     * Performs another search for needle in the document. For more information
     * on options, see Search.
     *
     * @param options Required. An object defining various search properties
     * @param animate Required. If true animate scrolling
     */
    @Deprecated
    public void findNext(JSObject options, Boolean animate) throws JSException {
        mEditor.call("findNext", options, animate);
    }

    /**
     * Performs a search for needle backwards. For more information on options,
     * see Search.
     *
     * @param options Required. An object defining various search properties
     * @param animate Required. If true animate scrolling
     */
    @Deprecated
    public void findPrevious(JSObject options, Boolean animate) throws JSException {
        mEditor.call("findPrevious", options, animate);
    }

    /**
     * Brings the current textInput into focus.
     */
    public void focus() throws JSException {
        mEditor.call("focus");
    }

    /**
     * Executes a command for each selection range.
     *
     * @param cmd Required. The command to execute
     * @param args Required. Any arguments for the command
     */
    @Deprecated
    public void forEachSelection(String cmd, String args) throws JSException {
        mEditor.call("forEachSelection", cmd, args);
    }

    /**
     * Gets if renderer has animated scroll.
     */
    public boolean getAnimatedScroll() throws JSException {
        return (boolean) mEditor.call("getAnimatedScroll");
    }

    /**
     * Returns true if the behaviors are currently enabled. "Behaviors" in this
     * case is the auto-pairing of special characters, like quotation marks,
     * parenthesis, or brackets.
     *
     * @return
     */
    public boolean getBehavioursEnabled() throws JSException {
        return (boolean) mEditor.call("getBehavioursEnabled");
    }

    /**
     * Returns the string of text currently highlighted.
     *
     * @return
     */
    public String getCopyText() throws JSException {
        return (String) mEditor.call("getCopyText");
    }

    /**
     * Returns the screen position of the cursor.
     */
    @Deprecated
    public JSObject getCursorPositionScreen() throws JSException {
        return (JSObject) mEditor.call("getCursorPositionScreen");
    }

    /**
     * Gets the display indent guide options.
     */
    public boolean getDisplayIndentGuides() throws JSException {
        return (boolean) mEditor.call("getDisplayIndentGuides");
    }

    /**
     * Returns the current mouse drag delay.
     */
    public int getDragDelay() throws JSException {
        return (int) mEditor.call("getDragDelay");
    }

    /**
     * Gets the fade fold widget option.
     */
    public boolean getFadeFoldWidgets() throws JSException {
        return (boolean) mEditor.call("getFadeFoldWidgets");
    }

    /**
     * Returns the index of the first visible row.
     */
    public int getFirstVisibleRow() throws JSException {
        return (int) mEditor.call("getFirstVisibleRow");
    }

    /**
     * Returns true if current lines are always highlighted.
     *
     * @return
     */
    public boolean getHighlightActiveLine() throws JSException {
        return (boolean) mEditor.call("getHighlightActiveLine");
    }

    /**
     * Returns true if gutter lines are always highlighted.
     *
     * @return
     */
    public boolean getHighlightGutterLine() throws JSException {
        return (boolean) mEditor.call("getHighlightGutterLine");
    }

    /**
     * Returns true if currently highlighted words are to be highlighted.
     *
     * @return
     */
    public boolean getHighlightSelectedWord() throws JSException {
        return (boolean) mEditor.call("getHighlightSelectedWord");
    }

    /**
     * Returns the keyboard handler, such as "vim" or "windows".
     *
     * @return
     */
    @Deprecated
    public JSObject getKeyboardHandler() throws JSException {
        return (JSObject) mEditor.call("getKeyboardHandler");
    }

    /**
     * Returns an object containing all the search options. For more information
     * on options, see Search.
     *
     * @return
     */
    @Deprecated
    public JSObject getLastSearchOptions() throws JSException {
        return (JSObject) mEditor.call("getLastSearchOptions");
    }

    /**
     * Returns the index of the last visible row.
     *
     * @return the keyboard handler, such as "vim" or "windows".
     */
    public int getLastVisibleRow() throws JSException {
        return (int) mEditor.call("getLastVisibleRow");
    }

    /**
     * Works like getEditSession().getTokenAt(), except it returns a number.
     *
     * @param row Required.
     * @param column Required.
     * @return
     */
    public Integer getNumberAt(Integer row, Integer column) throws JSException {
        return (Integer) mEditor.call("getNumberAt");
    }

    /**
     * Returns true if overwrites are enabled; false otherwise.
     *
     * @return
     */
    public boolean getOverwrite() throws JSException {
        return (boolean) mEditor.call("getOverwrite");
    }

    /**
     * Returns the column number of where the print margin is.
     *
     * @return
     */
    public int getPrintMarginColumn() throws JSException {
        return (int) mEditor.call("getPrintMarginColumn");
    }

    /**
     * Returns true if the editor is set to read-only mode.
     *
     * @return
     */
    public boolean getReadOnly() throws JSException {
        return (boolean) mEditor.call("getReadOnly");
    }

    /**
     * Returns the value indicating how fast the mouse scroll speed is (in
     * milliseconds).
     *
     * @return
     */
    public int getScrollSpeed() throws JSException {
        return (int) mEditor.call("getScrollSpeed");
    }

    /**
     * Returns the currently highlighted selection.
     *
     * @return
     */
    @Deprecated
    public JSObject getSelection() throws JSException {
        return (JSObject) mEditor.call("getSelection");
    }

    //Returns the Range for the selected text.
    //public Range getSelectionRange()  { }
    /**
     * Returns the current selection style.
     *
     * @return
     */
    public String getSelectionStyle() throws JSException {
        return (String) mEditor.call("getSelectionStyle");
    }

    /**
     * Returns the current session being used.
     *
     * @return Currently active edit session object.
     */
    public EditSession getSession() throws JSException {
        return mEditSession;
    }

    /**
     * Returns true if the fold widgets are shown.
     *
     * @return
     */
    public boolean getShowFoldWidgets() throws JSException {
        return (boolean) mEditor.call("getShowFoldWidgets");
    }

    /**
     * Returns true if invisible characters are being shown.
     *
     * @return
     */
    public boolean getShowInvisibles() throws JSException {
        return (boolean) mEditor.call("getShowInvisibles");
    }

    /**
     * Returns true if the print margin is being shown.
     *
     * @return
     */
    public boolean getShowPrintMargin() throws JSException {
        return (boolean) mEditor.call("getShowPrintMargin");
    }

    /**
     * Returns the path of the current theme.
     *
     * @return
     */
    public String getTheme() throws JSException {
        return (String) mEditor.call("getTheme");
    }

    /**
     * Returns the current session's content. 
     *
     * @return
     */
    public String getValue() throws JSException {
        return (String) mEditor.call("getValue");
    }

    /**
     * Returns true if the wrapping behaviors are currently enabled.
     *
     * @return
     */
    public boolean getWrapBehavioursEnabled() throws JSException {
        return (boolean) mEditor.call("getWrapBehavioursEnabled");
    }

    /**
     * Moves the cursor to the specified line number, and also into the
     * indicated column.
     *
     * @param lineNumber Required. The line number to go to
     * @param column Required. A column number to go to
     * @param animate Required. If true animates scrolling
     */
    public void gotoLine(Integer lineNumber, Integer column, Boolean animate) throws JSException {
        mEditor.call("gotoLine", lineNumber, column, animate);
    }

    /**
     * Shifts the document to wherever "page down" is, as well as moving the
     * cursor position.
     */
    public void gotoPageDown() throws JSException {
        mEditor.call("gotoPageDown");
    }

    /**
     * Shifts the document to wherever "page up" is, as well as moving the
     * cursor position.
     */
    public void gotoPageUp() throws JSException {
        mEditor.call("gotoPageUp");
    }

    /**
     * Indents the current line.
     */
    public void indent() throws JSException {
        mEditor.call("indent");
    }

    /**
     * Inserts text into wherever the cursor is pointing.
     *
     * @param text Required. The new text to add.
     */
    public void insert(String text) throws JSException {
        mEditor.call("insert", text);
    }

    /**
     * Inserts a block of text and the indicated position.
     *
     * @param row
     * @param column
     * @param text Required. A chunk of text to insert
     */
    public void insert(int row, int column, String text) throws JSException {
        gotoLine(row, column, Boolean.FALSE);
        insert(text);
    }

    /**
     * Returns true if the current textInput is in focus.
     *
     * @return true if the current textInput is in focus.
     */
    public boolean isFocused() throws JSException {
        return (boolean) mEditor.call("isFocused");
    }

    /**
     * Indicates if the entire row is currently visible on the screen.
     *
     * @param row Required. The row to check
     * @return true if the entire row is currently visible on the screen.
     */
    public boolean isRowFullyVisible(Integer row) throws JSException {
        return (boolean) mEditor.call("isRowFullyVisible", row);
    }

    /**
     * Indicates if the row is currently visible on the screen.
     *
     * @param row Required. The row to check
     * @return true if the row is currently visible on the screen.
     */
    public boolean isRowVisible(Integer row) throws JSException {
        return (boolean) mEditor.call("isRowVisible", row);
    }

    /**
     * Moves the cursor's row and column to the next matching bracket.
     */
    @Deprecated
    public void jumpToMatching(JSObject select) throws JSException {
        mEditor.call("jumpToMatching", select);
    }

    /**
     * If the character before the cursor is a number, this functions changes
     * its value by amount.
     *
     * @param amount Required. The value to change the numeral by (can be
     * negative to decrease value)
     */
    public void modifyNumber(Integer amount) throws JSException {
        mEditor.call("modifyNumber", amount);
    }

    /**
     * Moves the cursor to the specified row and column. Note that this does not
     * de-select the current selection.
     *
     * @param row Required. The row to move.
     * @param column Required. The columns to move.
     */
    public void moveCursorTo(Integer row, Integer column) throws JSException {
        mEditor.call("moveCursorTo", row, column);
    }

    /**
     * Moves the cursor to the position indicated by pos.row and pos.column.
     *
     * @param pos Required. An object with two properties, row and column
     */
    @Deprecated
    public void moveCursorToPosition(JSObject pos) throws JSException {
        mEditor.call("moveCursorToPosition", pos);
    }

    /**
     * Shifts all the selected lines down one row.
     *
     * @return number of lines moved.
     */
    public void moveLinesDown() throws JSException {
        mEditor.call("moveLinesDown");
    }

    /**
     * Shifts all the selected lines up one row.
     *
     * @return number of lines moved.
     */
    public void moveLinesUp() throws JSException {
        mEditor.call("moveLinesUp");
    }

    //Undocumented
    //moveText()
    /**
     * Moves the cursor down in the document the specified number of times. Note
     * that this does de-select the current selection.
     *
     * @param times Required. The number of times to change navigation
     */
    public void navigateDown(Integer times) throws JSException {
        mEditor.call("navigateDown", times);
    }

    /**
     * Moves the cursor to the end of the current file. Note that this does
     * de-select the current selection.
     */
    public void navigateFileEnd() throws JSException {
        mEditor.call("navigateFileEnd");
    }

    /**
     * Moves the cursor to the end of the current file. Note that this does
     * de-select the current selection.
     */
    public void navigateFileStart() throws JSException {
        mEditor.call("navigateFileStart");
    }

    /**
     * Moves the cursor left in the document the specified number of times. Note
     * that this does de-select the current selection.
     *
     * @param times Required. The number of times to change navigation
     */
    public void navigateLeft(Integer times) throws JSException {
        mEditor.call("navigateLeft", times);
    }

    /**
     * Moves the cursor to the end of the current line. Note that this does
     * de-select the current selection.
     */
    public void navigateLineEnd() throws JSException {
        mEditor.call("navigateLineEnd");
    }

    /**
     * Moves the cursor to the start of the current line. Note that this does
     * de-select the current selection.
     */
    public void navigateLineStart() throws JSException {
        mEditor.call("navigateLineStart");
    }

    /**
     * Moves the cursor right in the document the specified number of times.
     * Note that this does de-select the current selection.
     *
     * @param times Required. The number of times to change navigation
     */
    public void navigateRight(Integer times) throws JSException {
        mEditor.call("navigateRight", times);
    }

    /**
     * Moves the cursor to the specified row and column. Note that this does
     * de-select the current selection.
     *
     * @param row Required. The row to move.
     * @param column Required. The columns to move.
     */
    public void navigateTo(Integer row, Integer column) throws JSException {
        mEditor.call("navigateTo", row, column);
    }

    /**
     * Moves the cursor up in the document the specified number of times. Note
     * that this does de-select the current selection.
     *
     * @param times
     */
    public void navigateUp(Integer times) throws JSException {
        mEditor.call("navigateUp", times);
    }

    /**
     * Moves the cursor to the word immediately to the left of the current
     * position. Note that this does de-select the current selection.
     */
    public void navigateWordLeft() throws JSException {
        mEditor.call("navigateWordLeft");
    }

    /**
     * Moves the cursor to the word immediately to the right of the current
     * position. Note that this does de-select the current selection.
     */
    public void navigateWordRight() throws JSException {
        mEditor.call("navigateWordRight");
    }

    /*
     onBlur() Undocumented
     onChangeAnnotation() Undocumented
     onChangeBackMarker() Undocumented
     onChangeBreakpoint() Undocumented
     onChangeFold() Undocumented
     onChangeFrontMarker() Undocumented
     onChangeMode() Undocumented
     onChangeWrapLimit() Undocumented
     onChangeWrapMode() Undocumented
     onCommandKey() Undocumented
     onCompositionEnd() Undocumented
     onCompositionStart() Undocumented
     onCompositionUpdate() Undocumented
     onCopy()
     Called whenever a text "copy" happens.
     onCursorChange()
     Emitted when the selection changes.
     onCut()
     Called whenever a text "cut" happens.
     onDocumentChange() Undocumented
     onFocus() Undocumented
     onPaste(String text)
     Called whenever a text "paste" happens.
     onScrollLeftChange() Undocumented
     onScrollTopChange() Undocumented
     onSelectionChange() Undocumented
     onTextInput() Undocumented
     onTokenizerUpdate()
     */
    /**
     * Perform a redo operation on the document, re-implementing the last
     * change.
     */
    public void redo() throws JSException {
        mEditor.call("redo");
    }

    /**
     * Removes words of text from the editor. A "word" is defined as a string of
     * characters book-ended by whitespace.
     *
     * @param dir Required. The direction of the deletion to occur, either
     * "left" or "right"
     */
    public void remove(String dir) throws JSException {
        mEditor.call("remove", dir);
    }

    /**
     * Removes all the lines in the current selection
     */
    public void removeLines() throws JSException {
        mEditor.call("removeLines");
    }

    /**
     * Removes the selection marker.
     *
     * @param range Required. selection range added with addSelectionMarker().
     */
    @Deprecated
    public void removeSelectionMarker(JSObject range) throws JSException {
        mEditor.call("removeSelectionMarker", range);
    }

    /**
     * Removes all the words to the right of the current selection , until the
     * end of the line.
     */
    public void removeToLineEnd() throws JSException {
        mEditor.call("removeToLineEnd");
    }

    /**
     * Removes all the words to the left of the current selection , until the
     * start of the line.
     */
    public void removeToLineStart() throws JSException {
        mEditor.call("removeToLineStart");
    }

    /**
     * Removes the word directly to the left of the current selection.
     */
    public void removeWordLeft() throws JSException {
        mEditor.call("removeWordLeft");
    }

    /**
     * Removes the word directly to the right of the current selection.
     */
    public void removeWordRight() throws JSException {
        mEditor.call("removeWordRight");
    }

    /**
     * Replaces the first occurance of options.needle with the value in
     * replacement.
     *
     ** @param replacement Required. The text to replace with
     * @param options Required. The Search options to use
     */
    @Deprecated
    public void replace(String replacement, JSObject options) throws JSException {
        mEditor.call("replace", replacement, options);
    }

    /**
     * Replaces all occurances of options.needle with the value in replacement.
     *
     * @param replacement Required. The text to replace with
     * @param options Required. The Search options to use
     */
    @Deprecated
    public void replaceAll(String replacement, JSObject options) throws JSException {
        mEditor.call("replaceAll", replacement, options);
    }

    /**
     * Triggers a resize of the editor.
     *
     * @param force Required. If true, recomputes the size, even if the height
     * and width haven't changed
     */
    public void resize(Boolean force) throws JSException {
        mEditor.call("resize", force);
    }

    /**
     * Brings the range with view.
     *
     * @param range Required. Range to reveal. replacement.
     * @param animate Required. true to animate.
     */
    @Deprecated
    public void revealRange(JSObject range, Boolean animate) throws JSException {
        mEditor.call("revealRange", range, animate);
    }

    /**
     * Scrolls the document to wherever "page down" is , without changing the
     * cursor position.
     */
    public void scrollPageDown() throws JSException {
        mEditor.call("scrollPageDown");
    }

    /**
     * Scrolls the document to wherever "page up" is , without changing the
     * cursor position.
     */
    public void scrollPageUp() throws JSException {
        mEditor.call("scrollPageUp");
    }

    //scrollToLine(Number line, Boolean center, Boolean animate, Function callback)
    //Scrolls to a line. If center is true, it puts the line in middle of screen (or attempts to).
    /**
     * Moves the editor to the specified row.
     *
     * @param row Required. Row number.
     */
    public void scrollToRow(Integer row) throws JSException {
        mEditor.call("resize", row);
    }

    /**
     * Selects all the text in editor.
     */
    public void selectAll() throws JSException {
        mEditor.call("selectAll");
    }

    /**
     * Finds the next occurence of text in an active selection and adds it to
     * the selections.
     *
     * @param dir Required. The direction of lines to select: -1 for up, 1 for
     * down
     * @param skip Required. If true, removes the active selection range
     */
    public void selectMore(Integer dir, Boolean skip) throws JSException {
        mEditor.call("selectMore", dir, skip);
    }

    /**
     * Adds a cursor above or below the active cursor.
     *
     * @param dir Required. The direction of lines to select: -1 for up, 1 for
     * down
     * @param skip Required. If true, removes the active selection range
     */
    public void selectMoreLines(Integer dir, Boolean skip) throws JSException {
        mEditor.call("selectMoreLines", dir, skip);
    }

    /**
     * Selects the text from the current position of the document until where a
     * "page down" finishes.
     */
    public void selectPageDown() throws JSException {
        mEditor.call("selectPageDown");
    }

    /**
     * Selects the text from the current position of the document until where a
     * "page up" finishes.
     */
    public void selectPageUp() throws JSException {
        mEditor.call("selectPageUp");
    }

    /**
     * Sets the animated scroll option.
     *
     * @param shouldAnimate Required. True to enable animation.
     */
    public void setAnimatedScroll(Boolean shouldAnimate) throws JSException {
        mEditor.call("setAnimatedScroll", shouldAnimate);
    }

    /**
     * Specifies whether to use behaviors or not. "Behaviors" in this case is
     * the auto-pairing of special characters, like quotation marks,
     * parenthesis, or brackets.
     *
     * @param enabled Required. Enables or disables behaviors
     */
    public void setBehavioursEnabled(Boolean enabled) throws JSException {
        mEditor.call("setBehavioursEnabled", enabled);
    }

    /**
     * sets the Display Indent Guides option
     *
     * @param indentGuides Required. The Indent guides to set.
     * @return True on success.
     */
    public void setDisplayIndentGuides(Boolean indentGuides) throws JSException {
        mEditor.call("setDisplayIndentGuides", indentGuides);
    }

    /**
     * Sets the delay(in milliseconds) of the mouse drag.
     *
     * @param dragDelay Required. A value indicating the new delay
     */
    public void setDragDelay(Integer dragDelay) throws JSException {
        mEditor.call("setDragDelay", dragDelay);
    }

    /**
     * sets the Fade Fold Widgets
     *
     * @param fade true to enable fade fold
     */
    public void setFadeFoldWidgets(Boolean fade) throws JSException {
        mEditor.call("setFadeFoldWidgets", fade);
    }

    /**
     * Set a new font size(in pixels) for the editor text.
     *
     * @param size
     */
    public void setFontSize(Integer size) throws JSException {
        mEditor.call("setFontSize", size);
    }

    /**
     * Determines whether or not the current line should be highlighted.
     *
     * @param shouldHighlight Required. Set to true to highlight the current
     * line
     */
    public void setHighlightActiveLine(Boolean shouldHighlight) throws JSException {
        mEditor.call("setHighlightActiveLine", shouldHighlight);
    }

    /**
     * Determines whether or not the current gutter line should be highlighted.
     *
     * @param shouldHighlight Required. Set to true to highlight the current
     * line
     */
    public void setHighlightGutterLine(Boolean shouldHighlight) throws JSException {
        mEditor.call("setHighlightGutterLine", shouldHighlight);
    }

    /**
     * Determines if the currently selected word should be highlighted.
     *
     * @param shouldHighlight Required. Set to true to highlight the current
     * line
     */
    public void setHighlightSelectedWord(Boolean shouldHighlight) throws JSException {
        mEditor.call("setHighlightSelectedWord", shouldHighlight);
    }

    /**
     * Sets a new key handler, such as "vim" or "windows".
     *
     * @param keyboardHandler Required. The new key handler
     */
    public void setKeyboardHandler(String keyboardHandler) throws JSException {
        mEditor.call("setKeyboardHandler", keyboardHandler);
    }

    /**
     * Pass in true to enable overwrites in your session, or false to disable.
     * If overwrites is enabled, any text you enter will type over any text
     * after it. If the value of overwrite changes, this function also emits the
     * changeOverwrite event.
     *
     * @param overwrite Required. Defines whether or not to set overwrites
     */
    public void setOverwrite(Boolean overwrite) throws JSException {
        mEditor.call("setOverwrite", overwrite);
    }

    /**
     * Sets the column defining where the print margin should be.
     *
     * @param showPrintMargin Required. Specifies the new print margin.
     */
    public void setPrintMarginColumn(Integer showPrintMargin) throws JSException {
        mEditor.call("setPrintMarginColumn", showPrintMargin);
    }

    /**
     * If readOnly is true, then the editor is set to read-only mode, and none
     * of the content can change.
     *
     * @param readOnly Required. Specifies whether the editor can be modified or
     * not
     */
    public void setReadOnly(Boolean readOnly) throws JSException {
        mEditor.call("setReadOnly", readOnly);
    }

    /**
     * Sets how fast the mouse scrolling should do.
     *
     * @param speed Required. A value indicating the new speed (in milliseconds)
     */
    public void setScrollSpeed(Integer speed) throws JSException {
        mEditor.call("setScrollSpeed", speed);
    }

    /**
     * Indicates how selections should occur. <br/>
     * By default, selections are set to "line". There are no other styles at
     * the moment, although this code change in the future. <br/>
     * This function also emits the 'changeSelectionStyle' event.
     *
     * @param style Required. The new selection style
     */
    public void setSelectionStyle(String style) throws JSException {
        mEditor.call("setSelectionStyle", style);
    }

    // Sets a new editsession to use. This method also emits the 'changeSession' event.
    //     setSession(EditSession session)
    /**
     * Indicates whether the fold widgets are shown or not.
     *
     * @param show Required. Specifies whether the fold widgets are shown.
     */
    public void setShowFoldWidgets(Boolean show) throws JSException {
        mEditor.call("setShowFoldWidgets", show);
    }

    /**
     * If showInvisibles is set to true, invisible characters—like spaces or new
     * lines—are show in the editor.
     *
     * @param showInvisibles
     */
    public void setShowInvisibles(Boolean showInvisibles) throws JSException {
        mEditor.call("setShowInvisibles", showInvisibles);
    }

    /**
     * If showPrintMargin is set to true, the print margin is shown in the
     * editor .
     *
     * @param showPrintMargin Required. Specifies whether or not to show
     * invisible characters.
     */
    public void setShowPrintMargin(Boolean showPrintMargin) throws JSException {
        mEditor.call("setShowPrintMargin", showPrintMargin);
    }

    /**
     * Sets a new theme for the editor. theme should exist , and be a directory
     * path, like ace/theme/textmate.
     *
     * @param theme Required. The path to a theme.
     */
    public void setTheme(String theme) throws JSException {
        mEditor.call("setTheme(\"" + theme + "\");");
    }

    /**
     * Sets the current document to value.
     *
     * @param val Required. The new value to set for the document.
     * @param cursorPos Required. Where to set the new value. undefined or 0 is
     * selectAll, -1 is at the document start, and 1 is at the end
     * @return
     */
    public String setValue(String val, Integer cursorPos) throws JSException {
        return (String) mEditor.call("setValue", val, cursorPos);
    }

    /**
     * Specifies whether to use wrapping behaviors or not, i.e.automatically
     * wrapping the selection with characters such as brackets when such a
     * character is typed in.
     *
     * @param enabled true if wrap behaviors should be enabled.
     */
    public void setWrapBehavioursEnabled(Boolean enabled) throws JSException {
        mEditor.call("setWrapBehavioursEnabled", enabled);
    }

    /**
     * <strong>Undocumented</strong>
     */
    public void sortLines() throws JSException {
        mEditor.call("sortLines");
    }

    /**
     * Given the currently selected range ,this function either comments all the
     * lines, or uncomments all of them.
     */
    public void toggleCommentLines() throws JSException {
        mEditor.call("toggleCommentLines");
    }

    /**
     * Sets the value of overwrite to the opposite of whatever it currently is.
     */
    public void toggleOverwrite() throws JSException {
        mEditor.call("toggleOverwrite");
    }

    /**
     * Converts the current selection entirely into lowercase.
     */
    public void toLowerCase() throws JSException {
        mEditor.call("toLowerCase");
    }

    /**
     * Converts the current selection entirely into uppercase.
     */
    public void toUpperCase() throws JSException {
        mEditor.call("toUpperCase");
    }

    /**
     * Transposes current line.
     */
    public void transposeLetters() throws JSException {
        mEditor.call("transposeLetters");
    }

    /**
     * Transposes the selected ranges.
     *
     * @param dir Required. The direction to rotate selections.
     */
    public void transposeSelections(Integer dir) throws JSException {
        mEditor.call("setWrapBehavioursEnabled", dir);
    }

    /**
     * Perform an undo operation on the document , reverting the last change.
     */
    public void undo() throws JSException {
        mEditor.call("undo");
    }

    /**
     * Removes the class style from the editor.
     *
     * @param style Required.
     */
    @Deprecated
    public void unsetStyle(JSObject style) throws JSException {
        mEditor.call("unsetStyle", style);
    }

    /**
     * Updates the cursor and marker layers. public void
     */
    public void updateSelectionMarkers() throws JSException {
        mEditor.call("updateSelectionMarkers");
    }
}
