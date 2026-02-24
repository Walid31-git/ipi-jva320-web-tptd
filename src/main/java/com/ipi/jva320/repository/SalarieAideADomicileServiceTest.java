package com.ipi.jva350.service;

import com.ipi.jva350.model.SalarieAideADomicile;
import com.ipi.jva350.repository.SalarieAideADomicileRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class SalarieAideADomicileServiceTest {

    @Autowired
    private SalarieAideADomicileService service;

    @Autowired
    private SalarieAideADomicileRepository repository;

    @Test
    void testCalculeLimiteEntrepriseCongesPermis() {
        // GIVEN : un salarié avec congés acquis et début de contrat connu
        SalarieAideADomicile s = new SalarieAideADomicile();
        s.setNom("MonTestSal");
        s.setMoisDebutContrat(LocalDate.of(2021, 3, 1));
        s.setCongesPayesAcquisAnneeNMoins1(20);
        s.setMoisEnCours(LocalDate.of(2025, 9, 1));
        repository.save(s); // on sauvegarde le salarié

        // WHEN : calcul de la limite de congés possible
        long limite = service.calculeLimiteEntrepriseCongesPermis(
                LocalDate.of(2025, 9, 1),    // date de calcul
                20,                           // congés acquis
                s.getMoisDebutContrat(),      // début du contrat
                LocalDate.of(2025, 9, 10),   // début période de congé
                LocalDate.of(2025, 9, 15)    // fin période de congé
        );

        // THEN : la limite doit être positive
        assertTrue(limite > 0, "La limite de congés calculée doit être positive pour ce salarié");
    }
}