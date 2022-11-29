import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
 
public class Lab1 extends Application {
	static ArrayList<Double> arr =new ArrayList<>(Arrays.asList(0.29,0.35,0.34,0.42,0.47,0.60,0.91));
	static   HashMap <Double,Double> frequencyRow = new  HashMap<>();
	static   HashMap <Double,Double> relFrequencyRow = new  HashMap<>();
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);  
		
		for(Double n : arr) {
				if(frequencyRow.containsKey(n)) {
					frequencyRow.replace(n,frequencyRow.get(n)+1);
				}else {
					frequencyRow.put(n, 1.0);
				}
		}
		for(Double n : arr) {
			if(relFrequencyRow.containsKey(n)) {
				relFrequencyRow.replace(n,relFrequencyRow.get(n)+1);
			}else {
				relFrequencyRow.put(n, 1.0);
			}
		}
		for(int i=0; i<relFrequencyRow.size();i++) {
			relFrequencyRow.replace((Double) relFrequencyRow.keySet().toArray()[i], relFrequencyRow.get(relFrequencyRow.keySet().toArray()[i])/arr.size());
			
		}

		System.out.println("Середнє статистичне :"+DiscretStatisticSeries.averStat(arr));
		System.out.println("Мода :"+DiscretStatisticSeries.mode(arr));
		System.out.println("Медіана :"+DiscretStatisticSeries.median(arr));
		System.out.println("Розмах :"+DiscretStatisticSeries.range(arr));
		System.out.println("Дисперсія :"+DiscretStatisticSeries.dispersia(arr));
		System.out.println("Cереднє квадратичне відхилення :"+DiscretStatisticSeries.standardError(arr));
		System.out.println("Виправлена дисперсія :"+DiscretStatisticSeries.vyprDispersia(arr));
		System.out.println("Виправлене середнє квадратичне відхилення :"+DiscretStatisticSeries.vyprStandardError(arr));
		System.out.println("Варіація :"+DiscretStatisticSeries.variation(arr));
		System.out.println("Введіть порядок початкового та центрального моменту:");
		int p =sc.nextInt();
		System.out.println("Початковий момент "+p+" порядку:"+DiscretStatisticSeries.startMoment(arr,p));
		System.out.println("Центральний момент "+p+" порядку:"+DiscretStatisticSeries.centralMoment(arr,p));
		System.out.println("Асиметрія:"+DiscretStatisticSeries.assymetry(arr));
		System.out.println("Ексцес:"+DiscretStatisticSeries.excess(arr));

		launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
    	
    	arr.sort(null);
ArrayList<Double> set = new ArrayList<>();
		boolean setContains=false;
		for(double n : arr) {
			for(double unic: set) {
				if(unic==n)
					setContains=true;
			}
			if(!setContains) {
				set.add(n);
			}
		}
		
    	//polihon chastot----------------------------------------------------------------------
    	NumberAxis xAxis = new NumberAxis(0,arr.get(arr.size()-1),0.05);
    	xAxis.setLabel("Значення");
    	
    	NumberAxis yAxis = new NumberAxis(0,3,1);
    	yAxis.setLabel("Частота");
    	
    	LineChart lineChart = new LineChart(xAxis,yAxis);
    	XYChart.Series series = new XYChart.Series();
    	
    	series.setName("Полігон частот");
    	
    	for(int i=0; i<frequencyRow.size();i++) {
    		series.getData().add(new XYChart.Data(frequencyRow.keySet().toArray()[i],frequencyRow.get(frequencyRow.keySet().toArray()[i])));
    	}     
    	lineChart.getData().add(series);
    	//polihon chastot--------------------------------------------------------------------
    	//polihon vidnosnyh chastot----------------------------------------------------------
    	
    	NumberAxis xAxis1 = new NumberAxis(0,arr.get(arr.size()-1),0.05);
    	xAxis.setLabel("Значення");
    	
    	NumberAxis yAxis1 = new NumberAxis(0,1,0.01);
    	yAxis.setLabel("Відносна частота");
    	
    	LineChart lineChart1 = new LineChart(xAxis1,yAxis1);
    	XYChart.Series series1 = new XYChart.Series();
    	
    	series1.setName("Полігон відносних частот");
    	
    	for(int i=0; i<relFrequencyRow.size();i++) {
    		series1.getData().add(new XYChart.Data(relFrequencyRow.keySet().toArray()[i],relFrequencyRow.get(relFrequencyRow.keySet().toArray()[i])));
    	}     
    	lineChart1.getData().add(series1);
    	//polihon vidnosnyh chastot----------------------------------------------------------
    	//komuliatyvna kryva vydnosnykh nakopychenyh chastot---------------------------------

    	double [][] accumulatedRelFrequency = new double[frequencyRow.size()][2];
    	set.sort(null);
    	for(int i=0; i<set.size();i++) {
    		accumulatedRelFrequency[i][0]=set.get(i);
    		if(i!=0)
    			accumulatedRelFrequency[i][1]= accumulatedRelFrequency[i-1][1]+relFrequencyRow.get(set.get(i));
    		else
    			accumulatedRelFrequency[i][1]= relFrequencyRow.get(set.get(i));
    	}    	
    	
    	NumberAxis xAxis2 = new NumberAxis(0,arr.get(arr.size()-1),0.05);
    	xAxis.setLabel("Значення");
    	
    	NumberAxis yAxis2 = new NumberAxis(0,1,0.01);
    	yAxis.setLabel("Накопичена відносна частота");
    	
    	LineChart lineChart2 = new LineChart(xAxis2,yAxis2);
    	XYChart.Series series2 = new XYChart.Series();
    	
    	series2.setName("Комулятивна крива відносних частот");

    	for(int i=0; i<accumulatedRelFrequency.length;i++) {
    		series2.getData().add(new XYChart.Data(accumulatedRelFrequency[i][0],accumulatedRelFrequency[i][1]));
    	}     
    	lineChart2.getData().add(series2);
    	//komuliatyvna kryva vydnosnykh nakopychenyh chastot---------------------------------
    	//komuliatyvna kryva nakopychenyh chastot--------------------------------------------
    	
    	double [][] accumulatedFrequency = new double[frequencyRow.size()][2];
    	set.sort(null);
    	for(int i=0; i<set.size();i++) {
    		accumulatedFrequency[i][0]=set.get(i);
    		if(i!=0)
    			accumulatedFrequency[i][1]= accumulatedFrequency[i-1][1]+frequencyRow.get(set.get(i));
    		else
    			accumulatedFrequency[i][1]= frequencyRow.get(set.get(i));
    	}
    	
    	
    	NumberAxis xAxis3 = new NumberAxis(0,arr.get(arr.size()-1),0.05);
    	xAxis.setLabel("Значення");
    	
    	NumberAxis yAxis3 = new NumberAxis(0,10,0.01);
    	yAxis.setLabel("Накопичена частота");
    	
    	LineChart lineChart3 = new LineChart(xAxis3,yAxis3);
    	XYChart.Series series3 = new XYChart.Series();
    	
    	series3.setName("Комулятивна крива частот");

    	for(int i=0; i<accumulatedRelFrequency.length;i++) {
    		series3.getData().add(new XYChart.Data(accumulatedFrequency[i][0],accumulatedFrequency[i][1]));
    	}     
    	lineChart3.getData().add(series3);
    	
    	
    	
    	//komuliatyvna kryva nakopychenyh chastot--------------------------------------------
    	//empirychna funkciya rozpodilu------------------------------------------------------

    	double [][] empFunction = new double[(frequencyRow.size()*2)][2];
    	set.sort(null);
    	for(int i=0, j=0; j<set.size();j++) {
    		if(i==0) {
    			empFunction[i][0]=set.get(j);
    			empFunction[i][1]= accumulatedRelFrequency[j][1]; //relFrequencyRow.get(set.get(j));
    			i++;
    		}
    		else{
    			empFunction[i][0]=set.get(j)-0.001;
    			empFunction[i][1]=  accumulatedRelFrequency[j-1][1];
    			i++;
    			empFunction[i][0]=set.get(j)+0.001;
    			empFunction[i][1]= accumulatedRelFrequency[j][1];
    			i++;
    		}
    	}
		empFunction[set.size()*2-1][0]=  set.get(set.size()-1)+0.1;
		empFunction[set.size()*2-1][1]=  accumulatedRelFrequency[set.size()-1][1];

    	
    	
    	NumberAxis xAxis4 = new NumberAxis(empFunction[0][0],arr.get(arr.size()-1)+0.2,0.05);
    	xAxis.setLabel("Значення");
    	
    	NumberAxis yAxis4 = new NumberAxis(0,1.5,0.01);
    	yAxis.setLabel("Накопичена частота");
    	
    	LineChart lineChart4 = new LineChart(xAxis4,yAxis4);
    	XYChart.Series series4 = new XYChart.Series();
    	
    	series4.setName("Емпірична функція розподілу");

    	for(int i=0; i<empFunction.length;i++) {
    		series4.getData().add(new XYChart.Data(empFunction[i][0],empFunction[i][1]));
    	}     
    	lineChart4.getData().add(series4);
    	
    	//empirychna funkciya rozpodilu------------------------------------------------------
    	
    	Group root = new Group();
    	root.getChildren().add(lineChart);
    	root.getChildren().get(0).setLayoutX(0);
    	root.getChildren().get(0).setLayoutY(0);
    	
    	root.getChildren().add(lineChart1);
    	root.getChildren().get(1).setLayoutX(0);
    	root.getChildren().get(1).setLayoutY(400);
    	
    	root.getChildren().add(lineChart2);
    	root.getChildren().get(2).setLayoutX(500);
    	root.getChildren().get(2).setLayoutY(0);
    	
    	root.getChildren().add(lineChart3);
    	root.getChildren().get(3).setLayoutX(500);
    	root.getChildren().get(3).setLayoutY(400);
    	
    	root.getChildren().add(lineChart4);
    	root.getChildren().get(4).setLayoutX(1000);
    	root.getChildren().get(4).setLayoutY(0);
    	
    	Scene scene = new Scene(root,600,400);
        primaryStage.setTitle("Lab1!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}