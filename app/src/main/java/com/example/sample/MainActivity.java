package com.example.sample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    Button LoginButton, RegisterButton;
    EditText Usuario, Password, Repassword;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoginButton = (Button) findViewById(R.id.buttonLogin);
        RegisterButton = (Button) findViewById(R.id.buttonRegister);

        Usuario = (EditText) findViewById(R.id.username);
        Password = (EditText) findViewById(R.id.password);

        DB = new DBHelper(this);

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = Usuario.getText().toString();
                String pass = Password.getText().toString();

                if(user.equals("")||pass.equals(""))
                    Toast.makeText(MainActivity.this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkusernamepassword = DB.checkusernamepassword(user,pass);
                    if(checkusernamepassword){
                        Toast.makeText(MainActivity.this, "Inicio de sesion exitoso!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(MainActivity.this, "La contraseña o el usuario no es correcto.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });


    }
}
