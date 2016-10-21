# LoadingProgress
 When you want to load a picture from the network, you may use the progress bar to display the progress of the picture,This project uses the Drawable injection, support Fresco, UIL, GLIDE, Picasso
Four picture frames,Dumplites of [Fresco](https://github.com/search?utf8=%E2%9C%93&q=fresco)。<br>
###阅读中文文档 [请点击这里](https://github.com/peng8350/LoadingProgress/blob/origin/README_CN.md)
# Design sketch
  ![](https://github.com/peng8350/LoadingProgress/blob/origin/art/view1.gif)  ![](https://github.com/peng8350/LoadingProgress/blob/origin/art/view2.gif)  
  
  
# major function:
  1.Can dynamically change the progress of text size, color, can visiable。
  
  2.Can change the progress and background color。
  
  3.Ring support gradient。
  
  4.There are two styles of rings。
  
  ....
  
  Specific reference[DEMO](https://github.com/peng8350/LoadingProgress/tree/origin/Demo)
  
# usage:

  <h3>First of all, you must first create a CircleProgress or RectanglePropres </h3>
    
  ```Java
  CircleProgress progress = new CircleProgress.Builder().
                            setTextColor(Color.RED)
                            //setting your property...
                            
                            .build();
  progress.inject(your SimpleDraweeView or ImageView);
  ```

  <h3>Then, listen to the progress of the download picture changes, for different frameworks:</h3>
  
  <h3> > > Fresco: </h3>
  
  ```Java
       /*
       * Congratulations, you don't need to listen to the picture to load the progress of change
       */
  ```
  <h3> > > Glide: </h3>
  
  ```Java
       Glide.with(context).using(new ProgressModelLoader(new Handler() {
				@Override
				public void handleMessage(Message msg) {
					progress.setLevel(msg.arg1);
                    progress.setMaxValue(msg.arg2);
				}
			})).load(info.getUrl()).into(imageView);
       
  ````
  
  <h3> > > Picasso: </h3>
  
  ```Java
        SquareUtils.getPicasso(context, new SquareUtils.ProgressListener() {

				@Override
				public void update(final long current, final long total) {
                                        //Here is the relationship between the child thread
					handler.post(new Runnable() {
						@Override
						public void run() {
							progress.setMaxValue(total);
							progress.setLevel((int) current);
						}
					});
				}
			}).....loadyoururl
       
  ```
  
  <h3> > > UIL: </h3>
  
  ```Java
               
                //In the interface provided by ImageLoadingProgressListener UIL inside:
				@Override
				public void onProgressUpdate(String s, View view, int i, int i1) {
					progress.setLevel(i);
                    progress.setMaxValue(i1);
				}
       
  ```
  
  <h3> > >  Custom: </h3>
  
  ```Java
        //In your progress monitor callback method
        progress.setLevel(current);
        progress.setMaxValue(total);
       
  ```
 
# Attribute description:

 <h3> Public: </h3>
  `setTextColor` Set the progress bar in the middle of the text color<br>
  `setTextSize`  Set the size of the middle of the progress bar<br>
  `setTextShow`  Set whether the intermediate text is displayed<br>
  
  <h3>CircleProgress:</h3>
   
  `setCircleWidth`   Set the width of the ring<br>
  `setCircleRadius`  Set the radius of the circle<br>
  `setProgressColor` Set the ring has been loaded on the progress of the color<br>
  `setBottomColor`   Set the color of the ring not loaded<br>
  `setStyle`         Channge the style,enum of CircleStyle.Fan or Ring<br>
  `setGradientColor` set the color of gradient
  
  <h3>CircleProgress:</h3>
   
  `setRecBottomColor`   Set the color of the bottom of the long progress<br>
  `setRecProgressColor`  Set the background color that the long progress has been loaded<br>
  `setProgressColor` Set the ring has been loaded on the progress of the color<br>
  `setRecHeight`   Set the width of the long progress<br>
  `setRectProgressImage`  Use custom images to replace text to display (set up will not display text)<br>
  `setPosition` Set the position of the display indicator
  
# Hope
  
  If you find that you can listen to Xutils2,3 or volley and other frameworks to download progress changes in the way, you can tell me, I modify the.
  
  If you find BUG, can also be written in issue。
  
# About Me

  a junior student still study at School, love programming, especially Android
  
  Email:83508440@qq.com

  
