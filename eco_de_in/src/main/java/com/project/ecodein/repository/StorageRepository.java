package com.project.ecodein.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.project.ecodein.dto.ItemStockStorage;
import com.project.ecodein.dto.Storage;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface StorageRepository extends JpaRepository<Storage, Integer> {
    // 기본 조회
    @Query(value = "select * from storage", nativeQuery = true)
    public Page<Storage> findAll(Pageable pageable);
    
    @Query(value = "select * from storage order by storage_name asc", nativeQuery = true)
    public List<Storage> findAllStorage ();

    // 상태별 조회
    @Query(value = "select * from storage where storage_status like :storage_status", nativeQuery = true)
    public Page<Storage> findAllByStorageStatus(@Param("storage_status") String storage_status, Pageable pageable);
    
    @Query(value = "select * from storage where storage_status not like '정상'", nativeQuery = true)
    public Page<Storage> findAllByStorageStatusNegative(Pageable pageable);

    // 키워드 조회
    @Query(value = "select * from storage where storage_name like concat('%', :keyword, '%') " +
                    "or storage_site like concat('%', :keyword, '%')", nativeQuery = true)
    public Page<Storage> findAllByStorageNameOrStorageSite (@Param("keyword") String keyword,
                                                            Pageable pageable);

    // 창고별 재고 조회
    @Query(value = "select new com.project.ecodein.dto.ItemStockStorage(i.itemNo, i.itemName, i.itemPrice, i.isMaterial, i.itemImage, s.quantity, s.storage.storage_no) " +
        "from Stock s left join s.item i where s.storage.storage_no = :storage_no")
    public List<ItemStockStorage> findByItemStockByStorageNo(@Param("storage_no") int storage_no);

    // 창고 상태 변경
    @Modifying
    @Transactional
    @Query(value = "update storage set storage_status = :storage_status where storage_no = :storage_no",
        nativeQuery = true)
    public void statusUpdate (Integer storage_no, String storage_status);
}
