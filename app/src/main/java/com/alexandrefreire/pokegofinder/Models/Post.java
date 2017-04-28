package com.alexandrefreire.pokegofinder.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

/**
 * Created by Alexandre on 21/7/16.
 */
public class Post extends SugarRecord {
    @SerializedName("id")
    @Expose
    private Integer mId;

    @SerializedName("latitude")
    @Expose
    private Double mLatitude;

    @SerializedName("longitude")
    @Expose
    private Double mLongitude;

    @SerializedName("positive_count")
    @Expose
    private Integer mPositiveCount;

    @SerializedName("negative_count")
    @Expose
    private Integer mNegativeCount;

    @SerializedName("user")
    @Expose
    private User mUser;

    @SerializedName("pokemon")
    @Expose
    private Pokemon mPokemon;

    /**
     * No args constructor for use in serialization
     *
     */
    public Post() {
    }

    /**
     *
     * @param negativeCount
     * @param id
     * @param pokemon
     * @param longitude
     * @param latitude
     * @param user
     * @param positiveCount
     */
    public Post(Integer id, Double latitude, Double longitude, Integer positiveCount, Integer negativeCount, User user, Pokemon pokemon) {
        this.mId = id;
        this.mLatitude = latitude;
        this.mLongitude = longitude;
        this.mPositiveCount = positiveCount;
        this.mNegativeCount = negativeCount;
        this.mUser = user;
        this.mPokemon = pokemon;
    }

    /**
     *
     * @return
     * The id
     */
    public Integer getIdentifier() {
        return mId;
    }


    /**
     *
     * @return
     * The latitude
     */
    public Double getLatitude() {
        return mLatitude;
    }

    /**
     *
     * @param latitude
     * The latitude
     */
    public void setLatitude(Double latitude) {
        this.mLatitude = latitude;
    }

    /**
     *
     * @return
     * The longitude
     */
    public Double getLongitude() {
        return mLongitude;
    }

    /**
     *
     * @param longitude
     * The longitude
     */
    public void setLongitude(Double longitude) {
        this.mLongitude = longitude;
    }

    /**
     *
     * @return
     * The positiveCount
     */
    public Integer getPositiveCount() {
        return mPositiveCount;
    }


    /**
     *
     * @return
     * The negativeCount
     */
    public Integer getNegativeCount() {
        return mNegativeCount;
    }



    /**
     *
     * @return
     * The user
     */
    public User getUser() {
        return mUser;
    }

    /**
     *
     * @param user
     * The user
     */
    public void setUser(User user) {
        this.mUser = user;
    }

    /**
     *
     * @return
     * The pokemon
     */
    public Pokemon getPokemon() {
        return mPokemon;
    }

    /**
     *
     * @param pokemon
     * The pokemon
     */
    public void setPokemon(Pokemon pokemon) {
        this.mPokemon = pokemon;
    }
}
