/**
 * Luvina Software JSC, 2022
 * UserServiceIml.java, Bui Quang Hieu
 */
package com.hieubq.ServiceIml;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hieubq.Service.UserService;
import com.hieubq.entities.User;
import com.hieubq.reporitories.UserRepository;

/**
 * @author BUI_QUANG_HIEU
 */

@Service
public class UserServiceIml implements UserService {

	@Autowired
	UserRepository userRepository;

	/**
	 * Description của method là làm gì
	 * 
	 * @param [name] [giải thích]: mô tả ý nghĩa và giải thích cách dùng
	 * @return [giải thích]: Mô tả giá trị và về của nó
	 */
	@Override
	public List<User> getAll() {
		return userRepository.findAll();
	}

}
