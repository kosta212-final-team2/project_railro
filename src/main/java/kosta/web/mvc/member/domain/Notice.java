package kosta.web.mvc.member.domain;

import java.time.LocalDateTime;

public class Notice {

    private String toMemberId;
    private String text;
    private LocalDateTime regdate;
    
    
	
    public LocalDateTime getRegdate() {
		return regdate;
	}
	public void setRegdate(LocalDateTime regdate) {
		this.regdate = regdate;
	}

	public String getToMemberId() {
		return toMemberId;
	}
	public void setToMemberId(String toMemberId) {
		this.toMemberId = toMemberId;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
    
}