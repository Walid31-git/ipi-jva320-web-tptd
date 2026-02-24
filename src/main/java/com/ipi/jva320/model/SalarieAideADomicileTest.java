package com.ipi.jva350.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import java.util.LinkedHashSet;

class SalarieAideADomicileTest {

    @ParameterizedTest
    @CsvSource({
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

    @ParameterizedTest
    @CsvSource({
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
}