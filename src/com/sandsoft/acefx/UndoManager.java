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
public class UndoManager {

    private final JSObject mUndoManager;

    /**
     * Manipulate undo manager commands
     *
     * @param undoManager JavaScript object of undo manager.
     */
    public UndoManager(JSObject undoManager) {
        mUndoManager = undoManager;
    }

    /**
     * Provides a means for implementing your own undo manager. <br/>
     * options has one property, args: an Array, with two elements: <br/>
     * args[0] is an array of deltas. <br/>
     * args[1] is the document to associate with.
     *
     * @param options Required. Contains additional properties.
     */
    private void execute(Object options) {
        mUndoManager.call("execute", options);
    }

    /**
     * Returns true if there are redo operations left to perform.
     *
     * @return true if there are redo operations left to perform.
     */
    public boolean hasRedo() {
        return (boolean) mUndoManager.call("hasRedo");
    }

    /**
     * Returns true if there are undo operations left to perform.
     *
     * @return true if there are undo operations left to perform.
     */
    public boolean hasUndo() {
        return (boolean) mUndoManager.call("hasUndo");
    }

    /**
     * Perform a redo operation on the document, re-implementing the last
     * change.
     *
     * @param dontSelect Required. If true, doesn't select the range of where
     * the change occurred.
     */
    public void redo(Boolean dontSelect) {
        mUndoManager.call("redo", dontSelect);
    }

    /**
     * Destroys the stack of undo and redo redo operations.
     */
    public void reset() {
        mUndoManager.call("reset");
    }

    /**
     * Perform an undo operation on the document, reverting the last change.
     *
     * @param dontSelect Required. If true, doesn't select the range of where
     * the change occurred.
     */
    public void undo(Boolean dontSelect) {
        mUndoManager.call("undo", dontSelect);
    }
}