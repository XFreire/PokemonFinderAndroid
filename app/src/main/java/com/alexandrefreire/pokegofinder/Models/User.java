package com.alexandrefreire.pokegofinder.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

import java.util.List;

/**
 * Created by Alexandre on 21/7/16.
 */
public class User extends SugarRecord {
    @SerializedName("id")
    @Expose
    private Integer mIdentifier;

    @SerializedName("name")
    @Expose
    private String mName;

    @SerializedName("email")
    @Expose
    private String mEmail;

    @SerializedName("searches_count")
    @Expose
    private Integer mSearchesCount;

    @SerializedName("negative_votes_count")
    @Expose
    private Integer mNegativeVotesCount;

    @SerializedName("positive_votes_count")
    @Expose
    private Integer mPositiveVotesCount;

    @SerializedName("posts_count")
    @Expose
    private Integer mPostsCount;

    @SerializedName("api_key")
    @Expose
    private String mApiKey;

    public User(){

    }

    /**
     *
     * @return
     * The id
     */
    public Integer getIdentifier() {
        return mIdentifier;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return mName;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.mName = name;
    }

    /**
     *
     * @return
     * The email
     */
    public String getEmail() {
        return mEmail;
    }

    /**
     *
     * @param email
     * The email
     */
    public void setEmail(String email) {
        this.mEmail = email;
    }

    /**
     *
     * @return
     * The searchesCount
     */
    public Integer getSearchesCount() {
        return mSearchesCount;
    }


    /**
     *
     * @return
     * The negativeVotesCount
     */
    public Integer getNegativeVotesCount() {
        return mNegativeVotesCount;
    }

    /**
     *
     * @return
     * The positiveVotesCount
     */
    public Integer getPositiveVotesCount() {
        return mPositiveVotesCount;
    }



    /**
     *
     * @return
     * The postsCount
     */
    public Integer getPostsCount() {
        return mPostsCount;
    }


    public static User getCurrentUser() {
        List<User> list = User.listAll(User.class);
        User user = null;
        if (list.size() != 0){
            user = list.get(list.size()-1);
        }
        return user;
    }

    public String getApiKey() {
        return mApiKey;
    }
}
