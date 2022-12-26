package member;

import java.sql.Date;

public class MemberDto {


    int id;
    String nickName;
    int leftScore;
    int rightScore;
    Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getLeftScore() {
        return leftScore;
    }

    public void setLeftScore(int leftScore) {
        this.leftScore = leftScore;
    }

    public int getRightScore() {
        return rightScore;
    }

    public void setRightScore(int rightScore) {
        this.rightScore = rightScore;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "MemberDto{" +
                "id=" + id +
                ", nickName='" + nickName + '\'' +
                ", leftScore=" + leftScore +
                ", rightScore=" + rightScore +
                ", date=" + date +
                '}';
    }
}
