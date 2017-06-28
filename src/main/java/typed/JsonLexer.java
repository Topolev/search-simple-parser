/*
 * SonarSource Language Recognizer
 * Copyright (C) 2010-2017 SonarSource SA
 * mailto:info AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package typed;

import com.sonar.sslr.api.GenericTokenType;
import org.sonar.sslr.grammar.GrammarRuleKey;
import org.sonar.sslr.grammar.LexerlessGrammarBuilder;

public enum JsonLexer implements GrammarRuleKey {

    NUMBER_LITERAL,
    BOOLEAN_LITERAL,
    STRING_LITERAL,
    MULTILINE_STRING_LITERAL,
    LIST_LITERAL,
    OBJECT_LITERAL,

    PROPERTY,
    PAIR_WITH_VALUE_AS_OBJECT_LITERAL,
    PAIR_WITH_VALUE_AS_NON_OBJECT_LITERAL,


    PROPERTY_LIST,

    VALUE_LIST,
    WORD,

    VALUE,
    TRUE,
    FALSE,

    STRING,
    MULTILINE_STRING,
    NUMBER,

    LCURLYBRACE,
    RCURLYBRACE,
    LBRACKET,
    RBRACKET,
    COMMA,
    COLON,
    EQUAL,

    EOF,
    WHITESPACE,;

    public static LexerlessGrammarBuilder createGrammarBuilder(JsonLexer root) {
        LexerlessGrammarBuilder b = LexerlessGrammarBuilder.create();

        punctuator(b, LCURLYBRACE, "{");
        punctuator(b, RCURLYBRACE, "}");
        punctuator(b, LBRACKET, "[");
        punctuator(b, RBRACKET, "]");
        punctuator(b, COMMA, ",");
        punctuator(b, COLON, ":");
        punctuator(b, EQUAL, "=");

    /*b.rule(TRUE).is("true", WHITESPACE);
    b.rule(FALSE).is("false", WHITESPACE);
    b.rule(NULL).is("null", WHITESPACE);

    b.rule(WHITESPACE).is(b.regexp("[ \n\r\t\f]*+"));
    b.rule(NUMBER).is(b.regexp("-?+(0|[1-9][0-9]*+)(\\.[0-9]++)?+([eE][+-]?+[0-9]++)?+"), WHITESPACE);
    b.rule(STRING).is(b.regexp("\"([^\"\\\\]|\\\\([\"\\\\/bfnrt]|u[0-9a-fA-F]{4}))*+\""), WHITESPACE);

    b.rule(EOF).is(b.token(GenericTokenType.EOF, b.endOfInput()));*/

        b.rule(TRUE).is(b.skippedTrivia(WHITESPACE), "true", WHITESPACE);
        b.rule(FALSE).is(b.skippedTrivia(WHITESPACE), "false", WHITESPACE);

        b.rule(NUMBER).is(b.regexp(JsonRegExps.NUMERIC_LITERAL), WHITESPACE);
        b.rule(STRING).is(b.regexp(JsonRegExps.STRING_LITERAL), WHITESPACE);
        b.rule(MULTILINE_STRING).is(b.regexp(JsonRegExps.MULTILINE_STRING_LITERAL), WHITESPACE);

        b.rule(WORD).is(b.regexp(JsonRegExps.WORD_LITERAL), WHITESPACE);


        //b.rule(WHITESPACE).is(b.regexp("[ \n\r\t\f]*+"));
        b.rule(WHITESPACE).is(b.regexp("[ \\n\\t\\r\\f]*(?>(#[^\\n]*[ \\n]*)|/\\*([^\\*]|[\\r\\n]|(\\*+([^\\*/])))*\\*/[ \\n\\t\\r\\f]*)*+"));


        b.rule(EOF).is(b.token(GenericTokenType.EOF, b.endOfInput()));

        b.setRootRule(root);
        return b;
    }

    private static void punctuator(LexerlessGrammarBuilder b, GrammarRuleKey ruleKey, String value) {
        b.rule(ruleKey).is(value, WHITESPACE);
    }
}
