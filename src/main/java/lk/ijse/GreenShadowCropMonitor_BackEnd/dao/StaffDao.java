package lk.ijse.GreenShadowCropMonitor_BackEnd.dao;

import lk.ijse.GreenShadowCropMonitor_BackEnd.entity.StaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffDao extends JpaRepository<StaffEntity, String> {
    @Query(value = "SELECT * FROM staff ORDER BY first_name ASC", nativeQuery = true)
    List<StaffEntity> findAllSortedByFirstName();

    @Query(value = "SELECT * FROM staff ORDER BY designation ASC", nativeQuery = true)
    List<StaffEntity> findAllSortedByDesignation();

    @Query(value = "SELECT * FROM staff ORDER BY gender ASC", nativeQuery = true)
    List<StaffEntity> findAllSortedByGender();
}
