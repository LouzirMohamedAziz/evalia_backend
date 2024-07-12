package com.evalia.backend.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.evalia.backend.ctrl.ProfessionalController;
import com.evalia.backend.models.Professional;

@RestController
@RequestMapping("/api/professionals")
public class ProfessionalRestController {

    private final ProfessionalController professionalController;

    @Autowired
    public ProfessionalRestController(ProfessionalController professionalController) {
        this.professionalController = professionalController;
    }
    
    @GetMapping("/search")
    public List<Professional> search(@RequestParam(required = false) Long governorate,
                                     @RequestParam(required = false) Long delegation,
                                     @RequestParam(required = false) String sector,
                                     @RequestParam(required = false) String subSector) {
    	
        return professionalController.search(governorate, delegation, sector, subSector);
    }
}
