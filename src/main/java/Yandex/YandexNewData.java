package Yandex;

import Parsing.OnNewDataHandler;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;

public class YandexNewData implements OnNewDataHandler<ArrayList<String>> {
    private static final String IMAGE_DESTINATION_FOLDER = new File("").getAbsolutePath() + "\\yandexImages";

    @Override
    public void OnNewData(Object sender, ArrayList<String> args) {
        File newDirectory = new File(IMAGE_DESTINATION_FOLDER);
        newDirectory.mkdir();
        for (String link : args) {
            DownloadImage(link);
        }
    }

    private static void DownloadImage(String strImageURL){
        String strImageName = strImageURL.substring(strImageURL.lastIndexOf("/") + 1);
        try {
            URL urlImage = new URL(strImageURL);
            InputStream inputStream = urlImage.openStream();
            byte[] buffer = new byte[4096];
            int n = -1;
            OutputStream outputStream = new FileOutputStream(IMAGE_DESTINATION_FOLDER + "/" + strImageName);
            while ((n = inputStream.read(buffer)) != -1)
                outputStream.write(buffer, 0, n);
            outputStream.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}