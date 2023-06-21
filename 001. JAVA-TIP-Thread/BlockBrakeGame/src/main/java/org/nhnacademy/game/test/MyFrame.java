package org.nhnacademy.game.test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MyFrame extends JFrame {

    //constant
    static int BALL_WIDTH = 20;
    static int BALL_HEIGHT = 20;
    static int BLOCK_ROWS = 5;
    static int BLOCK_COLUMNS = 10;
    static int BLOCK_WIDTH = 40;
    static int BLOCK_HEIGHT = 20;
    static int BLOCK_GAP = 3;
    static int BAR_WIDTH = 80;
    static int BAR_HEIGHT = 20;
    static int CANVAS_WIDTH = 400 + (BLOCK_GAP * BLOCK_COLUMNS) - BLOCK_GAP;
    static int CANVAS_HEIGHT = 600;

    //variable
    static MyPanel myPanel = null;
    static int score = 0;
    static Timer timer = null;
    static Block[][] blocks = new Block[BLOCK_ROWS][BLOCK_COLUMNS];
    static Bar bar = new Bar();
    static Ball ball = new Ball();
    static int barXTarget = bar.x;
    static int dir = 0;
    static int ballSpeed = 5;
    static boolean isGameFinish = false;


    public MyFrame(String title) {
        super(title);
        this.setVisible(true);
        this.setSize(CANVAS_WIDTH, CANVAS_HEIGHT);
        this.setLocation(400, 300);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initData();

        myPanel = new MyPanel();
        this.add("Center", myPanel);

        setKeyListener();
        startTimer();
    }
    public void initData() {
        for(int i=0; i<BLOCK_ROWS; i++) {
            for(int j=0; j<BLOCK_COLUMNS; j++) {
                blocks[i][j] = new Block();
                blocks[i][j].x = BLOCK_WIDTH*j + BLOCK_GAP*j;
                blocks[i][j].y = 100 + BLOCK_HEIGHT*i + BLOCK_GAP*i;
                blocks[i][j].width = BLOCK_WIDTH;
                blocks[i][j].height = BLOCK_HEIGHT;
                blocks[i][j].color = 4 - i;
                blocks[i][j].isHidden = false;
            }
        }
    }
    public void setKeyListener() {
        this.addKeyListener( new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if( e.getKeyCode() == KeyEvent.VK_LEFT ) {
                    System.out.println("Pressed Left Key");
                    barXTarget -= 20;
                    if( bar.x < barXTarget) {
                        barXTarget = bar.x;
                    }
                }
                else if( e.getKeyCode() == KeyEvent.VK_RIGHT ) {
                    System.out.println("Pressed Right Key");
                    barXTarget += 20;
                    if( bar.x > barXTarget) {
                        barXTarget = bar.x;
                    }
                }
            }
        });
    }
    public void startTimer() {
        timer = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                movement();
                checkCollision();
                checkCollisionBlock();
                myPanel.repaint();

                isGameFinish();

            }
        });
        timer.start();
    }
    public void isGameFinish() {
        int count = 0;
        for(int i=0; i<BLOCK_ROWS; i++) {
            for(int j=0; j<BLOCK_COLUMNS; j++) {
                Block block = blocks[i][j];
                if( block.isHidden )
                    count++;
            }
        }
        if( count == BLOCK_ROWS * BLOCK_COLUMNS) {
            isGameFinish = true;
        }
    }
    public void movement() {
        if( bar.x < barXTarget) {
            bar.x += 5;
        }else if( bar.x > barXTarget ) {
            bar.x -= 5;
        }

        if(dir==0) {
            ball.x += ballSpeed;
            ball.y -= ballSpeed;
        }else if(dir==1) {
            ball.x += ballSpeed;
            ball.y += ballSpeed;
        }else if(dir==2) {
            ball.x -= ballSpeed;
            ball.y -= ballSpeed;
        }else if(dir==3) {
            ball.x -= ballSpeed;
            ball.y += ballSpeed;
        }

    }
    public boolean duplRect(Rectangle rect1, Rectangle rect2) {
        return rect1.intersects(rect2);
    }
    public void checkCollision() {
        if(dir==0) {
            //Wall
            if(ball.y < 0) {
                dir = 1;
            }
            if(ball.x>CANVAS_WIDTH-BALL_WIDTH) {
                dir = 2;
            }


        }else if(dir==1) {
            if(ball.y > CANVAS_HEIGHT-BALL_HEIGHT-BALL_HEIGHT) {
                dir = 0;
                ball.x = CANVAS_WIDTH/2 - BALL_WIDTH/2;
                ball.y = CANVAS_HEIGHT/2 - BALL_HEIGHT/2;
                score = 0;
            }
            if(ball.x > CANVAS_WIDTH-BALL_WIDTH) {
                dir = 3;
            }

            if( ball.getBottomCenter().y >= bar.y ) {
                if( duplRect(new Rectangle(ball.x, ball.y, ball.width, ball.height),
                        new Rectangle(bar.x, bar.y, bar.width, bar.height)) ) {
                    dir = 0;
                }
            }
        }else if(dir==2) {
            if( ball.y < 0 ) {
                dir = 3;
            }
            if( ball.x < 0) {
                dir = 0;
            }

        }else if(dir==3) {

            if( ball.y > CANVAS_HEIGHT-BALL_HEIGHT-BALL_HEIGHT) {
                dir = 2;

                dir = 0;
                ball.x = CANVAS_WIDTH/2 - BALL_WIDTH/2;
                ball.y = CANVAS_HEIGHT/2 - BALL_HEIGHT/2;
                score = 0;
            }
            if(ball.x < 0) {
                dir = 1;
            }
            if( ball.getBottomCenter().y >= bar.y ) {
                if( duplRect(new Rectangle(ball.x, ball.y, ball.width, ball.height),
                        new Rectangle(bar.x, bar.y, bar.width, bar.height)) ) {
                    dir = 2;
                }
            }
        }
    }
    public void checkCollisionBlock() {
        for(int i=0; i<BLOCK_ROWS; i++) {
            for(int j=0; j<BLOCK_COLUMNS; j++) {
                Block block = blocks[i][j];
                if(block.isHidden == false) {
                    if(dir==0) {
                        if( duplRect(new Rectangle(ball.x, ball.y, ball.width, ball.height),
                                new Rectangle(block.x, block.y, block.width, block.height)) ) {
                            if( ball.x > block.x + 2 &&
                                    ball.getRightCenter().x <= block.x + block.width - 2) {
                                dir = 1;
                            }else {
                                dir = 2;
                            }
                            block.isHidden = true;
                            if(block.color==0) {
                                score += 10;
                            }else if(block.color==1) {
                                score += 20;
                            }else if(block.color==2) {
                                score += 30;
                            }else if(block.color==3) {
                                score += 40;
                            }else if(block.color==4) {
                                score += 50;
                            }
                        }
                    }
                    else if(dir==1) {
                        if( duplRect(new Rectangle(ball.x, ball.y, ball.width, ball.height),
                                new Rectangle(block.x, block.y, block.width, block.height)) ) {
                            if( ball.x > block.x + 2 &&
                                    ball.getRightCenter().x <= block.x + block.width - 2) {
                                dir = 0;
                            }else {
                                dir = 3;
                            }
                            block.isHidden = true;
                            if(block.color==0) {
                                score += 10;
                            }else if(block.color==1) {
                                score += 20;
                            }else if(block.color==2) {
                                score += 30;
                            }else if(block.color==3) {
                                score += 40;
                            }else if(block.color==4) {
                                score += 50;
                            }
                        }
                    }
                    else if(dir==2) {
                        if( duplRect(new Rectangle(ball.x, ball.y, ball.width, ball.height),
                                new Rectangle(block.x, block.y, block.width, block.height)) ) {
                            if( ball.x > block.x + 2 &&
                                    ball.getRightCenter().x <= block.x + block.width - 2) {
                                dir = 3;
                            }else {
                                dir = 0;
                            }
                            block.isHidden = true;
                            if(block.color==0) {
                                score += 10;
                            }else if(block.color==1) {
                                score += 20;
                            }else if(block.color==2) {
                                score += 30;
                            }else if(block.color==3) {
                                score += 40;
                            }else if(block.color==4) {
                                score += 50;
                            }
                        }
                    }
                    else if(dir==3) {
                        if( duplRect(new Rectangle(ball.x, ball.y, ball.width, ball.height),
                                new Rectangle(block.x, block.y, block.width, block.height)) ) {
                            if( ball.x > block.x + 2 &&
                                    ball.getRightCenter().x <= block.x + block.width - 2) {
                                dir = 2;
                            }else {
                                dir = 1;
                            }
                            block.isHidden = true;
                            if(block.color==0) {
                                score += 10;
                            }else if(block.color==1) {
                                score += 20;
                            }else if(block.color==2) {
                                score += 30;
                            }else if(block.color==3) {
                                score += 40;
                            }else if(block.color==4) {
                                score += 50;
                            }
                        }
                    }
                }
            }
        }
    }
}
