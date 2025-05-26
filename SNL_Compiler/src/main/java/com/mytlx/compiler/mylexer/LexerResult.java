package com.mytlx.compiler.mylexer;

import com.mytlx.compiler.mylexer.Token;

import java.util.List;

/**
 * 词法分析的结果
 *
 */
public class LexerResult {
    private List<Token> tokenList;

    public List<Token> getTokenList() {
        return tokenList;
    }

    public void setTokenList(List<Token> tokenList) {
        this.tokenList = tokenList;
    }

}
