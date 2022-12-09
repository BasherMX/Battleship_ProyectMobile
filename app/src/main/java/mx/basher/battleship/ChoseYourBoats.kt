package mx.basher.battleship

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class ChoseYourBoats : AppCompatActivity() {
    private lateinit var btnFicha : Button
    private lateinit var terTablero : TresEnRaya
    private lateinit var lblCasilla : TextView
    private lateinit var nmbreJugador2jgs: TextView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chose_your_boats)
        var txt = ""


        //find Views
        terTablero = findViewById(R.id.tablero)
        btnFicha = findViewById(R.id.btnFicha)
        lblCasilla = findViewById(R.id.lblCasilla)
        nmbreJugador2jgs = findViewById(R.id.nmbreJugador2jgs)


        //Resetear Tablero
        terTablero.limpiarTodo()
        terTablero.setModoJuego(1)


        //recuperar el nombre del jugador
        var nombreJugador:String?= intent.getStringExtra("Nombre")
        var modoJuego= intent.getStringExtra("Modo")
        val turnoEscoger = intent.getStringExtra("turno")

        //set el nombre del jugador
        if(modoJuego == "dos"){
            if(turnoEscoger == "uno"){
                nmbreJugador2jgs.text = nombreJugador.toString()
            }else{
                nmbreJugador2jgs.text = "JUGADOR DOS"
            }
        }else{
            nmbreJugador2jgs.text = nombreJugador.toString()
        }
        lblCasilla.text = "Elije tus barcos";


        btnFicha.setOnClickListener {
            var TableroAux = terTablero.GuardaryJugar()

            if(TableroAux.isEmpty()){
                Toast.makeText(this, "Porfavor seleccione al menos 16 casillas", Toast.LENGTH_SHORT).show()
            }else{
                if(modoJuego == "uno"){
                    val i = Intent(this, TableroJuego::class.java)
                    i.putExtra("Nombre",nombreJugador)
                    i.putExtra("Modo","uno")
                    i.putExtra("TableroJugador",TableroAux)
                    i.putExtra("TableroAuto",generarTableroAuto())
                    startActivity(i);
                    finish();
                }else if(modoJuego == "dos"){


                    if(turnoEscoger == "uno"){
                        val i = Intent(this, ChoseYourBoats::class.java)
                        i.putExtra("Nombre",nombreJugador)
                        i.putExtra("Modo","dos")
                        i.putExtra("turno","dos")
                        i.putExtra("TableroJugadorUno",TableroAux)
                        startActivity(i);
                        finish();
                    }else{
                        var primerTablero = intent.extras?.getSerializable("TableroJugadorUno") as Array<Array<Int>>
                        val i = Intent(this, TableroJuego::class.java)
                        i.putExtra("Nombre",nombreJugador)
                        i.putExtra("Modo","dos")
                        i.putExtra("TableroJugadorUno", primerTablero)
                        i.putExtra("TableroJugadorDos", TableroAux)
                        startActivity(i);
                        finish();
                    }
                }
            }
        }

        terTablero.setOnCasillaSeleccionadaListener { contador, exist ->
            var aux = contador
            if(aux < 16){
                lblCasilla.text = "Barcos colocados: ${aux}/16"
            }else{
                lblCasilla.text = "Barcos colocados: 16/16";
                Toast.makeText(this, "Llegaste al limite de barcos", Toast.LENGTH_SHORT).show()
                //btnFicha.setEnabled(false)
            }
            if (exist != 0){
                Toast.makeText(this, "Ya hay un barco en esta casilla", Toast.LENGTH_SHORT).show()
            }
        }
    }//onCreate

    fun generarTableroAuto(): Array<Array<Int>> {
        var Tt = Array(10){Array(10){ 0}}
        var contador = 0
        do {
            Tt = Array(10){Array(10){ 0}}
            contador = 0
            for(i in 0..9){
                for(j in 0..9){
                    val rand = (0..10).random()
                    if(rand > 9 && contador < 16){
                        println(contador)
                        contador++
                        Tt[i][j] = 2
                    }
                }
            }
        }while (contador < 16)
        return Tt
    }
}//class