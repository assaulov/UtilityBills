package edu.project.utility_bills.dao;

import edu.project.utility_bills.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
/*
Репозиторий ролей пользователей
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
}
