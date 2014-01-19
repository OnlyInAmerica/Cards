package pro.dbro.cards;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import pro.dbro.cards.model.Card;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link pro.dbro.cards.CardFragment.CardFragmentListener} interface
 * to handle interaction events.
 * Use the {@link CardFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class CardFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_CARD = "card";

    private Card mCard;

    private CardFragmentListener mListener;

    public static CardFragment newInstance(Card card) {
        CardFragment fragment = new CardFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_CARD, card);
        fragment.setArguments(args);
        return fragment;
    }
    public CardFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCard = getArguments().getParcelable(ARG_CARD);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_card, container, false);
        ((TextView) root.findViewById(R.id.title)).setText(mCard.getName());
        ((NetworkImageView) root.findViewById(R.id.image)).setImageUrl(mCard.getUrl(), Network.getImageLoader(getActivity().getApplicationContext()));
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
            mListener = (CardFragmentListener) activity;
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
    public interface CardFragmentListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
