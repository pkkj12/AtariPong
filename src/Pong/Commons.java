package Pong;

import java.awt.*;

public interface Commons {

    // 게임 고정값
    public static final int WIDTH = 1016;   // 창 너비
    public static final int HEIGHT = 789;  // 창 높이
    public static final int LEFT_EDGE = 10; // 왼쪽 가장자리 모서리 부분
    public static final int RIGHT_EDGE = 1006; // 오른쪽 가장자리 모서리 부분

    // 왼쪽 패들 시작 위치
    public static final int INIT_PADDLE_X = 50;
    public static final int INIT_PADDLE_Y = 100;

    // 오른쪽 패들 시작 위치
    public static final int INIT_PADDLE2_X = 928;
    public static final int INIT_PADDLE2_Y = 500;

    // 공 시작 위치(중앙)
    public static final int INIT_BALL_X = 503;
    public static final int INIT_BALL_Y = 389;

    // 각 쓰레드의 시간 간격 및 대기 시간
    public static final int BEFORE_START_DELAY = 3000; // 게임을 시작하기 전의 대기 시간
    public static final int INTERVAL = 1000;  // 게임을 시작하기 전에 3,2,1,시작의 문구를 띄울 시간 간격
    public static final int PERIOD = 10; // 쓰레드 실행 간격
    public static final int NO_DELAY_IN_BEFORE_TIMER = 0; // 쓰레드 실행 간격

}
