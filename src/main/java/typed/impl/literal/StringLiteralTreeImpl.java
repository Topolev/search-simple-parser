package typed.impl.literal;

import typed.api.Tree;
import typed.api.literal.StringLiteralTree;
import typed.impl.AbstractTree;
import typed.impl.lexical.InternalSyntaxToken;

import java.util.regex.Pattern;

public class StringLiteralTreeImpl extends AbstractTree implements StringLiteralTree {


    private String value;

    public StringLiteralTreeImpl(InternalSyntaxToken syntaxToken){
        String value = syntaxToken.value();

        this.value = value.startsWith("\"") ?
                value.substring(1,syntaxToken.value().length()-1) :
                value;
    }


    @Override
    public String value() {
        return value;
    }

    @Override
    public Kind kind() {
        return Kind.STRING_LITERAL;
    }
}
