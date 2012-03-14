package cn.com.do1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MyLocationActivity extends Activity {
    /** Called when the activity is first created. */
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.main);
	        //getGPS();
	        final Button passingButton = (Button) findViewById(R.id.passingButton);
	        passingButton.setOnClickListener(new Button.OnClickListener() {
	        	public void onClick(View v){
	        		getGPS();
	        	}
	        });
	    }
	    
	    public void getGPS(){
	    	// ��ȡλ�ù������
	        LocationManager locationManager;
	        String serviceName = Context.LOCATION_SERVICE;
	        locationManager = (LocationManager) this.getSystemService(serviceName);
	    	
	    	// ���ҵ�������Ϣ
	        Criteria criteria = new Criteria();
	        criteria.setAccuracy(Criteria.ACCURACY_FINE); // �߾���
	        criteria.setAltitudeRequired(false);
	        criteria.setBearingRequired(false);
	        criteria.setCostAllowed(true);
	        criteria.setPowerRequirement(Criteria.POWER_LOW); // �͹���
	    	
	        String provider = locationManager.getBestProvider(criteria, true); // ��ȡGPS��Ϣ
	        
	        Location location = null;
	       if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
	    	   location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER); // ͨ��GPS��ȡλ��
	    	   //locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
	    	   
	    	   if(location == null){
	    		   if(locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
	   	        		location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER); // ͨ��NETWORK��ȡλ��
	   	        		//locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
	   	        	}
	    	   }
	    	   showLocation(location);
	        }
	        else if(locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
	        	location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER); // ͨ��NETWORK��ȡλ��
	        	//locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
	        	showLocation(location);
	        }
	        else{
	        	 Toast toast = Toast.makeText(getApplicationContext(),"NO SERVICE ENABLED", Toast.LENGTH_LONG);
	        	 toast.setGravity(Gravity.CENTER, 0, 0);
	        	 toast.show();
	        	 Intent intent=new Intent(Settings.ACTION_SECURITY_SETTINGS);
                 startActivityForResult(intent,0);
	        }
	        
	    	/*locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,  
	      			 1000, 0, locationListener);*/
	    }
	    
	    private void showLocation(Location location){
	    	/*latitude = location.getLatitude();     //����  
	    	longitude = location.getLongitude(); //γ��  
	    	altitude =  location.getAltitude();     //����
	    	 */ 
	    	
	    	if(location == null){
	    		//Toast.makeText(MyLocationActivity.this, "LOCATION IS NULL EXCEPTION", 5000);
	    		Toast toast = Toast.makeText(getApplicationContext(),"LOCATION IS NULL EXCEPTION", Toast.LENGTH_LONG);
	        	 toast.setGravity(Gravity.CENTER, 0, 0);
	        	 toast.show();
	    	}
	    	else{
		    	TextView latitudeTextView = (TextView)findViewById(R.id.latitudevalue);
		        TextView longitudeTextView = (TextView)findViewById(R.id.longitudevalue);
		        TextView altitudeTextView = (TextView)findViewById(R.id.altitudevalue);
		        
		        latitudeTextView.setText(String.valueOf(location.getLatitude()));	//����  
		        longitudeTextView.setText(String.valueOf(location.getLongitude()));	//γ��  
		        altitudeTextView.setText(String.valueOf(location.getAltitude()));	//����
	    	}
	    }
	    
	/*    private final LocationListener locationListener = new LocationListener() {  
	        public void onLocationChanged(Location location) { 
	        	//������ı�ʱ�����˺��������Provider������ͬ�����꣬���Ͳ��ᱻ����  
	            // log it when the location changes  
	            if (location != null) {  
	            	latitude = location.getLatitude();     //����  
	            	longitude = location.getLongitude(); //γ��  
	            	altitude =  location.getAltitude();     //���� 
	            	
	            	
	            	TextView latitudeTextView = (TextView)findViewById(R.id.latitudevalue);
	                TextView longitudeTextView = (TextView)findViewById(R.id.longitudevalue);
	                TextView altitudeTextView = (TextView)findViewById(R.id.altitudevalue);
	                
	                latitudeTextView.setText(String.valueOf(latitude));
	                longitudeTextView.setText(String.valueOf(longitude));
	                altitudeTextView.setText(String.valueOf(altitude)); 
	            }  
	        }  
	      
	        public void onProviderDisabled(String provider) {  
	        // Provider��disableʱ�����˺���������GPS���ر�  
	        }  
	      
	        public void onProviderEnabled(String provider) {  
	        //  Provider��enableʱ�����˺���������GPS����  
	        }  
	      
	        public void onStatusChanged(String provider, int status, Bundle extras) {  
	        // Provider��ת̬�ڿ��á���ʱ�����ú��޷�������״ֱ̬���л�ʱ�����˺���  
	        }  
	    }; */
}