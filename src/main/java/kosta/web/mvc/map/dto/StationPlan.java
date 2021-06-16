package kosta.web.mvc.map.dto;

import java.time.LocalDate;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

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
public class StationPlan {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "station_plan_sequence")
	@SequenceGenerator(sequenceName = "station_plan_sequence",allocationSize = 1,name = "station_plan_sequence")
	private int stationPlanId;
	
	@ManyToOne
	@JoinColumn(name = "planNum")
	private TravelPlan travelPlan;
	
	@ManyToOne
	@JoinColumn(name="stationNum")
	private TrainStation trainStation;
	private LocalDate travelDate;
	private int travelOrder;
	
	@OneToMany(mappedBy = "stationPlan", cascade = CascadeType.ALL)
	private List<DetailedPlan> detailedPlanList;
	
	public StationPlan(int spid) {
		this.stationPlanId=spid;
	}
}
