package telran.java41.person.service;

import java.time.LocalDate;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import telran.java41.person.dao.PersonRepository;
import telran.java41.person.dto.AddressDto;
import telran.java41.person.dto.CityPopulationDto;
import telran.java41.person.dto.PersonDto;
import telran.java41.person.dto.exceptions.EntityNotFoundException;
import telran.java41.person.model.Address;
import telran.java41.person.model.Person;

@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {
	
	PersonRepository personRepository;
	ModelMapper modelMapper;

	@Override
	@Transactional
	public boolean addPerson(PersonDto personDto) {
		if(personRepository.existsById(personDto.getId())) {
			return false;
		}
		personRepository.save(modelMapper.map(personDto, Person.class));
		return true;
	}

	@Override
	public PersonDto findPersonById(Integer id) {
		Person person = personRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
		return modelMapper.map(person, PersonDto.class);
	}

	@Override
	@Transactional
	public PersonDto removePerson(Integer id) {
		Person person = personRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
		personRepository.delete(person);
		return modelMapper.map(person, PersonDto.class);
	}

	@Override
	@Transactional
	public PersonDto updatePersonName(Integer id, String name) {
		Person person = personRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
		person.setName(name);
		return modelMapper.map(person, PersonDto.class);
	}

	@Override
	@Transactional
	public PersonDto updatePersonAddress(Integer id, AddressDto addressDto) {
		Person person = personRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
		person.setAddress(modelMapper.map(addressDto, Address.class));
		return modelMapper.map(person, PersonDto.class);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<PersonDto> findPersonByCity(String city) {
		return personRepository.findByAddressCity(city)
				.map(p -> modelMapper.map(p, PersonDto.class))
				.collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<PersonDto> findPersonByAges(Integer ageMin, Integer ageMax) {
		return personRepository.findByBirthDateBetween(LocalDate.now().minusYears(ageMax), LocalDate.now().minusYears(ageMin))
				.map(p -> modelMapper.map(p, PersonDto.class))
				.collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<PersonDto> findPersonByName(String name) {
		return personRepository.findByName(name)
				.map(p -> modelMapper.map(p, PersonDto.class))
				.collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<CityPopulationDto> getCityPopulation() {
		 
//				My decision:
//		return personRepository.findAll().stream()
//				.map(p -> p.getAddress().getCity())
//				.collect(Collectors.groupingBy(c -> c, Collectors.counting()))
//				.entrySet().stream()
//				.map(p -> new CityPopulationDto(p.getKey(), p.getValue()))
//				.collect(Collectors.toList());
		
//		Edward's decision:
		return	personRepository.getCityPopulation();
	}
}
