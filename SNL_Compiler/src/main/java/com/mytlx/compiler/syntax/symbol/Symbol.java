package com.mytlx.compiler.syntax.symbol;

import com.mytlx.compiler.syntax.tree.TreeNode;
import com.mytlx.compiler.utils.ToStringUtils;

/**
 * 符号，派生出终结符合非终结符
 *
 */
public abstract class Symbol extends ToStringUtils {

    public abstract TreeNode getNode();


}
