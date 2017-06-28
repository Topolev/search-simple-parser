package typed;


import com.sonar.sslr.api.typed.ActionParser;
import typed.api.Tree;
import typed.api.literal.BooleanLiteralTree;
import typed.api.literal.MultilineStringLiteralTree;
import typed.api.literal.NumberLiteralTree;
import typed.api.literal.StringLiteralTree;

import java.nio.charset.StandardCharsets;

import static org.fest.assertions.Assertions.assertThat;

public class TestUtils {


    public static ActionParser<Tree> createParser(JsonLexer root) {
        return new ActionParser<>(
                StandardCharsets.UTF_8,
                JsonLexer.createGrammarBuilder(root),
                typed.JsonGrammar.class,
                new TreeFactory(),
                new JsonNodeBuilder(),
                root);
    }

    public static void checkBoolenLiteral(String input, boolean expectedValue){
        ActionParser<Tree> parser = createParser(JsonLexer.BOOLEAN_LITERAL);
        Tree booleanLiteral = parser.parse(input);
        assertThat(booleanLiteral.is(Tree.Kind.BOOLEAN_LITERAL)).isTrue();
        assertThat(((BooleanLiteralTree) booleanLiteral).value()).isEqualTo(expectedValue);
    }

    public static void checkNumberLiteral(String input, Float expectedValue){
        ActionParser<Tree> parser = createParser(JsonLexer.NUMBER_LITERAL);
        Tree numberLiteral = parser.parse(input);
        assertThat(numberLiteral.is(Tree.Kind.NUMBER_LITERAL)).isTrue();
        assertThat(((NumberLiteralTree)numberLiteral).value()).isEqualTo(expectedValue);
    }

    public static void checkStringLiteral(String input, String expectedValue){
        ActionParser<Tree> parser = createParser(JsonLexer.STRING_LITERAL);
        Tree stringLiteral = parser.parse(input);

        assertThat(stringLiteral.is(Tree.Kind.STRING_LITERAL)).isTrue();
        assertThat(((StringLiteralTree)stringLiteral).value()).isEqualTo(expectedValue);
    }

    public static void checkMultilineStringLiteral(String input, String expectedMarker, String expectedString){
        ActionParser<Tree> parser = createParser(JsonLexer.MULTILINE_STRING_LITERAL);
        Tree stringLiteral = parser.parse(input);

        assertThat(stringLiteral.is(Tree.Kind.MULTILINE_STRING_LITERAL)).isTrue();
        assertThat(((MultilineStringLiteralTree)stringLiteral).marker()).isEqualTo(expectedMarker);
        assertThat(((MultilineStringLiteralTree)stringLiteral).value()).isEqualTo(expectedString);
    }
}
