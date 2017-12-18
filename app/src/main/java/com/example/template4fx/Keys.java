package com.example.template4fx;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;

public class Keys
{
    public static final KeyCombination ALT_LEFT = new KeyCodeCombination( KeyCode.LEFT, KeyCombination.ALT_DOWN );

    public static final KeyCombination ALT_RIGHT = new KeyCodeCombination( KeyCode.RIGHT, KeyCombination.ALT_DOWN );

    public static final KeyCombination CTRL_E = new KeyCodeCombination( KeyCode.E, KeyCombination.CONTROL_DOWN );

    public static final KeyCombination F5 = new KeyCodeCombination( KeyCode.F5 );

    public static final KeyCombination ENTER = new KeyCodeCombination( KeyCode.ENTER );

    public static final KeyCombination ESCAPE = new KeyCodeCombination( KeyCode.ESCAPE );

    public static final KeyCombination UP = new KeyCodeCombination( KeyCode.UP );

    public static final KeyCombination DOWN = new KeyCodeCombination( KeyCode.DOWN );

    public static final KeyCombination TAB = new KeyCodeCombination( KeyCode.TAB );

    public static final KeyCombination SHIFT_TAB = new KeyCodeCombination( KeyCode.TAB, KeyCombination.SHIFT_DOWN );
}
