package in.ashokit.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import in.ashokit.bindings.UserRequest;
import in.ashokit.entity.User;
import in.ashokit.repository.UserRepository;
import in.ashokit.service.ARService;


@Service
public class ARServiceImpl implements ARService {

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public String createApplication(UserRequest rqst) {
		String resp=null;
		//System.out.println("STATE: "+rqst.getSsn());
		//System.out.println("FIRST NAME: "+rqst.getFname());
		if(getState(rqst.getSsn()).equalsIgnoreCase("invalid-ssn")) {
			resp="You entered invalid ssn ";
		}
		else if(getState(rqst.getSsn())!=null && getState(rqst.getSsn()).equalsIgnoreCase("NEW JERSEY")) {
			User user=new User();
			BeanUtils.copyProperties(rqst, user);
			User u = userRepo.save(user);
			System.out.println(u.getId());
			if(u!=null)
				resp="Application Registered successfully.."+u.getId();
			else
				resp="Application failed to registered..";
		}else {
			resp= "You don't belong to NEW JERSEY";
		}
		return resp;
		
	}
	private static String getState(String ssn) {
		String state="";
		
		String endPointUrl="http://localhost:9090/ssa/ssn/"+ssn;
		HttpHeaders headers=new HttpHeaders();
		headers.add("Accept", "application/json");
		
		HttpEntity entity=new HttpEntity(headers);
		
		RestTemplate rt=new RestTemplate();
		ResponseEntity<String> restEntity = rt.exchange(endPointUrl, HttpMethod.GET,entity,String.class);
		state= restEntity.getBody();
		System.out.println("SSSS: "+state);
		return state;
	}
	@Override
	public List<UserRequest> getAllApplication() {
		List<User> users = userRepo.findAll();
		List<UserRequest> list=new ArrayList<>();
		for(User user:users) {
			UserRequest ur=new UserRequest();
			BeanUtils.copyProperties(user, ur);
			list.add(ur);
		}
		return list;
	}

}
