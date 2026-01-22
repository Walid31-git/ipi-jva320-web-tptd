package com.ipi.jva320.controller;

import com.ipi.jva320.model.SalarieAideADomicile;
import com.ipi.jva320.service.SalarieAideADomicileService;
import com.ipi.jva320.exception.SalarieException;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Controller
public class SalarieController {

    @Autowired
    private SalarieAideADomicileService salarieAideADomicileService;

    @GetMapping("/salaries/{id}")
    public String getDetailSalarie(@PathVariable("id") Long id, ModelMap model) {
        SalarieAideADomicile salarie = salarieAideADomicileService.getSalarie(id);
        if (salarie == null) {
            return "redirect:/salaries";
        }
        model.put("salarie", salarie);
        return "detail_Salarie";
    }

    @GetMapping("/salaries")
    // Recherche un salarié par nom via un paramètre URL : affiche la fiche détail du premier résultat (404 si aucun trouvé), sinon retourne la liste complète par défaut.
    public String searchSalarie(@RequestParam(value = "nom", required = false) String nom,
                                final ModelMap model) {

        if (nom != null && !nom.isEmpty()) {

            List<SalarieAideADomicile> salaries = salarieAideADomicileService.getSalaries(nom);

            if (salaries.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Aucun salarié trouvé avec le nom : " + nom);
            }

            model.put("salarie", salaries.get(0));
            return "detail_Salarie";
        }


    @GetMapping("/salaries/aide/new")
    public String newSalarie(ModelMap model) {
        model.put("salarie", new SalarieAideADomicile());
        return "new_Salarie";
    }

    @PostMapping("/salaries/aide/save")
    public String addSalarie(@ModelAttribute SalarieAideADomicile salarie)
            throws SalarieException, EntityExistsException {

        salarieAideADomicileService.creerSalarieAideADomicile(salarie);
        return "redirect:/salaries/" + salarie.getId();
    }


// Méthode qui supprime un salarié et redirige vers la liste afin d’éviter les problèmes de cache et constater sa disparition.
    @GetMapping("/salaries/{id}/delete")
    public String deleteSalarie(@PathVariable Long id)
            throws EntityExistsException, SalarieException {

        salarieAideADomicileService.deleteSalarieAideADomicile(id);
        return "redirect:/salaries";
    }

// Méthode qui gère la modification d’un salarié puis redirige vers sa page de détails afin de constater les changements (les champs modifiés ne sont plus éditables mais affichés comme pris en compte)
    @PostMapping("/salaries/{id}")
    public String updateSalarie(@PathVariable(value = "id") Long id, final ModelMap model,
                                SalarieAideADomicile updatedSalarie) throws EntityExistsException, SalarieException {
        salarieAideADomicileService.updateSalarieAideADomicile(updatedSalarie);
        model.put("salaries", salarieAideADomicileService.getSalaries());
        return "redirect:/salaries/" + updatedSalarie.getId();
    }
}