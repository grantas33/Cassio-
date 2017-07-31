package com.example.grantas.cassio.FragmentLogic;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import com.example.grantas.cassio.Food;
import com.example.grantas.cassio.LogItem;
import com.example.grantas.cassio.R;
import com.example.grantas.cassio.Tools.DatabaseHelper;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Grantas on 2017-07-10.
 */

public class ChooseFoodLogic {
    DatabaseHelper databaseHelper = null;
    Context context;

    public Food orange = new Food("Apelsinas", 87, 184, 21.6, 1.7, 0.2);
    public Food watermelon = new Food("Arbūzo riekė (1/16 arbūzo)", 86, 286, 21.6, 1.7, 0.4);
    public Food banana = new Food("Bananas", 105, 118, 27, 1.3, 0.4);
    public Food pear = new Food("Kriaušė", 103, 178, 27.5, 0.7, 0.2);
    public Food tangerine = new Food("Mandarinas", 47, 88, 11.7, 0.7, 0.3);
    public Food apple = new Food("Obuolys", 95, 182, 25.1, 0.5, 0.3);
    public Food peach = new Food("Persikas", 59, 150, 14.8, 1.4, 0.4);
    public Food tomato = new Food("Pomidoras", 22, 123, 4.8, 1.1, 0.2);


    public  ChooseFoodLogic(Context context) {
        this.context = context;
    }

    private DatabaseHelper getHelper() {
        if(databaseHelper == null)
        {
            databaseHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
        }
        return databaseHelper;
    }

    public List<Food> getSortedFoods() {
        List<Food> foods = new ArrayList<Food>();
        try
        {
            DatabaseHelper helper = getHelper();
            final Dao<Food, Integer> foodDao = helper.getFoodDao();
                    foods = new ArrayList<>(foodDao.queryForAll());
        }
        catch (SQLException e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG);
        }

//       for(Iterator<Food> it = foods.iterator(); it.hasNext();)
//       {
//           Food foo = it.next();
//           if(foo.Name.equals(orange.Name) ||
//                   foo.Name.equals(watermelon.Name) ||
//                   foo.Name.equals(banana.Name) ||
//                   foo.Name.equals(pear.Name) ||
//                   foo.Name.equals(tangerine.Name) ||
//                   foo.Name.equals(apple.Name)||
//                   foo.Name.equals(peach.Name) ||
//                   foo.Name.equals(tomato.Name))
//           {
//               it.remove();
//           }
//
//       }
        Collections.sort(foods);
        return foods;
    }

    public void AddLogItem(LogItem item){
        try {

            DatabaseHelper helper = getHelper();
            final Dao<LogItem, Integer> logDao = helper.getLogDao();

            //idedam i duombaze
            logDao.create(item);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void DeleteFoodItem(int id)
    {
        try
        {
            final Dao<Food, Integer> FoodDao = getHelper().getFoodDao();
            FoodDao.deleteById(id);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void UpdateGramsFoodItem(int grams, Food food)
    {
        try
        {
            final Dao<Food, Integer> FoodDao = getHelper().getFoodDao();
            Food newFood = new Food(food.Grams, food.foodId, food.Name, food.Calories, grams, food.Carbohydrates, food.Protein, food.Fat);
            FoodDao.update(newFood);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
