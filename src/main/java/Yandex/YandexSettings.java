package Yandex;

import Parsing.ParserSettings;

public class YandexSettings extends ParserSettings {
    public YandexSettings(String requset) {
        BASE_URL = "https://yandex.ru/images/search?text=" + requset;
    }
}
