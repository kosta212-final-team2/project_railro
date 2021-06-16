package kosta.web.mvc.member.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;


import kosta.web.mvc.member.domain.Notice;
@Component
public class WebSocketEventListener {
	
	@Autowired
	private SimpMessageSendingOperations messagingTemplate;
	
	private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);
	
	 @EventListener
	    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
	        logger.info("Received a new web socket connection");
	    }

	    @EventListener
	    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
	        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

	        String name = (String) headerAccessor.getSessionAttributes().get("name");
			/*
			 * if(name != null) { logger.info("User Disconnected : " + username);
			 * 
			 * Notice notice = new Notice(); notice.setToMemberId(name);
			 * 
			 * messagingTemplate.convertAndSend("/topic/public", notice); }
			 */
	    }
}
