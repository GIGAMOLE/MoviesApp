package com.example.moviesapp.widget

import android.content.Context
import android.graphics.Color
import android.graphics.Outline
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.view.ViewOutlineProvider
import androidx.annotation.DrawableRes
import com.example.moviesapp.R
import com.example.moviesapp.databinding.WidgetMovieBinding
import com.google.android.material.card.MaterialCardView

class MovieWidget @JvmOverloads constructor(
	context: Context, attrs: AttributeSet? = null
) : MaterialCardView(context, attrs) {

	private val binding: WidgetMovieBinding
	private val movieOutlineProvider = MovieOutlineProvider(1.0F)

	init {
		inflate(context, R.layout.widget_movie, this)
		binding = WidgetMovieBinding.bind(this)

		setRippleColorResource(android.R.color.transparent)

		context.obtainStyledAttributes(attrs, R.styleable.MovieWidget).run {
			val drawable = getDrawable(R.styleable.MovieWidget_movieImage)
			setMovieImage(drawable ?: ColorDrawable(Color.WHITE))

			setMovieImageAlpha(getFloat(R.styleable.MovieWidget_movieImageAlpha, 1.0F))
			setMovieImageElevationAlpha(getFloat(R.styleable.MovieWidget_movieImageElevationAlpha, 1.0F))
			setMovieImageTopFadeAlpha(getFloat(R.styleable.MovieWidget_movieImageTopFadeAlpha, 0.0F))
			setMovieImageZoom(getFloat(R.styleable.MovieWidget_movieImageZoom, 1.0F))
			setMovieImagePanY(getFloat(R.styleable.MovieWidget_movieImagePanY, 0.0F))

			recycle()
		}
	}

	/**
	 * Set the movie image content alpha.
	 *
	 * @param alpha the movie image content alpha.
	 */
	fun setMovieImageAlpha(alpha: Float) {
		binding.imageViewMovieContent.alpha = alpha
	}

	/**
	 * Set the [MaterialCardView] elevation alpha with the custom [MovieOutlineProvider].
	 *
	 * @param alpha the widget elevation alpha.
	 */
	fun setMovieImageElevationAlpha(alpha: Float) {
		movieOutlineProvider.alpha = alpha
		outlineProvider = movieOutlineProvider
	}

	/**
	 * Set the movie image content top fade alpha. Shown in the opened state, below the status bar.
	 *
	 * @param alpha the movie image content top fade alpha.
	 */
	fun setMovieImageTopFadeAlpha(alpha: Float) {
		binding.imageViewMovieTopFade.alpha = alpha
	}

	/**
	 * Set the movie image content zoom.
	 *
	 * @param zoom the movie image content zoom.
	 */
	fun setMovieImageZoom(zoom: Float) {
		binding.imageViewMovieContent.imageZoom = zoom
	}

	/**
	 * Set the movie image content pan Y.
	 *
	 * @param panY the movie image content panY.
	 */
	fun setMovieImagePanY(panY: Float) {
		binding.imageViewMovieContent.imagePanY = panY
	}

	/**
	 * Set the movie image [Drawable].
	 *
	 * @param drawable the movie image content.
	 */
	fun setMovieImage(drawable: Drawable) {
		binding.imageViewMovieContent.setImageDrawable(drawable)
	}

	/**
	 * Set the movie image [DrawableRes].
	 *
	 * @param image the movie image resource.
	 */
	fun setMovieImageResource(@DrawableRes image: Int) {
		binding.imageViewMovieContent.setImageResource(image)
	}

	private class MovieOutlineProvider(var alpha: Float) : ViewOutlineProvider() {

		override fun getOutline(view: View, outline: Outline) {
			view.background?.getOutline(outline) ?: outline.setRect(0, 0, view.width, view.height)
			outline.alpha = alpha
		}
	}
}
