package com.Cassio.app.cassio.Tools;

import com.Cassio.app.cassio.Food;
import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;


/**
 * Created by Lukas on 7/9/2017.
 */



public class DatabaseConfigUtil extends OrmLiteConfigUtil {
    private static final Class<?>[] classes = new Class[]{
            Food.class
    };

    public static void main(String[] args) throws Exception {
        writeConfigFile("ormlite_config.txt");
    }
}
