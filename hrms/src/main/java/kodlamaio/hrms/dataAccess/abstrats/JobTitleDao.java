package kodlamaio.hrms.dataAccess.abstrats;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entity.concretes.JobTitle;

public interface JobTitleDao extends JpaRepository<JobTitle, Integer>{
	boolean existsByTitle (String Title);
	

}
