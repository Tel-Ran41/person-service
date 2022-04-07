package telran.java41.person.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CityPopulation {
	String city;
	Long numberOfPersons;

	public CityPopulation(String city, Long numberOfPersons) {
		this.city = city;
		this.numberOfPersons = numberOfPersons;
	}

}
