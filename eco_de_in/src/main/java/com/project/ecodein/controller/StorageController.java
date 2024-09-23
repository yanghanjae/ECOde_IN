package com.project.ecodein.controller;

import com.project.ecodein.dto.Storage;
import com.project.ecodein.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/storage")
public class StorageController {

    private final StorageService storageService;

    public StorageController (StorageService storageService) {
        this. storageService = storageService;
    }

    @GetMapping({"/{page}/{keyword}/{storage_status}", "/{page}"})
    public String storage(Model model,
                                 @PathVariable(name = "page") Integer page,
                                 @PathVariable(name = "keyword", required = false) String keyword,
                                 @PathVariable(name = "storage_status", required = false) String storage_status) {

        if (page == null) {
            page = 1;
        }


        model.addAttribute("storages", storageService.storages(page, keyword, storage_status));

        return "storage/storage";
    }
}
