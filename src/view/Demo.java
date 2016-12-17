package view;
import javax.swing.*;
import java.awt.*;

public class Demo extends JFrame {
    public static void main(String args[]) {
        Demo demo = new Demo();
    }

    public Demo() {
        init();
        this.setSize(600,600);
        this.setVisible(true);
    }
    public void init() {
        j1 = new JLabel("Choose one: ");
        String[] choices = {"Directors' information", "Teachers' information", "Staffs' information", "Courses' information"};
        j2 = new JComboBox(choices);
        j3 = new JButton("OK");
        j4 = new JPanel();
        j5 = new JLabel();
        j6 = new JLabel();
        j7 = new JLabel();
        j9 = new JTextArea();
        j9.setBackground(Color.PINK);//为了看出效果，设置了颜色
        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        this.add(j1);//把组件添加进jframe
        this.add(j2);
        this.add(j3);
        this.add(j4);
        this.add(j5);
        this.add(j6);
        this.add(j7);
        this.add(j9);
        GridBagConstraints s= new GridBagConstraints();//定义一个GridBagConstraints，
//是用来控制添加进的组件的显示位置
        s.fill = GridBagConstraints.BOTH;
//该方法是为了设置如果组件所在的区域比组件本身要大时的显示情况
//NONE：不调整组件大小。
//HORIZONTAL：加宽组件，使它在水平方向上填满其显示区域，但是不改变高度。
//VERTICAL：加高组件，使它在垂直方向上填满其显示区域，但是不改变宽度。
//BOTH：使组件完全填满其显示区域。
        s.gridwidth=1;//该方法是设置组件水平所占用的格子数，如果为0，就说明该组件是该行的最后一个
        s.weightx = 0;//该方法设置组件水平的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
        s.weighty=0;//该方法设置组件垂直的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
        layout.setConstraints(j1, s);//设置组件
        s.gridwidth=1;
        s.weightx = 0;
        s.weighty=0;
        layout.setConstraints(j2, s);
        s.gridwidth=1;
        s.weightx = 0;
        s.weighty=0;
        layout.setConstraints(j3, s);
        s.gridwidth=0;
        s.weightx = 0;
        s.weighty=0;
        layout.setConstraints(j4, s);
        s.gridwidth=2;
        s.weightx = 0;
        s.weighty=0;
        layout.setConstraints(j5, s);
        ;s.gridwidth=4;
        s.weightx = 1;
        s.weighty=0;
        layout.setConstraints(j6, s);
        ;s.gridwidth=0;
        s.weightx = 0;
        s.weighty=0;
        layout.setConstraints(j7, s);
        s.gridwidth=7;
        s.weightx = 0;
        s.weighty=1;
        layout.setConstraints(j9, s);
    }
    JLabel j1;
    JComboBox j2;
    JButton j3;
    JPanel j4;
    JLabel j5;
    JLabel j6;
    JLabel j7;
    JList j8;
    JTextArea j9;
}