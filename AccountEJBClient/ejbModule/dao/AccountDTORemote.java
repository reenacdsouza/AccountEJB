package dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;
import java.util.Set;

import javax.ejb.Remote;

@Remote
public interface AccountDTORemote {
	public Set<Map<String, String>> custAllAccounts(int custId);

	public boolean createAccount(int custId, String type, BigDecimal deposit);

	public Map<String, String> withdraw(BigInteger accountnumber, BigDecimal withdrawal);

	public Map<String, String> deposit(BigInteger accountnumber, BigDecimal deposit);
}
