package view;

import javax.swing.*;
import java.awt.*;

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
    JLabel usernameLabel;
    JLabel passwordLabel;

    JTextField usernameField;
    JTextField passwordField;

    JButton submitButton;
    JButton cancelBUtton;

    UserLoginPanel(){
        usernameLabel = new JLabel("Username");
        passwordLabel = new JLabel("Password");

        usernameField = new JTextField();
        passwordField = new JTextField();


        cancelBUtton = new JButton("Cancel");
        submitButton = new JButton("Submit");
        setLayout(new GridLayout(3,2));

        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(cancelBUtton);
        add(submitButton);
    }
}
