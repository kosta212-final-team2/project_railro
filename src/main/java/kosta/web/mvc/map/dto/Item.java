package kosta.web.mvc.map.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Item {
	private int adultcharge;
	private String arrplacename;
	private Date arrplandtime;
	private String depplacename;
	private Date depplandtime;
	private String traingradename;
	private int trainno;
	private String duration;
}
