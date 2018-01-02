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
class LoginClass {
    EditText login_email,login_pwd;

    public void showDialogContent(final Context context) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View promptView = layoutInflater.inflate(R.layout.login_dialog_box, null);

        login_email = (EditText) promptView.findViewById(R.id.login_email);
        login_pwd = (EditText) promptView.findViewById(R.id.login_pwd);

        //login dialog box
        final Dialog login_dialog = new Dialog(context);
        login_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);


        Button login_btn = (Button) promptView.findViewById(R.id.btn_login); //login button inside the login dialog box
        login_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToLogin(context);
            }
        });

        ImageButton close_btn = (ImageButton) promptView.findViewById(R.id.login_close_btn);
        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login_dialog.dismiss();
            }
        });
        login_dialog.setContentView(promptView);
        login_dialog.show();
    }

    private void goToLogin(Context context) {
        //on click of login , activity here
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

}