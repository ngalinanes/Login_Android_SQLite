package com.example.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    Button RegisterButton;
    EditText Usuario, Password, Repassword, Email;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        RegisterButton = (Button) findViewById(R.id.buttonRegister1);

        Usuario = (EditText) findViewById(R.id.username1);
        Password = (EditText) findViewById(R.id.password1);
        Repassword = (EditText) findViewById(R.id.repassword1);
        Email = (EditText) findViewById(R.id.email);

        DB = new DBHelper(this);

        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = Usuario.getText().toString();
                String password = Password.getText().toString();
                String repass = Repassword.getText().toString();
                String correo = Email.getText().toString();

                if(user.equals("")|| password.equals("")||repass.equals("")||correo.equals(""))
                    Toast.makeText(RegisterActivity.this,"Por favor, completa todos los campos.", Toast.LENGTH_SHORT).show();
                else {
                    if (password.length() < 6 || repass.length() < 6)
                        Toast.makeText(RegisterActivity.this, "La contraseña debe tener mas de 6 caracteres.", Toast.LENGTH_SHORT).show();
                    else {
                        if (password.equals(repass)) {
                            Boolean checkuser = DB.checkusername(user);
                            if (!checkuser) {
                                Boolean insert = DB.insertData(user, password, correo);
                                if (insert) {
                                    Toast.makeText(RegisterActivity.this, "Registro exitoso! Ahora inicia sesion.", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(RegisterActivity.this, "Ocurrio un error durante el registro, intenta nuevamente.", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(RegisterActivity.this, "El usuario ya existe.", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(RegisterActivity.this, "Las contraseñas no coinciden.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }


}