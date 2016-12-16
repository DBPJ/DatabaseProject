package view;

import entity.StaffTakeCourseRecord;
import entity.Teacher;
import manager.impl.UserManagerImpl;
import org.apache.xmlbeans.impl.jam.internal.JamPrinter;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

/**
 * Created by alex on 16/12/2016.
 */
public class TeacherUI extends JFrame{
    TeacherPanel teacherPanel;
    public TeacherUI(){
        teacherPanel = new TeacherPanel();
        add(teacherPanel);
    }

    public static void main(String[] args) {
        TeacherUI teacherUI = new TeacherUI();
        teacherUI.setTitle("Teacher");
        teacherUI.setSize(new Dimension(800,600));
        teacherUI.setVisible(true);
        teacherUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}

class TeacherPanel extends JPanel {
    JPanel leftPanel;
    JPanel rightPanel;


    public TeacherPanel(){
        setLayout(new GridLayout(1,2));
        leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(4,1));
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

        CourseStaffModel model = new CourseStaffModel();
        JTable jTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(jTable);
        rightPanel.add(courseLabel);
        rightPanel.add(courseField);
        rightPanel.add(staffLabel);
        rightPanel.add(staffField);
        rightPanel.add(submit);
        rightPanel.add(scrollPane);

        GridBagConstraints s= new GridBagConstraints();
        s.fill = GridBagConstraints.HORIZONTAL;
        s.gridwidth = 1;
        s.weightx = 0;
        s.weighty = 0;
        layout.setConstraints(courseLabel,s);
        s.fill = GridBagConstraints.HORIZONTAL;
        s.gridwidth = 1;
        s.weightx = 1;
        s.weighty = 0;
        layout.setConstraints(courseField,s);
        s.gridwidth = 1;
        s.weightx = 0;
        s.weighty = 0;
        layout.setConstraints(staffLabel,s);
        s.fill = GridBagConstraints.HORIZONTAL;
        s.gridwidth = 1;
        s.weightx = 1;
        s.weighty = 0;
        layout.setConstraints(staffField,s);
        s.gridwidth = 0;
        s.weightx = 0;
        s.weighty = 0;
        layout.setConstraints(submit,s);

        s.fill = GridBagConstraints.BOTH;
        s.gridwidth = 0;
        s.weightx = 1;
        s.weighty = 1;
        layout.setConstraints(scrollPane,s);


        add(rightPanel);

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

    public UpdateGradePanel(){
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


        setLayout(new GridLayout(2,4));
        setBorder(BorderFactory.createTitledBorder("Update Grade"));
        add(courseLabel);
        add(courseField);
        add(staffLabel);
        add(staffField);
        add(gradeLabel);
        add(pass);
        add(fail);
        add(submit);
    }
}

class BatchAddCoursePanel extends JPanel{
    JLabel filenameLabel;
    JTextField filenameField;
    JButton cancel;
    JButton submit;

//    UserManagerImpl userManager = new UserManagerImpl();

    BatchAddCoursePanel(){
        setLayout(new GridLayout(1,4));
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
//                userManager.addUsers(filename);
            }
        });
    }
}
class DeleteCoursePanel extends JPanel{
    JLabel courseLabel;
    JTextField courseField;
    JButton cancel;
    JButton submit;

//    UserManagerImpl userManager = new UserManagerImpl();

    DeleteCoursePanel(){
        setLayout(new GridLayout(1,4));
        courseLabel = new JLabel("Number:");
        add(courseLabel);
        courseField = new JTextField();
        add(courseField );
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
//                userManager.deleteUser(number);
            }
        });
    }
}

class CourseStaffModel extends AbstractTableModel{

    String[] columns = new String[] {
            "Course Id", "Staff Number", "Grade","Status", "Operation"
    };

    List<StaffTakeCourseRecord> records;

    public CourseStaffModel(){
        records=new ArrayList<>();
    }

    public CourseStaffModel(List<StaffTakeCourseRecord> records){
        this.records = records;
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
        Object value=null;
        switch (columnIndex){
            case 0:value = records.get(rowIndex).getCourseID();break;
            case 1:value = records.get(rowIndex).getStaffNumber();break;
            case 2:value = records.get(rowIndex).getGrade();break;
            case 3:value = records.get(rowIndex).getStatus();break;
            case 4:if (records.get(rowIndex).getStatus().equals("applying")){
                value = new JButton("Accept");
            }else{
                value ="";
            }
        }
        return value;
    }

    public List<StaffTakeCourseRecord> getRecords() {
        return records;
    }

    public void setRecords(List<StaffTakeCourseRecord> records) {
        this.records = records;
    }
}