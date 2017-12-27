package com.example.bitjini.demoapp;


import android.app.Dialog;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;



/**
 * Created by bitjini on 26/12/17.
 */

public class Login {

    public void Show_Login_Dialog(final Context context){
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.login_details, null);

        //login dialog box
        final Dialog login_dialog = new Dialog(context);
        login_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);


        final EditText login_email = (EditText) view.findViewById(R.id.login_email);
        final EditText login_pwd = (EditText) view.findViewById(R.id.login_password);

        Button login_btn = (Button) view.findViewById(R.id.login_button);
        login_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                String email = login_email.getText().toString();
                String password = login_pwd.getText().toString();
                if (email.matches("")) {
                    Toast.makeText(context, "Please enter your Email Id", Toast.LENGTH_LONG).show();

                }
                if (password.matches("")) {
                    Toast.makeText(context, "Please enter your Password", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(context, "Login successful", Toast.LENGTH_LONG).show();
                }
            }
        });

        ImageButton close_btn = (ImageButton) view.findViewById(R.id.closeBtn);
        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login_dialog.dismiss();
            }
        });
        login_dialog.setContentView(view);
        login_dialog.show();

    }


}
