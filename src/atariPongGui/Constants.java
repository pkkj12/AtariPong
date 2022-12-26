package atariPongGui;

import java.awt.*;

public interface Constants {

    // 창 고정값
    public final int FRAME_WIDTH = 1000;
    public final int FRAME_HEIGHT = 1000;

    // 메뉴 버튼
    public static final String START = "시작하기";
    public static final String EXIT = "종료하기";
    public static final String SETTING = "설정";
    public static final String HELP = "도움말";
    public static final String REGISTER = "회원가입";
    public static final String LOGIN = "로그인";

    // 메뉴 버튼 색깔
    Color COLOR_BACKGROUND = new Color(0, 0, 0);
    Color COLOR_MENU_BACKGROUND = new Color(102, 102, 102);
    Color COLOR_MENU_FOREGROUND = new Color(0x19, 0x2B, 0x1F);
    Color COLOR_TITLE_FOREGROUND = new Color(153,153,153);

    // 하단 시간 표시
   String TIMESTAMP_FORMAT = "YYYY-MM-dd a HH:mm:ss";
}
