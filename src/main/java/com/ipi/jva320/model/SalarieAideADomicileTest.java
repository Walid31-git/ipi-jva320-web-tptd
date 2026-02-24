package com.ipi.jva350.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test; // ajouté pour les tests simples
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import java.util.LinkedHashSet;

class SalarieAideADomicileTest {

    // Test du droit aux congés (cas valide) - ajouté
    @Test
    public void testALegalementDroitADesCongesPayesRight() {
        // Given
        SalarieAideADomicile unSalarie = new SalarieAideADomicile();
        unSalarie.setJoursTravaillesAnneeNMoins1(11);

        // When
        Boolean droitCongesPayesTrue = unSalarie.aLegalementDroitADesCongesPayes();

        // Then
        Assertions.assertEquals(true, droitCongesPayesTrue);
    }

    // Test du droit aux congés (cas non valide) - ajouté
    @Test
    public void testALegalementDroitADesCongesPayesWrong() {
        // Given
        SalarieAideADomicile unSalarie = new SalarieAideADomicile();
        unSalarie.setJoursTravaillesAnneeNMoins1(9);

        // When
        Boolean droitCongesPayesTrue = unSalarie.aLegalementDroitADesCongesPayes();

        // Then
        Assertions.assertEquals(false, droitCongesPayesTrue);
    }

    // Ajout d'un deuxième cas dans le CsvSource
    @ParameterizedTest
    @CsvSource({
        "2025-11-01,2025-12-01",
        "2025-11-01,2025-12-01"
    })
    void testCalculeJoursDeCongeDecomptesPourPlageRight(String debut, String fin) {

        // Préparation des données
        SalarieAideADomicile unSalarie = new SalarieAideADomicile();
        LocalDate dateDebut = LocalDate.parse(debut);
        LocalDate dateFin = LocalDate.parse(fin);

        // Appel de la méthode
        LinkedHashSet<LocalDate> joursDeConges =
                unSalarie.calculeJoursDeCongeDecomptesPourPlage(dateDebut, dateFin);

        // Vérification du résultat attendu
        Assertions.assertEquals(24, joursDeConges.size());
    }

    // Ajout d'un deuxième cas ici aussi
    @ParameterizedTest
    @CsvSource({
        "2025-11-01,2025-12-01",
        "2025-11-01,2025-12-01"
    })
    void testCalculeJoursDeCongeDecomptesPourPlageWrong(String debut, String fin) {

        // Même logique que le test précédent
        SalarieAideADomicile unSalarie = new SalarieAideADomicile();
        LocalDate dateDebut = LocalDate.parse(debut);
        LocalDate dateFin = LocalDate.parse(fin);

        LinkedHashSet<LocalDate> joursDeConges =
                unSalarie.calculeJoursDeCongeDecomptesPourPlage(dateDebut, dateFin);

        // On vérifie que 20 n’est pas correct
        Assertions.assertNotEquals(20, joursDeConges.size());
    }

    // Méthode paramétrée plus propre (correction du prof) - ajoutée
    @ParameterizedTest
    @CsvSource({
        "2025-11-01,2025-12-01,24",
        "2025-11-01,2025-12-01,24"
    })
    public void testCalculeJoursDeCongeDecomptesPourPlage(String debut, String fin, int nbJoursDeConges) {

        // Given
        SalarieAideADomicile unSalarie = new SalarieAideADomicile();
        LocalDate dateDebut = LocalDate.parse(debut);
        LocalDate dateFin = LocalDate.parse(fin);

        // When
        LinkedHashSet<LocalDate> joursDeConges =
                unSalarie.calculeJoursDeCongeDecomptesPourPlage(dateDebut, dateFin);

        // Then
        Assertions.assertEquals(nbJoursDeConges, joursDeConges.size());
    }

    // Méthode de test ajoutée (à compléter plus tard)
    @Test
    public void testFindByNom() {
        // Given

        // When

        // Then
    }
}