package com.example.sahar.cppandey22;

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
 * Created by sahar on 22/12/17.
 */

class RegisterClass {
    EditText register_uname,register_email,register_pwd;

    public void showDialogContent(final Context context) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View promptView = layoutInflater.inflate(R.layout.register_dialog_box, null);

        //register dialog box
        final Dialog register_dialog = new Dialog(context);
        register_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

         register_uname=(EditText)promptView.findViewById(R.id.reg_uname);
         register_email = (EditText) promptView.findViewById(R.id.reg_email);
         register_pwd = (EditText) promptView.findViewById(R.id.reg_pwd);


        Button register_btn = (Button) promptView.findViewById(R.id.btn_register); //login button inside the login dialog box
        register_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToRegister(context);
            }
        });

        ImageButton close_btn = (ImageButton) promptView.findViewById(R.id.reg_close_btn);
        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register_dialog.dismiss();
            }
        });
        register_dialog.setContentView(promptView);
        register_dialog.show();
    }

    private void goToRegister(Context context) {
        //on click of register , activity here
        String username=register_uname.getText().toString();
        String email = register_email.getText().toString();
        String password = register_pwd.getText().toString();

        if(username.matches("")) {
            Toast.makeText(context, "Please enter your Username", Toast.LENGTH_SHORT).show();
        }
        if (email.matches("")) {
            Toast.makeText(context, "Please enter your Email Id", Toast.LENGTH_SHORT).show();

        }
        if (password.matches("")) {
            Toast.makeText(context, "Please enter your Passwrod", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(context, "Registration successful", Toast.LENGTH_LONG).show();
        }
    }
}