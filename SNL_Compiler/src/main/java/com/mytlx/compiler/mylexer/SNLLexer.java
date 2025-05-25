package com.mytlx.compiler.mylexer;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class SNLLexer {
    private enum LexerState{
        NORMAL,
        INID,
        INNUM,
        INASSIGN,
        INCOMMENT,
        INDOT,
        INRANGE,
        INCHAR,
        ERROR
    }

    private Integer line = 1;
    private Integer column = 0;
    private List<String> log = new ArrayList<>();
    private Reader reader;

}
