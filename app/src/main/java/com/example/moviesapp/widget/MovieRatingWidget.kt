package com.example.moviesapp.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.example.moviesapp.R
import com.example.moviesapp.databinding.WidgetMovieRatingBinding
import com.example.moviesapp.db.MOVIES
import com.google.android.material.math.MathUtils
import kotlin.random.Random

class MovieRatingWidget @JvmOverloads constructor(
	context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

	private val binding: WidgetMovieRatingBinding

	private var targetRating: Int = 0
	private var currentRating: Int = 0

	init {
		inflate(context, R.layout.widget_movie_rating, this)
		binding = WidgetMovieRatingBinding.bind(this)

		if (isInEditMode) {
			// Set the random rating in editor.
			setIndex(Random.nextInt(4))
		}
	}

	/**
	 * Invalidates the rating with the index.
	 *
	 * @param index the current movie index.
	 */
	fun setIndex(index: Int) {
		val currentMovie = MOVIES[index]

		currentRating = currentMovie.rating
		targetRating = currentRating

		setRatingProgress(1.0F)
	}

	/**
	 * Setup the target rating value for change progress transition.
	 *
	 * @param index the current index of the movie carousel.
	 * @param isForward is moving forward the movie carousel.
	 */
	fun invalidateRating(index: Int, isForward: Boolean) {
		val targetIndex = if (isForward) {
			index + 1
		} else {
			index - 1
		}

		val currentMovie = MOVIES[index]
		val targetMovie = MOVIES[targetIndex]

		currentRating = currentMovie.rating
		targetRating = targetMovie.rating
	}

	/**
	 * Animates the rating with the progress from the [MovieCarouselWidget].
	 *
	 * @param progress the fraction from 0 to 1.
	 */
	fun setRatingProgress(progress: Float) {
		val startProgress = calculateRatingProgress(currentRating)
		val endProgress = calculateRatingProgress(targetRating)

		binding.motionLayoutWidgetMovieRating.progress =
			MathUtils.lerp(startProgress, endProgress, progress)
	}

	private fun calculateRatingProgress(rating: Int): Float {
		// Finds the proper staggered progress.
		// For example: first star is in range from 0 to 20.
		return (rating.toFloat() + 1F) / 5F
	}
}
