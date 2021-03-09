package edu.project.utility_bills.dao;
/*
Репозиторий  пользователей
 */

import edu.project.utility_bills.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {



    User findByUsername(String userName);
/*    @Modifying(clearAutomatically = true)
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
                           @Param("pseudoName") String pseudoName);*/






}
