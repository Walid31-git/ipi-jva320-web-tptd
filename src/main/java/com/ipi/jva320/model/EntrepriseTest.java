package com.ipi.jva320.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EntrepriseTest {

    private LocalDate debutMars;
    private LocalDate finMars;

    @BeforeEach
    void setUp() {
        // Initialisation d'une nouvelle plage pour chaque test
        debutMars = LocalDate.of(2025, 3, 1);
        finMars = LocalDate.of(2025, 3, 31);
    }

    @Test
    void testDateDansIntervalle() {
        // Given
        LocalDate dateTest = LocalDate.of(2025, 3, 18); // date au milieu

        // When
        boolean resultat = Entreprise.estDansPlage(dateTest, debutMars, finMars);

        // Then
        assertTrue(resultat);
    }

    @Test
    void testDateAvantIntervalle() {
        // Given
        LocalDate dateTest = LocalDate.of(2025, 2, 28); // juste avant

        // When
        boolean resultat = Entreprise.estDansPlage(dateTest, debutMars, finMars);

        // Then
        assertFalse(resultat);
    }

    @Test
    void testDateApresIntervalle() {
        // Given
        LocalDate dateTest = LocalDate.of(2025, 4, 1); // juste après

        // When
        boolean resultat = Entreprise.estDansPlage(dateTest, debutMars, finMars);

        // Then
        assertFalse(resultat);
    }

    @Test
    void testDateEgaleDebutPlage() {
        // When
        boolean resultat = Entreprise.estDansPlage(debutMars, debutMars, finMars);

        // Then
        assertTrue(resultat); // borne incluse
    }

    @Test
    void testDateEgaleFinPlage() {
        // When
        boolean resultat = Entreprise.estDansPlage(finMars, debutMars, finMars);

        // Then
        assertTrue(resultat); // borne incluse
    }

    @Test
    void testIntervalleSurUnJour() {
        // Given
        LocalDate dateUnique = LocalDate.of(2026, 10, 10); // autre année

        // When
        boolean resultat = Entreprise.estDansPlage(dateUnique, dateUnique, dateUnique);

        // Then
        assertTrue(resultat);
    }

    @Test
    void testIntervalleInvalide() {
        // Given
        LocalDate dateTest = LocalDate.of(2025, 3, 10);
        LocalDate debut = LocalDate.of(2025, 6, 1); // début après fin
        LocalDate fin = LocalDate.of(2025, 5, 1);

        // When
        boolean resultat = Entreprise.estDansPlage(dateTest, debut, fin);

        // Then
        assertFalse(resultat);
    }
}