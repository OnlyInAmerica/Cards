package pro.dbro.cards.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by davidbrodsky on 1/19/14.
 */
public class Game implements Parcelable {

    private ArrayList<Card> mHand;
    private ArrayList<Card> mTable;
    private ArrayList<Card> mOpponentTable;

    public ArrayList<Card> getHand(){
        return mHand;
    }

    public ArrayList<Card> getTable(){
        return mTable;
    }

    public ArrayList<Card> getOpponentHand(){
        return mOpponentTable;
    }

    public void setHand(ArrayList<Card> hand){
        mHand = hand;
    }

    public void setTable(ArrayList<Card> table){
        mTable = table;
    }

    public void setOpponentHand(ArrayList<Card> opponentHand){
        mOpponentTable = opponentHand;
    }

    public Game(){

    }

    public Game(Parcel in){
        mHand = new ArrayList<Card>();
        in.readTypedList(mHand, Card.CREATOR);
        mTable = new ArrayList<Card>();
        in.readTypedList(mTable, Card.CREATOR);
        mOpponentTable = new ArrayList<Card>();
        in.readTypedList(mOpponentTable, Card.CREATOR);
    }

    public Game(ArrayList<Card> hand, ArrayList<Card> table, ArrayList<Card> opponentTable){
        mHand = hand;
        mTable = table;
        mOpponentTable = opponentTable;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(mHand);
        dest.writeTypedList(mTable);
        dest.writeTypedList(mOpponentTable);
    }

    public static final Parcelable.Creator<Game> CREATOR
            = new Parcelable.Creator<Game>() {
        public Game createFromParcel(Parcel in) {
            return new Game(in);
        }

        public Game[] newArray(int size) {
            return new Game[size];
        }
    };
}
