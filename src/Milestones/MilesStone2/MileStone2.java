package Milestones.MilesStone2;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class MileStone2 {
    static JFrame fra0;
    static JTextArea textA;
    static JComboBox comboB;

    public static void west(Container frame0) {
        JPanel wPanel = new JPanel();
        wPanel.setLayout(new FlowLayout());
        comboB = new JComboBox();
        comboB.setPreferredSize(new Dimension(195, 30));
        comboB.addItem("python.txt");
        comboB.addItem("java.txt");
        comboB.addItem("c.txt");
        comboB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                textA.setText("");
                FileReader read = null;
                try {
                    read = new FileReader("TEXT/" + comboB.getSelectedItem().toString());
                } catch (FileNotFoundException e) {
                    System.out.println("file not found");
                }
                try {
                    textA.read(read, "text/" + comboB.getSelectedItem().toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        JButton button1 = new JButton("Clear");
        button1.setPreferredSize(new Dimension(100, 30));
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                textA.setText("");
            }
        });
        wPanel.add(comboB);
        wPanel.add(button1);
        frame0.add("West", wPanel);
    }

    public static void south(Container southPanel) {
        JButton but1 = new JButton("Close");
        but1.setPreferredSize(new Dimension(100, 30));
        but1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
//this.frame.dispose();
            }
        });
        southPanel.add(but1);

    }

    public static void east(Container frame0) {
        textA = new JTextArea();
        textA.setLineWrap(true);
        textA.setBorder(new EmptyBorder(5, 5, 5, 5));
        textA.setEditable(false);
        textA.setPreferredSize(new Dimension(300, 300));
        FileReader reader = null;
        try {
            reader = new FileReader("TEXT/python.txt");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "File not found!", "WARNING",
                    JOptionPane.ERROR_MESSAGE);
        }
        try {
            textA.read(reader, "TEXT/python.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        JScrollPane scrollPane = new JScrollPane(textA,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(300, 150));
        frame0.add("East", scrollPane);
    }

    public static void GUI() {
        fra0 = new JFrame("Test events: files");
        fra0.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fra0.setLayout(new BorderLayout());
        fra0.setPreferredSize(new Dimension(900, 450));
        JPanel southP = new JPanel();
        southP.setBorder(new EmptyBorder(10, 400, 10, 10));
        fra0.add("South", southP);
        west(fra0);
        east(fra0);
        south(southP);

        fra0.pack();
        fra0.setLocationRelativeTo(null);
        fra0.setVisible(true);
    }
    public static void main(String[] args) {
        GUI();
    }
}