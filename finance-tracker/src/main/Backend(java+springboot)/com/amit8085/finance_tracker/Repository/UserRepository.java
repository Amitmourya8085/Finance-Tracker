package com.amit8085.finance_tracker.Repository;

import com.amit8085.finance_tracker.Entity.Transaction;
import com.amit8085.finance_tracker.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

}
