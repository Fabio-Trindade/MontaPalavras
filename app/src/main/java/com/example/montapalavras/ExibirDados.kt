package com.example.montapalavras

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class ExibirDados : AppCompatActivity() {

    lateinit var textView: TextView
    lateinit var editText: EditText
    lateinit var gridView1: GridView
    lateinit var gridView2: GridView
    lateinit var arrayAdapter1: ArrayAdapter<Char>
    lateinit var arrayAdapter2: ArrayAdapter<Char>
    lateinit var listChar1:ArrayList<Char>
    lateinit var listChar2:ArrayList<Char>

    private fun preencherDados(string:String): ArrayList<Char> {
         var dados= ArrayList<Char>()
        for (i in string){
            dados.add(i)
        }
        return  dados
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exibir_dados)

        textView=findViewById(R.id.textView)
        editText=findViewById(R.id.editPalavra2)
        gridView1=findViewById(R.id.gridView1)
        gridView2=findViewById(R.id.gridView2)

        val frase=intent.extras?.get("editText") as String

        BankOfWords.init()
        BankOfWords.searchForWords(frase)
        BankOfWords.findBetterMove()
        BankOfWords.findUnusedLetters(frase)

        listChar1 = preencherDados(BankOfWords.getBetterWord())
        listChar2= preencherDados(BankOfWords.getUnusedLetters())


        arrayAdapter1= ArrayAdapter<Char>(this,android.R.layout.simple_list_item_activated_1,listChar1)
        arrayAdapter2= ArrayAdapter<Char>(this,android.R.layout.simple_list_item_activated_1,listChar2)

        gridView1.adapter=arrayAdapter1
        gridView2.adapter=arrayAdapter2


        if (BankOfWords.getBetterScore()==0){
            textView.text="Palavra não encontrada "

        }else{
            textView.text="Palavra de ${BankOfWords.getBetterScore()} pontos "

        }
    }

    fun onClick2(view: View) {

        val frase=editText.text.toString()

        var ok:Boolean=true
        if(editText.text.toString().trim().isEmpty()){
            ok=false
            editText.error ="Digite as letras"
        }

        if(ok) {
            listChar1.clear()
            listChar2.clear()

            BankOfWords.init()
            BankOfWords.searchForWords(frase)
            BankOfWords.findBetterMove()
            BankOfWords.findUnusedLetters(frase)

            listChar1 = preencherDados(BankOfWords.getBetterWord())
            listChar2= preencherDados(BankOfWords.getUnusedLetters())

            arrayAdapter1= ArrayAdapter<Char>(this,android.R.layout.simple_list_item_activated_1,listChar1)
            arrayAdapter2= ArrayAdapter<Char>(this,android.R.layout.simple_list_item_activated_1,listChar2)

            gridView1.adapter=arrayAdapter1
            gridView2.adapter=arrayAdapter2

            if (BankOfWords.getBetterScore()==0){
                textView.text="Palavra não encontrada "

            }else{
                textView.text="Palavra de ${BankOfWords.getBetterScore()} pontos "

            }
        }
    }
}