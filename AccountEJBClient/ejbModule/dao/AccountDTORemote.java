package dao;

import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

@Remote
public interface AccountDTORemote {
	public List<Map<String, String>> allAccounts();

}
