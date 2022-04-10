package telran.java41.person.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CityPopulationDto {

	String city;
	Long numberOfPersons;
}
