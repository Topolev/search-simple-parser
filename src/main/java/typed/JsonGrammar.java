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


import com.sonar.sslr.api.typed.GrammarBuilder;
import typed.api.PropertyTree;
import typed.api.literal.BooleanLiteralTree;
import typed.api.literal.ListLiteralTree;
import typed.api.literal.MultilineStringLiteralTree;
import typed.api.literal.NumberLiteralTree;
import typed.api.literal.ObjectLiteralTree;
import typed.api.literal.StringLiteralTree;
import typed.impl.lexical.InternalSyntaxToken;
import typed.api.literal.ValueTree;
import typed.impl.literal.SyntaxList;

public class JsonGrammar {

    private final GrammarBuilder<InternalSyntaxToken> b;
    private final TreeFactory f;

    public JsonGrammar(GrammarBuilder<InternalSyntaxToken> b, TreeFactory f) {
        this.b = b;
        this.f = f;
    }

  /*public SyntaxList<PropertyTree> ROOT() {
    return b.<JsonTree>nonterminal(JsonLexer.JSON).is(
      f.json(
        b.firstOf(
          ARRAY(),
          OBJECT()),
        b.token(JsonLexer.EOF)));
  }*/

    public ObjectLiteralTree OBJECT_LITERAL() {
        return b.<ObjectLiteralTree>nonterminal(JsonLexer.OBJECT_LITERAL).is(
                f.objectLiteral(
                        b.token(JsonLexer.LCURLYBRACE),
                        b.optional(PAIR_LIST()),
                        b.token(JsonLexer.RCURLYBRACE)));
    }

    public SyntaxList<PropertyTree> PAIR_LIST() {
        return b.<SyntaxList<PropertyTree>>nonterminal(JsonLexer.PROPERTY_LIST).is(
                b.firstOf(
                        f.pairList(PROPERTY(), b.token(JsonLexer.WHITESPACE), PAIR_LIST()),
                        f.pairList(PROPERTY())));
    }


    public PropertyTree PROPERTY() {
        return b.<PropertyTree>nonterminal(JsonLexer.PROPERTY).is(
                b.firstOf(
                        PAIR_WITH_VALUE_AS_NON_OBJECT_LITERAL(),
                        PAIR_WITH_VALUE_AS_OBJECT_LITERAL()
                ));

    }


    public PropertyTree PAIR_WITH_VALUE_AS_NON_OBJECT_LITERAL() {
        return b.<PropertyTree>nonterminal(JsonLexer.PAIR_WITH_VALUE_AS_NON_OBJECT_LITERAL).is(
                f.pair(b.token(JsonLexer.WORD), b.token(JsonLexer.EQUAL), b.firstOf(VALUE(), OBJECT_LITERAL())));
    }

    public PropertyTree PAIR_WITH_VALUE_AS_OBJECT_LITERAL() {
        return b.<PropertyTree>nonterminal(JsonLexer.PAIR_WITH_VALUE_AS_OBJECT_LITERAL).is(
                f.pair(b.token(JsonLexer.WORD), b.zeroOrMore(STRING_LITERAL()), OBJECT_LITERAL()));
    }

    public ListLiteralTree LIST_LITERAL() {
        return b.<ListLiteralTree>nonterminal(JsonLexer.LIST_LITERAL).is(
                f.array(
                        b.token(JsonLexer.LBRACKET),
                        b.optional(VALUE_LIST()),
                        b.token(JsonLexer.RBRACKET)));
    }

    public SyntaxList<ValueTree> VALUE_LIST() {
        return b.<SyntaxList<ValueTree>>nonterminal(JsonLexer.VALUE_LIST).is(
                b.firstOf(
                        f.valueList(b.firstOf(VALUE(), OBJECT_LITERAL()), b.token(JsonLexer.COMMA), VALUE_LIST()),
                        f.valueList(b.firstOf(VALUE(), OBJECT_LITERAL()))));
    }


    public ValueTree VALUE() {
        return b.<ValueTree>nonterminal(JsonLexer.VALUE).is(
                b.firstOf(
                        STRING_LITERAL(),
                        MULTILINE_STRING_LITERAL(),
                        BOOLEAN_LITERAL(),
                        NUMBER_LITERAL(),
                        LIST_LITERAL()
                        //OBJECT_LITERAL()
                ));
    }


    public BooleanLiteralTree BOOLEAN_LITERAL() {
        return b.<BooleanLiteralTree>nonterminal(JsonLexer.BOOLEAN_LITERAL).is(
                f.booleanLiteral(b.firstOf(b.token(JsonLexer.TRUE), b.token(JsonLexer.FALSE)))
        );
    }

    public NumberLiteralTree NUMBER_LITERAL() {
        return b.<NumberLiteralTree>nonterminal(JsonLexer.NUMBER_LITERAL).is(
                f.numberLiteralTree(b.token(JsonLexer.NUMBER))
        );
    }

    public StringLiteralTree STRING_LITERAL() {
        return b.<StringLiteralTree>nonterminal(JsonLexer.STRING_LITERAL).is(
                f.stringLiteralTree(
                        b.token(JsonLexer.STRING)
                )
        );
    }

    public MultilineStringLiteralTree MULTILINE_STRING_LITERAL() {
        return b.<MultilineStringLiteralTree>nonterminal(JsonLexer.MULTILINE_STRING_LITERAL).is(
                f.multilineStringLiteralTree(b.token(JsonLexer.MULTILINE_STRING))
        );
    }

    /*public StringLiteralTree WORD_LITERAL() {
        return b.<StringLiteralTree>nonterminal(JsonLexer.WORD_LITERAL).is(
                f.stringLiteralTree(b.token(JsonLexer.WORD))
        );
    }*/
}
