package infiniteRunnerGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Enemies {

    Image logEnemy;
    int enemyX;
    int enemyY;
    int enemyDX;
    int enemyDY;

    public Enemies(){
        ImageIcon logIcon = new ImageIcon("D:\\SteppingStones2020\\InfiniteRunnerTest\\src\\infiniteRunnerGame\\images\\treestump.png");
        logEnemy= logIcon.getImage();
        enemyDX =0;
        enemyDY = 0;
        enemyY = 200;
        enemyX = 1500;
    }

    public Image getLogEnemy(){
        return logEnemy;
    }
    public void move(){
        enemyX -= enemyDX;
    }
    public void scrollMove(){
        enemyDX = 5;
    }
    public int getX(){
        return enemyX;
    }
    public int getY() {
        return enemyY;
    }
    public void setX(int x){
        enemyX = x;
    }
}
