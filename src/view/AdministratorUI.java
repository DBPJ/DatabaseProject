package view;

import entity.Administrator;
import entity.User;
import manager.impl.UserManagerImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by alex on 12/12/2016.
 */
public class AdministratorUI extends JFrame{
    AdministratorPanel administratorPanel;

    AdministratorUI(){
        administratorPanel = new AdministratorPanel();
        add(administratorPanel);
    }

    public static void main(String[] args) {
        AdministratorUI administratorUI = new AdministratorUI();
        administratorUI.setTitle("Administrator");
        administratorUI.setSize(new Dimension(400,400));
        administratorUI.setVisible(true);
        administratorUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
class AdministratorPanel extends JPanel{
    JPanel singleAddPanel;

    JPanel batchAddPanel;

    JPanel deletePanel;

    JPanel updatePanel;

    JPanel queryPanel;


    AdministratorPanel(){
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        singleAddPanel = new SingleAddPanel();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        add(singleAddPanel,c);


        batchAddPanel = new BatchAddPanel();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 1;
        add(batchAddPanel,c);

        deletePanel =  new DeletePanel();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 2;
        add(deletePanel,c);

        updatePanel = new UpdatePanel();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 3;
        add(updatePanel,c);

        queryPanel = new QueryPanel();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 4;
        add(queryPanel,c);
    }



}

class SingleAddPanel extends JPanel{
    JLabel numberLabel;
    JLabel passwordLabel;
    JLabel typeLabel;

    JTextField numberField;
    JTextField passwordField;
    JTextField typeField;

    JButton cancel;
    JButton submit;

    UserManagerImpl userManager = new UserManagerImpl();

    SingleAddPanel(){
        setLayout(new GridLayout(2,4));
        numberLabel = new JLabel("Number");
        add(numberLabel);
        numberField = new JTextField();
        add(numberField);

        passwordLabel = new JLabel("Password");
        add(passwordLabel);
        passwordField = new JTextField();
        add(passwordField);

        typeLabel = new JLabel("Type");
        add(typeLabel);
        typeField = new JTextField();
        add(typeField);

        cancel = new JButton("Cancel");
        add(cancel);
        submit = new JButton("Submit");
        add(submit);

        setBorder(BorderFactory.createTitledBorder("SingleAdd"));

        cancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                numberField.setText("");
                passwordField.setText("");
                typeField.setText("");
            }
        });

        submit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String number = numberField.getText();
                String password = passwordField.getText();
                String type = typeField.getText();
                //todo: 点击submit时检查Type？
                userManager.addUser(number,password,type);
            }
        });
    }
}

class BatchAddPanel extends JPanel{
    JLabel filenameLabel;
    JTextField filenameField;
    JButton cancel;
    JButton submit;

    UserManagerImpl userManager = new UserManagerImpl();

    BatchAddPanel(){
        setLayout(new GridLayout(1,4));
        filenameLabel = new JLabel("File path:");
        add(filenameLabel);
        filenameField = new JTextField();
        add(filenameField);
        cancel = new JButton("Cancel");
        add(cancel);
        submit = new JButton("submit");
        add(submit);

        setBorder(BorderFactory.createTitledBorder("BatchAdd"));

        cancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                filenameField.setText("");
            }
        });

        submit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String filename = filenameField.getText();
                userManager.addUsers(filename);
            }
        });
    }
}

class DeletePanel extends JPanel{
    JLabel numberLabel;
    JTextField numberField;
    JButton cancel;
    JButton submit;

    UserManagerImpl userManager = new UserManagerImpl();

    DeletePanel(){
        setLayout(new GridLayout(1,4));
        numberLabel = new JLabel("Number:");
        add(numberLabel);
        numberField = new JTextField();
        add(numberField);
        cancel = new JButton("Cancel");
        add(cancel);
        submit = new JButton("submit");
        add(submit);

        setBorder(BorderFactory.createTitledBorder("Delete"));

        cancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                numberField.setText("");
            }
        });

        submit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String number = numberField.getText();
                userManager.deleteUser(number);
            }
        });
    }
}
class  UpdatePanel extends JPanel{
    JLabel numberLabel;
    JLabel passwordLabel;
    JLabel typeLabel;

    JTextField numberField;
    JTextField passwordField;
    JTextField typeField;

    JButton cancel;
    JButton submit;

    UserManagerImpl userManager = new UserManagerImpl();

    UpdatePanel(){
        setLayout(new GridLayout(2,4));
        numberLabel = new JLabel("Number");
        add(numberLabel);
        numberField = new JTextField();
        add(numberField);

        passwordLabel = new JLabel("Password");
        add(passwordLabel);
        passwordField = new JTextField();
        add(passwordField);

        typeLabel = new JLabel("Type");
        add(typeLabel);
        typeField = new JTextField();
        add(typeField);

        cancel = new JButton("Cancel");
        add(cancel);
        submit = new JButton("Submit");
        add(submit);

        setBorder(BorderFactory.createTitledBorder("Update"));

        cancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                numberField.setText("");
                passwordField.setText("");
                typeField.setText("");
            }
        });

        submit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String number = numberField.getText();
                String password = passwordField.getText();
                String type = typeField.getText();
                //todo: 点击submit时检查Type？
                userManager.updateUserInfo(number,password,type);
            }
        });
    }
}

class QueryPanel extends  JPanel{
    JLabel numberLabel;
    JLabel passwordLabel;
    JLabel typeLabel;

    JTextField numberField;
    JTextField passwordField;
    JTextField typeField;

    JButton cancel;
    JButton submit;


    UserManagerImpl userManager = new UserManagerImpl();

    QueryPanel(){
        setLayout(new GridLayout(2,4));
        numberLabel = new JLabel("Number");
        add(numberLabel);
        numberField = new JTextField();
        add(numberField);

        cancel = new JButton("Cancel");
        add(cancel);
        submit = new JButton("Submit");
        add(submit);

        passwordLabel = new JLabel("Password");
        add(passwordLabel);
        passwordField = new JTextField();
        passwordField.setEditable(false);
        add(passwordField);

        typeLabel = new JLabel("Type");
        add(typeLabel);
        typeField = new JTextField();
        typeField.setEditable(false);
        add(typeField);

        setBorder(BorderFactory.createTitledBorder("Query"));

        cancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                numberField.setText("");
                passwordField.setText("");
                typeField.setText("");
            }
        });

        submit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String number = numberField.getText();
                User user = userManager.queryUser(number);
                passwordField.setText(user.getPassword());
                typeField.setText(user.getType());
            }
        });
    }
}
