package com.MVNFullStackProject.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {

	    private String name;        // <-- added
	    private String email;
	    private String password;
	    private String phone;
	    private String address;
	    private String role;
	    
	}


