package infiniteRunnerGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



public class Background extends JPanel implements ActionListener, Runnable{

    Runner runner;
    Enemies log;
    Enemies log2;
    Enemies log3;
    Enemies log4;

    Image background1;
    Image background2;
    Image clearBackground;
    Image playScreenImg;
    Image charBackground;
    Image playButton;
    Image tryAgainButton;
    Timer time;

    Thread animator;


    boolean playScreen = true;
    boolean charSelection = false;
    boolean gameOn = false;
    boolean gameEnd = false;

    int yPos = 200;
    int highScore = 0;
    int highScoretwo = 0;
    int playerSwitch = 0;

    int spawn1 = 0;
    int spawn2 = 750;
    int spawn3 =1400;
    int spawn4 = 2400;
    int respawn1 = 1500;
    int respawn2 = 1500;
    int respawn3 =1500;
    int respawn4 = 500;
    int delete1 = 2000;
    int delete2 = 2000;
    int delete3 =2000;
    int delete4 = 3000;

    int reload = 0;



    public Background(){
        runner = new Runner();
        log = new Enemies();
        log2 = new Enemies();
        log3 = new Enemies();
        log4 = new Enemies();

        addKeyListener(new AL());
        addMouseListener(new ML());

        setFocusable(true);
        setLayout(new GridLayout(3, 5));

        ImageIcon playScreenIcon = new ImageIcon(globalVairables.workingDir+"\\playScreen.png");
        ImageIcon background1Icon1 = new ImageIcon(globalVairables.workingDir+"\\background2.png");
        ImageIcon background1Icon2 = new ImageIcon(globalVairables.workingDir+"\\background1.png");
        ImageIcon clearBackgroundIcon = new ImageIcon(globalVairables.workingDir+"\\clearbackground.png");
        ImageIcon characterSelectionBackgroundIcon = new ImageIcon(globalVairables.workingDir+"\\characrerSelectionBackGround.png");
        ImageIcon playButtonIcon = new ImageIcon(globalVairables.workingDir+"\\playButton.png");
        ImageIcon tryAgainIcon = new ImageIcon(globalVairables.workingDir+"\\tryAgainButton.png");

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


        if(playScreen){
            super.paint(graphics);
            Graphics2D graphics2D = (Graphics2D) graphics;
            graphics2D.drawImage(playScreenImg, 0, 0, null);
            graphics2D.drawImage(playButton,630,140,null);

        }
        if(charSelection){
            super.paint(graphics);
            Graphics2D graphics2D = (Graphics2D) graphics;
            graphics2D.drawImage(charBackground, 0, 0, null);

        }
        if(gameOn) {


            if(globalVairables.randomizer ==1){
                spawn1 = 0;
                spawn2 = 750;
                spawn3 =1400;
//                spawn4 = 2400;
                respawn1 = 1500;
                respawn2 = 1500;
                respawn3 =1500;
//                respawn4 = 500;
                delete1 = 2000;
                delete2 = 2000;
                delete3 =2000;
//                delete4 = 3000;

            }else if(globalVairables.randomizer ==2){
                spawn1 = 750;
                spawn2 = 1400;
                spawn3 = 0;
//                spawn4 = 2400;
                respawn1 = 1500;
                respawn2 = 1500;
                respawn3 =1500;
//                respawn4 = 500;
                delete1 = 2000;
                delete2 = 2000;
                delete3 =2000;
//                delete4 = 3000;

            }else if(globalVairables.randomizer ==3){
                spawn1 = 1400; //2400
                spawn2 = 0;
                spawn3 =750;
//                spawn4 = 1400;
                respawn1 = 1500; //500
                respawn2 = 1500;
                respawn3 =1500;
//                respawn4 = 1500;
                delete1 = 2000; // 3000
                delete2 = 2000;
                delete3 =2000;
//              delete4 = 2000;

            }else if(globalVairables.randomizer ==4){
                spawn1 = 1400;
                spawn2 = 750; // 2400
                spawn3 =0;
//                spawn4 = 0; 2400
                respawn1 = 1500;
                respawn2 = 1500; //500
                respawn3 =1500;
//                respawn4 = 1500; 2400
                delete1 = 2000;
                delete2 = 2000; // 3000
                delete3 =2000;
//                delete4 = 2000;
            }

            super.paint(graphics);
            Graphics2D graphics2D = (Graphics2D) graphics;
            if (runner.characterDY == -1 && !globalVairables.jump) {
                globalVairables.jump = true;
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
            if ((log.enemyX+spawn1 == 50) && (yPos == log.getY() || yPos > 75)) {
                gameOn=false;
                gameEnd=true;

                log.enemyX = 1500+ spawn1;
                log2.enemyX += 1500+spawn2;
                log3.enemyX += 1500+spawn3;
                log4.enemyX += 1500+spawn4;
            }

            if((log2.enemyX+spawn2 == 50) && (yPos == log2.getY() || yPos > 75)) {
                gameOn=false;
                gameEnd=true;
                log.enemyX = 1500+ spawn1;
                log2.enemyX += 1500+spawn2;
                log3.enemyX += 1500+spawn3;
                log4.enemyX += 1500+spawn4;
            }

            if((log3.enemyX+spawn3 == 50)&(!globalVairables.crouch)){
                gameOn=false;
                gameEnd=true;
                log.enemyX = 1500+ spawn1;
                log2.enemyX += 1500+spawn2;
                log3.enemyX += 1500+spawn3;
                log4.enemyX += 1500+spawn4;
            }
//            if((log4.enemyX+spawn4 == 50)&(!globalVairables.crouch)){
//                gameOn=false;
//                gameEnd=true;
//                log.enemyX = 1500+ spawn1;
//                log2.enemyX += 1500+spawn2;
//                log3.enemyX += 1500+spawn3;
//                log4.enemyX += 1500+spawn4;
//            }

            if (log.getX() < -delete1) {
                log.enemyX = respawn1;
            }

            if (log2.getX() < -delete2) {
                log2.enemyX = respawn2;

            }

            if(log3.getX() < -delete3){
                log3.enemyX = respawn3;

            }

//            if( log4.getX() < -delete4){
//                log4.enemyX = respawn4;
//
//            }

            log.scrollMove();
            log2.scrollMove();
            log3.scrollMove();
 //           log4.scrollMove();

            graphics2D.drawImage(log.getLogEnemy(), log.enemyX + spawn1, log.getY(), null);
            graphics2D.drawImage(log2.getDoubleLogEnemy(), log2.enemyX + spawn2, log.getY(), null);
            graphics2D.drawImage(log3.getSideLogEnemy(),log3.enemyX + spawn3,150,null);
//            graphics2D.drawImage(log4.getSideLogEnemy(),log4.enemyX+ spawn4,150,null);






            //player
//            if(highScore >100){
//                globalVairables.gameSpeed = 10;
//            }
            runner.scrollMove();

            if(globalVairables.characterSwitch==1 && !globalVairables.crouch) {
                graphics2D.drawImage(runner.getAmyOneImage(), 50, yPos, null);
            }else if(globalVairables.characterSwitch==2 && !globalVairables.crouch){
                graphics2D.drawImage(runner.getJacksonOneImage(), 50, yPos, null);
            }

            if(globalVairables.characterSwitch==1 && globalVairables.crouch) {
                graphics2D.drawImage(runner.getAmyOneImage(), 50, yPos+100, 100,125,null);
            }else if(globalVairables.characterSwitch==2 && globalVairables.crouch){
                graphics2D.drawImage(runner.getJacksonOneImage(), 50, yPos+100, 100,125,null);
            }

            highScoretwo++;
            if (highScoretwo % 15 == 0) {
                highScore += 1;
            }
            if(highScore%45==0){
                System.out.println(globalVairables.randomizer);
                globalVairables.randomizer = (int) (Math.random()*4);
                if(globalVairables.randomizer ==0){
                    globalVairables.randomizer=1;
                }
            }

            graphics2D.setFont(new Font("Dialog", Font.BOLD, 50));
            graphics2D.drawString(Integer.toString(highScore), 50, 50);
        }

       if(gameEnd){
            super.paint(graphics);
            Graphics2D graphics2D = (Graphics2D) graphics;
            graphics2D.drawImage(clearBackground, 0, 0, null);
            graphics2D.drawImage(tryAgainButton,600,250,null);
            graphics2D.setFont(new Font("Dialog", Font.BOLD, 50));
            graphics2D.drawString("You lose!" , 650, 100);
            graphics2D.drawString("High score: " + highScore, 600, 200);




       }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        runner.move();
        log.move();
        log2.move();
        log3.move();
        log4.move();
        repaint();
    }

    @Override
    public void run() {
        long beforeTime;
        long timeDiff;
        long sleep;
        beforeTime = System.currentTimeMillis();

        while(!globalVairables.jumpFinish){
            yCycle();
            timeDiff = System.currentTimeMillis()-beforeTime;
            sleep = 10-timeDiff;
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
        globalVairables.jumpFinish=false;
        globalVairables.jumpPeak=false;
        globalVairables.jump=false;
    }

    public void yCycle(){
        if (!globalVairables.jumpPeak){
            yPos-=5;
        }
        if(yPos <= 25){
            globalVairables.jumpPeak = true;
        }

        if(globalVairables.jumpPeak && yPos <= 200){
            yPos+=5;
            if(yPos==200){
                globalVairables.jumpFinish=true;
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
            if((x1>= 648 && x1<=961)&&(y1>=148 && y1<=258) && playScreen){
                repaint();
                playScreen = false;
                charSelection = true;
            }

            if(((x1>=159 && x1<=499)&&(y1>=55 && y1<=462))&& charSelection){
                repaint();
                charSelection=false;
                gameOn=true;
                globalVairables.characterSwitch=1;
            }
            if(((x1>=999&&x1<=1339)&&(y1>=55&&y1<=462))&& charSelection){
                repaint();
                charSelection=false;
                gameOn=true;
                globalVairables.characterSwitch=2;
            }

            if((x1>= 619 && x1<=931)&&(y1>=254 && y1<=368) && gameEnd){
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
