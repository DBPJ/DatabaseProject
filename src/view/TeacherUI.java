package view;

import entity.StaffTakeCourseRecord;
import entity.Teacher;

import manager.impl.CourseManagerImpl;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;


/**
 * Created by alex on 16/12/2016.
 */
public class TeacherUI extends JFrame {
    TeacherPanel teacherPanel;
    Teacher teacher;
    CourseManagerImpl courseManager;

    public TeacherUI(Teacher teacher) {
        this.teacher = teacher;
        courseManager = new CourseManagerImpl();
        teacherPanel = new TeacherPanel();
        setTitle("Teacher");
        setSize(new Dimension(800, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int w = (Toolkit.getDefaultToolkit().getScreenSize().width - 800) / 2;
        int h = (Toolkit.getDefaultToolkit().getScreenSize().height - 600) / 2;
        setLocation(w, h);
        setVisible(true);
        add(teacherPanel);
    }

    public static void main(String[] args) {
        Teacher teacher = new Teacher();
        teacher.setNumber("TR01001");
        teacher.setName("李敏");
        teacher.setGender("male");
        teacher.setPhoneNumber((long) 12345);
        teacher.setEmail("haha@x.com");
        TeacherUI teacherUI = new TeacherUI(teacher);
        teacherUI.setTitle("Teacher");
        teacherUI.setSize(new Dimension(800, 600));
        teacherUI.setVisible(true);
        teacherUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        teacherUI.teacherPanel.addRecord();
    }


    class TeacherPanel extends JPanel {
        JPanel leftPanel;
        JPanel rightPanel;
        JTable jTable;
        CourseStaffModel model;

        public TeacherPanel() {
            setLayout(new GridLayout(1, 2));
            leftPanel = new JPanel();
            leftPanel.setLayout(new GridLayout(4, 1));
            leftPanel.add(new AddCoursePanel());
            leftPanel.add(new UpdateGradePanel());
            leftPanel.add(new BatchAddCoursePanel());
            leftPanel.add(new DeleteCoursePanel());
            add(leftPanel);

            rightPanel = new JPanel();
            GridBagLayout layout = new GridBagLayout();
            rightPanel.setLayout(layout);
            JLabel courseLabel = new JLabel("Course ID:");
            JTextField courseField = new JTextField();
            JLabel staffLabel = new JLabel("Staff");
            JTextField staffField = new JTextField();
            JButton submit = new JButton("Submit");

            model = new CourseStaffModel();
            jTable = new JTable(model);
            TableCellRenderer buttonRenderer = new JTableButtonRenderer();

            jTable.getColumn("Operation").setCellRenderer(buttonRenderer);
            JScrollPane scrollPane = new JScrollPane(jTable);
            rightPanel.add(courseLabel);
            rightPanel.add(courseField);
            rightPanel.add(staffLabel);
            rightPanel.add(staffField);
            rightPanel.add(submit);
            rightPanel.add(scrollPane);

            GridBagConstraints s = new GridBagConstraints();
            s.fill = GridBagConstraints.HORIZONTAL;
            s.gridwidth = 1;
            s.weightx = 0;
            s.weighty = 0;
            layout.setConstraints(courseLabel, s);
            s.fill = GridBagConstraints.HORIZONTAL;
            s.gridwidth = 1;
            s.weightx = 1;
            s.weighty = 0;
            layout.setConstraints(courseField, s);
            s.gridwidth = 1;
            s.weightx = 0;
            s.weighty = 0;
            layout.setConstraints(staffLabel, s);
            s.fill = GridBagConstraints.HORIZONTAL;
            s.gridwidth = 1;
            s.weightx = 1;
            s.weighty = 0;
            layout.setConstraints(staffField, s);
            s.gridwidth = 0;
            s.weightx = 0;
            s.weighty = 0;
            layout.setConstraints(submit, s);

            s.fill = GridBagConstraints.BOTH;
            s.gridwidth = 0;
            s.weightx = 1;
            s.weighty = 1;
            layout.setConstraints(scrollPane, s);


            add(rightPanel);

        }

        private void addRecord() {
            for (int i = 0; i < 20; i++) {
                StaffTakeCourseRecord stcr = new StaffTakeCourseRecord();
                stcr.setCourseID("id_" + i);
                stcr.setStaffNumber("staff_" + i);
                if (i % 5 == 0) {
                    stcr.setGrade("pass");
                } else {
                    stcr.setGrade("fail");
                }
                if (i % 2 == 0) {
                    stcr.setStatus("applying");
                } else {
                    stcr.setStatus("test");
                }
                model.addRecord(stcr);
            }
        }


        private class JTableButtonRenderer implements TableCellRenderer {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JButton button = (JButton) value;
                return button;
            }
        }

        class AddCoursePanel extends JPanel {
            JLabel courseIDLabel;
            JTextField courseField;
            JLabel nameLabel;
            JTextField nameField;
            JLabel classHourLabel;
            JTextField classHourField;
            JButton cancel;
            JButton submit;

            public AddCoursePanel() {
                setLayout(new GridLayout(2, 4));
                courseIDLabel = new JLabel("course ID:");
                add(courseIDLabel);
                courseField = new JTextField();
                add(courseField);
                nameLabel = new JLabel("Name");
                add(nameLabel);
                nameField = new JTextField();
                add(nameField);

                classHourLabel = new JLabel("Class Hour");
                add(classHourLabel);
                classHourField = new JTextField();
                add(classHourField);
                cancel = new JButton("Cancel");
                add(cancel);
                submit = new JButton("Submit");
                add(submit);

                setBorder(BorderFactory.createTitledBorder("Add Course"));

                submit.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        String course = courseField.getText();
                        String name = nameField.getText();
                        String classHour = classHourField.getText();
                        courseManager.addCourse(course, name, Integer.parseInt(classHour), teacher.getNumber());
                    }
                });
            }
        }


        class UpdateGradePanel extends JPanel {
            JLabel courseLabel;
            JTextField courseField;
            JLabel staffLabel;
            JTextField staffField;
            JLabel gradeLabel;
            ButtonGroup gradeGroup;
            JRadioButton pass;
            JRadioButton fail;
            JButton cancel;
            JButton submit;

            public UpdateGradePanel() {
                courseLabel = new JLabel("Course ID:");
                courseField = new JTextField();
                staffLabel = new JLabel("Staff number:");
                staffField = new JTextField();
                gradeLabel = new JLabel("Grade:");
                pass = new JRadioButton("Pass");
                fail = new JRadioButton("Fail");
                gradeGroup = new ButtonGroup();
                gradeGroup.add(pass);
                gradeGroup.add(fail);
                cancel = new JButton("Cancel:");
                submit = new JButton("Submit:");


                setLayout(new GridLayout(2, 4));
                setBorder(BorderFactory.createTitledBorder("Update Grade"));
                add(courseLabel);
                add(courseField);
                add(staffLabel);
                add(staffField);
                add(gradeLabel);
                add(pass);
                add(fail);
                add(submit);

                submit.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        String course = courseField.getText();
                        String staff = staffField.getText();
                        String grade = null;
                        if (pass.isSelected()) {
                            grade = "pass";
                        } else if (fail.isSelected()) {
                            grade = "fail";
                        } else {
                            //todo reminder
                            System.out.println("grade is not select");
                        }

                        courseManager.updateGrade(teacher.getNumber(), course, staff, grade);
                    }
                });
            }
        }

        class BatchAddCoursePanel extends JPanel {
            JLabel filenameLabel;
            JTextField filenameField;
            JButton cancel;
            JButton submit;

            CourseManagerImpl courseManager = new CourseManagerImpl();

            BatchAddCoursePanel() {
                setLayout(new GridLayout(1, 4));
                filenameLabel = new JLabel("File path:");
                add(filenameLabel);
                filenameField = new JTextField();
                add(filenameField);
                cancel = new JButton("Cancel");
                add(cancel);
                submit = new JButton("submit");
                add(submit);

                setBorder(BorderFactory.createTitledBorder("Batch Add Course"));

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
                        courseManager.addCourses(teacher, filename);
                    }
                });
            }
        }

        class DeleteCoursePanel extends JPanel {
            JLabel courseLabel;
            JTextField courseField;
            JButton cancel;
            JButton submit;

            CourseManagerImpl courseManager = new CourseManagerImpl();

            DeleteCoursePanel() {
                setLayout(new GridLayout(1, 4));
                courseLabel = new JLabel("Number:");
                add(courseLabel);
                courseField = new JTextField();
                add(courseField);
                cancel = new JButton("Cancel");
                add(cancel);
                submit = new JButton("submit");
                add(submit);

                setBorder(BorderFactory.createTitledBorder("Delete"));

                cancel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        courseField.setText("");
                    }
                });

                submit.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        String number = courseField.getText();
                        if (number.equals("")) {
                            //todo 提醒为空
                        } else {
                            courseManager.deleteCourse(number);
                        }
                    }
                });
            }
        }

        class CourseStaffModel extends AbstractTableModel {

            String[] columns = new String[]{
                    "Course Id", "Staff Number", "Grade", "Status", "Operation"
            };

            List<StaffTakeCourseRecord> records;

            public CourseStaffModel() {
                records = new ArrayList<>();
            }


            public CourseStaffModel(List<StaffTakeCourseRecord> records) {
                this.records = records;
            }


            //设置每一列的名字
            @Override
            public String getColumnName(int column) {
                return columns[column];
            }


            @Override
            public int getRowCount() {
                return records.size();
            }

            @Override
            public int getColumnCount() {
                return columns.length;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                Object value = null;
                switch (columnIndex) {
                    case 0:
                        value = records.get(rowIndex).getCourseID();
                        break;
                    case 1:
                        value = records.get(rowIndex).getStaffNumber();
                        break;
                    case 2:
                        value = records.get(rowIndex).getGrade();
                        break;
                    case 3:
                        value = records.get(rowIndex).getStatus();
                        break;
                    case 4:
                        JButton button = new JButton();
                        if (records.get(rowIndex).getStatus().equals("applying")) {
                            button.setText("Accept");
                            button.addMouseListener(new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    super.mouseClicked(e);
                                    //todo: add listener to accept staff application
                                }
                            });
                        }
                        value = button;
                }
                return value;
            }

            public List<StaffTakeCourseRecord> getRecords() {
                return records;
            }

            public void setRecords(List<StaffTakeCourseRecord> records) {
                this.records = records;
            }

            public void addRecord(StaffTakeCourseRecord record) {
                this.records.add(record);
            }
        }
    }
}


