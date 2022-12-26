package Pong;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

public class Board extends JPanel implements Commons {


    // 보드에 필요한 부품들 선언
    private Timer beforeTimer;      // 게임 시작 전의 타이머
    private Timer startTimer;       // 게임 시작 후의 타이머
    private Ball ball;      // 공
    private String message;     // 메세지
    private LeftPaddle leftPaddle;    // 왼쪽 패들
    private RightPaddle rightPaddle;    // 오른쪽 패들


    private int leftScore = 0;          // 왼쪽 스코어
    private int rightScore = 0;         // 오른쪽 스코어
    public int win = 5;             // 총 이길 횟수
//    public double mod = 0;

    private int countDown = 3;         // 시작하기 전의
    private boolean inGame = true;      // 인게임인지 아닌지 판별한다.


    public Board() { // 보드판 생성


        initBoard();


    }


    private void initBoard() {   // 보드 생성 ( 게임을 흘러가게 만드는 구역 )

        addKeyListener(new TAdapter());
        setFocusable(true);
        beforeTimer = new Timer();
        startTimer = new Timer();
        beforeTimer.schedule(new BeforeStart(), NO_DELAY_IN_BEFORE_TIMER,INTERVAL); // 시작하기 전의 타이머가 실행되는 중 ( 대기하지 않고 바로 실행됨 )
        startTimer.scheduleAtFixedRate(new GameStart(), BEFORE_START_DELAY, PERIOD); // 시작 전의 타이머가 진행될 때까지 대기하면서 시작이라는 문구가 나오면 바로 시작하지 않고 1초 더 대기하다가 실행


    }

    private class BeforeStart extends TimerTask {


        @Override
        public void run() {

                repaint();  // 페인트칠
                validate(); // 유효성 검사



        }

    }


    private class GameStart extends TimerTask {      // 실행할 작업들
        @Override
        public void run() {

            ball.move();
            leftPaddle.move();
            rightPaddle.move();
            checkCollision();
            repaint();
            validate();


        }
    }


    @Override
    public void addNotify() {
        super.addNotify();
        gameInit();

    }


    // 게임 생성 ( 공, 패들, 점수판)
    private void gameInit() {

        ball = new Ball();
        leftPaddle = new LeftPaddle();
        rightPaddle = new RightPaddle();

    }


    // 색칠 ( 가운데 점선, 점수, 패들 2개, 공 ) - 본체
    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.BLACK);
        // 가운데 점선
        for (int i = 0; i < 750; i += 40) {

            g.setColor(Color.GRAY);
            g.fillRect(Commons.WIDTH / 2, i, 10, 15);

        }

        Graphics2D g2d = (Graphics2D) g;
        Font beforeStartFont = new Font("글씨체", Font.BOLD, 50); // 문구를 구현할 폰트 생성
        Font leftScoreFont = new Font("글씨체", Font.BOLD, 70); // 문구를 구현할 폰트 생성
        Font rightScoreFont = new Font("글씨체", Font.BOLD, 70); // 문구를 구현할 폰트 생성
        int beforeTextSize = beforeStartFont.getSize();
        int leftScoreTextSize = leftScoreFont.getSize();
        int rightScoreTextSize = rightScoreFont.getSize();

        // TODO - 초읽기 코드 의미 있게 짜보기
        g2d.setColor(Color.WHITE);
        g2d.setFont(beforeStartFont);
        if (countDown == 0) {
            g2d.drawString("시작", Commons.WIDTH / 2 - beforeTextSize, 400);
        } else {
            g2d.drawString(Integer.toString(countDown), Commons.WIDTH / 2, 400);
        }

        // 왼쪽 점수
        g2d.setColor(Color.BLUE);
        g2d.setFont(leftScoreFont);
        g2d.drawString(Integer.toString(leftScore), Commons.WIDTH / 4, 100);

        // 오른쪽 점수
        g2d.setColor(Color.RED);
        g2d.setFont(rightScoreFont);
        g2d.drawString(Integer.toString(rightScore), Commons.WIDTH - 300, 100);


        if (inGame) {
            drawObjects(g2d);
        } else {
            gameFinished(g2d);
        }

        // TODO - 이게 뭔지 설명해야함
//        Toolkit.getDefaultToolkit().sync();

    }

    private void drawObjects(Graphics2D g2d) {   // 게임을 시작했을 때 구현할 물체들
        g2d.setColor(Color.WHITE);
        g2d.fillRect(ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight());
        g2d.fillRect(leftPaddle.getX(), leftPaddle.getY(), leftPaddle.getWidth(), leftPaddle.getHeight());
        g2d.fillRect(rightPaddle.getX(), rightPaddle.getY(), rightPaddle.getWidth(), rightPaddle.getHeight());

    }

    private void gameFinished(Graphics2D g2d) { // 게임이 끝났을 때 구현할 물체들

        Font font = new Font("글씨체", Font.BOLD, 45);
        FontMetrics metr = this.getFontMetrics(font);

        g2d.setColor(Color.WHITE);
        g2d.setFont(font);
        g2d.drawString(message,
                (Commons.WIDTH - metr.stringWidth(message)) / 2,
                Commons.WIDTH / 2);
    }


    private class TAdapter extends KeyAdapter { // TODO - 뭔지 모르겠음

        @Override
        public void keyReleased(KeyEvent e) {
            leftPaddle.keyReleased(e);
            rightPaddle.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            leftPaddle.keyPressed(e);
            rightPaddle.keyPressed(e);
        }

    }


    private void leftScore() { // 점수를 얻으면 그 다음 게임을 위해 초기화 시킴

        leftScore++;

        ball.setX(Commons.INIT_BALL_X);
        ball.setY(Commons.INIT_BALL_Y);
        ball.setXDir(2);
        ball.setYDir(-(int) (Math.random() * 4 - 2));
        startTimer.cancel();


        beforeTimer = new Timer();
        startTimer = new Timer();
        beforeTimer.schedule(new BeforeStart(), 0);
        startTimer.scheduleAtFixedRate(new GameStart(), BEFORE_START_DELAY, PERIOD);

        if (leftScore == win) {
            inGame = false;
            startTimer.cancel();
            message = "플레이어 1승!";
        }


    }

    private void rightScore() { // 점수를 얻으면 그 다음 게임을 위해 초기화 시킴


        rightScore++;

        ball.setX(Commons.INIT_BALL_X);
        ball.setY(Commons.INIT_BALL_Y);
        ball.setXDir(-2);
        ball.setYDir((int) (Math.random() * 4 - 2));
        startTimer.cancel();


        beforeTimer = new Timer();
        startTimer = new Timer();
        beforeTimer.schedule(new BeforeStart(), 0);
        startTimer.scheduleAtFixedRate(new GameStart(), BEFORE_START_DELAY, PERIOD);


        if (rightScore == win) {
            inGame = false;
            startTimer.cancel();
            message = "Player 2 Wins!";
        }


    }

    // 공과 패들끼리 충돌했을 경우 튕겨져 나가는 동작 원리 구현
    private void checkCollision() {
        int paddleLPos = (int) leftPaddle.getRect().getMinY();
        int paddleRPos = (int) rightPaddle.getRect().getMinY();
        int ballPos = (int) ball.getRect().getMinY() + 10;
        boolean lCollisionRange = (ballPos >= paddleLPos && ballPos <= paddleLPos + 100) ? true : false;
        boolean rCollisionRange = (ballPos >= paddleRPos && ballPos <= paddleRPos + 100) ? true : false;

        // 모서리 바깥 부분으로 나가면 점수 획득
        if (ball.getRect().getMaxX() > Commons.RIGHT_EDGE) {
            leftScore();
        }
        if (ball.getRect().getMaxX() < Commons.LEFT_EDGE) {
            rightScore();
        }

        // 왼쪽 패들 + 공 충돌
        if ((ball.getRect()).intersects(leftPaddle.getRect())) {
            leftCheck(lCollisionRange, leftPaddle.dy);
        }

        // 오른쪽 패들 + 공 충돌
        if ((ball.getRect()).intersects(rightPaddle.getRect())) {
            rightCheck(rCollisionRange, rightPaddle.dy);
        }
    }

    private void rightCheck(boolean rCollisionRange, int dy) {
        if (rCollisionRange) { // 패들 충돌 범위
            if (!(ball.getYDir() == 0)) {
                ball.setXDir(-2);
            } else {
                if (dy > 0) {
                    ball.setXDir(-2);
                    ball.setYDir(2);
                } else if (dy < 0) {
                    ball.setXDir(-2);
                    ball.setYDir(-2);
                } else {
                    ball.setXDir(-2);
                }


            }
        }
    }

    private void leftCheck(boolean lCollisionRange, int dy) {
        if (lCollisionRange) { // 패들 충돌 범위
            if (!(ball.getYDir() == 0)) {
                ball.setXDir(2);
            } else {
                if (dy > 0) {
                    ball.setXDir(2);
                    ball.setYDir(2);
                } else if (dy < 0) {
                    ball.setXDir(2);
                    ball.setYDir(-2);
                } else {
                    ball.setXDir(2);
                }


            }
        }
    }
}
