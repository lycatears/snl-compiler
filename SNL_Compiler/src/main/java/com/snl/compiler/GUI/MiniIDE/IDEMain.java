package com.snl.compiler.GUI.MiniIDE;

import com.snl.compiler.GUI.CustomTreeCellRenderer;
import com.snl.compiler.GUI.CustomTreeModel;
import com.snl.compiler.mylexer.LexerResult;
import com.snl.compiler.mylexer.SNLLexer;
import com.snl.compiler.mylexer.Token;
import com.snl.compiler.mysyntax.Parser;
import com.snl.compiler.mysyntax.tree.TreeNode;
import com.snl.compiler.semantic.SemanticAnalyzer;

import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.*;
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
    private JPanel IDEPanel;
    private JPanel sourceCodePanel;
    private JTabbedPane tabbedPane1;
    private JTable lexTable;
    private JTextArea compileLog;
    private JTextPane code;
    private JButton save;
    private JScrollPane codeScrollPane;
    private JTree grammarTree;
    private JTextPane semanticResult;
    private SNLLexer lexer = new SNLLexer();
    private List<Token> list;

    public IDEMain() {
        LineNumberHeaderView lineNumber = new LineNumberHeaderView();
        lineNumber.setLineHeight(18);
        codeScrollPane.setRowHeaderView(lineNumber);
        StyledDocument doc = code.getStyledDocument();
        SimpleAttributeSet attrs = new SimpleAttributeSet();
        StyleConstants.setLineSpacing(attrs, 20.0f);

        compileButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                InputStream in = new ByteArrayInputStream(code.getText().getBytes(StandardCharsets.UTF_8));
                try {
                    // 清空日志
                    lexer.getLog().clear();
                    DefaultTableModel dtm = (DefaultTableModel) lexTable.getModel();
                    compileLog.setText("");

                    for(int i = 0; i < dtm.getRowCount(); i++){
                        dtm.removeRow(i);
                    }

                    //词法分析
                    LexerResult result = lexer.getLexerResult(new InputStreamReader(in));
                    Parser parser = new Parser();
                    parser.lexParse(code.getText());

                    //语法分析
                    TreeNode root = parser.parse().root;
                    CustomTreeModel ctm = new CustomTreeModel(root);
                    grammarTree.setModel(ctm);
                    grammarTree.setCellRenderer(new CustomTreeCellRenderer());

                    //语义分析
                    SemanticAnalyzer sa = new SemanticAnalyzer();
                    sa.analyze(root);

                    list = result.getTokenList();
                    for (Token token : list) {
                        String[] columnNames = {"行号", "列号", "内容", "类型"};
                        dtm.setColumnIdentifiers(columnNames);
                        dtm.addRow(new Object[]{token.getLine(), token.getColumn(), token.getValue(), token.getType()});
                    }

                    for (String log : lexer.getLog()) {
                        compileLog.append(log + "\n");
                    }

                    for(String log: parser.getLog()){
                        compileLog.append(log + "\n");
                    }

                    for(String log: sa.getLog()){
                        compileLog.append(log + "\n");
                        if(log.contains("ERROR")){
                            StyledDocument sd = (StyledDocument) semanticResult.getDocument();
                            AttributeSet as = sd.addStyle("error", null);
                            sd.insertString(sd.getLength(),log+'\n',as);
                        }
                    }
                } catch (IOException | BadLocationException ex) {
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
