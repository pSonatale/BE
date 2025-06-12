package com.codeagain.sonatale.tts.application;


import org.springframework.stereotype.Service;
import java.io.*;
import java.util.*;

@Service
public class EmotionAnalysisService {

    public String analyzeEmotion(String text) throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder(
                "venv/bin/python",
                "emotion_runner.py", text
        );
        pb.redirectErrorStream(true);
        Process process = pb.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        String result = null;
        while ((line = reader.readLine()) != null) {
            result = line;
        }

        process.waitFor();
        return result != null ? result : "unknown";
    }
}
