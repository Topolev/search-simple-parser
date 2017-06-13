package typed.api.literal;


import typed.api.PropertyTree;

import java.util.List;

public interface ObjectLiteralTree extends ValueTree {

    List<PropertyTree> properties();
    PropertyTree getProperty(String key);
}
