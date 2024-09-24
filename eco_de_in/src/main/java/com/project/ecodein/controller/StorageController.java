package com.project.ecodein.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.ecodein.dto.Item;
import com.project.ecodein.dto.ItemStockStorage;
import com.project.ecodein.dto.Stock;
import com.project.ecodein.dto.Storage;
import com.project.ecodein.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    
    
    @GetMapping("/detail/{storage_no}")
    @ResponseBody
    public Storage storageDetail (@PathVariable Integer storage_no) {
    	
    	Storage storage = storageService.getStorageByStorageNo (storage_no);

    	return storage;
    }

    @GetMapping("/storageStock/{storage_no}")
    @ResponseBody
    public List<ItemStockStorage> storageStock (@PathVariable int storage_no) {
        List<ItemStockStorage> stockList = storageService.getItemStockByStorage(storage_no);

        if (stockList.isEmpty()) {
            return null;
        } else {
            return stockList;
        }

    }

}
