package strike.ex.persistence.domain;

import lombok.Data;

@Data
public class ChatMessage {
    private String sender;
    private String content;
    private String type;
    private String recipient;
    private String channel;

}
