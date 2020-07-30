package infiniteRunnerGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Enemies {

    Image logEnemy;
    Image doubleLogEnemy;
    Image sideLogEnemy;
    int enemyX;
    int enemyY;
    int enemyDX;
    int enemyDY;

    public Enemies(){
        String workingDir = System.getProperty("user.dir") + "\\InfiniteRunnerTest\\src\\infiniteRunnerGame\\images";
        ImageIcon logIcon = new ImageIcon(globalVairables.workingDir + "\\treestump.png");
        ImageIcon doubleLogIcon = new ImageIcon(globalVairables.workingDir+"\\dualtreestump.png");
        ImageIcon sidelogIcon = new ImageIcon(globalVairables.workingDir + "\\sidetreestump.png");

        logEnemy= logIcon.getImage();
        doubleLogEnemy= doubleLogIcon.getImage();
        sideLogEnemy = sidelogIcon.getImage();
        enemyDX =0;
        enemyDY = 0;
        enemyY = 200;
        enemyX = 1500;
    }

    public Image getLogEnemy(){
        return logEnemy;
    }
    public Image getDoubleLogEnemy(){return doubleLogEnemy;}
    public Image getSideLogEnemy() { return sideLogEnemy;}
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
