package Nanegative;

import Parsing.OnNewDataHandler;

import java.util.ArrayList;

public class NanegativeNewData implements OnNewDataHandler<ArrayList<Company>> {
    @Override
    public void OnNewData(Object sender, ArrayList<Company> args) {
        for (Company company : args)
            System.out.println(company);
    }
}