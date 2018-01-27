package io.github.biezhi.json.gson;

import com.google.gson.Gson;
import io.github.biezhi.json.gson.model.RestaurantMenuItem;
import io.github.biezhi.json.gson.model.RestaurantWithMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * @author biezhi
 * @date 2018/1/26
 */
public class ArraysAndListsExample {

    public static void main(String[] args) {
        List<RestaurantMenuItem> menu = new ArrayList<>();
        menu.add(new RestaurantMenuItem("Spaghetti", 7.99f));
        menu.add(new RestaurantMenuItem("Steak", 12.99f));
        menu.add(new RestaurantMenuItem("Salad", 5.99f));

        RestaurantWithMenu restaurant = new RestaurantWithMenu("Future Studio Steak House", menu);

        Gson   gson           = new Gson();
        String restaurantJson = gson.toJson(restaurant);
    }
}
