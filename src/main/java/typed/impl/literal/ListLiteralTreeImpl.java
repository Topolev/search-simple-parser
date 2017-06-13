package typed.impl.literal;


import typed.api.lexical.SyntaxToken;
import typed.api.literal.ListLiteralTree;
import typed.api.literal.ValueTree;
import typed.impl.AbstractTree;

import javax.annotation.Nullable;

public class ListLiteralTreeImpl extends AbstractTree implements ListLiteralTree {

    private SyntaxToken openBracketToken;
    private SyntaxList<ValueTree> values;
    private SyntaxToken closeBracketToken;

    public ListLiteralTreeImpl(SyntaxToken openBracketToken, @Nullable SyntaxList<ValueTree> values, SyntaxToken closeBracketToken) {
        this.openBracketToken = openBracketToken;
        this.values = values;
        this.closeBracketToken = closeBracketToken;
    }

    @Override
    public SyntaxToken openBracketToken() {
        return openBracketToken;
    }

    @Override
    public SyntaxList<ValueTree> values() {
        return values;
    }

    @Override
    public SyntaxToken closeBracketToken() {
        return closeBracketToken;
    }

    @Override
    public Kind kind() {
        return Kind.LIST_LITERAL;
    }
}
