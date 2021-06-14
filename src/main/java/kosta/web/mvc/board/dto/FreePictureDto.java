package kosta.web.mvc.board.dto;

import java.time.LocalDate;

import kosta.web.mvc.board.domain.FreeBoard;
import kosta.web.mvc.board.domain.FreePicture;
import lombok.Getter;

@Getter
public class FreePictureDto {

	private Long freePno;
	private String freePicName;
	private String freePicSaveName;
	private String freePicURL;
	private Long freePicSize;
	private String freePicEXT;
	private LocalDate freePicRegdate;
	private FreeBoard freeBoard;
	
	public FreePictureDto(FreePicture freePicture) {
		freePno = freePicture.getFreePno();
		freePicName = freePicture.getFreePicName();
		freePicSaveName = freePicture.getFreePicSaveName();
		freePicURL = freePicture.getFreePicURL();
		freePicSize = freePicture.getFreePicSize();
		freePicEXT = freePicture.getFreePicEXT();
		freePicRegdate = freePicture.getFreePicRegdate();
		freeBoard = freePicture.getFreeBoard();
	}
}
