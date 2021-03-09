package edu.project.utility_bills.dao;
/*
Репозиторий показаний счетчиков.
 */

import edu.project.utility_bills.domain.User;
import edu.project.utility_bills.domain.Utilities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UtilityRepository extends JpaRepository<Utilities, Long> {


    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "INSERT INTO ub_utilities (id, date_of_write_utility_meter, cold_water, hot_water, electricity, gas, house_utility, capital_repair) " +
            " VALUES (:user, :dateOfWriteUtilityMeter, :coldWater, :hotWater, :electricity, :gas, :houseUtility, :capitalRepair)",
            nativeQuery = true)
    void addUtility(
            @Param("user") User user,
            @Param("dateOfWriteUtilityMeter") LocalDate dateOfWriteUtilityMeter,
            @Param("coldWater") double coldWater,
            @Param("hotWater") double hotWater,
            @Param("electricity") double electricity,
            @Param("gas") double gas,
            @Param("houseUtility") double houseUtility,
            @Param("capitalRepair") double capitalRepair);


    @Override
    List<Utilities> findAll();


    @Query(value = "SELECT ut FROM Utilities ut WHERE ut.dateOfWriteUtilityMeter = :dateOfWriteUtilityMeter")
    List<Utilities> findUtilitiesByDate(@Param("dateOfWriteUtilityMeter") LocalDate dateOfWriteUtilityMeter);

    @Query(value = "SELECT ut FROM Utilities ut WHERE ut.dateOfWriteUtilityMeter >= :dateFrom AND ut.dateOfWriteUtilityMeter <= :dateTo AND ut.user = :user")
    List<Utilities> findUtilitiesByPeriod(@Param("dateFrom") LocalDate dateFrom,
                                          @Param("dateTo") LocalDate dateTo,
                                          @Param("user") User user);
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM Utilities ut WHERE ut.utilityId = :utilityId")
    void deleteById (@Param("utilityId") long utilityId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Utilities ut SET ut.coldWater = :coldWater, ut.hotWater= :hotWater, ut.electricity = :electricity, ut.gas= :gas, ut.houseUtility= :houseUtility, ut.capitalRepair = :capitalRepair WHERE ut.utilityId= :utilityId")
    void updateById(@Param("utilityId") long utilityId,
                    @Param("coldWater") double coldWater,
                    @Param("hotWater") double hotWater,
                    @Param("electricity") double electricity,
                    @Param("gas") double gas,
                    @Param("houseUtility") double houseUtility,
                    @Param("capitalRepair") double capitalRepair);


   @Query(value = "SELECT ut FROM Utilities  ut WHERE ut.utilityId = :id")
    Utilities findUtilityById(@Param("id") long  id);
}

