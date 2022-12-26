package atariPongGui;

import Pong.Pong;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;


public class StandByGui extends JFrame implements Constants, ActionListener, Runnable {

    private SimpleDateFormat dataFormat = new SimpleDateFormat(TIMESTAMP_FORMAT);
    private JLabel timerLabel;



    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("클릭!");
        JButton button = (JButton) e.getSource();   // 버튼의 데이터를 불러온다.
        //불러온 데이터에 맞는 기능을 설정
        switch (button.getText()) {
            case START:
                dispose();
                new Pong();
                break;
            case REGISTER:
                dispose();
                new RegisterGui();
                break;
            case LOGIN:
                dispose();
                new LoginGui();
                break;
            case SETTING:
                dispose();
                new Settings();
                break;
            case HELP:
                dispose();
                new HelpGui();
                break;
            case EXIT:
                System.exit(0);
                break;

        }
    }

    private JPanel upperButtonPanel(){
        final String LOGIN = "로그인";
        final String REGISTER = "회원가입";


        JPanel buttonPanel = new JPanel(); // 상단 패널

        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setSize(new Dimension(500, 100));
        buttonPanel.setBackground(COLOR_MENU_BACKGROUND);

        JButton[] buttons = new JButton[]{new JButton(LOGIN), new JButton(REGISTER)};

        for(JButton button : buttons){
            button.setFont(new Font("글씨체",Font.BOLD,30));
            button.setBackground(Color.darkGray);
            button.setForeground(Color.BLACK);
            button.setVerticalAlignment(SwingConstants.CENTER);
            button.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            buttonPanel.add(button);
//            if(button.getText().equals(REGISTER)){
//                continue;
//            }
//            Box.createHorizontalStrut(40);

        }

        return buttonPanel;
    }
    private JPanel upperPanel(){ // 생성자에 호출하기 위해 생성한 기본 메서드
        return upperPanel(upperButtonPanel());
    }

    private JPanel upperPanel(JPanel buttonPanel) {

        JPanel panel = new JPanel(); // 상단 패널

        panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panel.setPreferredSize(new Dimension(0, 60));
        panel.setBackground(COLOR_MENU_BACKGROUND);
        panel.add(buttonPanel);

        return panel;
    }

    public JPanel lowerPanel() {
        timerLabel = new JLabel();
        timerLabel.setFont(new Font("글씨체",Font.BOLD,15));
        timerLabel.setForeground(Color.BLACK);
        new Thread(this).start();


        JPanel panel = new JPanel(); // 하단 패널
        panel.setPreferredSize(new Dimension(0, 30));
        panel.setBackground(COLOR_MENU_BACKGROUND);


        panel.add(timerLabel);
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panel.setBorder(BorderFactory.createEtchedBorder());
        return panel;
    }

    private JPanel centerPanel() {

        JPanel panel = new JPanel(); // 중앙 패널
        panel.setLayout(null);  // 배치 레이아웃 자유
//        panel.setAlignmentX(SwingConstants.CENTER);
//        panel.setAlignmentY(SwingConstants.CENTER);
        panel.setBackground(COLOR_BACKGROUND); // 중앙 패널 배경색

        JLabel title = new JLabel("ATARI PONG");
        title.setFont(new Font("Serif", Font.ITALIC, 100));
        title.setForeground(COLOR_TITLE_FOREGROUND);
        title.setBounds(210, 150, 700, 100);

        JPanel buttonPanel = new JPanel();  // 버튼 패널
        buttonPanel.setLayout(new GridLayout(2, 2, 10, 10)); // 그리드 레이아웃 방식
        buttonPanel.setSize(new Dimension(300, 300)); // 버튼 크기만 조정
        buttonPanel.setBackground(Color.BLACK);

        JButton[] buttons = { // 버튼 생성
                new JButton(START), new JButton(EXIT),
                new JButton(HELP), new JButton(SETTING)
        };

        for (JButton button : buttons) { // 버튼 기능 추가하고 버튼 패널에 부착하기
            button.setFont(new Font("Serif", Font.BOLD, 25)); // serif 글꼴체로 진하게 크기25인 폰트 설정
            button.setForeground(COLOR_MENU_FOREGROUND); //
            button.setBackground(COLOR_MENU_BACKGROUND);
            button.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            button.addActionListener(this);
            buttonPanel.add(button);
        }
        buttonPanel.setBounds(320, 450, 350, 350); // 버튼 패널 위치 지정
        panel.add(buttonPanel); // 버튼 패널을 중앙 패널에 부착
        panel.add(title);
        return panel;   // 중앙 패널 반환
    }

    @Override
    public void run() {
        while (true) {
            timerLabel.setText(dataFormat.format(System.currentTimeMillis()));
            try {
                Thread.sleep(1000);
            } catch(InterruptedException e){
                break;
            }
        }
    }

    public StandByGui() {

        super("아타리 퐁 게임즈");


        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setLayout(new BorderLayout());
        this.add(upperPanel(), BorderLayout.NORTH);
        this.add(centerPanel(), BorderLayout.CENTER);
        this.add(lowerPanel(), BorderLayout.SOUTH);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {

        new StandByGui();

    }


}
