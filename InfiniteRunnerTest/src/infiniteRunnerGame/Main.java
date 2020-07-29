package infiniteRunnerGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    public Main() {
        JFrame frame = new JFrame();
        JPanel playScreen = new JPanel();

        frame.add(new Background());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500, 500);
        frame.setResizable(false);
        frame.setTitle("test");
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }
}

