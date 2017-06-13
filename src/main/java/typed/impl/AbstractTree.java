package typed.impl;


import typed.api.Tree;

import java.util.Arrays;

public abstract class AbstractTree implements Tree {

    protected Tree parent;



    @Override
    public boolean is(Kind... kind) {
        return Arrays.stream(kind)
                .anyMatch(k -> k == kind());
    }

    @Override
    public Tree parent() {
        return parent;
    }

}
