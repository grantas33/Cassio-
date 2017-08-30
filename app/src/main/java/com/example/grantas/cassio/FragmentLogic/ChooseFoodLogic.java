package com.example.grantas.cassio.FragmentLogic;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import com.example.grantas.cassio.ChooseFoodTabs;
import com.example.grantas.cassio.Food;
import com.example.grantas.cassio.LogItem;
import com.example.grantas.cassio.R;
import com.example.grantas.cassio.Tools.DatabaseHelper;
import com.github.johnpersano.supertoasts.library.utils.PaletteUtils;
import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperActivityToast;
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
    public SuperActivityToast mSuper = null;

    public Food cucumber = new Food("Trumpavaisis agurkas", 19, 158, 3.4, 0.9, 0.3);
    public Food pineapple = new Food("Ananaso riekė (1/10 ananaso)", 42, 84, 11, 0.5, 0.1);
    public Food orange = new Food("Apelsinas", 87, 184, 21.6, 1.7, 0.2);
    public Food watermelon = new Food("Arbūzo riekė (1/16 arbūzo)", 86, 286, 21.6, 1.7, 0.4);
    public Food avocado = new Food("Avokadas", 322, 201, 17, 4, 29);
    public Food banana = new Food("Bananas", 105, 118, 27, 1.3, 0.4);
    public Food strawberries = new Food("Braškės (10 vnt.)", 38, 120, 9, 0.9, 0.4);
    public Food kiwi = new Food("Kivis", 46, 76, 11.1, 0.9, 0.4);
    public Food pear = new Food("Kriaušė", 103, 178, 27.5, 0.7, 0.2);
    public Food tangerine = new Food("Mandarinas", 47, 88, 11.7, 0.7, 0.3);
    public Food mango = new Food("Mangas", 135, 207, 35.2, 1.1, 0.6);
    public Food melon = new Food("Meliono riekė (1/8 meliono)", 58, 160, 14.5, 0.9, 0.2);
    public Food carrot = new Food("Morka", 25, 61, 5.8, 0.6, 0.1);
    public Food apple = new Food("Obuolys", 95, 182, 25.1, 0.5, 0.3);
    public Food peach = new Food("Persikas", 59, 150, 14.8, 1.4, 0.4);
    public Food tomato = new Food("Pomidoras", 22, 123, 4.8, 1.1, 0.2);
    public Food plum = new Food("Slyva", 30, 66, 7.5, 0.5, 0.2);
    public Food grapes = new Food("Vynuogės (20 vnt.)", 68, 98, 17.8, 0.8, 0.2);


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

    public void DismissToast()
    {
        if(mSuper != null) mSuper.dismiss();
    }

    public void GenerateToast(Activity activity, boolean male, String name)
    {
        if(mSuper != null) mSuper.dismiss();
        mSuper = (SuperActivityToast) SuperActivityToast.create(activity, new Style(), Style.TYPE_STANDARD)
                .setFrame(Style.FRAME_KITKAT)
                .setText(male ? "Pridėtas " + name : "Pridėta " + name)
                .setColor(PaletteUtils.getSolidColor(PaletteUtils.MATERIAL_DEEP_ORANGE))
                .setAnimations(Style.ANIMATIONS_POP);
        mSuper.show();
    }


}
