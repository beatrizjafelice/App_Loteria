package com.jafan.loteria

import androidx.lifecycle.ViewModel
import kotlin.random.Random

class NumberViewModel: ViewModel() {

    private var _total: Int? = 0
    val total get() = _total!!

    private var _dezenas: Int? = 0
    val dezenas get() = _dezenas!!

    private var _showSorteio = false
    val showSorteio get() = _showSorteio

    private var _numbers: MutableList<String> = mutableListOf()
    val numbers: List<String> = _numbers



    fun setSorteio(tot: Int, dez: Int) {
        _total = tot
        _dezenas = dez
    }

    private fun inputValido(tot: Int?, dez: Int?): Boolean {
        return (tot != null && tot != 0) && (dez != null && dez != 0) && (tot > dez)
    }


    fun sorteio(tot: Int?, dez: Int?): List<String> {
        if (inputValido(tot, dez)) {
            _total = tot
            _dezenas = dez
            var num: Int
            val sorteio = mutableListOf<String>()
            while (sorteio.size != dezenas) {
                num = Random.nextInt(1, total.plus(1))
                val str = if (num in 1..9) "0${num}" else num.toString()
                if (!sorteio.contains(str)) {
                    sorteio.add(str)
                }
            }
            _numbers = sorteio
            _showSorteio = true
            return _numbers.sorted()
        } else {
            _showSorteio = false
            return listOf()
        }

    }

    fun resetSorteio() {
        _total = 0
        _dezenas = 0
        _numbers.clear()
        _showSorteio = false
    }




}