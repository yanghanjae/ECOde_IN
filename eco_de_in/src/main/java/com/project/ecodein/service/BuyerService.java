package com.project.ecodein.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.project.ecodein.dto.Buyer;
import com.project.ecodein.repository.BuyerRepository;

@Service
public class BuyerService {

	private final BuyerRepository buyerRepository;

	public BuyerService (BuyerRepository buyerRepository) {

		this.buyerRepository = buyerRepository;

	}

	public Page<Buyer> buyers (int page, String keyword) {

		List<Sort.Order> sorts = new ArrayList<> ();

		sorts.add (Sort.Order.asc ("buyer_regist_date"));

		Sort sort = Sort.by (Sort.Order.desc ("buyerCode"));

		Pageable pageable = PageRequest.of (page - 1, 10, sort);

		if (keyword == null) {

			return buyerRepository.findAll (pageable);

		}
		return null;

	}

}
