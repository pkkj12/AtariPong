package Pong;

public class Ball extends Sprite implements Commons{

    // 공의 스텟 설정
    private double xDir;// 공의 시작지점
    private double yDir;// 공의 시작지점



    public Ball(){
        initBall();
    }

    private void initBall() {

        xDir = (int)(Math.random() + 2);
        yDir = (int)(Math.random() * 4 - 2);

        width = getWidth();
        height = getHeight();
        resetState();



    }

    public void move() {


        x += xDir;
        y += yDir;

        if (y <= 0) {
            yDir = -yDir;
            y += yDir;
        }

        if (y >= HEIGHT - height-25) {
            yDir = -yDir;
        }

    }

    public double getXDir() {
        return xDir;
    }

    public void setXDir(double xDir) {
        this.xDir = xDir;
    }

    public double getYDir() {
        return yDir;
    }

    public void setYDir(double yDir) {
        this.yDir = yDir;
    }

    private void resetState() {

        x = Commons.INIT_BALL_X;
        y = Commons.INIT_BALL_Y;
        setWidth(20);
        setHeight(20);
    }



}
