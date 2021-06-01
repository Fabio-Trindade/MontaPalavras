package com.example.montapalavras

object MapScore {
    private var tableScore= arrayListOf(1,1,3,10,1,1,1,1,4,2,8,0,10,1,3,3,2,1,4,2,4,1,8,0,1,3)

    private fun generateKey(char:Char):Int{
        return (char.toInt())%26
    }

    fun generateScore(string: String):Int{
        var score =0
        var key:Int
        for(letra in string){
            key= generateKey(letra)
            score+= tableScore[key]
        }
        return score
    }
}