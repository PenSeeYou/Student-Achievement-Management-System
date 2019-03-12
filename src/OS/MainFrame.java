package OS;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.lang.String;

public class MainFrame extends JFrame {

    public MainFrame(String[] args) {
        // 创建 JFrame 实例
        JFrame frame = new JFrame("Login Example");
        // Setting the width and height of frame
        frame.setSize(320, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);// 窗口居中
        /* 创建面板，这个类似于 HTML 的 div 标签
         * 我们可以创建多个面板并在 JFrame 中指定位置
         * 面板中我们可以添加文本字段，按钮及其他组件。
         */
        JPanel panel = new JPanel();
        // 添加面板
        frame.add(panel);
        /* 
         * 调用用户定义的方法并添加组件到面板
         */
        placeComponents(panel);

        // 设置界面可见
        frame.setVisible(true);
        setLocationRelativeTo(null);//居中
    }

    private static void placeComponents(JPanel panel) {

        //JTextField dataText = new JTextField("");
        //dataText.setBounds(30,240,250,250);
        //panel.add(dataText);
        JTextArea area = new JTextArea(3, 20);
        area.setBounds(30, 240, 250, 250);
        panel.add(area);
        area.setLineWrap(true);
        //JScrollPane pane=new JScrollPane(area, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        panel.setLayout(null);
        JLabel nameLabel = new JLabel("          姓名：");
        nameLabel.setBounds(10, 20, 80, 25);
        panel.add(nameLabel);

        JLabel nol = new JLabel("Chinese");
        nol.setBounds(999, 999, 999, 999);
        panel.add(nol);
        /* 
         * 创建文本域用于用户输入
         */
        //
        JTextField nameText = new JTextField(20);
        nameText.setBounds(100, 20, 165, 25);
        panel.add(nameText);
        //
        JLabel namberLabel = new JLabel("          学号：");
        namberLabel.setBounds(10, 50, 80, 25);
        panel.add(namberLabel);

        JTextField namberText = new JTextField(20);
        namberText.setBounds(100, 50, 165, 25);
        panel.add(namberText);
        //
        JLabel kLabel = new JLabel("          科目：");
        kLabel.setBounds(10, 80, 80, 25);
        panel.add(kLabel);

        JComboBox comboBox = new JComboBox();
        comboBox.addItem("Chinese");
        comboBox.addItem("Maths");
        comboBox.addItem("English");
        panel.add(comboBox);
        comboBox.setBounds(100, 80, 165, 25);
        comboBox.addItemListener(new ItemListener() {

            public void itemStateChanged(ItemEvent ie) {
                if (ie.getStateChange() == 1) {

                    nol.setText(ie.getItem().toString());
                }
            }

        });
        //
        JLabel cLabel = new JLabel("          成绩：");
        cLabel.setBounds(10, 110, 80, 25);
        panel.add(cLabel);

        JTextField cText = new JTextField(20);
        cText.setBounds(100, 110, 165, 25);
        panel.add(cText);
        //
        JLabel findLabel = new JLabel("     查询学号:");
        findLabel.setBounds(10, 140, 80, 25);
        panel.add(findLabel);

        JTextField findText = new JTextField(20);
        findText.setBounds(100, 140, 165, 25);
        panel.add(findText);

        // 创建录入按钮
        JButton wButton = new JButton("录入");
        wButton.setBounds(40, 170, 80, 25);
        panel.add(wButton);
        wButton.addActionListener(new ActionListener() {

        area.append( "My GitHub Project is \nhttps://github.com/PenSeeYou/Student-Achievement-Management-System");    
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == wButton) {
                    FileWriter fw = null;
                    if (namberText.getText().trim().equals("") || nameText.getText().trim().equals("") || cText.getText().trim().equals("")) {
                        JOptionPane.showMessageDialog(wButton, "录入错误，请检查是否将前四项填写！");
                    } else {
                        try {
                            new File("DATA").mkdirs();
                            new File("DATA", "C").mkdirs();
                            new File("DATA", "M").mkdirs();
                            new File("DATA", "E").mkdirs();

                            String a = "Chinese";
                            String nn = nol.getText();

                            if (nn == "语文") {
                                a = "DATA/C/" + namberText.getText();
                                fw.write("学号:" + namberText.getText() + "号 " + nameText.getText() + " " + "语文" + " " + cText.getText());
                            }
                            if (nn == "数学") {
                                a = "DATA/M/" + namberText.getText();
                                fw.write("学号:" + namberText.getText() + "号 " + nameText.getText() + " " + "数学" + " " + cText.getText());
                            }
                            if (nn == "英语") {
                                a = "DATA/E/" + namberText.getText();
                                fw.write("学号:" + namberText.getText() + "号 " + nameText.getText() + " " + "英语" + " " + cText.getText());
                            }

                            fw = new FileWriter(a);

                            fw.flush();
                            fw.close();
                            JOptionPane.showMessageDialog(wButton, "录入成功！");
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                    namberText.setText("");
                    nameText.setText("");
                    //kText.setText("");
                    cText.setText("");
                    findText.setText("");
                }
            }
        });
        // 创建查询按钮
        JButton fButton = new JButton("查询");
        fButton.setBounds(180, 170, 80, 25);
        panel.add(fButton);
        fButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (findText.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(fButton, "查询错误，请检查是否将最后一项填写！");
                }

                try {
                    String FIND = "DATA/C/" + findText.getText();
                    BufferedReader fin = new BufferedReader(
                            new FileReader(FIND));
                    String ss = fin.readLine();
                    area.append(ss + "\n");

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                try {
                    String FIND = "DATA/M/" + findText.getText();
                    BufferedReader fin = new BufferedReader(
                            new FileReader(FIND));
                    String ss = fin.readLine();
                    area.append(ss + "\n");

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                try {
                    String FIND = "DATA/E/" + findText.getText();
                    BufferedReader fin = new BufferedReader(
                            new FileReader(FIND));
                    String ss = fin.readLine();
                    area.append(ss + "\n");

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

}
