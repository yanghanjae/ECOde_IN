package com.project.ecodein.repository;

import com.project.ecodein.dto.Item;
import com.project.ecodein.dto.OrderDetail;
import com.project.ecodein.dto.Ordering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {

    @Query(value = "select * from order_detail where order_no = :approval_no", nativeQuery = true)
    List<OrderDetail> findAllByOrderId(int approval_no);

    @Query(value = "select count(*) count from order_detail where order_no = :approval_no", nativeQuery = true)
    int getOrderDetailCountByOrderNo(int approval_no);

    @Transactional
    @Query(value = "insert into order_detail (item_no, order_no, quantity) " +
            "values (:order_no, :orderNo, :quantitie)", nativeQuery = true)
    int addsave(int order_no, int orderNo, int quantitie);
}
