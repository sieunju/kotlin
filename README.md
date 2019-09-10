# kotlin
Visual 적인 요소들을 만들어 놓은 Kotlin Library 입니다.

# CustomBehavior
## CoordinatorLayout.Behavior를 기반으로 위치, 크기, 투명도를 표현 할 수 있는 Open Class
- Example GIF <br>
![](https://user-images.githubusercontent.com/33802191/64616773-31201300-d418-11e9-92e4-86b52c6bccdd.gif)

## __HOW TO__

  ###### _※xml에서 TranslationBehavior Attribute 사용시\'tools:ignore="missingPrefix"\' 를 선언해주시길 바랍니다.※_
  ---
  - <b>attrs.xml</b> 파일에 속성에 대한 정의가 명시 되어 있고, 좀더 부연 설명을 하자면.
    - behavior_dependId     -> Dependency View의 Id값을 입력합니다.
    - behavior_dependType   -> Dependency 스크롤 타입입니다. 기본 값은 수직.
    - behavior_dependPin    -> app:layout_collapseMode="pin" 속성을 가진 View 의 높이값을 입력합니다. 기본값은 OS ActionBar 높이.
    - behavior_dependRange  -> Dependency Scroll Range. 기본 값은 Scroll 최대 값.
    - behavior_endX         -> Child View 를 이동하고 싶은 X 좌표 단위는 DP, Format {기준, X 좌표} or {X 좌표}
          - Left 기준으로 정한다면 {s,좌표} or {좌표} 
          - Right 기준으로 정한다면 {e,좌표}
    - behavior_endY         -> Child View 를 이동하고 싶은 Y 좌표 단위는 DP, Format {기준, Y 좌표} or {기준,Y 좌표}
          - Top 기준으로 정한다면 {s,좌표} or {좌표} 
          - Bottom 기준으로 정한다면 {e,좌표}
    ###### _※behavior_endX, behavior_endY 에 대한 예시 이미지※_

![custom_behavior_캡처](https://user-images.githubusercontent.com/33802191/64620890-aa6f3400-d41f-11e9-9c88-0e53a6ee1c39.jpg)

    - behavior_endAlpha     -> Child View 끝나는 지점의 투명도 값 (단위는 float)
    - behavior_endWidth     -> Child View 끝나는 지점의 너비 값 (단위는 DP)
    - behavior_endHeight    -> Child View 끝나는 지점의 높이 값 (단위는 DP)
    
## __Sample Code__

\'        
<androidx.cardview.widget.CardView
                android:layout_width="80dp"
                android:layout_height="80dp"
                android:layout_marginTop="80dp"
                android:layout_marginRight="50dp"
                android:layout_gravity="right|top"
                app:cardCornerRadius="40dp"
                app:cardElevation="5dp"
                app:layout_behavior="com.hmju.custombehavior.TranslationBehavior"
                app:behavior_dependId="@+id/abl_header"
                app:behavior_dependPin="@dimen/height_header_pin"
                app:behavior_endX="e,10"
                app:behavior_endY="14"
                app:behavior_endWidth="30dp"
                app:behavior_endHeight="30dp"
        >
            
            <ImageView
                    android:layout_width="match_parent"
                    android:layout_height="match_parent"
                    android:src="@drawable/icon_face_b"
                    android:scaleType="centerCrop"
                    android:adjustViewBounds="true"
            />
</androidx.cardview.widget.CardView>
\'
