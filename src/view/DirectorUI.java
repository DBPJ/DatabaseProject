package view;

import entity.*;
import manager.impl.CourseManagerImpl;
import manager.impl.StaffManagerImpl;

import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;


//import static view.DirectorUI.loginInfo;

/**
 * Created by Jindiwei on 2016/12/15.
 */
public class DirectorUI extends JFrame {
    Director director;
    public DirectorUI() {
        Director director = new Director();
        director.setDepartmentName("人事");
        this.director = director;
        int width = 1000;
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
        this.director = director;
        int width = 1000;
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
        int width = 1000;
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
        JLabel choose;
        JComboBox choice;
        JButton commit;
        JPanel nouse;
        JLabel j1;
        JLabel j2;
        JLabel j3;
        ResultPanel resultPanel;

        RightPanel() {

            choose = new JLabel("Choose : ");
            String[] choices = {"Courses Staffs Choosed", "Courses Information"};
            choice = new JComboBox(choices);
            commit = new JButton("Commit");
            nouse = new JPanel();
            j1 = new JLabel();
            j2 = new JLabel();
            j3 = new JLabel();
            resultPanel = new ResultPanel();
            resultPanel.setBackground(Color.PINK);//为了看出效果，设置了颜色

            GridBagLayout layout = new GridBagLayout();
            this.setLayout(layout);
            this.add(choose);//把组件添加进jframe
            this.add(choice);
            this.add(commit);
            this.add(nouse);
            this.add(j1);
            this.add(j2);
            this.add(j3);
            add(resultPanel);
            GridBagConstraints s = new GridBagConstraints();
            s.fill = GridBagConstraints.BOTH;
            s.gridwidth = 1;
            s.weightx = 0;
            s.weighty = 0;
            layout.setConstraints(choose, s);
            s.gridwidth = 1;
            s.weightx = 0;
            s.weighty = 0;
            layout.setConstraints(choice, s);
            s.gridwidth = 1;
            s.weightx = 0;
            s.weighty = 0;
            layout.setConstraints(commit, s);
            s.gridwidth = 0;
            s.weightx = 0;
            s.weighty = 0;
            layout.setConstraints(nouse, s);
            s.gridwidth = 2;
            s.weightx = 0;
            s.weighty = 0;
            layout.setConstraints(j1, s);
            s.gridwidth = 7;
            s.weightx = 1;
            s.weighty = 0;
            layout.setConstraints(j2, s);
            s.gridwidth = 0;
            s.weightx = 0;
            s.weighty = 0;
            layout.setConstraints(j3, s);
            s.gridwidth = 9;
            s.weightx = 0;
            s.weighty = 1;
            layout.setConstraints(resultPanel, s);


            commit.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    int selectedIndex = choice.getSelectedIndex();
                    resultPanel.change(selectedIndex);
                }

            });
        }
    }

    class ResultPanel extends JPanel {
        JTable table;
        StaffCourseTableModel sctm;
        JScrollPane scrollPane;

        ResultPanel() {
            table = new JTable();
//            StaffManagerImpl staffManager = new StaffManagerImpl();
//            ArrayList<String[]> courses = staffManager.queryStaffsCourses();
//            sctm = new StaffCourseTableModel(courses);
//            table.setModel(sctm);
            scrollPane = new JScrollPane(table);
            add(scrollPane);
        }

        public void change(int index) {
            switch (index) {
                case 0: {
                    StaffManagerImpl staffManager = new StaffManagerImpl();
                    List<String[]> courses = staffManager.getCourseTakenInfo(director);
                    sctm = new StaffCourseTableModel(courses);
                    table.setModel(sctm);
                    break;
                }
                case 1: {
                    CourseManagerImpl courseManager = new CourseManagerImpl();
                    List<Course> courseList = courseManager.queryCourses();
                    TableModel ctm = new ShowCoursesTableModel(courseList);
                    table.setModel(ctm);
                    TableCellRenderer comboboxRenderer = new JTableComboBoxRenderer();
                    table.getColumn("Type").setCellRenderer(comboboxRenderer);
                    JTableComBoxEditor editor = new JTableComBoxEditor();
                    String [] list = {"equired", "Electives", "Other"};
                    JComboBox types = new JComboBox(list);
                    table.getColumn("Type").setCellEditor(new DefaultCellEditor(types));
                    break;
                }
            }
        }
    }

    private class JTableComboBoxRenderer implements TableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JComboBox combobox = (JComboBox) value;
            if (hasFocus){
                System.out.println("have focus");
            }

            if (isSelected){
                System.out.println("is selected");
            }
            combobox.setEditable(true);
            return combobox;
        }
    }

    private class JTableComBoxEditor implements TableCellEditor {

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            return null;
        }

        @Override
        public Object getCellEditorValue() {
            return null;
        }

        @Override
        public boolean isCellEditable(EventObject anEvent) {
            return false;
        }

        @Override
        public boolean shouldSelectCell(EventObject anEvent) {
            return false;
        }

        @Override
        public boolean stopCellEditing() {
            return false;
        }

        @Override
        public void cancelCellEditing() {

        }

        @Override
        public void addCellEditorListener(CellEditorListener l) {

        }

        @Override
        public void removeCellEditorListener(CellEditorListener l) {

        }
    }

    class ShowCoursesTableModel implements TableModel {
        private List<Course> courses_list;
        String[] columns = new String[]{"Number","Name","Class Hour","Type"};

        public ShowCoursesTableModel(List<Course> list) {
            this.courses_list = list;
        }

        public int getRowCount() {
            return courses_list.size();
        }

        public int getColumnCount() {
            return columns.length;
        }

        public Class<?> getColumnClass(int columnIndex) {
            return String.class;
        }

        public Object getValueAt(int rowIndex, int columnIndex) {
            Course course = courses_list.get(rowIndex);
            if (columnIndex == 0) {
                return "" + course.getId();
            } else if (columnIndex == 1) {
                return course.getName();
            } else if (columnIndex == 2) {
                return "" + course.getClassHour();
            } else if (columnIndex == 3) {
                String [] list = {"equired", "Electives", "Other"};
                JComboBox types = new JComboBox(list);
                return types;
            }
            else {
                return "出错!";
            }
        }

        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        }

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            if(columnIndex == 4){
                return true;
            }
            return false;
        }

        public String getColumnName(int columnIndex) {
            return columns[columnIndex];
        }

        public void addTableModelListener(TableModelListener l) {
        }

        public void removeTableModelListener(TableModelListener l) {
        }
    }

    class StaffTableModel implements TableModel {
        private ArrayList<Staff> staff_list;

        public StaffTableModel(ArrayList<Staff> list) {
            this.staff_list = list;
        }

        public int getRowCount() {
            return staff_list.size();
        }

        public int getColumnCount() {
            return 8;
        }

        public Class<?> getColumnClass(int columnIndex) {
            return String.class;
        }

        public Object getValueAt(int rowIndex, int columnIndex) {
            Staff user = staff_list.get(rowIndex);
            if (columnIndex == 0) {
                return "" + user.getNumber();
            } else if (columnIndex == 1) {
                return user.getName();
            } else if (columnIndex == 2) {
                return "" + user.getGender().name();
            } else if (columnIndex == 3) {
                return "" + user.getDepartmentName();
            } else if (columnIndex == 4) {
                return "" + user.getLocation();
            } else if (columnIndex == 5) {
                return "" + user.getWorkAge();
            } else if (columnIndex == 6) {
                return "" + user.getSalary();
            } else if (columnIndex == 7) {
                return "" + user.getAdditionRate();
            } else {
                return "出错!";
            }
        }

        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        }

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }

        public String getColumnName(int columnIndex) {
            if (columnIndex == 0) {
                return "工 号";
            } else if (columnIndex == 1) {
                return "姓 名";
            } else if (columnIndex == 2) {
                return "性 别";
            } else if (columnIndex == 3) {
                return "部 门";
            } else if (columnIndex == 4) {
                return "工作地点";
            } else if (columnIndex == 5) {
                return "工 龄";
            } else if (columnIndex == 6) {
                return "基本工资";
            } else if (columnIndex == 7) {
                return "加成比例";
            } else {
                return "出错!";
            }
        }

        public void addTableModelListener(TableModelListener l) {
        }

        public void removeTableModelListener(TableModelListener l) {
        }
    }

    class StaffCourseTableModel implements TableModel {
        private List<String[]> course_list;

        public StaffCourseTableModel(List<String[]> list) {
            this.course_list = list;
        }

        public int getRowCount() {
            return course_list.size();
        }

        public int getColumnCount() {
            //System.out.println("this is : 6");
            return 6;
        }

        public Class<?> getColumnClass(int columnIndex) {
            return String.class;
        }

        public Object getValueAt(int rowIndex, int columnIndex) {
            String[] course = course_list.get(rowIndex);
            if (columnIndex == 0) {
                return "" + course[0];
            } else if (columnIndex == 1) {
                return course[1];
            } else if (columnIndex == 2) {
                return "" + course[2];
            } else if (columnIndex == 3) {
                return "" + course[3];
            } else if (columnIndex == 4) {
                return "" + course[4];
            } else if (columnIndex == 5) {
                return "" + course[5];
            } else {
                return "出错!";
            }
        }

        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            String info = rowIndex + "行" + columnIndex + "列的值改变: " + aValue.toString();
            javax.swing.JOptionPane.showMessageDialog(null, info);
        }

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }

        public String getColumnName(int columnIndex) {
            if (columnIndex == 0) {
                return "工 号";
            } else if (columnIndex == 1) {
                return "部 门";
            } else if (columnIndex == 2) {
                return "姓 名";
            } else if (columnIndex == 3) {
                return "课程编号";
            } else if (columnIndex == 4) {
                return "课程名称";
            } else if (columnIndex == 5) {
                return "成绩";
            } else {
                return "出错!";
            }
        }

        public void addTableModelListener(TableModelListener l) {
        }

        public void removeTableModelListener(TableModelListener l) {
        }
    }

    class TeacherTableModel implements TableModel {
        private List<Teacher> teachers_list;

        public TeacherTableModel(List<Teacher> list) {
            this.teachers_list = list;
        }

        public int getRowCount() {
            return teachers_list.size();
        }

        public int getColumnCount() {
            return 5;
        }

        public Class<?> getColumnClass(int columnIndex) {
            return String.class;
        }

        public Object getValueAt(int rowIndex, int columnIndex) {
            Teacher user = teachers_list.get(rowIndex);
            if (columnIndex == 0) {
                return "" + user.getNumber();
            } else if (columnIndex == 1) {
                return user.getName();
            } else if (columnIndex == 2) {
                return "" + user.getGender();
            } else if (columnIndex == 3) {
                return "" + user.getPhoneNumber();
            } else if (columnIndex == 4) {
                return "" + user.getEmail();
            } else {
                return "出错!";
            }
        }

        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        }

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }

        public String getColumnName(int columnIndex) {
            if (columnIndex == 0) {
                return "工 号";
            } else if (columnIndex == 1) {
                return "姓 名";
            } else if (columnIndex == 2) {
                return "性 别";
            } else if (columnIndex == 3) {
                return "联系电话";
            } else if (columnIndex == 4) {
                return "邮箱";
            } else {
                return "出错!";
            }
        }

        public void addTableModelListener(TableModelListener l) {
        }

        public void removeTableModelListener(TableModelListener l) {
        }
    }
}


