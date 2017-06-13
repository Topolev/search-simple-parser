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
 *  https://www.terraform.io/docs/configuration/data-sources.html
 *
 *  There's no info about specific declaration of this block
 *
 *    Example:
 *      data "aws_ami" "web" {
 *           filter {
 *              name   = "state"
 *              values = ["available"]
 *            }
 *
 *            filter {
 *              name   = "tag:Component"
 *              values = ["web"]
 *            }
 *
 *            most_recent = true
 *    }
 *
 *    Syntax:
 *    data TYPE NAME{
 *        // The data meta-parameters look like the resource meta-parameters excluding lifecycle (https://www.terraform.io/docs/configuration/data-sources.html#meta-parameters)
 *    }
 * */

public interface DataSourcesSectionTree extends PropertyTree {

    /**
     * This method extracts the first value from list of nestedObjects of KeyTree
     */
    String type();

    /**
     * This method extracts the second value from list of nestedObjects of KeyTree
     */
    String name();


    // Extracting meta-parameters available to all resources (see https://www.terraform.io/docs/configuration/resources.html#meta-parameters)
    Tree count();

    Tree dependsOn();

    Tree provider();

    Tree connection();

    Tree provisioner();

    // Extracting all propertyTree from ObjectLiteral
    List<PropertyTree> metaParameters();
}
