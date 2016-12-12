package view;

import manager.impl.UserManagerImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by alex on 12/12/2016.
 */
public class UserLogin extends JFrame{
    UserLoginPanel panel;


    UserLogin(){
        panel = new UserLoginPanel();
        add(panel);
    }

    public static void main(String[] args) {
        UserLogin userLogin = new UserLogin();
        userLogin.setTitle("Sign In");
        userLogin.setVisible(true);
        userLogin.setSize(new Dimension(300,200));
        userLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

class UserLoginPanel extends JPanel{
    JLabel numberLabel;
    JLabel passwordLabel;

    JTextField numberField;
    JTextField passwordField;

    JButton submitButton;
    JButton cancelBUtton;

    UserLoginPanel(){
        numberLabel = new JLabel("Number");
        passwordLabel = new JLabel("Password");

        numberField = new JTextField();
        passwordField = new JTextField();


        cancelBUtton = new JButton("Cancel");
        submitButton = new JButton("Submit");
        setLayout(new GridLayout(3,2));

        add(numberLabel);
        add(numberField);
        add(passwordLabel);
        add(passwordField);
        add(cancelBUtton);
        add(submitButton);

        submitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String number = numberField.getText();
                String password = passwordField.getText();
                UserManagerImpl userManager = new UserManagerImpl();
                System.out.println(userManager.verifyUser(number,password));
                //todo: 登录成功或失败之后需要有不同的提示，或者直接进入下一个UI
            }
        });
    }
}
