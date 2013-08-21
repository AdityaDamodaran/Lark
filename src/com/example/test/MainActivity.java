package com.example.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.speech.RecognizerIntent;
import android.speech.tts.*;
import android.speech.tts.TextToSpeech.OnInitListener;
public class MainActivity extends Activity implements OnInitListener{
int mode=0;
public String name;
	public Button button1a;
	public EditText edt1,edt2;
    public TextToSpeech speechthing;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1a = (Button) findViewById(R.id.button1);
    	edt1= (EditText) findViewById(R.id.editText1);
    	edt2= (EditText) findViewById(R.id.editText2);
    	speechthing= new TextToSpeech(this, this);
        button1a.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
		speechthing.setLanguage(Locale.US);
	
		//speechthing.setSpeechRate((float) 2.0);
		ai();
		}
	});
    
    }
    
    public void ai(){
    	if(mode==1)
    	{
    		if(edt1.getText().toString().contains("yes")){
    		if(name==null)
    		{
    			speechthing.speak("I don't know yours either.", TextToSpeech.QUEUE_FLUSH, null);
    			edt2.setText(edt2.getText().toString()+"\nI don't know yours either.");	
    		}
    		else
    		{
    			speechthing.speak("Your name is " + name + ".", TextToSpeech.QUEUE_FLUSH, null);
    			edt2.setText(edt2.getText().toString()+"\nI don't know yours either.");	
        		
    		}
			mode=0;
    		}
    		else
    		{
    			speechthing.speak("Fine. Be rude.", TextToSpeech.QUEUE_FLUSH, null);
    			edt2.setText(edt2.getText().toString()+"\nFine. Be rude.");	
    			mode=0;
        			
    		}
    		return;
    	}
    	if(edt1.getText().toString().contains("hello"))
		{
			speechthing.speak("Hello. How are you?", TextToSpeech.QUEUE_FLUSH, null);
			edt2.setText(edt2.getText().toString()+"\nHello. How are you?");	
				
		}
    	else if(edt1.getText().toString().contains("my name is"))
		{
			int j=edt2.getText().toString().indexOf("is");
    		name=new String(edt2.getText().toString().substring(j+2).trim());
    		speechthing.speak("Ok, "+name+".", TextToSpeech.QUEUE_FLUSH, null);
			edt2.setText(edt2.getText().toString()+"\nOk, "+name+".");	
			mode=0;
		}
		else if(edt1.getText().toString().contains("what is your name"))
		{
			speechthing.speak("I don't have one. Do you want to know your name?", TextToSpeech.QUEUE_FLUSH, null);
			edt2.setText(edt2.getText().toString()+"\nI don't have one. Do you want to know your name?");	
			mode=1;
		}
		else if(edt1.getText().toString().contains("Google"))
		{
			speechthing.speak("Ok.", TextToSpeech.QUEUE_FLUSH, null);
			edt2.setText(edt2.getText().toString()+"\nOk.");	
			
			int i = edt1.getText().toString().indexOf("Google");
			
			Intent Browserintent=new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.google.com/search?q="+edt1.getText().toString().substring(i+6).trim()));
			startActivity(Browserintent);
		}
		else
		{
			speechthing.speak(edt1.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
			edt2.setText(edt2.getText().toString()+"\n"+edt1.getText().toString());	
				
		}
		    	
    	edt1.setText("");
    }

    //@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }


	@Override
	public void onInit(int arg0) {
		// TODO Auto-generated method stub
		
	}

    
}
