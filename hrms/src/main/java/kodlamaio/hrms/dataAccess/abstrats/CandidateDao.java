package kodlamaio.hrms.dataAccess.abstrats;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entity.concretes.Candidate;

public interface CandidateDao extends JpaRepository<Candidate, Integer> {
	boolean existsByNationalIdentity(String tcNo);
	
	

}
