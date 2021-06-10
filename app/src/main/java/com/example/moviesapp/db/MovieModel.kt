package com.example.moviesapp.db

import androidx.annotation.DrawableRes
import com.example.moviesapp.R

/**
 * The example global movies DB.
 */
val MOVIES: List<Movie> = listOf(
	Movie(
		id = 0,
		title = "Blade Runner 2049",
		subtitle = "Coming in 21.01.18",
		description = "Officer K, a new blade runner for the Los Angeles Police Department, unearths a long-buried secret that has the potential to plunge what's left of society into chaos.",
		poster = R.drawable.img_blade_runner,
		rating = 4
	),
	Movie(
		id = 1,
		title = "Justice League",
		subtitle = "Coming in 17.12.20",
		description = "Fueled by his restored faith in humanity and inspired by Superman's selfless act, Bruce Wayne enlists the help of his new-found ally, to face an even greater enemy.",
		poster = R.drawable.img_justice_league,
		rating = 3
	),
	Movie(
		id = 2,
		title = "Project Power",
		subtitle = "Coming in 01.01.21",
		description = "In near-future New Orleans, a mysterious distributor offers a free supply of “Power”–a pill that grants various superpowers for five minutes–to a group of drug dealers.",
		poster = R.drawable.img_project_power,
		rating = 1
	),
	Movie(
		id = 3,
		title = "Joker",
		subtitle = "Coming in 12.08.19",
		description = "The Joker is a fictional character who appears in Christopher Nolan's 2008 superhero film The Dark Knight, based upon the DC character and supervillain of the same name.",
		poster = R.drawable.img_joker,
		rating = 4
	),
	Movie(
		id = 4,
		title = "Taxi Driver",
		subtitle = "Coming in 31.05.99",
		description = "Travis Bickle is a 26-year-old honorably discharged U.S. Marine and Vietnam War veteran suffering from post-traumatic stress disorder and living in isolation in NY City.",
		poster = R.drawable.img_taxi_driver,
		rating = 0
	)
)

/**
 * Movie model.
 *
 * @property id the id.
 * @property title the title.
 * @property subtitle the subtitle.
 * @property description the description.
 * @property poster the poster drawable resource.
 * @property rating the rating in range from 0 to 4.
 */
data class Movie(
	val id: Int,
	val title: String,
	val subtitle: String,
	val description: String,
	@DrawableRes
	val poster: Int,
	val rating: Int
)
