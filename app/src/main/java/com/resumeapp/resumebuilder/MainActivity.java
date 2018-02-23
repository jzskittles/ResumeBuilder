package com.resumeapp.resumebuilder;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST = 1;
    ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        Frame imageFrame = new Frame.Builder().setBitmap(icon).build();

        ArrayList<String> imageTexts = new ArrayList<String>();

        String textss = "";

        SparseArray<TextBlock> textBlocks = textRecognizer.detect(imageFrame);

        for(int i=0;i<textBlocks.size(); i++){
            TextBlock textBlock = textBlocks.get(textBlocks.keyAt(i));
            imageTexts.add(textBlock.getValue());
            textss = textss + " " + textBlock.getValue();
        }
        TextView textView = (TextView)findViewById(R.id.contents);
        textView.setText(textss);
    }
}
