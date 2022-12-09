package mx.basher.battleship

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class ModoJuego : AppCompatActivity() {
    private lateinit var single_btn: Button;
    private lateinit var pairs_btn: Button;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modo_juego)
        single_btn = findViewById(R.id.single_btn)
        pairs_btn = findViewById(R.id.pairs_btn)

        //recuperar el nombre del jugador
        var nombreJugador= intent.getStringExtra("Nombre")

        single_btn.setOnClickListener(){
            val i = Intent(this, ChoseYourBoats::class.java)
            i.putExtra("Nombre",nombreJugador)
            i.putExtra("Modo","uno")
            startActivity(i)
            finish();

        }

        pairs_btn.setOnClickListener(){
            val i = Intent(this, ChoseYourBoats::class.java)
            i.putExtra("Nombre",nombreJugador)
            i.putExtra("Modo","dos")
            i.putExtra("turno","uno")
            startActivity(i)
            finish();

        }





    }
}