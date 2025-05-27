package com.mytlx.compiler.GUI.MiniIDE;

import com.mytlx.compiler.mylexer.LexerResult;
import com.mytlx.compiler.mylexer.SNLLexer;
import com.mytlx.compiler.mylexer.Token;

import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class IDEMain {
    private JButton newSnlFIle;
    private JButton openSnlFIle;
    private JButton compileButton;
    private JCheckBox enableHighlightCheckBox;
    private JPanel IDEPanel;
    private JPanel sourceCodePanel;
    private JTabbedPane tabbedPane1;
    private JTable lexTable;
    private JTextArea compileLog;
    private JTextPane code;
    private JButton save;
    private JScrollPane codeScrollPane;
    private SNLLexer lexer = new SNLLexer();
    private List<Token> list;

    public IDEMain() {
        codeScrollPane.setRowHeaderView(new LineNumberHeaderView());
        StyledDocument doc = code.getStyledDocument();
        SimpleAttributeSet attrs = new SimpleAttributeSet();
        StyleConstants.setLineSpacing(attrs, 20.0f);
//        Style keywordStyle = doc.addStyle("Keyword", null);
//        StyleConstants.setForeground(keywordStyle, Color.BLUE);
//        StyleConstants.setBold(keywordStyle, true);
//
//        Style idStyle = doc.addStyle("Identifier", null);
//        StyleConstants.setForeground(idStyle, new Color(122, 113, 253)); // 深绿色
//
//        Style numStyle = doc.addStyle("Number", null);
//        StyleConstants.setForeground(numStyle, Color.MAGENTA);
//
//        Style defaultStyle = doc.addStyle("Default", null);
//        StyleConstants.setForeground(defaultStyle, Color.BLACK);

//        code.getStyledDocument().addDocumentListener(new DocumentListener() {
//            @Override
//            public void insertUpdate(DocumentEvent e) {
//                try {
//                    System.out.println(0);
//                    applyHighlighting();
//                } catch (IOException ex) {
//                    throw new RuntimeException(ex);
//                }
//            }
//
//            @Override
//            public void removeUpdate(DocumentEvent e) {
//                try {
//                    applyHighlighting();
//                } catch (IOException ex) {
//                    throw new RuntimeException(ex);
//                }
//            }
//
//            @Override
//            public void changedUpdate(DocumentEvent e) {
//                try {
//                    applyHighlighting();
//                } catch (IOException ex) {
//                    throw new RuntimeException(ex);
//                }
//            }
//
//            void applyHighlighting() throws IOException {
//                InputStream in = new ByteArrayInputStream(code.getText().getBytes(StandardCharsets.UTF_8));
//                LexerResult result = lexer.getLexerResult(new InputStreamReader(in));
//                list = result.getTokenList();
//
//                for (Token token : list) {
//                    String text = token.getValue();
//                    int offset = computeOffset(token.getLine(), token.getColumn()); // 计算该 token 在 JTextPane 中的字符偏移
//
//                    Style style = switch (token.getType()) {
//                        case IF, THEN, WHILE, ELSE, ENDWH, END, PROGRAM, PROCEDURE, TYPE, VAR, FI, DO, BEGIN, READ,
//                             WRITE, OF, RETURN, ARRAY, RECORD -> doc.getStyle("Keyword");
//                        case ID -> doc.getStyle("Identifier");
//                        case INTEGER, INTC -> doc.getStyle("Number");
//                        default -> doc.getStyle("Default");
//                    };
//
//                    new Thread(() -> {
//                        try {
//                            doc.remove(offset, text.length());
//                            System.out.println(1);
//                            doc.insertString(offset, text, style);
//                        } catch (BadLocationException e) {
//                            throw new RuntimeException(e);
//                        }
//                    }).start();
//                }
//            }
//
//            int computeOffset(int line, int column) {
//                int offset = 0;
//                List<String> lines = List.of(code.getText().split("\n"));
//                for (int i = 0; i < line - 1; i++) {
//                    offset += lines.get(i).length() + 1; // +1 处理换行
//                }
//                return offset + (column - 1);
//            }
//
//        });

        compileButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                InputStream in = new ByteArrayInputStream(code.getText().getBytes(StandardCharsets.UTF_8));
                try {
                    lexer.getLog().clear();
                    DefaultTableModel dtm = (DefaultTableModel) lexTable.getModel();
                    compileLog.setText("");

                    for(int i = 0; i < dtm.getRowCount(); i++){
                        dtm.removeRow(i);
                    }

                    LexerResult result = lexer.getLexerResult(new InputStreamReader(in));
                    list = result.getTokenList();
                    for (Token token : list) {
                        String[] columnNames = {"行号", "列号", "内容", "类型"};
                        dtm.setColumnIdentifiers(columnNames);
                        dtm.addRow(new Object[]{token.getLine(), token.getColumn(), token.getValue(), token.getType()});
                    }

                    for (String log : lexer.getLog()) {
                        compileLog.append(log + "\n");
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        JFrame frame = new JFrame("IDEMain");
        frame.setSize(1440, 900);
        frame.setContentPane(new IDEMain().IDEPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
