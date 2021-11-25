package br.com.fiap.cp02.rmXXXXX_rmXXXXX

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    lateinit var rdgMaterias : RadioGroup
    lateinit var edtMetrosQuadrados : EditText
    lateinit var chkFrete : CheckBox
    lateinit var txtResultado : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rdgMaterias = findViewById(R.id.rdgMateriais)
        edtMetrosQuadrados = findViewById(R.id.edtMetrosQuadrados)
        chkFrete = findViewById(R.id.chkFrete)
        txtResultado = findViewById(R.id.txtResultado)
    }

    /**
     * Evento disparado pelo botão calcular
     *
     * @param view
     */
    fun calcular(view: View) {
        val strMetrosQuadrados = edtMetrosQuadrados.text.toString().trim()

        if ( strMetrosQuadrados.isNullOrEmpty() ) {
            toast(getString(R.string.medida_em_m2))
            return
        }

        try {
            val metrosQuadrados = strMetrosQuadrados.toDouble()

            if ( metrosQuadrados <= 0 ) {
                toast(getString(R.string.informe_numero_positivo))
                return
            }

            var total = when ( rdgMaterias.checkedRadioButtonId ) {
                R.id.rdbPisoBranco -> 24.9
                R.id.rdbPisoAlbania -> 11.9
                R.id.rdbPorcelanatoPerlato -> 39.9
                R.id.rdbRevestimentoMosaico -> 16.9
                else -> 0.0
            }

            total *= metrosQuadrados

            if ( chkFrete.isChecked ) {
                total *= 1.3
            }

            txtResultado.text = String.format("R$ %.2f", total)

        } catch ( e : Exception ) {
            toast("${getString(R.string.ocorreu_um_erro)} ${e.message}")
        }
    }

    /**
     * Padroniza um toast para este app
     *
     * @param msg
     */
    fun toast(msg : String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}