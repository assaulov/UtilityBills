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
                           @Param("coldWater") int coldWater,
                           @Param("hotWater") int hotWater,
                           @Param("electricity") int electricity,
                           @Param("gas") int gas,
                           @Param("houseUtility") int houseUtility,
                           @Param("capitalRepair") int capitalRepair);
}
