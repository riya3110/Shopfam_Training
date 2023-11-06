package com.shopfam.admin.user;

import static org.assertj.core.api.Assertions.assertThat;



import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.data.domain.*;

import com.shopfam.common.entity.Role;
import com.shopfam.common.entity.User;

@DataJpaTest(showSql= false)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
	@Autowired
	private UserRepository repo;

	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void testCreateNewUserWithOneRole() {
		Role roleAdmin = entityManager.find(Role.class, 1);
		User userAman = new User("Aman@shopfam.com", "Aman2020", "Aman", "Miller");
		userAman.addRole(roleAdmin);

		User savedUser = repo.save(userAman);
		assertThat(savedUser.getId()).isGreaterThan(0);

	}

	@Test
	public void testCreateNewUserWithTwoRole() {
		User userRavi = new User("ravi@gmail.com", "ravi2020", "Ravi", "Kumar");
		Role roleEditor = new Role(3);
		Role roleAssistant = new Role(5);

		userRavi.addRole(roleEditor);
		userRavi.addRole(roleAssistant);

		User savedUser = repo.save(userRavi);

		assertThat(savedUser.getId()).isGreaterThan(0);
	}

	@Test
	public void testListAllUsers() {
		Iterable<User> listUsers = repo.findAll();
		listUsers.forEach(user -> System.out.println(user));

	}

	@Test
	public void testGetUserById() {
		User userAman = repo.findById(1).get();
		System.out.println(userAman);
		assertThat(userAman).isNotNull();
	}

	@Test
	public void testUpdateUserDetails() {
		User userAman = repo.findById(1).get();
		userAman.setEnabled(true);
		userAman.setEmail("AMANProgrammer@gmail.com");

		repo.save(userAman);

	}

	@Test
	public void testUpdateUser() {
		User userRavi = repo.findById(7).get();
		Role roleEditor = new Role(3);
		Role roleSalesperson = new Role(2);

		userRavi.getRoles().remove(roleEditor);
		userRavi.addRole(roleSalesperson);

		repo.save(userRavi);

	}

	@Test
	public void testDeleteUser() {
		Integer userId = 7;
		repo.deleteById(userId);

	}

	@Test
	public void testGetUserByEmail() {
		String email = "ravi@gmail.com";
		User user = repo.getUserByEmail(email);

		assertThat(user).isNotNull();
	}

	@Test
	public void testCountById() {
		Integer id = 1;
		Long countById = repo.countById(id);

		assertThat(countById).isNotNull().isGreaterThan(0);
	}

	@Test
	public void testDisableUser() {
		Integer id = 1;
		repo.updateEnabledStatus(id, false);
	}

	@Test
	public void testEnableUser() {
		Integer id = 3;
		repo.updateEnabledStatus(id, true);
	}

	@Test
	public void testListFirstPage() {
		int pagenumber = 0;
		int pagesize = 4;
		Pageable pageable = PageRequest.of(pagenumber, pagesize);
		Page<User> page = repo.findAll(pageable);

		List<User> listUsers = page.getContent();
		listUsers.forEach(user -> System.out.println(user));

		assertThat(listUsers.size()).isEqualTo(pagesize);
	}
	@Test
	public void testSearchUsers() {
		String keyword = "Joey";
		int pagenumber=0;
		int pagesize=4;
		Pageable pageable = PageRequest.of(pagenumber, pagesize);
		Page<User> page = repo.findAll(keyword , pageable);

		List<User> listUsers = page.getContent();
		
		listUsers.forEach(user -> System.out.println(user));
		
		assertThat(listUsers.size()).isGreaterThan(0);
		
	}
}
