package typed.section;


import com.sonar.sslr.api.typed.ActionParser;
import org.junit.Test;
import typed.JsonLexer;
import typed.api.Tree;
import typed.api.section.ModuleSectionTree;
import typed.api.section.VariableSectionTree;

import static org.fest.assertions.Assertions.assertThat;
import static typed.TestUtils.createParser;

public class ModuleSectionTreeTest {


    @Test
    public void moduleSection() {
        ActionParser<Tree> parser = createParser(JsonLexer.PROPERTY);
        ModuleSectionTree moduleSection = (ModuleSectionTree) parser.parse(
                "module \"consul\" {" +
                        "source = \"github.com/hashicorp/consul/terraform/aws\"\n" +
                        "servers =5" +
                        "}");

        assertThat(moduleSection.is(Tree.Kind.MODULE_SECTION)).isTrue();
        assertThat(moduleSection.metaParameters().size()).isEqualTo(2);

        assertThat(moduleSection.source().is(Tree.Kind.STRING_LITERAL)).isTrue();
    }

    @Test
    public void emptyModuleSection() {
        ActionParser<Tree> parser = createParser(JsonLexer.PROPERTY);
        ModuleSectionTree moduleSection = (ModuleSectionTree) parser.parse(
                "module \"consul\" {}");

        assertThat(moduleSection.is(Tree.Kind.MODULE_SECTION)).isTrue();
        assertThat(moduleSection.name()).isEqualTo("consul");

        assertThat(moduleSection.source()).isNull();
    }

}
