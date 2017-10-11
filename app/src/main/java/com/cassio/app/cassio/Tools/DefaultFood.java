package com.cassio.app.cassio.tools;

import com.cassio.app.cassio.models.Food;
import com.cassio.app.cassio.R;

public class DefaultFood {

    private static Food[] FruitsAndVegetablesArray = new Food[]{
            new Food("Trumpavaisis agurkas", 19, 158, 3.4, 0.9, 0.3),
            new Food("Ananaso riekė (1/10 ananaso)", 42, 84, 11, 0.5, 0.1),
            new Food("Apelsinas", 87, 184, 21.6, 1.7, 0.2),
            new Food("Arbūzo riekė (1/16 arbūzo)", 86, 286, 21.6, 1.7, 0.4),
            new Food("Avokadas", 322, 201, 17, 4, 29),
            new Food("Bananas", 105, 118, 27, 1.3, 0.4),
            new Food("Braškės (10 vnt.)", 38, 120, 9, 0.9, 0.4),
            new Food("Kivis", 46, 76, 11.1, 0.9, 0.4),
            new Food("Kriaušė", 103, 178, 27.5, 0.7, 0.2),
            new Food("Mandarinas", 47, 88, 11.7, 0.7, 0.3),
            new Food("Mangas", 135, 207, 35.2, 1.1, 0.6),
            new Food("Meliono riekė (1/8 meliono)", 58, 160, 14.5, 0.9, 0.2),
            new Food("Morka", 25, 61, 5.8, 0.6, 0.1),
            new Food("Obuolys", 95, 182, 25.1, 0.5, 0.3),
            new Food("Persikas", 59, 150, 14.8, 1.4, 0.4),
            new Food("Pomidoras", 22, 123, 4.8, 1.1, 0.2),
            new Food("Slyva", 30, 66, 7.5, 0.5, 0.2),
            new Food("Vynuogės (20 vnt.)", 68, 98, 17.8, 0.8, 0.2)};

    private static Food[] MilkProductsArray = new Food[]{
            new Food("Grietinėlės šaukštas, 36% rieb", 41, 12, 0.3, 0.2, 4.3),
            new Food("Grietinės šaukštas", 23, 12, 0.3, 0.2, 2.4),
            new Food("Jogurto stiklinė, 3% rieb.", 189, 180, 27.2, 5.8, 6.3),
            new Food("Kiaušinis", 72, 50, 0.4, 6.3, 5.0),
            new Food("Pieno stiklinė, 3.25% rieb.", 108, 180, 9.5, 5.8, 5.9),
            new Food("Sūrelis, varškės", 134, 40, 8.3, 4.4, 9.2),
            new Food("Sūrio gabalėlis", 141, 38, 1.0, 8.8, 11.3),
            new Food("Varškės sūrio gabalėlis", 113, 38, 1.0, 9.0, 8.0),
            new Food("Sviestas, storai užtepta riekė", 72, 10, 0, 0.1, 8.1),
            new Food("Varškės pakelis, 9% rieb.", 279, 180, 6.3, 26.5, 16.2)

    };

    private static Food[] BakedProductsArray = new Food[]{
            new Food("Avinžirniai", 364, 100, 61, 19, 6),
            new Food("Avižinės kruopos", 389, 100, 66, 17, 6),
            new Food("Batono riekė", 66, 25, 13.3, 2, 3),
            new Food("Bolivinė balanda", 356, 100, 56, 13, 7),
            new Food("Duonos riekė", 65, 25, 12, 2.1, 0.8),
            new Food("Grikių kruopos", 355, 100, 69, 13, 3),
            new Food("Kukurūzų kruopos", 359, 100, 78, 7.9, 1),
            new Food("Makaronai", 345, 100, 68, 15, 1),
            new Food("Meduolis", 132, 40, 30, 2.4, 0.4),
            new Food("Miltai, kvietiniai", 364, 100, 76, 10, 1),
            new Food("Miltai, ruginiai", 346, 100, 77, 7, 1),
            new Food("Perlinės kruopos", 356, 100, 75, 10, 2),
            new Food("Ryžiai, ilgagrūdžiai", 365, 100, 80, 7, 1),

    };

    private static int[] FruitsAndVegetablesLogos = new int[]{
            R.drawable.cucumber, R.drawable.pineapple, R.drawable.orange, R.drawable.slicewatermelon,
            R.drawable.avocado, R.drawable.banana, R.drawable.stawberries, R.drawable.kiwi, R.drawable.pear, R.drawable.tangerine, R.drawable.mango,
            R.drawable.melon, R.drawable.carrot, R.drawable.apple, R.drawable.peach, R.drawable.tomato, R.drawable.plum, R.drawable.grapes

    };

    private static int[] MilkProductsLogos = new int[]{
            R.drawable.sweetcream, R.drawable.sour_cream, R.drawable.yogurt, R.drawable.egg, R.drawable.milk, R.drawable.cheesecurdsnack,
            R.drawable.cheese, R.drawable.curdcheese, R.drawable.butter, R.drawable.curd
    };

    private static int[] BakedProductsLogos = new int[]{

            R.drawable.chickpea, R.drawable.oatmeal, R.drawable.loafofbread, R.drawable.quinoa, R.drawable.darkbread,
            R.drawable.buckwheat, R.drawable.corn_grain, R.drawable.pastapng, R.drawable.gingerbread, R.drawable.wheat_flour,
            R.drawable.rye_flour, R.drawable.pearl_barley, R.drawable.rice
    };

    public final static Food[][] DataArrays = new Food[][]{
            FruitsAndVegetablesArray,
            MilkProductsArray,
            BakedProductsArray
    };

    public final static int[][] LogoArrays = new int[][]{
            FruitsAndVegetablesLogos,
            MilkProductsLogos,
            BakedProductsLogos
    };


}
