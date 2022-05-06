package in.ashokit.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.bindings.UserRequest;
import in.ashokit.service.ARService;



@RestController
@RequestMapping("registration")
public class ARRestController {

	@Autowired
	private ARService service;
	
	//# 1.user registration
	
	@PostMapping("/user")
	public ResponseEntity<?> registerUser(@RequestBody UserRequest rqst){
		
		ResponseEntity<?> resp=null;
		
		String status = service.createApplication(rqst);
		if(status!=null)
			resp=new ResponseEntity<String>(status, HttpStatus.OK);
		else
			resp=new ResponseEntity<String>("something went wrong ..", HttpStatus.OK);
		return resp;
	}
	@GetMapping("/applications")
	public ResponseEntity<?> getAllApplications(){
		List<UserRequest> list=service.getAllApplication();
		return new ResponseEntity<List<UserRequest>>(list, HttpStatus.OK);
	}
}
