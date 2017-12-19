package com.io;

import com.io.contracts.MouseIO;

import java.awt.*;
import java.awt.event.InputEvent;

public class MouseIOImpl implements MouseIO {
    private Robot robot;

    public MouseIOImpl(Robot robot) throws AWTException {
        this.robot = robot;
    }

    @Override
    public void rightClick(int x, int y) {
        performClick(x, y, InputEvent.BUTTON3_DOWN_MASK);
    }

    @Override
    public void middleClick(int x, int y) {
        performClick(x, y, InputEvent.BUTTON2_DOWN_MASK);
    }

    @Override
    public void leftClick(int x, int y) {
        performClick(x, y, InputEvent.BUTTON1_DOWN_MASK);
    }

    private void performClick(int x, int y, int button) {
        robot.mouseMove(x, y);
        robot.mousePress(button);
        robot.mouseRelease(button);
    }
}
