package org.nhnacademy.game.test;

import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel { //VANVAS for Draw!
    public MyPanel() {
        this.setSize(MyFrame.CANVAS_WIDTH, MyFrame.CANVAS_HEIGHT);
        this.setBackground(Color.BLACK);
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;

        drawUI( g2d );
    }
    private void drawUI(Graphics2D g2d) {
        //draw Blocks
        for(int i=0; i<MyFrame.BLOCK_ROWS; i++) {
            for(int j=0; j<MyFrame.BLOCK_COLUMNS; j++) {
                if(MyFrame.blocks[i][j].isHidden) {
                    continue;
                }
                if(MyFrame.blocks[i][j].color==0) {
                    g2d.setColor(Color.WHITE);
                }
                else if(MyFrame.blocks[i][j].color==1) {
                    g2d.setColor(Color.YELLOW);
                }
                else if(MyFrame.blocks[i][j].color==2) {
                    g2d.setColor(Color.BLUE);
                }
                else if(MyFrame.blocks[i][j].color==3) {
                    g2d.setColor(Color.MAGENTA);
                }
                else if(MyFrame.blocks[i][j].color==4) {
                    g2d.setColor(Color.RED);
                }
                g2d.fillRect(MyFrame.blocks[i][j].x, MyFrame.blocks[i][j].y,
                        MyFrame.blocks[i][j].width, MyFrame.blocks[i][j].height);
            }

            //draw score
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("TimesRoman", Font.BOLD, 20));
            g2d.drawString("score : " + MyFrame.score, MyFrame.CANVAS_WIDTH/2 - 30, 20);
            if( MyFrame.isGameFinish ) {
                g2d.setColor(Color.RED);
                g2d.drawString("Game Finished!", MyFrame.CANVAS_WIDTH/2 - 55, 50);
            }

            //draw Ball
            g2d.setColor(Color.WHITE);
            g2d.fillOval(MyFrame.ball.x, MyFrame.ball.y, MyFrame.BALL_WIDTH, MyFrame.BALL_HEIGHT);

            //draw Bar
            g2d.setColor(Color.WHITE);
            g2d.fillRect(MyFrame.bar.x, MyFrame.bar.y, MyFrame.bar.width, MyFrame.bar.height);
        }
    }
}
