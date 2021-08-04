# 인스타그램 클론코딩(서버)
### 목차
- [개발 배경](#개발-배경)
- [개발 과정](#개발-과정)
  - [개발 기간](#1-개발-기간)
  - [사용 언어](#2-사용-언어)
  - [기능 구현 목표](#3-기능-구현-목표)
  - [API 설계](#4-API-설계)
  - [예외처리 설계](#5-예외처리-설계)
  - [DB 설계](#6-DB-설계)
- [개발 결과](#개발-결과)
  - [구현한 기능](#1구현한-기능)
  - [실제 서비스 모습](#2-실제-서비스-모습)
  - [피드백](#3-회고-및-피드백)
---
### 개발 배경
- 실제 서비스 되고 있는 [인스타그램]과 동일한 모습의 페이지와 기능을 만드는 클론 프로젝트입니다.
- 여러가지 도전점을 찾을 수 있었습니다. 이전에 구현에 실패한 `좋아요, 팔로우-팔로잉, 마이페이지`를 본 프로젝트의 주된 목표로 삼았습니다.
- 학습 중인 Java의 Spring Framework를 더 연마하고 DB에 대한 고민을 해볼 수 있는 기회였습니다.
---
### 개발 과정

#### 1. 개발 기간
- `2021년 07월 16일(금) ~ 2021년 07월 22일(목) / 총 7일`
- `설계 1일 / 개발 6일`

#### 2. 사용 언어
- **Languages** : Java / `프론트 : React, Redux`
- **Framework** : Spring
- **DB** : MySQL, H2(테스트용)

#### 3. 기능 구현 목표
1. 회원가입, 로그인(JWT)
2. 좋아요(토글)
3. 인스타그램 스타일의 게시물 생성, 조회
4. 댓글 작성
5. 추천 친구
6. 팔로우-팔로잉

#### 4. API 설계
| 페이지 | 기능 | API URL | Method | Request | Response |
|:----------|:----------:|:----------:|:----------:|:----------|:----------|
| 메인 페이지 | 전체 페이지 조회 | /api/articles | GET | - | 아래 (1) |
| 작성 페이지 | 게시물 작성 | /api/articles | POST | content, imageUrl| 아래 (2) |
| 세부 페이지 | 특정 게시물 조회 | /api/articles/{articleId} | GET | 게시물 번호 | 아래 (3) |
| 세부 페이지 | 특정 게시물 수정  | /api/articles/{articleId} | PUT | content, imageUrl | 아래 (4) |
| 세부 페이지 | 특정 게시물 삭제  | /api/articles/{articleId} | DELETE | 게시물 번호 | 200 OK |
| 메인 페이지 | 댓글 작성 | /api/articles/{articleId}/comments | POST | 게시물 번호, content | 아래(5) |
| 메인 페이지 | 댓글 수정 | /api/articles/{articleId}/comments/{commentId} | PUT | 게시물 번호, content | 아래(6) |
| 메인 페이지 | 댓글 삭제 | /api/articles/{articleId}/comments/{commentId} | DELETE | 게시물 번호 | 아래(7) |
| 메인 페이지 | 팔로우 | /api/follow/{userId} | POST | 유저 번호 | true |
| 메인 페이지 | 좋아요 | /api/articles/{articleId}/like | POST | 게시물 번호 | true, false |
| 마이 페이지 | 내 게시물 수, 팔로우-팔로잉 수, 게시물 확인 | /api/myInfo | GET | - | 아래(8) |
| 메인 페이지 | 추천 친구 표시 | /api/recommend | GET | - | 아래(9) |
| 로그인 페이지 | 이메일 또는 유저명으로 로그인 | /api/login | POST | 이메일 or 유저명, 비밀번호 | 유저명, 토큰 |
| 회원가입 페이지 | 회원가입 처리 | /api/signup | POST | 이메일, 닉네임, 유저명, 비밀번호 | 200 OK |
| 유저정보 수정 페이지 | 로그인 한 유저 정보 표시 | /api/user | GET | - | 아래(10) |
| 유저정보 수정 페이지 | 유저 정보(닉네임, 프로필사진) 수정 | /api/user | PUT | 변경할 닉네임, imageUrl | 200 OK |

- (1) 전체 페이지 조회 Response
- ```json
  {
    "articleId": 0,
    "author": "string",
    "authorProfileImageUrl": "string",
    "comments": [
      {
        "articleId": 0,
        "commentAuthor": "string",
        "commentAuthorProfileImageUrl": "string",
        "commentId": 0,
        "content": "string",
        "createdAt": "2021-08-03T07:40:32.473Z",
        "modifiedAt": "2021-08-03T07:40:32.473Z",
        "username": "string"
      }
    ],
    "content": "string",
    "createdAt": "2021-08-03T07:40:32.473Z",
    "imageUrl": "string",
    "isLiked": true,
    "likeCount": 0,
    "modifiedAt": "2021-08-03T07:40:32.473Z",
    "username": "string"
  }

- (2) 게시물 작성 Response
- ```json
  {
  "articleId": 0,
  "author": "string",
  "authorProfileImageUrl": "string",
  "comments": [
    {
      "articleId": 0,
      "commentAuthor": "string",
      "commentAuthorProfileImageUrl": "string",
      "commentId": 0,
      "content": "string",
      "createdAt": "2021-08-03T07:44:36.446Z",
      "modifiedAt": "2021-08-03T07:44:36.446Z",
      "username": "string"
    }
  ],
  "content": "string",
  "createdAt": "2021-08-03T07:44:36.446Z",
  "imageUrl": "string",
  "isLiked": true,
  "likeCount": 0,
  "modifiedAt": "2021-08-03T07:44:36.446Z",
  "username": "string"}

- (3) 특정 게시물 조회 Response
- ```json
  {
  "articleId": 0,
  "author": "string",
  "authorProfileImageUrl": "string",
  "comments": [
    {
      "articleId": 0,
      "commentAuthor": "string",
      "commentAuthorProfileImageUrl": "string",
      "commentId": 0,
      "content": "string",
      "createdAt": "2021-08-03T07:45:07.106Z",
      "modifiedAt": "2021-08-03T07:45:07.106Z",
      "username": "string"
    }
  ],
  "content": "string",
  "createdAt": "2021-08-03T07:45:07.106Z",
  "imageUrl": "string",
  "isLiked": true,
  "likeCount": 0,
  "modifiedAt": "2021-08-03T07:45:07.106Z",
  "username": "string"}

- (4) 특정 게시물 수정 Response
- ```json
  {
  "articleId": 0,
  "author": "string",
  "authorProfileImageUrl": "string",
  "comments": [
    {
      "articleId": 0,
      "commentAuthor": "string",
      "commentAuthorProfileImageUrl": "string",
      "commentId": 0,
      "content": "string",
      "createdAt": "2021-08-03T07:45:40.346Z",
      "modifiedAt": "2021-08-03T07:45:40.346Z",
      "username": "string"
    }
  ],
  "content": "string",
  "createdAt": "2021-08-03T07:45:40.346Z",
  "imageUrl": "string",
  "isLiked": true,
  "likeCount": 0,
  "modifiedAt": "2021-08-03T07:45:40.346Z",
  "username": "string"}

- (5) 댓글 작성 Response
- ```json
  {
    "articleId": 0,
    "commentAuthor": "string",
    "commentAuthorProfileImageUrl": "string",
    "commentId": 0,
    "content": "string",
    "createdAt": "2021-08-03T07:46:17.282Z",
    "modifiedAt": "2021-08-03T07:46:17.282Z",
    "username": "string" }

 - (6) 댓글 수정 Response
- ```json
  {
    "articleId": 0,
    "commentAuthor": "string",
    "commentAuthorProfileImageUrl": "string",
    "commentId": 0,
    "content": "string",
    "createdAt": "2021-08-03T07:46:17.282Z",
    "modifiedAt": "2021-08-03T07:46:17.282Z",
    "username": "string" }

- (7) 댓글 삭제 Response
- ```json
  {
    "articleId": 0,
    "commentAuthor": "string",
    "commentAuthorProfileImageUrl": "string",
    "commentId": 0,
    "content": "string",
    "createdAt": "2021-08-03T07:46:44.596Z",
    "modifiedAt": "2021-08-03T07:46:44.596Z",
    "username": "string" }


- (8) 마이 페이지 진입 시 Response
- ```json
  {
  "articleCount": 0,
  "followCount": 0,
  "followerCount": 0,
  "myInfoArticleResponseDtoList": [
    {
      "articleId": 0,
      "articleImageUrl": "string"
    }
  ],
  "myProfileImageUrl": "string",
  "nickname": "string" }

- (9) 추천 친구 표시 Response
- ```json
  {
    "nickname": "string",
    "profileImageUrl": "string",
    "userId": 0 }


- (10) 유저 정보 수정 시 Response
- ```json
  {
  "nickname": "string",
  "profileImageUrl": "string"}


#### 5. 예외처리 설계
[instagram_clone_에러메시지 종합.pdf](https://github.com/ipinid613/Instagram_Clone_sv/files/6922328/instagram_clone_.pdf)
![image](https://user-images.githubusercontent.com/85334989/128237602-52ed0732-9f48-4f71-b380-88ddf175d280.png)
![image](https://user-images.githubusercontent.com/85334989/128237637-aecd57f9-537e-483a-9191-cc27c3a4c8ff.png)

#### 6. DB 설계
- ERD(Entity Relationship Diagram)


![DB](https://images.velog.io/images/ipinid613/post/66de4847-92aa-4a59-ac54-344a6c13caf0/image.png)


---
### 개발 결과

#### 1.구현한 기능
최초 목표했던 6가지 기능을 부분적 완성의 형태로 구현하였습니다.</br></br>
한계점도 찾을 수 있었습니다. 실제 인스타그램 서비스 시에는 팔로우 관계에 따라 유저가 보게 되는 게시물이 표시되는데, 이번 클론 프로젝트에서는 팔로워-팔로잉 수를 구현하는 데 그쳤습니다. 또한, 내가 작성한 게시글을 모아보는 '마이 페이지'은 구현이 되었는데, 다른 유저의 페이지로 이동하는 것은 구현하지 못했습니다. </br></br>

- 토글 방식의 좋아요 기능(Contoller First, then Service, then Model)
```java
// Controller
 @ApiOperation("라이크 누름")
    @PostMapping("/api/articles/{articleId}/like")
    public Boolean likeArticle(@PathVariable Long articleId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return likedService.toggleLiked(articleId,userDetails.getUser());
    }

// Service
@Transactional
    public Boolean toggleLiked(Long articleId, User user) {
        Optional<Article> article = articleRepository.findById(articleId);

        if (article.isPresent()) {
            for (Liked liked : article.get().getLikedList()) {
                if (liked.getUser().getId().equals(user.getId())) {
                    article.get().getLikedList().remove(liked);
                    liked.disconnectArticle();
                    likedRepository.delete(liked);
                    return false;
                }
            }
            likedRepository.save(Liked.builder()
                    .user(user)
                    .article(article.get())
                    .build());
            return true;
        } else {
            throw new ArticleRequestException("해당 게시글이 없습니다. id=" + articleId);
        }

// Model
@Getter
@NoArgsConstructor
@DynamicUpdate
@Entity
public class Liked extends Timestamped {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Article article;

    @Builder
    public Liked(User user, Article article) {
        this.user = user;
        this.article = article;
    }

    public void disconnectArticle(){
        this.article = null;
    }

} 
```

- 마이 페이지 진입 시 로그인 한 유저의 정보(게시글 수, 팔로우 수, 프로필 사진, 이름 등) Read
```java
// Controller
@ApiOperation("현재 로그인 한 유저정보")
    @GetMapping("/api/myinfo")
    public MyInfoResponseDto readMyInfo(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return myInfoService.readMyInfo(userDetails.getUser());
    }
    
// Service
public MyInfoResponseDto readMyInfo(User user) {
        User contextUser = userRepository.findById(user.getId()).get();

        List<Article> articleList = contextUser.getArticleList();
        List<MyInfoArticleResponseDto> myInfoArticleResponseDtoList = new ArrayList<>();
        for(Article article:articleList){
            myInfoArticleResponseDtoList.add(MyInfoArticleResponseDto.of(article));
        }



        return MyInfoResponseDto.builder()
                .myProfileImageUrl(contextUser.getProfileImageUrl())
                .nickname(contextUser.getNickname())
                .articleCount(contextUser.getUserInfo().getArticleCount())
                .followerCount(contextUser.getUserInfo().getFollowerCount())
                .followCount(contextUser.getUserInfo().getFollowCount())
                .myInfoArticleResponseDtoList(myInfoArticleResponseDtoList)
                .build();
    }
```

- Swagger 3.0을 통한 협업(API 관리 툴)
![image](https://user-images.githubusercontent.com/85334989/127982782-23cc6e1f-3815-4eb2-99b5-4d47f7a5d820.png)
```java
// dependency 추가
implementation group: 'io.springfox', name: 'springfox-boot-starter', version: '3.0.0'

// configuration
@Configuration
public class SpringFoxConfig{
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //apiInfo는 선택 요소임. 본 프로젝트에서 사용한 api들을 소개하는 내용을 담을 수 있는 부분임. 아래 apiInfo 메서드 볼 것.
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/api/**"))
                .build();
    }

    // 위 Docket의 apiInfo에 포함될 내용들 작성
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Instagram Clone Project")
                .version("1.0")
                .description("This is a teamwork-based project to clone a service [Instagram]. 2 Frontend devs," +
                        " 2 backend devs used React.js, Redux for client-side & Java Spring for server-side to make this service. We focused on making" +
                        " exactly same major functions on Instagram ; Likes, Follow(Following), Comments, Posts and etc.")
                .license("HangHae99 Team 22nd")
                .build();
    }
}
```

#### 2. 실제 서비스 모습
[시연 영상 YouTube 이동](https://www.youtube.com/watch?v=GtaLFIjpkXI)
- 회원가입

![회원가입](https://images.velog.io/images/ipinid613/post/4d590e8c-6baa-4d2e-94fc-96d362987915/image.png)

- 로그인

![로그인](https://images.velog.io/images/ipinid613/post/7a03eca5-1306-4803-97ea-1197e4522674/image.png)

- 메인 페이지

![메인 페이지 1](https://images.velog.io/images/ipinid613/post/96d375de-0cd6-40e3-bde2-9f22de6475cd/image.png)
![메인 페이지 2](https://images.velog.io/images/ipinid613/post/3fcbbedb-b960-494e-be09-d39ec80701b6/image.png)

- 마이 페이지

![마이 페이지](https://images.velog.io/images/ipinid613/post/72e38eb6-3aa7-417a-8ae7-cf9d3bb33417/image.png)

- 프로필 편집

![프로필 편집](https://images.velog.io/images/ipinid613/post/3d86bf3b-c5ca-40b5-afd4-25f4319f2d15/image.png)

#### 3. 회고 및 피드백
- 클론코딩이라는 주제와 맞게 실제 서비스 중인 인스타그램과 최대한 비슷해보이고, 기능도 작동하는 웹페이지를 제작하는 것이 목표였습니다.<br></br>
 가장 먼저 인스타그램 웹페이지에 접속해 기능 분석을 했습니다. 1)좋아요(토글 : 1회 누르면 true, 1회 누르면 false 처리), 2)팔로우/팔로잉(숫자로 표현), 3)마이페이지. 세 가지가 핵심적인 기능이었습니다. 다른 팀에서 구현을 포기한 부분이기도 해서 이 기능들을 구현하는 것을 목표로 설정했습니다.<br></br>
 Spring 환경에서 회원가입/로그인/게시판/댓글 구현은 지난 프로젝트에서 다뤄보아 어렵지 않게 빠르게 완성할 수 있었습니다. 핵심기능들을 구현하는데 오랜 시간을 쏟았습니다. 기능을 구현하기 위해 DB 설계(단방향, 양방향 연관관계 매핑)에 관한 공부를 하였고, 협업을 통해 목표했던 기능들도 모두 구현하는데 성공했습니다.<br></br>
 배포 이후에는 약 40여명의 가입자를 받아 서비스에서 나타나는 가장 큰 문제였던 예외처리 관련 피드백을 수용하여 개선했습니다. 서비스가 복잡해질수록 예외처리가 중요함을 깨달았습니다. 서버로 잘못된 요청이 들어온다고 해서 서버가 작동을 멈춰서는 안되고, 적절한 에러메시지를 띄워줌으로써 UX를 향상하거나 악의적 접근을 차단할 수 있기 때문입니다. 때문에 가능한 모든 경우의 수를 찾아 예외처리 코드를 작성하였고, 정리된 표를 프론트엔드에게 공유하여 클라이언트 측에서 1차로 잘못된 요청을 차단하도록 개선하였습니다.
