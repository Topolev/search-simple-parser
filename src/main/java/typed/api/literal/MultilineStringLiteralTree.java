/*
 * Copyright (C) 2017 Cloud Technology Partners
 * All rights reserved
 * uri.shatil@cloudtp.com
 */

package typed.api.literal;


/**
 *  Examples:
 *  <<NAME_MARKER
 *       hello
 *       world
 *   NAME_MARKER
 * */


public interface MultilineStringLiteralTree extends ValueTree {
    /**
     * Return marker restricting multiline string.
     * For example: <<FOO conetent of multiline strig FOO
     * In this case method returns FOO.
     */
    String marker();

    String value();
}
