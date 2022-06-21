package com.rnl.ms.ms_users.producers;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CustomMessage(
        @JsonProperty("ownerRef") String ownerRef,
        @JsonProperty("emailFrom") String emailFrom,
        @JsonProperty("emailTo") String emailTo,
        @JsonProperty("subject") String subject,
        @JsonProperty("text") String text)
        implements Serializable
{
}
