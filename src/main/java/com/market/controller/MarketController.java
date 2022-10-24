package com.market.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.market.Entitys.Cars;
import com.market.dao.CarDao;

@RestController
@RequestMapping("/market")
public class MarketController {

	@Autowired
	private CarDao carDao;
	@GetMapping
	public ResponseEntity<List<Cars>> getCars(){
		
		List<Cars> car = carDao.findAll();  
		return ResponseEntity.ok(car);
	}
	
	@GetMapping(value="{carsId}")
	public ResponseEntity<Cars> getCarsById(@PathVariable("carsId") Long carsId){
		Optional<Cars> optionalCars = carDao.findById(carsId); 
		if(optionalCars.isPresent()) {
			return ResponseEntity.ok(optionalCars.get());
		}else {
			return ResponseEntity.noContent().build();
		}
	}
	    
	@PostMapping
	public ResponseEntity<Cars> createCars(@RequestBody Cars cars){
	   Cars newcars = carDao.save(cars);
	   return ResponseEntity.ok(newcars);
	}
	
	@DeleteMapping(value="{carsId}")
    public ResponseEntity<Void> deleteCars(@PathVariable("carsId") Long carsId){
	   carDao.deleteById(carsId);
       return ResponseEntity.ok(null);
    }
	
	@PutMapping
	public ResponseEntity<Cars> updateCars(@RequestBody Cars cars){
	    Optional<Cars> optionalCars = carDao.findById(cars.getId());
	    if(optionalCars.isPresent()) {
	        Cars updateCars = optionalCars.get();
	        updateCars.setName(cars.getName());
	        carDao.save(updateCars);
	        return ResponseEntity.ok(updateCars);
	    }else {
	        return ResponseEntity.notFound().build();
	    }
	}
}
