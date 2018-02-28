package com.resumeapp.resumebuilder;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

import java.util.ArrayList;

public class processImage extends AppCompatActivity {

    private static final int CAMERA_REQUEST = 1;
    ImageView imgView;
    private String education="";
    private String skills="";
    private String experience="";
    private ArrayList<String> awards=new ArrayList<String>();
    private String leadership="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process_image);

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(cameraIntent.resolveActivity(getPackageManager())!= null)
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);*/
                checkText();
            }
        });

        imgView = (ImageView)findViewById(R.id.newpic);


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == CAMERA_REQUEST && resultCode == RESULT_OK){
            Bitmap photo = (Bitmap)data.getExtras().get("data");
            imgView.setImageBitmap(photo);
            //checkText(photo);
        }
    }

    public void checkText(){//Bitmap icon){
        Bitmap icon = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.resume1);

        TextRecognizer textRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();

        if(!textRecognizer.isOperational()){
            Log.e("Error", "Detector dependencies are not yet available");
        }else{
            Frame imageFrame = new Frame.Builder().setBitmap(icon).build();

            ArrayList<String> imageTexts = new ArrayList<String>();

            SparseArray<TextBlock> textBlocks = textRecognizer.detect(imageFrame);

            StringBuilder stringBuilder = new StringBuilder();

            for(int i=0;i<textBlocks.size(); i++){
                TextBlock textBlock = textBlocks.valueAt(i);
                stringBuilder.append(textBlock.getValue());
                stringBuilder.append("\n");
                imageTexts.add(textBlock.getValue());
                //textss = textss + " " + textBlock.getValue();
                if(textBlock.getValue().toLowerCase().contains("education")){
                    education = textBlocks.valueAt(i+1).getValue();
                }
                if(textBlock.getValue().toLowerCase().contains("skills")){
                    skills = textBlocks.valueAt(i+1).getValue();
                }
                if(textBlock.getValue().toLowerCase().contains("experience")){
                    experience = textBlocks.valueAt(i+1).getValue();
                }
                if(textBlock.getValue().toLowerCase().contains("award")){
                    awards.add(textBlocks.valueAt(i).getValue());
                }
                if(textBlock.getValue().toLowerCase().contains("leadership")){
                    leadership = textBlocks.valueAt(i+1).getValue();
                }
            }
            TextView textView = (TextView)findViewById(R.id.contents);
            //textView.setText(stringBuilder.toString());
            /*Toast.makeText(getApplicationContext(),""+education, Toast.LENGTH_LONG).show();
            Toast.makeText(getApplicationContext(),""+experience, Toast.LENGTH_LONG).show();
            Toast.makeText(getApplicationContext(),""+skills, Toast.LENGTH_LONG).show();
            Toast.makeText(getApplicationContext(),""+awards.toString(), Toast.LENGTH_LONG).show();*/

            String award = "";

            for(int i=0;i<awards.size();i++){
                award = awards.get(i)+" ";
            }


            Intent intent = new Intent(getApplicationContext(), resumeForm.class);
            intent.putExtra("education", education);
            intent.putExtra("skills",skills);
            intent.putExtra("experience", experience);
            intent.putExtra("awards", award);
            intent.putExtra("leadership",leadership);
            startActivity(intent);




        }
    }
}
