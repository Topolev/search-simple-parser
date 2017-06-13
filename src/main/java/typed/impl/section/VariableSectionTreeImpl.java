/*
 * Copyright (C) 2017 Cloud Technology Partners
 * All rights reserved
 * uri.shatil@cloudtp.com
 */

package typed.impl.section;



import typed.api.literal.StringLiteralTree;
import typed.api.literal.ValueTree;
import typed.api.section.VariableSectionTree;
import typed.impl.lexical.InternalSyntaxToken;

import java.util.List;

/**
 * Syntax
 *      variable NAME {
 *          [type = TYPE]
 *          [default = DEFAULT] - optional
 *          [description = DESCRIPTION] - optional
 *      }
 * */
public class VariableSectionTreeImpl extends AbstractSectionTree implements VariableSectionTree {


    private final static String META_PARAMETER_TYPE = "type";
    private final static String META_PARAMETER_DEFAULT = "default";
    private final static String META_PARAMETER_DESCRIPTION = "description";



    public VariableSectionTreeImpl(InternalSyntaxToken keyToken, List<StringLiteralTree> nestedObjects, ValueTree value) {
        super(keyToken, nestedObjects, null, value);
    }

    @Override
    public String name() {
        return ((StringLiteralTree)nestedObjects().get(0)).value();
    }

    @Override
    public String type() {
        return getStringFromMetaParameter(META_PARAMETER_TYPE);
    }

    @Override
    public ValueTree defaultValue() {
        return getValueFromMetaParameter(META_PARAMETER_DEFAULT);
    }

    @Override
    public ValueTree description() {
        return getValueFromMetaParameter(META_PARAMETER_DESCRIPTION);
    }

    @Override
    public Kind kind() {
        return Kind.VARIABLE_SECTION;
    }
}
