package in.ashokit.bindings;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UserRequest {

	private String fname;
	private String lname;
	private String email;
	private String phone;
	private LocalDate dob;
	private String gender;
	private String ssn;
	
	
}
