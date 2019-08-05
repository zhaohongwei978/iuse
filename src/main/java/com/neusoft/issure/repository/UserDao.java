package com.neusoft.issure.repository;

import com.neusoft.issure.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserDao extends JpaRepository<User,Long>, JpaSpecificationExecutor<User> {
    User findUserByMobile(String mobile);
}
