package pro.dbro.cards;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by davidbrodsky on 1/18/14.
 */
public class Network {

    private static RequestQueue mRequestQueue;
    private static ImageLoader mImageLoader;

    public static RequestQueue getRequestQueue(Context c){
        if(mRequestQueue == null)
            mRequestQueue = Volley.newRequestQueue(c);
        return mRequestQueue;
    }

    public static ImageLoader getImageLoader(Context c){
        if(mImageLoader == null)
            mImageLoader = new ImageLoader(getRequestQueue(c), new LruBitmapCache(getDefaultLruCacheSize()));
        return mImageLoader;
    }

    public static int getDefaultLruCacheSize() {
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        final int cacheSize = maxMemory / 8;

        return cacheSize;
    }

    public static class LruBitmapCache extends LruCache<String, Bitmap> implements ImageLoader.ImageCache {

        public LruBitmapCache(int maxSize) {
            super(maxSize);
        }

        @Override
        protected int sizeOf(String key, Bitmap value) {
            return value.getRowBytes() * value.getHeight();
        }

        @Override
        public Bitmap getBitmap(String url) {
            return get(url);
        }

        @Override
        public void putBitmap(String url, Bitmap bitmap) {
            put(url, bitmap);
        }

    }
}
