package pro.dbro.cards;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Scroller;

/**
 * Created by davidbrodsky on 1/11/14.
 */
public class ViewUtils {
    private static final String TAG = "ViewUtils";

    public static final int SWIPE_MIN_DISTANCE = 120;
    public static final int SWIPE_THRESHOLD_VELOCITY = 200;

    public static class FlingGestureListener extends GestureDetector.SimpleOnGestureListener {

        View mHost;

        int SCALE = 4;      // Scales animation speed

        public FlingGestureListener(View v){
            mHost = v;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if(e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                Log.i(TAG, "Swipe left!");
                return false; // Right to left
            }  else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                Log.i(TAG, "Swipe right!");
                return false; // Left to right
            }

            if(e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                Log.i(TAG, "Swipe up with velocity: " + velocityY);
                mHost.animate().yBy(-600).setInterpolator(new DecelerateInterpolator(2f));
                return true; // Bottom to top
            }  else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                Log.i(TAG, "Swipe down!");
                return false; // Top to bottom
            }
            Log.i(TAG, "onFling");
            return false;
        }
    }
}
