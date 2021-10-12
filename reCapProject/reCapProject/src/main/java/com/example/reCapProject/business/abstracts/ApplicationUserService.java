package com.example.reCapProject.business.abstracts;

import java.util.List;


import com.example.reCapProject.core.utilities.result.DataResult;
import com.example.reCapProject.entities.abstracts.ApplicationUser;



public interface ApplicationUserService {


    DataResult<List<ApplicationUser >> getAll();
	
	
	
}
