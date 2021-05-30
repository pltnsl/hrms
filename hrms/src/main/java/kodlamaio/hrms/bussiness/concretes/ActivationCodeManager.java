package kodlamaio.hrms.bussiness.concretes;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.bussiness.abstracts.ActivationCodeService;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstrats.ActivationCodeDao;
import kodlamaio.hrms.entity.concretes.ActivationCode;
import kodlamaio.hrms.entity.concretes.User;
@Service
public class ActivationCodeManager implements ActivationCodeService{
	
	private ActivationCodeDao activationCodeDao;
	
	
	@Autowired
	public ActivationCodeManager(ActivationCodeDao activationCodeDao) {
		super();
		this.activationCodeDao = activationCodeDao;
	}

	@Override
	public void createActivationCode(User user) {
		String activationCode = UUID.randomUUID().toString();
		ActivationCode activationCodes = new ActivationCode();
		activationCodes.setActivationCode(activationCode);
		activationCodes.setUser(user);
		activationCodes.setConfirmedDate(LocalDate.now());
		activationCodeDao.save(activationCodes);
		
		
	}

	@Override
	public Result verifyCode(String code) {
		ActivationCode activationCode = new ActivationCode();
		activationCode=activationCodeDao.getByActivationCode(code);
		if(!activationCodeDao.existsByActivationCode(code)) {
			return new ErrorResult("Böyle bir kod bulunamadı.");
		}
		if(activationCode.isConfirmed()) {
			
			
			return new ErrorResult("Doğrulama işlemi daha önce yapıldı.");
		}
		activationCode.setConfirmed(true);
		activationCode.setConfirmedDate(LocalDate.now());
		activationCodeDao.save(activationCode);
		
		return new SuccessResult("Doğrulama başarılı");
	}

}
