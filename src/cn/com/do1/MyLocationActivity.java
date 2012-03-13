package cn.com.do1;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
	        Location location = locationManager.getLastKnownLocation(provider); // ͨ��GPS��ȡλ��
	    	
	        
	        showLocation(location);
	    	
	    	
	    	/*locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,  
	      			 1000, 0, locationListener);*/
	    }
	    
	    private void showLocation(Location location){
	    	/*latitude = location.getLatitude();     //����  
	    	longitude = location.getLongitude(); //γ��  
	    	altitude =  location.getAltitude();     //���� 
	*/    	
	    	TextView latitudeTextView = (TextView)findViewById(R.id.latitudevalue);
	        TextView longitudeTextView = (TextView)findViewById(R.id.longitudevalue);
	        TextView altitudeTextView = (TextView)findViewById(R.id.altitudevalue);
	        
	        latitudeTextView.setText(String.valueOf(location.getLatitude()));	//����  
	        longitudeTextView.setText(String.valueOf(location.getLongitude()));	//γ��  
	        altitudeTextView.setText(String.valueOf(location.getAltitude()));	//����
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