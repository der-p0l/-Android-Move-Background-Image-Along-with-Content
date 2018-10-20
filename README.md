# [Android] Move Background Image Along with Content
Simple example of how to achieve a moving background image with Android layouts.  
The intended behaviour is to use an image as background, then when the user scrolls on the main content, the background image should scroll along with it.  

First, we need to create (or use) an image that has according dimensions with our device screen (640x960 on phone, for example). That image also needs to have proper borders to make it tileable (so we don't notice the border if we put two images next to each other). To see an example image, please refer to '*img_background.png*'.  
Then we make two copies of that image, and set them one on top of the other. When the user scrolls the main content, the whole background moves along; and if we reach the bottom of the second image, or the top of the first one, the background 'jumps' to make the effect seamless.  

**Technical Explanation:**  
*activity_main.xml*  
&nbsp;&nbsp;&nbsp;&nbsp;We use a RelativeLayout as a global container. Inside we have a FrameLayout and a NestedScrollView. The FrameLayout is our background layout, so inside of it we have two equal ImageViews of the background image. The NestedScrollView will contain the main content.  
&nbsp;&nbsp;&nbsp;&nbsp;I was tempted to use a LinearLayout instead of a FrameLayout as the background container, but in that case the second ImageView is created outside of the screen, and it doesn't get rendered when we move it. So instead we use the FrameLayout, which creates the ImageViews inside the screen at the same time.  
&nbsp;&nbsp;&nbsp;&nbsp;The NestedScrollView in this example only contains a vertical LinearLayout, and inside of it there is Space (so we can test the scroll).  

*MainActivity.java*  
&nbsp;&nbsp;&nbsp;&nbsp;First we declare some variables:  
&nbsp;&nbsp;&nbsp;&nbsp;- NestedScrollView nswMain: It's the main NestedScrollView, we use it to track the scroll.  
&nbsp;&nbsp;&nbsp;&nbsp;- ImageView ivBgImg1/ivBgImg2: The background ImageViews, we will translate their Y position.  
&nbsp;&nbsp;&nbsp;&nbsp;- int bgImgHeight: This is the height of the background image, we obtain it from *ivBgImg1*. Notice that we can't use it as final, because the height of *ivBgImg1* it's **not** the height of our image; *ivBgImg1* will adapt to the height of the screen.  
&nbsp;&nbsp;&nbsp;&nbsp;- int bgImgScrollY: It's the absolute translation of the background.  
&nbsp;&nbsp;&nbsp;&nbsp;Then we create the activity with *onCreate* as always. But we initialize *nswMain*, *ivBgImg1/ivBgImg2*, and set the *nswMain* ScrollChangeListener. This listener calls another private method named *onMainScrollChange*.  
&nbsp;&nbsp;&nbsp;&nbsp;Now we have a method that gets executed every time the main content scrolls. Inside it we need to initialize *bgImgHeight*. We didn't do it inside *onCreate* because when that method gets executed the height of *ivBgImg1* is 0dp.  
&nbsp;&nbsp;&nbsp;&nbsp;After that all we have to do is calculate *bgImgScrollY*, which is equal to *scrollY % bgImgHeight* (where *scrollY* is the Y position of the main scroll). This gets done only if *bgImgHeight* is greater than 0 (i.e. if we could initialize *bgImgHeight* succesfully), because we have a division.  
&nbsp;&nbsp;&nbsp;&nbsp;Now that we calculated *bgImgScrollY*, we translate the first image to the negative of that number, and the second image to the negative of that number plus *bgImgHeight*. So we just put the second image below the first one.
