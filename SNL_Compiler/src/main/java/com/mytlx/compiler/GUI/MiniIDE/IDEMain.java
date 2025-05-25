package com.mytlx.compiler.GUI.MiniIDE;

import javax.swing.*;
import javax.swing.JPanel;

public class IDEMain {
    private JButton newSnlFIle;
    private JButton openSnlFIle;
    private JButton compileButton;
    private JCheckBox enableHighlightCheckBox;
    private JPanel IDEPanel;
    private JPanel sourceCodePanel;
    private JTabbedPane tabbedPane1;
    private JTable table1;
    private JTextArea textArea1;
    private JTextArea textArea2;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        JFrame frame = new JFrame("IDEMain");
        frame.setSize(1440,900);
        frame.setContentPane(new IDEMain().IDEPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
