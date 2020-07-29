package infiniteRunnerGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Background extends JPanel implements ActionListener, Runnable{

    Runner runner;
    Enemies log;
    Enemies log2;

    Image background1;
    Image background2;
    Image clearBackground;
    Image playScreenImg;
    Image charBackground;
    Image playButton;
    Image tryAgainButton;
    Timer time;

    Thread animator;

    boolean jump = false;
    boolean jumpFinish = false;
    boolean jumpPeak = false;
    boolean playScreen = true;
    boolean charSelection = false;
    boolean gameOn = false;
    boolean gameEnd = false;

    int yPos = 200;
    int highScore = 0;
    int highScoretwo = 0;
    int playerSwitch = 0;

    public Background(){
        runner = new Runner();
        log = new Enemies();
        log2 = new Enemies();

        addKeyListener(new AL());
        addMouseListener(new ML());

        setFocusable(true);
        setLayout(new GridLayout(3, 5));

        ImageIcon playScreenIcon = new ImageIcon("D:\\SteppingStones2020\\InfiniteRunnerTest\\src\\infiniteRunnerGame\\images\\playScreen.png");
        ImageIcon background1Icon1 = new ImageIcon("D:\\SteppingStones2020\\InfiniteRunnerTest\\src\\infiniteRunnerGame\\images\\background2.png");
        ImageIcon background1Icon2 = new ImageIcon("D:\\SteppingStones2020\\InfiniteRunnerTest\\src\\infiniteRunnerGame\\images\\background1.png");
        ImageIcon clearBackgroundIcon = new ImageIcon("D:\\SteppingStones2020\\InfiniteRunnerTest\\src\\infiniteRunnerGame\\images\\clearbackground.png");
        ImageIcon characterSelectionBackgroundIcon = new ImageIcon("D:\\SteppingStones2020\\InfiniteRunnerTest\\src\\infiniteRunnerGame\\images\\characrerSelectionBackGround.png");
        ImageIcon playButtonIcon = new ImageIcon("D:\\SteppingStones2020\\InfiniteRunnerTest\\src\\infiniteRunnerGame\\images\\playButton.png");
        ImageIcon tryAgainIcon = new ImageIcon("D:\\SteppingStones2020\\InfiniteRunnerTest\\src\\infiniteRunnerGame\\images\\tryAgainButton.png");

        clearBackground = clearBackgroundIcon.getImage();
        background1 = background1Icon1.getImage();
        background2= background1Icon2.getImage();
        charBackground = characterSelectionBackgroundIcon.getImage();
        playScreenImg = playScreenIcon.getImage();
        playButton = playButtonIcon.getImage();
        tryAgainButton = tryAgainIcon.getImage();

        time = new Timer(5, this);
        time.start();
    }

    public void paint(Graphics graphics){


        if(playScreen == true){
            super.paint(graphics);
            Graphics2D graphics2D = (Graphics2D) graphics;
            graphics2D.drawImage(playScreenImg, 0, 0, null);
            graphics2D.drawImage(playButton,630,140,null);

        }
        if(charSelection==true){
            super.paint(graphics);
            Graphics2D graphics2D = (Graphics2D) graphics;
            graphics2D.drawImage(charBackground, 0, 0, null);

        }
        if(gameOn == true) {
            super.paint(graphics);
            Graphics2D graphics2D = (Graphics2D) graphics;
            if (runner.characterDY == -1 && jump == false) {
                jump = true;
                animator = new Thread(this);
                animator.start();
            }

            //infinite background
            for (int i = 1; i < 500; i += 2) {
                graphics2D.drawImage(background2, (i * 1485) - runner.backgroundXTwo, 0, null); // please help me :( i need help :(
            }
            for (int i = 0; i < 500; i += 2) {
                graphics2D.drawImage(background1, (i * 1485) - runner.backgroundXTwo, 0, null);
            }

            //enemy spawning
            if ((log.enemyX == 50) && (yPos == log.getY() || yPos > 75)) {
                gameOn=false;
                gameEnd=true;
            }

            if((log2.enemyX == 50) && (yPos == log2.getY() || yPos > 75)) {
                gameOn=false;
                gameEnd=true;
            }

            if (log.getX() < -2000) {
                log.enemyX = 2500;
            }

            if (log2.getX() < -2000) {

                log2.enemyX = (int)(Math.random() * 1000);
            }

            log.scrollMove();
            log2.scrollMove();
            graphics2D.drawImage(log.getLogEnemy(), log.enemyX, log.getY(), null);
            graphics2D.drawImage(log2.getLogEnemy(), log2.enemyX, log.getY(), null);
            graphics2D.drawImage(log2.getLogEnemy(), log2.enemyX+150, log.getY(), null);





            //player
//            if(highScore >100){
//                globalVairables.gameSpeed = 10;
//            }
            runner.scrollMove();

            if(globalVairables.characterSwitch==1) {
                graphics2D.drawImage(runner.getAmyOneImage(), 50, yPos, null);
            }else if(globalVairables.characterSwitch==2){
                graphics2D.drawImage(runner.getJacksonOneImage(), 50, yPos, null);
            }

            highScoretwo++;
            if (highScoretwo % 15 == 0) {
                highScore += 1;
            }
            graphics2D.setFont(new Font("Dialog", Font.BOLD, 50));
            graphics2D.drawString(Integer.toString(highScore), 50, 50);
        }

       if(gameEnd == true){
            super.paint(graphics);
            Graphics2D graphics2D = (Graphics2D) graphics;
            graphics2D.drawImage(clearBackground, 0, 0, null);
            graphics2D.drawImage(tryAgainButton,600,250,null);
            graphics2D.setFont(new Font("Dialog", Font.BOLD, 50));
            graphics2D.drawString("You lose!" , 650, 100);
            graphics2D.drawString("High score: " + Integer.toString(highScore), 600, 200);




       }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        runner.move();
        log.move();
        log2.move();
        repaint();
    }

    @Override
    public void run() {
        long beforeTime;
        long timeDiff;
        long sleep;
        beforeTime = System.currentTimeMillis();

        while(jumpFinish == false){
            yCycle();
            timeDiff = System.currentTimeMillis()-beforeTime;
            sleep = 8-timeDiff;
            if(sleep <0){
                sleep =2;
            }
            try{
                Thread.sleep(sleep);
            }catch(Exception e){
                e.printStackTrace();
            }
            beforeTime = System.currentTimeMillis();
        }
        jumpFinish=false;
        jumpPeak=false;
        jump=false;
    }

    public void yCycle(){
        if (jumpPeak==false){
            yPos-=3;
        }
        if(yPos <= 50){
            jumpPeak = true;
        }

        if(jumpPeak==true && yPos <= 200){
            yPos+=2;
            if(yPos==200){
                jumpFinish=true;
            }
        }

    }


    private class AL extends KeyAdapter{
        @Override
       public void keyReleased(KeyEvent e){
           runner.keyReleased(e);
       }@Override
       public void keyPressed(KeyEvent e){
           runner.keyPressed(e);

       }
    }
    private class ML implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            int  x1 = e.getX();
            int  y1 = e.getY();
            if((x1>= 648 && x1<=961)&&(y1>=148 && y1<=258) && playScreen==true){
                repaint();
                playScreen = false;
                charSelection = true;
            }

            if(((x1>=159 && x1<=499)&&(y1>=55 && y1<=462))&&charSelection==true){
                repaint();
                charSelection=false;
                gameOn=true;
                globalVairables.characterSwitch=1;
            }
            if(((x1>=999&&x1<=1339)&&(y1>=55&&y1<=462))&&charSelection==true){
                repaint();
                charSelection=false;
                gameOn=true;
                globalVairables.characterSwitch=2;
            }

            if((x1>= 619 && x1<=931)&&(y1>=254 && y1<=368) &&gameEnd==true){
                repaint();
                highScore=0;
                highScoretwo=0;
                gameEnd= false;
                gameOn=true;
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}
