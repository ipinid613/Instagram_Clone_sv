# Instagram_Clone_sv

## Version 0.0.1
### 1. 회원가입 기능 구현

* ```이메일, 실명, 유저명, 비밀번호``` **필수입력**하도록 설정, 이메일은 형식도 맞출 것 요구
  + @NotBlank, @Email
  + 입력값에 오류 발생 시, 400에러와 message로 response
* ```이메일, 실명, 유저명```은 **기존 회원과중복 불가**, ```비밀번호```에는 ```이메일, 실명, 유저명``` **포함 불가**
  + 위반 시 400에러와 message로 response
  
### 2. 로그인 기능 구현

* ```유저명, 비밀번호``` 입력하여 로그인
  + 유저명이 DB에 없는 경우 400에러와 message로 response
  + 유저명은 맞는데 비밀번호가 틀린 경우 400에러와 message로 response
* 로그인 성공 시, 리스트의 형태로 ```유저명과 JWT```를 response

### 3. 제한 사항

* 회원가입 후 DB에 ```USER```테이블 저장 시, createdAt과 modifiedAt이 포함되지 않는 오류 발생
------
## Version 0.0.2
### 1. Version 0.0.1의 제한사항 해결

* Timestamped 정상화
