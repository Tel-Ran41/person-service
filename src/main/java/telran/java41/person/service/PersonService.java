package telran.java41.person.service;

import telran.java41.person.dto.AddressDto;
import telran.java41.person.dto.CityPopulationDto;
import telran.java41.person.dto.PersonDto;

public interface PersonService {
	boolean addPerson(PersonDto personDto);

	PersonDto findPersonById(Integer id);

	PersonDto removePerson(Integer id);

	PersonDto updatePersonName(Integer id, String name);

	PersonDto updatePersonAddress(Integer id, AddressDto addressDto);

	Iterable<PersonDto> findPersonByCity(String city);

	Iterable<PersonDto> findPersonByAges(Integer ageMin, Integer ageMax);

	Iterable<PersonDto> findPersonByName(String name);

	Iterable<CityPopulationDto> getCityPopulation();
}
