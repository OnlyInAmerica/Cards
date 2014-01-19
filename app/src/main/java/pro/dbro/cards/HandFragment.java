package pro.dbro.cards;

import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import pro.dbro.cards.model.Card;


public class HandFragment extends Fragment {
    private static final String TAG = "HandFragment";

    private static final String ARG_CARDS = "cards";

    private CardFragmentPagerAdapter mAdapter;
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
        mViewPager = (ViewPager) root.findViewById(R.id.pager);
        mAdapter = new CardFragmentPagerAdapter(((FragmentActivity) this.getActivity()).getSupportFragmentManager(), mCards);
        mViewPager.setAdapter(mAdapter);
        Log.i(TAG, "onCreateView. Set adapter and pager");
        // Old Custom ViewPager way:
        //mAdapter = new CardPagerAdapter(getActivity().getApplicationContext(), mViewPager, mCards);


        return root;
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
