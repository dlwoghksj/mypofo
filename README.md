# 1. 포트폴리오 개요

> **프로젝트 명:** 와투(와치투게더) OTT쉐어 어플리케이션
>
> **기획 및 제작:** 본인 외 1인
>
> **분류:** 팀 프로젝트
>
> **주요 기능:** 로그인, 회원가입, 게시판, 관리자 페이지, 구독자 관리
>
> **기타 기능:** dlwoghksj@gmail.com
>
> **맡은 역할:** 프론트엔드, 관리자페이지, 데이터베이스 일부
>
> **문의:** dlwoghksj@gmail.com


## 1-1. 기술 스택
### 앱
> **프로그래밍 언어:** Java
> 
> **마크업 언어:** XML
> 
> **IDE:** Android Studio
### 웹
> **프로그래밍 언어:** Java, JavaScript, jsp
> 
> **마크업 언어 및 스타일시트 언어:** HTML, CSS
> 
> **프레임 워크:** Spring Boot, Spring
> 
> **IDE:** Eclipse
### 데이터베이스 : mysql
<br />
<br />

# 2. 프로젝트 설명
WATTO는 OTT쉐어 어플리케이션 서비스로써 구독료를 더치페이하여 이용자들에게 좀더 저렴하게 OTT서비스를 이용할 수 있게 해주는 어플리케이션입니다.

<br />

# 3. 설계
## 인터페이스 설계)
![UI 설계](https://github.com/dlwoghksj/mypofo/assets/104198797/6264be0c-35a8-468b-ac3e-2cb81e89ca50)

<br />

## 시스템 구조)
![시스템구조1](https://github.com/dlwoghksj/mypofo/assets/104198797/b5c9eb2a-5181-411e-8d7f-d9aad7aa5885)

<br />

## 작동 방식)
![시스템구조2](https://github.com/dlwoghksj/mypofo/assets/104198797/d58e6d15-4996-48e0-a4f5-0357e4c83ce1)

<br />

## 구독 취소)
![시스템구조3](https://github.com/dlwoghksj/mypofo/assets/104198797/5c870efa-b69b-4ab0-ab5e-db87a7dec831)

<br />
<br />

# 4. 구현
## 관리자 페이지(웹으로 구현)
https://drive.google.com/file/d/1_fzbSYGkBclfxxxafccVpcYyeQQXu69o/view?usp=sharing
- 관리자만 접근할 수 있는 페이지
- 관리자는 네비게이션 메뉴를 통해 유저를 관리할 수 있음
- OTT 웨이팅 리스트를 확인하고 관리할 수 있고 이를 통해 결제 후 유저들에게 OTT계정을 배포.

## 어플리케이션 구현
### 로그인
![기능구현1](https://github.com/dlwoghksj/mypofo/assets/104198797/04345a99-f1de-4c36-b0e1-566113b5f50d)
### 회원가입
![기능구현2](https://github.com/dlwoghksj/mypofo/assets/104198797/97e7a80e-71a0-4f74-93b6-ffdce8f40afe)
### 메인화면
![기능구현3](https://github.com/dlwoghksj/mypofo/assets/104198797/cc31257d-cffa-4c00-bce3-1bd7b31d912c)
### OTT를 클릭했을시
![기능구현5](https://github.com/dlwoghksj/mypofo/assets/104198797/366ddc58-f93f-4f36-960e-c44586aa274f)
- 카드정보를 등록하지 않은 상태에서 구독을 누르면 카드정보를 등록하라는 알림이 나온다.
### 네비게이션 메뉴
![기능구현10](https://github.com/dlwoghksj/mypofo/assets/104198797/e1134f57-1188-4347-8909-4820adbbd185)
### 내구독 화면1 ( 결제 대기 )
![기능구현11](https://github.com/dlwoghksj/mypofo/assets/104198797/9bd5e269-ca5d-46ef-82f4-bc8cbf8e2ff9)
- 관리자가 관리자 페이지에서 결제를 해주지 않았다면 관리자 결제 대기중이라는 문구가 나온디.
### 내구독 화면2 ( 결제 완료시)
![기능구현12](https://github.com/dlwoghksj/mypofo/assets/104198797/9e83df5f-5acf-47db-a1f3-d48426c49709)
- 관리자 페이지에서 결제를 완료하면 웨이팅 리스트에 들어가 대기하게된다.
### 대기자가 4명 모두 찼을시
![기능구현15](https://github.com/dlwoghksj/mypofo/assets/104198797/c08cce61-e67b-4142-b97a-892059e399d5)
- OTT 서비스의 계정을 알 수 있다.
<br />
<br />

### 구현 영상
https://drive.google.com/file/d/11kPb3I15ktlg7o6n0xmn1E3JzNfzeN8Q/view?usp=sharing

### ERD
<img width="743" alt="erd" src="https://github.com/dlwoghksj/mypofo/assets/104198797/edbcd4ae-0784-4471-923c-268f1bc6b176">





