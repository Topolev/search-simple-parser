package typed.impl.literal;

import typed.api.Tree;
import typed.api.literal.BooleanLiteralTree;
import typed.impl.AbstractTree;
import typed.impl.lexical.InternalSyntaxToken;

public class BooleanLiteralTreeImpl extends AbstractTree implements BooleanLiteralTree {

    private boolean value;
    private InternalSyntaxToken token;

    public BooleanLiteralTreeImpl(InternalSyntaxToken internalSyntaxToken){
        value = Boolean.valueOf(internalSyntaxToken.value());
        token = internalSyntaxToken;
    }

    @Override
    public Boolean value() {
        return value;
    }

    @Override
    public Kind kind() {
        return Kind.BOOLEAN_LITERAL;
    }
}
