package com.example.proyecto;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Variables contorno miembro
    EditText username, password, repassword;
    //Botones de iniciar sesion y registrarse
    Button signup, signin;
    //base de datos DB Helper
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Encuentra las variables
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);
        //Encuentra los botones
        signup = (Button) findViewById(R.id.btnsignup);
        signin = (Button) findViewById(R.id.btnsignin);
        DB = new DBHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                //Si el usuario, la contraseña y el password son iguales
                if(user.equals("")||pass.equals("")||repass.equals(""))
                    Toast.makeText(MainActivity.this, "Por favor, llena todos los campos", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(repass)){
                        Boolean checkuser = DB.checkusername(user);
                        if(checkuser==false){
                            Boolean insert = DB.insertData(user, pass);
                            if(insert==true){
                                Toast.makeText(MainActivity.this, "Registro exitoso!!!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(MainActivity.this, "El registro ha fallado", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(MainActivity.this, "El usuario ya existe, intenta de nuevo", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(MainActivity.this, "La contraseña es incorrecta", Toast.LENGTH_SHORT).show();
                    }
                } }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}