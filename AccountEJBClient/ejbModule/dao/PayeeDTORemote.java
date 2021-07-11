package dao;

import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

@Remote
public interface PayeeDTORemote {
	public List<Map<String, String>> custAllPayees(int custId);

}
