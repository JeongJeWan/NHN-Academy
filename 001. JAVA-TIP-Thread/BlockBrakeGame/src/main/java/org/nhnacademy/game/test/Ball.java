package org.nhnacademy.game.test;

import java.awt.*;

public class Ball {
    int x = MyFrame.CANVAS_WIDTH/2 - MyFrame.BALL_WIDTH/2;
    int y = MyFrame.CANVAS_HEIGHT/2 - MyFrame.BALL_HEIGHT/2;
    int width = MyFrame.BALL_WIDTH;
    int height = MyFrame.BALL_HEIGHT;

    Point getCenter() {
        return new Point( x + (MyFrame.BALL_WIDTH/2), y + (MyFrame.BALL_HEIGHT/2));
    }
    Point getBottomCenter() {
        return new Point( x + (MyFrame.BALL_WIDTH/2), y + (MyFrame.BALL_HEIGHT));
    }
    Point getTopCenter() {
        return new Point( x + (MyFrame.BALL_WIDTH/2), y);
    }
    Point getLeftCenter() {
        return new Point( x, y + (MyFrame.BALL_HEIGHT/2));
    }
    Point getRightCenter() {
        return new Point( x + (MyFrame.BALL_WIDTH), y + (MyFrame.BALL_HEIGHT/2));
    }
}
