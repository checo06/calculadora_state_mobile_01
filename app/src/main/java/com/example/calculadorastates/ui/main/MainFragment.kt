package com.example.calculadorastates.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.calculadorastates.R
import com.example.calculadorastates.databinding.FragmentMainBinding


class MainFragment : Fragment(R.layout.fragment_main) {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by viewModels()

    /*
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }
    */

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentMainBinding.bind(view)

        binding.display.text = viewModel.calculadoraDominioIE.getVisualizador()

        binding.seven.setOnClickListener({
            viewModel.onClickOperando( (it as Button).text.toString())
        })
        binding.eight.setOnClickListener({
            viewModel.onClickOperando( (it as Button).text.toString())
        })
        binding.nine.setOnClickListener({
            viewModel.onClickOperando( (it as Button).text.toString())
        })

        binding.four.setOnClickListener({
            viewModel.onClickOperando( (it as Button).text.toString())
        })
        binding.five.setOnClickListener({
            viewModel.onClickOperando( (it as Button).text.toString())
        })
        binding.six.setOnClickListener({
            viewModel.onClickOperando( (it as Button).text.toString())
        })

        binding.one.setOnClickListener({
            viewModel.onClickOperando( (it as Button).text.toString())
        })
        binding.two.setOnClickListener({
            viewModel.onClickOperando( (it as Button).text.toString())
        })
        binding.three.setOnClickListener({
            viewModel.onClickOperando( (it as Button).text.toString())
        })
        binding.sign.setOnClickListener({
            viewModel.onClickOperando( (it as Button).text.toString())
        })

        binding.cero.setOnClickListener({
            viewModel.onClickOperando( (it as Button).text.toString())
        })
        binding.dot.setOnClickListener({
            viewModel.onClickOperando( (it as Button).text.toString())
        })

        binding.plus.setOnClickListener({
            viewModel.onClickOperador( (it as Button).text.toString())
        })
        binding.minus.setOnClickListener({
            viewModel.onClickOperador( (it as Button).text.toString())
        })
        binding.multiply.setOnClickListener({
            viewModel.onClickOperador( (it as Button).text.toString())
        })
        binding.divide.setOnClickListener({
            viewModel.onClickOperador( (it as Button).text.toString())
        })
        binding.equal.setOnClickListener({
            viewModel.onClickOperador( (it as Button).text.toString())
        })

        binding.errase.setOnClickListener({
            viewModel.onClickBorrar()
        })

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.calculatorEvent.collect { event ->
                when (event) {
                    is MainViewModel.CalculatorEvent.ShowValue -> {
                        binding.display.text = event.value
                    }
                }
            }
        }


    }


    }