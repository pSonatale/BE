# 🎵 Sonatale - 감정 기반 유아 동화 BGM 생성 앱

**Sonatale**는 유아 동화 낭독 시 실시간 감정 분석을 통해, 어린이의 감정 몰입을 돕는 감정 기반 배경음악(BGM)을 자동 생성하는 AI 애플리케이션입니다. 교사의 수업 준비 부담을 줄이고, 아이들의 정서 발달과 창의력을 동시에 촉진합니다.

<p align="center">
  <img src="https://github.com/user-attachments/assets/fc1621e6-50f7-45f7-b70a-0be928134aa2" width="200"/>
  <img src="https://github.com/user-attachments/assets/e686ae95-020d-4a0d-a68f-f8adf0394920" width="200"/>
  <img src="https://github.com/user-attachments/assets/192b1f12-46d0-48c7-8fa6-72cb7b1cb6e8" width="200"/>
</p>

**배포주소** : 현재는 미운영중입니다, 다시 또 만나요! 👋

[<img src="https://img.shields.io/badge/프로젝트 기간-2024.09~2025.06.16-F95700?style=flat&logo=&logoColor=white" />]()

<br>

## 👥 팀원 소개

|         Backend / AI         |         Frontend         |         Frontend         |         AI         |
|:----------------------------:|:------------------------:|:------------------------:|:------------------:|
| <img src="https://avatars.githubusercontent.com/u/144196895?v=4" width=200px alt="강민재"/> | <img src="https://avatars.githubusercontent.com/u/165664062?v=4" width=200px alt="김서영"/> | <img src="https://avatars.githubusercontent.com/u/113006871?v=4" width=200px alt="김혜정"/> | <img src="https://avatars.githubusercontent.com/u/106727015?v=4" width=200px alt="홍강래"/> |
| [@KMJ200](https://github.com/KMJ200) | [@syoung01](https://github.com/syoung01) | [@mjeong21](https://github.com/mjeong21) | [@HongGR](https://github.com/HongGR) |
| 강민재 | 김서영 | 김혜정 | 홍강래 |

<br>
                        
##  🛠️개발 환경

<table>
    <thead>
        <tr>
            <th>분류</th>
            <th>기술 스택</th>
        </tr>
    </thead>
    <tbody>
        <tr>
             <td>
                  <p>Environment</p>
                 </td>
                         <td>
                 <img src="https://img.shields.io/badge/Github-181717?style=for-the-badge&logo=Github&logoColor=ffffff">
                 <img src="https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=git&logoColor=ffffff">
            </td>
            </tr>
        <tr>
            <td>
                  <p>Development</p>
            </td>
            <td>
                  <img src="https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=OpenJDK&logoColor=white" />
                  <img src="https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=SpringBoot&logoColor=white" />
                  <img src="https://img.shields.io/badge/HuggingFace-FFBF00?style=for-the-badge&logo=HuggingFace&logoColor=black" />
                <img src="https://img.shields.io/badge/Python-3776AB?style=for-the-badge&logo=Python&logoColor=white" />
                <img src="https://img.shields.io/badge/PyTorch-EE4C2C?style=for-the-badge&logo=PyTorch&logoColor=white" />
            </td>
        </tr>
        <tr>
            <td>
                <p>Communication</p>
            </td>
            <td>
                <img src="https://img.shields.io/badge/Notion-000000?style=for-the-badge&logo=Notion">
                <img src="https://img.shields.io/badge/Figma-F24E1E?style=for-the-badge&logo=Figma&logoColor=ffffff">
                <img src="https://img.shields.io/badge/Discord-5865F2?style=for-the-badge&logo=Discord&logoColor=ffffff">
            </td>
        </tr>
    </tbody>

</table>

<br>

## 🌟 주요 기능

- 🎙 실시간 음성 → 텍스트(STT) 변환 (Google Speech-to-Text API 사용)
- 🧠 다중 감정 분석 (BERT 기반 43개 감정 분류 모델)
- 🎼 감정 기반 음악 생성 (Meta MusicGen 기반)
- 💾 동화 + BGM 저장 및 재생 기능
- 📱 직관적인 모바일 UI/UX (유아 대상 최적화)

<br>

## 📄 API 명세

Sonatale 프로젝트의 주요 API 명세는 아래와 같습니다.

### 🎵 P-1: 감정 기반 음원 재생
- **Method**: `GET`  
- **URL**: `/tts/play`  
- **설명**: 텍스트 입력에 대응하는 감정 기반 `.m4a` 음원을 스트리밍으로 반환
- **요청 예시**: GET /tts/play?text=너무 우울해
  
| 필드명 | 타입   | 필수 | 설명                          |
|--------|--------|------|-------------------------------|
| text   | String | ✅    | 감정 분석용 입력 텍스트 (키) |

-**응답**: Content-Type: `audio/m4a` / Body: 감정에 매핑된 음원 스트리밍

### 📚 P-4: 저장된 텍스트-음원 매핑 목록 조회
- **Method**: `GET`  
- **URL**: `/tts/list`  
- **설명**: 저장된 키-음원 매핑 목록을 반환
- **요청 예시**: GET /tts/list

| 필드명 | 타입   | 필수 | 설명                          |
|--------|--------|------|-------------------------------|
| key   | String | ✅    | 저장될 음원 이름과 매핑됨 |
| value   | String | ✅    | 확장자 .m4a 권장 |

-**응답 예시**: { "hi": "static/a1.m4a" }

### 🎼 P-5: 텍스트 기반 AI 음악 생성 (MusicGen)
- **Method**: `POST`  
- **URL**: `/api/music`  
- **설명**: 사용자 입력 프롬프트와 길이를 기반으로 MusicGen 모델로 음악 생성
- **요청 예시**: POST /api/music { "promt": "불안", "duration": 10 }

| 필드명 | 타입   | 필수 | 설명                          |
|--------|--------|------|-------------------------------|
| prompt   | String | ✅    | 음악 생성 프롬프트 |
| duration   | int | ✅    | 생성할 음악 길이 (초) |

-**응답 예시**: { "filePath": "/music/output_1718200112.wav", "duration": 10, "prompt": "불안"}

### 💬 P-6: 텍스트 감정 분석
- **Method**: `GET`  
- **URL**: `/api/emotion`  
- **설명**: 입력 텍스트의 감정을 분석하여 라벨 반환
- **요청 예시**: GET /api/emotion?text=나는 너무 피곤해

| 필드명 | 타입   | 필수 | 설명                          |
|--------|--------|------|-------------------------------|
| text   | String | ✅    | 분석할 텍스트 |

-**응답 예시**: { "피곤함" }

<br>

## 🧐 프로젝트 회고

### 🔹 아쉬웠던 점
- **초기 통합 구조 설계 미흡**  
  : Spring에서 Python 기반 MusicGen을 직접 실행하는 구조 설계에 시간이 지체되었고, 예상보다 복잡한 연동 작업으로 디버깅에 많은 시간이 소요됨
- **감정-음원 매핑 지연**  
  : 감정 분석 후 음원 파일을 연결하는 구조를 뒤늦게 구현하게 되어 전체 흐름 테스트가 늦어짐

### 🔹 잘했던 점
- **기획에서 기술까지 일관된 흐름 유지**  
  : 감정 → 분석 → 음악 매핑 흐름을 처음부터 끝까지 설계하며 기술적 흐름과 서비스 기획을 일치시킴
- **감정 모델의 실전 적용**  
  : Huggingface의 `kote_for_easygoing_people` 모델을 성공적으로 REST API화하여 응용 가능성을 확인함
- **구조화된 백엔드 설계**  
  : `MusicGenController`, `EmotionController`, `TtsController` 등 역할 기반 클래스로 유지보수 가능한 구조 구현
- **백엔드-프론트 간 커뮤니케이션 원활**  
  : GET/POST 요청 설계 및 테스트를 빠르게 반복하며 API 명세 정리를 완료함

### 🔹 느낀 점
- **AI 서비스의 현실성 체감**  
  : 단순한 모델 호출이 아닌, 사용자의 텍스트 맥락을 읽고 감정에 맞는 음원을 제공하는 시스템을 만드는 데서 AI 서비스의 실제 구현 난이도를 체감함
- **STT, 감정 분석, 음악 추천의 연결성 실현**  
  : 서로 다른 기술이 유기적으로 연결될 때의 흐름 설계 중요성을 깨달음
- **음원 기반 서비스의 사용자 경험 설계 필요성**  
  : 음악의 길이, 전환 시간, 사운드 퀄리티 등이 사용자 만족도에 영향을 줌을 실감함

### 🔹 개선할 점
- **정밀한 감정-음원 매핑 개선**  
  : 43개 감정에 단순히 1:1로 연결된 음원이 아니라, 사용자 성향에 따른 유동적인 음악 추천이 가능하도록 개선 필요
- **사용자 피드백 기반 UI 반복 개선**  
  : 피드백에 따라 UI 전환 속도나 음악 재생 방식에 대한 즉각적 개선을 고려해야 함




