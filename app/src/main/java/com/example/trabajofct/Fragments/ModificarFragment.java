package com.example.trabajofct.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.trabajo1.R;
import com.example.trabajofct.Activities.SecondActivity;
import com.example.trabajofct.Modules.Usuarios;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * A simple {@link Fragment} subclass.
 */
public class ModificarFragment extends Fragment {

    private EditText editTextNombreModificar;
    private EditText editTextEmailModificar;
    private EditText editTextApellidosModificar;
    private EditText editTextEdadModificar;
    private EditText editTextContraseñaModificar;

    private Button buttonModificar;
    private Button buttonSubirFoto;
    private boolean subirfoto= false;
    private Uri uri;
    private final int GALLERY_INTENT = 1;

    private FirebaseAuth autorizacion;
    private DatabaseReference BBDD;
    private StorageReference storage;
    private DatabaseReference firebase = FirebaseDatabase.getInstance().getReference();
    private String id;

    public ModificarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_modificar, container, false);

        autorizacion = FirebaseAuth.getInstance();
        BBDD = FirebaseDatabase.getInstance().getReference();
        storage = FirebaseStorage.getInstance().getReference();


        buttonSubirFoto= (Button) vista.findViewById(R.id.btnSubirFotoModificar);
        editTextNombreModificar = (EditText) vista.findViewById(R.id.editTextNombreModificar);
        editTextEmailModificar = (EditText) vista.findViewById(R.id.editTextEmailModificar);
        editTextContraseñaModificar = (EditText) vista.findViewById(R.id.editTextContraseñaModificar);
        editTextApellidosModificar = (EditText) vista.findViewById(R.id.editTextApellidosModificar);
        editTextEdadModificar = (EditText) vista.findViewById(R.id.editTextEdadModificar);
        buttonModificar = (Button) vista.findViewById(R.id.btnModificar);

        id = autorizacion.getCurrentUser().getUid();

        firebase.child("Usuarios").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Usuarios u = dataSnapshot.getValue(Usuarios.class);
                    editTextNombreModificar.setText(""+u.getNombre());
                    editTextApellidosModificar.setText(""+u.getApellidos());
                    editTextEdadModificar.setText(""+u.getEdad());
                    editTextEmailModificar.setText(""+u.getEmail());
                    editTextContraseñaModificar.setText(""+u.getContraseña());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        buttonModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firebase.child("Usuarios").child(id).child("nombre").setValue(editTextNombreModificar.getText().toString());
                firebase.child("Usuarios").child(id).child("apellidos").setValue(editTextApellidosModificar.getText().toString());
                firebase.child("Usuarios").child(id).child("edad").setValue(Integer.parseInt(editTextEdadModificar.getText().toString()));
                firebase.child("Usuarios").child(id).child("email").setValue(editTextEmailModificar.getText().toString());
                firebase.child("Usuarios").child(id).child("contraseña").setValue(editTextContraseñaModificar.getText().toString());

                Intent intent = new Intent(getContext(), SecondActivity.class);
                startActivity(intent);
            }
        });

        // Inflate the layout for this fragment
        return vista;
    }
}
