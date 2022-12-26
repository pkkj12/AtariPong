package Pong;

import javax.swing.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static java.awt.event.KeyEvent.VK_S;
import static java.awt.event.KeyEvent.VK_W;

public class LeftPaddle extends Sprite implements Commons{


    protected int dy; // y 변화율


    public LeftPaddle(){ // 왼쪽편 패들 생성


        initPaddle();   // 패들 생성 메서드

    }
    private void initPaddle() {

        resetState();   // 시작 위치 초기화 ( 패들 생성 메서드 )
    }

    public void move() {    // 움직이는 동작 원리

        y += dy;

        if (y <= 0) {
            y = 0;
        }

        if (y >= HEIGHT - (height + 25)) {
            y = HEIGHT - (height + 25);
        }
    }


    public void keyPressed(KeyEvent e) { // 키를 누르면 움직일 수치량
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_W){
            dy = -3;
        }

        if(key == KeyEvent.VK_S){
            dy = 3;
        }
    }



    public void keyReleased(KeyEvent e) { // 누른 값 확인!
//        System.out.println("누름!");
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_W){
            dy = 0;
        }

        if(key == KeyEvent.VK_S){
            dy = 0;
        }
    }

    private void resetState(){
        x = INIT_PADDLE_X;
        y = INIT_PADDLE_Y;
        setWidth(15);
        setHeight(100);

    }










}
