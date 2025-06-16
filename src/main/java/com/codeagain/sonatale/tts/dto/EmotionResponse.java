package com.codeagain.sonatale.tts.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmotionResponse {
    private String emotion;

    public EmotionResponse(String emotion) {
        this.emotion = emotion;
    }}

