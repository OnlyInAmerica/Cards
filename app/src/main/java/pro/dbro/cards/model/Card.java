package pro.dbro.cards.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Immutable Card
 * Created by davidbrodsky on 1/18/14.
 */
public class Card implements Parcelable {
    private String mName;
    private String mUrl;

    public Card(String name, String url) {
        mName = name;
        mUrl = url;
    }

    public Card(Parcel in) {
        String[] arrayIn = null;
        in.readStringArray(arrayIn);
        mName = arrayIn[0];
        mUrl = arrayIn[1];
    }

    public String getName() {
        return mName;
    }

    public String getUrl() {
        return mUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{mName, mUrl});

    }

    public static final Parcelable.Creator<Card> CREATOR
            = new Parcelable.Creator<Card>() {
        public Card createFromParcel(Parcel in) {
            return new Card(in);
        }

        public Card[] newArray(int size) {
            return new Card[size];
        }
    };

    public String toString(){
        return mName;
    }
}
