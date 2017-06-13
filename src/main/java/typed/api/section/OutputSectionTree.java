/*
 * Copyright (C) 2017 Cloud Technology Partners
 * All rights reserved
 * uri.shatil@cloudtp.com
 */

package typed.api.section;


import typed.api.PropertyTree;
import typed.api.Tree;

import java.util.List;

/**
 *  More info see: https://www.terraform.io/docs/configuration/outputs.html
 *
 *  The full syntax is:
 *      output NAME {
 *          value = VALUE        - required
 *          description = VALUE  - optional
 *          depends_on = VALUE   - optional
 *          sensetive = VALUE    - optional
 *      }
 *
 *  Example:
 *      output "sensitive" {
 *          sensitive = true
 *          value     = VALUE
 *      }
 *
 * */


public interface OutputSectionTree extends PropertyTree {

    /**
     * This method extracts the first value from list of nestedObjects of KeyTree
      */
    String name();


    // Extracting meta-parameters which is described according to https://www.terraform.io/docs/configuration/outputs.html#description

    // According documentation this field is required
    Tree valueParameter();

    Tree description();

    Tree dependsOn();

    Tree sensitive();

    // Extracting all propertyTree from ObjectLiteral
    List<PropertyTree> metaParameters();
}
