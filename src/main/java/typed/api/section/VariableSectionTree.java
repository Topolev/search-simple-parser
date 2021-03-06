/*
 * Copyright (C) 2017 Cloud Technology Partners
 * All rights reserved
 * uri.shatil@cloudtp.com
 */

package typed.api.section;


import typed.api.PropertyTree;
import typed.api.Tree;
import typed.api.literal.ValueTree;

/**
 *  More info see: https://www.terraform.io/docs/configuration/variables.html
 *
 *  Examples:
 *      variable "key" {
 *          type =  "string"
 *      }
 *      variable "images" {
 *          type =  "map"
 *          default = {
 *              us-east-1 = "image-1234"
 *              us-west-2 = "image-4566"
 *          }
 *      }
 *
 *  The full syntax is:
 *      variable NAME {
 *          [type = TYPE]
 *          [default = DEFAULT] - optional
 *          [description = DESCRIPTION] - optional
 *      }
 *
 * */


public interface VariableSectionTree extends PropertyTree {

    /**
     * A convenient way to extract
     * a variable name from key of property
      */
    String name();

    /**
     * A convenient way to extract an appropriate value of property from ObjectLiteral
     *
     * According the documentation the type might be omitted by user
     * but in this case the variable type will be inferred based on the defaultValue.
     */
    String type();

    /**
     * A convenient way to extract an appropriate value of property from ObjectLiteral
     *
     * The value might be omitted and injected from command line, for example
     * */
    ValueTree defaultValue();

    /**
     * A convenient way to extract an appropriate value of property from ObjectLiteral
     *
     * An optional property
     * */
    ValueTree description();
}
