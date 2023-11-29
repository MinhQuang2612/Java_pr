package gui;

import javax.swing.*;
import java.awt.*;

public class TrangChu_Form extends JPanel {
    JPanel pn_Center;
    JLabel lbCenter,lbCenter1,lbCenter2,lbCenter3,lbCenter4;
    ImageIcon s[];
    public TrangChu_Form(){
        doShow();
    }

    private void doShow() {
        pn_Center = new JPanel();
        this.setLayout(new BorderLayout());
        //pn_Center.setLayout(new GridLayout(2,1));
        JPanel pn_C1 = new JPanel();
        JPanel pn_C2 = new JPanel();
//        TitledBorder title_center = new TitledBorder("Trang chủ");
//        this.setBorder(title_center);
        lbCenter = new JLabel();lbCenter1 = new JLabel();lbCenter2 = new JLabel();
        lbCenter3 = new JLabel();lbCenter4 = new JLabel();

        s = new ImageIcon[7];
        s[0] = new ImageIcon(getClass().getResource("/images/pn111.png"));
        s[1] = new ImageIcon(getClass().getResource("/images/pn1112.png"));
        s[2] = new ImageIcon(getClass().getResource("/images/pn1113.png"));
        s[3] = new ImageIcon(getClass().getResource("/images/pn1114.png"));
        s[4] = new ImageIcon(getClass().getResource("/images/pn1115.png"));
        s[5] = new ImageIcon(getClass().getResource("/images/pn1116.png"));
        s[6] = new ImageIcon(getClass().getResource("/images/pn1118.png"));

        lbCenter.setIcon(new ImageIcon(getClass().getResource("/images/pn1116.png")));


        lbCenter1.setIcon(new ImageIcon(getClass().getResource("/images/pn1121.png")));
        lbCenter2.setIcon(new ImageIcon(getClass().getResource("/images/pn1131.png")));
        lbCenter3.setIcon(new ImageIcon(getClass().getResource("/images/pn1132.png")));
        lbCenter4.setIcon(new ImageIcon(getClass().getResource("/images/pn1151.png")));
        
        pn_C1.add(lbCenter);
        pn_C1.add(lbCenter1);
        pn_C2.add(lbCenter2);
        pn_C2.add(lbCenter3);
        pn_C2.add(lbCenter4);

        this.add(pn_C1, BorderLayout.NORTH);
        this.add(pn_C2,BorderLayout.SOUTH);
    }
}
