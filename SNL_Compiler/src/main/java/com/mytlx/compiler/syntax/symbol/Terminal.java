package com.mytlx.compiler.syntax.symbol;

import com.mytlx.compiler.lexer.Token;
import com.mytlx.compiler.lexer.TokenType;
import com.mytlx.compiler.syntax.tree.TreeNode;

/**
 * 终结符
 *
 */
public class Terminal extends Symbol {


    private final TreeNode node;
    private Token token;

    public Terminal(Token token) {
        super();
        this.token = token;
        this.node = new TreeNode(token.getValue());
    }

    /**
     * 终结符工厂
     *
     * @param tokenType
     * @return
     */
    public static Terminal terFactory(TokenType tokenType) {
        return new Terminal(new Token(tokenType));
    }


    public Token getToken() {
        return token;
    }

    @Override
    public TreeNode getNode() {
        return node;
    }
}
