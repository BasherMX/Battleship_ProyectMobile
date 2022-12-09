package mx.basher.battleship

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Creditos : AppCompatActivity() {
    private lateinit var salirbtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creditos)

        salirbtn = findViewById(R.id.salirbtn)


        salirbtn.setOnClickListener(){
            finish();
        }
    }
}