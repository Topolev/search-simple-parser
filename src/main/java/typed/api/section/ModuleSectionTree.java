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
 *  More info see: https://www.terraform.io/docs/configuration/modules.html
 *
 *  The full syntax is:
 *      module NAME {
 *           source = SOURCE_URL
 *           CONFIG ...
 *      }
 *
 *    Example:
 *      module "consul" {
 *          source  = "github.com/hashicorp/consul/terraform/aws"
 *          servers = 5
 *      }
 *
 * */


public interface ModuleSectionTree extends PropertyTree {

    /**
     * This method extracts the first value from list of nestedObjects of KeyTree
      */
    String name();


    // The only required configuration key for a module
    Tree source();

    // Extracting all propertyTree from ObjectLiteral
    List<PropertyTree> metaParameters();
}
