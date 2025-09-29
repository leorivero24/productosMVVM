package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity {

    private Button btnCargar, btnListar, btnSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCargar = findViewById(R.id.btnCargar);
        btnListar = findViewById(R.id.btnListar);
        btnSalir = findViewById(R.id.btnSalir);

        // Abrir fragment Cargar
        btnCargar.setOnClickListener(v -> openFragment(new CargarFragment()));

        // Abrir fragment Listar
        btnListar.setOnClickListener(v -> openFragment(new ListarFragment()));

        // Confirmar salida
        btnSalir.setOnClickListener(v -> {
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Salir")
                    .setMessage("¿Seguro que desea cerrar la aplicación?")
                    .setPositiveButton("Sí", (dialog, which) -> finishAffinity())
                    .setNegativeButton("No", null)
                    .show();
        });
    }

    // Función para reemplazar fragment en el FrameLayout
    private void openFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }
}
