package kodlamaio.hrms.core.adapters;

import java.util.Locale;

import kodlamaio.hrms.business.abstracts.PersonCheckService;
import tr.gov.nvi.tckimlik.WS.KPSPublicSoapProxy;

public class MernisServiceAdapter implements PersonCheckService{

	public boolean checkIfRealPerson(String identityId,String firstName,String lastName,int birthYear) {
		KPSPublicSoapProxy client = new KPSPublicSoapProxy();
boolean result =true;
        try{
            result=
        	
        	 client.TCKimlikNoDogrula(
                    Long.parseLong((identityId)),
                    firstName.toUpperCase(new Locale("tr")),
                    lastName.toUpperCase(new Locale("tr")),
                    birthYear);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;

    }
}
