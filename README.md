# Movies. App

`Movies.` is the sample preview app of the `MotionLayout` experiments.  
This app realizes the [InVision Studio — Movies app concept](https://dribbble.com/shots/3982621-InVision-Studio-Movies-app-concept). 

## Result

| Design | Code |
|-|-|
| ![](/media/movies_design.gif) | ![](/media/movies_code.gif) |

## Features

- Advanced animation examples by only using the `ConstraintLayout` and `MotionLayout` APIs.   
- The nested `MotionLayout`s are decorated as `CustomViews`. It is possible to have multiple `MotionLayout`s on 
  the same screen and manipulate them in your own manner.
- The multiple `Carousel`s relations and progress transitions examples.
- The examples of switching and propagating the progress between the transitions programmatically.
- Great staggered animation example and its REAL usage.

## MotionLayout Advantages:

- Animates the default scenarios easily and with the advanced preview/editor.
- Possibility to decouple `MotionLayout`s into the nested ones (f.e. `CustomView`).
- The `ImageFilterView` is kinda of a cherry on top, across the `MotionLayout` helpers.
- Possibility to run different and nested `MotionLayout`s transitions simultaneously.   
- Decouples the animation stuff from the code to the motion scene file.  
- Ability to transition the custom views and their attributes.
- Staggered animations and their manipulation.

## MotionLayout Disadvantages:

- The advanced animation cases currently hard to achieve, especially with the multitouch.
- The `Carousel` does not provide a quick solution, it is better to use `ViewPager` or `RecyclerView`.
- The `MotionLabel` is BUGGY/artifacty inside the transition and does not support the custom fonts.
- The multitouch support requires a lot of improvements. It is not possible to properly have vertical/horizontal swipe 
  and clicks simultaneously.
- And again, `Carousel` with the swipe and click, is the functionality we lack.
- The `NestedScrollView`/nested scroll inside the transition is not working. It is better to use `AppBar`/
  `CollapsingToolbar`.  
- Sometimes `MotionLayout` draws some artifacts on quick transitions switch. 
- Editor does not invalidate the cache, and it forces reloading Android Studio frequently.
- The `ConstraintSet`/`Constraint` overrides sometimes does not work properly, so it forces to rewrite the whole 
  `Constraint` from the original state.   
- Lack of functionality to properly set/switch between the transitions. Right now it works the best when you have one 
  scene with one transition.
- It is not possible to extend the MotionLayout as the `CustomView`, because there is not such a method to set the scene
  programmatically. It forces to wrap the MotionLayout into the `FrameLayout` or other.
- It still in beta, so it requires the last beta/alpha Android Studio build.
- Lack of the possibility to chain/reference multiple/nested `MotionLayout`s. For example, if you want to animate the 
  main transition and with it propagate the animation state to the others.

## Summary

The `MotionLayout` is an amazing solution for simple and advanced screen animations. It works well for the single
transition to the end and back, but when there are multiple transitions the problems could occur. It has the handy 
scene editor under the hood, so it is useful to test transitions inside the Android Studio. The other advantage of the 
`MotionLayout`s is the ability to decouple them into the `CustomView`s. Also, `MotionLayout` supports touch events, like
click and swipe, but I would not recommend them right now, because it still buggy, especially inside the `Carousel`. The
following helpers `Carousel` and `MotionLabel` are not worth it for now, because the API is not completed yet and 
they contain some real issues. On the other hand, the `ImageFilterView` and `MotionEffect` do the job properly. 
In my opinion, `MotionLayout` should be used for some stunning screen animations, where no touch events included, for 
example splash screen, tutorial screen, or dashboard screen.

## Environment

This sample app uses the following `ConstrainLayout` version:  
```groovy
dependencies {
    implementation("androidx.constraintlayout:constraintlayout:2.1.0-beta02")
}
```

Also, this sample app requires a [Android Studio Beta - Arctic Fox](https://developer.android.com/studio/preview).

## Credits

Special thanks to the [Charles Patterson](https://dribbble.com/CharlesPatterson). 

## Author:

[Basil Miller](https://www.linkedin.com/in/gigamole/)  
[gigamole53@gmail.com](mailto:gigamole53@gmail.com)
