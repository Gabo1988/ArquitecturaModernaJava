package com.project.pedidos;

import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.dependencies.SlicesRuleDefinition;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

public class MyArchitectureTest {
    @Test
    public void noCyclicDependenciesBetweenPackages() {
        ArchRule rule = SlicesRuleDefinition.slices()
            .matching("com.project.(*)..")
            .should().beFreeOfCycles();
        rule.check(new ClassFileImporter().importPackages("com.project"));
    }

    @Test
    public void apiShouldOnlyContainControllers() {
        ArchRule rule = classes()
                .that().resideInAPackage("com.project.api.controller..")
                .should().haveSimpleNameEndingWith("Controller");
        rule.check(new ClassFileImporter().importPackages("com.project"));
    }

    @Test
    public void apiShouldOnlyDependOnServiceInterfaces() {
        ArchRule rule = classes()
                .that().resideInAPackage("com.project.api..")
                .should().onlyDependOnClassesThat()
                .resideInAnyPackage("com.project.application.service..")
                .andShould().onlyDependOnClassesThat()
                .areInterfaces();
        rule.check(new ClassFileImporter().importPackages("com.project"));
    }

    @Test
    public void apiShouldNotDependDirectlyOnPersistence() {
        ArchRule rule = noClasses()
                .that().resideInAPackage("com.project.api..")
                .should().dependOnClassesThat().resideInAPackage("com.project.infrastructure.persistence..");
        rule.check(new ClassFileImporter().importPackages("com.project"));
    }

    @Test
    public void apiShouldNotDependDirectlyOnClient() {
        ArchRule rule = noClasses()
                .that().resideInAPackage("com.project.api..")
                .should().dependOnClassesThat().resideInAPackage("com.project.infrastructure.client..");
        rule.check(new ClassFileImporter().importPackages("com.project"));
    }

    @Test
    public void clientShouldNotDependOnApplicationOrDomain() {
        ArchRule rule = noClasses()
                .that().resideInAPackage("com.project.infrastructure.client..")
                .should().dependOnClassesThat().resideInAPackage("com.project.application..")
                .or().resideInAPackage("com.project.domain..");
        rule.check(new ClassFileImporter().importPackages("com.project"));
    }

    @Test
    public void repositoryShouldNotDependOnApplicationOrDomain() {
        ArchRule rule = noClasses()
                .that().resideInAPackage("com.project.infrastructure.persistence..")
                .should().dependOnClassesThat().resideInAPackage("com.project.application..")
                .or().resideInAPackage("com.project.domain..");
        rule.check(new ClassFileImporter().importPackages("com.project"));
    }

    @Test
    public void persistenceShouldOnlyAccessSPQueryAndCommandClasses() {
        ArchRule rule = classes()
                .that().resideInAPackage("com.project.infrastructure.persistence..")
                .should().onlyAccessClassesThat()
                .resideInAnyPackage("com.project.infrastructure.persistence..")
                .andShould().haveSimpleNameEndingWith("SP")
                .orShould().haveSimpleNameEndingWith("Query")
                .orShould().haveSimpleNameEndingWith("Command");
        rule.check(new ClassFileImporter().importPackages("com.project"));
    }

    @Test
    public void dtoShouldNotContainClassesWithRqOrRsSuffix() {
        ArchRule rule = noClasses()
                .that().resideInAPackage("com.project.api.dto..")
                .should().haveSimpleNameEndingWith("Rq")
                .orShould().haveSimpleNameEndingWith("Rs");
        rule.check(new ClassFileImporter().importPackages("com.project"));
    }

    @Test
    public void modelShouldNotContainClassesWithRecordOrResultSuffix() {
        ArchRule rule = noClasses()
                .that().resideInAPackage("com.project.domain.model..")
                .should().haveSimpleNameEndingWith("Record")
                .orShould().haveSimpleNameEndingWith("Result");
        rule.check(new ClassFileImporter().importPackages("com.project"));
    }

    @Test
    public void recordAndResultClassesInDtoShouldOnlyAccessClassesInDto() {
        ArchRule rule = classes()
                .that().resideInAPackage("com.project.api.dto..")
                .and().haveSimpleNameEndingWith("Record")
                .or().haveSimpleNameEndingWith("Result")
                .should().onlyAccessClassesThat()
                .resideInAPackage("com.project.api.dto..");
        rule.check(new ClassFileImporter().importPackages("com.project"));
    }

    @Test
    public void rqAndRsClassesInModelShouldOnlyAccessClassesInModel() {
        ArchRule rule = classes()
                .that().resideInAPackage("com.project.domain.model..")
                .and().haveSimpleNameEndingWith("Rq")
                .or().haveSimpleNameEndingWith("Rs")
                .should().onlyAccessClassesThat()
                .resideInAnyPackage("com.project.domain.model..");
        rule.check(new ClassFileImporter().importPackages("com.project"));
    }

    @Test
    public void servicesAndBusinessShouldNotDependOnControllers() {
        ArchRule rule = noClasses()
                .that().resideInAPackage("com.project.application..")
                .or().resideInAPackage("com.project.application.service..")
                .should().dependOnClassesThat()
                .resideInAPackage("com.project.api.controller..");
        rule.check(new ClassFileImporter().importPackages("com.project"));
    }

    @Test
    public void testsShouldBeInTestPackage() {
        ArchRule rule = classes()
                .that().resideInAPackage("com.project..")
                .should().resideInAPackage("com.project.test..");
        rule.check(new ClassFileImporter().importPackages("com.project"));
    }

    @Test
    public void servicesShouldHaveSeparateInterfacesAndImplementations() {
        ArchRule rule = classes()
                .that().resideInAPackage("com.project.application.service..")
                .and().areInterfaces()
                .should().onlyDependOnClassesThat()
                .resideInAnyPackage("com.project.application.usecase..");
        rule.check(new ClassFileImporter().importPackages("com.project"));
    }

    @Test
    public void domainClassesShouldFollowNamingConventions() {
        ArchRule rule = classes()
                .that().resideInAPackage("com.project.domain.model..")
                .should().haveSimpleNameNotContaining("Dto")
                .andShould().haveSimpleNameNotContaining("Entity");
        rule.check(new ClassFileImporter().importPackages("com.project"));
    }
}
