package com.codeagain.sonatale.tts.controller;


import com.codeagain.sonatale.tts.dto.*;
import com.codeagain.sonatale.tts.application.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/music/emotion")
public class EmotionMusicController {

    @Autowired
    private EmotionAnalysisService emotionAnalysisService;

    @Autowired
    private MusicGenService musicGenService;

    @GetMapping
    public MusicGenResponse process(@RequestParam("text") String text) throws Exception {
        String emotion = emotionAnalysisService.analyzeEmotion(text);
        MusicGenRequest request = new MusicGenRequest(emotion, 5);  // 감정 이름으로 음악 생성
        return musicGenService.generateMusic(request);
    }
}