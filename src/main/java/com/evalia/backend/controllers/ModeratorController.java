package com.evalia.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evalia.backend.models.Moderator;
import com.evalia.backend.services.ModeratorService;
@RestController
@RequestMapping("/moderators")
public class ModeratorController {

    private final ModeratorService moderatorsService;

    @Autowired
    public ModeratorController(ModeratorService moderatorsService) {
        this.moderatorsService = moderatorsService;
    }

    @PostMapping
    public Moderator createModerator(@RequestBody Moderator moderators) {
        return moderatorsService.createModerator(moderators);
    }

    @GetMapping
    public List<Moderator> getAllModerators() {
        return moderatorsService.getAllModerator();
    }

    @GetMapping("/{id}")
    public Moderator getModeratorById(@PathVariable Long id) {
        return moderatorsService.getModeratorById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteModeratorc(@PathVariable Long id) {
        moderatorsService.deleteModerator(id);
    }
}