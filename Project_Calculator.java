import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Project_Calculator extends JFrame {
    
    private JLabel lb_cal; // 입력하고 있는 값과 결과 출력
    private JLabel lb_result; // 입력받은 값과 수식을 저장
    private double result = 0;
    private double num = 0;
    private String math = "";
    private String str = "";

    public Project_Calculator() {
        // 창의 제목 : Calculator
        setTitle("Calculator");

        // java.awt의 window 클래스에 위치하는 메소드 null 값을 입력시 화면 가운데에 창을 띄운다
        setLocationRelativeTo(null); 
        // 이 코드를 사용하지않으면 JFrame으로 띄운 창을 지워도 프로세스 상애는 계속 가동되므로 메모리를 잡아 먹는다
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 컨테이너 선언
        Container con = getContentPane();
        // 컨테이너에 간격 좌우 5, 상하 5 인 BorderLayout 만듬
        con.setLayout(new BorderLayout(5, 5));
        // 배경색 설정
        con.setBackground(Color.GRAY);

        // 입력한 수식과 결과값이 출력되는 패널 생성 후 컨테이너에 추가
        Cal_Panel cp = new Cal_Panel();
        con.add(cp, BorderLayout.EAST);

        // 버튼을 넣을 패널 생성 후 컨테이너에 추가
        Button_Panel bp = new Button_Panel();
        con.add(bp, BorderLayout.SOUTH);

        // 창의 크기 설정
        setSize(350, 600);
        // 창을 화면에 나타낼것인지 설정
        setVisible(true);

    }
    
    class Cal_Panel extends JPanel {
        public Cal_Panel() {
            // 3행 1열 그리드레이아웃 만들기
            setLayout(new GridLayout(3, 1));
            // 배경색 지정
            setBackground(Color.GRAY);

            lb_result = new JLabel("");
            lb_cal = new JLabel("0");

            // 폰트 설정
            lb_result.setFont(new Font("궁서체", 0, 40));
            // 폰트 색상
            lb_result.setForeground(Color.BLACK);
            // 오른쪽 정렬
            lb_result.setHorizontalAlignment(SwingConstants.RIGHT);

            // 폰트 설정
            lb_cal.setFont(new Font("궁서체", Font.BOLD, 50));
            // 폰트 색상
            lb_cal.setForeground(Color.BLACK);
            // 오른쪽 정렬
            lb_cal.setHorizontalAlignment(SwingConstants.RIGHT);

            add(lb_result);
            add(lb_cal);

        }
    }
    
    class Button_Panel extends JPanel {
        public Button_Panel() {
            // 20칸 짜리 버튼배열 선언
            JButton bt[] = new JButton[20];
            // 4행 5열 좌우간격 5, 상하간격 5 짜리 GridLayout생성
            setLayout(new GridLayout(5, 4, 5, 5));
            // 배경색 설정
            setBackground(Color.DARK_GRAY);

            // 순서에 맞게 버튼 선언
            String button_name[] = {"×", "÷", "AC", "C", "7", "8", "9",
            "√", "4", "5", "6", "-", "1", "2", "3", "+", "＾2", "0", ".", "="};

            
            for(int i = 0 ; i < bt.length ; i++) {
                // 버튼값 집어넣기
                bt[i] = new JButton(button_name[i]);
                // 크기 설정
                bt[i].setPreferredSize(new Dimension(0, 65));
                // 버튼 폰트 설정
                bt[i].setFont(new Font("궁서체", 0, 20));
                // 버튼 폰트 색상 설정
                bt[i].setForeground(Color.BLACK);
                // 버튼 배경색 설정
                bt[i].setBackground(Color.RED);

                if(3 < i && i < 15 && i % 4 != 3) {
                    // 버튼에 액션 리스너 추가
                    // 액션리스너 상속시켜준 후 actionPerformed(ActionEvent e)메소드로 이벤트 처리
                    bt[i].addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // 이벤트를 발생시킨 버튼값 저장 
                            JButton jb = (JButton)e.getSource();

                            if(lb_cal.getText() == "0") {
                                lb_cal.setText("");

                            }

                            String lb_caltext = lb_cal.getText();
                            String jb_text = jb.getText();
                            String text = lb_caltext + jb_text;

                            // 입력 값 10자리까지 입력받을 수 있게 설정
                            if(text.length() <= 10) {
                                lb_cal.setText(text);

                            }

                            // lb_result값에 "=" 이 들어있으면 
                            if(lb_result.getText().contains("=") == true) {
                                // lb_result값 초기화
                                lb_result.setText("");
                                lb_cal.setText(jb.getText());
                                result = 0;
                                num = 0;

                            }

                        }
                    });

                // 0 값을 입력 받았을 때
                }else if(i == 17) {
                    bt[i].addActionListener(new ActionListener() {
                    
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // 이벤트를 발생시킨 객체의 위치값 저장
                            JButton jb = (JButton)e.getSource();

                            String lb_caltext = lb_cal.getText();
                            String jb_text = jb.getText();
                            String text = lb_caltext + jb_text;

                            // 입력받은 값이 0이면
                            if(lb_caltext == "0") {
                                // 0으로 유지
                                lb_cal.setText("0");

                            // 다른 값이면
                            }else {
                                // 입력 값 추가
                                lb_cal.setText(text);

                            }
                        }

                    });

                // 입력값이 "="이면
                }else if(i == 19) {
                    // 색 지정
                    bt[i].setBackground(new Color(255, 0, 0));
                    // Cal_Result(계산)메소드 실행
                    bt[i].addActionListener(new Cal_Result());
                
                // 0, 1, 2, 3, 7, 11, 15, 16, 17, 18, 19일 때
                }else if(i % 4 == 3 || i < 3 || 15 < i) {
                    // 색 지정
                    bt[i].setBackground(new Color(255, 0, 255));

                    // 입력값이 "AC"이면
                    if(i== 2) {
                        bt[i].addActionListener(new ActionListener() {

                            @Override
                            public void actionPerformed(ActionEvent e) {
                                // lb_cal의 길이를 받아옴
                                int a = lb_cal.getText().length();

                                // lb_cal의 길이가 0보다 크면
                                if(a >= 0) {
                                    // 초기화
                                    lb_cal.setText("0");
                                    lb_result.setText("");

                                }
                                // 초기화
                                result = 0;

                            }
                        });

                    // 입력 값이 "C"이면 
                    }else if(i == 3) {
                        bt[i].addActionListener(new ActionListener() {

                            @Override
                            public void actionPerformed(ActionEvent e) {
                                // lb_cal의 길이를 받아옴
                                int a = lb_cal.getText().length();

                                // lb_cal의 길이가 0보다 크면
                                if(a > 0) {
                                    // 뒤에서 입력 값 1개 지움
                                    lb_cal_setText(lb_cal_getText().substring(0, lb_cal_getText().length() - 1));
                                
                                }

                                // 입력한 값을 다 지워버리면
                                if(lb_cal.getText() == "") {
                                    // 0 값 으로 지정
                                    lb_cal.setText("0");

                                }
                            }

                        });
                    
                    // 입력 값이 "."이면
                    }else if(i == 18) {
                        bt[i].addActionListener(new ActionListener() {

                            @Override
                            public void actionPerformed(ActionEvent e) {
                                // 입력받은 버튼 값 저장
                                JButton jb = (JButton)e.getSource();
                                
                                String lb_caltext = lb_cal.getText();
                                String jb_text = jb.getText();
                                String text = lb_caltext + jb_text;

                                int a = text.length();

                                // 입력한 값이 "."이 포함되어있지 않고, 길이가 10보다 작을 때
                                if(lb_cal.getText().contains(jb.getText()) == false && a < 10) {
                                    // 입력 값에 "." 추가
                                    lb_cal.setText(text);

                                }
                            }

                        });

                    // 버튼이 수 일때
                    }else {
                        // 계산
                        bt[i].addActionListener(new Cal_Result());

                    }
                }
                add(bt[i]);
                
            }
        }
    }

    // 계산 메소드
    public class Cal_Result implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // 입력받은 버튼 값 저장
            JButton jb = (JButton)e.getSource();

            String lb_caltext = lb_cal.getText();
            String jb_text = jb.getText();
            String text = lb_caltext + jb_text;

            int a = text.length();

            if(jb_text != "＾2") {
                num = Double.parseDouble(lb_cal.getText().substring(0, a - 1));

            }

            // math값이 +일 때 
            if(math == "+") {
                result += num;
                math = "";

            // math값이 -일 때
            }else if(math == "-") {
                result -= num;
                math = "";

            // math값이 x일 때
            }else if(math == "×") {
                result *= num;
                math = "";

            // math값이 ÷일 때                
            }else if(math == "÷") {
                result /= num;
                math = "";

            }

            // math값이 없을 때
            if(math == "") {
                math = jb.getText();

            }

            if(lb_result.getText() == "" && jb_text != "＾2" && jb_text != "=") {
                lb_result.setText(text);
                result = num;
                lb_cal.setText("0");

            }else if(lb_result.getText() != "" && jb_text != "＾2" && jb_text != "=") {
                result = (Math.round(result * 1000000000) / 1000000000.0);

                if(result % 1 == 0) {
                    lb_result.setText((int)result + jb_text);
                    lb_cal.setText("0");

                }else {
                    lb_result.setText(result + jb_text);
                    lb_cal.setText("0");

                }
            }

            if(jb_text == "＾2") {
                num = Double.parseDouble(lb_cal.getText().substring(0, a - 2));
                math = "";

                if(lb_result.getText() == "") {
                    result = (Math.round((Math.pow(num, 2)) * 1000000000) / 1000000000.0);

                    if(result % 1 == 0) {
                        lb_result.setText("pow(" + (int)num + ")");
                        lb_cal.setText(String.valueOf((int)result));

                    }else {
                        lb_result.setText("pow(" + num + ")");
                        lb_cal.setText(String.valueOf(result));

                    }

                }else {
                    if(result % 1 == 0) {
                        lb_result.setText("pow(" + (int)result + ")");
                        result = (Math.round((Math.pow(result, 2)) * 1000000000) / 1000000000.0);
                        lb_cal.setText(String.valueOf((int)result));

                    }else {
                        lb_result.setText("pow(" + result + ")");
                        result = (Math.round((Math.pow(result, 2)) * 1000000000) / 1000000000.0);
                        lb_cal.setText(String.valueOf(result));

                    }
                }
            }

            if(jb_text == "√") {
                math = "";

                if(lb_result.getText() == "") {
                    result = (Math.round((Math.sqrt(num)) * 1000000000) / 1000000000.0);

                    if(result % 1 == 0) {
                        lb_result.setText("sqrt(" + (int)num + ")");
                        lb_cal.setText(String.valueOf((int)result));

                    }else {
                        lb_result.setText("sqrt(" + num + ")");
                        lb_cal.setText(String.valueOf(result));

                    }

                }else {
                    if(Math.sqrt(result) % 1 == 0) {
                        lb_result.setText("sqrt(" + (int)result + ")");
                        result = (Math.round((Math.sqrt(result)) * 1000000000) / 1000000000.0);
                        lb_cal.setText(String.valueOf((int)result));

                    }else {

                        lb_result.setText("sqrt(" + result + ")");
                        result = (Math.round((Math.sqrt(result)) * 1000000000) / 1000000000.0);
                        lb_cal.setText(String.valueOf(result));
                    }
                }

            }

            if(jb_text == "=") {
                math = "";

                if(lb_result.getText() != "" && lb_cal.getText() != "" && lb_result.getText().contains(jb_text) == false) {
                    if(result % 1 == 0) {
                        lb_result.setText(lb_result.getText() + lb_cal.getText() + jb_text);
                        lb_cal.setText(String.valueOf((int)result));

                    }else {
                        lb_result.setText(lb_result.getText() + lb_cal.getText() + jb_text);
                        lb_cal.setText(String.valueOf(result));

                    }
                }
            }

        }
        
    }

    private void lb_cal_setText(String str) {
        lb_cal.setText(str);
    }
    
    private String lb_cal_getText() {
        return lb_cal.getText();
    }


    public static void main(String ar[]){
        new Project_Calculator();
    }
}
