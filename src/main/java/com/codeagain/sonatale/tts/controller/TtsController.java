package com.codeagain.sonatale.tts.controller;

import com.codeagain.sonatale.tts.application.AudioService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tts")
public class TtsController {

    private final AudioService audioService;

    public TtsController(AudioService audioService) {
        this.audioService = audioService;
    }

    @GetMapping("/play")
    public ResponseEntity<Resource> playAudio(@RequestParam String text) {
        Resource audio = audioService.getAudioByText(text);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "audio/mp4")
                .body(audio);
    }
    @GetMapping("/list")
    public ResponseEntity<Map<String, String>> getAudioList() {
        Map<String, String> audioList = audioService.getAudioMap();
        return ResponseEntity.ok(audioList);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadAudio(@RequestParam String text,
                                              @RequestParam MultipartFile file) {
        boolean result = audioService.saveAudioFile(text, file);
        if (result) {
            return ResponseEntity.ok("{\"message\": \"음원이 성공적으로 등록되었습니다.\"}");
        }
        return ResponseEntity.badRequest().body("{\"message\": \"파일 업로드 실패\"}");
    }

    @PostMapping("/analyze")
    public ResponseEntity<Map<String, Object>> analyzeText(@RequestBody String fullText) {
        List<String> keywords = audioService.extractKeywords(fullText);

        Map<String, Object> response = new HashMap<>();
        response.put("keywords", keywords);

        return ResponseEntity.ok(response);
    }

}
