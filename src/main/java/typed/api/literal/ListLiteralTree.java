/*
 * Copyright (C) 2017 Cloud Technology Partners
 * All rights reserved
 * uri.shatil@cloudtp.com
 */
package typed.api.literal;

import typed.api.lexical.SyntaxToken;
import typed.impl.literal.SyntaxList;

/**
 *  According https://github.com/hashicorp/hcl this
 *  structure called as array
 *
 *  According https://www.terraform.io/docs/configuration/syntax.html
 *  structure called as list
 *
 *  We choose name 'list' which is more close to this subject-oriented domain
 *
 *  Examples:
 *  ["foo", "bar", "baz", 12]
 * */

public interface ListLiteralTree extends ValueTree {

    SyntaxToken openBracketToken();

    SyntaxList<ValueTree> values();

    SyntaxToken closeBracketToken();

}
