package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gameboard {
    private JLabel mineCounter;
    private JLabel timeCounter;
    private JButton button1;
    private JPanel minePanel;
    private JPanel panel1;

    private ActionListener mineButtonListener;

    private void setMineButtonListener(){
        mineButtonListener=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        };
    }

    private void createUIComponents() {
        GridLayout mineLayout=new GridLayout(8,8);
        minePanel=new JPanel();
        minePanel.setLayout(mineLayout);
        for(int i=0; i<8*8; i++){
            JButton mineButton=new JButton();
            mineButton.addActionListener(mineButtonListener);
            mineButton.setActionCommand(String.valueOf(i));
            minePanel.add(mineButton);
        }
    }
}
