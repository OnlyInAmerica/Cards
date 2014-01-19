package pro.dbro.cards;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.ArrayList;

import pro.dbro.cards.model.Card;

/**
 * Created by davidbrodsky on 1/18/14.
 */
public class HandFragmentPagerAdapter extends FragmentPagerAdapter {
    private static final String TAG = "HandFragmentPagerAdapter";

    private ArrayList<Card> mCards;

    public HandFragmentPagerAdapter(FragmentManager fm, ArrayList<Card> cards){
        super(fm);
        mCards = cards;
    }

    @Override
    public Fragment getItem(int i) {
        Log.i(TAG, "Making CardFragment for Card " + mCards.get(i).getName());
        return CardFragment.newInstance(mCards.get(i));
    }

    @Override
    public int getCount() {
        return mCards.size();
    }
}
