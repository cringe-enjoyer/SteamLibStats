package com.example.cursach;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.lukaspradel.steamapi.data.json.playerachievements.Achievement;

import java.util.ArrayList;
import java.util.List;

public class StatsFragment extends Fragment {

    public StatsFragment() {

    }

    public static StatsFragment newInstance() {

        Bundle args = new Bundle();

        StatsFragment fragment = new StatsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_stats, container, false);
        PieChart chart = (PieChart) result.findViewById(R.id.pieChart);
        return result;
    }

    public PieData getAchievementsData(String steamID, int gameID) { //Надо сделать диаграмму где показаны сколько
        // получено достижений и сколько осталось
        List<Achievement> userGameAchievements = SteamAPIConnector.getUserGameAchievements(gameID, steamID);
        List<PieEntry> entries = new ArrayList<>();
        int receivedAch = 0;
        for (int i = 0; i < userGameAchievements.size(); i++) {
            if (userGameAchievements.get(i).getAchieved() == 1)
                receivedAch++;
        }
        float receivedPercent = receivedAch * userGameAchievements.size() / 100f;
        float remainingPercent = 100f - receivedPercent;
        entries.add(new PieEntry(receivedPercent, 0));
        entries.add(new PieEntry(remainingPercent, 1));
        PieDataSet dataSet = new PieDataSet(entries, "Прогресс достижений");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        return new PieData(dataSet);
    }
}