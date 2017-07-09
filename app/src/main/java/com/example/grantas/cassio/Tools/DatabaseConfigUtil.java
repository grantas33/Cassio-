package com.example.grantas.cassio.Tools;

import com.example.grantas.cassio.Food;
import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;


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
