package kodlamaio.hrms.bussiness.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.adapters.abstracts.CustomerCheckService;
import kodlamaio.hrms.bussiness.abstracts.ActivationCodeService;
import kodlamaio.hrms.bussiness.abstracts.CandidateService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstrats.CandidateDao;
import kodlamaio.hrms.dataAccess.abstrats.UserDao;
import kodlamaio.hrms.entity.concretes.Candidate;


@Service

public class CandidateManager implements CandidateService{
	private UserDao userDao;
	private CustomerCheckService customerCheckService;
	private CandidateDao candidateDao;
	private ActivationCodeService activationCodeService;
	@Autowired
	public CandidateManager( CandidateDao candidateDao, CustomerCheckService customerCheckService, UserDao userDao, ActivationCodeService activationCodeService) {
		this.candidateDao= candidateDao;
		this.customerCheckService=customerCheckService;
		this.userDao=userDao;
		this.activationCodeService=activationCodeService;
	}
	

	@Override
	public DataResult<List<Candidate>> getAll() {
		return new SuccessDataResult<List<Candidate>>(this.candidateDao.findAll(), "Aday listesi getirildi.");
	}

	@Override
	public Result add(Candidate candidate) {
		if(candidate.getName()!=null && candidate.getSurname()!=null && candidate.getEmail()!=null && candidate.getBirthYear()!=null
				&& candidate.getPassword()!= null && candidate.getNationalIdentity()!=null && candidate.getPasswordRepeatition()!=null){
			if(candidate.getPassword().equals(candidate.getPasswordRepeatition())) {
				System.out.println(candidate.getBirthYear());
				if(customerCheckService.CheckIfRealPerson(candidate)) {
					if(this.candidateDao.existsByNationalIdentity(candidate.getNationalIdentity())) {
						return new ErrorResult("Tc kimlik numaras?? bulunmaktad??r.");
					}else {
						if(this.userDao.existsByEmail(candidate.getEmail())) {
							return new ErrorResult ("Email mevcuttur.");
						}else {
							this.candidateDao.save(candidate);
							this.activationCodeService.createActivationCode(candidate);
							
							return new SuccessResult("Kullac?? eklendi.");
						}
					
					}			
				}else {
					return new ErrorResult("Ki??i bilgileri e??le??miyor, l??tfen kontrol ediniz.");
				}
					
			}else {
				return new ErrorResult("??ifreler e??le??miyor, l??tfen kontrol ediniz.");
			}
		
			
			
		}else {
			return new ErrorResult("B??t??n alanlar?? doldurunuz.");
		}
		
		
	}

}
