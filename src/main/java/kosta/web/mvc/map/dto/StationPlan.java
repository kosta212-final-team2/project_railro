package kosta.web.mvc.map.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import kosta.web.mvc.map.domain.Station;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class StationPlan {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "station_plan_sequence")
	@SequenceGenerator(sequenceName = "station_plan_sequence",allocationSize = 1,name = "station_plan_sequence")
	private int stationPlanId;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "planNum")
	private TravelPlan travelPlan;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="stationNum")
	private Station trainStation;
	private String travelDate;
	private int travelOrder;// 한 여행계획에서의 여행순서 
	
	@OneToMany(mappedBy = "stationPlan", cascade = CascadeType.ALL)
	private List<DetailedPlan> detailedPlanList;
}
