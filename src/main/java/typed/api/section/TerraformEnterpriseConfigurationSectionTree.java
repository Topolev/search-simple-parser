/*
 * Copyright (C) 2017 Cloud Technology Partners
 * All rights reserved
 * uri.shatil@cloudtp.com
 */

package typed.api.section;


import typed.api.PropertyTree;
import typed.api.Tree;

/**
 *  More info see: https://www.terraform.io/docs/configuration/terraform-enterprise.html
 *
 *  The full syntax is:
 *      atlas {
 *          name = VALUE
 *      }
 *
 *   Example:
 *      atlas {
 *          name = "mitchellh/production-example"
 *      }
 *
 * */
public interface TerraformEnterpriseConfigurationSectionTree extends PropertyTree {

    Tree name();

}
