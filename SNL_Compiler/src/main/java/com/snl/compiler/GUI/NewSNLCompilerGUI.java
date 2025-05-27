package com.snl.compiler.GUI;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxScheme;
import org.fife.ui.rsyntaxtextarea.Token;
import org.fife.ui.rsyntaxtextarea.TokenMakerFactory;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;
import java.awt.*;

public class NewSNLCompilerGUI extends JFrame {
    private RSyntaxTextArea codeEditor;

    public NewSNLCompilerGUI() {
        setTitle("SNL 编译器演示程序");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initUI();
    }

    private void initUI() {
        codeEditor = new RSyntaxTextArea(30, 80);
        codeEditor.setSyntaxEditingStyle("text/snl");
        codeEditor.setCodeFoldingEnabled(true);
        codeEditor.setAntiAliasingEnabled(true);

        // 自定义颜色（可选）
        SyntaxScheme scheme = codeEditor.getSyntaxScheme();
        scheme.getStyle(Token.RESERVED_WORD).foreground = Color.BLUE;
        scheme.getStyle(Token.IDENTIFIER).foreground = new Color(172, 104, 255);
        scheme.getStyle(Token.LITERAL_NUMBER_DECIMAL_INT).foreground = Color.MAGENTA;
        scheme.getStyle(Token.OPERATOR).foreground = Color.DARK_GRAY;

        RTextScrollPane sp = new RTextScrollPane(codeEditor);
        getContentPane().add(sp, BorderLayout.CENTER);
    }

//    public static void main(String[] args) {
//        // 注册自定义 TokenMakerFactory
//        TokenMakerFactory.setDefaultInstance(
////                new SNLTokenMakerFactory());
//        );
//
//        SwingUtilities.invokeLater(() -> {
//            SNLCompilerGUI gui = new SNLCompilerGUI();
//            gui.setVisible(true);
//        });
//    }
}
