package com.project.ecodein.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.project.ecodein.dto.Item;
import com.project.ecodein.dto.Stock;
import com.project.ecodein.dto.Storage;
import com.project.ecodein.repository.ItemRepository;
import com.project.ecodein.repository.StockRepository;
import com.project.ecodein.repository.StorageRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StockService {

	private final StockRepository STOCK_REPOSITORY;
	private final StorageRepository STORAGE_REPOSITORY;
	private final ItemRepository ITEM_REPOSITORY;

	public StockService (StockRepository STOCK_REPOSITORY, StorageRepository STORAGE_REPOSITORY,
		ItemRepository ITEM_REPOSITORY) {
		this.STOCK_REPOSITORY = STOCK_REPOSITORY;
		this.STORAGE_REPOSITORY = STORAGE_REPOSITORY;
		this.ITEM_REPOSITORY = ITEM_REPOSITORY;
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

	public Optional<Stock> findByStockNo (int stock_no) {

		return STOCK_REPOSITORY.findByStockNO (stock_no);
	}

	public void editStock (int editStockNo, int editQunaitity) {
		
		STOCK_REPOSITORY.updateStock(editStockNo, editQunaitity);
	}

	public Page<Item> searchItems (String search, int page) {
		Pageable pageable = PageRequest.of (page-1, 10, Sort.by ("item_name").ascending ());
		
		if(search == null || search.isEmpty ()) {
			search = "";
		}
		
		return ITEM_REPOSITORY.searchItem(search, pageable);

	}

	public Page<Storage> searchStorages (String search, int page) {

		Pageable pageable = PageRequest.of (page-1, 10, Sort.by ("storage_name").ascending ());
		
		if(search == null || search.isEmpty ()) {
			search = "";
		}
		return STORAGE_REPOSITORY.findAllByStorageNameOrStorageSite (search, pageable);

	}
}
