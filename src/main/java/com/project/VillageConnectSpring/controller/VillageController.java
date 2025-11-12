package com.project.VillageConnectSpring.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.VillageConnectSpring.model.Village;
import com.project.VillageConnectSpring.repository.VillageRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class VillageController {
	@Autowired
    private VillageRepository villageRepo;
	
	@GetMapping("/villageData")
	public String getVillageData(Model model, HttpSession session) {
		String villageName=(String) session.getAttribute("villageName");
		
		if(villageName==null) {
			return "redirect:/login.html";
		}
		Optional<Village> villageOpt=villageRepo.findByVillageName(villageName);
		
		if(villageOpt.isPresent()) {
			Village village=villageOpt.get();
			model.addAttribute("village", village);
		}
		return "villageData";
	}

}
