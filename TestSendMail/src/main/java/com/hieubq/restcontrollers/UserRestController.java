/**
 * Luvina Software JSC, 2022
 * UserRestController.java, Bui Quang Hieu
 */
package com.hieubq.restcontrollers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hieubq.Service.UserService;
import com.hieubq.entities.User;
import com.hieubq.reporitories.UserRepository;

/**
 * @author BUI_QUANG_HIEU
 */

@CrossOrigin("*")
@RestController
@RequestMapping("rest/api/user")
public class UserRestController {

	@Autowired
	UserService service;

	@Autowired
	UserRepository repository;

	@PostMapping
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		try {
			return ResponseEntity.ok(repository.save(user));
		} catch (Exception e) {
			return ResponseEntity.ok().build();
		}
	}

	@GetMapping("{id}")
	public User findById(@PathVariable("id") Integer id) {
		return repository.findById(id).get();
	}

	@GetMapping
	public ResponseEntity<List<User>> getAll() {
		try {
			return ResponseEntity.ok(service.getAll());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok().build();
		}
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}

}
