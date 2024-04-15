package com.example.clothingca;

    public class StockItem {
        private String title;
        private String manufacturer;
        private double price;
        private String category;

        // Default constructor is needed for Firebase
        public StockItem() {
        }

        // Constructor with parameters
        public StockItem(String title, String manufacturer, double price, String category) {
            this.title = title;
            this.manufacturer = manufacturer;
            this.price = price;
            this.category = category;
        }

        // Getters
        public String getTitle() {
            return title;
        }

        public String getManufacturer() {
            return manufacturer;
        }

        public double getPrice() {
            return price;
        }

        public String getCategory() {
            return category;
        }

        // Setters, if needed for updating the properties
        public void setTitle(String title) {
            this.title = title;
        }

        public void setManufacturer(String manufacturer) {
            this.manufacturer = manufacturer;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public void setCategory(String category) {
            this.category = category;
        }
    }

