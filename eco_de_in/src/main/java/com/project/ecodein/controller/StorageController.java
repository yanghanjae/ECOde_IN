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

    private final StorageService STORAGE_SERVICE;

    public StorageController (StorageService storageService) {
        this. STORAGE_SERVICE = storageService;
    }

    @GetMapping({"/{page}/{keyword}/{storage_status}", "/{page}", "/{page}/{keyword}"})
    public String storage(Model model,
                          @PathVariable(name = "page") Integer page,
                          @PathVariable(name = "keyword", required = false) String keyword,
                          @PathVariable(name = "storage_status", required = false) String storage_status) {

        if (page == null) {
            page = 1;
        }

        model.addAttribute("storages", STORAGE_SERVICE.storages(page, keyword, storage_status));

        return "storage/storage";
    }
    
    
    @GetMapping("/detail/{storage_no}")
    @ResponseBody
    public Storage storageDetail (@PathVariable Integer storage_no) {
    	
    	Storage storage = STORAGE_SERVICE.getStorageByStorageNo (storage_no);

    	return storage;
    }

    @GetMapping("/storageStock/{storage_no}")
    @ResponseBody
    public List<ItemStockStorage> storageStock (@PathVariable int storage_no) {
        List<ItemStockStorage> stockList = STORAGE_SERVICE.getItemStockByStorage(storage_no);

        if (stockList.isEmpty()) {
            return null;
        } else {
            return stockList;
        }

    }
    
    @PostMapping("/add")
    public String storageAdd (@ModelAttribute Storage storage) {
    	
    	STORAGE_SERVICE.storageAdd (storage);
    	
    	return "redirect:/storage/1";
    }
    
    @GetMapping("/remove/{storage_no}")
    public String storageRemove (@PathVariable int storage_no) {
    	
    	STORAGE_SERVICE.storageRemove (storage_no);
    	
    	return "redirect:/storage/1";
    }

    @GetMapping("/status_modify/{storage_no}/{storage_status}")
    public String storageStatusModify (@PathVariable Integer storage_no, @PathVariable String storage_status) {

        STORAGE_SERVICE.storageStatusUpdate(storage_no, storage_status);

        return "redirect:/storage/1";
    }
}
