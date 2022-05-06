package in.ashokit.service;

import java.util.List;

import in.ashokit.bindings.UserRequest;
import in.ashokit.entity.User;

public interface ARService {

	public String createApplication(UserRequest rqst);
	public List<UserRequest> getAllApplication();
}
