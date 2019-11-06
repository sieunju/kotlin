like this
Visual 적인 요소들을 만들어 놓은 Kotlin Library 입니다.

# CustomBehavior
## Description
`CoordinatorLayout.Behavior`를 기반으로 위치, 크기, 투명도를 표현 할 수 있는 라이브러리 입니다.

### Example GIF
![result](https://user-images.githubusercontent.com/33802191/64616773-31201300-d418-11e9-92e4-86b52c6bccdd.gif)

## API
xml에서 TranslationBehavior Attribute 사용시`'tools:ignore="missingPrefix"\` 를 선언해주시길 바랍니다.
- *attrs.xml* 파일에 속성에 대한 정의가 명시 되어 있고, 좀더 부연 설명을 하자면.
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
    - ![custom_behavior_캡처](https://user-images.githubusercontent.com/33802191/64620890-aa6f3400-d41f-11e9-9c88-0e53a6ee1c39.jpg)

## Sample Code 
이해를 돕기 위해 Dimens, Colors에 대한 값은 하드코딩함을 알려드립니다.

1. Code
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

## Description
기존 ProgressBar를 이용하여 진행률을 노출할때 실시간으로 setProgress , incrementProgressBy를 이용하여 표현하기에는 제한 사항이 많았습니다. 좀 더 자연스러운 표현을 위한 SurfaceView 기반의 클래스 입니다.

### Example GIF HorizonTal Type
![kotlinStudy_2019-09-16-13-13-13_1](https://user-images.githubusercontent.com/33802191/64938322-76b96180-d898-11e9-8dbb-80461cace116.gif)
### Example GIF Vertical Type 
![kotlinStudy_2019-09-16-13-27-54_1](https://user-images.githubusercontent.com/33802191/64938324-7751f800-d898-11e9-9309-62d0f46fa420.gif)

## __HOW TO__
CustomProgressView Module > res > values > attrs.xml
  - **attrs.xml** 파일에 속성에 대한 정의가 명시 되어 있고, 좀더 부연 설명을 하자면.
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
    
## Sample Code
이해를 돕기 위해 Dimens, Colors에 대한 값은 하드코딩하였습니다.

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
 
## Description
RecyclerView 에서 사용자가 원하는 위치에 따라서 ReDraw 를 표현할수 있는 라이브러리 입니다.
- Example GIF
![example](https://user-images.githubusercontent.com/33802191/67152949-670bbd80-f31b-11e9-87ea-7163f26c187b.gif)

## HOW TO
ParallaxViewHolder Module 에서 ParallaxViewHolder 만 가져와서 사용자 환경에 맞게 세팅 하시면 됩니다. 나머지 BaseAdapter, BaseViewHolder...은 테스트 영상을 위해서 추가된 클래스입니다. 

> 각 변수 및 수정 가능한 함수에 대해서 사용법을 알려드립니다.
- mActionStartPos   사용자가 이 ViewHolder 액션을 취하고 싶은 디바이스 위치 (비율 기준)
- mActionEndPos     사용자가 이 ViewHolder 액션을 끝내고 싶은 디바이스 위치 (비율 기준)
- mMaxHeight        사용자가 표현하고 싶은 최대 View 높이
- mMinHeight        사용자가 표현하고 싶은 최소 View 높이
- onScrollChanged   이 Override 함수에서 동작을 취합니다.
    시작 지점에서 끝지점까지 Percentage 로 나타냅니다. (0.0~1.0)
- bindView          사용자 원하는 대로 변경하시면 되겠습니다.
- bindAlpha         이 함수에서 사용자가 나타내고 싶은 Alpha 값을 변경하면 되겠습니다.
  (단, R.layout.view_holder_parallax.xml 에서 알맞게 수정 하셔야 합니다.) 
