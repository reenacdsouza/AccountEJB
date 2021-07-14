package dao;

import java.util.Set;
import java.util.Map;

import javax.ejb.Remote;

@Remote
public interface PayeeDTORemote {
	public Set<Map<String, String>> custAllPayees(int custId);

}
