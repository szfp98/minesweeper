package frontend;

import javax.swing.*;
import java.awt.*;

public class ResultsFrame extends JFrame {

    public ResultsFrame(String[][] results){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        String[] columns={"#", "Time", "Level", "Date"};
        JTable table = new JTable(results, columns);
        JScrollPane scroll = new JScrollPane(table);
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e->dispose());
        add(scroll, BorderLayout.CENTER);
        add(backButton, BorderLayout.SOUTH);
        pack();
    }
}
