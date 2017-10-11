package com.cassio.app.cassio.tools;

//import com.example.grantas.cassio.Models.DayItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Lukas on 7/27/2017.
 */

public class DayItemsDataPump {
    public static HashMap<String, List<DayItem>> getDataFromList(List<DayItem> days) {
        HashMap<String, List<DayItem>> dataDetail = new HashMap<>();
        List<String> groups = getGroups(days);

        for (String group:
             groups) {
            List<DayItem> filteredList = new ArrayList<>();
            for (DayItem day:
                 days) {
                if (day.getYearMonth().equals(group)) {
                    filteredList.add(day);
                }
            }
            dataDetail.put(group, filteredList);
        }
        return  dataDetail;
    }

    private static List<String> getGroups(List<DayItem> days) {
        List<String> groups = new ArrayList<>();
        for (DayItem day:
             days) {
            String dayDate = day.getYearMonth();
            if (!groups.contains(dayDate)) {
                groups.add(dayDate);
            }
        }
        return groups;
    }
}
