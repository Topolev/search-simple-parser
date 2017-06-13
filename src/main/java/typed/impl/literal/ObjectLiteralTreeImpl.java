package typed.impl.literal;


import typed.api.PropertyTree;
import typed.api.Tree;
import typed.api.lexical.SyntaxToken;
import typed.api.literal.AlternativeListLiteralTree;
import typed.api.literal.ObjectLiteralTree;
import typed.impl.AbstractTree;
import typed.impl.PropertyTreeImpl;
import typed.impl.lexical.InternalSyntaxToken;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ObjectLiteralTreeImpl extends AbstractTree implements ObjectLiteralTree {

    private List<PropertyTree> properties;
    private SyntaxList<PropertyTree> pairs;

    private SyntaxToken openCurlyBraceToken;
    private SyntaxToken closeCurlyBraceToken;


    public ObjectLiteralTreeImpl(InternalSyntaxToken openCurlyBraceToken, SyntaxList<PropertyTree> pairs, InternalSyntaxToken closeCurlyBraceToken) {

        this.openCurlyBraceToken = openCurlyBraceToken;
        this.closeCurlyBraceToken = closeCurlyBraceToken;
        this.pairs = pairs;

        properties = new ArrayList<>();

        while (pairs != null) {
            properties.add(pairs.element());
            pairs = pairs.next();
        }


    }

    @Override
    public List<PropertyTree> properties() {
        return properties;
    }

    @Override
    public PropertyTree getProperty(String key) {
        List<PropertyTree> properties = properties().stream()
                .filter(property -> property.key().equals(key))
                .collect(Collectors.toList());
        if (properties.size() == 0) {
            return null;
        }
        if (properties.size() == 1) {
            return properties.get(0);
        }
        return extractAlternativeList(properties);
    }

    /* There's an alternative syntax to define a list, such as:
    *   resource "openstack_compute_secgroup_v2" "terraform" {
    *       .....
    *       rule {
    *           from_port   = 22
    *       }
    *       rule {
    *           from_port   = 33
    *       }
    *       .....
    *   }
    *   Therefore we extract all values from PropertyTree and
    *   create new PropertyTree with value AlternativeListLiteralTree
    * */
    private PropertyTree extractAlternativeList(List<PropertyTree> properties) {
        List<Tree> values = properties.stream().map(PropertyTree::value).collect(Collectors.toList());
        AlternativeListLiteralTreeImpl alternativeLiteral = new AlternativeListLiteralTreeImpl(values);

        PropertyTree firstProperty = properties.get(0);
        return new PropertyTreeImpl(firstProperty.getKeyToken(), firstProperty.nestedObjects(), alternativeLiteral);
    }

    @Override
    public Kind kind() {
        return Kind.OBJECT_LITERAL;
    }
}
