package com.challenge.magalu.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Channel {

    @Id
    private Long id;

    private String description;

    public enum Values {
        EMAIL(1L, "email"),
        SMS(2L, "sms"),
        PUSH(3L, "push"),
        WHATSAPP(4L, "whatsapp");

        private Long id;
        private String description;

        Values(Long id, String description) {
            this.id = id;
            this.description = description;
        }

        public Channel toChannel() {
            return new Channel(id, description);
        }
    }

}
