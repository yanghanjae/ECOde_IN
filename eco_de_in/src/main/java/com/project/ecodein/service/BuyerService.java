package com.project.ecodein.service;

import java.util.Optional;
import java.util.Random;
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

	public Page<Buyer> buyers (int page, String keyword, String buyer_status) {
		
		int status;
		
		if (keyword == "") {
			if (buyer_status.equals ("default")) {
				status = 1;
			} else {
				status = 0;
			}
			
			Sort sort = Sort.by (Sort.Order.desc ("buyer_code"));
			
			Pageable pageable = PageRequest.of (page - 1, 10, sort);
			
			return buyerRepository.findByBuyerStatus (pageable, status);
		}

		if (buyer_status.equals ("default")) {
			
			status = 1;
			
			Sort sort = Sort.by (Sort.Order.desc ("buyer_code"));

			Pageable pageable = PageRequest.of (page - 1, 10, sort);

			return buyerRepository.findByBuyerStatusAndBuyerNameOrBuyerNumber (keyword, status, pageable);

		} else {
			
			status = 0;

			Sort sort = Sort.by (Sort.Order.desc ("buyer_code"));

			Pageable pageable = PageRequest.of (page - 1, 10, sort);

			return buyerRepository.findByBuyerStatusAndBuyerNameOrBuyerNumber (keyword, status, pageable);

		}
		
	}

	public Buyer buyerInsert (Buyer buyer) {

		buyer.setBuyer_secure_code (seruceCodeCreate ());
		buyer.setBuyer_status (true);

		return buyerRepository.save (buyer);

	}

	public Optional<Buyer> buyerDetail (Long buyer_code) {

		return buyerRepository.findById (buyer_code).stream ().findFirst ();

	}
	
	public void updateStatus (Long buyer_code) {
		
		buyerRepository.updateBuyerStatusByBuyerCode (buyer_code);
		
	}

	// 보안코드 생성 메서드
	private String seruceCodeCreate () {

		StringBuilder sb = new StringBuilder ();
		Random rd = new Random ();

		for (int i = 0; i < 5; i++) {

			sb.append ((char) (rd.nextInt (26) + 65));

		}

		System.out.println (sb.toString ());

		return sb.toString ();

	}
	
}
