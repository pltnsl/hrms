package kodlamaio.hrms.bussiness.abstracts;

import java.util.List;


import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entity.concretes.JobTitle;

public interface JobTitleService {
	
		DataResult<List<JobTitle>> getAll();
		Result add(JobTitle jobTitle);
	
	

}
