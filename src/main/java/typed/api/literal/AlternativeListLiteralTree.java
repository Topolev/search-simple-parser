/*
 * Copyright (C) 2017 Cloud Technology Partners
 * All rights reserved
 * uri.shatil@cloudtp.com
 */

package typed.api.literal;


import typed.api.Tree;

import java.util.List;

/* There's an alternative syntax to define a list, such as:
 *  resource "openstack_compute_secgroup_v2" "terraform" {
 *       .....
 *       rule {
 *           from_port   = 22
 *       }
 *       rule {
 *           from_port   = 33
 *       }
 *       .....
 *   }
 *   This expression equals to:
 *   rule = [{...}, {...}]
 *   This interface is similar as ListLiteralTree but
 *   also it will be as a marker for this special cases.
 * */
public interface AlternativeListLiteralTree extends ValueTree {

    List<Tree> values();
}
