package edu.project.utility_bills.dao;


import edu.project.utility_bills.domain.User;
import edu.project.utility_bills.domain.Utilities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UtilityRepository extends JpaRepository<Utilities, Long> {


    @Query("SELECT us FROM User us WHERE us.lastName = :lastName AND " +
            "us.firstName = :firstName AND us.middleName= :middleName  AND us.dateOfBirth = :dateOfBirth AND"  +
           " us.passportSeria = :passportSeria AND us.passportNumber = :passportNumber AND us.passportDate = :passportDate")
    List<User> findUser(@Param("lastName") String lastName,
                           @Param("firstName") String firstName,
                           @Param("middleName") String middleName,
                           @Param("dateOfBirth") LocalDate dateOfBirth,
                           @Param("passportSeria") String passportSeria,
                           @Param("passportNumber") String passportNumber,
                           @Param("passportDate") LocalDate passportDate);
}
