package com.alexandrefreire.pokegofinder.Modules.SearchPokemon;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.alexandrefreire.pokegofinder.Models.Pokemon;
import com.alexandrefreire.pokegofinder.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexandre on 27/7/16.
 */
public class PokemonArrayAdapter extends ArrayAdapter<Pokemon> implements Filterable{
    private Context mContext;
    private int mViewResourceId;
    private List<Pokemon> mData;
    private List<Pokemon> mDataAll;
    private List<Pokemon> mSuggestions;

    public PokemonArrayAdapter(Context context, int resource, List<Pokemon> objects) {
        super(context, resource, objects);
        mContext = context;
        mViewResourceId = resource;
        mData = objects;
        mDataAll = new ArrayList<>(objects);
        mSuggestions = new ArrayList<>();
    }


    private class ViewHolder {
        ImageView imageView;
        TextView title;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        Pokemon pokemon = mData.get(position);

        LayoutInflater mInflater = (LayoutInflater) mContext
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.pokemon_listitem, null);
            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.imageView = (ImageView) convertView.findViewById(R.id.icon);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.title.setText(String.format("#%03d  %s", pokemon.getPokedexIdentifier(), pokemon.getName()));
        //holder.imageView.setImageResource(rowItem.getImageId());
        Resources res = mContext.getResources();
        String mDrawableName = pokemon.getName().toLowerCase();
        String input = mDrawableName.replace(" ", "").replace("'","").replace(".","");
        int resID = res.getIdentifier(input , "drawable", mContext.getPackageName());
        Drawable drawable = res.getDrawable(resID );
        holder.imageView.setImageDrawable(drawable );
        return convertView;
    }

    @Override
    public Filter getFilter() {
        return nameFilter;
    }

    Filter nameFilter = new Filter() {
        Filter.FilterResults filterResults = new FilterResults();
        ArrayList<Pokemon> tempList=new ArrayList<Pokemon>();
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if(constraint != null) {

                tempList.clear();
                for (Pokemon pokemon : mDataAll) {
                    if (pokemon.getName().toLowerCase().startsWith(constraint.toString().toLowerCase())) {
                        tempList.add(pokemon);
                    }
                }
                filterResults.values = tempList;
                filterResults.count = tempList.size();
            }
            return filterResults;
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            ArrayList<Pokemon> filteredList = (ArrayList<Pokemon>) results.values;
            if(results != null && results.count > 0) {
                clear();
                for (Pokemon c : filteredList) {
                    add(c);
                }
                notifyDataSetChanged();
            }
        }
    };
}
