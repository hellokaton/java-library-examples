package io.github.biezhi.json.gson.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class RestaurantWithMenu {
    String name;

    List<RestaurantMenuItem> menu;
    //RestaurantMenuItem[] menu; // alternative, either one is fine
}