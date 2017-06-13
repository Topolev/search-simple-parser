package typed.impl.section;


import typed.api.PropertyTree;
import typed.api.literal.StringLiteralTree;
import typed.api.literal.ValueTree;
import typed.api.section.ModuleSectionTree;
import typed.impl.lexical.InternalSyntaxToken;

import java.util.List;

public class ModuleSectionTreeImpl extends AbstractSectionTree implements ModuleSectionTree {

    private static final String META_PARAMETER_SOURCE = "source";

    public ModuleSectionTreeImpl(InternalSyntaxToken keyToken, List<StringLiteralTree> nestedObjects, ValueTree value) {
        super(keyToken, nestedObjects, null, value);
    }

    @Override
    public String name() {
        return ((StringLiteralTree) nestedObjects().get(0)).value();
    }

    @Override
    public ValueTree source() {
      return getValueFromMetaParameter(META_PARAMETER_SOURCE);
    }

    @Override
    public List<PropertyTree> metaParameters() {
        return getMetaParameters();
    }

    @Override
    public Kind kind() {
        return Kind.MODULE_SECTION;
    }
}
