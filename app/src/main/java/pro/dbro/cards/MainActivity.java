package pro.dbro.cards;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import pro.dbro.cards.model.Card;

public class MainActivity extends FragmentActivity implements HandFragment.HandFragmentListener, CardFragment.CardFragmentListener {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card("Reya Dawnbringer", "https://www.wizards.com/magic/images/tournamentcenter/2007/gameday_reya.jpg"));
        cards.add(new Card("Capricious Effect", "http://www.wizards.com/mtg/images/daily/ld/ld29_efreet.jpg"));

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, HandFragment.newInstance(cards))
                    .commit();
            Log.i(TAG, "Added HandFragment");
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        Log.i(TAG, "onFragmentInteraction " + uri.toString());
    }
}
