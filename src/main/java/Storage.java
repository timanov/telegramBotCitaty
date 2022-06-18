import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    private ArrayList<String> quoteList;

    Storage() {
        quoteList = new ArrayList<>();
        parser("https://citatko.com/temy/lyubov/tsitaty-pro-lyubov-k-devushke?");
    }

    String getRandQuote() {
        //получаем случайное значение в интервале от 0 до самого большого индекса
        int randValue = (int) (Math.random() * quoteList.size());
        //Из коллекции получаем цитату со случайным инедксом и возвращаем ее
        return quoteList.get(randValue);
    }

    void parser(String strURL) {
        String className = "ads-color-box";
        Document doc = null;
        try {
            //Получаем документ нужной нам страницы
            doc = Jsoup.connect(strURL).maxBodySize(0).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Получаем группу объектов, обращась методом из Jsoup к определенному блоку
        Elements elQuote = doc.getElementsByClass(className);

        //Достаем текст из каждого объекта поочереди и добавляем в наше хранилище
        elQuote.forEach(el -> {
           quoteList.add(el.text());
        });
    }
}
