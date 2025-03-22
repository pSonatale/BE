package com.codeagain.sonatale.tts.application;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@Service
public class AudioService {

    private final Map<String, String> audioMap = new HashMap<>();

    public AudioService() {
        audioMap.put("hi", "static/a1.m4a");
        audioMap.put("by", "static/a2.m4a");
        audioMap.put("re", "static/a3.m4a");
    }

    public Resource getAudioByText(String text) {
        String filePath = audioMap.getOrDefault(text.toLowerCase(), "static/default.m4a");
        return new ClassPathResource(filePath);
    }

    public boolean saveAudioFile(String text, MultipartFile file) {
        try {
            String path = "src/main/resources/static/" + text + ".m4a";
            try (FileOutputStream fos = new FileOutputStream(path)) {
                fos.write(file.getBytes());
            }
            audioMap.put(text.toLowerCase(), "static/" + text + ".m4a");
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public List<String> extractKeywords(String fullText) {
        List<String> keywords = List.of("행복", "슬퍼", "신나","잠와");
        String lowerText = fullText.toLowerCase();

        List<String> result = new ArrayList<>();
        int index = 0;

        while (index < lowerText.length()) {
            int minPos = Integer.MAX_VALUE;
            String found = null;

            for (String keyword : keywords) {
                int pos = lowerText.indexOf(keyword, index);
                if (pos != -1 && pos < minPos) {
                    minPos = pos;
                    found = keyword;
                }
            }

            if (found != null) {
                result.add(found);
                index = minPos + found.length();
            } else {
                break;
            }
        }

        return result;
    }

}
