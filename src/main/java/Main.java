import LeroyMerlin.LeroyMerlinNewData;
import LeroyMerlin.LeroyMerlinParser;
import LeroyMerlin.LeroyMerlinSettings;
import Nanegative.NanegativeNewData;
import Nanegative.NanegativeParser;
import Nanegative.NanegativeSettings;
import Parsing.Completed;
import Parsing.ParserWorker;
import Yandex.YandexNewData;
import Yandex.YandexParser;
import Yandex.YandexSettings;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        int a;
        System.out.println("""
                Choose a option:
                 1. Feedbacks about building companies from Nanegative.ru
                 2. Information about concrete product from Leroymerlin.ru
                 3. Download images by a request from Yandex.ru/Images
                 0. Exit from the application""");
        do {
            a = scanner.nextInt();
            switch (a) {
                case 1 -> {
                    ParserWorker<ArrayList<String>> parser = new ParserWorker<>();
                    parser.setParser(new NanegativeParser());
                    parser.setParserSettings(new NanegativeSettings());
                    parser.onCompletedList.add(new Completed());
                    parser.onNewDataList.add(new NanegativeNewData());
                    parser.Start();
                    Thread.sleep(2000);
                    parser.Abort();
                }
                case 2 -> {
                    System.out.print("Enter a category name: ");
                    scanner.nextLine();
                    String category = scanner.nextLine();
                    ParserWorker<ArrayList<String>> parser = new ParserWorker<>();
                    parser.setParser(new LeroyMerlinParser());
                    parser.setParserSettings(new LeroyMerlinSettings(category));
                    parser.onCompletedList.add(new Completed());
                    parser.onNewDataList.add(new LeroyMerlinNewData());
                    parser.Start();
                    Thread.sleep(15000);
                    parser.Abort();
                }
                case 3 -> {
                    ParserWorker<ArrayList<String>> parser = new ParserWorker<>();
                    parser.setParser(new YandexParser());
                    System.out.print("Enter a request for images to search: ");
                    scanner.nextLine();
                    String request = scanner.nextLine();
                    parser.setParserSettings(new YandexSettings(request));
                    parser.onCompletedList.add(new Completed());
                    parser.onNewDataList.add(new YandexNewData());
                    parser.Start();
                    Thread.sleep(15000);
                    parser.Abort();
                }
                case 0 -> System.out.println("End");
                default -> System.out.println("Wrong input");
            }
        } while (a != 0);
    }
}
