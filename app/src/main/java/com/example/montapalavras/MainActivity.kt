/**
 * A lógica do programa se baseia em uma tabela hash onde cada posição contém a quantidade da respectiva letra da entrada de acordo
 * com a função h(k)=k mod 26, onde k é o valor da letra na tabela ASCII.Antes de começar a análise das palavras para ver
 * se elas existem é feito o tratamento da entrada(palavra inserida pelo usuário), deixando as palavras sem acento ,sem caracteres
 * especiais e todas as letras maiúsculas, cada palavra do banco de palavras também recebe o mesmo tratamento.Para analisar se uma
 * palavra existe, o programa percorre o banco de palavras onde a cada palavra analisada é gerada uma chave de acordo com cada letra
 * da palavra,e se na posição da tabela da chave gerada tiver um número maior que zero(significa que tem a letra) é decrementada uma
 * unidade nessa posição(significa que uma unidade dessa letra não está mais dísponivel) e um contador é incrementado uma unidade
 * (no final da análise da palavra é feito uma comparação entre o contador e o tamanho da palavra analisada,se for igual significa
 * que a palavra pode ser formada) e se tiver um numero na posição da chave gerada menor ou igual a zero(significa que a não possui
 * a letra ou não possui letra suficiente para formar a palavra), o programa vai analisar a próxima palavra. Como citado acima,
 * no final da análise de cada palavra é feita uma comparação para verificar se a palavra existe, se existir ela é adicionada em
 * um array de palavras encontradas.
 *
 * Para achar o melhor movimento o programa pecorre o array de palavras encontradas comparando as suas pontuações
 * seguindo as regras citadas na descrição da prova. Aqui se utiliza a função de gerar pontuação que tem como parametro
 * a palavra atual do array, para gerar a pontuação é utilizada uma lista onde cada posição contem uma pontuação correspondente
 * a cada letra e cada letra corresponde a uma posição de acordo com a função h(k)=k mod 26, e k o valor da
 * letra na tabela ASCII, e assim se soma o valor de todas as letras.
 *
 * Foram utilizados 3 estruturas de dados:
 *      Dois objetos:com.example.montapalavras.BankOfWords e HashScore
 *      Uma classe:com.example.montapalavras.TabelaHash
 *
 * No arquivo "readme.txt" será feita uma descrição mais detalhada do programa como a descrição das estruturas criadas e
 * das funções implementadas, assim como exemplos para melhor entendimento.
 */


package com.example.montapalavras

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    private lateinit var tela: Intent
    private lateinit var palavra:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        palavra= findViewById(R.id.editPalavra)
    }


    fun onClick(view: View) {
        var ok:Boolean=true
        if(palavra.text.toString().trim().isEmpty()){
            ok=false
            palavra.error ="Digite as letras"
        }

        if(ok) {
        tela= Intent(applicationContext,ExibirDados::class.java)
        tela.putExtra("editText",palavra.text.toString() )
        startActivity(tela)
    }
    }




}