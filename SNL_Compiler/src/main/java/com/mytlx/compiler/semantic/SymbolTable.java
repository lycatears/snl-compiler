package com.mytlx.compiler.semantic;

import com.mytlx.compiler.mysyntax.symbols.Symbol;

import java.util.HashMap;
import java.util.Map;

public class SymbolTable {
    private Map<String, Symbol> table = new HashMap<>();
    private SymbolTable parent;

    public SymbolTable(SymbolTable parent) {
        this.parent = parent;
    }

    public void put(String name, Symbol info) {
        table.put(name, info);
    }

    public boolean contains(String name) {
        return table.containsKey(name);
    }

    public boolean containsRecursive(String name) {
        return contains(name) || (parent != null && parent.containsRecursive(name));
    }
}
