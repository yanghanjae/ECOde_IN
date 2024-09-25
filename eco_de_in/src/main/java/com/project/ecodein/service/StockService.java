package com.project.ecodein.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.project.ecodein.dto.Stock;
import com.project.ecodein.dto.Storage;
import com.project.ecodein.repository.StockRepository;
import com.project.ecodein.repository.StorageRepository;

@Service
public class StockService {

	private final StockRepository STOCK_REPOSITORY;
	private final StorageRepository STORAGE_REPOSITORY;

	public StockService (StockRepository STOCK_REPOSITORY, StorageRepository STORAGE_REPOSITORY) {
		this.STOCK_REPOSITORY = STOCK_REPOSITORY;
		this.STORAGE_REPOSITORY = STORAGE_REPOSITORY;
	}
	
	public Page<Stock> findStock (int page, boolean is_item, String search, Integer storage_no) {
		Pageable pageable = PageRequest.of (page, 10, Sort.by ("stock_no").descending ()); 
		
		if(storage_no == null) {
			if(!is_item) {
				return STOCK_REPOSITORY.findAllStockByKeyword(search,pageable);
			} else {
				return STOCK_REPOSITORY.findStockByKeyword(search, pageable);
			}
		} else {
			if(!is_item) {
				return STOCK_REPOSITORY.findAllStockByKeywordStorage(search,storage_no,pageable);
			} else {
				return STOCK_REPOSITORY.findyStockByKeywordStorage(search,storage_no, pageable);
			}	
		}
		
	}
	
	public List<Storage> findAllStorage () {
		return STORAGE_REPOSITORY.findAllStorage ();
	}
}
