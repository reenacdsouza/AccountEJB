package dao;

import java.util.Map;

import javax.ejb.Remote;

@Remote
public interface LoginDTORemote {
	public Map<String, String> verifyUserAuth(String username, String password);

}
