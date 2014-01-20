package pro.dbro.cards;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import pro.dbro.cards.model.Card;


public class HandFragment extends Fragment {
    private static final String TAG = "HandFragment";

    private static final String ARG_CARDS = "cards";

    //private CardPagerAdapter mAdapter;
    private HandFragmentPagerAdapter mAdapter;
    private ViewPager mViewPager;
    private ArrayList<Card> mCards;

    private HandFragmentListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param cards Parameter 1.
     * @return A new instance of fragment HandFragment.
     */
    public static HandFragment newInstance(ArrayList<Card> cards) {
        HandFragment fragment = new HandFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_CARDS, cards);
        fragment.setArguments(args);
        return fragment;
    }

    public HandFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCards = getArguments().getParcelableArrayList(ARG_CARDS);
            Log.i(TAG, "Got Cards onCreate " + mCards.size());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_hand, container, false);
        Log.i(TAG, "onCreateView for Cards " + mCards.toString());
        return root;
    }

    @Override
    public void onActivityCreated (Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        mViewPager = (ViewPager) getView().findViewById(R.id.pager);
        mAdapter = new HandFragmentPagerAdapter(this.getChildFragmentManager(), mCards);
        mViewPager.setAdapter(mAdapter);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (HandFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement HandFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface HandFragmentListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
