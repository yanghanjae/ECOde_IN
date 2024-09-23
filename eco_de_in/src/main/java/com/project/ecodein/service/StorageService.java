package com.project.ecodein.service;

import com.project.ecodein.dto.Storage;
import com.project.ecodein.repository.StorageRepository;
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
}
