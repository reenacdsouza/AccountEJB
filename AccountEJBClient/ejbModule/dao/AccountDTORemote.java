package dao;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

import javax.ejb.Remote;

@Remote
public interface AccountDTORemote {
	public Set<Map<String, String>> custAllAccounts(int custId);
	
	public boolean createAccount(int custId, String type, BigDecimal deposit);

}
