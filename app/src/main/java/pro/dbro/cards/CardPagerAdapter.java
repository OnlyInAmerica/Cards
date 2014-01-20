package pro.dbro.cards;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;
import java.util.HashMap;

import pro.dbro.cards.model.Card;

/**
 * Created by davidbrodsky on 1/18/14.
 */
public class CardPagerAdapter extends PagerAdapter implements ViewPager.OnPageChangeListener{
    private static final String TAG = "CardPagerAdapter";

    private ViewPager mViewPager;

    private ArrayList<Card> mCards;
    //private HashMap<Card, View> mViewMap;
    private Context mContext;
    private LayoutInflater mInflater;

    public CardPagerAdapter(Context c, ViewPager pager, ArrayList<Card> cards){
        super();
        mCards = cards;
        mInflater = (LayoutInflater) c.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        mContext = c;
        mViewPager = pager;
        mViewPager.setAdapter(this);
        mViewPager.setOnPageChangeListener(this);
        //mViewMap = new HashMap<Card, View>();
    }

    @Override
    public int getCount() {
        return mCards.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return false;
    }

    @Override
    public Object instantiateItem (ViewGroup container, int position){
        Log.i(TAG, "instantiateItem: " + position);
        Card card = mCards.get(position);
        View cardView = null;
        /*
        if(!mViewMap.containsKey(card)){
            cardView =  mInflater.inflate(R.layout.card, container);
            mViewMap.put(card, cardView);
            ((NetworkImageView) cardView.findViewById(R.id.image)).setImageUrl(card.getUrl(), Network.getImageLoader(mContext));
        }else{
            cardView = mViewMap.get(card);
        }
        */
        cardView =  mInflater.inflate(R.layout.card, container);
        ((TextView) cardView.findViewById(R.id.title)).setText(card.getName());
        ((NetworkImageView) cardView.findViewById(R.id.image)).setImageUrl(card.getUrl(), Network.getImageLoader(mContext));
        return cardView;
    }

    @Override
    public void destroyItem (ViewGroup container, int position, Object object){
        Log.i(TAG, "destroyItem: " + position);
        mCards.remove(position);
        container.removeView((View) object);
    }

    @Override
    public void onPageScrolled(int i, float v, int i2) {

    }

    @Override
    public void onPageSelected(int i) {
        Log.i(TAG, "onPageSelected: " + i);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }
}
