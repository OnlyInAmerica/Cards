package pro.dbro.cards;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import pro.dbro.cards.model.Card;
import pro.dbro.cards.model.Game;

public class MainActivity extends FragmentActivity implements HandFragment.HandFragmentListener, CardFragment.CardFragmentListener, GameFragment.GameFragmentListener {
    private static final String TAG = "MainActivity";

    private String[] mDrawerItems;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;

    Game mGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerItems = getResources().getStringArray(R.array.drawer_items);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // Set the adapter for the list view
        mDrawerList.setAdapter(new DrawerItemAdapter(this, mDrawerItems));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        ArrayList<Card> hand = new ArrayList<Card>();
        hand.add(new Card("Reya Dawnbringer", "https://www.wizards.com/magic/images/tournamentcenter/2007/gameday_reya.jpg"));
        hand.add(new Card("Capricious Effect", "http://www.wizards.com/mtg/images/daily/ld/ld29_efreet.jpg"));

        ArrayList<Card> table = new ArrayList<Card>();
        table.add(new Card("Illusory Thoughts", "http://media.wizards.com/images/magic/daily/arcana/491_ds1_jkvgpw3ent.jpg"));
        table.add(new Card("Distract", "http://media.wizards.com/images/magic/daily/arcana/491_ds3_jkvgpw3ent.jpg"));

        ArrayList<Card> opponentTable = new ArrayList<Card>();
        opponentTable.add(new Card("Black Knight", "http://www.wizards.com/mtg/images/daily/features/27a_blackKnight_0ni7n.jpg"));
        opponentTable.add(new Card("Hoard-Smelter Dragon", "http://media.wizards.com/images/magic/tcg/products/scarsofmirrodin/ciyqwjmm5c_en.jpg"));

        mGame = new Game(table, hand, opponentTable);

        /*
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, GameFragment.newInstance(game))
                    .commit();
            Log.i(TAG, "Added GameFragment");
        }
        */

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


    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            navigateToDrawerSelection(position);
        }
    }

    private void navigateToDrawerSelection(int position) {
        // Create a new fragment and specify the planet to show based on position
        Fragment fragment = null;
        Log.i(TAG, "position " + position);

        switch(position){
            case 0:
                fragment = HandFragment.newInstance(mGame.getHand());
                break;
            case 1:
                fragment = HandFragment.newInstance(mGame.getTable());
                break;
            case 2:
                fragment = HandFragment.newInstance(mGame.getOpponentHand());
                break;
        }


        //fragment = InsaneFragment.newInstance("Insane position " + position);
        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.linearLayout, fragment)
                .addToBackStack(null)
                .commit();

        // Highlight the selected item, update the title, and close the drawer
        mDrawerList.setItemChecked(position, true);
        mDrawerLayout.closeDrawer(mDrawerList);
    }
}
