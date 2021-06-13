package kosta.web.mvc.map.dto;

import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TrainStation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "train_station_sequence")
	@SequenceGenerator(sequenceName = "train_station_sequence",allocationSize = 1,name = "train_station_sequence")
	private int stationId;
	
	private String stationName;
	private double stationLat;
	private double stationLng;
	private String stationAddr;
	private String stationNodeId;
	

}
