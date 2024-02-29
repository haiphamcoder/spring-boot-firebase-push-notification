package com.haiphamcoder.springbootfirebasepushnotification.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notice implements Serializable {
    private String subject;
    private String content;
    private String image;
    private Map<String, String> data;
    private List<String> registrationTokens;
}
