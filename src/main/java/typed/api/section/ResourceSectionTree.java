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
 *  More info see: https://www.terraform.io/docs/configuration/resources.html
 *
 *  The full syntax is:
 *      resource TYPE NAME {
 *           CONFIG ...
 *           [count = COUNT]
 *           [depends_on = [NAME, ...]]
 *           [provider = PROVIDER]
 *
 *           [LIFECYCLE]
 *
 *           [CONNECTION]
 *           [PROVISIONER ...]
 *    }
 *
 *    Example:
 *      resource "alicloud_vswitch" "main" {
 *           vpc_id = "${alicloud_vpc.main.id}"
 *           count = "${length(split(",", var.availability_zones))}"
 *           cidr_block = "${lookup(var.cidr_blocks, "az${count.index}")}"
 *           depends_on = [
 *               "alicloud_vpc.main"]
 *      }
 *
 * */


public interface ResourceSectionTree extends PropertyTree {

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

    Tree lifecycle();

    Tree connection();

    Tree provisioner();

    // Extracting all propertyTree from ObjectLiteral
    List<PropertyTree> metaParameters();
}
