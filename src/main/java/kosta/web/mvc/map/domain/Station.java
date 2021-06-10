package kosta.web.mvc.map.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Station {
	@Id
	private int id;
	private String station;
	private double lat;
	private double lng;
	private String addr;
	private int citycode;
	
	

}
