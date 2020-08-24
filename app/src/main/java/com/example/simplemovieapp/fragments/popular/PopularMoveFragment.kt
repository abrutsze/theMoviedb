package com.example.simplemovieapp.fragments.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.simplemovieapp.R
import com.example.simplemovieapp.fragments.moviedetail.DetailMoveFragment
import com.example.simplemovieapp.fragments.popular.adapter.PopularMoveAdapter
import com.example.simplemovieapp.fragments.popular.viewmodel.PopularMoveViewModel
import com.example.simplemovieapp.supportclass.PaginationScrollListener
import kotlinx.android.synthetic.main.fragment_popular_move.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PopularMoveFragment : Fragment() {
    private lateinit var popularMoveAdapter: PopularMoveAdapter
    private val popularMoveViewModel: PopularMoveViewModel by viewModel()
    var isLastPage: Boolean = false
    var isLoading: Boolean = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_popular_move, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFragmentView()
        initViewModel()
    }

    private fun initFragmentView() {
        popularMoveAdapter = PopularMoveAdapter(mutableListOf()) { detailMoveId ->
            val specialtyFragment = DetailMoveFragment.newInstance(detailMoveId)
            val fragment = activity?.supportFragmentManager?.beginTransaction()
            fragment?.replace(R.id.rootFragment, specialtyFragment)
            fragment?.addToBackStack(null)
            fragment?.commit()
        }
        rvPopular.setHasFixedSize(true)
        rvPopular.adapter = popularMoveAdapter
        val layoutManager = GridLayoutManager(context, 2)
        rvPopular.layoutManager = layoutManager
        rvPopular.addOnScrollListener(object : PaginationScrollListener(layoutManager) {
            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

            override fun loadMoreItems() {
                isLoading = true
                getMorePopularItemData()
            }
        })
    }

    private fun getMorePopularItemData() {
        popularMoveViewModel.getPopularMoveList()
        vLoadingPaginition.visibility = View.VISIBLE
    }

    private fun initViewModel() {

        popularMoveViewModel.getPopularMoveList.observe(viewLifecycleOwner, Observer {

            popularMoveAdapter.updateList(it)
        })

        popularMoveViewModel.errorNullData.observe(viewLifecycleOwner, Observer {
            context?.run {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        })
        popularMoveViewModel.loadingData.observe(viewLifecycleOwner, Observer { isVisible ->
            if (isVisible) {
                vLoadingData.visibility = View.VISIBLE
            } else {
                vLoadingData.visibility = View.GONE
            }
        })
        popularMoveViewModel.finishPaginition.observe(
            viewLifecycleOwner,
            Observer { isFinishPaginition ->
                if (isFinishPaginition) {
                    vLoadingPaginition.visibility = View.VISIBLE
                    isLoading = true
                    isLastPage = true
                } else {
                    vLoadingPaginition.visibility = View.GONE
                    isLoading = false

                }
            })

    }
}