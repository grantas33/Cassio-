package com.cassio.app.cassio.Tools;

import com.cassio.app.cassio.models.Food;
import com.cassio.app.cassio.models.Recipe;
import com.cassio.app.cassio.models.RecipeFood;
import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

public class DatabaseConfigUtil extends OrmLiteConfigUtil {
    private static final Class<?>[] classes = new Class[]{
            Food.class,
            Recipe.class,
            RecipeFood.class
    };

    public static void main(String[] args) throws Exception {
        writeConfigFile("ormlite_config.txt");
    }
}
