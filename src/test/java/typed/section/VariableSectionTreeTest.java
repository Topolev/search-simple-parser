package typed.section;


import com.sonar.sslr.api.typed.ActionParser;
import org.junit.Test;
import typed.JsonLexer;
import typed.api.Tree;
import typed.api.section.VariableSectionTree;

import static org.fest.assertions.Assertions.assertThat;

import static typed.TestUtils.createParser;

public class VariableSectionTreeTest {

    @Test
    public void variableSection() {
        ActionParser<Tree> parser = createParser(JsonLexer.PROPERTY);
        VariableSectionTree variableSection = (VariableSectionTree) parser.parse(
                "variable \"aws\" {" +
                        "type = \"string\"\n" +
                        "default = {}\n" +
                        "description = \"description\""+
                        "}");

        assertThat(variableSection.is(Tree.Kind.VARIABLE_SECTION)).isTrue();
        assertThat(variableSection.name()).isEqualTo("aws");
        assertThat(variableSection.type()).isEqualTo("string");

        assertThat(variableSection.defaultValue()).isNotNull();
        assertThat(variableSection.defaultValue().is(Tree.Kind.OBJECT_LITERAL)).isTrue();

        assertThat(variableSection.description()).isNotNull();
        assertThat(variableSection.description().is(Tree.Kind.STRING_LITERAL)).isTrue();
    }

    @Test
    public void emptyVariableSection() {
        ActionParser<Tree> parser = createParser(JsonLexer.PROPERTY);
        VariableSectionTree variableSection = (VariableSectionTree) parser.parse(
                "variable \"aws\" {}");

        assertThat(variableSection.is(Tree.Kind.VARIABLE_SECTION)).isTrue();
        assertThat(variableSection.name()).isEqualTo("aws");

        assertThat(variableSection.type()).isNull();
        assertThat(variableSection.defaultValue()).isNull();
        assertThat(variableSection.description()).isNull();
    }

}
