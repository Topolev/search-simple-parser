package typed.section;


import com.sonar.sslr.api.typed.ActionParser;
import org.junit.Test;
import typed.JsonLexer;
import typed.api.Tree;
import typed.api.section.DataSourcesSectionTree;
import typed.api.section.VariableSectionTree;

import static org.fest.assertions.Assertions.assertThat;
import static typed.TestUtils.createParser;

public class DataSourceSectionTreeTest {

    @Test
    public void variableSection() {
        ActionParser<Tree> parser = createParser(JsonLexer.PROPERTY);
        DataSourcesSectionTree dataSourcesSection = (DataSourcesSectionTree) parser.parse(
                "data \"aws_ami\" \"web\" {" +
                        "count = 12\n" +
                        "depends_on = [\"aws_instance.leader\", \"module.vpc\"]\n" +
                        "provider = \"aws.west\"\n" +
                        "connection = {key1= \"value1\"\nkey2= \"value2\"}\n"+
                        "provisioner \"aws\" {key1=\"value1\"\nkey2=12\nkey3=true}"+
                        "}");

        assertThat(dataSourcesSection.is(Tree.Kind.DATA_SOURCES_SECTION)).isTrue();
        assertThat(dataSourcesSection.metaParameters().size()).isEqualTo(5);
        assertThat(dataSourcesSection.type()).isEqualTo("aws_ami");
        assertThat(dataSourcesSection.name()).isEqualTo("web");

        assertThat(dataSourcesSection.count().is(Tree.Kind.NUMBER_LITERAL)).isTrue();
        assertThat(dataSourcesSection.dependsOn().is(Tree.Kind.LIST_LITERAL)).isTrue();
        assertThat(dataSourcesSection.provider().is(Tree.Kind.STRING_LITERAL)).isTrue();
        assertThat(dataSourcesSection.connection().is(Tree.Kind.OBJECT_LITERAL)).isTrue();
        assertThat(dataSourcesSection.provisioner().is(Tree.Kind.OBJECT_LITERAL)).isTrue();
    }

    @Test
    public void emptyDataSourceSection() {
        ActionParser<Tree> parser = createParser(JsonLexer.PROPERTY);
        DataSourcesSectionTree dataSourcesSection = (DataSourcesSectionTree) parser.parse(
                "data \"aws_ami\" \"web\" {} ");

        assertThat(dataSourcesSection.is(Tree.Kind.DATA_SOURCES_SECTION)).isTrue();
        assertThat(dataSourcesSection.metaParameters().size()).isEqualTo(0);
        assertThat(dataSourcesSection.type()).isEqualTo("aws_ami");
        assertThat(dataSourcesSection.name()).isEqualTo("web");

        assertThat(dataSourcesSection.count()).isNull();
        assertThat(dataSourcesSection.dependsOn()).isNull();
        assertThat(dataSourcesSection.provider()).isNull();
        assertThat(dataSourcesSection.connection()).isNull();
        assertThat(dataSourcesSection.provisioner()).isNull();
    }

}
