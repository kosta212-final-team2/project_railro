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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import kosta.web.mvc.map.domain.Station;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "travelPlan")
public class StationPlan {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "station_plan_sequence")
	@SequenceGenerator(sequenceName = "station_plan_sequence",allocationSize = 1,name = "station_plan_sequence")
	private int stationPlanId;
	
	@ManyToOne
	@JoinColumn(name = "planNum")
	@JsonBackReference
	private TravelPlan travelPlan;
	
	@ManyToOne
	@JoinColumn(name="stationNum")
	private Station trainStation;
	private String travelDate;
	private int travelOrder;// 한 여행계획에서의 여행순서 
	
	@OneToMany(mappedBy = "stationPlan", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<DetailedPlan> detailedPlanList;
	
	public StationPlan(int spid) {
		this.stationPlanId=spid;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		StationPlan plan = (StationPlan)obj;
		if(this.stationPlanId == plan.getStationPlanId()) {
			return true; 
		}
		else {
			return false;
		}
	}
	
	
	
	
}
