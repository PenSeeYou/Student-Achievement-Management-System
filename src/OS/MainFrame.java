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

    private static final File ROOT_PATH;

    // 只需要初始化一次
    static {
        ROOT_PATH = new File("./database");
        // 如果目标路径是文件，则删除，因为同一个路径下不能出现同名的文件和文件夹
        if (ROOT_PATH.isFile()) {
            ROOT_PATH.delete();
        }
        if (!ROOT_PATH.exists()) {
            ROOT_PATH.mkdirs();
        }
    }

    private static boolean createDir(File file) {
        if (file == null) {
            return false;
        }

        if (file.exists() && file.isFile()) {
            file.delete();
        }

        if (file.exists() && file.isDirectory()) {
            return true;
        } else {
            file.mkdirs();
            return true;
        }

    }

    private static boolean createFile(File file) throws IOException {
        if (file == null) {
            return false;
        }
        if (file.exists() && file.isDirectory()) {
            file.delete();
        }

        if (file.exists() && file.isFile()) {
            return true;
        } else {
            file.createNewFile();
            return true;
        }
    }

    public MainFrame(String[] args) {
        // 创建 JFrame 实例
        JFrame frame = new JFrame("学生成绩管理系统");
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
        frame.setLocationRelativeTo(null);//居中        
        //frame.setResizable(false);
        frame.add(panel);
        /*
         * 调用用户定义的方法并添加组件到面板
         */
        placeComponents(panel);

        // 设置界面可见
        frame.setVisible(true);

    }

    private static void placeComponents(JPanel panel) {

        //JTextField dataText = new JTextField("");
        //dataText.setBounds(30,240,250,250);
        //panel.add(dataText);
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(30, 240, 250, 250);
        scrollPane_1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
        panel.add(scrollPane_1);
        JTextArea area = new JTextArea(3, 20);
        //area.setBounds(30, 240, 250, 250);
        panel.add(area);
        //area.setLineWrap(true);

        scrollPane_1.setViewportView(area);

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

        area.append("My GitHub Project is \nhttps://github.com/PenSeeYou/Student-Achievement-Management-System\n");

        // 创建录入按钮
        JButton wButton = new JButton("录入");
        wButton.setBounds(40, 170, 80, 25);
        panel.add(wButton);
        wButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == wButton) {
                    FileWriter fw = null;
                    if (namberText.getText().trim().equals("") || nameText.getText().trim().equals("") || cText.getText().trim().equals("")) {
                        JOptionPane.showMessageDialog(wButton, "录入错误，请检查是否将前四项填写！");
                    } else {
                        try {

                            File chinese = new File(ROOT_PATH, "C");
                            File math = new File(ROOT_PATH, "M");
                            File english = new File(ROOT_PATH, "E");

                            if (!createDir(chinese) || !createDir(math) || !createDir(english)) {
                                throw new UnsupportedOperationException("create file dir error");
                            }

                            String a = "Chinese";
                            String nn = nol.getText();

                            if (nn.equals("Chinese")) {
                                a = chinese.getAbsolutePath() + File.separator + namberText.getText();
                                if (!createFile(new File(a))) {
                                    throw new UnsupportedOperationException("create file error");
                                }
                                fw = new FileWriter(a);
                                fw.write("学号:" + namberText.getText() + "号 " + nameText.getText() + " " + "语文" + " " + cText.getText());
                            }
                            if (nn.equals("Maths")) {
                                a = math.getAbsolutePath() + File.separator + namberText.getText();

                                if (!createFile(new File(a))) {
                                    throw new UnsupportedOperationException("create file error");
                                }
                                fw = new FileWriter(a);
                                fw.write("学号:" + namberText.getText() + "号 " + nameText.getText() + " " + "数学" + " " + cText.getText());
                            }
                            if (nn.equals("English")) {
                                a = english.getAbsolutePath() + File.separator + namberText.getText();
                                if (!createFile(new File(a))) {
                                    throw new UnsupportedOperationException("create file error");
                                }
                                fw = new FileWriter(a);
                                fw.write("学号:" + namberText.getText() + "号 " + nameText.getText() + " " + "英语" + " " + cText.getText());
                            }

                            fw.flush();
                            fw.close();
                            JOptionPane.showMessageDialog(wButton, "录入成功！");
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(wButton, "录入失败！");
                        }
                    }

                    cText.setText("");
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
                } else {
                    File chinese = new File(ROOT_PATH, "C");
                    File math = new File(ROOT_PATH, "M");
                    File english = new File(ROOT_PATH, "E");
                    try {

                        String FIND = chinese.getAbsolutePath() + File.separator + findText.getText();
                        BufferedReader fin = new BufferedReader(
                                new FileReader(FIND));
                        String ss = fin.readLine();
                        area.append(ss + "\n");

                    } catch (Exception ex) {
                        ex.printStackTrace();

                        JOptionPane.showMessageDialog(wButton, "没有找到" + findText.getText() + "号" + "学生的语文成绩");
                    }
                    try {
                        String FIND = math.getAbsolutePath() + File.separator + findText.getText();
                        BufferedReader fin = new BufferedReader(
                                new FileReader(FIND));
                        String ss = fin.readLine();
                        area.append(ss + "\n");

                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(wButton, "没有找到" + findText.getText() + "号" + "学生的数学成绩");
                    }
                    try {
                        String FIND = english.getAbsolutePath() + File.separator + findText.getText();
                        BufferedReader fin = new BufferedReader(
                                new FileReader(FIND));
                        String ss = fin.readLine();
                        area.append(ss + "\n");

                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(wButton, "没有找到" + findText.getText() + "号" + "学生的英语成绩");
                    }
                }
                findText.setText("");
            }
        });

        JButton nullButton = new JButton("重置");
        nullButton.setBounds(40, 210, 80, 25);
        panel.add(nullButton);
        nullButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                namberText.setText("");
                nameText.setText("");
                //kText.setText("");
                cText.setText("");
                findText.setText("");

            }
        });

        JButton paiButton = new JButton("排名");
        paiButton.setBounds(180, 210, 80, 25);
        panel.add(paiButton);
    }

}
