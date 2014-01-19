package pro.dbro.cards;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.ArrayList;

import pro.dbro.cards.model.Card;
import pro.dbro.cards.model.Game;

/**
 * Created by davidbrodsky on 1/18/14.
 */
public class GameFragmentPagerAdapter extends FragmentPagerAdapter {
    private static final String TAG = "GameFragmentPagerAdapter";

    private Game mGame;

    public GameFragmentPagerAdapter(FragmentManager fm, Game game){
        super(fm);
        mGame = game;
    }

    @Override
    public Fragment getItem(int i) {
        Log.i(TAG, "getItem " + i);

        Fragment result = null;
        switch(i){
            case 0:
                result = HandFragment.newInstance(mGame.getHand());
                break;
            case 1:
                result = HandFragment.newInstance(mGame.getTable());
                break;
            case 2:
                result = HandFragment.newInstance(mGame.getOpponentHand());
                break;
        }
        return result;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
