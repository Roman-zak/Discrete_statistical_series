
import java.math.*;
import java.util.List;
public class DiscretStatisticSeries {
	static double sum(List<Double> arr) {
		double sum=0;
		for(double n:arr) {
			sum+=n;
		}
		return sum;
	}
	static double averStat(List<Double> arr) {
		double aver=sum(arr)/arr.size();
		return aver;
	}
	static String mode(List<Double> arr) {
		return "Цей ряд не має моди";
	}
	static double median(List<Double> arr) {
		double median=0;
		arr.sort(null);
		if(arr.size()%2==0){
			 median=(arr.get(arr.size()/2)+arr.get(arr.size()/2-1))/2.0;
			 }
			 else{
			 median=arr.get(arr.size()/2);
			 }
		return median;
	}
	static double range(List<Double> arr) {
		double range=0;
		arr.sort(null);
		range = arr.get(arr.size()-1)- arr.get(0);
		return range;
	}
	static double dispersia(List<Double> arr) {
		double aver = averStat(arr);
		double dispersia=0;
		for(double n:arr) {
			dispersia+=Math.pow(n-aver, 2);
		}
		dispersia/=arr.size();
		return dispersia;
	}
	static double standardError(List<Double> arr) {
		double standardError=Math.sqrt(dispersia(arr));
		return standardError;
	}
	static double vyprDispersia(List<Double> arr) {
		double vyprDispersia = dispersia(arr)*arr.size()/(arr.size()-1);
		return vyprDispersia;
	}
	static double vyprStandardError(List<Double> arr) {
		return Math.sqrt(vyprDispersia(arr));
	}
	static double variation(List<Double> arr) {
		return standardError(arr)/averStat(arr);
	}
	static double startMoment(List<Double> arr, int order) {
		double startMoment=0;
		for(var i=0; i<arr.size(); i++){
			startMoment+=Math.pow(arr.get(i),order);
			 }
		startMoment/=arr.size();
		return startMoment;
	}
	static double centralMoment(List<Double> arr, int order) {
		double centralMoment=0;
		double aver = averStat(arr);
		for(var i=0; i<arr.size(); i++){
			centralMoment+=Math.pow(arr.get(i)-aver,order);
			 }
		centralMoment/=arr.size();
		return centralMoment;
	}
	static double assymetry(List<Double> arr){
		double assymetry = centralMoment(arr,3)/Math.pow(standardError(arr),3);
		if(assymetry<0) {
			System.out.println("Крива розподілу є зсунутою праворуч"); 
		}else {
			System.out.println("Крива розподілу є зсунутою ліворуч"); 
		}
		 return assymetry;
	}
	static double excess(List<Double> arr){
		double excess = centralMoment(arr,4)/Math.pow(standardError(arr),4)-3;

		 return excess;
	}
}
