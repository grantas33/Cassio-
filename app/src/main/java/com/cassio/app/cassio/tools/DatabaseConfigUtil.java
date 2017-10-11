package com.cassio.app.cassio.tools;

import com.cassio.app.cassio.models.Food;
import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

public class DatabaseConfigUtil extends OrmLiteConfigUtil {
    private static final Class<?>[] classes = new Class[]{
            Food.class
    };

    public static void main(String[] args) throws Exception {
        writeConfigFile("ormlite_config.txt");
    }
}
