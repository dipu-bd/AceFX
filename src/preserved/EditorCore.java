/**
 * THIS IS A PRESERVED COPY OF EDITOR CORE *
 */

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
package preserved;
 
import javafx.concurrent.Worker;
import javafx.geometry.Point2D;
import javafx.scene.web.WebEngine;

/**
 *
 * @author Sudipto Chandra.
 */
public class EditorCore {

    /**
     * Options for find and replace text.
     */
    public class Options {

        public boolean backwars = false;
        public boolean wrap = false;
        public boolean caseSensitive = false;
        public boolean wholeWord = false;
        public boolean regExp = false;

        public Options() {
        }
    }

    /**
     * Constructor.
     *
     * @param engine WebEngine to pass javascript commands.
     */
    public EditorCore(WebEngine engine) {
        webEngine = engine;
    }

    //web engine
    private WebEngine webEngine;

    /**
     * Checks if the editor is ready for interaction.
     *
     * @return True if worker is successfully loaded.
     */
    public boolean isReady() {
        return (webEngine != null && webEngine.getLoadWorker().getState() == Worker.State.SUCCEEDED);
    }

    /**
     * Executes a java-script and returns result.
     *
     * @param script Script to execute.
     * @return Result return by the function.
     */
    protected final Object execute(String script) {
        if (isReady()) {
            return (Object) webEngine.executeScript(script);
        }
        return null;
    }

    /**
     * formats a content into a string passable inside quote.
     *
     * @param content text to format
     * @return formated content
     */
    private String formatText(String content) {
        return content
                .replace("\\", "\\\\") //replace backslash
                .replace("\"", "\\\"") //replace quote
                .replace("\r", "") //replace return
                .replace("\n", "\\n") //replace new line
                .replace("\t", "\\t") //replace tab 
                ;
    }

    /**
     * Formats the boolean value and return JavaScript string.
     *
     * @param val Value to convert.
     * @return JavaScript supported string.
     */
    private String formatBool(boolean val) {
        return val ? "true" : "false";
    }

    /**
     * Formats other value and return JavaScript string.
     *
     * @param num Number to convert.
     * @return JavaScript supported string.
     */
    private String formatOther(int num) {
        return String.valueOf(num);
    }

    /* 
     //Adds the selection and cursor.
     Range addSelectionMarker(Range orientedRange)     
     */
    /**
     * Aligns the cursors or selected text.
     */
    public void alignCursors() {
        execute("editor.alignCursors();");
    }

    /**
     * Out-dents the current line.
     */
    public void blockOutdent() {
        execute("editor.alignCursors();");
    }

    /**
     * Blurs the current textInput.
     */
    public void blur() {
        execute("editor.blur();");
    }

    /**
     * Attempts to center the current selection on the screen.
     */
    public void centerSelection() {
        execute("editor.centerSelection();");
    }

    /**
     * Empties the selection (by de-selecting it). This function also emits the
     * 'changeSelection' event.
     */
    public void clearSelection() {
        execute("editor.clearSelection();");
    }

    /**
     * Copies all the selected lines down one row.
     *
     * @return
     */
    public int copyLinesDown() {
        return (int) execute("editor.copyLinesDown();");
    }

    /**
     * Copies all the selected lines up one row.
     *
     * @return
     */
    public int copyLinesUp() {
        return (int) execute("editor.copyLinesUp();");
    }

    /**
     * Duplicate the selected text.
     */
    public void duplicateSelection() {
        execute("editor.duplicateSelection();");
    }

    /**
     * Removes all the selections except the last added one.
     */
    public void exitMultiSelectMode() {
        execute("editor.exitMultiSelectMode();");
    }

    /**
     * Attempts to find needle within the document. For more information on
     * options, see Search.
     *
     * @param needle Required. The text to search for (optional)
     * @param options Required. An object defining various search properties
     * @param animate Required. If true animate scrolling
     */
    public void find(String needle, Options options, boolean animate) {
        execute("editor.find(\"" + formatText(needle) + "\","
                + "{ backwards: " + formatBool(options.backwars)
                + ", wrap: " + formatBool(options.wrap)
                + ", caseSensitive: " + formatBool(options.caseSensitive)
                + ", wholeWord: " + formatBool(options.wholeWord)
                + ", regExp: " + formatBool(options.regExp)
                + "}, " + formatBool(animate) + ");");
    }

    /*
     //Finds and selects all the occurences of needle.
     public int findAll(String needle, Object options, boolean keeps) {   }
     */
    /**
     * Performs another search for needle in the document. For more information
     * on options, see Search.
     *
     * @param options Required. An object defining various search properties
     * @param animate Required. If true animate scrolling
     */
    public void findNext(Options options, boolean animate) {
        execute("editor.findNext({"
                + "  backwards: " + formatBool(options.backwars)
                + ", wrap: " + formatBool(options.wrap)
                + ", caseSensitive: " + formatBool(options.caseSensitive)
                + ", wholeWord: " + formatBool(options.wholeWord)
                + ", regExp: " + formatBool(options.regExp)
                + "}, " + formatBool(animate) + "\");");
    }

    /**
     * Performs a search for needle backwards. For more information on options,
     * see Search.
     *
     * @param options Required. An object defining various search properties
     * @param animate Required. If true animate scrolling
     */
    public void findPrevious(Options options, boolean animate) {
        execute("editor.findPrevious({"
                + "  backwards: " + formatBool(options.backwars)
                + ", wrap: " + formatBool(options.wrap)
                + ", caseSensitive: " + formatBool(options.caseSensitive)
                + ", wholeWord: " + formatBool(options.wholeWord)
                + ", regExp: " + formatBool(options.regExp)
                + "}, " + formatBool(animate) + "\");");
    }

    /**
     * Brings the current textInput into focus.
     */
    public void focus() {
        execute("editor.focus();");
    }

    /**
     * Executes a command for each selection range.
     *
     * @param cmd
     * @param args
     */
    public void forEachSelection(String cmd, String args) {
        execute("editor.forEachSelection(\"" + formatText(cmd) + "\",\"" + formatText(args) + "\");");
    }

    /**
     * Returns true if the behaviors are currently enabled. "Behaviors" in this
     * case is the auto-pairing of special characters, like quotation marks,
     * parenthesis, or brackets.
     *
     * @return
     */
    public boolean getBehavioursEnabled() {
        return (boolean) execute("editor.getBehavioursEnabled();");
    }

    /**
     * Returns the string of text currently highlighted.
     *
     * @return
     */
    public String getCopyText() {
        return (String) execute("editor.getCopyText();");
    }

    /**
     * Gets the current position of the cursor.
     *
     * @return
     */
    public Point2D getCursorPosition() {
        int row = (int) execute("editor.getCursorPosition().row");
        int col = (int) execute("editor.getCursorPosition().column");
        return new Point2D(row, col);
    }

    /**
     * Returns the screen position of the cursor.
     *
     * @return
     */
    public int getCursorPositionScreen() {
        return (int) execute("editor.getCursorPositionScreen();");
    }

    /**
     * Returns the index of the first visible row.
     *
     * @return
     */
    public int getFirstVisibleRow() {
        return (int) execute("editor.getFirstVisibleRow();");
    }

    /**
     * Returns true if current lines are always highlighted.
     *
     * @return
     */
    public boolean getHighlightActiveLine() {
        return (boolean) execute("editor.getHighlightActiveLine();");
    }

    /**
     * Returns true if currently highlighted words are to be highlighted.
     *
     * @return
     */
    public boolean getHighlightSelectedWord() {
        return (boolean) execute("editor.getHighlightSelectedWord();");
    }

    /**
     * Returns the keyboard handler, such as "vim" or "windows".
     *
     * @return
     */
    public String getKeyboardHandler() {
        return (String) execute("editor.getKeyboardHandler();");
    }

    /**
     * Returns an object containing all the search options. For more information
     * on options, see Search.
     */
    //public Object getLastSearchOptions() { }
    /**
     * Returns the index of the last visible row.
     *
     * @return
     */
    public int getLastVisibleRow() {
        return (int) execute("editor.getLastVisibleRow();");
    }

    /**
     * Works like EditSession.getTokenAt(), except it returns a number.
     *
     * @param row
     * @param column
     * @return
     */
    public int getNumberAt(int row, int column) {
        return (int) execute("editor.getNumberAt(" + formatOther(row) + "," + formatOther(column) + ");");
    }

    /**
     * Returns true if overwrites are enabled; false otherwise.
     *
     * @return
     */
    public boolean getOverwrite() {
        return (boolean) execute("editor.getOverwrite();");
    }

    /**
     * Returns the column number of where the print margin is.
     *
     * @return
     */
    public int getPrintMarginColumn() {
        return (int) execute("editor.getPrintMarginColumn();");
    }

    /**
     * Returns true if the editor is set to read-only mode.
     *
     * @return
     */
    public boolean getReadOnly() {
        return (boolean) execute("editor.getReadOnly();");
    }

    /**
     * Returns the value indicating how fast the mouse scroll speed is (in
     * milliseconds).
     *
     * @return
     */
    public int getScrollSpeed() {
        return (int) execute("editor.getScrollSpeed();");
    }

    /**
     * Returns the currently highlighted selection.
     *
     * @return
     */
    public String getSelection() {
        return (String) execute("editor.getSelection();");
    }

    //Returns the Range for the selected text.
    //public Range getSelectionRange()  { }
    /**
     * Returns the current selection style.
     *
     * @return
     */
    public String getSelectionStyle() {
        return (String) execute("editor.getSelectionStyle();");
    }

    //Returns the current session being used.
    //public EditSession getSession()  { }
    /**
     * Returns true if the fold widgets are shown.
     *
     * @return
     */
    public boolean getShowFoldWidgets() {
        return (boolean) execute("editor.getShowFoldWidgets();");
    }

    /**
     * Returns true if invisible characters are being shown.
     *
     * @return
     */
    public boolean getShowInvisibles() {
        return (boolean) execute("editor.getShowInvisibles();");
    }

    /**
     * Returns true if the print margin is being shown.
     *
     * @return
     */
    public boolean getShowPrintMargin() {
        return (boolean) execute("editor.getShowPrintMargin();");
    }

    /**
     * Returns the path of the current theme.
     *
     * @return
     */
    public String getTheme() {
        return (String) execute("editor.getTheme();");
    }

    /**
     * Returns the current session's content.
     *
     * @return
     */
    public String getValue() {
        return (String) execute("editor.getValue();");
    }

    /**
     * Returns true if the wrapping behaviors are currently enabled.
     *
     * @return
     */
    public boolean getWrapBehavioursEnabled() {
        return (boolean) execute("editor.getWrapBehavioursEnabled();");
    }

    /**
     * Moves the cursor to the specified line number, and also into the
     * indicated column.
     *
     * @param lineNumber
     * @param column
     * @param animate
     */
    public void gotoLine(int lineNumber, int column, boolean animate) {
        execute("editor.gotoLine(" + formatOther(lineNumber) + ","
                + formatOther(column) + "," + formatBool(animate) + ");");
    }

    /**
     * Shifts the document to wherever "page down" is, as well as moving the
     * cursor position.
     */
    public void gotoPageDown() {
        execute("editor.gotoPageDown();");
    }

    /**
     * Shifts the document to wherever "page up" is, as well as moving the
     * cursor position.
     */
    public void gotoPageUp() {
        execute("editor.gotoPageUp();");
    }

    /**
     * Indents the current line.
     */
    public void indent() {
        execute("editor.indent();");
    }

    /**
     * Inserts text into wherever the cursor is pointing.
     *
     * @param text
     */
    public void insert(String text) {
        execute("editor.insert(\"" + formatText(text) + "\");");
    }

    /**
     * Returns true if the current textInput is in focus.
     *
     * @return
     */
    public boolean isFocused() {
        return (boolean) execute("editor.isFocused();");
    }

    /**
     * Indicates if the entire row is currently visible on the screen.
     *
     * @param row
     * @return
     */
    public boolean isRowFullyVisible(int row) {
        return (boolean) execute("editor.isRowFullyVisible(" + formatOther(row) + ");");
    }

    /**
     * Indicates if the row is currently visible on the screen.
     *
     * @param row
     * @return
     */
    public boolean isRowVisible(int row) {
        return (boolean) execute("editor.isRowVisible(" + formatOther(row) + ");");
    }

    /**
     * Moves the cursor's row and column to the next matching bracket.
     */
    //public void jumpToMatching(Object select) { }
    /**
     * If the character before the cursor is a number, this functions changes
     * its value by amount.
     *
     * @param amount
     */
    public void modifyNumber(int amount) {
        execute("editor.modifyNumber(" + formatOther(amount) + ");");
    }

    /**
     * Moves the cursor to the specified row and column. Note that this does not
     * de-select the current selection.
     *
     * @param row
     * @param column
     */
    public void moveCursorTo(int row, int column) {
        execute("editor.moveCursorTo(" + formatOther(row) + "," + formatOther(column) + ");");
    }

    /**
     * Moves the cursor to the position indicated by pos.row and pos.column.
     *
     * @param pos
     */
    public void moveCursorToPosition(Point2D pos) {
        execute("editor.moveCursorToPosition({"
                + "row : " + formatOther((int) pos.getX())
                + ", column:" + formatOther((int) pos.getY())
                + "});");
    }

    /**
     * Shifts all the selected lines down one row.
     *
     * @return
     */
    public int moveLinesDown() {
        return (int) execute("editor.moveLinesDown();");
    }

    /**
     * Shifts all the selected lines up one row.
     *
     * @return
     */
    public int moveLinesUp() {
        return (int) execute("editor.moveLinesUp();");
    }

    /**
     *
     */
    public void moveText() {
        execute("editor.moveText();");
    }

    /**
     * Moves the cursor down in the document the specified number of times. Note
     * that this does de-select the current selection.
     *
     * @param times
     */
    public void navigateDown(int times) {
        execute("editor.navigateDown(" + formatOther(times) + ");");
    }

    /**
     * Moves the cursor to the end of the current file. Note that this does
     * de-select the current selection.
     */
    public void navigateFileEnd() {
        execute("editor.navigateFileEnd();");
    }

    /**
     * Moves the cursor to the end of the current file. Note that this does
     * de-select the current selection.
     */
    public void navigateFileStart() {
        execute("editor.navigateFileStart();");
    }

    /**
     * Moves the cursor left in the document the specified number of times. Note
     * that this does de-select the current selection.
     *
     * @param times
     */
    public void navigateLeft(int times) {
        execute("editor.navigateLeft(" + formatOther(times) + ");");
    }

    /**
     * Moves the cursor to the end of the current line. Note that this does
     * de-select the current selection.
     */
    public void navigateLineEnd() {
        execute("editor.navigateLineEnd();");
    }

    /**
     * Moves the cursor to the start of the current line. Note that this does
     * de-select the current selection.
     */
    public void navigateLineStart() {
        execute("editor.navigateLineStart();");
    }

    /**
     * Moves the cursor right in the document the specified number of times.
     * Note that this does de-select the current selection.
     *
     * @param times
     */
    public void navigateRight(int times) {
        execute("editor.navigateRight(" + formatOther(times) + ");");
    }

    /**
     * Moves the cursor to the specified row and column. Note that this does
     * de-select the current selection.
     *
     * @param row
     * @param column
     */
    public void navigateTo(int row, int column) {
        execute("editor.navigateTo(" + formatOther(row) + "," + formatOther(column) + ");");
    }

    /**
     * Moves the cursor up in the document the specified number of times. Note
     * that this does de-select the current selection.
     *
     * @param times
     */
    public void navigateUp(int times) {
        execute("editor.navigateUp(" + formatOther(times) + ");");
    }

    /**
     * Moves the cursor to the word immediately to the left of the current
     * position. Note that this does de-select the current selection.
     */
    public void navigateWordLeft() {
        execute("editor.navigateWordLeft();");
    }

    /**
     * Moves the cursor to the word immediately to the right of the current
     * position. Note that this does de-select the current selection.
     */
    public void navigateWordRight() {
        execute("editor.navigateWordRight();");
    }

    /*
     //     onBlur() Undocumented
     //     onChangeAnnotation() Undocumented
     //     onChangeBackMarker() Undocumented
     //     onChangeBreakpoint() Undocumented
     //     onChangeFold() Undocumented
     //     onChangeFrontMarker() Undocumented
     //     onChangeMode() Undocumented
     //     onChangeWrapLimit() Undocumented
     //     onChangeWrapMode() Undocumented
     //     onCommandKey() Undocumented
     //     onCompositionEnd() Undocumented
     //     onCompositionStart() Undocumented
     //     onCompositionUpdate() Undocumented
     //     onCopy()
     //     Called whenever a text "copy" happens.
     //     onCursorChange()
     //     Emitted when the selection changes.
     //     onCut()
     //     Called whenever a text "cut" happens.
     //     onDocumentChange() Undocumented
     //     onFocus() Undocumented
     //     onPaste(String text)
     //     Called whenever a text "paste" happens.
     //     onScrollLeftChange() Undocumented
     //     onScrollTopChange() Undocumented
     //     onSelectionChange() Undocumented
     //     onTextInput() Undocumented
     //     onTokenizerUpdate() Undocumented
     */
    /**
     * Perform a redo operation on the document , reimplementing the last
     * change.
     */
    public void redo() {
        execute("editor.redo();");
    }

    /**
     * Removes words of text from the editor.A "word" is defined as a string of
     * characters bookended by whitespace.
     *
     * @param dir
     */
    public void remove(String dir) {
        execute("editor.remove(\"" + formatText(dir) + "\");");
    }

    /**
     * Removes all the lines in the current selection
     */
    public void removeLines() {
        execute("editor.removeLines();");
    }

    //Removes the selection marker
    //public void removeSelectionMarker(Range The) { }
    /**
     * Removes all the words to the right of the current selection , until the
     * end of the line.
     */
    public void removeToLineEnd() {
        execute("editor.removeToLineEnd();");
    }

    /**
     * Removes all the words to the left of the current selection , until the
     * start of the line.
     */
    public void removeToLineStart() {
        execute("editor.removeToLineStart();");
    }

    /**
     * Removes the word directly to the left of the current selection.
     */
    public void removeWordLeft() {
        execute("editor.removeWordLeft();");
    }

    /**
     * Removes the word directly to the right of the current selection.
     */
    public void removeWordRight() {
        execute("editor.removeWordRight();");
    }

    /**
     * Replaces the first occurance of options. needle with the value in
     * replacement.
     *
     * @param replacement
     * @param options
     */
    public void replace(String replacement, Options options) {
        execute("editor.replace(\"" + formatText(replacement) + "\","
                + "{ backwards: " + formatBool(options.backwars)
                + ", wrap: " + formatBool(options.wrap)
                + ", caseSensitive: " + formatBool(options.caseSensitive)
                + ", wholeWord: " + formatBool(options.wholeWord)
                + ", regExp: " + formatBool(options.regExp)
                + "});");
    }

    /**
     * Replaces all occurances of options.needle with the value in replacement.
     *
     * @param replacement
     * @param options
     */
    public void replaceAll(String replacement, Options options) {
        execute("editor.replaceAll(\"" + formatText(replacement) + "\","
                + "{ backwards: " + formatBool(options.backwars)
                + ", wrap: " + formatBool(options.wrap)
                + ", caseSensitive: " + formatBool(options.caseSensitive)
                + ", wholeWord: " + formatBool(options.wholeWord)
                + ", regExp: " + formatBool(options.regExp)
                + "});");
    }

    /**
     * Triggers a resize of the editor.
     *
     * @param force
     */
    public void resize(boolean force) {
        execute("editor.resize(" + formatBool(force) + ");");
    }

    /**
     *
     */
    public void revealRange() {
        execute("editor.revealRange();");
    }

    /**
     * Scrolls the document to wherever "page down" is , without changing the
     * cursor position.
     */
    public void scrollPageDown() {
        execute("editor.scrollPageDown();");
    }

    /**
     * Scrolls the document to wherever "page up" is , without changing the
     * cursor position.
     */
    public void scrollPageUp() {
        execute("editor.scrollPageUp();");
    }

    /**
     * Scrolls to a line. If center is true, it puts the line in middle of
     * screen(or attempts to).
     */
    //public void scrollToLine(int line, boolean center, boolean animate, Function callback) { }
    /**
     * Moves the editor to the specified row.
     *
     * @param row
     */
    public void scrollToRow(int row) {
        execute("editor.resize(" + formatOther(row) + ");");
    }

    /**
     * Selects all the text in editor.
     */
    public void selectAll() {
        execute("editor.selectAll();");
    }

    /**
     * Finds the next occurence of text in an active selection and adds it to
     * the selections.
     *
     * @param dir
     * @param skip
     */
    public void selectMore(int dir, boolean skip) {
        execute("editor.selectMore(" + formatOther(dir) + "," + formatBool(skip) + ");");
    }

    /**
     * Adds a cursor above or below the active cursor.
     *
     * @param dir
     * @param skip
     */
    public void selectMoreLines(int dir, boolean skip) {
        execute("editor.selectMoreLines(" + formatOther(dir) + "," + formatBool(skip) + ");");
    }

    /**
     * Selects the text from the current position of the document until where a
     * "page down" finishes.
     */
    public void selectPageDown() {
        execute("editor.selectPageDown();");
    }

    /**
     * Selects the text from the current position of the document until where a
     * "page up" finishes.
     */
    public void selectPageUp() {
        execute("editor.selectPageUp();");
    }

    /**
     *
     */
    public void setAnimatedScroll() {
        execute("editor.setAnimatedScroll();");
    }

    /**
     * Specifies whether to use behaviors or not. "Behaviors" in this case is
     * the auto-pairing of special characters, like quotation marks,
     * parenthesis, or brackets.
     *
     * @param enabled
     */
    public void setBehavioursEnabled(boolean enabled) {
        execute("editor.setBehavioursEnabled(" + formatBool(enabled) + ");");
    }

    /**
     *
     */
    public void setDisplayIndentGuides() {
        execute("editor.setDisplayIndentGuides();");
    }

    /**
     * Sets the delay(in milliseconds) of the mouse drag.
     *
     * @param dragDelay
     */
    public void setDragDelay(int dragDelay) {
        execute("editor.setDragDelay(" + formatOther(dragDelay) + ");");
    }

    /**
     *
     */
    public void setFadeFoldWidgets() {
        execute("editor.setFadeFoldWidgets();");
    }

    /**
     * Set a new font size(in pixels) for the editor text.
     *
     * @param size
     */
    public void setFontSize(int size) {
        execute("editor.setFontSize(" + formatOther(size) + ");");
    }

    /**
     * Determines whether or not the current line should be highlighted.
     *
     * @param shouldHighlight
     */
    public void setHighlightActiveLine(boolean shouldHighlight) {
        execute("editor.setHighlightActiveLine(" + formatBool(shouldHighlight) + ");");
    }

    /**
     *
     */
    public void setHighlightGutterLine() {
        execute("editor.setHighlightGutterLine();");
    }

    /**
     * Determines if the currently selected word should be highlighted.
     *
     * @param shouldHighlight
     */
    public void setHighlightSelectedWord(boolean shouldHighlight) {
        execute("editor.setHighlightSelectedWord(" + formatBool(shouldHighlight) + ");");
    }

    /**
     * Sets a new key handler, such as "vim" or "windows".
     *
     * @param keyboardHandler
     */
    public void setKeyboardHandler(String keyboardHandler) {
        execute("editor.setKeyboardHandler(\"" + formatText(keyboardHandler) + "\");");
    }

    /**
     * Pass in true to enable overwrites in your session, or false to disable.
     * If overwrites is enabled, any text you enter will type over any text
     * after it. If the value of overwrite changes, this function also emites
     * the changeOverwrite event.
     *
     * @param overwrite
     */
    public void setOverwrite(boolean overwrite) {
        execute("editor.setOverwrite(" + formatBool(overwrite) + ");");
    }

    /**
     * Sets the column defining where the print margin should be.
     *
     * @param showPrintMargin
     */
    public void setPrintMarginColumn(int showPrintMargin) {
        execute("editor.setPrintMarginColumn(" + formatOther(showPrintMargin) + ");");
    }

    /**
     * If readOnly is true, then the editor is set to read-only mode, and none
     * of the content can change.
     *
     * @param readOnly
     */
    public void setReadOnly(boolean readOnly) {
        execute("editor.setReadOnly(" + formatBool(readOnly) + ");");
    }

    /**
     * Sets how fast the mouse scrolling should do.
     *
     * @param speed
     */
    public void setScrollSpeed(int speed) {
        execute("editor.setScrollSpeed(" + formatOther(speed) + ");");
    }

    /**
     * Indicates how selections should occur.
     *
     * @param style
     */
    public void setSelectionStyle(String style) {
        execute("editor.setSelectionStyle(\"" + formatText(style) + "\");");
    }

    //Sets a new editsession to use.This method also emits the 'changeSession' event.
    //public void setSession(EditSession session) { }
    //
    /**
     * Indicates whether the fold widgets are shown or not.
     *
     * @param show
     */
    public void setShowFoldWidgets(boolean show) {
        execute("editor.setShowFoldWidgets(" + formatBool(show) + ");");
    }

    /**
     * If showInvisibles is set to true, invisible characters—like spaces or new
     * lines—are show in the editor.
     *
     * @param showInvisibles
     */
    public void setShowInvisibles(boolean showInvisibles) {
        execute("editor.setShowInvisibles(" + formatBool(showInvisibles) + ");");
    }

    /**
     * If showPrintMargin is set to true, the print margin is shown in the
     * editor .
     *
     * @param showPrintMargin
     */
    public void setShowPrintMargin(boolean showPrintMargin) {
        execute("editor.setShowPrintMargin(" + formatBool(showPrintMargin) + ");");
    }

    /**
     * Adds a new class, style, to the editor.
     *
     * @param style
     */
    public void setStyle(String style) {
        execute("editor.setStyle(\"" + formatText(style) + "\");");
    }

    /**
     * Sets a new theme for the editor. theme should exist , and be a directory
     * path, like ace/theme/textmate.
     *
     * @param theme
     */
    public void setTheme(String theme) {
        execute("editor.setTheme(\"ace/theme/" + theme + "\");");
    }

    /**
     * Sets the current document to val.
     *
     * @param val
     * @param cursorPos
     * @return
     */
    public String setValue(String val, int cursorPos) {
        return (String) execute("editor.setValue(\"" + formatText(val) + "\"," + formatOther(cursorPos) + ");");
    }

    /**
     * Specifies whether to use wrapping behaviors or not, i.e.automatically
     * wrapping the selection with characters such as brackets when such a
     * character is typed in.
     *
     * @param enabled
     */
    public void setWrapBehavioursEnabled(boolean enabled) {
        execute("editor.setWrapBehavioursEnabled(" + formatBool(enabled) + ");");
    }

    /**
     *
     */
    public void sortLines() {
        execute("editor.sortLines();");
    }

    /**
     * Given the currently selected range ,this function either comments all the
     * lines, or uncomments all of them.
     */
    public void toggleCommentLines() {
        execute("editor.toggleCommentLines();");
    }

    /**
     * Sets the value of overwrite to the opposite of whatever it currently is.
     */
    public void toggleOverwrite() {
        execute("editor.toggleOverwrite();");
    }

    /**
     * Converts the current selection entirely into lowercase.
     */
    public void toLowerCase() {
        execute("editor.toLowerCase();");
    }

    /**
     * Converts the current selection entirely into uppercase.
     */
    public void toUpperCase() {
        execute("editor.toUpperCase();");
    }

    /**
     * Transposes current line.
     */
    public void transposeLetters() {
        execute("editor.transposeLetters();");
    }

    /**
     * Transposes the selected ranges.
     *
     * @param dir
     */
    public void transposeSelections(int dir) {
        execute("editor.setWrapBehavioursEnabled(" + formatOther(dir) + ");");
    }

    /**
     * Perform an undo operation on the document , reverting the last change.
     */
    public void undo() {
        execute("editor.undo();");
    }

    /**
     * Removes the class style from the editor. public void
     *
     * @param style
     */
    public void unsetStyle(String style) {
        execute("editor.unsetStyle(\"" + formatText(style) + "\");");
    }

    /**
     * Updates the cursor and marker layers. public void
     */
    public void updateSelectionMarkers() {
        execute("editor.updateSelectionMarkers();");
    }

}
