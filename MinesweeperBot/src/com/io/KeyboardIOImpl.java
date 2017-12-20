package com.io;

import com.io.contracts.KeyboardIO;

import java.awt.*;
import java.awt.event.KeyEvent;

public class KeyboardIOImpl implements KeyboardIO {
    private Robot robot;

    public KeyboardIOImpl(Robot robot) throws AWTException {
        this.robot = robot;
    }


    @Override
    public void resetGame() {
        robot.keyPress(KeyEvent.VK_F2);
        robot.keyRelease(KeyEvent.VK_F2);
    }
}
