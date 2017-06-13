package typed.impl.section;


import typed.api.PropertyTree;
import typed.api.literal.ObjectLiteralTree;
import typed.api.literal.StringLiteralTree;
import typed.api.literal.ValueTree;
import typed.impl.PropertyTreeImpl;
import typed.impl.lexical.InternalSyntaxToken;

import java.util.List;

public class AbstractSectionTree extends PropertyTreeImpl {

    public AbstractSectionTree(InternalSyntaxToken keyToken, List<StringLiteralTree> nestedObjects, InternalSyntaxToken colonToken, ValueTree value) {
        super(keyToken, nestedObjects, colonToken, value);
    }

    protected List<PropertyTree> getMetaParameters() {
        return ((ObjectLiteralTree) value()).properties();
    }

    protected PropertyTree getMetaParameter(String key) {
        return ((ObjectLiteralTree) value()).getProperty(key);
    }

    protected ValueTree getValueFromMetaParameter(String key) {
        PropertyTree property = getMetaParameter(key);
        return property != null ? property.value() : null;
    }

    protected String getStringFromMetaParameter(String key) {
        PropertyTree property = getMetaParameter(key);
        return property != null ? ((StringLiteralTree) property.value()).value() : null;
    }
}
