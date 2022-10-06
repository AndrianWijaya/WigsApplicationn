package com.iglo.wigsapplicationn.activity.product


import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ActionMode
import androidx.databinding.DataBindingUtil
import com.iglo.common.helper.clear
import com.iglo.common.helper.isEmpty
import com.iglo.common.helper.size
import com.iglo.common.helper.toggle
import com.iglo.wigsapplicationn.BR
import com.iglo.wigsapplicationn.R
import com.iglo.wigsapplicationn.databinding.ListProductLayoutBinding
import com.iglo.wigsapplicationn.view_model.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Product: AppCompatActivity() {
    val layoutResourceId = R.layout.list_product_layout
    lateinit var binding: ListProductLayoutBinding

    val vm: ProductViewModel by viewModels()

    val adapter = ProductAdapter(this,::startActionMode, {
        vm.selection.value.orEmpty()
    }) {
        toggleClick(it)
    }

    var actionMode: ActionMode? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(this), layoutResourceId, null, false
        )
        binding.lifecycleOwner = this
        setContentView(binding.root)
        binding.setVariable(BR.vm, vm)

        binding.recycler.adapter = adapter

        vm.dataProduct.observe(this@Product){
            adapter.submitData(it.toList())
        }

        vm.selection.observe(this@Product) {
            actionMode ?: kotlin.run {
                actionMode = startSupportActionMode(object : ActionMode.Callback {
                    override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                        return true
                    }

                    override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                        return true
                    }

                    override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
                        return true
                    }

                    override fun onDestroyActionMode(mode: ActionMode?) {
                        val sel = vm.selection
                        if (!sel.isEmpty()) {
                            adapter.clearSelection { vm.selection.clear() }
                        }
                        actionMode = null
                    }

                })
            }
            actionMode?.title = "${vm.selection.size()} selected"
            if (it.isEmpty()) {
                actionMode?.finish()
                actionMode = null
            }
        }
    }
    fun startActionMode(product: com.iglo.common.entity.Product) {
        adapter.toggleSelection(product) {
            vm.selection.toggle(product)
        }
    }

    fun toggleClick(product: com.iglo.common.entity.Product) {
        adapter.toggleSelection(product) {
            vm.selection.toggle(product)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        actionMode = null
    }

}


