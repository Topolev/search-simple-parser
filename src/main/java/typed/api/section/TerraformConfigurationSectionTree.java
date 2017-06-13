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
 *  More info see:
 *          https://www.terraform.io/docs/configuration/terraform.html
 *          https://www.terraform.io/docs/backends/config.html
 *
 *  It's very strange, because they give a full section syntax in the first link
 *  According to this link the section has to have only one property "required_version".
 *  On the second link new property 'backend' added
 *
 *  The full syntax is:
 *      terraform {
 *          required_version = VALUE
 *      }
 *
 *   Example:
 *      terraform {
 *          required_version = "> 0.7.0"
 *      }
 *
 * */
public interface TerraformConfigurationSectionTree extends PropertyTree {

    Tree requiredVersion();

    Tree backend();

    List<PropertyTree> metaParameters();

}
