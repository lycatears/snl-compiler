package com.mytlx.compiler.semantic;

import com.mytlx.compiler.mysyntax.symbols.Symbol;
import com.mytlx.compiler.mysyntax.symbols.Terminal;
import com.mytlx.compiler.mysyntax.tree.TreeNode;

public class SemanticAnalyzer {

    private SymbolTable globalTable = new SymbolTable(null); // 全局符号表

    public void analyze(TreeNode root) {
        traverse(root, globalTable);
    }

    private void traverse(TreeNode node, SymbolTable currentScope) {
        while (node != null) {
            switch (node.getText()) {
                case "Program":
                    traverse(node.getChildren(), currentScope);
                    break;
                case "VarDecl":
                    String varName = extractIdentifier(node); // 从子节点中提取变量名
                    if (currentScope.contains(varName)) {
                        reportError("重复定义变量: " + varName, node);
                    } else {
                        currentScope.put(varName, new Terminal(node.getToken())); // 简化处理类型为 int
                    }
                    break;
                case "AssignStmt":
                    String left = extractLeft(node);
                    if (!currentScope.containsRecursive(left)) {
                        reportError("变量未定义: " + left, node);
                    }
                    // 也可加类型推导判断右边表达式类型
                    break;
                case "ProcedureDecl":
                    String procName = extractIdentifier(node);
                    SymbolTable newScope = new SymbolTable(currentScope);
                    currentScope.put(procName, new Terminal(node.getToken()));
                    traverse(node.getChildren(), newScope);
                    break;
                default:
                    traverse(node.getChildren(), currentScope);
                    break;
            }
            node = node.getSiblings();
        }
    }

    private void reportError(String message, TreeNode node) {
        System.err.println("语义错误: " + message + " at " + node.getText());
    }

    private String extractIdentifier(TreeNode node) {
        // 实际应递归查找 IDENTIFIER 类型子节点
        return node.getChildren().getText();
    }

    private String extractLeft(TreeNode node) {
        return node.getChildren().getText(); // 简化处理
    }
}
