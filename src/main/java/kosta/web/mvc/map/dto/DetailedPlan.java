package kosta.web.mvc.map.dto;

import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DetailedPlan {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "detailed_plan_sequence")
	@SequenceGenerator(sequenceName = "detailed_plan_sequence",allocationSize = 1,name = "detailed_plan_sequence")
	private int detatiledPlanId;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "stationPlanNum" )
	private StationPlan stationPlan;
	
	private String placeName;
	private String placeAddr;
	private int detailedOrder;
	private String placeType;
	private int travelTime;
	private double placeLat;
	private double placeLng;
}
