package view;

import entity.Director;
import entity.Gender;
import entity.Staff;
import manager.impl.StaffManagerImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//import static view.DirectorUI.loginInfo;

/**
 * Created by Jindiwei on 2016/12/15.
 */
public class DirectorUI extends JFrame {
    public DirectorUI() {
        int width = 800;
        int height = 460;
        setTitle("Director");
        setSize(new Dimension(width, height));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int w = (Toolkit.getDefaultToolkit().getScreenSize().width - width) / 2;
        int h = (Toolkit.getDefaultToolkit().getScreenSize().height - height) / 2;
        setLocation(w, h);
        setVisible(true);
        DirectorPanel directorPanel = new DirectorPanel();
        add(directorPanel);
    }

    public DirectorUI(Director director) {
        int width = 800;
        int height = 460;
        setTitle("Director");
        setSize(new Dimension(width, height));
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int w = (Toolkit.getDefaultToolkit().getScreenSize().width - width) / 2;
        int h = (Toolkit.getDefaultToolkit().getScreenSize().height - height) / 2;
        setLocation(w, h);
        String logininfo = "ID: " + director.getNumber() + " Name: " + director.getName() + " 部门: " + director.getDepartmentName();
        DirectorPanel directorPanel = new DirectorPanel(logininfo);
        add(directorPanel);


    }


    public static void main(String args[]) {
        int width = 800;
        int height = 460;
        DirectorUI directorUI = new DirectorUI();
        directorUI.setTitle("Director");
        directorUI.setSize(new Dimension(width, height));
        directorUI.setVisible(true);
        directorUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int w = (Toolkit.getDefaultToolkit().getScreenSize().width - width) / 2;
        int h = (Toolkit.getDefaultToolkit().getScreenSize().height - height) / 2;
        directorUI.setLocation(w, h);

    }


    /**
     * the panel contain all of other panels
     */


    class DirectorPanel extends JPanel {
        DirectorPanel() {
            LeftPanel leftPanel = new LeftPanel();
            RightPanel rightPanel = new RightPanel();
            setLayout(new GridLayout(1, 2));
            add(leftPanel);
            add(rightPanel);
        }

        DirectorPanel(String info) {
            LeftPanel leftPanel = new LeftPanel(info);
            RightPanel rightPanel = new RightPanel();
            setLayout(new GridLayout(1, 2));
            add(leftPanel);
            add(rightPanel);
        }


    }

    /**
     * this panel is on the left
     */
    class LeftPanel extends JPanel {
        LeftPanel() {
            JPanel loginInfo = new JPanel();
            JLabel info = new JLabel("sdfsdf");
            loginInfo.add(info);
            AddStaffPanel addStaffPanel = new AddStaffPanel();
            DeleteStaffPanel deleteStaffPanel = new DeleteStaffPanel();
            QueryOrModifyPanel queryOrDeletePanel = new QueryOrModifyPanel();

            setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();

            c.fill = GridBagConstraints.EAST;
            c.gridx = 0;
            c.gridy = 0;

            add(loginInfo);
            c.fill = GridBagConstraints.EAST;
            c.gridx = 0;
            c.gridy = 1;
            add(addStaffPanel, c);

            c.fill = GridBagConstraints.EAST;
            c.gridx = 0;
            c.gridy = 2;
//        deleteStaffPanel.setBounds(0, 400, 440, 80);
            add(deleteStaffPanel, c);

            c.fill = GridBagConstraints.EAST;
            c.gridx = 0;
            c.gridy = 3;
            add(queryOrDeletePanel, c);
        }

        LeftPanel(String logininfo) {
            JPanel loginInfo = new JPanel();
            JLabel info = new JLabel(logininfo);
            loginInfo.add(info);
            AddStaffPanel addStaffPanel = new AddStaffPanel();
            DeleteStaffPanel deleteStaffPanel = new DeleteStaffPanel();
            QueryOrModifyPanel queryOrDeletePanel = new QueryOrModifyPanel();

            setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();

            c.fill = GridBagConstraints.EAST;
            c.gridx = 0;
            c.gridy = 0;

            add(loginInfo);
            c.fill = GridBagConstraints.EAST;
            c.gridx = 0;
            c.gridy = 1;
            add(addStaffPanel, c);

            c.fill = GridBagConstraints.EAST;
            c.gridx = 0;
            c.gridy = 2;
            add(deleteStaffPanel, c);

            c.fill = GridBagConstraints.EAST;
            c.gridx = 0;
            c.gridy = 3;
            add(queryOrDeletePanel, c);
        }

    }

    class AddStaffPanel extends JPanel {
        JLabel numberLabel;
        JLabel nameLabel;
        JLabel genderLabel;
        JLabel departmentLabel;
        JLabel workAgeLabel;
        JLabel locationLabel;
        JLabel salaryLabel;
        JLabel additionrateLabel;

        JTextField numberField;
        JTextField nameField;
        JTextField departmentField;
        JTextField workAgeField;
        JTextField locationField;
        JTextField salartField;
        JTextField additionrateField;

        JComboBox genderCB;

        JButton cancel;
        JButton submit;

        StaffManagerImpl staffManager = new StaffManagerImpl();

        AddStaffPanel() {
            setLayout(new GridLayout(5, 4));
            numberLabel = new JLabel("ID");
            add(numberLabel);
            numberField = new JTextField();
            add(numberField);

            nameLabel = new JLabel("Name");
            add(nameLabel);
            nameField = new JTextField();
            add(nameField);

            genderLabel = new JLabel("Gender");
            add(genderLabel);
            String[] gender = {"male", "female"};
            genderCB = new JComboBox(gender);
            add(genderCB);


            departmentLabel = new JLabel("Department");
            add(departmentLabel);
            departmentField = new JTextField();
            add(departmentField);


            workAgeLabel = new JLabel("Work age");
            add(workAgeLabel);
            workAgeField = new JTextField("0");
            add(workAgeField);

            locationLabel = new JLabel("Location");
            add(locationLabel);
            locationField = new JTextField();
            add(locationField);

            salaryLabel = new JLabel("Salary");
            add(salaryLabel);
            salartField = new JTextField("0.0");
            add(salartField);

            additionrateLabel = new JLabel("Addition rate");
            add(additionrateLabel);
            additionrateField = new JTextField("0.0");
            add(additionrateField);

            submit = new JButton("Submit");
            add(submit);
            cancel = new JButton("Cancel");
            add(cancel);

            setBorder(BorderFactory.createTitledBorder("Add Staff"));

            cancel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    numberField.setText("");
                    nameField.setText("");
                    departmentField.setText("");
                    workAgeField.setText("0");
                    locationField.setText("");
                    salartField.setText("0.0");
                    additionrateField.setText("0.0");
                    genderCB.setSelectedIndex(0);

                }
            });

            submit.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    String number = numberField.getText();
                    String name = nameField.getText();
                    String gender = genderCB.getActionCommand();
                    String department_name = departmentField.getText();
                    String work_age = workAgeField.getText();
                    String location = locationField.getText();
                    String salary = salartField.getText();
                    String additionrate = additionrateField.getText();
                    boolean isEmpty = false;
                    //todo: 点击submit时检查Type？
                    if (number.length() == 0) {
                        isEmpty = true;
                    }
                    if (name.length() == 0) {
                        isEmpty = true;
                    }

                    if (department_name.length() == 0) {
                        isEmpty = true;
                    }

                    if (location.length() == 0) {
                        isEmpty = true;
                    }
                    if (salary.equals("0.0")) {
                        isEmpty = true;
                    }
                    if (!isEmpty) {
                        boolean rt = staffManager.addStaffInfo(number, name, gender, department_name, Integer.valueOf(work_age), location, Double.valueOf(salary), Double.valueOf(additionrate));
                        if (rt) {
                            JOptionPane.showMessageDialog(AddStaffPanel.this, "Succeed !!!");
                        } else {
                            JOptionPane.showMessageDialog(AddStaffPanel.this, "Sorry, Something is wrong !!!", "information", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(AddStaffPanel.this, "The Staff's information is incomple !!!", "Alert", JOptionPane.WARNING_MESSAGE);
                    }
                }
            });
        }
    }

    class DeleteStaffPanel extends JPanel {
        JLabel numberLabel;
        JTextField numberField;
        JLabel hiddenLabel;
        JTextField hiddenField;
        JButton deleteButton;
        JButton cancelButton;

        StaffManagerImpl staffManager = new StaffManagerImpl();

        DeleteStaffPanel() {
            setLayout(new GridLayout(1, 5));
            numberLabel = new JLabel("ID");
            add(numberLabel);

            numberField = new JTextField();
            add(numberField);

            deleteButton = new JButton("Delete");

            add(deleteButton);
            cancelButton = new JButton("Cancel");
            add(cancelButton);

            setBorder(BorderFactory.createTitledBorder("Delete Staff"));

            cancelButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    numberField.setText("");
                }
            });

            deleteButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    String number = numberField.getText();
                    if (number.length() == 0) {
                        JOptionPane.showMessageDialog(DeleteStaffPanel.this, "The Staff's ID is empty !!!", "Alert", JOptionPane.WARNING_MESSAGE);
                    } else {
                        // TODO : where get the director object
                        boolean rt = staffManager.deleteStaffInfo("人事", number);
                        if (rt) {
                            JOptionPane.showMessageDialog(DeleteStaffPanel.this, "Succeed !!!");
                        } else {
                            JOptionPane.showMessageDialog(DeleteStaffPanel.this, "Sorry, Something is wrong !!!", "information", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            });


        }
    }

    class QueryOrModifyPanel extends JPanel {
        JLabel idQueryLabel;

        JLabel numberLabel;
        JLabel nameLabel;
        JLabel genderLabel;
        JLabel departmentLabel;
        JLabel workAgeLabel;
        JLabel locationLabel;
        JLabel salaryLabel;
        JLabel additionrateLabel;

        JTextField idQueryField;
        JTextField numberField;
        JTextField nameField;
        JTextField departmentField;
        JTextField workAgeField;
        JTextField locationField;
        JTextField salartField;
        JTextField additionrateField;

        JComboBox genderCB;

        JButton search;
        JButton cancel;
        JButton submit;


        StaffManagerImpl staffManager = new StaffManagerImpl();
        Staff staff;

        QueryOrModifyPanel() {
            setLayout(new GridLayout(6, 4));

            idQueryLabel = new JLabel("Staff ID");
            add(idQueryLabel);
            idQueryField = new JTextField();
            add(idQueryField);

            search = new JButton("Search");
            add(search);

            JLabel addLabel = new JLabel("");
            add(addLabel);
            addLabel.setVisible(false);

            numberLabel = new JLabel("ID");
            add(numberLabel);
            numberField = new JTextField();
            add(numberField);

            nameLabel = new JLabel("Name");
            add(nameLabel);
            nameField = new JTextField();
            add(nameField);

            genderLabel = new JLabel("Gender");
            add(genderLabel);
            String[] gender = {"male", "female"};
            genderCB = new JComboBox(gender);
            add(genderCB);


            departmentLabel = new JLabel("Department");
            add(departmentLabel);
            departmentField = new JTextField();
            add(departmentField);


            workAgeLabel = new JLabel("Work age");
            add(workAgeLabel);
            workAgeField = new JTextField();
            add(workAgeField);

            locationLabel = new JLabel("Location");
            add(locationLabel);
            locationField = new JTextField();
            add(locationField);

            salaryLabel = new JLabel("Salary");
            add(salaryLabel);
            salartField = new JTextField();
            add(salartField);

            additionrateLabel = new JLabel("Addition rate");
            add(additionrateLabel);
            additionrateField = new JTextField();
            add(additionrateField);

            submit = new JButton("Submit");
            add(submit);
            cancel = new JButton("Cancel");
            add(cancel);

            setBorder(BorderFactory.createTitledBorder("Add Staff"));


            search.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    String number = idQueryField.getText();
                    if (number.length() == 0) {
                        JOptionPane.showMessageDialog(QueryOrModifyPanel.this, "The Staff's ID is empty !!!", "Alert", JOptionPane.WARNING_MESSAGE);
                    } else {
                        // TODO : where get the director object
                        staff = staffManager.getStaffInfo("人事", number);
                        if (staff == null) {
                            JOptionPane.showMessageDialog(QueryOrModifyPanel.this, "Sorry, Something is wrong !!!", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            numberField.setText(staff.getNumber());
                            numberField.setEditable(false);
                            nameField.setText(staff.getName());
                            departmentField.setText(staff.getDepartmentName());
                            workAgeField.setText(String.valueOf(staff.getWorkAge()));
                            locationField.setText(staff.getLocation());
                            salartField.setText(String.valueOf(staff.getSalary()));
                            additionrateField.setText(String.valueOf(staff.getAdditionRate()));
                            // TODO :注意检查返回值
                            genderCB.setActionCommand(staff.getGender().toString());
                        }
                    }
                }
            });

            submit.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    String number = numberField.getText();
                    String name = nameField.getText();
                    String gender = genderCB.getActionCommand();
                    String department_name = departmentField.getText();
                    String work_age = workAgeField.getText();
                    String location = locationField.getText();
                    String salary = salartField.getText();
                    String additionrate = additionrateField.getText();
                    boolean isEmpty = false;
                    if (number.length() == 0) {
                        isEmpty = true;
                    }
                    if (name.length() == 0) {
                        isEmpty = true;
                    }

                    if (department_name.length() == 0) {
                        isEmpty = true;
                    }

                    if (location.length() == 0) {
                        isEmpty = true;
                    }
                    if (salary.equals("0.0")) {
                        isEmpty = true;
                    }
                    if (!isEmpty) {
                        Staff staff_new = new Staff();
                        staff_new.setNumber(number);
                        staff_new.setName(name);
                        staff_new.setGender(getGender(gender));
                        staff_new.setDepartmentName(department_name);
                        staff_new.setWorkAge(Integer.valueOf(work_age));
                        staff_new.setLocation(location);
                        staff_new.setSalary(Double.valueOf(salary));
                        staff_new.setAdditionRate(Double.valueOf(additionrate));

                        //TODO : where to get the department name ?
                        boolean rt = staffManager.updateStaffInfo("人事", number, staff);
                        if (rt) {
                            staff = staff_new;
                            JOptionPane.showMessageDialog(QueryOrModifyPanel.this, "Succeed !!!");
                        } else {
                            JOptionPane.showInternalMessageDialog(QueryOrModifyPanel.this, "Sorry, Something is wrong !!!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(QueryOrModifyPanel.this, "The Staff's information is incomple !!!", "Alert", JOptionPane.WARNING_MESSAGE);
                    }
                }
            });

            cancel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    if (staff != null) {
                        numberField.setText(staff.getNumber());
                        numberField.setEditable(false);
                        nameField.setText(staff.getName());
                        departmentField.setText(staff.getDepartmentName());
                        workAgeField.setText(String.valueOf(staff.getWorkAge()));
                        locationField.setText(staff.getLocation());
                        salartField.setText(String.valueOf(staff.getSalary()));
                        additionrateField.setText(String.valueOf(staff.getAdditionRate()));
                        // TODO :注意检查返回值
                        genderCB.setSelectedIndex(0);
                    }
                }
            });
        }

        private Enum getGender(String gender) {
            if (gender.equals("male")) {
                return Gender.MALE;
            } else {
                return Gender.FEMALE;
            }
        }
    }


    /**
     * this panel is on the right
     */
    class RightPanel extends JPanel {
        ResultPanel resultPanel = new ResultPanel();

        RightPanel() {
            JLabel j1;
            JComboBox j2;
            JButton j3;
            JPanel j4;
            JPanel j9;
            j1 = new JLabel("Choose : ");
            String[] choices = {"Courses Staffs Choosed", "Courses Information"};
            j2 = new JComboBox(choices);
            j3 = new JButton("Commit");
            j4 = new JPanel();
            j9 = new ResultPanel();
            j9.setBackground(Color.PINK);//为了看出效果，设置了颜色

            GridBagLayout layout = new GridBagLayout();
            setLayout(layout);
            add(j1);//把组件添加进jframe
            add(j2);
            add(j3);
            add(j4);
            add(j9);
            GridBagConstraints s = new GridBagConstraints();
            s.fill = GridBagConstraints.BOTH;
            s.gridwidth = 1;
            s.weightx = 0;
            s.weighty = 0;
            layout.setConstraints(j1, s);
            s.gridwidth = 2;
            s.weightx = 0;
            s.weighty = 0;
            layout.setConstraints(j2, s);
            s.gridwidth = 1;
            s.weightx = 0;
            s.weighty = 0;
            layout.setConstraints(j3, s);
            s.gridwidth = 0;
            s.weightx = 0;
            s.weighty = 0;
            layout.setConstraints(j4, s);
            s.gridwidth = 5;
            s.weightx = 0;
            s.weighty = 1;
            layout.setConstraints(j9, s);
        }
    }

    class ResultPanel extends JPanel {
        ResultPanel() {

        }
    }
}



