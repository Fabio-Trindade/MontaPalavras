package com.example.montapalavras

class TabelaHash {
    //A->13 Z->12
    var table:IntArray=IntArray(26){-30}

    private fun initArray(){
        table=IntArray(26){-30}
    }

    fun generateKey(char:Char):Int{
        return (char.toInt())%26
    }

    private fun increment(key:Int){
        if(table[key]==-30){
            table[key]=1
        }else{
            table[key]++
        }
    }

    fun insert(string: String){
        initArray()
        var key: Int
        for (i in string){
            if (i!=' ') {
                key = generateKey(i)
                increment(key)
            }
        }
    }
}