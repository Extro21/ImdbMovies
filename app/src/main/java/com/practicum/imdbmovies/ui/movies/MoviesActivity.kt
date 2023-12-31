package com.practicum.imdbmovies.ui.movies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.practicum.imdbmovies.util.Creator
import com.practicum.imdbmovies.MoviesAdapter
import com.practicum.imdbmovies.ui.poster.PosterActivity
import com.practicum.imdbmovies.R

class MoviesActivity : AppCompatActivity() {
   // private val moviesInteractor = Creator.provideMoviesInteractor()


    companion object {
        private const val CLICK_DEBOUNCE_DELAY = 1000L
      //  private const val SEARCH_DEBOUNCE_DELAY = 2000L
    }

//    private lateinit var queryInput: EditText
//    private lateinit var placeholderMessage: TextView
//    private lateinit var moviesList: RecyclerView
//    private lateinit var progressBar: ProgressBar

   // private val movies = ArrayList<Movie>()

    private val adapter = MoviesAdapter {
        if (clickDebounce()) {
            val intent = Intent(this, PosterActivity::class.java)
            intent.putExtra("poster", it.image)
            startActivity(intent)
        }
    }

    private var isClickAllowed = true

    private val handler = Handler(Looper.getMainLooper())

   // private val searchRunnable = Runnable { searchRequest() }

    private val moviesSearchController = Creator.provideMoviesSearchController(this, adapter)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)
        moviesSearchController.onCreate()

//        placeholderMessage = findViewById(R.id.placeholderMessage)
//        queryInput = findViewById(R.id.queryInput)
//        moviesList = findViewById(R.id.locations)
//        progressBar = findViewById(R.id.progressBar)
//
//        adapter.movies = movies
//
//        moviesList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//        moviesList.adapter = adapter
//
//        queryInput.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//            }
//
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                searchDebounce()
//            }
//
//            override fun afterTextChanged(p0: Editable?) {
//            }
//
//        })
    }

    override fun onDestroy() {
        super.onDestroy()
     //   handler.removeCallbacks(searchRunnable)
        moviesSearchController.onDestroy()
    }

//    private fun searchRequest() {
//        if (queryInput.text.isNotEmpty()) {
//
//            placeholderMessage.visibility = View.GONE
//            moviesList.visibility = View.GONE
//            progressBar.visibility = View.VISIBLE
//
//            moviesInteractor.searchMovies(queryInput.text.toString(), object : MoviesInteractor.MoviesConsumer {
//                override fun consume(foundMovies: List<Movie>, code : Int) {
//                    handler.post {
//                        if(code != 200){
//                            showMessage(getString(R.string.nothing_internet), "")
//                            progressBar.visibility = View.GONE
//                        } else {
//                            progressBar.visibility = View.GONE
//                            movies.clear()
//                            movies.addAll(foundMovies)
//                            moviesList.visibility = View.VISIBLE
//                            adapter.notifyDataSetChanged()
//
//                            if (movies.isEmpty()) {
//                                showMessage(getString(R.string.nothing_found), "")
//                            } else {
//                               hideMessage()
//                            }
//                        }
//
//
//                    }
//                }
//            })
//        }
//    }

//    private fun showMessage(text: String, additionalMessage: String) {
//        if (text.isNotEmpty()) {
//            placeholderMessage.visibility = View.VISIBLE
//            movies.clear()
//            adapter.notifyDataSetChanged()
//            placeholderMessage.text = text
//            if (additionalMessage.isNotEmpty()) {
//                Toast.makeText(applicationContext, additionalMessage, Toast.LENGTH_LONG)
//                    .show()
//            }
//        } else {
//            placeholderMessage.visibility = View.GONE
//        }
//    }
//
//    private fun hideMessage() {
//        placeholderMessage.visibility = View.GONE
//    }

    private fun clickDebounce() : Boolean {
        val current = isClickAllowed
        if (isClickAllowed) {
            isClickAllowed = false
            handler.postDelayed({ isClickAllowed = true }, CLICK_DEBOUNCE_DELAY)
        }
        return current
    }

//    private fun searchDebounce() {
//        handler.removeCallbacks(searchRunnable)
//        handler.postDelayed(searchRunnable, SEARCH_DEBOUNCE_DELAY)
//    }
}