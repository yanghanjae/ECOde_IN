package com.project.ecodein.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.project.ecodein.dto.Item;
import com.project.ecodein.repository.ItemRepository;
import com.project.ecodein.repository.StockRepository;
import com.project.ecodein.repository.StorageRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ItemService {

	private final ItemRepository ITEM_REPOSITORY;

	public ItemService (ItemRepository ITEM_REPOSITORY) {

		this.ITEM_REPOSITORY = ITEM_REPOSITORY;

	}

	public Page<Item> findItem (int page, boolean is_item, String search, Integer itemNo) {

		Pageable pageable = PageRequest.of (page - 1, 10, Sort.by ("item_no").descending ());

		if (is_item) {

			return ITEM_REPOSITORY.searchItemOnly (search, pageable);

		} else {

			return ITEM_REPOSITORY.searchItem (search, pageable);

		}

	}

}
