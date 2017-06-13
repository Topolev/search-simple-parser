package typed.impl;


import typed.api.PropertyTree;
import typed.api.Tree;
import typed.api.literal.StringLiteralTree;
import typed.api.literal.ValueTree;
import typed.impl.lexical.InternalSyntaxToken;

import java.util.List;

public class PropertyTreeImpl extends AbstractTree implements PropertyTree {

    protected ValueTree value;
    protected String key;
    protected List<StringLiteralTree> nestedObjects;
    protected InternalSyntaxToken keyToken;

    public PropertyTreeImpl(InternalSyntaxToken keyToken, List<StringLiteralTree> nestedObjects, InternalSyntaxToken colonToken, ValueTree value) {
        this(keyToken, nestedObjects, value);
    }

    public PropertyTreeImpl(InternalSyntaxToken keyToken, List<StringLiteralTree> nestedObjects, ValueTree value) {
        this.keyToken = keyToken;
        this.value = value;
        this.key = keyToken.value();
        this.nestedObjects = nestedObjects;
    }

    @Override
    public String key() {
        return key;
    }

    @Override
    public List<StringLiteralTree>  nestedObjects() {
        return nestedObjects;
    }

    @Override
    public ValueTree value() {
        return value;
    }

    @Override
    public Kind kind() {
        return Kind.PROPERTY;
    }

    @Override
    public InternalSyntaxToken getKeyToken(){
        return keyToken;
    }
}
