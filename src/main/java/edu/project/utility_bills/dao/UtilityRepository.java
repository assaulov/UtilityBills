package edu.project.utility_bills.dao;


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
    @Query(value="INSERT INTO ub_utilities (user_id, date_of_write_utility_meter, cold_water, hot_water, electricity, gas, house_utility, capital_repair) " +
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


    @Query("SELECT us FROM User us WHERE us.userId IN (SELECT ub FROM Utilities ub WHERE  ub.user = : userId)")
    List<Utilities> findUtilitiesByUser(
            @Param("userId") long userId );


   /* @Query("SELECT ub FROM Utilities ub WHERE ub.user = :user AND ub.dateOfWriteUtilityMeter = :dateOfWriteUtilityMeter AND ub.coldWater = :coldWater AND ub.hotWater = :hotWater AND " +
            " ub.electricity = :electricity AND ub.gas = :gas AND ub.houseUtility = :houseUtility AND ub.capitalRepair = :capitalRepair")
    List<Utilities> findUtilities(
            @Param("user") User user,
            @Param("dateOfWriteUtilityMeter") LocalDate dateOfWriteUtilityMeter,
            @Param("coldWater") double coldWater,
            @Param("hotWater") double hotWater,
            @Param("electricity") double electricity,
            @Param("gas") double gas,
            @Param("houseUtility") double houseUtility,
            @Param("capitalRepair") double capitalRepair);*/

}
