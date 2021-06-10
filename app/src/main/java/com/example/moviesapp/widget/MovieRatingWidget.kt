package com.example.moviesapp.widget

import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.example.moviesapp.R
import com.example.moviesapp.databinding.WidgetMovieRatingBinding
import kotlin.random.Random

class MovieRatingWidget @JvmOverloads constructor(
	context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

	private val binding: WidgetMovieRatingBinding
	private var progressAnimator = ValueAnimator.ofFloat(0.0F, 1.0F)

	init {
		inflate(context, R.layout.widget_movie_rating, this)
		binding = WidgetMovieRatingBinding.bind(this)

		if (isInEditMode) {
			// Animates to the random rating in editor.
			animateRating(Random.nextInt(4), true)
		}
	}

	/**
	 * Animate rating. Transforms the [rating] value to the MotionLayout progress.
	 *
	 * @param rating the movie rating from 0 to 4.
	 * @param force if true - set rating without animation.
	 */
	fun animateRating(rating: Int, force: Boolean = false) {
		// Finds the proper staggered progress.
		// For example: first star is in range from 0 to 20.
		val progress = (rating.toFloat() + 1F) / 5F
		val lastProgress = progressAnimator.animatedValue as Float

		progressAnimator.pause()
		progressAnimator = ValueAnimator.ofFloat(lastProgress, progress).apply {
			duration = if (force) 0L else 350L
			addUpdateListener {
				val currentProgress = it.animatedValue as Float
				binding.motionLayoutWidgetMovieRating.progress = currentProgress
			}
		}

		progressAnimator.start()
	}
}
