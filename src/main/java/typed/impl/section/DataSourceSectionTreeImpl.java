package typed.impl.section;


import typed.api.PropertyTree;
import typed.api.Tree;
import typed.api.literal.StringLiteralTree;
import typed.api.literal.ValueTree;
import typed.api.section.DataSourcesSectionTree;
import typed.api.section.VariableSectionTree;
import typed.impl.lexical.InternalSyntaxToken;

import java.util.List;

public class DataSourceSectionTreeImpl extends AbstractSectionTree implements DataSourcesSectionTree {

    private final static String META_PARAMETER_COUNT = "count";
    private final static String META_PARAMETER_DEPENDS_ON = "depends_on";
    private final static String META_PARAMETER_PROVIDER = "provider";
    private final static String META_PARAMETER_CONNECTION = "connection";
    private final static String META_PARAMETER_PROVISIONER= "provisioner";



    public DataSourceSectionTreeImpl(InternalSyntaxToken keyToken, List<StringLiteralTree> nestedObjects, ValueTree value) {
        super(keyToken, nestedObjects, null, value);
    }

    @Override
    public String type() {
        return ((StringLiteralTree) nestedObjects().get(0)).value();
    }

    @Override
    public String name() {
        return ((StringLiteralTree) nestedObjects().get(1)).value();
    }

    @Override
    public Tree count() {
        return getValueFromMetaParameter(META_PARAMETER_COUNT);
    }

    @Override
    public Tree dependsOn() {
        return getValueFromMetaParameter(META_PARAMETER_DEPENDS_ON);
    }

    @Override
    public Tree provider() {
        return getValueFromMetaParameter(META_PARAMETER_PROVIDER);
    }

    @Override
    public Tree connection() {
        return getValueFromMetaParameter(META_PARAMETER_CONNECTION);
    }

    @Override
    public Tree provisioner() {
        return getValueFromMetaParameter(META_PARAMETER_PROVISIONER);
    }

    @Override
    public List<PropertyTree> metaParameters() {
        return getMetaParameters();
    }

    @Override
    public Kind kind() {
        return Kind.DATA_SOURCES_SECTION;
    }
}
