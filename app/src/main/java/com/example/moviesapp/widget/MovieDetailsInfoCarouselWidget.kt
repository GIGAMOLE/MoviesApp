package com.example.moviesapp.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.constraintlayout.helper.widget.Carousel
import com.example.moviesapp.R
import com.example.moviesapp.databinding.WidgetMovieCarouselDetailsInfoBinding
import com.example.moviesapp.db.MOVIES

class MovieDetailsInfoCarouselWidget @JvmOverloads constructor(
	context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

	private val binding: WidgetMovieCarouselDetailsInfoBinding
	private val adapter = object : Carousel.Adapter {
		override fun count(): Int = MOVIES.size

		override fun populate(view: View?, index: Int) {
			if (view == null) return

			val movie = MOVIES[index]
			val movieDetailsInfoWidget = view as MovieDetailsInfoWidget

			movieDetailsInfoWidget.setMovieDetails(movie.title, movie.subtitle)
		}

		override fun onNewItem(index: Int) = Unit
	}

	init {
		inflate(context, R.layout.widget_movie_carousel_details_info, this)
		binding = WidgetMovieCarouselDetailsInfoBinding.bind(this)

		// Enables the elevation and animation draw outside of this widget.
		clipToPadding = false
		clipChildren = false

		binding.carousel.setAdapter(adapter)
	}

	/**
	 * Invalidates the carousels [index].
	 *
	 * @param index a new index for the carousel.
	 */
	fun invalidateOnNewItem(index: Int) {
		// Transition to the start state and override the mCurrentState variable.
		binding.motionLayoutWidgetMovieCarouselDetailsInfo.transitionToState(
			R.id.constraint_set_carousel_movie_details_info_start
		)
		binding.carousel.jumpToIndex(index)
	}

	/**
	 * Setup the transition before the onNewItem() progress change.
	 *
	 * @param isForward is moving forward the movie carousel.
	 */
	fun setOnNewItemStart(isForward: Boolean) {
		if (isForward) {
			binding.motionLayoutWidgetMovieCarouselDetailsInfo.setTransition(
				R.id.transition_carousel_movie_details_info_forward
			)
		} else {
			binding.motionLayoutWidgetMovieCarouselDetailsInfo.setTransition(
				R.id.transition_carousel_movie_details_info_backward
			)
		}
	}

	/**
	 * Set the transition progress while the onNewItem() transition of the [MovieCarouselWidget] moving.
	 *
	 * @param progress the movie carousel progress.
	 */
	fun setOnNewItemProgress(progress: Float) {
		binding.motionLayoutWidgetMovieCarouselDetailsInfo.progress = progress
	}

	/**
	 * Sets the transition to the opened movie details info state.
	 */
	fun enableOpenTransition() {
		binding.motionLayoutWidgetMovieCarouselDetailsInfo.setTransition(
			R.id.transition_carousel_movie_details_info_open
		)
	}

	/**
	 * Interpolates the opened movie details info state progress.
	 */
	fun setOpenTransitionProgress(progress: Float) {
		binding.motionLayoutWidgetMovieCarouselDetailsInfo.progress = progress
	}

	/**
	 * Restore the carousel transitions to default.
	 */
	fun restoreDefaultTransition() {
		binding.motionLayoutWidgetMovieCarouselDetailsInfo.transitionToState(
			R.id.constraint_set_carousel_movie_details_info_start
		)
	}
}
