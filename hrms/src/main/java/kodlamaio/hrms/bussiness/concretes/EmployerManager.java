package kodlamaio.hrms.bussiness.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.bussiness.abstracts.ActivationCodeService;
import kodlamaio.hrms.bussiness.abstracts.EmployerService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstrats.EmployerDao;
import kodlamaio.hrms.dataAccess.abstrats.UserDao;
import kodlamaio.hrms.entity.concretes.Employer;


@Service

public class EmployerManager implements EmployerService {
	
	private EmployerDao employerDao;
	private UserDao userDao;
	private ActivationCodeService activationCodeService;
	@Autowired
	
	public EmployerManager(EmployerDao employerDao, UserDao userDao,ActivationCodeService activaitonCodeService ) {
		super();
		this.employerDao=employerDao;
		this.userDao=userDao;
		this.activationCodeService=activaitonCodeService;
	}
	

	@Override
	public DataResult<List<Employer>> getAll() {
		return new SuccessDataResult<List<Employer>>(this.employerDao.findAll(),"İş verenler getirildi.");
	}

	@Override
	public Result add(Employer employer) {
		if(employer.getCompanyName()!=null && employer.getWebAddress()!=null && employer.getPhoneNumber()!=null &&
				employer.getPassword()!=null && employer.getPasswordRepeatition()!=null && employer.getEmail()!=null) {
			if(this.userDao.existsByEmail(employer.getEmail())) {
				return new SuccessResult("Email mevcuttur.");
			}else {
				if(employer.getPassword().equals(employer.getPasswordRepeatition())) {
					this.employerDao.save(employer);
					this.activationCodeService.createActivationCode(employer);
					return new SuccessResult("İş veren eklendi.");
				}else {
					return new ErrorResult("Şifreler eşleşmiyor.");
				}
				
			}
			
		}else {
			return new ErrorResult("Bütün alanları doldurunuz.");
			
		}
		
	}

}
