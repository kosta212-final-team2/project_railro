package kosta.web.mvc.member.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import kosta.web.mvc.member.domain.Notice;

@Controller
public class WebSocketController {
	
	@MessageMapping("/chat.notice")
    @SendTo("/topic/public")
    public Notice sendMessage(@Payload Notice notice) {
        return notice;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public Notice addUser(@Payload Notice notice, SimpMessageHeaderAccessor headerAccessor){
        headerAccessor.getSessionAttributes().put("name", notice.getToMemberId());
        return notice;
    }
}
