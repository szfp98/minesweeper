package frontend;

import javax.swing.*;
import java.awt.*;

public class SettingsFrame extends JFrame {
    private JLabel widthLabel, heightLabel, basicBombsLabel, timeBombsLabel, explodingTimeLabel;
    private JTextField widthTF, heightTF, basicBombsTF, timeBombsTF, explodingTimeTF;
    private JButton okButton;

    public SettingsFrame(){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLayout(new GridLayout(6, 2));

        widthLabel=new JLabel("Width");
        widthTF=new JTextField();
        heightLabel=new JLabel("Height");
        heightTF=new JTextField();
        basicBombsLabel=new JLabel("Number of basic bombs");
        basicBombsTF=new JTextField();
        timeBombsLabel=new JLabel("Number of timed bombs");
        timeBombsTF=new JTextField();
        explodingTimeLabel=new JLabel("Time boms' exploding time");
        explodingTimeTF=new JTextField();
        okButton=new JButton("OK");
        add(widthLabel);
        add(widthTF);
        add(heightLabel);
        add(heightTF);
        add(basicBombsLabel);
        add(basicBombsTF);
        add(timeBombsLabel);
        add(timeBombsTF);
        add(explodingTimeLabel);
        add(explodingTimeTF);
        add(okButton);

        pack();
    }
}
