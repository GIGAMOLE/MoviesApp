package com.example.moviesapp

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.motion.widget.TransitionAdapter
import androidx.fragment.app.Fragment
import com.example.moviesapp.databinding.FragmentMoviesBinding
import com.example.moviesapp.db.MOVIES

class MoviesFragment : Fragment() {

	private var _binding: FragmentMoviesBinding? = null
	internal val binding get() = _binding!!

	/**
	 * Upon triggering, delegates the transition of the root MotionLayout to the initial state.
	 * Used to back from the opened movie state on the back press.
	 */
	private val onBackPressedCallback = object : OnBackPressedCallback(false) {
		override fun handleOnBackPressed() {
			binding.root.transitionToStart()
		}
	}

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		_binding = FragmentMoviesBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		changeStatusBarIconsToDark(true)
		requireActivity().onBackPressedDispatcher.addCallback(onBackPressedCallback)

		setupMainCarousel()
		setupOpenTransition()
	}

	private fun setupMainCarousel() {
		binding.widgetMovieCarousel.onNewItemCallback = { index ->
			// Animates and invalidates related widgets, when the main movie carousel changes.
			binding.widgetMovieDetailsInfoCarousel.animateToIndex(index)
			invalidateCurrentMovieDetails()
		}
		invalidateCurrentMovieDetails()
	}

	private fun setupOpenTransition() {
		binding.root.setTransitionListener(object : TransitionAdapter() {
			override fun onTransitionStarted(motionLayout: MotionLayout?, startId: Int, endId: Int) {
				if (endId == R.id.constraint_set_fragment_movies_end) {
					binding.widgetMovieCarousel.enableOpenTransition()
					binding.widgetMovieDetailsInfoCarousel.enableOpenTransition()

					onBackPressedCallback.isEnabled = true
				}
			}

			override fun onTransitionChange(motionLayout: MotionLayout?, startId: Int, endId: Int, progress: Float) {
				if (endId == R.id.constraint_set_fragment_movies_end) {
					binding.widgetMovieCarousel.setOpenTransitionProgress(progress)
					binding.widgetMovieDetailsInfoCarousel.setOpenTransitionProgress(progress)
				}
			}

			override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
				if (currentId == R.id.constraint_set_fragment_movies_start) {
					binding.widgetMovieCarousel.restoreDefaultTransition()
					binding.widgetMovieDetailsInfoCarousel.restoreDefaultTransition()

					onBackPressedCallback.isEnabled = false
				}
			}

			override fun onTransitionTrigger(
				motionLayout: MotionLayout?,
				triggerId: Int,
				positive: Boolean,
				progress: Float
			) {
				changeStatusBarIconsToDark(!positive)
			}
		})
	}

	private fun invalidateCurrentMovieDetails() {
		val movie = MOVIES[binding.widgetMovieCarousel.currentIndex]
		binding.widgetMovieRating.animateRating(movie.rating)
		binding.textViewDescription.text = movie.description
	}

	@Suppress("DEPRECATION")
	private fun changeStatusBarIconsToDark(value: Boolean) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			val decorView = requireActivity().window.decorView
			val oldFlags = decorView.systemUiVisibility

			var flags = oldFlags
			flags = if (value) {
				flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
			} else {
				flags and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
			}

			decorView.systemUiVisibility = flags
		}
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}
