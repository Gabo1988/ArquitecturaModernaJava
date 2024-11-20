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
                .that().resideInAPackage("com.project.api..")
                .should().haveSimpleNameEndingWith("Controller");
        rule.check(new ClassFileImporter().importPackages("com.project"));
    }

    @Test
    public void apiShouldOnlyDependOnServiceInterfaces() {
        ArchRule rule = classes()
                .that().resideInAPackage("com.project.api..")
                .should().onlyDependOnClassesThat()
                .resideInAnyPackage("com.project.service..")
                .andShould().onlyDependOnClassesThat()
                .areInterfaces();
        rule.check(new ClassFileImporter().importPackages("com.project"));
    }

    @Test
    public void apiShouldNotDependDirectlyOnRepository() {
        ArchRule rule = noClasses()
                .that().resideInAPackage("com.project.api..")
                .should().dependOnClassesThat().resideInAPackage("com.project.repository..");
        rule.check(new ClassFileImporter().importPackages("com.project"));
    }

    @Test
    public void apiShouldNotDependDirectlyOnClient() {
        ArchRule rule = noClasses()
                .that().resideInAPackage("com.project.api..")
                .should().dependOnClassesThat().resideInAPackage("com.project.client..");
        rule.check(new ClassFileImporter().importPackages("com.project"));
    }

    @Test
    public void clientShouldNotDependOnBusiness() {
        ArchRule rule = noClasses()
                .that().resideInAPackage("com.project.client..")
                .should().dependOnClassesThat().resideInAPackage("com.project.business..");
        rule.check(new ClassFileImporter().importPackages("com.project"));
    }

    @Test
    public void repositoryShouldNotDependOnBusiness() {
        ArchRule rule = noClasses()
                .that().resideInAPackage("com.project.repository..")
                .should().dependOnClassesThat().resideInAPackage("com.project.business..");
        rule.check(new ClassFileImporter().importPackages("com.project"));
    }

    @Test
    public void repositoryShouldOnlyAccessSPQueryAndCommandClasses() {
        ArchRule rule = classes()
                .that().resideInAPackage("com.project.repository..")
                .should().onlyAccessClassesThat()
                .resideInAnyPackage("com.project.repository..")
                .andShould().haveSimpleNameEndingWith("SP")
                .orShould().haveSimpleNameEndingWith("Query")
                .orShould().haveSimpleNameEndingWith("Command");
        rule.check(new ClassFileImporter().importPackages("com.project"));
    }

    @Test
    public void dtoShouldNotContainClassesWithRqOrRsSuffix() {
        ArchRule rule = noClasses()
                .that().resideInAPackage("com.project.dto..")
                .should().haveSimpleNameEndingWith("Rq")
                .orShould().haveSimpleNameEndingWith("Rs");
        rule.check(new ClassFileImporter().importPackages("com.project"));
    }

    @Test
    public void modelShouldNotContainClassesWithRecordOrResultSuffix() {
        ArchRule rule = noClasses()
                .that().resideInAPackage("com.project.model..")
                .should().haveSimpleNameEndingWith("Record")
                .orShould().haveSimpleNameEndingWith("Result");
        rule.check(new ClassFileImporter().importPackages("com.project"));
    }

    @Test
    public void recordAndResultClassesInDtoShouldOnlyAccessClassesInDto() {
        ArchRule rule = classes()
                .that().resideInAPackage("com.project.dto..")
                .and().haveSimpleNameEndingWith("Record")
                .or().haveSimpleNameEndingWith("Result")
                .should().onlyAccessClassesThat()
                .resideInAPackage("com.project.dto..");
        rule.check(new ClassFileImporter().importPackages("com.project"));
    }

    @Test
    public void rqAndRsClassesInModelShouldOnlyAccessClassesInModel() {
        ArchRule rule = classes()
                .that().resideInAPackage("com.project.model..")
                .and().haveSimpleNameEndingWith("Rq")
                .or().haveSimpleNameEndingWith("Rs")
                .should().onlyAccessClassesThat()
                .resideInAnyPackage("com.project.model..");
        rule.check(new ClassFileImporter().importPackages("com.project"));
    }

    @Test
    public void servicesAndBusinessShouldNotDependOnControllers() {
        ArchRule rule = noClasses()
                .that().resideInAPackage("com.project.service..")
                .or().resideInAPackage("com.project.business..")
                .should().dependOnClassesThat()
                .resideInAPackage("com.project.api..");
        rule.check(new ClassFileImporter().importPackages("com.project"));
    }
}
