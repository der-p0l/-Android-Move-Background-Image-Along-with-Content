package example.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.NestedScrollView.OnScrollChangeListener;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

	/**
	 * Main scroll.
	 */
	NestedScrollView nswMain;
	/**
	 * Background images.
	 */
	ImageView ivBgImg1;
	ImageView ivBgImg2;
	/**
	 * Background image height.
	 */
	int bgImgHeight = 0;

	/**
	 * Normalized top position of the background.
	 */
	int bgImgScrollY = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Initialize variables
		nswMain = findViewById(R.id.nestedscrollview_main);
		ivBgImg1 = findViewById(R.id.imageview1_bgimg);
		ivBgImg2 = findViewById(R.id.imageview2_bgimg);

		// Set main scroll listener
		nswMain.setOnScrollChangeListener(new OnScrollChangeListener() {
			@Override
			public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
				onMainScrollChange(v, scrollX, scrollY, oldScrollX, oldScrollY);
			}
		});
	}

	/**
	 * Executed in every change of the main scroll.
	 *
	 * @param v          Scrolled element
	 * @param scrollX    New horizontal position
	 * @param scrollY    New vertical position
	 * @param oldScrollX Old horizontal position
	 * @param oldScrollY Old vertical position
	 */
	private void onMainScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
		// Initialize height of background image
		if(bgImgHeight == 0) {
			bgImgHeight = ivBgImg1.getHeight();
		}

		// Move the background infinitely according to the main scroll
		if(bgImgHeight > 0) {
			bgImgScrollY = scrollY % bgImgHeight;
			ivBgImg1.setTranslationY(-bgImgScrollY);
			ivBgImg2.setTranslationY(-bgImgScrollY + bgImgHeight);
		}
	}

}
