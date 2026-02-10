package com.ipi.jva320.controllers;

import com.ipi.jva320.model.SalarieAideADomicile;
import com.ipi.jva320.service.SalarieAideADomicileService;
import com.ipi.jva320.exception.SalarieException;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/salaries")
public class SalarieController {

    @Autowired
    private SalarieAideADomicileService salarieAideADomicileService;

    // =======================
    // LISTE / RECHERCHE DES SALARIÉS
    // =======================
    @GetMapping
    public String searchSalarie(@RequestParam(value = "nom", required = false) String nom,
                                final ModelMap model) {

        if (nom != null && !nom.isEmpty()) {
            List<SalarieAideADomicile> salaries = salarieAideADomicileService.getSalaries(nom);

            if (salaries.isEmpty()) {
                // On reste sur la liste avec un message si personne n'est trouvé
                model.put("errorMessage", "Aucun salarié trouvé pour : " + nom);
                model.put("salaries", salaries);
                model.put("nbSalaries", 0);
                return "list";
            }

            model.put("salarie", salaries.get(0));
            return "detail_Salarie";
        }

        List<SalarieAideADomicile> salaries = salarieAideADomicileService.getSalaries();
        model.put("salaries", salaries);
        model.put("nbSalaries", salaries.size());
        return "list";
    }

    // =======================
    // DÉTAIL SALARIÉ (Correction du path ici)
    // =======================
    @GetMapping("/aide/{id}")
    public String getDetailSalarie(@PathVariable("id") Long id, ModelMap model) {
        SalarieAideADomicile salarie = salarieAideADomicileService.getSalarie(id);
        if (salarie == null) {
            return "redirect:/salaries";
        }
        model.put("salarie", salarie);
        return "detail_Salarie";
    }

    // =======================
    // FORMULAIRE CRÉATION
    // =======================
    @GetMapping("/aide/new")
    public String newSalarie(ModelMap model) {
        model.put("salarie", new SalarieAideADomicile());
        return "new_salarie";
    }

    // =======================
    // SAUVEGARDE (CRÉATION)
    // =======================
    @PostMapping("/aide/save")
    public String addSalarie(@ModelAttribute SalarieAideADomicile salarie)
            throws SalarieException, EntityExistsException {

        salarieAideADomicileService.creerSalarieAideADomicile(salarie);
        return "redirect:/salaries/aide/" + salarie.getId();
    }

    // =======================
    // SUPPRESSION
    // =======================
    @GetMapping("/{id}/delete")
    public String deleteSalarie(@PathVariable Long id)
            throws EntityExistsException, SalarieException {

        salarieAideADomicileService.deleteSalarieAideADomicile(id);
        return "redirect:/salaries";
    }

    // =======================
    // MISE À JOUR
    // =======================
    @PostMapping("/aide/{id}")
    public String updateSalarie(@PathVariable(value = "id") Long id,
                                @ModelAttribute SalarieAideADomicile updatedSalarie)
            throws EntityExistsException, SalarieException {

        salarieAideADomicileService.updateSalarieAideADomicile(updatedSalarie);
        return "redirect:/salaries/aide/" + updatedSalarie.getId();
    }
}