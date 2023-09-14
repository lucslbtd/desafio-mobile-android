package com.example.desafiomarvel.presentation.home

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.desafiomarvel.data.model.Character
import com.example.desafiomarvel.databinding.ActivityHomeBinding
import com.example.desafiomarvel.domain.usecases.MarvelUseCase
import com.example.desafiomarvel.presentation.utils.CharacterAdapter
import org.koin.android.ext.android.get

class HomeActivity : AppCompatActivity(), HomeContract.View {

    private lateinit var homePresenter: HomePresenter
    private lateinit var characterAdapter: CharacterAdapter
    private lateinit var carouselAdapter: CharacterAdapter
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        homePresenter = HomePresenter(this, MarvelUseCase(get()))

        val recyclerView = binding.rvHomeCharacters
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        characterAdapter = CharacterAdapter(mutableListOf())
        recyclerView.adapter = characterAdapter

        val carouselRecyclerView = binding.carouselRecyclerView
        carouselRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        carouselAdapter = CharacterAdapter(mutableListOf())
        carouselRecyclerView.adapter = carouselAdapter

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val totalItemCount = layoutManager.itemCount
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()

                if (totalItemCount <= (lastVisibleItem + 2)) {
                    homePresenter.loadCharacters()
                }
            }
        })
        homePresenter.loadCarouselCharacters()
        homePresenter.loadCharacters()
    }

    override fun showCharacters(characters: List<Character>?) {
        if (characters != null) {
            characterAdapter.characters.addAll(characters)
        }
        characterAdapter.notifyDataSetChanged() // nao ta muito legal
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showCarouselCharacters(characters: List<Character>?) {
        if (characters != null) {
            carouselAdapter.characters.addAll(characters)
        }
        carouselAdapter.notifyDataSetChanged()// nao ta muito legal
    }
}
