package com.alexandrefreire.pokegofinder.Modules.Pokemon;

import com.orm.SugarRecord;

/**
 * Created by Alexandre on 23/7/16.
 */
public interface Persistence {
    void save(SugarRecord record);
}
