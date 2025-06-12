package com.codeagain.sonatale.tts.application;

import com.codeagain.sonatale.tts.dto.MusicGenRequest;
import com.codeagain.sonatale.tts.dto.MusicGenResponse;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class MusicGenService {

    public String generate(String prompt, int duration) throws IOException, InterruptedException {
        String fileName = "output_" + System.currentTimeMillis() + ".wav";
        String outputPath = new File("src/main/resources/static/music/" + fileName).getAbsolutePath();

        String pythonPath = "/Users/dd/Desktop/psonatail/venv/bin/python";
        ProcessBuilder pb = new ProcessBuilder(
                pythonPath, "musicgen_runner.py",
                prompt, String.valueOf(duration), outputPath
        );
        pb.directory(new File(System.getProperty("user.dir")));
        pb.redirectErrorStream(true);

        Process process = pb.start();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            reader.lines().forEach(System.out::println);
        }

        int exitCode = process.waitFor();
        if (exitCode != 0) throw new RuntimeException("MusicGen 실행 실패");

        return "/music/" + fileName;  // static 경로 기준
    }

    public MusicGenResponse generateMusic(MusicGenRequest request) {
        try {
            String path = generate(request.getPrompt(), request.getDuration());
            return new MusicGenResponse(path);
        } catch (Exception e) {
            return new MusicGenResponse("error: " + e.getMessage());
        }
    }
}
