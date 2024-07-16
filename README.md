# <img src="https://github.com/user-attachments/assets/a2b39fad-3a91-48b6-b21b-ce9493dddb35" alt="KakaoTalk_20240713_135653727" width="50"/> spartaKanbanBoard 
- 프로젝트 명 :  스파르타 칸반 보드
- 소개
    - 한 줄 정리 : 스파르타 칸반 보드는 스파르타 팀 프로젝트를 진행하는 과정을 담은 보드로 업무를 시각적으로 표현하여 팀이 작업과 프로세스를 효율적으로 관리할 수 있게 도와줍니다.
    - 내용 :
    - 회원가입시 어드민 계정과 일반 유저 계정으로 나뉘어집니다.
    - 어드민 유저는 보드 및 컬럼을 생성, 수정, 삭제를 할 수있고 원하는 유저를 특정 보드에 초대할 수 있습니다.
    - 일반 유저는 초대받아 자신이 속한 보드에 들어가 카드 생성을 할 수 있습니다.

# 🖥️Tech Stack
- 언어 : Java
- 버전 : JDK17
- Tools : GitHub, Git, Docker
- IDE : IntelliJ IDEA
- DB: MySQL 8.0.37, Redis
- Front : Html, Css, JavaScript
- Framework : SpringBoot 3.3.1
- 인증/인가 방식 : JWT, Spring security

# 👉팀원 소개 & 역할 분담
<details>
<summary>
  ${\textsf{\color{green}박강현(팀장)}}$
</summary>
  <br>
   - 유저 관련 기능 <br>
   - 전체 페이지 Html 구현 <br>
   - 메인 페이지, 유저 기능 프론트 연결  <br>
   - Docker, Github Action을 이용한 CI, CD 구현
</details>
<details>
<summary>
  권수연
</summary>
  <br>
   - 보드 관련 기능 <br>
   - 로그인 후 메인페이지, 보드 생성, 보드 페이지 프론트 연결<br>
   - Docker, Github Action을 이용한 CI, CD 구현<br>
</details>
<details>
<summary>
  김형석
</summary>
  <br>
   - 카드, 댓 관련 기능 <br>
   - 카드 페이지, 카드 생성 페이지, 카드 순서 변경 기능 프론트 연결
</details>
<details>
<summary>
  이인호
</summary>
  <br>
   - 컬럼 관련 기능 <br>
   - 컬럼 생성 페이지, 컬럼 수정 기능, 컬럼 순서 변경 기능 프론트연결
</details>

# 📢Github Rules & Code Convention

<details>
<summary>
  ⚖️Github Rules
</summary>
  <br>
  •  Github Organization 사용 <br>
  •  이슈, PR 템플릿 적용 <br> 
  <details>
    <summary>
      💾 이슈 템플릿
    </summary>
    <br>
 <img src="https://github.com/user-attachments/assets/d05d86e1-40fa-41d3-a124-e3dcf78c0f93" alt="이슈" width="1000"/>


  </details>
  <details>
    <summary>
      💾 PR 템플릿
    </summary>
    <br>
    <img src="https://github.com/user-attachments/assets/99b65eb6-42d3-4965-8b26-d75dbcc00d90" alt="PR" width="1000"/>


  </details>
<details>
<summary>상세 설명</summary>
  <br>
  - 브랜치 이름 규칙 <br>
    - dev 브랜치, 각자 개발 기능 구현 feat/(기능이름) 브랜치 <br>
    - 두가지 단어라면 ‘ - ’ (하이픈) 사용해서 구분 <br>
    - feat/signup, feat/order-create <br>
    - refactor/order-create <br>
  <br>
  - 커밋 메시지 규칙 <br>
    - ~~[feat] #1(이슈 번호) 로그인 함수 구현~~ <br>
    - **✨ update - #1 로그인 함수 구현 (이모지는 밑에 상황별 이모지)** <br>
    - ✨ update, 🩹 fix - #1 로그인 함수 구현, 회원가입 함수명 수정 <br>
  <br>
  - 이슈 작성 규칙 <br>
    - 이슈 사용 <br>
    - title : 🌟 [feat] 이슈명 / description : 템플릿 따라서 작성 <br>
    - title : 👾 [bug]  이슈명 / description : 템플릿 따라서 작성 <br>
  <br>
  - PR 작성 규칙 <br>
    - pr 규칙 사용 [현재날짜] 브랜치명 >> 간단한 설명 <br>
    - [2024/06/19] feat/signup(브랜치명) >> 로그인 기능 구현(구현한 것 간단하게) <br>
  <br>
  - 코드리뷰 적용 (리뷰 1개이상 머지 가능, 겹치는 부분은 해당 담당자에게 리뷰 받기 필수) <br>
</details>

</details>

<details>
<summary>
  🔑Code Convention
</summary>
  <br>
  •  구글 자바 컨벤션 적용 <br>
  •  생성자 패턴이 아닌 빌더 패턴으로 통일 <br>
  •  패키지 이름: 소문자 <br>
  •  클래스 이름: 파스칼 케이스(PascalCase) <br>
  •  상수 이름: 모두 대문자 <br>
  •  변수 이름: 카멜 케이스(camelCase) <br>
  •  메서드 이름: 카멜 케이스(camelCase) <br>
  •  도메인형 디렉토리 구조
</details>

# 📑API 명세서
![image](https://github.com/user-attachments/assets/6cf84d3e-7a35-45cf-9587-0a5b27381828)
![image](https://github.com/user-attachments/assets/b8779f4a-355a-4165-bda3-550c04fcf475)
![image](https://github.com/user-attachments/assets/2a1d4e02-05ec-4d31-9953-6c51b55e935a)

# 📈ERD 다이어그램
![image](https://github.com/user-attachments/assets/c520f8f9-b9da-4aed-a68b-3332e4e90fc8)

# POSTMAN 
https://documenter.getpostman.com/view/36493338/2sA3kPqQFH

# 🔊트러블 슈팅
<details>
    <summary>${\textsf{\color{red}컬럼 순서 변경 문제}}$</summary>
<details>
<summary>
   버그 발생 상황 😱
</summary>
  <br>
   • 컬럼 수정 시에 이름변경만 했을 경우엔 제대로 작동<br>
   • 컬럼 드래그 앤 드롭 시 제대로 작동<br>
   • 🚬순서 변경 완료 후 수정 완료 버튼을 눌렀을 시 순서가 다시 배치되는 문제 발생<br><br>
  
</details>

<details>
<summary>
  예상되는 오류 이유 🤔
</summary>
   <br>
  • 백엔드 로직의 오류?<br>
  • 프론트의 수정 완료 버튼 로직에서 불필요한 순서변경이 추가로 발생? <br>
</details>
<details>
<summary>
  오류 발생 원인 ❓
</summary>
   <br>
  • 🔎프론트엔드에서 "컬럼 수정" 버튼을 누르면 컬럼의 순서를 변경하고 이름을 수정할 수 있습니다. 그러나 이름을 수정하지 않고 순서만 변경해도 기존 코드에서는 컬럼의 ID를 인덱스 기반으로 가져오게 되어 컬럼의 이름이 변경된 것으로 잘못 인식하는 문제가 있었습니다. <br>
  • 😡예시의 상황으로 1번 2번 3번 4번의 컬럼이 있었을 때 4번의 컬럼을 1번 위치로 옮기고 수정 완료 버튼을 누르면 1번 인덱스의 컬럼이 4로 이름이 변경된 것으로 인식 <br>
</details>
<details>
<summary>
  문제 해결 🪄
</summary>
   <br> 
  • 컬럼의 Id 값을 인덱스 기반으로 가져오는 것이 아닌 고유한 값을 가져오도록 코드 변경<br>
</details>
</details>
