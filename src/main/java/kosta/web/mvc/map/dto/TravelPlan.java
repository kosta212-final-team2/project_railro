package kosta.web.mvc.map.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TravelPlan {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "travel_plan_sequence")
	@SequenceGenerator(sequenceName = "travel_plan_sequence",allocationSize = 1,name = "travel_plan_sequence")
	private int planId;
	
	private String userId;
	private String planName;
	
	private String startDate;
	private String endDate;
	
	@OneToMany(mappedBy = "travelPlan", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<StationPlan> stationPlanList;

	
}
