package com.mytlx;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Test extends JFrame {
    private JTextPane textPane;
    private StyledDocument doc;
    private Map<String, Style> tokenStyles;

    public Test() {
        setTitle("代码高亮示例");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textPane = new JTextPane();
        doc = textPane.getStyledDocument();

        // 初始化样式
        initStyles();

        // 添加文本变化监听器
        textPane.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                highlight();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                highlight();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                highlight();
            }
        });

        add(new JScrollPane(textPane), BorderLayout.CENTER);
    }

    private void initStyles() {
        tokenStyles = new HashMap<>();

        StyleContext sc = StyleContext.getDefaultStyleContext();

        // 定义不同token的样式
        Style defaultStyle = sc.addStyle("default", null);
        StyleConstants.setForeground(defaultStyle, Color.BLACK);

        Style keywordStyle = sc.addStyle("keyword", defaultStyle);
        StyleConstants.setForeground(keywordStyle, Color.BLUE);
        StyleConstants.setBold(keywordStyle, true);

        Style stringStyle = sc.addStyle("string", defaultStyle);
        StyleConstants.setForeground(stringStyle, new Color(0, 128, 0));

        Style commentStyle = sc.addStyle("comment", defaultStyle);
        StyleConstants.setForeground(commentStyle, Color.GRAY);
        StyleConstants.setItalic(commentStyle, true);

        // 将token类型映射到样式
        tokenStyles.put("KEYWORD", keywordStyle);
        tokenStyles.put("STRING", stringStyle);
        tokenStyles.put("COMMENT", commentStyle);
        // 添加更多token类型...
    }

    private void highlight() {
        try {
            String text = doc.getText(0, doc.getLength());

            // 先重置所有文本为默认样式
            doc.setCharacterAttributes(0, doc.getLength(),
                    tokenStyles.get("DEFAULT"), true);

            // 这里应该调用你的词法分析器来获取token序列
            // List<Token> tokens = yourLexer.tokenize(text);

            // 模拟token序列 (实际应用中替换为你的词法分析结果)
            // for (Token token : tokens) {
            //     Style style = tokenStyles.get(token.getType());
            //     if (style != null) {
            //         doc.setCharacterAttributes(token.getStart(), 
            //             token.getLength(), style, false);
            //     }
            // }

            // 简单示例：高亮"public"关键字
            int pos = 0;
            while ((pos = text.indexOf("public", pos)) >= 0) {
                doc.setCharacterAttributes(pos, 6,
                        tokenStyles.get("KEYWORD"), false);
                pos += 6;
            }

        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Test().setVisible(true);
        });
    }
}