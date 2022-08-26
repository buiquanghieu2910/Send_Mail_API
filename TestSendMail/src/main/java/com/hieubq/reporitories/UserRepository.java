/**
 * Luvina Software JSC, 2022
 * UserRepository.java, Bui Quang Hieu
 */
package com.hieubq.reporitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hieubq.entities.User;

/**
 * @author BUI_QUANG_HIEU
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
