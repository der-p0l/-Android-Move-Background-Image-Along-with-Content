# [Android] Move Background Image Along with Content
Simple example of how to achieve a moving background image with Android layouts.

The intended behaviour is to use an image as background, then when the user scrolls on the main content, the background image should scroll along with it.
First, we need to create (or use) an image that has according dimensions with our device screen (640x960 on phone, for example). That image also needs to have proper borders to make it tileable (so we don't notice the border if we put two images next to each other). Then we make two copies of that image, and set them one on top of the other. When the user scrolls the main content, the whole background moves along; and if we reach the bottom of the second image, or the top of the first one, the background 'jumps' to make the effect seamless.
