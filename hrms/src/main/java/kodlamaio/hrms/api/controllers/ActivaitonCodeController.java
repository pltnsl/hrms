package kodlamaio.hrms.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.bussiness.abstracts.ActivationCodeService;
import kodlamaio.hrms.core.utilities.results.Result;



@RestController
@RequestMapping("/activationCode")

public class ActivaitonCodeController {
	private ActivationCodeService activationCodeService;
	@Autowired
	public ActivaitonCodeController(ActivationCodeService activationCodeService) {
		super();
		this.activationCodeService = activationCodeService;
	}
	@PutMapping("/{activationCode}")
	public Result verifyCode(@PathVariable("activationCode") String activationCode) {
		return activationCodeService.verifyCode(activationCode);
		
	}

}
