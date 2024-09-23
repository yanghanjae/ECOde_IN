package com.project.ecodein.repository;

import com.project.ecodein.dto.Storage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageRepository extends JpaRepository<Storage, Integer> {
    // 기본 조회
    @Query(value = "select * from storage", nativeQuery = true)
    public Page<Storage> findAll(Pageable pageable);

    // 상태별 조회
    @Query(value = "select * from storage where storage_status like :storage_status", nativeQuery = true)
    public Page<Storage> findAllByStorageStatus(@Param("storage_status") String storage_status, Pageable pageable);

    // 키워드 조회
    @Query(value = "select * from storage where storage_name like :keyword or storage_site like  :keyword", nativeQuery = true)
    public Page<Storage> findAllByStorageNameOrStorageSite (@Param("keyword") String keyword,
                                                            Pageable pageable);

}
