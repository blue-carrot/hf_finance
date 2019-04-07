package com.kakaopay.hf_finance.repository;

import com.kakaopay.hf_finance.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
