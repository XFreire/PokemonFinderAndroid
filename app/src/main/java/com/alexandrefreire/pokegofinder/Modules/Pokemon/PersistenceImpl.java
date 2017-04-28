package com.alexandrefreire.pokegofinder.Modules.Pokemon;

import com.orm.SugarRecord;

/**
 * Created by Alexandre on 23/7/16.
 */
public class PersistenceImpl implements Persistence {
    private SugarRecord mRecord;

    @Override
    public void save(SugarRecord record) {
        mRecord = record;
        mRecord.save();
    }
}
