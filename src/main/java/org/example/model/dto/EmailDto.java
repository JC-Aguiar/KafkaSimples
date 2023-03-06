package org.example.model.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.ToString;
import lombok.Value;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Value
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmailDto implements Serializable {

    String email;
    String userName;
    List<String> targetEmails = new ArrayList<>();
    LocalDateTime sendDate;

    public void addEmail(String email) {
        targetEmails.add(email);
    }
    public void addEmails(List<String> emails) {
        targetEmails.addAll(emails);
    }

}
