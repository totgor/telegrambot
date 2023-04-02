package com.telegrambot;

import java.util.ArrayList;

public class Storage {
    private ArrayList<String> quoteList;

    Storage() {
        quoteList = new ArrayList<>();
        quoteList.add("Мечта — это не то, что не дает заснуть вечером. Это то, что заставляет проснуться рано утром!");
        quoteList.add("Если вы не произвели роботизацию какого-то процесса, то завтра вашей компании не будет существовать.");
        quoteList.add("IT-директор — уже главный человек в компании, он определяет повестку дня, дорожную карту!");
        quoteList.add("Потребитель готов прощать вам небольшие проблемы с продуктом, если вы даете ему идеальный сервис.");
    }

    String getRandQuote() {
        int rand = (int)(Math.random() * quoteList.size());
        return quoteList.get(rand);
    }
}
