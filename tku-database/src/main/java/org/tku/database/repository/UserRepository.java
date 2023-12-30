package org.tku.database.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.tku.database.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}