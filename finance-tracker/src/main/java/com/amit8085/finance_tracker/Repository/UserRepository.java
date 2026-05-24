package com.amit8085.finance_tracker.Repository;

import com.amit8085.finance_tracker.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

}
