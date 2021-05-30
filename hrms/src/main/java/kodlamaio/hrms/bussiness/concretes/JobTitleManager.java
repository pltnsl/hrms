package kodlamaio.hrms.bussiness.concretes;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.bussiness.abstracts.JobTitleService;
import kodlamaio.hrms.core.utilities.results.DataResult;

import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstrats.JobTitleDao;
import kodlamaio.hrms.entity.concretes.JobTitle;


@Service
public class JobTitleManager implements JobTitleService {
	
	private JobTitleDao jobTitleDao;
	
	@Autowired
	
	public JobTitleManager(JobTitleDao jobTitleDao) {
		super();
		this.jobTitleDao=jobTitleDao;
	}
	
	@Override
	public  DataResult<List<JobTitle>> getAll() {
		
		return new SuccessDataResult<List<JobTitle>>(this.jobTitleDao.findAll(), "Data Listelendi.");
				
				
	}

	@Override
	public Result add(JobTitle jobTitle) {
		if(this.jobTitleDao.existsByTitle(jobTitle.getTitle())) {
			return new ErrorResult("İş pozisyonu mevcut.");
		}
		this.jobTitleDao.save(jobTitle);
		return new SuccessResult("İş pozisyonu başarılı şekilde eklendi.");
	}
}
