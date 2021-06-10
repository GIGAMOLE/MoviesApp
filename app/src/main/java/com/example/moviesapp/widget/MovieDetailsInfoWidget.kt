package com.example.moviesapp.widget

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.moviesapp.R
import com.example.moviesapp.databinding.WidgetMovieDetailsInfoBinding

class MovieDetailsInfoWidget @JvmOverloads constructor(
	context: Context, attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

	private val binding: WidgetMovieDetailsInfoBinding

	init {
		inflate(context, R.layout.widget_movie_details_info, this)
		binding = WidgetMovieDetailsInfoBinding.bind(this)

		context.obtainStyledAttributes(attrs, R.styleable.MovieDetailsInfoWidget).run {
			setMovieDetailsScale(
				getFloat(R.styleable.MovieDetailsInfoWidget_movieDetailsScale, 1.0F)
			)

			recycle()
		}
	}

	/**
	 * Set the movie details title scale.
	 *
	 * @param scale the proportional XY scale.
	 */
	fun setMovieDetailsScale(scale: Float) {
		binding.textViewMovieDetailsInfoTitle.scaleX = scale
		binding.textViewMovieDetailsInfoTitle.scaleY = scale
	}

	/**
	 * Set the details from the movie model to the widget.
	 *
	 * @param title the movie title.
	 * @param subtitle the movie subtitle.
	 */
	fun setMovieDetails(title: String, subtitle: String) {
		binding.textViewMovieDetailsInfoTitle.text = title
		binding.textViewMovieDetailsInfoSubtitle.text = subtitle
	}
}
