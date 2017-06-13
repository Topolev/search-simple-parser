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
package typed.api;

import typed.api.lexical.SyntaxToken;
import typed.api.literal.AlternativeListLiteralTree;
import typed.api.literal.BooleanLiteralTree;
import typed.api.literal.ListLiteralTree;
import typed.api.literal.NumberLiteralTree;
import typed.api.literal.MultilineStringLiteralTree;
import typed.api.literal.ObjectLiteralTree;
import typed.api.literal.StringLiteralTree;
import typed.api.section.DataSourcesSectionTree;
import typed.api.section.ModuleSectionTree;
import typed.api.section.OutputSectionTree;
import typed.api.section.ProviderSectionTree;
import typed.api.section.ResourceSectionTree;
import typed.api.section.TerraformConfigurationSectionTree;
import typed.api.section.TerraformEnterpriseConfigurationSectionTree;
import typed.api.section.VariableSectionTree;

public interface Tree {


    boolean is(Kind... kind);

    Tree parent();

    Kind kind();

    enum Kind {

        TOKEN(SyntaxToken.class),



        // Hierarchy of PropertyTree values
        BOOLEAN_LITERAL(BooleanLiteralTree.class),
        NUMBER_LITERAL(NumberLiteralTree.class),
        MULTILINE_STRING_LITERAL(MultilineStringLiteralTree.class),
        STRING_LITERAL(StringLiteralTree.class),
        LIST_LITERAL(ListLiteralTree.class),
        OBJECT_LITERAL(ObjectLiteralTree.class),
        ALTERNATIVE_LIST_LITERAL(AlternativeListLiteralTree.class),



        // Hierarchy of PropertyTree
        PROPERTY(PropertyTree.class),
        DATA_SOURCES_SECTION(DataSourcesSectionTree.class),
        MODULE_SECTION(ModuleSectionTree.class),
        OUTPUT_SECTION(OutputSectionTree.class),
        PROVIDER_SECTION(ProviderSectionTree.class),
        RESOURCE_SECTION(ResourceSectionTree.class),
        TERRAFORM_CONFIGURATION_SECTION(TerraformConfigurationSectionTree.class),
        TERRAFORM_ENTERPRISE_CONFIGURATION_SECTION(TerraformEnterpriseConfigurationSectionTree.class),
        VARIABLE_SECTION(VariableSectionTree.class),

        ;


        final Class<? extends Tree> associatedInterface;

        Kind(Class<? extends Tree> associatedInterface) {
            this.associatedInterface = associatedInterface;
        }

        public Class<? extends Tree> getAssociatedInterface() {
            return this.associatedInterface;
        }
    }
}




