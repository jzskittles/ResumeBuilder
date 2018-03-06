package com.resumeapp.resumebuilder;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;

import java.util.HashMap;

public class resumeForm extends AppCompatActivity {
    SessionManagement session;

    private EditText name, email, phone, education, skills, experience, awards, leadership;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume_form);

        session = new SessionManagement(getApplicationContext());

        session.checkLogin();

        // get user data from session
        HashMap<String, String> user = session.getUserDetails();
        String sessname = user.get(SessionManagement.KEY_NAME);
        String sessemail = user.get(SessionManagement.KEY_EMAIL);
        int sessphoneNumber = Integer.parseInt(user.get(SessionManagement.KEY_PHONENUMBER));

        name = (EditText)findViewById(R.id.name);
        name.setText(sessname);
        email = (EditText)findViewById(R.id.email);
        email.setText(sessemail);
        phone = (EditText)findViewById(R.id.phone);
        phone.setText(""+sessphoneNumber);
        education = (EditText)findViewById(R.id.education);
        skills = (EditText)findViewById(R.id.skills);
        experience = (EditText)findViewById(R.id.experience);
        awards = (EditText)findViewById(R.id.awards);
        leadership = (EditText)findViewById(R.id.leadership);

        if(getIntent().hasExtra("education"))
            education.setText(getIntent().getStringExtra("education"));
        if(getIntent().hasExtra("skills"))
            skills.setText(getIntent().getStringExtra("skills"));
        if(getIntent().hasExtra("experience"))
            experience.setText(getIntent().getStringExtra("experience"));
        if(getIntent().hasExtra("awards"))
            awards.setText(getIntent().getStringExtra("awards"));
        if(getIntent().hasExtra("leadership"))
            leadership.setText(getIntent().getStringExtra("leadership"));
    }

}
