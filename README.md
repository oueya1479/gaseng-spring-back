# 가(家)생이 : KYC 인증을 활용한 룸메이트 매칭 서비스 개발

![image](https://github.com/gaseng/Back/assets/147911523/c63d38a8-45b4-42f6-9b60-3467dcefec00)

## 1. 프로젝트 소개

- 기간 : 2023. 09 ~ 12
- 참여 인원 : 3명
- 기술스택 : Spring Boot, JPA, MySQL

### 아이디어 배경

> 쉐어하우스란 공용화된 공간에서 개인적인 공간이 별도로 분리되어 있는 주거형태를 의미합니다.

쉐어하우스는 자취와는 달리 개인적인 공간이 협소합니다. 즉, 자신과 모르는 사람과 함께 사용하는 공간이 더 많기 때문에 룸메이트의 성격과 취향, 생활패턴에 많은 영향을 받게 됩니다. **주거 공유를 하고자 하는 사람이 자신의 가치관과 딱 맞는 룸메이트를 매우 구하기 어렵다는 의미입니다.**

룸메이트를 구하는 것도 문제이지만, 현재 룸메이트를 구할 수 있는 앱에서도 큰 문제가 존재합니다. **바로 인증에 취약하다는 것입니다.** 이 때문에 주거지가 많은 사람들에게 노출되거나 성별 관계없이 게시물을 열람할 수 있는 시스템은 범죄를 야기하고 있었습니다.

이런 문제점과 단점을 보완하고자 룸메이트 체크리스트와 KYC 인증 서비스를 제작하여 안전한 룸메이트 매칭서비스를 제작했습니다. KYC는 Know Your Customer의 약자로 다른 사람과 앱으로 소통하기 전에 자신에 대한 인증 절차를 거쳐야한다는 의미입니다.

### Architecture

![image](https://github.com/gaseng/Back/assets/147911523/5415afac-f899-4d40-a0eb-5c9935a61f44)

**Front**

- Users는 Mobile을 통해 Back Server에 접근합니다.
- Mobile은 크로스 플랫폼인 Flutter로 제작이 되어 IOS, Android 둘 다 사용가능합니다.
- Front 코드는 (Front Repository)[https://github.com/gaseng/Front]를 참고해주세요

**Back**

- 개발자들은 Git을 사용하여 코드를 공유합니다.
- Git Actions에 CI/CD가 구축되어 있어, main branch로 push되면 자동으로 `Linux server`에 적용됩니다.
- Docker로 이미지를 빌드하여 Linux server에서 이미지를 실행시키는 구조입니다.
- Linux server는 AWS의 t2.medium을 사용했습니다.

**DB**

- Web hosting을 위해 Firebase를 사용했습니다.
- 이미지를 저장하는 `S3`, JWT사용 등 인증 절차에 관한 `Redis`, 각종 데이터를 저장하는 `Amazon RDS`를 사용했습니다.

**Others**

- Blockchain의 토큰을 구현하기 위해 Ethereum의 erc20을 활용하였습니다.
- Solidity 언어를 사용하였으며 KYC와 관련된 서버가 따로 배치되어 있습니다.
- KYC 서버를 모니터링하기 위하여 Docker를 활용하여 Grafana를 구축하였습니다.
- Contract는 Sepolia Ethereum(Test network)에 배포되어 있는 상태입니다.

> Transaction: 0xD69297b898f3540D0FDa0BE69F69c54E65043A89

## 2. 프로젝트 정보

### 요구 기능 정의 및 개발 업무표

### 사용자

| 주요기능      | 세부기능               | v0.3 (10/19, 목) | v0.5 (10/26, 목) | v0.6 (11/02, 목) | v0.9 (11/09, 목) | v1.0 (11/16, 목) |
|---------------|------------------------|-------------------|-------------------|-------------------|-------------------|------------------|
| 디자인        | UI 사용자 개발        | O                 |                  |                   |                   |                  |
|               | UI 관리자 개발        |                   | O                 |                  |                   |                  |
| 회원          | 회원가입               | O                 |                  |                   |                   |                  |
|               | 로그인                 | O                 |                  |                   |                   |                  |
|               | 로그아웃               | O                 |                  |                   |                   |                  |
|               | 회원 탈퇴              |                   |                   | O                 |                  |                  |
|               | 아이디 찾기            |                   |                   | O                 |                  |                  |
|               | 비밀번호 변경          |                   |                   | O                 |                  |                  |
|               | 내가 쓴 글 조회        |                   |                   | O                 |                  |                  |
|               | 스크랩 게시물 조회    |                   |                   |                   | O                 |                 |
|               | 체크리스트 작성        | O                 |                  |                   |                   |                  |
|               | 체크리스트 수정        | O                 |                  |                   |                   |                  |
| 쉐어하우스    | 쉐어하우스 글 등록     |                   | O                 |                  |                   |                  |
|               | 쉐어하우스 글 수정     |                   | O                 |                  |                   |                  |
|               | 쉐어하우스 글 삭제     |                   | O                 |                  |                   |                  |
|               | 쉐어하우스 글 전체 조회 |                   |                   | O                 |                  |                  |
|               | 쉐어하우스 글 상세 조회 |                   |                   | O                 |                  |                  |
|               | 위치 조회              |                   |                   | O                 |                  |                  |
|               | 쉐어하우스 글 스크랩 등록 |               |                   |                   |                   | O                |
|               | 쉐어하우스 글 스크랩 취소 |               |                   |                   |                   | O                |
|               | 매칭 신청 (채팅방 생성 기능과 병합됨) |       | O                 |                  |                   |                  |
|               | 채팅방 접속            |                   |                   | O                 |                  |                  |
|               | 채팅 메시지 등록       |                   |                   | O                 |                  |                  |
|               | 채팅방 전체 조회       |                   |                   |                   | O                 |                 |
|               | 채팅방 나가기 (채팅방 삭제 기능과 병합됨) |     |                   |                   |                   | O                |
| KYC           | KYC 인증확인           |                   | O                 |                  |                   |                  |
|               | 신분증 촬영            |                   | O                 |                  |                   |                  |
|               | 얼굴 촬영              |                   |                   | O                 |                  |                  |
|               | 특정 행동 얼굴 촬영   |                   |                   | O                 |                  |                  |
|               | KYC 제출 (KYC 요청 기능과 병합됨) |             |                   |                   | O                 |                  |
|               | KYC 정보 조회          |                   |                   | O                 |                  |                  |

### 관리자

| 주요기능      | 세부기능               | v0.3 (10/21, 목) | v0.5 (10/28, 목) | v0.6 (11/3, 목) | v0.9 (11/10, 목) | v1.0 (11/17, 목) |
|---------------|------------------------|-------------------|-------------------|------------------|------------------|------------------|
| 회원 관리     | 관리자 로그인         |                   | O                 |                 |                  |                  |
|               | 관리자 로그아웃       |                   | O                 |                 |                  |                  |
|               | 사용자 목록 조회       |                   | O                 |                 |                  |                  |
|               | 사용자 정보 상세조회   |                   | O                 |                 |                  |                  |
|               | 사용자 상태 제한       |                   | O                 |                 |                  |                  |
| KYC           | KYC 요청 조회          |                   |                   |                   | O                |                  |
|               | KYC 상태 변경          |                   |                   |                   | O                |                  |
|               | 범죄이력 조회          |                   |                   |                   |                  | O                |
|               | KYC 저장 (KYC 공지 기능과 병합됨) |             |                   |                   |                  | O                |

### 서버 실행 방법

**Back**
1.	code를 git에 push한다.
2.	ec2를 하나 제작하여 ec2에 대한 access key를 git secret에 넣어두고, 자동으로 배포될 수 있도록 한다.
3.	github actions가 실행되어 gaseng 서비스가 docker로 이미지화 되어 서버로 올라간다.

**Database**
1.	AWS RDS를 제작한다.
2.	해당 정보를 resource 안의 secret.yml 파일을 만들어 RDS에 관한 정보를 넣는다.

**Blockchain**
1.	sepolia test network의 배포된 KYC 컨트랙트에 연결되어 있다.
2.	해당 컨트랙트의 주소는 0xD69297b898f3540D0FDa0BE69F69c54E65043A89 이다.

```yml
# secret.yml으로 관리해야 하는 키는 다음과 같다.
RDS_HOST: [AWS RDS 호스트]
RDS_USERNAME: [AWS RDS 사용자 이름]
RDS_PASSWORD: [AWS RDS 패스워드]

S3_BUCKET_NAME: [AWS S3 버킷 이름]
S3_ACCESS_KEY: [AWS S3 권한 있는 액세스 키]
S3_SECRET_KEY: [AWS S3 권한 있는 비밀 키]

JWT_SECRET: [JWT로 사용될 비밀 키]
  
COOL_KEY: [전화번호 API 키]
COOL_SECRET: [전화번호 API 비밀키]
COOL_PHONE: [해당 전화번호로 인증문자 전송]

WALLET_ADDRESS: [metamask address]
CONTRACT_ADDRESS: [sepolia test network에 배포된 KYC 컨트랙트]
WALLET_PRIVATE_KEY: [metamask 비밀 키]
INFURA_API_URL: [infura 서비스 sepolia api]
```

## 3. 프로젝트 결과물

[가생이 프로젝트 설계서.hwp (250쪽 분량) 다운로드](https://drive.google.com/file/d/1-kyK38KVzZTP4ve-0XE2qVfM5eMlQyIm/view?usp=sharing)

[유저 인터페이스 (figma) 58장](https://www.figma.com/file/RLjIeEEioElpbGlRz6xkDx/%EA%B0%80%EC%83%9D%EC%9D%B4?type=design&node-id=67-2&mode=design)

## 4. 기술 및 기능

### S3와 Lambda를 활용한 이미지 리사이징 기능

AWS EC2 Instance를 활용하여 온디멘드 서버를 구축했습니다. 이와 관련되어 안정성과 높은 가용성을 자랑하는 S3를 활용하여 이미지를 저장하는 방법을 선택했습니다. 각 사진은 메인화면에 전시되는 Thumbnail용 사진이 있기 때문에 이미지를 리사이징하는 기능이 필요로 했습니다. 서버에서 이미지 리사이징 기능을 제작하게 되면 원본 사진과 Custom한 사진 두 개를 업로드하는 기능이 필요로 했고, 이는 불필요한 API를 서버 내에서 감당할 가능성이 있기에 S3에 이미지가 업로드 될 때 Lambda를 Trigger하여 클라우드 상에서 이미지 리사이징 기능을 대체했습니다.

### 채팅 서비스, Realtime database의 사용

기획 단계에서 RDBMS를 사용하여 채팅 서비스를 구현하도록 정했습니다. 개발 도중 채팅 시스템에 KYC 열람기능 및 프로필 등 요구 사항이 변동되었고, 채팅에 관한 기능 하나하나가 추가 될 때마다 스키마를 변경해야 하는 문제가 발생했습니다. 또한 가장 많은 데이터가 담긴 테이블이었으며, 읽기보다 쓰기가 더 많이 일어나는 채팅 시스템을 유연하게 바꾸는 과정이 필요했습니다. 이에 Firebase의 Realtime database를 도입했습니다. 기존 채팅방이 고유하게 가지고 있던 데이터는 RDBMS에 유지하고, 채팅 내용 등 자주 발생하는 데이터는 NoSQL로 옮기어 데이터 수정을 유연하게 대처할 수 있도록 했습니다.

### JWT 인증

Gaseng은 IOS, Android 기반으로 만들어진 Application이지만 Firebase를 이용하여 Web에도 호스팅을 진행했습니다. 따라서 Web에서의 권한 없는 사용자들의 부적절한 API 사용을 방지해야 했습니다. 이에 모든 API Header의 Authentication 항목을 검증하여 인증 받은 사용자의 JWT를 확인하는 절차를 추가했습니다. Session 방식과 Token 방식을 비교하여 팀원들과 논의했으며, 현 프로젝트에서는 둘의 차이가 극명하게 나뉘지는 결과는 없으나 Blockchain 및 타 서비스와의 연결이 많아 서버의 부하를 줄이고자 Token 방식으로 추가했습니다.

### Blockchain transaction 속도 테스트

블록 시간은 블록이 만들어지는데 걸리는 시간을 의미합니다. 이는 네트워크마다 어떤 방식을 택하고 있는지에 따라 다릅니다. 이 프로젝트는 Ethereum Network를 사용하고 있으며 KYC 데이터를 저장하는 시간 측정 결과 대략 8~9초의 시간이 걸립니다. 이에 시간을 단축할 방법을 찾아보고자 Network별로 특성을 조사했으며 Polygon(6초), Avalanche(2~3초), TON(1~2초)라는 결과를 확인했습니다. Ethereum에서 사용하는 Solidity의 확장성이 가장 컸기 때문에 Ethereum을 사용했지만, 속도를 우선시하는 Application에서는 Avalanche및 TON을 사용해봐도 좋을 것 같습니다.
