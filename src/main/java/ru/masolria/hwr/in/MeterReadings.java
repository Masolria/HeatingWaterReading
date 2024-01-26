package ru.masolria.hwr.in;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MeterReadings {
    private ArrayList<Calendar> monthsOfReading = new ArrayList<>();
    private ArrayList<Float> coldWater = new ArrayList<>();
    private ArrayList<Float> hotWater = new ArrayList<>();
    private
    ArrayList<Float> heating = new ArrayList<>();

    public boolean availableAddReading() {
        if(monthsOfReading.size() ==0){
            return true;
        }
        Calendar currentMonth = Calendar.getInstance();
        Calendar lastMonth = monthsOfReading.get(monthsOfReading.size() - 1);
        if (currentMonth.get(Calendar.MONTH) == lastMonth.get(Calendar.MONTH)) {
            return false;
        }
        return true;
    }


    public boolean addReading(float cold, float hot, float heat, Calendar time) {
        if (cold < 0 || hot < 0 || heat < 0) return false;
        if (monthsOfReading.size() != 0) {
            Calendar previousMon = monthsOfReading.get(monthsOfReading.size() - 1);
            float previousCold = coldWater.get(coldWater.size() - 1);
            float previousHot = hotWater.get(hotWater.size() - 1);
            float previousHeat = heating.get(heating.size() - 1);
            if (time.get(Calendar.MONTH) == previousMon.get(Calendar.MONTH)
                    || previousCold > cold || previousHot > hot
                    || previousHeat > heat) {
                return false;
            }
        }
        coldWater.add(cold);
        hotWater.add(hot);
        heating.add(heat);
        monthsOfReading.add(time);
        return true;
    }

    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("MM-yyyy");
        StringBuilder table_building = new StringBuilder();
        for (int i = 0; i < monthsOfReading.size(); i++) {
            Calendar month = monthsOfReading.get(i);
            String time = formatter.format(month.getTime());

            table_building.append(time).append(":\n\tхол. вода ").
                    append(coldWater.get(i)).append("\n\tгоряч. вода ").
                    append(hotWater.get(i)).
                    append("\n\tотопление ").append(heating.get(i)).append("\n");

        }
        return table_building.toString();
    }
}
