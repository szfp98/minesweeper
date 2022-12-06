package frontend;

import game.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GameFrame extends JFrame {
    private JMenuBar menuBar;
    private JMenu fileMenu, gameMenu, optionsMenu;
    private JMenuItem newMI, resultsMI, beginnerMI, intermediateMI, expertMI, customMI, timeBombsMI, questionMarksMI;
    private JPanel panel1, panel2;
    private JButton resetButton;
    private JLabel mineCounter, timeCounter;
    private JButton[][] fieldButtons;

    private JLabel message;

    private Timer timer;

    private int width, height;

    private boolean timeBombsMIChecked, questionMarksMIChecked;

    public GameFrame(int width, int height){

        timeBombsMIChecked=true;
        questionMarksMIChecked=true;

        this.width=width;
        this.height=height;
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);

        menuBar=new JMenuBar();
        fileMenu=new JMenu("File");
        newMI=new JMenuItem("New");
        newMI.addActionListener(e -> {
            try{
                Controller.newGame(Controller.game.getLevel(), timeBombsMIChecked, questionMarksMIChecked);
            } catch (Exception ex){
                message.setText(ex.getMessage());
            }
        });
        resultsMI=new JMenuItem("Results");
        resultsMI.addActionListener(e -> {
            try{
                Controller.getResultList();
                new ResultsFrame(Controller.resultList.toArray());
            } catch (Exception ex){
                message.setText(ex.getMessage());
            }
        });
        fileMenu.add(newMI);
        fileMenu.add(resultsMI);
        menuBar.add(fileMenu);
        gameMenu=new JMenu("Game");
        beginnerMI=new JMenuItem("Beginner");
        beginnerMI.addActionListener(e -> {
            try{
                Controller.newGame("beginner", timeBombsMIChecked, questionMarksMIChecked);
            } catch (Exception ex){
                message.setText(ex.getMessage());
            }
        });
        intermediateMI=new JMenuItem("Intermediate");
        intermediateMI.addActionListener(e -> {
           try{
               Controller.newGame("intermediate", timeBombsMIChecked, questionMarksMIChecked);
           } catch (Exception ex){
               message.setText(ex.getMessage());
           }
        });
        expertMI=new JMenuItem("Expert");
        expertMI.addActionListener(e -> {
            try{
                Controller.newGame(Controller.game.getLevel(), timeBombsMIChecked, questionMarksMIChecked);
            } catch (Exception ex){
                message.setText(ex.getMessage());
            }
        });
        customMI=new JMenuItem("Custom");
        gameMenu.add(beginnerMI);
        gameMenu.add(intermediateMI);
        gameMenu.add(expertMI);
        //gameMenu.add(customMI);
        menuBar.add(gameMenu);
        optionsMenu=new JMenu("Options");
        timeBombsMI=new JRadioButtonMenuItem("Enable time bombs");
        questionMarksMI=new JRadioButtonMenuItem("Enable question marks");
        optionsMenu.add(timeBombsMI);
        optionsMenu.add(questionMarksMI);
        menuBar.add(optionsMenu);
        this.setJMenuBar(menuBar);

        panel1=new JPanel();
        panel1.setLayout(new GridLayout());
        mineCounter=new JLabel("MineCounter");
        mineCounter.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                try{
                    JLabel label=(JLabel) evt.getSource();
                    if(label.getText().equals("0")){
                        if(Controller.game.checkWin()){
                            Controller.gameOver();
                        }
                    }
                } catch (Exception ex){
                    message.setText(ex.getMessage());
                }
            }
        });
        panel1.add(mineCounter);
        resetButton=new JButton();
        resetButton.addActionListener(e -> {
            Controller.newGame(Controller.game.getLevel(), timeBombsMIChecked, questionMarksMIChecked);
        });
        panel1.add(resetButton);
        timeCounter=new JLabel("TimeCounter");
        panel1.add(timeCounter);
        this.add(panel1, BorderLayout.NORTH);

        panel2=new JPanel();
        panel2.setLayout(new GridLayout(height,width));
        fieldButtons=new JButton[height][width];
        for(int i=0; i<height; i++){
            for (int j=0; j<width; j++){
                fieldButtons[i][j]=new JButton();
                fieldButtons[i][j].setActionCommand(String.valueOf(i)+';'+ String.valueOf(j));
                panel2.add(fieldButtons[i][j]);
                fieldButtons[i][j].addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        JButton button=(JButton) e.getSource();
                        String[] split=button.getActionCommand().split(";");
                        int y=Integer.parseInt(split[0]);
                        int x=Integer.parseInt(split[1]);
                        int value;
                        if(e.getButton()==MouseEvent.BUTTON1){  //left click
                            try{
                                if(button.getText().equals("")){
                                    value=Controller.openField(x, y);
                                    if(value==-1){
                                        button.setText("*");
                                    }
                                    else{
                                        button.setText(String.valueOf(value));
                                    }
                                }
                            } catch (Exception ex){
                                message.setText(ex.getMessage());
                            }
                        }
                        if(e.getButton()==MouseEvent.BUTTON3){  //right click
                            try{
                                if(button.getText().equals("")){
                                    if(Controller.flagField(x, y)){
                                        button.setText("flag");
                                    }
                                }
                                else if(button.getText().equals("flag")){
                                    if(Controller.game.isQuestionMarkEnabled()){
                                        if(Controller.questionMarkField(x, y)){
                                            button.setText("?");
                                        }
                                    }
                                    else{
                                        if(Controller.removeFlag(x, y)==false){
                                            button.setText("");
                                        }
                                    }
                                }
                                else if(button.getText().equals("?")){
                                    if(Controller.removeQuestionMark(x, y)==false){
                                        button.setText("");
                                    }
                                }
                            } catch (Exception ex){
                                message.setText(ex.getMessage());
                            }
                        }
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {}

                    @Override
                    public void mouseReleased(MouseEvent e) {}

                    @Override
                    public void mouseEntered(MouseEvent e) {}

                    @Override
                    public void mouseExited(MouseEvent e) {}
                });
            }
        }
        this.add(panel2, BorderLayout.CENTER);

        message=new JLabel();
        this.add(message, BorderLayout.SOUTH);

        timer=new Timer(500,e -> {
            mineCounter.setText(String.valueOf(Controller.getMineCounterValue()));
            timeCounter.setText(String.valueOf(Controller.getTimeCounterValue()));
        });
        timer.start();
    }
    public void revealFields(){
        try {
            int[][] fieldsValues=Controller.game.getFieldsValues();
            if(fieldButtons.length==fieldsValues.length){
                for(int i=0; i<fieldButtons.length; i++){
                    if(fieldButtons[i].length==fieldsValues[i].length){
                        for(int j=0; j<fieldButtons[i].length; j++){
                            if(fieldsValues[i][j]>=0){
                                fieldButtons[i][j].setText(String.valueOf(fieldsValues[i][j]));
                            } else if(fieldsValues[i][j]==-1){
                                fieldButtons[i][j].setText("*");
                            }
                        }
                    }
                }
            }
        } catch (Exception e){
            message.setText(e.getMessage());
        }
    }

    public void sendMessage(String message){
        this.message.setText(message);
    }
}
