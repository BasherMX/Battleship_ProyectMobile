package mx.basher.battleship

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import org.json.JSONArray
import kotlin.random.Random

class ChoseYourBoats : AppCompatActivity() {
    private lateinit var btnFicha : Button
    private lateinit var terTablero : TresEnRaya
    private lateinit var lblCasilla : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chose_your_boats)



        terTablero = findViewById(R.id.tablero)
        btnFicha = findViewById(R.id.btnFicha)

        lblCasilla = findViewById(R.id.lblCasilla)
        lblCasilla.text = "Elije tus barcos";


        //recuperar el nombre del jugador
        var nombreJugador= intent.getStringExtra("Nombre")
        var modoJuego= intent.getStringExtra("Modo")



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
                    var TableroAuto = generarTableroAuto()
                    i.putExtra("TableroAuto",TableroAuto)
                    startActivity(i)
                }else if(modoJuego == "dos"){
                    val i = Intent(this, TableroJuego::class.java)
                    i.putExtra("Nombre",nombreJugador)
                    i.putExtra("Modo","dos")
                    i.putExtra("TableroJugadorUno",TableroAux)
                    i.putExtra("TableroJugadorDos",TableroAux)
                    startActivity(i)

                }


            }
        }

        btnFicha.text = terTablero.getBtnNombre()



        terTablero.setOnCasillaSeleccionadaListener { contador, exist ->
            var aux = contador
            if(aux < 16){
                lblCasilla.text = "Barcos colocados: ${aux}/16";
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

       // Toast.makeText(this, JSONArray(Tt).toString(), Toast.LENGTH_SHORT).show()


        return Tt

    }


}//class