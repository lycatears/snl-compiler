package com.mytlx.compiler.mysyntax.symbols;


import com.mytlx.compiler.mylexer.Token;
import com.mytlx.compiler.mylexer.TokenType;
import com.mytlx.compiler.mysyntax.tree.TreeNode;

public class Terminal extends Symbol{
    private final TreeNode node;
    private Token token;

    public Terminal(Token token) {
        this.token = token;
        this.node = new TreeNode(token);
    }

    @Override
    public TreeNode getNode() {
        return node;
    }

    public Token getToken() {
        return token;
    }

    public static Terminal terminalFactory(TokenType tokenType) {
        return new Terminal(new Token(tokenType));
    }
}
