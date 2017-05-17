# LoadingProgress
 When you want to load a picture from the network, you may use the progress bar to display the progress of the picture,This project uses the Drawable injection, support Fresco, UIL, GLIDE, Picasso
Four picture frames,Dumplites of [Fresco](https://github.com/facebook/fresco)。<br>

###阅读中文文档 [请点击这里](https://github.com/peng8350/LoadingProgress/blob/master/README_CN.md)
# Design sketch
  ![](https://github.com/peng8350/LoadingProgress/blob/master/art/view.gif)
  
   ![](https://github.com/peng8350/LoadingProgress/blob/master/art/1.gif)
    ![](https://github.com/peng8350/LoadingProgress/blob/master/art/2.gif) <br> 
      ![](https://github.com/peng8350/LoadingProgress/blob/master/art/3.gif)
       ![](https://github.com/peng8350/LoadingProgress/blob/master/art/4.gif) <br> 

# Install
   <h3>Gradle:</h3>
  ```Java
    compile 'com.jpeng:LoadingProgress:1.1.0'
  ```
   <h3>Jar package:</h3>
   [Jar package Download](https://github.com/peng8350/LoadingProgress/blob/master/loadprogress.jar)

# Major Function:
  - Can dynamically change the progress of text size, color,type,can visiable。
  
  - Can change the progress and background color。
  
  - Ring support gradient。
  
  - There are two styles of rings。
  
  - Support into ListView Cache;
  
  - More style of progress
  
  Specific reference[DEMO](https://github.com/peng8350/LoadingProgress/tree/master/Demo)
  
# Usage:

  <h3>First of all, you must first create a CircleProgress or RectanglePropres and other </h3>
    
  ```Java
  CircleProgress progress = new CircleProgress.Builder().
                            setTextColor(Color.RED)
                            //setting your property...
                            
                            .build();
   
   // if you use ImageView to load(Glide,UIL,Picasso)
  progress.inject(your  ImageView);
  //if you use Fresco
  progress.injectFresco(your SimpleDraweeView)
  ```

  <h3>Then, listen to the progress of the download picture changes, for different frameworks:</h3>
  
  <h3> Fresco: </h3>
  
  ```Java
       /*
       * Congratulations, you don't need to listen to the picture to load the progress of change
       */
  ```

  <h3> UIL: </h3>
  
  ```Java
               
                //In the interface provided by ImageLoadingProgressListener UIL inside:
				@Override
				public void onProgressUpdate(String s, View view, int i, int i1) {
					progress.setLevel(i);
                    progress.setMaxValue(i1);
				}
       
  ```
  
  <h3> The other: </h3>
  
  ```Java
        /*
        *If you are use Glide and Picasso 
        *you can refer to my demo how to listening the Downloading
        ×But in my demo seems not corrent
        *Because in my test,some pictures cannot display
        * may be the reason is about okhttp
        */
        //In your progress monitor callback method
        progress.setLevel(current);
        progress.setMaxValue(total);
       
  ```
  <h3> LevelProgress </h3>
   Note that the construction method which is passed levellistdrawable or level can change the resource of the LayerDrawable, or no effect.
    Example:
   ```Java
    <?xml version="1.0" encoding="utf-8"?>
    <layer-list xmlns:android="http://schemas.android.com/apk/res/android">
        <item
            android:id="@android:id/background"
            android:drawable="@mipmap/battery_full"/>
        <item android:id="@android:id/progress">
            <clip
                android:drawable="@mipmap/battery_none"
                android:gravity="bottom"
                android:clipOrientation="vertical"/>
        </item>
    </layer-list>
   ```
    
   If all of the above is not what you want.,you can custom a class extends BaseProgress,Draw a picture of what you want.:
   ```Java
      public class CustomProgress extends BaseProgress{
          @Override
          public void DrawOther(Canvas canvas){
              //Custom yourself
          }
      }
   ```
 
# Attribute description:

 <h3> Public: </h3>
  `setTextColor` Set the progress bar in the middle of the text color<br>
  `setTextSize`  Set the size of the middle of the progress bar<br>
  `setTextShow`  Set whether the intermediate text is displayed<br>
  `setTextType`  set the text type<br>
  `setCustomText`   set the custom text replace the origin number text<br>
  `setTextXOffset`  set the X distance to the center pointer<br>
  `setTextYOffset`  set the Y distance to the center pointer
     
  <h3>CircleProgress:</h3>
   
  `setCircleWidth`   Set the width of the ring<br>
  `setCircleRadius`  Set the radius of the circle<br>
  `setProgressColor` Set the ring has been loaded on the progress of the color<br>
  `setBottomColor`   Set the color of the ring not loaded<br>
  `setStyle`         Channge the style,enum of CircleStyle.Fan or Ring<br>
  `setGradientColor` set the color of gradient<br>
  `setBottomWidth`  set the width of bottom<br>
  `setFanPadding`   set the padding line to the circle with FanStyle 
   
  <h3>RectangleProgress:</h3>
   
  `setRecBottomColor`   Set the color of the bottom of the long progress<br>
  `setRecProgressColor`  Set the background color that the long progress has been loaded<br>
  `setProgressColor` Set the ring has been loaded on the progress of the color<br>
  `setRecHeight`   Set the width of the long progress<br>
  `setRectProgressImage`  Use custom images to replace text to display (set up will not display text)<br>
  `setPosition` Set the position of the display indicator
   
   <h3>RotateProgress:</h3>
    `setSpeed`   set the speed of rotate<br>
    `setDirection` set the rotate direction
    
   <h3>LevelProgress:</h3>
   `EnableAlpha` allow the alpha change by progress
# Hope
  
  If you find that you can listen to Xutils2,3 or volley and other frameworks to download progress changes in the way, you can tell me, I modify the.
  
  If you find BUG, can also be written in issue。
  
# About Me

  a junior student still study at School, love programming, especially Android
  
  Email:peng8350@gmail.com

  
