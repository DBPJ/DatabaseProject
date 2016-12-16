package view;

import manager.impl.UserManagerImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserLogin extends JFrame implements ActionListener {
    JButton jb1, jb2;
    JTextField jtf1;
    JPasswordField jpf1;

    public UserLogin() {
        setTitle("Login");
        setSize(300, 180);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JPanel jp = new JPanel(new GridLayout(4, 1));
        JPanel jp1 = new JPanel();
        JLabel jl1 = new JLabel("Account: ");
        jtf1 = new JTextField(12);
        jp1.add(jl1);
        jp1.add(jtf1);
        jp.add(jp1);
        // 第二行
        JPanel jp2 = new JPanel();
        JLabel jl2 = new JLabel("Password: ");
        jpf1 = new JPasswordField(12);
        jp2.add(jl2);
        jp2.add(jpf1);
        jp.add(jp2);
        // 第三行
        JPanel jp3 = new JPanel();
        jb1 = new JButton("Login");
        jb1.addActionListener(this);
        jb2 = new JButton("Reset");
        jb2.addActionListener(this);
        jp3.add(jb1);
        jp3.add(jb2);
        jp.add(jp3);
        add(jp);

    }

    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (cmd.equals("Login")) {
            String number = jtf1.getText();
            String password = new String(jpf1.getPassword());
            UserManagerImpl userManager = new UserManagerImpl();
            if(userManager.verifyUser(number,password)){
                setVisible(false);
                dispose();
            }
            else {
                JOptionPane.showMessageDialog(UserLogin.this,"Sorry, Number or password you input is wrong !!!", "Error", JOptionPane.ERROR_MESSAGE);
                clearText();
            }
        } else if (cmd.equals("Reset")) {
            clearText();
        }

    }

    private void clearText() {
        jtf1.setText("");
        jpf1.setText("");
    }


    public static void main(String[] args) {
        new UserLogin().setVisible(true);//创建登录窗口,并可见
    }

}