package LeroyMerlin;

import Parsing.OnNewDataHandler;

import java.util.ArrayList;

public class LeroyMerlinNewData implements OnNewDataHandler<ArrayList<Product>> {
    @Override
    public void OnNewData(Object sender, ArrayList<Product> args) {
        for (Product product : args)
            System.out.println(product);
    }
}