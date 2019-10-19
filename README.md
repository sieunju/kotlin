> 안녕하세요. 반갑습니다.

>> _Visual 적인 요소들을 만들어 놓은 Kotlin Library 입니다._

# CustomBehavior

## __Description__
>> CoordinatorLayout.Behavior를 기반으로 위치, 크기, 투명도를 표현 할 수 있는 라이브러리 입니다.
- Example GIF <br>
![](https://user-images.githubusercontent.com/33802191/64616773-31201300-d418-11e9-92e4-86b52c6bccdd.gif)

## __HOW TO__

  ###### _※xml에서 TranslationBehavior Attribute 사용시\'tools:ignore="missingPrefix"\' 를 선언해주시길 바랍니다.※_
  
  - <b>attrs.xml</b> 파일에 속성에 대한 정의가 명시 되어 있고, 좀더 부연 설명을 하자면.
    - behavior_dependId     -> Dependency View의 Id값을 입력합니다.
    - behavior_dependType   -> Dependency 스크롤 타입입니다. 기본 값은 수직.
    - behavior_dependPin    -> app:layout_collapseMode="pin" 속성을 가진 View 의 높이값을 입력합니다. 기본값은 OS ActionBar 높이.
    - behavior_dependRange  -> Dependency Scroll Range. 기본 값은 Scroll 최대 값.
    - behavior_endAlpha     -> Child View 끝나는 지점의 투명도 값 (단위는 float)
    - behavior_endWidth     -> Child View 끝나는 지점의 너비 값 (단위는 DP)
    - behavior_endHeight    -> Child View 끝나는 지점의 높이 값 (단위는 DP)
    - behavior_endX         -> Child View 를 이동하고 싶은 X 좌표 단위는 DP, Format {기준, X 좌표} or {X 좌표}
          - Left 기준으로 정한다면 {s,좌표} or {좌표} 
          - Right 기준으로 정한다면 {e,좌표}
    - behavior_endY         -> Child View 를 이동하고 싶은 Y 좌표 단위는 DP, Format {기준, Y 좌표} or {기준,Y 좌표}
          - Top 기준으로 정한다면 {s,좌표} or {좌표} 
          - Bottom 기준으로 정한다면 {e,좌표}
    ###### _※behavior_endX, behavior_endY 에 대한 예시 이미지※_

![custom_behavior_캡처](https://user-images.githubusercontent.com/33802191/64620890-aa6f3400-d41f-11e9-9c88-0e53a6ee1c39.jpg)

## __Sample Code__
###### _※이해를 돕기 위해 Dimens, Colors에 대한 값은 하드코딩하였습니다.※_
1 Code
```
<TextView
  android:layout_width="wrap_content"
  android:layout_height="wrap_content"
  android:alpha="0"
  android:text="Hello hmJu"
  android:textColor="#FFFFFF"
  android:textSize="15sp"
  android:layout_marginLeft="5dp"
  android:elevation="5dp"
  app:layout_behavior="com.hmju.custombehavior.TranslationBehavior"
  app:behavior_dependId="@+id/abl_header"
  app:behavior_dependPin="@dimen/height_header_pin"
  app:behavior_endY="25"
  app:behavior_endAlpha="1"/>
```
2 Code
```
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
```


# CustomProgressView

## __Description__
>> 기존 ProgressBar를 이용하여 진행률을 노출할때 실시간으로 setProgress , incrementProgressBy를 이용하여 표현하기에는 제한 사항이
많았습니다. 좀 더 자연스러운 표현을 위한 SurfaceView 기반의 클래스 입니다.
- Example GIF HorizonTal Type <br>
![kotlinStudy_2019-09-16-13-13-13_1](https://user-images.githubusercontent.com/33802191/64938322-76b96180-d898-11e9-8dbb-80461cace116.gif)

- Example GIF Vertical Type <br>
![kotlinStudy_2019-09-16-13-27-54_1](https://user-images.githubusercontent.com/33802191/64938324-7751f800-d898-11e9-9309-62d0f46fa420.gif)

## __HOW TO__
  CustomProgressView Module > res > values > attrs.xml
  - <b>attrs.xml</b> 파일에 속성에 대한 정의가 명시 되어 있고, 좀더 부연 설명을 하자면.
    - type -> 수평,수직으로 표현 할거에 대한 속성값       
    default 수평
    - gradientRadius -> View의 각 모서리에 대한 각도값 
    default 0
    - gradientStartColor -> View의 Gradient를 표현하기 위한 StartColor 
    default Black
    - gradientCenterColor -> View의 Gradient를 표현하기 위한 CenterColor 
    default Black
    - gradientEndColor -> View의 Gradient를 표현하기 위한 EndColor 
    default Black
    - bgColor -> View의 Background Color 
    default Black
    - max -> View의 진행률을 노출하고 싶은 최대값 ex.) 0 ~ 100 까지 노출하고 싶다 -> 100 , 0~ 500 까지 노출하고싶다 -> 500 
    default 100
    - min -> View의 첫 시작 진행률에 대한 값 ex.) 20 ~ Max로 노출하고싶다. -> 20
    default 0
    
## __Sample Code__
###### _※이해를 돕기 위해 Dimens, Colors에 대한 값은 하드코딩하였습니다.※_
1. Horizontal Type
```
<com.hmju.customprogressview.CustomProgressView
            android:id="@+id/v_progress"
            android:layout_width="match_parent"
            android:layout_height="30dp"
            android:layout_margin="20dp"
            app:layout_constraintLeft_toLeftOf="parent"
            app:layout_constraintRight_toRightOf="parent"
            app:layout_constraintTop_toTopOf="parent"
            app:layout_constraintBottom_toBottomOf="parent"
            app:bgColor="#e4e4e4"
            app:gradientStartColor="#FF0000"
            app:gradientCenterColor="#FF00FD"
            app:gradientEndColor="#0045FF"
            app:gradientLocation="0.3"
            app:gradientRadius="15dp"
            app:max="500"
            app:type="horizontal"
    />
```
2. Vertical Type
```
<com.hmju.customprogressview.CustomProgressView
            android:id="@+id/v_progress"
            android:layout_width="30dp"
            android:layout_height="200dp"
            android:layout_margin="20dp"
            app:layout_constraintLeft_toLeftOf="parent"
            app:layout_constraintRight_toRightOf="parent"
            app:layout_constraintTop_toTopOf="parent"
            app:layout_constraintBottom_toBottomOf="parent"
            app:bgColor="#e4e4e4"
            app:gradientStartColor="#FF0000"
            app:gradientCenterColor="#FF00FD"
            app:gradientEndColor="#0045FF"
            app:gradientLocation="0.3"
            app:gradientRadius="15dp"
            app:max="500"
            app:type="vertical"
    />
```

# Parallaxviewholder
