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
 * Created by bitjini on 27/12/17.
 */

class Register {

    public void Show_Register_Dialog(final Context context){
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.registration_details, null);

        //Register dialog box
        final Dialog register_dialog = new Dialog(context);
        register_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        final EditText register_username = (EditText)view.findViewById(R.id.User_name);
        final EditText register_email = (EditText) view.findViewById(R.id.register_email);
        final EditText register_password = (EditText) view.findViewById(R.id.register_password);

        Button register_btn = (Button) view.findViewById(R.id.register_button);
        register_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

//                String username = register_username.getText().toString();
//                String email = register_email.getText().toString();
//                String password = register_password.getText().toString();
//                if (email.matches("")) {
//                    Toast.makeText(context, "Please enter your Email Id", Toast.LENGTH_LONG).show();
//
//                }
//                if (password.matches("")) {
//                    Toast.makeText(context, "Please enter your Password", Toast.LENGTH_LONG).show();
//
//                } else {
//                    Toast.makeText(context, "Login successful", Toast.LENGTH_LONG).show();
//                }
            }
        });

        ImageButton register_closebtn = (ImageButton) view.findViewById(R.id.register_close_button);
        register_closebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register_dialog.dismiss();
            }
        });
        register_dialog.setContentView(view);
        register_dialog.show();

    }

}
