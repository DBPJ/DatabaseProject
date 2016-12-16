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
        j9 = new JPanel();
        j9.setBackground(Color.PINK);//为了看出效果，设置了颜色

        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        this.add(j1);//把组件添加进jframe
        this.add(j2);
        this.add(j3);
        this.add(j4);
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
        s.gridwidth=2;
        s.weightx = 0;
        s.weighty=0;
        layout.setConstraints(j2, s);
        s.gridwidth=1;
        s.weightx = 0;
        s.weighty=0;
        layout.setConstraints(j3, s);
        s.gridwidth=0;//该方法是设置组件水平所占用的格子数，如果为0，就说明该组件是该行的最后一个
        s.weightx = 0;//不能为1，j4是占了4个格，并且可以横向拉伸，
//但是如果为1，后面行的列的格也会跟着拉伸,导致j7所在的列也可以拉伸 
//所以应该是跟着j6进行拉伸 
        s.weighty=0;
        layout.setConstraints(j4, s);
        s.gridwidth=5;
        s.weightx = 0;
        s.weighty=1;
        layout.setConstraints(j9, s);
    }
    JLabel j1;
    JComboBox j2;
    JButton j3;
    JPanel j4;
    JComboBox j5;
    JTextField j6;
    JButton j7;
    JList j8;
    JPanel j9;
}