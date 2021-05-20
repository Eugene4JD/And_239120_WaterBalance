package com.example.and_2021_293120_waterbalanceapp.ui.notifications;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.charts.Cartesian3d;
import com.anychart.core.cartesian.series.Area3d;
import com.anychart.core.cartesian.series.Line;
import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.data.View;
import com.anychart.enums.Anchor;
import com.anychart.enums.HoverMode;
import com.anychart.enums.MarkerType;
import com.anychart.enums.Position;
import com.anychart.enums.TooltipPositionMode;
import com.anychart.graphics.vector.Stroke;
import com.anychart.graphics.vector.hatchfill.HatchFillType;
import com.example.and_2021_293120_waterbalanceapp.Data.Record;
import com.example.and_2021_293120_waterbalanceapp.Data.RecordLiveData;
import com.example.and_2021_293120_waterbalanceapp.Repository.RecordRepository;

import java.util.ArrayList;
import java.util.List;

public class RecordsGraphViewModel extends ViewModel {
    private final RecordRepository recordRepository;
    private ArrayList<Record> displayList;
    private final Cartesian3d area3d;
    private final Area3d series1;
    private final Area3d series2;

    public RecordsGraphViewModel() {
        recordRepository = RecordRepository.getInstance();
        displayList = new ArrayList<>();
        area3d = AnyChart.area3d();

        area3d.xAxis(0).labels().format("{%Value}");

        area3d.animation(true);

        area3d.yAxis(0).title("The Share Price");
        area3d.xAxis(0).title("Year/Month/Day");
        area3d.xAxis(0).labels().padding(5d, 5d, 0d, 5d);

        area3d.title("Your records overview");

        area3d.title().useHtml(true);
        area3d.title().padding(0d, 0d, 20d, 0d);

        List<DataEntry> seriesData = new ArrayList<>();
        seriesData.add(new CustomDataEntry("0", 0, 0));
        seriesData.add(new CustomDataEntry("0", 0, 0));
        seriesData.add(new CustomDataEntry("0", 0, 0));
        seriesData.add(new CustomDataEntry("0", 0, 0));


        Set set = Set.instantiate();
        set.data(seriesData);
        Mapping series1Data = set.mapAs("{ x: 'x', value: 'value' }");
        Mapping series2Data = set.mapAs("{ x: 'x', value: 'value2' }");

        series1 = area3d.area(series1Data);
        series1.name("Goal in millilitres");
        series1.hovered().markers(false);
        series1.hatchFill("diagonal", "#000", 0.6d, 10d);

        series2 = area3d.area(series2Data);
        series2.name("Your progress in millilitres");
        series2.hovered().markers(false);
        series2.hatchFill(HatchFillType.DIAGONAL_BRICK, "#000", 0.6d, 10d);

        area3d.tooltip()
                .position(Position.CENTER_TOP)
                .positionMode(TooltipPositionMode.POINT)
                .anchor(Anchor.LEFT_BOTTOM)
                .offsetX(5d)
                .offsetY(5d);

        area3d.interactivity().hoverMode(HoverMode.BY_X);
        area3d.zAspect("100%");

    }

    public void init() throws IllegalAccessException {
        recordRepository.init();
    }

    public LiveData<List<Record>> getRecords() {
        return recordRepository.getRecord();
    }

    public Cartesian3d getChart() {


        return area3d;

    }

    public void setDisplayList(List<Record> records) {
        this.displayList = (ArrayList<Record>) records;
    }

    public void removeRecords() {
        this.recordRepository.removeRecords();
    }

    public void updateGraph() {

        List<DataEntry> seriesData = new ArrayList<>();
        for (int i = 0; i < displayList.size(); i++) {
            seriesData.add(new CustomDataEntry(displayList.get(i).getTimeStamp(), displayList.get(i).getGoal(), displayList.get(i).getProgress()));
        }
        if (seriesData.size() == 0)
            seriesData.add(new CustomDataEntry("0",0,0));
        Set set = Set.instantiate();
        set.data(seriesData);
        Mapping series1Data = set.mapAs("{ x: 'x', value: 'value' }");
        Mapping series2Data = set.mapAs("{ x: 'x', value: 'value2' }");
        this.series1.data(series1Data);
        this.series2.data(series2Data);
    }

    private class CustomDataEntry extends ValueDataEntry {
        CustomDataEntry(String x, Number value, Number value2) {
            super(x, value);
            setValue("value2", value2);
        }
    }


}