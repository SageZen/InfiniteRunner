package infiniteRunnerGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Runner {
    int characterX;
    int characterY;
    int characterDX;
    int characterDY;
    int backgroundXOne;
    boolean jumping = false;
    long jumpTime;

    int backgroundXTwo;
    Image jacksonOne;
    Image amyOne;

    public Runner(){
        ImageIcon jacksonIcon1 = new ImageIcon("D:\\SteppingStones2020\\InfiniteRunnerTest\\src\\infiniteRunnerGame\\images\\smallJacksonXuRun.png");
        ImageIcon amyIcon1 = new ImageIcon("D:\\SteppingStones2020\\InfiniteRunnerTest\\src\\infiniteRunnerGame\\images\\smallAmyHuangRun.png");

        jacksonOne = jacksonIcon1.getImage();
        amyOne = amyIcon1.getImage();

        characterDX = 0;
        characterY = 200;
        characterDY = 0;
        backgroundXOne = 0;
        backgroundXTwo = 1485;


    }

    public void move(){
        characterX += characterDX;
        backgroundXOne += characterDX;
        backgroundXTwo += characterDX;
        characterY += characterDY;

    }
    public void scrollMove(){
        characterDX = globalVairables.gameSpeed;
        }
    public int getX(){
        return characterX;
    }
    public int getY(){
        return characterY;
    }
    public Image getJacksonOneImage(){
        return jacksonOne;
    }
    public Image getAmyOneImage(){
        return amyOne;
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();

        if(key== KeyEvent.VK_SPACE){
            characterDY = -1;
        }
        if(key==KeyEvent.VK_S){
            //
        }
    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        if(key== KeyEvent.VK_SPACE){
            characterDY = 0;
        }
    }
}
