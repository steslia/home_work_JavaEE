package parser;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JacksonParser {
    public List<CurrencyCursPrivateBank> parseJackson(String url) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        //Получаем массив элементов CurrencyCursPrivateBank, и передаем ссылку какой JSON считывать и откуда(в данном случ из сайта)
        CurrencyCursPrivateBank[] currencyCursPrivateBank = objectMapper.readValue(new URL(url), CurrencyCursPrivateBank[].class);

        //Использую стримы для сортировки и добавления в список
        List<CurrencyCursPrivateBank> resultList = Arrays.stream(currencyCursPrivateBank)
                .filter(s -> !s.getFrom().equals("BTC"))
                .collect(Collectors.toList());

        return resultList;

    }
}
