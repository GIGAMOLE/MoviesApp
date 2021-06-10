package com.example.moviesapp.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.constraintlayout.helper.widget.Carousel
import com.example.moviesapp.R
import com.example.moviesapp.databinding.WidgetMovieCarouselBinding
import com.example.moviesapp.db.MOVIES

class MovieCarouselWidget @JvmOverloads constructor(
	context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

	/**
	 * Callback when the onNewItem() method from the carousel adapter is happening.
	 */
	lateinit var onNewItemCallback: (index: Int) -> Unit

	/**
	 * Returns the current movie index from the carousel.
	 */
	val currentIndex: Int
		get() = binding.carousel.currentIndex

	private val binding: WidgetMovieCarouselBinding
	private val adapter = object : Carousel.Adapter {
		override fun count(): Int = MOVIES.size

		override fun populate(view: View?, index: Int) {
			if (view == null) return

			val movie = MOVIES[index]
			val movieWidget = view as MovieWidget

			movieWidget.setMovieImageResource(movie.poster)
		}

		override fun onNewItem(index: Int) {
			onNewItemCallback(index)
		}
	}

	init {
		inflate(context, R.layout.widget_movie_carousel, this)
		binding = WidgetMovieCarouselBinding.bind(this)

		// Enables the elevation and animation draw outside of this widget.
		clipToPadding = false
		clipChildren = false

		binding.carousel.setAdapter(adapter)
	}

	/**
	 * Sets the transition to the opened movie item state. In the opened state swipe is blocked.
	 */
	fun enableOpenTransition() {
		binding.motionLayoutWidgetMovieCarousel.isInteractionEnabled = false
		binding.motionLayoutWidgetMovieCarousel.setTransition(R.id.transition_carousel_movie_open)
	}

	/**
	 * Interpolates the opened movie item state progress.
	 */
	fun setOpenTransitionProgress(progress: Float) {
		binding.motionLayoutWidgetMovieCarousel.progress = progress
	}

	/**
	 * Restore the carousel transitions to default.
	 */
	fun restoreDefaultTransition() {
		binding.motionLayoutWidgetMovieCarousel.isInteractionEnabled = true
		binding.motionLayoutWidgetMovieCarousel.transitionToState(R.id.constraint_set_carousel_movie_start)
	}
}
