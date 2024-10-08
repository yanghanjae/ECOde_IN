package com.project.ecodein.repository;

import com.project.ecodein.dto.Item;
import com.project.ecodein.dto.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {

    @Query(value = "select * from order_detail where order_no = :approval_no", nativeQuery = true)
    List<OrderDetail> findAllByOrderId(int approval_no);

    @Query(value = "select count(*) count from order_detail where order_no = :approval_no", nativeQuery = true)
    int getOrderDetailCountByOrderNo(int approval_no);

    @Transactional
    @Query(value = "insert into order_detail (item_no, order_no, quantity) " +
            "values (:orderDetail.item_no, :orderDetail.order_no, :orderDetail.quantity)", nativeQuery = true)
    int itemadd(OrderDetail orderDetail);
}
