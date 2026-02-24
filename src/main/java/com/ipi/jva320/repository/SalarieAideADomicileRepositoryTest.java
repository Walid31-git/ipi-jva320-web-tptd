package com.ipi.jva350.repository;

import com.ipi.jva350.model.SalarieAideADomicile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.BeforeEach;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class SalarieAideADomicileRepositoryTest {

    @Autowired
    private SalarieAideADomicileRepository repository; // injection du repository

     @BeforeEach
    public void before() {
    	SalarieAideADomicileRepository.deleteAll();
    }
    
    @Test
    void testFindByNom_shouldReturnSalarie() {

        // Préparation
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        salarie.setNom("ALLAL"); // petit test avec mon nom 😉
        repository.save(salarie);

        // Exécution
        SalarieAideADomicile resultat = repository.findByNom("ALLAL");

        // Vérification
        Assertions.assertNotNull(resultat);
        Assertions.assertEquals("ALLAL", resultat.getNom());
    }

    @Test
    void testFindByNom_shouldReturnNull() {

        // Préparation
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        salarie.setNom("Martin");
        repository.save(salarie);

        // Exécution
        SalarieAideADomicile resultat = repository.findByNom("Durand");

        // Vérification : ici je m’attends à ne rien trouver
        Assertions.assertNull(resultat);
    }
}