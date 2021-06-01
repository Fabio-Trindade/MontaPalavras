package com.example.montapalavras

object BankOfWords {

    private var foundWords = arrayListOf<String>()
    private var betterWord=""
    private var betterScore=0
    private var unusedLetters:String =""
    private var addSpecialCharacter=false

    private val bankOfWords= arrayOf( "Abacaxi", "Manada", "mandar", "porta", "mesa", "Dado", "Mangas", "Já", "coisas", "radiografia",
        "matemática", "Drogas", "prédios","implementação", "computador", "balão", "Xícara", "Tédio",
        "faixa", "Livro", "deixar", "superior", "Profissão", "Reunião", "Prédios", "Montanha", "Botânica",
        "Banheiro", "Caixas", "Xingamento", "Infestação", "Cupim", "Premiada", "empanada", "Ratos",
        "Ruído", "Antecedente", "Empresa", "Emissário", "Folga", "Fratura", "Goiaba", "Gratuito",
        "Hídrico", "Homem", "Jantar", "Jogos", "Montagem", "Manual", "Nuvem", "Neve", "Operação",
        "Ontem", "Pato", "Pé", "viagem", "Queijo", "Quarto", "Quintal", "Solto", "rota", "Selva",
        "Tatuagem", "Tigre", "Uva", "Último", "Vitupério", "Voltagem", "Zangado", "Zombaria", "Dor","boi" )

    fun getBetterWord(): String {
        return betterWord
    }
    fun getBetterScore(): Int {
        return betterScore
    }
    fun getUnusedLetters(): String {
        return unusedLetters
    }

    fun init(){
        foundWords.clear()
        unusedLetters =""
        betterScore =0
        betterWord =""
        addSpecialCharacter =false
    }

    fun searchForWords(input:String){
        val copyInput= treatWords(input.toUpperCase())
        val table = TabelaHash()
        table.insert(copyInput)
        for (word in bankOfWords ){
            val copyWord= treatWords(word.toUpperCase())
            table.insert(copyInput)
            var count =0
            for (letra in copyWord){
                val key:Int=table.generateKey(letra)
                    if(table.table[key]>0){
                        table.table[key]--
                        count++
                    }else{
                        break
                    }
            }
            if (count==word.length){
                foundWords.add(copyWord)
            }
        }
    }

    private fun treatWords(word: String):String{
        var copyWord:String=word
        for(letra in word) {
            if (letra.toInt() >= 0 && letra.toInt() < 65) {
                if (!addSpecialCharacter){
                    unusedLetters +=letra
                }
                copyWord= copyWord.replace(letra, ' ')
            } else if (letra.toInt() > 90) {
                copyWord = if (letra == 'Á' || letra == 'Â' || letra == 'Ã' || letra == 'À') {
                    copyWord.replace(letra, 'A')
                } else if (letra == 'É' || letra == 'Ê' || letra == 'Ẽ' || letra == 'È') {
                    copyWord.replace(letra, 'E')
                } else if (letra == 'Í' || letra == 'Î' || letra == 'Ĩ' || letra == 'Ì') {
                    copyWord.replace(letra, 'I')
                } else if (letra == 'Ó' || letra == 'Ô' || letra == 'Õ' || letra == 'Ò') {
                    copyWord.replace(letra, 'O')
                } else if (letra == 'Ú' || letra == 'Û' || letra == 'Ũ' || letra == 'Ù') {
                    copyWord.replace(letra, 'U')
                } else if (letra == 'Ç') {
                    copyWord.replace(letra, 'C')
                } else {
                    if (!addSpecialCharacter){
                        unusedLetters +=letra
                    }
                    copyWord.replace(letra, ' ')
                }
            }
        }
        if (!addSpecialCharacter){
            addSpecialCharacter =true
        }
        return copyWord
    }



    fun findBetterMove(){
        var pontAtual: Int
        for (word in foundWords){
            pontAtual= MapScore.generateScore(word)
            if(pontAtual>= betterScore){
                if (pontAtual== betterScore){
                    if (word.length < betterWord.length){
                        betterWord =word
                    }
                }else{
                    betterWord =word
                    betterScore = pontAtual
                }
            }
        }

    }

    fun findUnusedLetters(input: String){
        var copyInput = treatWords(input.toUpperCase())
       for (letra in betterWord){
          val copyletra=letra.toUpperCase()
           for (j in 0..input.lastIndex){
               if (copyletra== copyInput[j] ){
                   copyInput= copyInput.removeRange(j,j+1)
                   break
               }

               }
           }
        for (i in copyInput){
            if (i!=' '){
                unusedLetters +=i
            }
       }
    }


}




