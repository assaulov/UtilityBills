package edu.project.utility_bills.dao;


import edu.project.utility_bills.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {



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


    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("SELECT us FROM User us WHERE us.lastName = :lastName AND " +
            "us.firstName = :firstName AND us.middleName= :middleName  AND us.dateOfBirth = :dateOfBirth AND"  +
           " us.pseudoName = :pseudoName")
    List<User> findUser(@Param("lastName") String lastName,
                           @Param("firstName") String firstName,
                           @Param("middleName") String middleName,
                           @Param("dateOfBirth") LocalDate dateOfBirth,
                           @Param("pseudoName") String pseudoName);


    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("SELECT us FROM User us WHERE us.userId = :userId")
    List<User> findUserById(@Param("userId") long userId);

}
