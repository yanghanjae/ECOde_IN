package com.project.ecodein.service;

import com.project.ecodein.dto.ItemStockStorage;
import com.project.ecodein.dto.Storage;
import com.project.ecodein.repository.StorageRepository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class StorageService {

    private final StorageRepository STORAGE_REPOSITORY;

    public StorageService (StorageRepository storageRepository) {
        this.STORAGE_REPOSITORY = storageRepository;
    }

    public Page<Storage> storages (int page, String keyword, String storage_status) {
        Sort sort = Sort.by (Sort.Order.desc ("storage_no"));
        Pageable pageable = PageRequest.of (page - 1, 10, sort);
        
        if (keyword == null && storage_status == null) {
            return STORAGE_REPOSITORY.findAll(pageable);
        } else if (!storage_status.equals ("200")) {
            return STORAGE_REPOSITORY.findAllByStorageStatus (storage_status, pageable);
        }
        
    	return STORAGE_REPOSITORY.findAllByStorageNameOrStorageSite(keyword, pageable);

    }
    
    public Storage getStorageByStorageNo (Integer storage_no) {

        return STORAGE_REPOSITORY.findById(storage_no).orElse(null);

    }

	public List<ItemStockStorage> getItemStockByStorage (int storage_no) {
        return STORAGE_REPOSITORY.findByItemStockByStorageNo(storage_no);
	}

	public void storageAdd (Storage storage) {
		storage.setStorage_regist (LocalDate.now ());
		storage.setStorage_status ("정상");

		STORAGE_REPOSITORY.save (storage);
	}

	public void storageRemove (int storage_no) {

		STORAGE_REPOSITORY.deleteById (storage_no);
		
	}

    public void storageUpdate (Storage storage) {
        STORAGE_REPOSITORY.save (storage);
    }

    public void storageStatusUpdate (Integer storage_no, String storage_status) {
        STORAGE_REPOSITORY.statusUpdate(storage_no, storage_status);
    }
}
