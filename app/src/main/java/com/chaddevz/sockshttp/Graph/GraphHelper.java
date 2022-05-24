package com.chaddevz.sockshttp.Graph;

import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.os.*;
import android.util.*;
import android.os.Handler;
import com.github.mikephil.charting.charts.*;
import com.github.mikephil.charting.components.*;
import com.github.mikephil.charting.components.XAxis.*;
import com.github.mikephil.charting.data.*;
import com.github.mikephil.charting.formatter.*;
import com.github.mikephil.charting.interfaces.datasets.*;
import java.util.*;
import com.chadx.sockshttp.R;


public class GraphHelper {
    private static final int TIME_PERIOD_SECCONDS = 0;
	public static String GrapHhelper = new String(new byte[]{72,97,114,108,105,101,83,32,98,117,105,108,100,45,49,54,});
    private static Handler mHandler;
    private static GraphHelper m_GraphHelper;
    private String TAG = "GraphHelper";
    private int mColor;
    private Context mContext;
    private LineChart mLineChart;
    private boolean mLogScale = false;
    public Runnable triggerRefresh = new Runnable() {

        @Override
        public void run() {
            refreshGraph2();
            GraphHelper.mHandler.postDelayed(this, (long) 3000);
        }
    };

    class ValueFormat extends ValueFormatter {
        @Override
        public String getFormattedValue(float f) {
            if (mLogScale && f < 2.1f) {
                //return "< 100\u2009bit/s";
            }
            if (mLogScale) {
                f = ((float) Math.pow((double) 10, (double) f)) / ((float) 8);
            }
            return humanReadableByteCount((long) f, true);
        }
    }

    public static synchronized GraphHelper getHelper() {
        GraphHelper graphHelper;
        synchronized (GraphHelper.class) {
            if (m_GraphHelper == null) {
                m_GraphHelper = new GraphHelper();
            }
            if (mHandler == null) {
                mHandler = new Handler();
            }
            graphHelper = m_GraphHelper;
        }
        return graphHelper;
    }

    public GraphHelper color(int i) {
        this.mColor = i;
        return m_GraphHelper;
    }

    public GraphHelper chart(LineChart lineChart) {
        this.mLineChart = lineChart;
        return m_GraphHelper;
    }

    public GraphHelper with(Context context) {
        this.mContext = context;
        return m_GraphHelper;
    }

    public void refreshGraph1() {
        try {
            this.mLineChart.getDescription().setEnabled(false);
            this.mLineChart.setTouchEnabled(false);
            this.mLineChart.setDrawGridBackground(false);
            this.mLineChart.getLegend().setEnabled(false);
            XAxis xAxis = this.mLineChart.getXAxis();
            xAxis.setPosition(XAxisPosition.BOTTOM);
			xAxis.setTextColor(Color.WHITE);
			xAxis.setValueFormatter(new ValueFormat());
			xAxis.enableGridDashedLine(5f, 5f, 5f);
            YAxis axisLeft = this.mLineChart.getAxisLeft();
			axisLeft.enableGridDashedLine(5f, 5f, 5f);
            axisLeft.setTextColor(Color.WHITE);
            axisLeft.setValueFormatter(new ValueFormat());
			this.mLineChart.getAxisRight().setTextColor(Color.WHITE);
            LineData dataSet = getDataSet(0);
			axisLeft.setAxisMinimum(0.0f);
			axisLeft.resetAxisMaximum();
			axisLeft.setTextSize(7);
			xAxis.setLabelCount(5);
			xAxis.setTextSize(7);
			this.mLineChart.getAxisRight().setTextSize(7);
            if (((ILineDataSet) dataSet.getDataSetByIndex(0)).getEntryCount() < 1) {
                this.mLineChart.setData((LineData) null);
            } else {
                this.mLineChart.setData(dataSet);
            }
            this.mLineChart.invalidate();
        } catch (Exception e) {
            Log.e(this.TAG, e.toString());
        }
    }
	public void refreshGraph2() {
        try {
            this.mLineChart.getDescription().setEnabled(false);
            this.mLineChart.setTouchEnabled(false);
            this.mLineChart.setDrawGridBackground(false);
            this.mLineChart.getLegend().setEnabled(false);
            XAxis xAxis = this.mLineChart.getXAxis();
            xAxis.setPosition(XAxisPosition.BOTTOM);
            xAxis.setDrawGridLines(false);
            xAxis.setDrawLabels(false);
            xAxis.setDrawAxisLine(false);
            YAxis axisLeft = this.mLineChart.getAxisLeft();
            axisLeft.setLabelCount(5, false);
            axisLeft.setDrawAxisLine(false);
            axisLeft.setTextColor(Color.WHITE);
            axisLeft.setValueFormatter(new ValueFormat());
            this.mLineChart.getAxisRight().setEnabled(false);
            LineData dataSet = getDataSet(0);
            float yMax = dataSet.getYMax();
            if (this.mLogScale) {
                axisLeft.setAxisMinimum(2.0f);
                axisLeft.setAxisMaximum((float) Math.ceil((double) yMax));
                axisLeft.setLabelCount((int) Math.ceil((double) (yMax - 2.0f)));
            } else {
                axisLeft.setAxisMinimum(0.0f);
                axisLeft.resetAxisMaximum();
                axisLeft.setLabelCount(6);
            }
            if (((ILineDataSet) dataSet.getDataSetByIndex(0)).getEntryCount() < 1) {
                this.mLineChart.setData((LineData) null);
            } else {
                this.mLineChart.setData(dataSet);
            }
            this.mLineChart.invalidate();
        } catch (Exception e) {
            Log.e(this.TAG, e.toString());
        }
    }
    private LineData getDataSet(int io) {

		List<Long> dList = StoredData.downloadList;
		ArrayList<Entry> e1 = new ArrayList<Entry>();
		float t1;
		//OpenVPNClient.credit_dev.setText(GrapHhelper);
		for (int i = 0; i < dList.size(); i++) {

			t1 = (float) dList.get(i) / 1024;  //convert o Kilobyte
			//t2 = (float) uList.get(i) / 1024;
			e1.add(new Entry(i, t1));
		}
        List arrayList = new ArrayList();
		//arrayList.clear();
        LineDataSet lineDataSet = new LineDataSet(e1, this.mContext.getString(R.string.app_name));
        setLineDataAttributes(lineDataSet, this.mContext.getResources().getColor(R.color.white));
		lineDataSet.notifyDataSetChanged();
        arrayList.add(lineDataSet);
        return new LineData(arrayList);
    }

    private void setLineDataAttributes(LineDataSet lineDataSet, int i) {
		lineDataSet.setDrawCircles(false);
        lineDataSet.setCircleColor(i);
        lineDataSet.setColor(i);
        lineDataSet.setMode(LineDataSet.Mode.LINEAR);
		lineDataSet.setCubicIntensity(0.1f);
		lineDataSet.setDrawFilled(true);
		lineDataSet.setDrawValues(true);
		lineDataSet.setFillColor(this.mContext.getResources().getColor(R.color.white));
		lineDataSet.setFillAlpha(50);
		lineDataSet.setDrawHorizontalHighlightIndicator(true);


		/*lineDataSet.setLineWidth((float) 1);
		 lineDataSet.setCircleRadius((float) 1);
		 lineDataSet.setDrawCircles(false);
		 lineDataSet.setCircleColor(mContext.getResources().getColor(R.color.graphColor));
		 lineDataSet.setColor(this.mContext.getResources().getColor(R.color.graphColor));
		 lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
		 lineDataSet.setCubicIntensity(0.2f);
		 lineDataSet.setDrawFilled(false);
		 lineDataSet.setDrawValues(false);
		 lineDataSet.setFillColor(Color.TRANSPARENT);
		 //lineDataSet.setFillAlpha(100);
		 lineDataSet.setDrawHorizontalHighlightIndicator(false);*/

    }


	public void start() {
		GraphHelper.mHandler.removeCallbacks(triggerRefresh);
		refreshGraph2();
		GraphHelper.mHandler.postDelayed( triggerRefresh, (long) 1000);
    }

    public void stop() {
		mHandler.removeCallbacks(this.triggerRefresh);
		this.mLineChart.clear();
        this.mLineChart.invalidate();
    }
	private String humanReadableByteCount(long bytes, boolean mbit) {
        if (mbit) {
            bytes = bytes * 8;
        }
        int unit = mbit ? 1000 : 1024;
        if (bytes < unit) {
            return bytes + (mbit ? " bit" : " B");
        }
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = (mbit ? "kMGTPE" : "KMGTPE").charAt(exp - 1) + (mbit ? "" : "");
        if (mbit) {
            return String.format(Locale.getDefault(), "%.1f %sbit", bytes / Math.pow(unit, exp), pre);
        } else {
            return String.format(Locale.getDefault(), "%.1f %sB", bytes / Math.pow(unit, exp), pre);
        }
    }
}

