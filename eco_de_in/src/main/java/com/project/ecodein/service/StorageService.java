package com.project.ecodein.service;

import com.project.ecodein.dto.Item;
import com.project.ecodein.dto.ItemStockStorage;
import com.project.ecodein.dto.Stock;
import com.project.ecodein.dto.Storage;
import com.project.ecodein.repository.StorageRepository;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class StorageService {

    private final StorageRepository storageRepository;

    public StorageService (StorageRepository storageRepository) {
        this.storageRepository = storageRepository;
    }

    public Page<Storage> storages (int page, String keyword, String storage_status) {
        Sort sort = Sort.by (Sort.Order.desc ("storage_no"));
        Pageable pageable = PageRequest.of (page - 1, 10, sort);
        if (keyword == null && storage_status == null) {
            return storageRepository.findAll(pageable);
        } else if (!storage_status.isEmpty()) {
            return storageRepository.findAll(pageable);
        }

        return null;
    }
    
    public Storage getStorageByStorageNo (Integer storage_no) {

        return storageRepository.findById(storage_no).orElse(null);

    }

	public List<ItemStockStorage> getItemStockByStorage (int storage_no) {
        System.out.println("storage_no = " + storage_no);
        return storageRepository.findByItemStockByStorageNo(storage_no);

	}
    
    
}
