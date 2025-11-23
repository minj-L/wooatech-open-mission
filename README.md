# 📈 실시간 주식 거래 시뮬레이터

웹 소켓 통신 기반 실시간 주식 매수/매도 시뮬레이터 프로젝트입니다.<br>
웹 기반 인터페이스로 매수/매도를 수행하고, 실시간 차트로 <br>
거래 내역을 확인할 수 있습니다.

## 🛠 주요 기능

- 실시간 매수/매도 처리
- 실시간 가격 변동 그래프
- 거래 결과 테이블 출력
- WebSocket 기반 실시간 데이터 전송

## 실행 방법

### 🐳 Docker로 로컬 실행

```bash
1️⃣ Docker 이미지 Pull
docker pull minjo0310/real-time-stock:latest

2️⃣ 컨테이너 실행
docker run -p 8080:8080 minjo0310/real-time-stock:latest

3️⃣ 브라우저에서 접속
http://localhost:8080
```

### 🚀 Docker없이 실행(소스코드 다운로드 필요, window 기준)

```
1️⃣ git clone
git clone https://github.com/minj-L/wooatech-open-mission.git

2️⃣ 프로젝트 루트에서 Gradle 빌드
cd .\wooatech-open-mission\
./gradlew clean build -x test

3️⃣ 빌드된 JAR 확인 (예: build/libs/real-time-stock-0.0.1-SNAPSHOT.jar)
java -jar build/libs/real-time-stock-0.0.1-SNAPSHOT.jar

4️⃣ 브라우저에서 접속
http://localhost:8080
```
