# 加载进度条
 当你想从网络中加载一张图片时,你可能会用到进度条来显示加载图片的进度,本项目采用Drawable注入式,支持Fresco,UIL,GLIDE,Picasso
  四大图片框架,参考了[Fresco](https://github.com/facebook/fresco)。
# 效果图
  ![](https://github.com/peng8350/LoadingProgress/blob/master/art/view1.gif)  ![](https://github.com/peng8350/LoadingProgress/blob/master/art/view2.gif)  
  
   ![](https://github.com/peng8350/LoadingProgress/blob/master/art/1.gif) </t> 
    ![](https://github.com/peng8350/LoadingProgress/blob/master/art/2.gif) <br> 
      ![](https://github.com/peng8350/LoadingProgress/blob/master/art/3.gif) </t> 
       ![](https://github.com/peng8350/LoadingProgress/blob/master/art/4.gif) <br> 
   
# 编译
  <h3>Gradle:</h3>
  ```Java
    compile 'com.jpeng:LoadingProgress:1.0.8'
  ```
   <h3>Jar包:</h3>
   [Jar包下载](https://github.com/peng8350/LoadingProgress/blob/master/loadprogress.jar)  
# 主要功能:
  1.可动态改变进度文字大小,颜色,可显性。
  
  2.可改变进度和背景颜色。
  
  3.圆环支持渐变。
  
  4.圆环有两种风格。
  
  5.支持ListView缓存;
    
  6.多种不同的进度条
  ....
  
  具体可参考[DEMO](https://github.com/peng8350/LoadingProgress/tree/master/Demo)
  
# 用法:

  <h3>首先,你必须先创建一个CircleProgress或者RectanglePropress或者其他</h3>
    
  ```Java
  CircleProgress progress = new CircleProgress.Builder().
                            setTextColor(Color.RED)
                            //设置你的属性...
                            
                            .build();
  //如果你使用的是ImageView(Glide,UIL,Picasso)
   progress.inject(your  ImageView);
   //如果你使用的框架是Fresco
   progress.injectFresco(your SimpleDraweeView)
  ```

  <h3>接着,监听下载图片的进度变化,对于不同框架: </h3>
  
  <h3> > > Fresco: </h3>
  
  ```Java
       /*
       * 恭喜你,你不需要监听图片加载进度的变化
       *
       */
  ```

  <h3> > > UIL: </h3>
  
  ```Java
               
                //在UIL提供的接口ImageLoadingProgressListener里面：
				@Override
				public void onProgressUpdate(String s, View view, int i, int i1) {
					progress.setLevel(i);
                    progress.setMaxValue(i1);
				}
       
  ```
  
  <h3> > >  其他: </h3>
  
  ```Java
          /*
          *如果你使用的是Glide或者Picasso 
          *你可以参考一下我的demo如何实现监听下载进度的回调
          ×但是在我的demo这种方式未必是对的
          *因为在我的测试里面,发现有一些图片没能加载出来,
          *可能是okhttp的原因
          */
        //在你的进度监听回调方法里面
        progress.setLevel(current);
        progress.setMaxValue(total);
       
  ```
  <h3> > >  LevelProgress </h3>
  注意,构造方法里面传的是是LayerDrawable或者levellistdrawable可以更改level的resource,不然没有效果。
  例子:
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
  
  如果以上我所实现的进度都不是你想要的,你可以自定义继承BaseProgress,自己画一个你想要的进度出来:
  ```Java
    public class CustomProgress extends BaseProgress{
           @Override
        public void DrawOther(Canvas canvas){
            //Custom yourself
        }
    }
   ```
# 属性说明:

 <h3> 公有: </h3>
  `setTextColor` 设置进度条中间文字颜色<br>
  `setTextSize`  设置进度条中间文字大小<br>
  `setTextShow`  设置中间文字是否显示<br>
  `setTextType`  设置字体类型
  
  <h3>CircleProgress:</h3>
   
  `setCircleWidth`   设置圆环的宽度<br>
  `setCircleRadius`  设置圆环的半径<br>
  `setProgressColor` 设置圆环已经加载进度中的颜色<br>
  `setBottomColor`   设置圆环未加载背景的颜色<br>
  `setStyle`         变换风格,枚举CircleStyle.Fan or Ring<br>
  `setGradientColor` 设置渐变颜色
  
  <h3>RectangleProgress:</h3>
   
  `setRecBottomColor`   设置长进度底部的颜色<br>
  `setRecProgressColor`  设置长进度已经加载的背景颜色<br>
  `setProgressColor` 设置圆环已经加载进度中的颜色<br>
  `setRecHeight`   设置长进度的宽度<br>
  `setRectProgressImage`  使用自定义图片来替换文字来展示(设置了就不会显示文字)<br>
  `setPosition` 设置展示指示器的位置
  
   <h3>RotateProgress:</h3>
    `setSpeed`   设置旋转速度
    `setDirection` 设置旋转方向
      
   <h3>LevelProgress:</h3>
    `EnableAlpha` 允许alpha随着进度变化而变化
# 希望
  
  如果你发现可以监听Xutils2,3或者volley等其他框架下载进度变化的方法,可以告诉我,方便我修改。
  
  如果发现BUG,也可以在issue写下。
  
# 关于我

  一个在校大三的学生,热爱编程,尤其是Android
  
  Email:83508440@qq.com

  
