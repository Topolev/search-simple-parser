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

import com.sonar.sslr.api.typed.Optional;
import typed.api.PropertyTree;
import typed.api.literal.BooleanLiteralTree;
import typed.api.literal.ListLiteralTree;
import typed.api.literal.MultilineStringLiteralTree;
import typed.api.literal.NumberLiteralTree;
import typed.api.literal.ObjectLiteralTree;
import typed.api.literal.StringLiteralTree;
import typed.api.literal.ValueTree;
import typed.impl.PropertyTreeImpl;
import typed.impl.lexical.InternalSyntaxToken;
import typed.impl.literal.BooleanLiteralTreeImpl;
import typed.impl.literal.ListLiteralTreeImpl;
import typed.impl.literal.MultilineStringLiteralImpl;
import typed.impl.literal.NumberLiteralTreeImpl;
import typed.impl.literal.ObjectLiteralTreeImpl;
import typed.impl.literal.StringLiteralTreeImpl;
import typed.impl.literal.SyntaxList;
import typed.impl.section.VariableSectionTreeImpl;

import java.util.List;


public class TreeFactory {

  /*public JsonTree json(Tree arrayOrObject, InternalSyntaxToken eof) {
    return new JsonTreeImpl(arrayOrObject);
  }

 */

    private PropertyTree createPair(InternalSyntaxToken keyToken, List<StringLiteralTree> nestedObjects, ValueTree value) {
        String key = keyToken.value();
        switch (key) {
            case "variable":
                return new VariableSectionTreeImpl(keyToken, nestedObjects, value);
            default: return new PropertyTreeImpl(keyToken, nestedObjects, value);
        }
    }

    /* Pattern: key = value */
    public PropertyTree pair(InternalSyntaxToken key, List<StringLiteralTree> string, InternalSyntaxToken colonToken, ValueTree value) {
        return new PropertyTreeImpl(key, string, colonToken, value);
    }

    /* Pattern: key "nestedObject1" "nestedObject2" { } */
    public PropertyTree pair(InternalSyntaxToken key, List<StringLiteralTree> string, ValueTree value) {
        return new PropertyTreeImpl(key, string, value);
    }

    public ListLiteralTree array(InternalSyntaxToken openBracketToken, Optional<SyntaxList<ValueTree>> values, InternalSyntaxToken closeBracketToken) {
        return new ListLiteralTreeImpl(openBracketToken, values.orNull(), closeBracketToken);
    }

    public SyntaxList<ValueTree> valueList(ValueTree value) {
        return new SyntaxList<>(value, null, null);
    }

    public SyntaxList<ValueTree> valueList(ValueTree value, InternalSyntaxToken commaToken, SyntaxList<ValueTree> next) {
        return new SyntaxList<>(value, commaToken, next);
    }

    public BooleanLiteralTree booleanLiteral(InternalSyntaxToken syntaxToken) {
        return new BooleanLiteralTreeImpl(syntaxToken);
    }

    public NumberLiteralTree numberLiteralTree(InternalSyntaxToken syntaxToken) {
        return new NumberLiteralTreeImpl(syntaxToken);
    }

    public StringLiteralTree stringLiteralTree(InternalSyntaxToken syntaxToken) {

        return new StringLiteralTreeImpl(syntaxToken);
    }

    public MultilineStringLiteralTree multilineStringLiteralTree(InternalSyntaxToken syntaxToken) {
        return new MultilineStringLiteralImpl(syntaxToken);
    }

    public SyntaxList<PropertyTree> pairList(PropertyTree pair) {
        return new SyntaxList<>(pair, null, null);
    }


    public SyntaxList<PropertyTree> pairList(PropertyTree pair, InternalSyntaxToken commaToken, SyntaxList<PropertyTree> next) {
        return new SyntaxList<>(pair, commaToken, next);
    }

    public ObjectLiteralTree objectLiteral(InternalSyntaxToken openCurlyBraceToken, Optional<SyntaxList<PropertyTree>> pairs, InternalSyntaxToken closeCurlyBraceToken) {
        return new ObjectLiteralTreeImpl(openCurlyBraceToken, pairs.orNull(), closeCurlyBraceToken);
    }
}
