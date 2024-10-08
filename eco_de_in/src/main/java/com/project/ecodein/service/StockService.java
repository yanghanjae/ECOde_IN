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
		Pageable pageable = PageRequest.of (page-1, 10, Sort.by ("stock_no").descending ()); 
		
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
		if (editQunaitity == 0) {
			STOCK_REPOSITORY.delete (STOCK_REPOSITORY.findByStockNO (editStockNo).get ());
		} else {
			STOCK_REPOSITORY.updateStock(editStockNo, editQunaitity);
		}
	}

	public Page<Item> searchStocks (String search, int page) {
		Pageable pageable = PageRequest.of (page-1, 10, Sort.by ("item_name").ascending ());
		
		if(search == null || search.isEmpty ()) {
			search = "";
		}
		
		return ITEM_REPOSITORY.searchItem(search, pageable);

	}
	
	public Page<Item> searchItems (String search, int page) {
		Pageable pageable = PageRequest.of (page-1, 10, Sort.by ("item_name").ascending ());
		
		if(search == null || search.isEmpty ()) {
			search = "";
		}
		
		return ITEM_REPOSITORY.searchItemOnly(search, pageable);

	}
	
	public Page<Item> searchMaterials (String search, int page) {
		Pageable pageable = PageRequest.of (page-1, 10, Sort.by ("item_name").ascending ());
		
		if(search == null || search.isEmpty ()) {
			search = "";
		}
		
		return ITEM_REPOSITORY.searchMaterialOnly(search, pageable);
		
	}

	public Page<Storage> searchStorages (String search, int page) {

		Pageable pageable = PageRequest.of (page-1, 10, Sort.by ("storage_name").ascending ());
		
		if(search == null || search.isEmpty ()) {
			search = "";
		}
		return STORAGE_REPOSITORY.findAllByStorageNameOrStorageSite (search, pageable);

	}

	public void addStock(int item_no, int storage_no, int quantity) {
		
		Optional<Stock> stock = STOCK_REPOSITORY.findStock(item_no, storage_no);
		
		// 등록하려는 재고가 창고에 있을시, 수량만 추가. 없을시 새 재고를 등록
		if(stock.isPresent()) {
			STOCK_REPOSITORY.updateStock(stock.get().getStockNo(), stock.get().getQuantity()+quantity);
		} else {
			STOCK_REPOSITORY.addStock(item_no, storage_no, quantity);
		}
	}

	
	// 생산품 추가 (재료들의 수량을 확인 후, 해당 창고의 재고보다 수량이 클 시 재고 삭제, 작을 시 수량 감소)
	public void addItem (Integer itemNo,
		Integer storage_no, Integer quantity,
		List<Integer> ingredient,
		List<Integer> ingredient_quantity) {
		
		for (int i=0; i<ingredient.size (); i++) {
			
			Optional<Stock> stock = STOCK_REPOSITORY.findStock(ingredient.get (i),storage_no);
			
			if (stock.isPresent()) {
				if(stock.get ().getQuantity () > ingredient_quantity.get (i)) {
					STOCK_REPOSITORY.updateStock(stock.get().getStockNo(), stock.get().getQuantity()-ingredient_quantity.get (i));
				} else {
					STOCK_REPOSITORY.delete (stock.get ());
				}
			}
			
		}

		Optional<Stock> stock = STOCK_REPOSITORY.findStock(itemNo,storage_no);
		
		if (stock.isPresent ()) {
			STOCK_REPOSITORY.updateStock(stock.get().getStockNo(), stock.get().getQuantity()+quantity);
		} else {
			STOCK_REPOSITORY.addStock(itemNo, storage_no, quantity);
		}
		
	}
}
