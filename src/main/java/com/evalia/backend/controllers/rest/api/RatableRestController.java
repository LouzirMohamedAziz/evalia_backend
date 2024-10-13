package com.evalia.backend.controllers.rest.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.evalia.backend.controllers.impl.RatableController;
import com.evalia.backend.models.Ratable;

@RestController
@RequestMapping("/api/professionals")
public class RatableRestController {

    private final RatableController ratableController;

    @Autowired
    public RatableRestController(RatableController professionalController) {
        this.ratableController = professionalController;
    }
    
    @GetMapping("/search")
    public List<Ratable> search(@RequestParam(required = false) Long governorate,
                                     @RequestParam(required = false) Long delegation,
                                     @RequestParam(required = false) String sector,
                                     @RequestParam(required = false) String subSector) {
    	
        return ratableController.search(governorate, delegation, sector, subSector);
    }
}
