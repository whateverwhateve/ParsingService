package LeroyMerlin;

import Parsing.ParserSettings;

public class LeroyMerlinSettings extends ParserSettings {
    public LeroyMerlinSettings(String category) {
        BASE_URL = "https://leroymerlin.ru/catalogue/" + category;
    }
}
