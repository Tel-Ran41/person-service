package telran.java41.person.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import telran.java41.person.dto.AddressDto;
import telran.java41.person.dto.CityPopulationDto;
import telran.java41.person.dto.PersonDto;
import telran.java41.person.service.PersonService;

@RestController
@RequestMapping("/person")
@AllArgsConstructor
public class PersonController {

	PersonService personService;

	@PostMapping
	public boolean addPerson(@RequestBody PersonDto personDto) {
		return personService.addPerson(personDto);
	}

	@GetMapping("/{id}")
	public PersonDto findPerson(@PathVariable Integer id) {
		return personService.findPersonById(id);
	}

	@DeleteMapping("/{id}")
	public PersonDto removePerson(@PathVariable Integer id) {
		return personService.removePerson(id);
	}

	@PutMapping("/{id}/name/{name}")
	public PersonDto updatePersonName(@PathVariable Integer id, @PathVariable String name) {
		return personService.updatePersonName(id, name);
	}
	
	@PutMapping("/{id}/address")
	public PersonDto updatePersonAddress(@PathVariable Integer id, @RequestBody AddressDto addressDto) {
		return personService.updatePersonAddress(id, addressDto);
	}
	
	@GetMapping("/city/{city}")
	public Iterable<PersonDto> findPersonByCity(@PathVariable String city) {
		return personService.findPersonByCity(city);
	}
	
	@GetMapping("/ages/{ageMin}/{ageMax}")
	public Iterable<PersonDto> findPersonByAges(@PathVariable Integer ageMin, @PathVariable Integer ageMax) {
		return personService.findPersonByAges(ageMin, ageMax);
	}
	
	@GetMapping("/name/{name}")
	public Iterable<PersonDto> findPersonByName(@PathVariable String name) {
		return personService.findPersonByName(name);
	}
	
	@GetMapping("/population/city")
	public Iterable<CityPopulationDto> getCityPopulation() {
		return personService.getCityPopulation();
	}
}
