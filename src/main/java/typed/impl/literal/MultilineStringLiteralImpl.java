package typed.impl.literal;

import typed.api.Tree;
import typed.api.literal.MultilineStringLiteralTree;
import typed.impl.AbstractTree;
import typed.impl.lexical.InternalSyntaxToken;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MultilineStringLiteralImpl extends AbstractTree implements MultilineStringLiteralTree {

    private final static Pattern MULTILELINE = Pattern.compile("<<([\\w]+)([\\w\\s]*?)\\1");
    private String value;
    private String marker;

    public MultilineStringLiteralImpl(InternalSyntaxToken syntaxToken){
        Matcher matcher = MULTILELINE.matcher(syntaxToken.value());
        matcher.find();
        this.value = matcher.group(2);
        this.marker = matcher.group(1);
    }

    @Override
    public String marker() {
        return marker;
    }

    @Override
    public String value() {
        return value;
    }

    @Override
    public Kind kind() {
        return Kind.MULTILINE_STRING_LITERAL;
    }
}
