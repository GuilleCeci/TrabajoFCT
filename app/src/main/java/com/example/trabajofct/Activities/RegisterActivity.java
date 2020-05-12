package com.example.trabajofct.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.example.trabajo1.R;
import com.example.trabajofct.Modules.Usuarios;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class RegisterActivity extends AppCompatActivity {
    private EditText EditTextNombre;
    private EditText EditTextEmail;
    private EditText EditTextApellidos;
    private EditText EditTextEdad;
    private EditText EditTextContraseña;

    private Button ButtonRegistrar;
    private Button ButtonSubirFoto;
    private Usuarios A;
    private boolean subirfoto= false;
    private String nombre;
    private String email;
    private String contraseña;
    private String apellidos;
    private int edad;
    private Uri uri;
    private String id;
    private final int GALLERY_INTENT = 1;

    private FirebaseAuth Autorizacion;
    private DatabaseReference BBDD;
    private StorageReference Storage;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resgistro);

        Autorizacion = FirebaseAuth.getInstance();
        BBDD = FirebaseDatabase.getInstance().getReference();
        Storage = FirebaseStorage.getInstance().getReference();


        ButtonSubirFoto= (Button) findViewById(R.id.btnSubirFoto);
        EditTextNombre = (EditText) findViewById(R.id.editTextNombre);
        EditTextEmail = (EditText) findViewById(R.id.editTextEmail);
        EditTextContraseña = (EditText) findViewById(R.id.editTextContraseña);
        EditTextApellidos = (EditText) findViewById(R.id.editTextApellidos);
        EditTextEdad = (EditText) findViewById(R.id.editTextEdad);
        ButtonRegistrar = (Button) findViewById(R.id.btnRegistrar);

        ButtonSubirFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, GALLERY_INTENT);
            }

        });

        ButtonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombre = EditTextNombre.getText().toString();
                email = EditTextEmail.getText().toString();
                contraseña = EditTextContraseña.getText().toString();
                apellidos =  EditTextApellidos.getText().toString();
                edad = Integer.parseInt(EditTextEdad.getText().toString());



                if (!nombre.isEmpty() && !email.isEmpty() && !contraseña.isEmpty() && !apellidos.isEmpty() && edad!=0) {
                    if (contraseña.length() >= 6) {
                        registerUser();
                    } else {
                        Toast.makeText(RegisterActivity.this, "La contraseña debe de tener al menos 6 caracteres", Toast.LENGTH_SHORT).show();

                    }
                } else {
                    Toast.makeText( RegisterActivity.this, "Debe completar todos los campos", Toast.LENGTH_SHORT).show();

                }
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_INTENT && resultCode == RESULT_OK){
            uri = data.getData();

            subirfoto= true;
        }
    }

    private void registerUser() {
        Autorizacion.createUserWithEmailAndPassword(email, contraseña).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    Usuarios A = new Usuarios(nombre, apellidos, email , edad, contraseña);
                    id = Autorizacion.getCurrentUser().getUid();

                    BBDD.child("Usuarios").child(id).setValue(A).addOnCompleteListener(new OnCompleteListener<Void>() {

                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {

                            if (task2.isSuccessful()) {
                                if(subirfoto == true){
                                    StorageReference filepath = Storage.child("foto").child(uri.getLastPathSegment());

                                    filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                            Toast.makeText(RegisterActivity.this, "Se ha subido la foto correctamente", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                                Toast.makeText(RegisterActivity.this, "Inicia sesion para continuar", Toast.LENGTH_SHORT).show();
                                finish();

                            } else {
                                Toast.makeText(RegisterActivity.this, "No se han podido crear los datos correctamente", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                } else {
                    Toast.makeText( RegisterActivity.this, "No se pudo registrar este usuario", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
