package view;

import entity.Course;
import entity.Staff;
import entity.StaffTakeCourseRecord;
import entity.TrainPlan;
import manager.impl.CourseManagerImpl;
import manager.impl.StaffManagerImpl;
import manager.impl.TrainingPlanManagerImpl;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by alex on 16/12/2016.
 */
public class StaffUI extends JFrame {
    StaffPanel staffPanel;
    Staff staff;

    public StaffUI() {
        staff = new Staff();
        staff.setNumber("CH02001");
        staff.setDepartmentName("策划");
        int width = 800;
        int height = 600;
        setTitle("Staff");
        setSize(new Dimension(width, height));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int w = (Toolkit.getDefaultToolkit().getScreenSize().width - width) / 2;
        int h = (Toolkit.getDefaultToolkit().getScreenSize().height - height) / 2;
        setLocation(w, h);
        setVisible(true);
        staffPanel = new StaffPanel();
        add(staffPanel);
    }

    public StaffUI(Staff staff) {
        this.staff = staff;
        int width = 800;
        int height = 600;
        setTitle("Staff");
        setSize(new Dimension(width, height));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int w = (Toolkit.getDefaultToolkit().getScreenSize().width - width) / 2;
        int h = (Toolkit.getDefaultToolkit().getScreenSize().height - height) / 2;
        setLocation(w, h);
        setVisible(true);
        staffPanel = new StaffPanel();
        add(staffPanel);
    }

    public static void main(String[] args) {
        new StaffUI().setVisible(true);
    }

    class StaffPanel extends JPanel {
        SelectPanel selectPanel;
        CoursePanel coursePanel;
        TrainingPlanPanel trainingPlanPanel;
        GradePanel gradePanel;

        JPanel leftPanel;
        JPanel rightPanel;

        StaffPanel() {
            selectPanel = new SelectPanel();
            coursePanel = new CoursePanel();
            trainingPlanPanel = new TrainingPlanPanel();
            gradePanel = new GradePanel();

            leftPanel = new JPanel();
            rightPanel = new JPanel();

            GridBagLayout leftLayout = new GridBagLayout();
            leftPanel.setLayout(leftLayout);
            GridBagConstraints ls = new GridBagConstraints();
            leftPanel.add(selectPanel);
            leftPanel.add(coursePanel);
            ls.fill = GridBagConstraints.NONE;
            ls.gridwidth = 0;
            ls.weightx = 0;
            ls.weighty = 0;
            leftLayout.setConstraints(selectPanel, ls);
            ls.fill = GridBagConstraints.BOTH;
            ls.gridwidth = 0;
            ls.weightx = 0;
            ls.weighty = 1;
            leftLayout.setConstraints(coursePanel, ls);

            GridBagLayout rightLayout = new GridBagLayout();
            rightPanel.setLayout(rightLayout);
            GridBagConstraints rs = new GridBagConstraints();
            rightPanel.add(trainingPlanPanel);
            rightPanel.add(gradePanel);
            rs.fill = GridBagConstraints.BOTH;
            rs.gridwidth = 0;
            rs.weightx = 1;
            rs.weighty = 1;
            rightLayout.setConstraints(trainingPlanPanel, rs);
            rs.fill = GridBagConstraints.BOTH;
            rs.gridwidth = 0;
            rs.weightx = 1;
            rs.weighty = 1;
            rightLayout.setConstraints(gradePanel, rs);


            setLayout(new GridLayout(1, 2));
            add(leftPanel);
            add(rightPanel);

        }
    }

    class SelectPanel extends JPanel {
        JLabel courseLabel;
        JTextField courseField;
        JButton cancel;
        JButton submit;
        CourseManagerImpl courseManager = new CourseManagerImpl();

        SelectPanel() {
            courseLabel = new JLabel("Course number:");
            courseField = new JTextField();
            cancel = new JButton("Cancel");
            submit = new JButton("Submit");

            setLayout(new GridLayout(1, 4));
            setBorder(BorderFactory.createTitledBorder("Select Course"));

            add(courseLabel);
            add(courseField);
            add(cancel);
            add(submit);

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
//                    todo: add mouse listener
                    String courseID = courseField.getText();
                    courseManager.selectCourse(courseID,staff.getNumber());
                }
            });
        }
    }

    class CoursePanel extends JPanel {
        JButton hide;
        JButton show;
        JTable courseTable;
        CourseModel courseModel;
        JScrollPane scrollPane;

        CourseManagerImpl courseManager;


        CoursePanel() {
            courseManager = new CourseManagerImpl();

            hide = new JButton("Hide");
            show = new JButton("Show");
            courseModel = new CourseModel();
            courseTable = new JTable(courseModel);
            scrollPane = new JScrollPane(courseTable);
            add(hide);
            add(show);
            add(scrollPane);
            GridBagLayout layout = new GridBagLayout();
            setLayout(layout);

            setBorder(BorderFactory.createTitledBorder("Course"));
            GridBagConstraints s = new GridBagConstraints();
            s.fill = GridBagConstraints.HORIZONTAL;
            s.gridwidth = 1;
            s.weightx = 1;
            s.weighty = 0;
            layout.setConstraints(hide, s);
            s.fill = GridBagConstraints.HORIZONTAL;
            s.gridwidth = 0;
            s.weightx = 1;
            s.weighty = 0;
            layout.setConstraints(show, s);

            s.fill = GridBagConstraints.BOTH;
            s.gridwidth = 0;
            s.weightx = 1;
            s.weighty = 1;
            layout.setConstraints(scrollPane, s);

            hide.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    courseModel.setCourses(new ArrayList<>());
                }
            });

            show.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    List<Course> courses = courseManager.queryCourses();
                    courseModel.setCourses(courses);
                    courseModel.fireTableDataChanged();
                }
            });

        }
    }

    class TrainingPlanPanel extends JPanel {
        JButton hide;
        JButton show;
        JTable trainPlanTable;
        TrainingPlanModel trainingPlanModel;
        JScrollPane scrollPane;
        TrainingPlanManagerImpl trainingPlanManager = new TrainingPlanManagerImpl();


        TrainingPlanPanel() {
            hide = new JButton("Hide");
            show = new JButton("Show");
            trainingPlanModel = new TrainingPlanModel();
            trainPlanTable = new JTable(trainingPlanModel);
            scrollPane = new JScrollPane(trainPlanTable);
            add(hide);
            add(show);
            add(scrollPane);
            GridBagLayout layout = new GridBagLayout();
            setLayout(layout);
            setBorder(BorderFactory.createTitledBorder("Training Plan"));

            GridBagConstraints s = new GridBagConstraints();
            s.fill = GridBagConstraints.HORIZONTAL;
            s.gridwidth = 1;
            s.weightx = 1;
            s.weighty = 0;
            layout.setConstraints(hide, s);
            s.fill = GridBagConstraints.HORIZONTAL;
            s.gridwidth = 0;
            s.weightx = 1;
            s.weighty = 0;
            layout.setConstraints(show, s);

            s.fill = GridBagConstraints.BOTH;
            s.gridwidth = 0;
            s.weightx = 1;
            s.weighty = 1;
            layout.setConstraints(scrollPane, s);

            hide.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    trainingPlanModel.setTrainPlans(new ArrayList<>());
                }
            });

            show.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    List<TrainPlan> trainPlans = trainingPlanManager.queryTrainPlans(staff);
                    trainingPlanModel.setTrainPlans(trainPlans);
                    trainingPlanModel.fireTableDataChanged();
                }
            });

        }
    }

    class GradePanel extends JPanel {
        JButton hide;
        JButton show;
        JTable recordTable;
        GradeRecordModel recordModel;
        JScrollPane scrollPane;

        StaffManagerImpl staffManager = new StaffManagerImpl();

        GradePanel() {
            hide = new JButton("Hide");
            show = new JButton("Show");
            recordModel = new GradeRecordModel();
            recordTable = new JTable(recordModel);
            TableCellRenderer buttonRenderer = new JTableButtonRenderer();
            recordTable.getColumn("Apply").setCellRenderer(buttonRenderer);
            scrollPane = new JScrollPane(recordTable);

            add(hide);
            add(show);
            add(scrollPane);
            GridBagLayout layout = new GridBagLayout();
            setLayout(layout);
            setBorder(BorderFactory.createTitledBorder("Grade"));

            GridBagConstraints s = new GridBagConstraints();
            s.fill = GridBagConstraints.HORIZONTAL;
            s.gridwidth = 1;
            s.weightx = 1;
            s.weighty = 0;
            layout.setConstraints(hide, s);
            s.fill = GridBagConstraints.HORIZONTAL;
            s.gridwidth = 0;
            s.weightx = 1;
            s.weighty = 0;
            layout.setConstraints(show, s);

            s.fill = GridBagConstraints.BOTH;
            s.gridwidth = 0;
            s.weightx = 1;
            s.weighty = 1;
            layout.setConstraints(scrollPane, s);


            hide.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    recordModel.setRecords(new ArrayList<>());
                    recordModel.fireTableDataChanged();
                }
            });
            show.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    List<StaffTakeCourseRecord> records = staffManager.queryCourseRecords(staff.getNumber());
                    recordModel.setRecords(records);
                    recordModel.fireTableDataChanged();
                }
            });
        }

        private class JTableButtonRenderer implements TableCellRenderer {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JButton button = (JButton) value;
                if (hasFocus) {
                    System.out.println("has focus");
                    if (button.getText().equals("Apply")) {
                        System.out.println(" apply button");
                        StaffTakeCourseRecord record = recordModel.getRecord(row);
                        staffManager.applyResit(staff.getNumber(), record.getCourseID());
                        button.setText("Applied");
                        List<StaffTakeCourseRecord> records = staffManager.queryCourseRecords(staff.getNumber());
                        recordModel.setRecords(records);

                    }
                }
                return button;
            }
        }
    }

    class CourseModel extends AbstractTableModel {

        String[] columns = new String[]{
                "Course Id", "Name", "ClassHour"
        };

        List<Course> courses;

        CourseModel() {
            courses = new ArrayList<>();
        }

        CourseModel(List<Course> courses) {
            this.courses = courses;
        }


        @Override
        public int getRowCount() {
            return courses.size();
        }

        @Override
        public int getColumnCount() {
            return columns.length;
        }

        @Override
        public String getColumnName(int columnIndex) {
            return columns[columnIndex];
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            switch (columnIndex) {
                case 0:
                    return courses.get(rowIndex).getId();
                case 1:
                    return courses.get(rowIndex).getName();
                case 2:
                    return courses.get(rowIndex).getClassHour();
                default:
                    return null;
            }
        }


        public void addCourse(Course course) {
            this.courses.add(course);
        }

        public void setCourses(List<Course> courses) {
            this.courses = courses;
        }
    }

    class TrainingPlanModel extends AbstractTableModel {
        List<TrainPlan> trainPlans;
        String[] columns = new String[]{
                "Course Id", "Name", "type"
        };

        TrainingPlanModel() {
            trainPlans = new ArrayList<>();
        }

        TrainingPlanModel(List<TrainPlan> trainPlans) {
            this.trainPlans = trainPlans;
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
            switch (columnIndex) {
                case 0:
                    return trainPlans.get(rowIndex).getCourseID();
                case 1:
                    return trainPlans.get(rowIndex).getCourseName();
                case 2:
                    return trainPlans.get(rowIndex).getType();
                default:
                    return null;
            }
        }

        public void setTrainPlans(List<TrainPlan> trainPlans) {
            this.trainPlans = trainPlans;
        }
    }

    class GradeRecordModel extends AbstractTableModel {
        List<StaffTakeCourseRecord> records;
        String[] columns = new String[]{
                "Course Id", "Course Name", "Grade", "Apply"
        };

        GradeRecordModel() {
            records = new ArrayList<>();
        }

        GradeRecordModel(List<StaffTakeCourseRecord> records) {
            this.records = records;
        }


        @Override
        public String getColumnName(int columnIndex) {
            return columns[columnIndex];
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
            switch (columnIndex) {
                case 0:
                    return records.get(rowIndex).getCourseID();
                case 1:
                    return records.get(rowIndex).getCourseName();
                case 2:
                    return records.get(rowIndex).getGrade();
                case 3:
                    JButton button = new JButton();
                    if ("need".equals(records.get(rowIndex).getResit())) {
                        button.setText("Apply");
                    } else if ("applying".equals(records.get(rowIndex).getResit())) {
                        button.setText("Applied");
                    } else if ("accept".equals(records.get(rowIndex).getResit())) {
                        button.setText("Accept");
                    } else {
                        button.setText("");
                    }
                    return button;
                default:
                    return null;
            }
        }

        public void setRecords(List<StaffTakeCourseRecord> records) {
            this.records = records;
        }

        public StaffTakeCourseRecord getRecord(int index) {
            if (index >= records.size()) {
                return null;
            }
            return records.get(index);
        }
    }
}
