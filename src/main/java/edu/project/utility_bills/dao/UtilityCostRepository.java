package edu.project.utility_bills.dao;


import edu.project.utility_bills.domain.UtilityCost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Repository
public interface UtilityCostRepository extends JpaRepository<UtilityCost, Long> {


    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value="INSERT INTO ub_utility_cost (cold_water_cost, hot_water_cost, electricity_cost ,gas_cost)" +
            " VALUES (:coldWaterCost, :hotWaterCost, :electricityCost, :gasCost)",
            nativeQuery = true)
    void addUtilityCost(
            @Param("coldWaterCost") double coldWaterCost,
            @Param("hotWaterCost") double hotWaterCost,
            @Param("electricityCost") double electricityCost,
            @Param("gasCost") double gasCost
            );
}
