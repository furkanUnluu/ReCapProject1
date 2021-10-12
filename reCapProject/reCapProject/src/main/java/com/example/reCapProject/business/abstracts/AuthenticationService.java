package com.example.reCapProject.business.abstracts;

import com.example.reCapProject.core.utilities.result.Result;
import com.example.reCapProject.entities.request.get.CreateLoginRequest;

public interface AuthenticationService {
	
	Result logIn(CreateLoginRequest createLoginRequest);
}
