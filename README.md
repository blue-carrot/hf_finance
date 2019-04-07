# 개발환경
Java 1.8
Spring Boot 2.0.6
Maven Bulid
H2 DataBase

# 실행방법
mvn spring-boot:run

# EndPoint
저장하는 API
POST /app/upload

금융기관(은행) 목록 출력
GET /app/inslist

년도별 각 금융기관 지원금액 합계
GET /app/financeinfo

기관의 전체 지원금액 중에서 가장 큰 금액의 기관명 출력
GET /app/maxamount

외환은행의 지원금액 평균 중에서 가장 작은 금액과 큰 금액 출력
GET /app/avgamount

