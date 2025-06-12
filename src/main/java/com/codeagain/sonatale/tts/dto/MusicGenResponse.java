package com.codeagain.sonatale.tts.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor

public class MusicGenResponse {
    private String filePath;
    public MusicGenResponse(String filePath) {
        this.filePath = filePath;
    }
    // getter
}
