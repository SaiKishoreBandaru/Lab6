package com.example.whattodo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import org.achartengine.util.MathHelper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;








import android.app.Activity;
import android.app.ActionBar;
import android.app.Application;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.os.Build;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			//getFragmentManager().beginTransaction()
					//.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		Button b3,b2;
		
		b2=(Button)findViewById(R.id.button4);
		b2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent intent = new Intent(MainActivity.this, orderscreen.class);
		        startActivity(intent);
				
				
			}
		});

		b3 = (Button)findViewById(R.id.button1);
		b3.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				/*WebView webview = new WebView(MainActivity.this);
				 setContentView(webview);
				 webview.loadUrl("http://134.193.136.147:8080/HbaseWS/jaxrs/generic/hbaseRetrieveAll/CRUD");
*/
				HttpClient dClientRequest = new DefaultHttpClient();
				
				InputStream resultStream = null;
				
				String s="";
				
				try {
					
					HttpGet hpURL = new HttpGet("http://192.168.199.128:8080/HMMWS/jaxrs/generic/HMMTrainingTest/-home-cloudera-Stomp.seq:-home-cloudera-Facepalm.seq/stomp:facepalm/-home-cloudera-Test.seq");
				   HttpResponse hrWebResponse = dClientRequest.execute(hpURL);
				    StatusLine statusLine = hrWebResponse.getStatusLine();
				    if(statusLine.getStatusCode() == HttpStatus.SC_OK)
				    {
				    HttpEntity heWebEntity = hrWebResponse.getEntity();
				    resultStream = heWebEntity.getContent();
				    BufferedReader bReader = new BufferedReader(new InputStreamReader(resultStream, "UTF-8"), 8);
				    StringBuilder sb = new StringBuilder();
				    
				    while((s = bReader.readLine()) != null)
				    {
				        sb.append(s + "\n");
				        //rEditText.setText(s);
				        
				        Log.i("conetxt",sb.toString());
				        
				    }
				    }
				    
				} catch (Exception e) { 
					e.printStackTrace();
				
				}
				
				

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

	 private long HOUR = 3600 * 1000;

	  private long DAY = HOUR * 24;

	  private int HOURS = 24;
	  
	  public Intent execute()
	  {
	  Context context=getApplicationContext();
	 String[] titles = new String[] { "Happy", "Normal", "Irritated" };
	    long now = Math.round(new Date().getTime() / DAY) * DAY;
	    //List<Date[]> x = new ArrayList<Date[]>();
	    List<Date[]> x=new ArrayList<Date[]>();
	    for (int k = 0; k < titles.length; k++) {
	      Date[] dates = new Date[HOURS];
	      for (int j = 0; j < HOURS; j++) {
	        dates[j] = new Date(now - (HOURS - j) * HOUR);
	      }
	      x.add(dates);
	    }
	    List<double[]> values = new ArrayList<double[]>();

	    values.add(new double[] { 25.2, 25.5, 27.7, 28.5, 29.4, 26.4, 27.3, 28.1, 26.6, 27.3, 28.2,
	        29.9, 29.7, 29.6, 29.9, 24.3, 20.6, 26.9, 24.2, 27.6, 27.9, 22.1, 25.9, 25.5 });
	    values.add(new double[] { 11.2, 11.5, 14.7, 14.5, 12.4, 13.4, 14.3, 14.1, 14.6, 14.3, 14.2,
	            14.9, 14.7, 14.6, 14.9, 14.3, 14.6, 13.9, 12.2, 12.6, 13.9, 14.1, 11.9, 12.5 });
	    values.add(new double[] { 5.9, 4.2, 6.9, 7.5, 3.1, 3.5, 6.6, MathHelper.NULL_VALUE,
	        MathHelper.NULL_VALUE, 1.8, 5.3, 1.4, 3.4, 4.9, 7.0, 6.4, 3.4, 2.0, 4.5, 3.9, 3.5,
	        MathHelper.NULL_VALUE, 3.9, 6.5, 4.3 });

	    int[] colors = new int[] { Color.GREEN, Color.BLUE, Color.RED };
	    PointStyle[] styles = new PointStyle[] { PointStyle.CIRCLE, PointStyle.DIAMOND, PointStyle.SQUARE };
	    XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
	    int length = renderer.getSeriesRendererCount();
	    for (int i = 0; i < length; i++) {
	      ((XYSeriesRenderer) renderer.getSeriesRendererAt(i)).setFillPoints(true);
	    }
	    setChartSettings(renderer, "Emotion Sensor", "Hour", "Emotions (0-10 Irritated; 11-20 Normal; 20-30 Happy) ",
	        x.get(0)[0].getTime(), x.get(0)[HOURS - 1].getTime(), -5, 30, Color.LTGRAY, Color.LTGRAY);
	    renderer.setXLabels(10);
	    renderer.setYLabels(10);
	    renderer.setShowGrid(true);
	    renderer.setXLabelsAlign(Align.CENTER);
	    renderer.setYLabelsAlign(Align.RIGHT);
	    Intent intent = ChartFactory.getTimeChartIntent(context, buildDateDataset(titles, x, values),
	        renderer, "h:mm a");
	    return intent;
	

}
	  
	  protected void setChartSettings(XYMultipleSeriesRenderer renderer, String title, String xTitle,
		      String yTitle, double xMin, double xMax, double yMin, double yMax, int axesColor,
		      int labelsColor) {
		    renderer.setChartTitle(title);
		    renderer.setXTitle(xTitle);
		    renderer.setYTitle(yTitle);
		    renderer.setXAxisMin(xMin);
		    renderer.setXAxisMax(xMax);
		    renderer.setYAxisMin(yMin);
		    renderer.setYAxisMax(yMax);
		    renderer.setAxesColor(axesColor);
		    renderer.setLabelsColor(labelsColor);
		  }
	  protected XYMultipleSeriesDataset buildDateDataset(String[] titles, List<Date[]> xValues,
		      List<double[]> yValues) {
		    XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		    int length = titles.length;
		    for (int i = 0; i < length; i++) {
		      TimeSeries series = new TimeSeries(titles[i]);
		      Date[] xV = xValues.get(i);
		      double[] yV = yValues.get(i);
		      int seriesLength = xV.length;
		      for (int k = 0; k < seriesLength; k++) {
		        series.add(xV[k], yV[k]);
		      }
		      dataset.addSeries(series);
		    }
		    return dataset;
		  }


public XYMultipleSeriesRenderer buildRenderer(int[] colors, PointStyle[] styles) 
{
   // TODO Auto-generated method stub
   XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
   setRenderer(renderer, colors, styles);
   return renderer;
}




private void setRenderer(XYMultipleSeriesRenderer renderer2, int[] colors,
       PointStyle[] styles) {
   // TODO Auto-generated method stub
   renderer2.setAxisTitleTextSize(16);
   renderer2.setChartTitleTextSize(20);
   renderer2.setLabelsTextSize(15);
   renderer2.setLegendTextSize(15);
   renderer2.setPointSize(5f);
   renderer2.setMargins(new int[] { 20, 30, 15, 20 });
   int length = colors.length;
   for (int i = 0; i < length; i++) {
       XYSeriesRenderer r = new XYSeriesRenderer();
       r.setColor(colors[i]);
       r.setPointStyle(styles[i]);
       renderer2.addSeriesRenderer(r);
   }
}
	
	
	
}
