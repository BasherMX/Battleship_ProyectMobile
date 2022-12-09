package mx.basher.battleship

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class WinnerActivity : AppCompatActivity() {
    lateinit var volverjugar_btn: Button
    lateinit var fin_btn: Button
    private lateinit var winnerTXT: TextView
    private lateinit var ganadorTXT: TextView
    private lateinit var ganadorTXT2: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_winner)

        //find views
        winnerTXT = findViewById(R.id.ganadorTXT_nombre)
        volverjugar_btn = findViewById(R.id.volverjugar_btn)
        fin_btn = findViewById(R.id.fin_btn)
        ganadorTXT = findViewById(R.id.ganadorTXT)
        ganadorTXT2 = findViewById(R.id.ganadorTXT2)

        //recuperar el nombre del jugador
        var nombreJugador= intent.getStringExtra("Nombre")
        var tipo = intent.getStringExtra("tipo")


        if(tipo == "ganador"){
            winnerTXT.text = nombreJugador.toString()
        }else{
            ganadorTXT.text = "EMPATE"
            ganadorTXT2.text = "EMPATE"
            winnerTXT.text = ""
        }


        volverjugar_btn.setOnClickListener(){
            finish();
        }

        fin_btn.setOnClickListener(){
            val i = Intent(this, Creditos::class.java)
            startActivity(i)
            finish();
        }

    }
}