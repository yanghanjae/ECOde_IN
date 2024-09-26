package com.project.ecodein.service;

import com.project.ecodein.dto.ItemStockStorage;
import com.project.ecodein.dto.Storage;
import com.project.ecodein.repository.StorageRepository;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StorageService {

    private final StorageRepository STORAGE_SERVICE;

    public StorageService (StorageRepository storageRepository) {
        this.STORAGE_SERVICE = storageRepository;
    }

    public Page<Storage> storages (int page, String keyword, String storage_status) {
        Sort sort = Sort.by (Sort.Order.desc ("storage_no"));
        Pageable pageable = PageRequest.of (page - 1, 10, sort);
        
        if (keyword == null && storage_status == null) {
            return STORAGE_SERVICE.findAll(pageable);
        } else if (storage_status != null && !storage_status.equals ("200")) {
        	if (storage_status.equals ("정상")) {
        		return STORAGE_SERVICE.findAllByStorageStatus (storage_status, pageable);
         	} else {
         		return STORAGE_SERVICE.findAllByStorageStatusNegative (pageable);
         	}
        }
        
    	return STORAGE_SERVICE.findAllByStorageNameOrStorageSite(keyword, pageable);

    }
    
    public Storage getStorageByStorageNo (Integer storage_no) {

        return STORAGE_SERVICE.findById(storage_no).orElse(null);

    }

	public List<ItemStockStorage> getItemStockByStorage (int storage_no) {
        return STORAGE_SERVICE.findByItemStockByStorageNo(storage_no);
	}

	public void storageAdd (Storage storage) {
		storage.setStorage_regist (LocalDate.now ());
		storage.setStorage_status ("정상");

		STORAGE_SERVICE.save (storage);
	}

	public void storageRemove (int storage_no) {

		STORAGE_SERVICE.deleteById (storage_no);
		
	}

    public void storageUpdate (Storage storage) {
        STORAGE_SERVICE.save (storage);
    }

    public void storageStatusUpdate (Integer storage_no, String storage_status) {
        STORAGE_SERVICE.statusUpdate(storage_no, storage_status);
    }
}
