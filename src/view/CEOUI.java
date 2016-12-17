package view;

import entity.Director;
import entity.Staff;
import entity.Teacher;
import manager.impl.DirectorManagerImpl;
import manager.impl.StaffManagerImpl;
import manager.impl.TeacherManagerImpl;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


/**
 * Created by Jindiwei on 2016/12/16.
 */
public class CEOUI extends JFrame {

    public CEOUI() {
        int width = 600;
        int height = 460;
        setTitle("CEO");
        setSize(new Dimension(width, height));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int w = (Toolkit.getDefaultToolkit().getScreenSize().width - width) / 2;
        int h = (Toolkit.getDefaultToolkit().getScreenSize().height - height) / 2;
        setLocation(w, h);
        setVisible(true);
        CEOPanel ceoPanel = new CEOPanel();
        add(ceoPanel);
    }

    class CEOPanel extends JPanel {
        JLabel choose;
        JComboBox items;
        JButton confirm;
        JPanel nouse;
        JLabel j1;
        JLabel j2;
        JLabel j3;
        ResultPanel resultPanel;

        CEOPanel() {
            choose = new JLabel("    Choose one: ");
            String[] choices = {"Directors' information", "Teachers' information", "Staffs' information", "Courses' information"};
            items = new JComboBox(choices);
            confirm = new JButton("OK");
            nouse = new JPanel();
            j1 = new JLabel();
            j2 = new JLabel();
            j3 = new JLabel();
            resultPanel = new ResultPanel();
            GridBagLayout layout = new GridBagLayout();
            this.setLayout(layout);
            this.add(choose);
            this.add(items);
            this.add(confirm);
            this.add(nouse);
            this.add(j1);
            this.add(j2);
            this.add(j3);
            this.add(resultPanel);
            GridBagConstraints s = new GridBagConstraints();
            s.fill = GridBagConstraints.BOTH;
            s.gridwidth = 1;
            s.weightx = 0;
            s.weighty = 0;
            layout.setConstraints(choose, s);
            s.gridwidth = 1;
            s.weightx = 0;
            s.weighty = 0;
            layout.setConstraints(items, s);
            s.gridwidth = 1;
            s.weightx = 0;
            s.weighty = 0;
            layout.setConstraints(confirm, s);
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

            confirm.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    int selectedIndex = items.getSelectedIndex();
                    resultPanel.change(selectedIndex);
                }

            });
        }
    }

    class ResultPanel extends JPanel {
        JTable table;
        StaffTableModel stm;
        TeacherTableModel ttm;
        DirectorTableModel dtm;
        StaffCourseTableModel sctm;
        JScrollPane scrollPane;


        ResultPanel() {
            table = new JTable();
            scrollPane = new JScrollPane(table);
            add(scrollPane);
        }

        public void change(int index) {
            System.out.println(index);
            switch (index) {
                case 0: {
                    DirectorManagerImpl directorManager = new DirectorManagerImpl();
                    ArrayList<Director> directors = directorManager.queryDirectors();
                    dtm = new DirectorTableModel(directors);
                    table.setModel(dtm);
                    break;
                }
                case 1: {
                    TeacherManagerImpl teacherManager = new TeacherManagerImpl();
                    ArrayList<Teacher> teachers = teacherManager.queryTeacher();
                    ttm = new TeacherTableModel(teachers);
                    table.setModel(ttm);
                    break;
                }
                case 2: {
                    StaffManagerImpl staffManager = new StaffManagerImpl();
                    ArrayList<Staff> staffs = staffManager.queryStaffs();
                    stm = new StaffTableModel(staffs);
                    table.setModel(stm);
                    break;
                }
                case 3: {
                    StaffManagerImpl staffManager = new StaffManagerImpl();
                    ArrayList<String[]> courses = staffManager.queryStaffsCourses();
                    sctm = new StaffCourseTableModel(courses);
                    table.setModel(sctm);
                    break;
                }
            }
        }
    }

    public static void main(String args[]) {
        new CEOUI().setVisible(true);
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

    class TeacherTableModel implements TableModel {
        private ArrayList<Teacher> teachers_list;

        public TeacherTableModel(ArrayList<Teacher> list) {
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


    class DirectorTableModel implements TableModel {
        private ArrayList<Director> director_list;

        public DirectorTableModel(ArrayList<Director> list) {
            this.director_list = list;
        }

        public int getRowCount() {
            return director_list.size();
        }

        public int getColumnCount() {
            return 6;
        }

        public Class<?> getColumnClass(int columnIndex) {
            return String.class;
        }

        public Object getValueAt(int rowIndex, int columnIndex) {
            Director user = director_list.get(rowIndex);
            if (columnIndex == 0) {
                return "" + user.getNumber();
            } else if (columnIndex == 1) {
                return user.getDepartmentName();
            } else if (columnIndex == 2) {
                return "" + user.getName();
            } else if (columnIndex == 3) {
                return "" + user.getGender();
            } else if (columnIndex == 4) {
                return "" + user.getWorkspace();
            } else if (columnIndex == 5) {
                return "" + user.getPhoneNumber();
            } else if (columnIndex == 6) {
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
                return "部 门";
            } else if (columnIndex == 2) {
                return "姓 名";
            } else if (columnIndex == 3) {
                return "性 别";
            } else if (columnIndex == 4) {
                return "工作地点";
            } else if (columnIndex == 5) {
                return "联系方式";
            } else if (columnIndex == 6) {
                return "邮件";
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
        private ArrayList<String[]> course_list;

        public StaffCourseTableModel(ArrayList<String[]> list) {
            this.course_list = list;
        }

        public int getRowCount() {
            return course_list.size();
        }

        public int getColumnCount() {
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
}

