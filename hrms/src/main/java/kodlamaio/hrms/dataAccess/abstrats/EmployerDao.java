package kodlamaio.hrms.dataAccess.abstrats;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entity.concretes.Employer;

public interface EmployerDao extends JpaRepository<Employer, Integer> {

}
