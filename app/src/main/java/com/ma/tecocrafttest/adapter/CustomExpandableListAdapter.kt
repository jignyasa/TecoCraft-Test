package com.ma.tecocrafttest.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import com.ma.tecocrafttest.R
import kotlinx.android.synthetic.main.lay_category.view.*
import kotlinx.android.synthetic.main.lay_sub_category.view.*

class CustomExpandableListAdapter(val context:Context) : BaseExpandableListAdapter() {
   private var alTitleCategory:ArrayList<String>
   private var hmSubCategoryDetail:HashMap<String,List<String>>
    init{
        alTitleCategory= ArrayList()
        hmSubCategoryDetail= HashMap()
    }
    fun addData(alTitle:ArrayList<String>,hmDetails:HashMap<String,List<String>>)
    {
        alTitleCategory=alTitle
        hmSubCategoryDetail=hmDetails
        notifyDataSetChanged()
    }
    override fun getGroup(groupPosition: Int): Any {
        return alTitleCategory.get(groupPosition)
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return false
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val view=LayoutInflater.from(context).inflate(R.layout.lay_category,parent,false)
        view.tvCategory.text=alTitleCategory.get(groupPosition)
        if(hmSubCategoryDetail.get(alTitleCategory.get(groupPosition))!!.size>0) {
            view.ivArrow.visibility = View.VISIBLE
        }
        else
            view.ivArrow.visibility=View.GONE
        if(isExpanded)
            view.ivArrow.setImageResource(R.drawable.ic_up_arrow)
        else
            view.ivArrow.setImageResource(R.drawable.ic_download)
        return view
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return hmSubCategoryDetail.get(alTitleCategory.get(groupPosition))!!.size
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return hmSubCategoryDetail.get(alTitleCategory.get(groupPosition))!!.get(childPosition)
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
       val childView=LayoutInflater.from(context).inflate(R.layout.lay_sub_category,parent,false)
        childView.tvSubCategory.text=hmSubCategoryDetail.get(alTitleCategory.get(groupPosition))!!.get(childPosition)
        return childView
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun getGroupCount(): Int {
        return alTitleCategory.size
    }

}