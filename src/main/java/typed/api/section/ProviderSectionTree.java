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
 *  More info see: https://www.terraform.io/docs/configuration/providers.html
 *
 *  The full syntax is:
 *      provider NAME {
 *          CONFIG ...
 *          [alias = ALIAS]
 *      }
 *
 *    Example:
 *      provider "aws" {
 *          alias  = "west"
 *          region = "us-west-2"
 *      }
 *
 * */


public interface ProviderSectionTree extends PropertyTree {

    /**
     * This method extracts the first value from list of nestedObjects of KeyTree
      */
    String name();

    // Extracting meta-parameters according defined template
    Tree alias();

    // Extracting all propertyTree from ObjectLiteral
    List<PropertyTree> metaParameters();
}
