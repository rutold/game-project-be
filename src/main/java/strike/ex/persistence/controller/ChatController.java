package strike.ex.persistence.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import strike.ex.persistence.domain.ChatMessage;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(ChatMessage chatMessage) {
        return chatMessage;
    }

    @MessageMapping("/whisper")
    public void whisper(ChatMessage chatMessage) {
        messagingTemplate.convertAndSendToUser(chatMessage.getRecipient(), "/queue/reply", chatMessage);
    }

    @MessageMapping("/chat.sendMessage")
    public void processMessage(ChatMessage chatMessage) {
        if ("CHAT".equals(chatMessage.getType())) {
            messagingTemplate.convertAndSend(chatMessage.getChannel(), chatMessage);
        }
    }
}