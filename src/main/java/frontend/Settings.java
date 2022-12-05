package frontend;

import game.Controller;
import game.CustomGame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Settings {
    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JButton OKButton;

    public Settings() {
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.game=new CustomGame(
                        Integer.parseInt(textField1.getText()),
                        Integer.parseInt(textField2.getText()),
                        Integer.parseInt(textField3.getText()),
                        Integer.parseInt(textField4.getText()),
                        Integer.parseInt(textField5.getText()));
            }
        });
    }
}
