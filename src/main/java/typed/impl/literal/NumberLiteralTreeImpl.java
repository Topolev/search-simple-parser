package typed.impl.literal;


import typed.api.Tree;
import typed.api.literal.NumberLiteralTree;
import typed.impl.AbstractTree;
import typed.impl.lexical.InternalSyntaxToken;

import java.util.regex.Pattern;

public class NumberLiteralTreeImpl extends AbstractTree implements NumberLiteralTree {

    private static final Pattern HEX = Pattern.compile("^-?+0[xX][0-9a-fA-F]++$");
    private static final Pattern OCT = Pattern.compile("^0[\\d]+$");

    private Float value;

    public NumberLiteralTreeImpl(InternalSyntaxToken syntaxToken) {
        String value = syntaxToken.value();
        this.value =
                isHex(value) ? convertHex(value)
                        : isOct(value) ? convertOct(value) : Float.valueOf(value);
    }

    private Float convertHex(String str) {
        Long number = Long.parseLong(str.substring(2), 16);
        return Float.valueOf(number);
    }

    private Float convertOct(String str) {
        Long number = Long.parseLong(str.substring(1), 8);
        return Float.valueOf(number);
    }

    private boolean isHex(String str) {
        return HEX.matcher(str).matches();
    }

    private boolean isOct(String str) {
        return OCT.matcher(str).matches();
    }

    @Override
    public Float value() {
        return value;
    }

    @Override
    public Kind kind() {
        return Kind.NUMBER_LITERAL;
    }
}
