package kodlamaio.hrms.dataAccess.abstrats;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entity.concretes.User;

public interface UserDao extends JpaRepository<User, Integer>{
	boolean existsByEmail(String email);

}
