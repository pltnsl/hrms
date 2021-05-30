package kodlamaio.hrms.adapters.abstracts;

import kodlamaio.hrms.entity.concretes.Candidate;

public interface CustomerCheckService {
	boolean CheckIfRealPerson(Candidate candidate);
	

}
