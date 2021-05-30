package kodlamaio.hrms.dataAccess.abstrats;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entity.concretes.ActivationCode;

public interface ActivationCodeDao extends JpaRepository<ActivationCode, Integer> {
	boolean existsByActivationCode(String code);
	ActivationCode getByActivationCode(String code);

}
