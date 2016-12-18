package view;

import entity.*;
import manager.impl.StaffManagerImpl;
import manager.impl.TrainingPlanManagerImpl;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Jindiwei on 2016/12/15.
 */
public class DirectorUI extends JFrame {
    Director director;

    public DirectorUI(Director director, boolean hasTrainPlan) {
        this.director = director;
        int width = 1100;
        int height = 500;
        setTitle("Director");
        setSize(new Dimension(width, height));
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int w = (Toolkit.getDefaultToolkit().getScreenSize().width - width) / 2;
        int h = (Toolkit.getDefaultToolkit().getScreenSize().height - height) / 2;
        setLocation(w, h);
        String logininfo = "ID: " + director.getNumber() + " Name: " + director.getName() + " 部门: " + director.getDepartmentName();
        DirectorPanel directorPanel = new DirectorPanel(logininfo, hasTrainPlan);
        add(directorPanel);
    }


    public static void main(String args[]) {
//        new DirectorUI().setVisible(true);
    }


    /**
     * the panel contain all of other panels
     */


    class DirectorPanel extends JPanel {
        DirectorPanel(String info, boolean hasTrainPlan) {
            LeftPanel leftPanel = new LeftPanel(info);
            RightPanel rightPanel = new RightPanel(hasTrainPlan);
            setLayout(new GridLayout(1, 2));
            add(leftPanel);
            add(rightPanel);
        }


    }

    /**
     * this panel is on the left
     */
    class LeftPanel extends JPanel {

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
        JButton save;
        boolean hasTrainPlan;

        RightPanel(boolean hasplan) {
            this.hasTrainPlan = hasplan;

            choose = new JLabel("Choose : ");
            String[] choices = {"Courses Staffs Choosed", "Courses Information"};
            choice = new JComboBox(choices);
            commit = new JButton("Commit");
            save = new JButton("Save");
            save.setVisible(false);
            j1 = new JLabel();
            j2 = new JLabel();
            j3 = new JLabel();
            resultPanel = new ResultPanel(hasplan);
            resultPanel.setBackground(Color.PINK);//为了看出效果，设置了颜色

            GridBagLayout layout = new GridBagLayout();
            this.setLayout(layout);
            this.add(choose);//把组件添加进jframe
            this.add(choice);
            this.add(commit);
            this.add(save);
            this.add(j1);
            this.add(j2);
            this.add(j3);
            this.add(resultPanel);
            GridBagConstraints s = new GridBagConstraints();
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
            s.gridwidth = 1;
            s.weightx = 0;
            s.weighty = 0;
            layout.setConstraints(save, s);
            s.gridwidth = 1;
            s.weightx = 0;
            s.weighty = 0;
            layout.setConstraints(j1, s);
            s.gridwidth = 1;
            s.weightx = 1;
            s.weighty = 0;
            layout.setConstraints(j2, s);
            s.gridwidth = 0;
            s.weightx = 0;
            s.weighty = 0;
            layout.setConstraints(j3, s);
            s.gridwidth = 9;
            s.weightx = 1;
            s.weighty = 1;
            s.fill = GridBagConstraints.BOTH;
            layout.setConstraints(resultPanel, s);

            commit.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    int selectedIndex = choice.getSelectedIndex();
                    if (hasTrainPlan || selectedIndex == 0) {
                        save.setVisible(false);
                    } else {
                        save.setVisible(true);
                    }
                    resultPanel.change(selectedIndex);
                }

            });

            save.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    ShowCoursesTableModel stm = (ShowCoursesTableModel) resultPanel.table.getModel();
                    List<TrainPlan> lists = stm.getTrainPlans();
                    TrainingPlanManagerImpl trainingPlanManager = new TrainingPlanManagerImpl();
                    for (int i = 0; i < lists.size(); i++) {
                        TrainPlan trainPlan = lists.get(i);
                        if (!"other".equals(trainPlan.getType())) { //只保存必修或者选修
                            trainPlan.setDepartmentName(director.getDepartmentName());
                            trainingPlanManager.addTrainingPlan(trainPlan);
                        }
                    }
                    hasTrainPlan = true;
                    stm.setEditable(false);
                    save.setVisible(false);
                    resultPanel.setHasTrainPlan(true);
                }

            });
        }
    }

    class ResultPanel extends JPanel {
        JTable table;
        StaffCourseTableModel sctm;
        ShowCoursesTableModel ctm;
        JScrollPane scrollPane;

        List<TableCellEditor> editors = new ArrayList<TableCellEditor>(1);

        TrainingPlanManagerImpl trainingPlanManager = new TrainingPlanManagerImpl();
        boolean hasTrainPlan;

        ResultPanel(boolean hasTrainPlan) {
            this.hasTrainPlan = hasTrainPlan;
            table = new JTable();
            scrollPane = new JScrollPane(table);
            add(scrollPane);
            ctm = new ShowCoursesTableModel(hasTrainPlan);
        }

        public void change(int index) {
            switch (index) {
                case 0: {
                    StaffManagerImpl staffManager = new StaffManagerImpl();
                    ArrayList<String[]> courses = staffManager.getCourseTakenInfo(director);
                    sctm = new StaffCourseTableModel(courses);
                    table.setModel(sctm);
                    break;
                }
                case 1: {
                    String[] list = {"required", "elective", "other"};
                    JComboBox<String> comboBox1 = new JComboBox<>(list);
                    DefaultCellEditor dce1 = new DefaultCellEditor(comboBox1);
                    editors.add(dce1);
                    List<TrainPlan> trainPlans = new ArrayList<>();
                    if (hasTrainPlan) {
                        trainPlans = trainingPlanManager.queryTrainPlans(director);
                    } else {
                        trainPlans = trainingPlanManager.queryFreePlans();
                    }
                    ctm.setTrainPlans(trainPlans);
                    table.setModel(ctm);
                    table.getColumn("Type").setCellEditor(new MyComboBoxEditor(list));
                    break;
                }
            }
        }


        public int getCourseRow(ShowCoursesTableModel ctm, String course_id) {
            for (int i = 0; i < ctm.getRowCount(); i++) {
                if (ctm.getValueAt(i, 0).equals(course_id)) {
                    return i;
                }
            }
            return 0;
        }

        public void setHasTrainPlan(boolean hasTrainPlan) {
            this.hasTrainPlan = hasTrainPlan;
        }
    }

    class MyComboBoxEditor extends DefaultCellEditor {
        public MyComboBoxEditor(String[] items) {
            super(new JComboBox(items));
        }
    }

    class ShowCoursesTableModel extends AbstractTableModel {
        private List<TrainPlan> trainPlans;
        String[] columns = new String[]{"ID", "Name", "Class Hour", "Type"};
        boolean editable;

        public ShowCoursesTableModel(boolean hasTrainPlan) {
            trainPlans = new ArrayList<>();
            this.editable = !hasTrainPlan;
        }


        @Override
        public String getColumnName(int columnIndex) {
            return columns[columnIndex];
        }

        @Override
        public int getRowCount() {
            return trainPlans.size();
        }

        @Override
        public int getColumnCount() {
            return columns.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            TrainPlan trainPlan = trainPlans.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return trainPlan.getCourseID();
                case 1:
                    return trainPlan.getCourseName();
                case 2:
                    return trainPlan.getClassHour();
                case 3:
                    return trainPlan.getType();
                default:
                    return null;
            }
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            TrainPlan trainPlan = trainPlans.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    trainPlan.setCourseID((String) aValue);
                    break;
                case 1:
                    trainPlan.setCourseName((String) aValue);
                    break;
                case 2:
                    trainPlan.setClassHour((Integer) aValue);
                    break;
                case 3:
                    trainPlan.setType((String) aValue);
                    break;
            }
        }

        public List<TrainPlan> getTrainPlans() {
            return trainPlans;
        }

        public void setTrainPlans(List<TrainPlan> trainPlans) {
            this.trainPlans = trainPlans;
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            if (editable && columnIndex == 3) {
                return true;
            }
            return false;
        }

        public TrainPlan getTrainPlan(int index){
            return trainPlans.get(index);
        }

        public void setEditable(boolean editable) {
            this.editable = editable;
        }
    }

    class StaffCourseTableModel implements TableModel {
        private List<String[]> course_list;

        public StaffCourseTableModel(ArrayList<String[]> list) {
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

        public List<String[]> getCourses_list() {
            return course_list;
        }
    }

}


