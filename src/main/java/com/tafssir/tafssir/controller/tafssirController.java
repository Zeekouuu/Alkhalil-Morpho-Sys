package com.tafssir.tafssir.controller;

import com.tafssir.tafssir.model.Tafssir;

import com.tafssir.tafssir.repository.tafssirRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/Tafssir")
public class tafssirController {

	private final tafssirRepo tafssirService;

	public tafssirController(tafssirRepo tafssirService) {
		this.tafssirService = tafssirService;
	}

	@GetMapping (path = "all/{NTafsir}")
	public List<Tafssir> getAllChihaja(@PathVariable Integer NTafsir){
		List<Tafssir> kolch = tafssirService.findByAllDakci(NTafsir);
		return kolch;
	}
	@GetMapping (path= "/getAllTafassir/{num}/{num2}")
	public List<Tafssir> getAllTafassir(@PathVariable Integer num, @PathVariable Integer num2){
		List<Tafssir> allData = tafssirService.findByAllTafssir2(num,num2);
		return allData;
	}
}
