package Pong;

import javax.swing.*;
import java.awt.*;

public class Pong extends JFrame {


    public Pong(){
        super("아타리 퐁 게임즈");
        initUI();
    }
    private void initUI() {

        add(new Board());

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(Commons.WIDTH, Commons.HEIGHT));
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Pong();
    }

}
