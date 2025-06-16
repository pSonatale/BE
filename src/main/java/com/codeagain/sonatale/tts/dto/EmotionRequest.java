package com.codeagain.sonatale.tts.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmotionRequest {
    private String text;

    public EmotionRequest(String text) {
        this.text = text;
    }

}
