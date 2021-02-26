package com.example.askproject.presentation.home

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.Point
import android.graphics.Rect
import android.graphics.RectF
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.askproject.R
import com.example.askproject.domain.home.Content
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.android.synthetic.main.home_item_holder.*

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    //    var secondsInHour = 86400
    var secondsInHour = 60
    private lateinit var viewModel: HomeViewModel
    private val homeAdapter = PostAdapter(
        onItemClicked = {},
        onImageClicked = {
            zoomImageFromThumb(postImageView, Uri.parse("https://velvet-sheep.com/wp-content/uploads/2017/04/C3reXRdWcAADCiX.jpg"))
        }
//            val direction = KhazanahListFragmentDirections.actionToKhazanahDetailFragment(it)
//            findNavController().navigate(direction)
    )
    private var list = mutableListOf<Content>()
    var liveList = MutableLiveData<List<Content>>()
    var datas1 = MutableLiveData<Content>()
    var datas2 = MutableLiveData<Content>()

    var dur1 = MutableLiveData<Double>()
    var dur2 = MutableLiveData<Double>()

    var isRemove = false

    // Hold a reference to the current animator,
    // so that it can be canceled mid-way.
    private var currentAnimator: Animator? = null

    // The system "short" animation time duration, in milliseconds. This
    // duration is ideal for subtle animations or animations that occur
    // very frequently.
    private var shortAnimationDuration: Int = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    //custom method to calculate progress
    fun calculateProgress(currentDuration: Double): Double {
        var latestProgress: Double = 100.0
        val pengurangProgress = 100.0 / secondsInHour
        var latestDuration = secondsInHour - currentDuration
        latestProgress -= (pengurangProgress * latestDuration)
        return latestProgress
    }

    //custom method to zoom image on click
    private fun zoomImageFromThumb(thumbView: View, imageResId: Uri) {
        // If there's an animation in progress, cancel it
        // immediately and proceed with this one.
        currentAnimator?.cancel()

        // Load the high-resolution "zoomed-in" image.
        val expandedImageView = expandedImgView
//        expandedImageView.setImageResource(imageResId)
        Glide.with(this)
            .load(imageResId)
            .into(expandedImageView)

        // Calculate the starting and ending bounds for the zoomed-in image.
        // This step involves lots of math. Yay, math.
        val startBoundsInt = Rect()
        val finalBoundsInt = Rect()
        val globalOffset = Point()

        // The start bounds are the global visible rectangle of the thumbnail,
        // and the final bounds are the global visible rectangle of the container
        // view. Also set the container view's offset as the origin for the
        // bounds, since that's the origin for the positioning animation
        // properties (X, Y).
        thumbView.getGlobalVisibleRect(startBoundsInt)
        homeContainer
            .getGlobalVisibleRect(finalBoundsInt, globalOffset)
        startBoundsInt.offset(-globalOffset.x, -globalOffset.y)
        finalBoundsInt.offset(-globalOffset.x, -globalOffset.y)

        val startBounds = RectF(startBoundsInt)
        val finalBounds = RectF(finalBoundsInt)

        // Adjust the start bounds to be the same aspect ratio as the final
        // bounds using the "center crop" technique. This prevents undesirable
        // stretching during the animation. Also calculate the start scaling
        // factor (the end scaling factor is always 1.0).
        val startScale: Float
        if ((finalBounds.width() / finalBounds.height() > startBounds.width() / startBounds.height())) {
            // Extend start bounds horizontally
            startScale = startBounds.height() / finalBounds.height()
            val startWidth: Float = startScale * finalBounds.width()
            val deltaWidth: Float = (startWidth - startBounds.width()) / 2
            startBounds.left -= deltaWidth.toInt()
            startBounds.right += deltaWidth.toInt()
        } else {
            // Extend start bounds vertically
            startScale = startBounds.width() / finalBounds.width()
            val startHeight: Float = startScale * finalBounds.height()
            val deltaHeight: Float = (startHeight - startBounds.height()) / 2f
            startBounds.top -= deltaHeight.toInt()
            startBounds.bottom += deltaHeight.toInt()
        }

        // Hide the thumbnail and show the zoomed-in view. When the animation
        // begins, it will position the zoomed-in view in the place of the
        // thumbnail.
        thumbView.alpha = 0f
        expandedImageView.visibility = View.VISIBLE

        // Set the pivot point for SCALE_X and SCALE_Y transformations
        // to the top-left corner of the zoomed-in view (the default
        // is the center of the view).
        expandedImageView.pivotX = 0f
        expandedImageView.pivotY = 0f

        // Construct and run the parallel animation of the four translation and
        // scale properties (X, Y, SCALE_X, and SCALE_Y).
        currentAnimator = AnimatorSet().apply {
            play(
                ObjectAnimator.ofFloat(
                    expandedImageView,
                    View.X,
                    startBounds.left,
                    finalBounds.left
                )
            ).apply {
                with(
                    ObjectAnimator.ofFloat(
                        expandedImageView,
                        View.Y,
                        startBounds.top,
                        finalBounds.top
                    )
                )
                with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_X, startScale, 1f))
                with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_Y, startScale, 1f))
            }
            duration = shortAnimationDuration.toLong()
            interpolator = DecelerateInterpolator()
            addListener(object : AnimatorListenerAdapter() {

                override fun onAnimationEnd(animation: Animator) {
                    currentAnimator = null
                }

                override fun onAnimationCancel(animation: Animator) {
                    currentAnimator = null
                }
            })
            start()
        }

        // Upon clicking the zoomed-in image, it should zoom back down
        // to the original bounds and show the thumbnail instead of
        // the expanded image.
        expandedImageView.setOnClickListener {
            currentAnimator?.cancel()

            // Animate the four positioning/sizing properties in parallel,
            // back to their original values.
            currentAnimator = AnimatorSet().apply {
                play(ObjectAnimator.ofFloat(expandedImageView, View.X, startBounds.left)).apply {
                    with(ObjectAnimator.ofFloat(expandedImageView, View.Y, startBounds.top))
                    with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_X, startScale))
                    with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_Y, startScale))
                }
                duration = shortAnimationDuration.toLong()
                interpolator = DecelerateInterpolator()
                addListener(object : AnimatorListenerAdapter() {

                    override fun onAnimationEnd(animation: Animator) {
                        thumbView.alpha = 1f
                        expandedImageView.visibility = View.GONE
                        currentAnimator = null
                    }

                    override fun onAnimationCancel(animation: Animator) {
                        thumbView.alpha = 1f
                        expandedImageView.visibility = View.GONE
                        currentAnimator = null
                    }
                })
                start()
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        // TODO: Use the ViewModel
        dur1.postValue(10.0)
        dur2.postValue(60.0)

//        list.add(Content(0, "Heru Apriadi", "Halo aku bingung mau tulis apa", ""))
//        list.add(Content(1, "Ricky Irandi", "aku juga nihh.. apaa yaaa hmmmm", ""))
//        list.add(Content(2, "Amelia Murray", "Halo aku bingung mau tulis apa", "", 86400000))
//        list.add(Content(3, "Amelia Murray", "Halo aku bingung mau tulis apa lagi", "", 100))
//        list.add(Content(4, "Amelia Murray", "Halo aku bingung mau tulis apa", "", 86400000))
//        list.add(Content(5, "Amelia Murray", "Halo aku bingung mau tulis apa", "", 86400000))
//        list.add(Content(6, "Amelia Murray", "Halo aku bingung mau tulis apa", "", 86400000))
//        list.add(Content(7, "Amelia Murray", "Halo aku bingung mau tulis apa", "", 86400000))
//        list.add(Content(8, "Amelia Murray", "Halo aku bingung mau tulis apa", "", 86400000))
//        list.add(Content(9, "Amelia Murray", "Halo aku bingung mau tulis apa LAST", "", 86400000))
//        list.add(Content(10, "", "", "", 100000))


        with(recyclerView) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = homeAdapter
        }

//        logoutButton.setOnClickListener {
//            startActivity(
//                Intent(requireActivity(), AuthActivity::class.java),
//                ActivityOptions.makeSceneTransitionAnimation(requireActivity()).toBundle()
//            )
//            requireActivity().finish()
//        }
    }

    override fun onStart() {
        super.onStart()
        //countdown
        object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                dur1.postValue(dur1.value!! - 1)
                dur2.postValue(dur2.value!! - 1)
            }

            override fun onFinish() {
            }
        }.start()

        datas1.observe(requireActivity(), Observer {
            if (it != null) {
                list.clear()
                if (it.progress < 1) {
                    list.remove(it)
                    if (!isRemove) {
                        homeAdapter.removeItem(0)
                        isRemove = true
                    }
                } else {
                    list.add(it)
                    homeAdapter.insertItem()
                }
                if (datas2.value != null) {
                    list.add(datas2.value!!)
                    list.add(datas2.value!!)
                    homeAdapter.insertItem()
                }
                homeAdapter.addItems(list)
            }
        })

//        datas2.observe(requireActivity(), Observer {
//            if (it != null) {
//                list.clear()
//
//                homeAdapter.addItems(list)
//            }
//        })

        dur1.observe(requireActivity(), {
            if (it != null) {
                datas1.postValue(
                    Content(
                        0,
                        "Amelia Murray",
                        "Halo aku bingung mau tulis apa",
                        "",
                        it.toLong(),
                        calculateProgress(it)
                    )
                )

            }
        })

        dur2.observe(requireActivity(), {
            if (it != null) {
                datas2.postValue(
                    Content(
                        1,
                        "Amelia Murray",
                        "Halo aku bingung mau tulis apa jug",
                        "",
                        it.toLong(),
                        calculateProgress(it)
                    )
                )
            }
        })

    }
}