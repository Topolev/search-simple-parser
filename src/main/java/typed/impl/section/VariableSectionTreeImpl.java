/*
 * Copyright (C) 2017 Cloud Technology Partners
 * All rights reserved
 * uri.shatil@cloudtp.com
 */

package typed.impl.section;



import typed.api.PropertyTree;
import typed.api.Tree;
import typed.api.literal.StringLiteralTree;
import typed.api.literal.ValueTree;
import typed.api.section.VariableSectionTree;
import typed.impl.AbstractTree;
import typed.impl.PropertyTreeImpl;
import typed.impl.lexical.InternalSyntaxToken;

import java.util.List;

/**
 * Syntax
 *      variable NAME {
 *          [type = TYPE]
 *          [default = DEFAULT] - optional
 *          [description = DESCRIPTION] - optional
 *      }
 * */
public class VariableSectionTreeImpl extends PropertyTreeImpl implements VariableSectionTree {


    public VariableSectionTreeImpl(InternalSyntaxToken keyToken, List<StringLiteralTree> nestedObjects, ValueTree value) {
        super(keyToken, nestedObjects, value);
    }


    @Override
    public Kind kind() {
        return Kind.VARIABLE_SECTION;
    }

    @Override
    public String name() {
        return key;
    }

    @Override
    public Tree type() {
        return null;
    }

    @Override
    public Tree defaultValue() {
        return null;
    }

    @Override
    public Tree description() {
        return null;
    }
}
