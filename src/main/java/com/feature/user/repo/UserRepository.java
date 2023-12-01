package com.feature.user.repo;


import com.feature.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created on July, 2023
 *
 * @author uihyeon1229
 */
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUserNm(String username);

	boolean existsByUserEmail(String email);

	boolean existsByUserNm(String username);

}
