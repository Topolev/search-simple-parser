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

import com.sonar.sslr.api.typed.ActionParser;
import org.junit.Test;
import typed.api.PropertyTree;
import typed.api.Tree;
import typed.api.literal.BooleanLiteralTree;
import typed.api.literal.ListLiteralTree;
import typed.api.literal.NumberLiteralTree;
import typed.api.literal.ObjectLiteralTree;
import typed.api.literal.StringLiteralTree;
import typed.api.literal.ValueTree;
import typed.impl.literal.SyntaxList;

import static org.fest.assertions.Assertions.assertThat;
import static typed.TestUtils.checkBoolenLiteral;
import static typed.TestUtils.checkNumberLiteral;
import static typed.TestUtils.checkStringLiteral;
import static typed.TestUtils.checkMultilineStringLiteral;

import static typed.TestUtils.createParser;


public class JsonGrammarTest {

    /*private static ActionParser<Tree> parser = new ActionParser<>(
        StandardCharsets.UTF_8,
        JsonLexer.createGrammarBuilder(),
        typed.JsonGrammar.class,
        new TreeFactory(),
        new JsonNodeBuilder(),
        JsonLexer.BOOLEAN_LITERAL);*/
    private static String comments = " /*\n multiline comment \n*/\n     # line comment \n";

    @Test
    public void booleanLiteral() {
        checkBoolenLiteral("true", true);
        checkBoolenLiteral("false", false);

        // include comments
        checkBoolenLiteral("true" + comments, true);
        checkBoolenLiteral("false" + comments, false);
    }

    @Test
    public void numberLiteral() {
        // Decimal
        checkNumberLiteral("1234567890", 1234567890f);
        checkNumberLiteral("-1234567890", -1234567890f);

        // Decimal in science notation
        checkNumberLiteral("1E2", 100f);
        checkNumberLiteral("1e2", 100f);
        checkNumberLiteral("1e+2", 100f);
        checkNumberLiteral("1e-2", 0.01f);
        checkNumberLiteral("0.0123456789", 0.0123456789f);

        // Hex
        checkNumberLiteral("0xff", (float) 0xff);

        // Oct
        checkNumberLiteral("077", (float) 077);

        //include comments
        checkNumberLiteral("077" + comments, (float) 077);
        checkNumberLiteral("1E2" + comments, 100f);
        checkNumberLiteral("0.0123456789" + comments, 0.0123456789f);

    }

    String str = "asd\\";

    @Test
    public void stringLiteral() {
        checkStringLiteral("\"test\"","test");
        checkStringLiteral("\"\"", "");
        checkStringLiteral("\"\\\"\"","\\\"");
        checkStringLiteral("\"\\\\\"", "\\\\");
        checkStringLiteral("\"\\/\"", "\\/");
        checkStringLiteral("\"\\b\"", "\\b");
        checkStringLiteral("\"\\f\"","\\f");
        checkStringLiteral("\"\\n\"", "\\n");
        checkStringLiteral("\"\\r\"", "\\r");
        checkStringLiteral("\"\\t\"","\\t");
        checkStringLiteral("\"\\uFFFF\"", "\\uFFFF");
        checkStringLiteral("\"string\"", "string");
        checkStringLiteral("\"string ${\"test\" as \"}\"", "string ${\"test\" as \"}");

        //include comments
        checkStringLiteral("\"test\"" + comments, "test");
        checkStringLiteral("\"string ${\"test\" as \"}\"" + comments, "string ${\"test\" as \"}");
        checkStringLiteral("\"\\\"\"" + comments,"\\\"");
    }

    @Test
    public void multilineStringLiteral() {
        checkMultilineStringLiteral("<<EOF test test EOF", "EOF", " test test ");
        checkMultilineStringLiteral("<<EOF test \n \t \r \f test EOF", "EOF", " test \n \t \r \f test ");
        checkMultilineStringLiteral("<<EOF test \n \t \r \f test EOF EOF", "EOF", " test \n \t \r \f test ");
    }

    @Test
    public void valueTree() {
        ActionParser<Tree> parser = createParser(JsonLexer.VALUE);

        Tree numberLiteral = parser.parse("123456");
        assertThat(numberLiteral.is(Tree.Kind.NUMBER_LITERAL)).isTrue();
        assertThat(((NumberLiteralTree) numberLiteral).value()).isEqualTo(123456);

        Tree stringLiteral = parser.parse("\"test\"");
        assertThat(stringLiteral.is(Tree.Kind.STRING_LITERAL)).isTrue();
        assertThat(((StringLiteralTree) stringLiteral).value()).isEqualTo("test");

        Tree booleanLiteral = parser.parse("true");
        assertThat(booleanLiteral.is(Tree.Kind.BOOLEAN_LITERAL)).isTrue();
        assertThat(((BooleanLiteralTree) booleanLiteral).value()).isTrue();

    }

    @Test
    public void valueListLiteral() {
        ActionParser<Tree> parser = createParser(JsonLexer.VALUE_LIST);

        SyntaxList valueList = (SyntaxList) parser.parse("12, \"string string string\", true");

        assertThat(valueList.element().is(Tree.Kind.NUMBER_LITERAL)).isTrue();
        assertThat(((NumberLiteralTree) valueList.element()).value()).isEqualTo(12);

        valueList = valueList.next();
        assertThat(valueList.element().is(Tree.Kind.STRING_LITERAL)).isTrue();
        assertThat(((StringLiteralTree) valueList.element()).value()).isEqualTo("string string string");

        valueList = valueList.next();
        assertThat(valueList.element().is(Tree.Kind.BOOLEAN_LITERAL)).isTrue();
        assertThat(((BooleanLiteralTree) valueList.element()).value()).isTrue();
    }

    @Test
    public void listLiteral() {
        ActionParser<Tree> parser = createParser(JsonLexer.LIST_LITERAL);

        Tree listLiteral = parser.parse("[12, \"string\", true]");
        assertThat(listLiteral.is(Tree.Kind.LIST_LITERAL)).isTrue();

        SyntaxList<ValueTree> valueList = ((ListLiteralTree) listLiteral).values();
        assertThat(valueList.element().is(Tree.Kind.NUMBER_LITERAL)).isTrue();
        assertThat(((NumberLiteralTree) valueList.element()).value()).isEqualTo(12);

        valueList = valueList.next();
        assertThat(valueList.element().is(Tree.Kind.STRING_LITERAL)).isTrue();
        assertThat(((StringLiteralTree) valueList.element()).value()).isEqualTo("string");

        valueList = valueList.next();
        assertThat(valueList.element().is(Tree.Kind.BOOLEAN_LITERAL)).isTrue();
        assertThat(((BooleanLiteralTree) valueList.element()).value()).isTrue();

    }


    @Test
    public void property() {
        ActionParser<Tree> parser = createParser(JsonLexer.PROPERTY);
        Tree property = parser.parse("test = 12");

        assertThat(property.is(Tree.Kind.PROPERTY)).isTrue();

        Tree value = ((PropertyTree) property).value();
        assertThat(value.is(Tree.Kind.NUMBER_LITERAL)).isTrue();
        assertThat(((NumberLiteralTree) value).value()).isEqualTo(12);

        Tree property2 = parser.parse("key \"object1\" \"object2\" {key3 = 14  key4=2}");
        assertThat(property2.is(Tree.Kind.PROPERTY)).isTrue();
        Tree value2 = ((PropertyTree) property2).value();
        assertThat(value2.is(Tree.Kind.OBJECT_LITERAL)).isTrue();
        assertThat(((ObjectLiteralTree) value2).properties().size()).isEqualTo(2);

    }

    @Test
    public void propertyList() {
        ActionParser<Tree> parser = createParser(JsonLexer.PROPERTY_LIST);
        SyntaxList properties = (SyntaxList) parser.parse("key1 = 12 \n key2 = {}");

        assertThat(properties.element().is(Tree.Kind.PROPERTY)).isTrue();
        Tree value = ((PropertyTree) properties.element()).value();
        assertThat(value.is(Tree.Kind.NUMBER_LITERAL)).isTrue();
        assertThat(((NumberLiteralTree) value).value()).isEqualTo(12);

        properties = properties.next();
        assertThat(properties.element().is(Tree.Kind.PROPERTY)).isTrue();
        value = ((PropertyTree) properties.element()).value();
        assertThat(value.is(Tree.Kind.OBJECT_LITERAL)).isTrue();
    }

    @Test
    public void objectLiteral() {
        ActionParser<Tree> parser = createParser(JsonLexer.OBJECT_LITERAL);
        ObjectLiteralTree object = (ObjectLiteralTree) parser.parse("{test2 = true}");

        assertThat(object.is(Tree.Kind.OBJECT_LITERAL)).isTrue();
        assertThat(object.properties().size()).isEqualTo(1);

        assertThat(object.properties().get(0).is(Tree.Kind.PROPERTY)).isTrue();

    }







  /*@Test
  public void number() throws Exception {
    assertLiteral("1234567890");
    assertLiteral("-1234567890");
    assertLiteral("0");
    assertLiteral("0.0123456789");
    assertLiteral("1E2");
    assertLiteral("1e2");
    assertLiteral("1E+2");
    assertLiteral("1E-2");
  }

  @Test(expected = RecognitionException.class)
  public void number_not_parsed() throws Exception {
    parser.parse("[ 01 ]");
  }

  @Test
  public void string() throws Exception {
    assertLiteral("\"\"");
    assertLiteral("\"\\\"\"");
    assertLiteral("\"\\\\\"");
    assertLiteral("\"\\/\"");
    assertLiteral("\"\\b\"");
    assertLiteral("\"\\f\"");
    assertLiteral("\"\\n\"");
    assertLiteral("\"\\r\"");
    assertLiteral("\"\\t\"");
    assertLiteral("\"\\uFFFF\"");
    assertLiteral("\"string\"");
  }

  @Test
  public void value() throws Exception {
    assertValue("\"string\"", LiteralTree.class);
    assertValue("{}", ObjectTree.class);
    assertValue("[]", ArrayTree.class);
    assertValue("42", LiteralTree.class);
    assertValue("true", BuiltInValueTree.class);
    assertValue("false", BuiltInValueTree.class);
    assertValue("null", BuiltInValueTree.class);
  }

  @Test
  public void object() throws Exception {
    ObjectTree tree = (ObjectTree) ((JsonTree) parser.parse("{}")).arrayOrObject();
    assertThat(tree.openCurlyBraceToken().value()).isEqualTo("{");
    assertThat(tree.closeCurlyBraceToken().value()).isEqualTo("}");
    assertThat(tree.pairs()).isNull();

    tree = (ObjectTree) ((JsonTree) parser.parse("{ \"string\" : true }")).arrayOrObject();
    assertThat(tree.pairs()).isNotNull();
    assertThat(tree.pairs().next()).isNull();
    PairTree pair = tree.pairs().element();
    assertThat(pair.name().token().value()).isEqualTo("\"string\"");
    assertThat(((BuiltInValueTree) pair.value()).token().value()).isEqualTo("true");
    assertThat(pair.colonToken().value()).isEqualTo(":");

    parser.parse("{ \"string\" : true, \"string\" : false }");
  }

  @Test
  public void array() {
    ArrayTree tree = (ArrayTree) ((JsonTree) parser.parse("[]")).arrayOrObject();
    assertThat(tree.openBracketToken().value()).isEqualTo("[");
    assertThat(tree.closeBracketToken().value()).isEqualTo("]");
    assertThat(tree.values()).isNull();

    tree =(ArrayTree) ((JsonTree) parser.parse("[ true, false ]")).arrayOrObject();
    assertThat(tree.values()).isNotNull();
    assertThat(tree.values().element()).isInstanceOf(BuiltInValueTree.class);
    assertThat(tree.values().commaToken().value()).isEqualTo(",");
    assertThat(tree.values().next().element()).isInstanceOf(BuiltInValueTree.class);
  }

  @Test
  public void json() {
    Tree tree = parser.parse("{}");
    assertThat(tree).isInstanceOf(JsonTree.class);
    assertThat(((JsonTree) tree).arrayOrObject()).isInstanceOf(ObjectTree.class);

    tree = parser.parse("[]");
    assertThat(tree).isInstanceOf(JsonTree.class);
    assertThat(((JsonTree) tree).arrayOrObject()).isInstanceOf(ArrayTree.class);
  }

  private void assertValue(String code, Class c) {
    JsonTree tree = (JsonTree) parser.parse("[ " + code + " ]");
    ValueTree value = ((ArrayTree) tree.arrayOrObject()).values().element();
    assertThat(value).isInstanceOf(c);
  }

  private void assertLiteral(String code) {
    JsonTree tree = (JsonTree) parser.parse("[ " + code + " ]");
    ValueTree value = ((ArrayTree) tree.arrayOrObject()).values().element();
    assertThat(value).isInstanceOf(LiteralTree.class);
    assertThat(((LiteralTree) value).token().value()).isEqualTo(code);
  }*/

}
