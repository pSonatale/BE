package com.codeagain.sonatale.tts.controller;

import com.codeagain.sonatale.tts.application.MusicGenService;
import com.codeagain.sonatale.tts.dto.MusicGenRequest;
import com.codeagain.sonatale.tts.dto.MusicGenResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/music")
@RequiredArgsConstructor
public class MusicGenController {

    private final MusicGenService musicGenService;

    @PostMapping
    public ResponseEntity<MusicGenResponse> generate(@RequestBody MusicGenRequest request) {
        MusicGenResponse response = musicGenService.generateMusic(request);
        return ResponseEntity.ok(response);
    }
}

