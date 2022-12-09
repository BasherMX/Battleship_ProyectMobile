package mx.basher.battleship

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var jugar_btn: Button
    lateinit var nombre_txt:EditText

    override fun onCreate(savedInstanceState: Bundle?) {

        setTheme(R.style.Theme_Battleship)
        Thread.sleep(1200)

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        jugar_btn = findViewById(R.id.jugar_btn)
        nombre_txt = findViewById(R.id.nombre_txt)

        //Quitar
        //nombre_txt.setText("Ulises")

        jugar_btn.setOnClickListener(){
            if(nombre_txt.text.toString() != ""){
                val i = Intent(this, ModoJuego::class.java)
                i.putExtra("Nombre",nombre_txt.text.toString()!!.uppercase())
                startActivity(i)
            }else{
                Toast.makeText(this, "Por favor Ingresa un Nombre", Toast.LENGTH_SHORT).show()
            }
        }

    }
}