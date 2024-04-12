package com.example.clothingca;

import java.util.ArrayList;
import java.util.List;

public class BasketManager {
    private static List<ClothingItem> basket = new ArrayList<>();

    public static void addItem(ClothingItem item) {
        basket.add(item);
    }

    public static List<ClothingItem> getBasket() {
        return basket;
    }
}
