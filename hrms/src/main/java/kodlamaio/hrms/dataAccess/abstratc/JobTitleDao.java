package kodlamaio.hrms.dataAccess.abstratc;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entity.JobTitle;

public interface JobTitleDao extends JpaRepository<JobTitle, Integer>{

}
