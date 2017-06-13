package typed.api;


import typed.api.literal.StringLiteralTree;
import typed.api.literal.ValueTree;
import typed.impl.lexical.InternalSyntaxToken;

import java.util.List;

public interface PropertyTree extends Tree {

    InternalSyntaxToken getKeyToken();
    String key();
    List<StringLiteralTree> nestedObjects();
    ValueTree value();

}
