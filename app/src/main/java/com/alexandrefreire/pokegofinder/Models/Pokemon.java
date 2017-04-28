package com.alexandrefreire.pokegofinder.Models;

import android.content.Context;
import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

/**
 * Created by Alexandre on 21/7/16.
 */
public class Pokemon extends SugarRecord implements Parcelable {
    @SerializedName("name")
    @Expose
    private String mName;
    @SerializedName("pokedex_identifier")
    @Expose
    private Integer mPokedexIdentifier;

    /**
     * No args constructor for use in serialization
     *
     */
    public Pokemon() {
    }

    /**
     *
     * @param name
     * @param pokedexIdentifier
     */
    public Pokemon(String name, Integer pokedexIdentifier) {
        this.mName = name;
        this.mPokedexIdentifier = pokedexIdentifier;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return mName;
    }

    public int getPokemonIconId(Context context){
        Resources res = context.getResources();
        String mDrawableName = getName().toLowerCase();
        String input = mDrawableName.replace(" ", "").replace("'","").replace(".","");
        int resID = res.getIdentifier(input , "drawable", context.getPackageName());
        return resID;
    }
    /**
     *
     * @return
     * The pokedexIdentifier
     */
    public Integer getPokedexIdentifier() {
        return mPokedexIdentifier;
    }

    @Override
    public String toString(){
        return mName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeInt(mPokedexIdentifier);
    }
    public static final Parcelable.Creator<Pokemon> CREATOR = new Parcelable.Creator<Pokemon>() {

        @Override
        public Pokemon createFromParcel(Parcel source) {
            return new Pokemon(source);
        }

        @Override
        public Pokemon[] newArray(int size) {
            return new Pokemon[size];
        }
    };

    private Pokemon(Parcel source) {
        this.mName = source.readString();
        this.mPokedexIdentifier = source.readInt();
    }
}
