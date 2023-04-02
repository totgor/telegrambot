package com.telegrambot;

import java.util.ArrayList;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {

    final private String BOT_TOKEN = "6213555871:AAETosSNof2fXDPNmxCUnepb5sksEYd3lCs";
    final private String BOT_NAME = "QuoteBot";
    Storage storage;
    ReplyKeyboardMarkup replyKeyboardMarkup;

    Bot() {
        storage = new Storage();
        initKeyboard();
    }
    
    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }
    
    @Override
    public void onUpdateReceived(Update arg0) {
        try {
            Message inputMessage = arg0.getMessage();
            String chatId = inputMessage.getChatId().toString();
            String response = parseMessage(inputMessage.getText());
            SendMessage responseMessage = new SendMessage();
            responseMessage.setChatId(chatId);
            responseMessage.setText(response);
            responseMessage.setReplyMarkup(replyKeyboardMarkup);
            execute(responseMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    public String parseMessage(String inputMessageText) {
        String response;
				
        switch (inputMessageText) {
            case "/start": 
                response = "Жми /get, чтобы получить случайную цитату. Снизу есть кнопки.";
                break;            
            case "/get":
                response = storage.getRandQuote();
                break;
            case "Хочу цитату.":
                response = storage.getRandQuote();
                break;
            case "Помощь.":
                response = "Жми /get, чтобы получить случайную цитату.";
                break;
            default:
                response = "Сообщение не распознано.";
                break;
        }

        return response;
    }

    void initKeyboard() {
        replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRows.add(keyboardRow);
        keyboardRow.add(new KeyboardButton("Хочу цитату."));
        keyboardRow.add(new KeyboardButton("Помощь."));
        replyKeyboardMarkup.setKeyboard(keyboardRows);
        
    }
    
}
