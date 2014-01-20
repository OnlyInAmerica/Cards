package pro.dbro.cards;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by davidbrodsky on 6/20/13.
 */
public class DrawerItemAdapter extends ArrayAdapter {
    Context mContext;
    String[] mItems;
    static int layoutId = R.layout.drawer_item;


    public DrawerItemAdapter(Context context, String[] items) {
        super(context, layoutId, items);
        mItems = items;
        mContext = context;
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent){
        View view = convertView;
        ViewCache viewCache;
        if(view == null)
        {
            LayoutInflater inflater = ((Activity)mContext).getLayoutInflater();
            view = inflater.inflate(layoutId, parent, false);

            viewCache = new ViewCache();
            viewCache.icon = (ImageView)view.findViewById(R.id.icon);
            viewCache.title = (TextView)view.findViewById(R.id.title);

            view.setTag(R.id.list_item_cache, viewCache);
        }
        else
        {
            viewCache = (ViewCache) view.getTag(R.id.list_item_cache);
        }

        viewCache.icon.setVisibility(View.VISIBLE);
        viewCache.title.setText(mItems[position]);


        view.setTag(R.id.list_item_model, mItems[position]);
        return view;
    }

    static class ViewCache {
        ImageView icon;
        TextView title;
    }
}