package com.jafan.loteria

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.jafan.loteria.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var sorteio: List<String> = listOf()
    private val viewModel: NumberViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.dezenasEditText.setOnKeyListener { view, keyCode, _ -> handleKeyEvent(view, keyCode) }
        binding.totalEditText.setOnKeyListener { view, keyCode, _ -> handleKeyEvent(view, keyCode)  }

        adaptarView()
        val adapter = NumberAdapter()
        binding.recyclerView.adapter = adapter
        binding.botaoSortear.setOnClickListener {
            lifecycleScope.launch {
                sorteio = sortear()
                if (sorteio.isNotEmpty()) {
                    adapter.submitList(sorteio)
                    adaptarView()
                }
            }
                    }

    }

    private fun sortear(): List<String> {
        val tot = binding.totalEditText.text.toString().toIntOrNull()
        val dez = binding.dezenasEditText.text.toString().toIntOrNull()
        return viewModel.sorteio(tot, dez)
    }

    private fun adaptarView() {
        binding.apply {
            if (viewModel.showSorteio) {
                resultado.visibility = View.VISIBLE
                dezenasEditText.isEnabled = false
                totalEditText.isEnabled = false
            } else {
                resultado.visibility = View.GONE
                dezenasEditText.isEnabled = true
                totalEditText.isEnabled = true
            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.opcao_novo_sorteio -> {
                viewModel.resetSorteio()
                adaptarView()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }





}