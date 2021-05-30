package kodlamaio.hrms.adapters.conceretes;

import java.rmi.RemoteException;
import java.util.Locale;

import org.springframework.stereotype.Service;

import kodlamaio.hrms.adapters.abstracts.CustomerCheckService;
import kodlamaio.hrms.entity.concretes.Candidate;
import tr.gov.nvi.tckimlik.WS.KPSPublicSoapProxy;

@Service
public class MernisServiceAdapter implements CustomerCheckService {
		
	@Override
	public boolean CheckIfRealPerson(Candidate candidate) {
		KPSPublicSoapProxy mernis = new KPSPublicSoapProxy();
        boolean result = true;

        try {
            result = mernis.TCKimlikNoDogrula(
                    Long.parseLong(candidate.getNationalIdentity()),
                    candidate.getName().toUpperCase(new Locale("tr")),
                    candidate.getSurname().toUpperCase(new Locale("tr")), 
                    candidate.getBirthYear().getYear());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return result;
	}

}
