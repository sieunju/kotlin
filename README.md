# kotlin
Visual 적인 요소들을 만들어 놓은 Kotlin Library 입니다.

# CustomBehavior
## CoordinatorLayout.Behavior를 기반으로 위치, 크기, 투명도를 표현 할 수 있는 Open Class
- Example GIF <br>
![](https://user-images.githubusercontent.com/33802191/64616773-31201300-d418-11e9-92e4-86b52c6bccdd.gif)

### How To Use

  ※ xml에서 TranslationBehavior Attribute 사용시 <b>'tools:ignore="missingPrefix"'</b> 를 선언해주시길 바랍니다. ※
  ---
  - <b>attrs.xml</b> 파일에 속성에 대한 정의가 명시 되어 있고, 좀더 부연 설명을 하자면.
    - behavior_dependId     -> Dependency View의 Id값을 입력합니다.
    - behavior_dependType   -> Dependency 스크롤 타입입니다. 기본 값은 수직.
    - behavior_dependPin    -> app:layout_collapseMode="pin" 속성을 가진 View 의 높이값을 입력합니다. 기본값은 OS ActionBar 높이.
    - behavior_dependRange  -> Dependency Scroll Range. 기본 값은 Scroll 최대 값.
    - behavior_endX         -> Child View 를 이동하고 싶은 X 좌표 단위는 DP 
          Format {기준, X 좌표} 왼쪽을 기준으로 한다면 s,좌표 or 좌표
    - behavior_endY         -> Child View 를 이동하고 싶은 Y 좌표 단위는 DP 
          Foramt {기준, Y 좌표}
      <br>
    ※behavior_endX, behavior_endY 에 대한 예시 이미지※
      <br><br>
      ![custom_behavior_캡처](https://user-images.githubusercontent.com/33802191/64620890-aa6f3400-d41f-11e9-9c88-0e53a6ee1c39.jpg)
      <br><br>
