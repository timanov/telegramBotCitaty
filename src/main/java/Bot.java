import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiValidationException;

import java.awt.*;
import java.util.ArrayList;

public class Bot extends TelegramLongPollingBot {
    //создаем две константы, присваиваем им значения токена и имя бота соответственно
    //Вместо звездочек подставляйте свои данные
    final private String BOT_TOKEN = "5532935508:AAHYxI5beyRIaCPektSyQHReqpok6l-GncY";
    final private String BOT_NAME = "MishkaCitatyBot";
    Storage storage;


    Bot()
    {
        storage = new Storage();
    }

    @Override
    public String getBotUsername(){
        return BOT_NAME;
    }

    @Override
    public String getBotToken(){
        return BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update){
        if(update.hasMessage() && update.getMessage().hasText())
        {
            //Извлекаем из объекта сообщение пользователя
            Message inMess = update.getMessage();
            //Достаем из inMess id чата пользователя
            String chatId = inMess.getChatId().toString();
            //Получаем текст сообщения пользователя, отправляем в написанный нами обработчик
            String response = parseMessage(inMess.getText());
            //Создаем объект класса SendMessage = наш будущий ответ пользователяю
            SendMessage outMess = new SendMessage();

            //Добавляем в наше сообщение id чата а также наш ответ
            outMess.setChatId(chatId);
            outMess.setText(response);
            ReplyKeyboard replyKeyboardMarkup = new ReplyKeyboard() {
                @Override
                public void validate() throws TelegramApiValidationException {

                }
            };
            outMess.setReplyMarkup(replyKeyboardMarkup);


            //Отправка в чат
            try {
                execute(outMess);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public String parseMessage(String textMsg){
        String response;

        //Сравниваем текст пользователя с нашими комнадами, на основе этого формируем ответ
        if(textMsg.equals("/start"))
            response = "Приветствую, бот знает много цитат. Жми /get, чтобы получить случайную из них";
        else if(textMsg.equals("/get") || textMsg.equals("Просвяти"))
            response = storage.getRandQuote();
        else
            response = "Сообщение не распознано";



        return response;
    }


    void initKeyboard(){
        //Создаем объект будущей клавиатуры и выставляем нужные настройки;
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true); //подгоняем размер
        replyKeyboardMarkup.setOneTimeKeyboard(false); //скрываем после использования

        //Создаем список с рядами кнопок
        ArrayList<KeyboardRow> keyboardRows = new ArrayList<KeyboardRow>();
        //Создаем один ряд кнопок и добавляем его в список
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRows.add(keyboardRow);
        //Добавляем одну кнопку с текстом "Просвяти" в наш ряд
        keyboardRow.add(new KeyboardButton("Просвяти"));
        //Добавляем лист с одним рядом кнопок в главный объект
        replyKeyboardMarkup.setKeyboard(keyboardRows);
    }

}
