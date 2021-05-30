package kodlamaio.hrms.bussiness.abstracts;

import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entity.concretes.User;

public interface ActivationCodeService {
	void createActivationCode(User user);
	
	Result verifyCode (String code);

}
