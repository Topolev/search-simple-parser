/*
 * Copyright (C) 2017 Cloud Technology Partners
 * All rights reserved
 * uri.shatil@cloudtp.com
 */

package typed.impl.literal;


import typed.api.Tree;
import typed.api.literal.AlternativeListLiteralTree;
import typed.impl.AbstractTree;

import java.util.List;

public class AlternativeListLiteralTreeImpl extends AbstractTree implements AlternativeListLiteralTree {

    private List<Tree> values;

    public AlternativeListLiteralTreeImpl(List<Tree> values) {
        this.values = values;
    }

    @Override
    public List<Tree> values() {
        return values;
    }

    @Override
    public Kind kind() {
        return Kind.ALTERNATIVE_LIST_LITERAL;
    }
}
