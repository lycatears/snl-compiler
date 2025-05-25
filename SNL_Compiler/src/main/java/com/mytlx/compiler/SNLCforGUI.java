package com.mytlx.compiler;

import com.mytlx.compiler.GUI.SNLCompilerGUI;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
/**
 * SNL编译器的GUI版本，主启动函数
 *
 */
public class SNLCforGUI {
    public static void main(String[] args) {
        new SNLCompilerGUI();
        /*
        * 以下代码用来测试中文乱码问题
        * */
        /*
        JFrame jf = new JFrame("123");
        JTextArea jta = new JTextArea();
        jta.append("啊啊啊");
        JScrollPane logScrollPane = new JScrollPane(jta);
        JTabbedPane debugTabbedPane = new JTabbedPane();
        debugTabbedPane.addTab("12345", logScrollPane);
        jf.add(debugTabbedPane);
        jf.setVisible(true);
        jf.setBounds(200, 200, 200, 200);
        jta.append("汉字");
        jta.paintImmediately(jta.getBounds());
         */
    }
}
