package br.com.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.springboot.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

  // usando a query
  // @Query("Select u from User u where u.username like %:f%")
  // List<User> findByNormalUsername();

  // usando consultas personalizadas do spring boot
  List<User> findByUsernameNotContaining(String username);

  List<User> findByUsernameStartingWith(String username);

  List<User> findByNameIgnoreCaseContaining(String name);

}
