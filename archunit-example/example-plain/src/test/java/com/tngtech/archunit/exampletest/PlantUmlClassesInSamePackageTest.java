package com.tngtech.archunit.exampletest;

import java.net.URL;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.example.uml.ObjectA;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.plantuml.PlantUmlArchCondition.Configurations.consideringOnlyDependenciesInAnyPackage;
import static com.tngtech.archunit.library.plantuml.PlantUmlArchCondition.adhereToPlantUmlDiagram;

@Category(Example.class)
public class PlantUmlClassesInSamePackageTest {
    private static final URL diagram = PlantUmlClassesInSamePackageTest.class.getResource("classes_in_same_package.puml");

    @Test
    public void classes_in_same_package_should_respect_their_dependencies() {
        JavaClasses classes = new ClassFileImporter().importPackagesOf(ObjectA.class);

        ArchRule rule = classes().should(adhereToPlantUmlDiagram(diagram, consideringOnlyDependenciesInAnyPackage("..uml..")));

        rule.check(classes);
    }
}
