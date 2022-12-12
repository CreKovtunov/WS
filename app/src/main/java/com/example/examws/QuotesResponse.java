package com.example.examws;

import java.util.ArrayList;

public class QuotesResponse {
    private boolean success;
    private ArrayList<QuoteModel> data;

    private static class QuoteModel {
        private int id;
        private String title;
        private String image;
        private String description;

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getImage() {
            return image;
        }

        public String getDescription() {
            return description;
        }
    }

    public boolean getSuccess() {
        return success;
    }

    public ArrayList<QuoteModel> getData() {
        return data;
    }
}
