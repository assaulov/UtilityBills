package edu.project.utility_bills.dao;


import edu.project.utility_bills.domain.User;
import edu.project.utility_bills.rest.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;


public interface UserRepository extends CrudRepository<User, Long> {



    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value="INSERT INTO ub_users (last_name, first_name, middle_name, date_of_birth, pseudo_name) values (:lastName, :firstName, :middleName, :dateOfBirth, :pseudoName)",
            nativeQuery = true)
    public void createUser(
                    @Param("lastName") String lastName,
                    @Param("firstName") String firstName,
                    @Param("middleName") String middleName,
                    @Param("dateOfBirth") LocalDate dateOfBirth,
                    @Param("pseudoName") String pseudoName);



    @Query("SELECT us FROM User us WHERE us.lastName = :lastName AND " +
            "us.firstName = :firstName AND us.middleName= :middleName  AND us.dateOfBirth = :dateOfBirth AND"  +
           " us.pseudoName = :pseudoName")
    List<User> findUser(@Param("lastName") String lastName,
                           @Param("firstName") String firstName,
                           @Param("middleName") String middleName,
                           @Param("dateOfBirth") LocalDate dateOfBirth,
                           @Param("pseudoName") String pseudoName);



}
