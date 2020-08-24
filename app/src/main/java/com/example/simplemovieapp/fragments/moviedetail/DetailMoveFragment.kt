package com.example.simplemovieapp.fragments.moviedetail

import androidx.lifecycle.Observer
import com.example.entities.localmodels.DetailMove
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.entities.localmodels.TopCast
import com.example.simplemovieapp.R
import com.example.simplemovieapp.fragments.moviedetail.adapter.TopCastAdapter
import com.example.simplemovieapp.fragments.moviedetail.viewmodel.DetailMoveViewModel
import com.example.simplemovieapp.utils.convertDurationToHour
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_detail.vLoadingData


import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailMoveFragment : Fragment() {

    private val detailMoveViewModel: DetailMoveViewModel by viewModel()
    private var moveId: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            moveId = it.getInt(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initData()
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    private fun initData() {
        detailMoveViewModel.getDetailData(moveId)
        detailMoveViewModel.getTopCastData(moveId)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
    }

    private fun initViewModel() {
        detailMoveViewModel.getDetailMoveData.observe(
            viewLifecycleOwner,
            Observer(::initDetailDataView)
        )
        detailMoveViewModel.getTopCastMoveData.observe(
            viewLifecycleOwner,
            Observer(::initTopCastDataView)
        )
        detailMoveViewModel.errorNullData.observe(viewLifecycleOwner, Observer {
            context?.run {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        })
        detailMoveViewModel.loadingData.observe(viewLifecycleOwner, Observer { isVisible->
            if(isVisible){
                vLoadingData.visibility=View.VISIBLE
            }else{
                vLoadingData.visibility=View.GONE
            }
        })
    }

    private fun initDetailDataView(detailMove: DetailMove) {
        vDetailMoveName.text = detailMove.title?.run { this }
        vMoveReleaseDate.text = detailMove.releaseDate?.run { getString(R.string.release) + this }
        var genreNames = ""
        detailMove.genres?.run {
            forEach {
                genreNames = genreNames + it.name + ", "
            }
        }
        vMoveGenre.text = genreNames
        val rate = detailMove.voteCount?.div(detailMove.voteAverage!!)?.toInt()
        rate?.run {
            pgRateCount.progress = this
            vRateCount.text = this.toString()
        }
        vOverViewText.text = detailMove.overview?.run { this }
        vMoveDuration.text = detailMove.runtime?.run { convertDurationToHour(this) }
        detailMove.posterPath?.let {
            context?.run {
                Glide.with(this)
                    .load(it)
                    .into(vDetailItemPhoto)
            }
        }
        detailMove.backdropPath?.let {
            context?.run {
                Glide.with(this)
                    .load(it)
                    .into(vDetailItemCover)
            }
        }
    }

    private fun initTopCastDataView(topCast: TopCast) {
        topCast.cast?.run {
            rvTopCast.setHasFixedSize(true)
            rvTopCast.adapter = TopCastAdapter(this)
        }

    }

    companion object {
        private const val ARG_PARAM1 = "moveId"

        @JvmStatic
        fun newInstance(param1: Int) =
            DetailMoveFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)
                }
            }
    }
}




