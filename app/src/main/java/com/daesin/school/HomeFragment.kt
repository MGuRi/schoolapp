package com.daesin.school

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.daesin.school.noticeAdapter.NoticeData
import com.daesin.school.simpleNoti.NotiAdapater
import com.daesin.school.simpleNoti.NotiData
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jsoup.Jsoup

class HomeFragment : Fragment() {
    //공지사항
    private var notice = ArrayList<NotiData>()
    //가정통신문
    private var letter = ArrayList<NotiData>()
    //칭찬합시다(변경예정이라 만들어지진않음)
    var praise = ArrayList<NotiData>()

    //공지사항
    private val noticeUrl = "http://school.busanedu.net/daesin-m/na/ntt/selectNttList.do?mi=618566&bbsId=1011229&listCo=5"
    //가정통신문
    private val letterUrl = "http://school.busanedu.net/daesin-m/na/ntt/selectNttList.do?mi=618567&bbsId=1011230&listCo=5"
    //칭찬합시다
    private val praiseUrl = "http://school.busanedu.net/daesin-m/main.do"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        parse()
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //공지사항 클릭
        home_alert.setOnClickListener { recyclerView.adapter = NotiAdapater(notice); change(home_alert) }
        //가정통신문 클릭
        letter_alert.setOnClickListener { recyclerView.adapter = NotiAdapater(letter); change(letter_alert) }
        //칭찬합시다
        praise_alert.setOnClickListener { recyclerView.adapter = NotiAdapater(praise); change(praise_alert) }
    }
    private fun parse() {
        GlobalScope.launch(Dispatchers.IO) {
            getList(noticeUrl, notice)
            getList(letterUrl, letter)
            //칭찬합시다 불러오는부분 오류나서 주석처리
//            val jsoup = Jsoup.connect(praiseUrl).get()
//            val doc = jsoup.select(".widgDiv notice1051 > div:nth-child(4)")
//            Log.d("noti", doc.html())
//            for (ele in doc) {
//                praise.add(NotiData(ele.select("a").text()))
//                Log.d("noti", ele.select("a").text())
//            }
            activity?.runOnUiThread {
                recyclerView.layoutManager = LinearLayoutManager(requireContext())
                recyclerView.adapter = NotiAdapater(notice)
            }
        }
    }

    //파싱url과 리스트넣으면 리스트에 파싱값을 넣어줌
    private fun getList(url: String, list: ArrayList<NotiData>) {
        val jsoup = Jsoup.connect(url).get()
        val doc = jsoup.select("tbody tr")

        for (tr in doc) {
            //공지가아닌것만 가져오기
            if (tr.select("td:nth-child(1)").text() != "공지")
                list.add(NotiData(title = tr.select("td:nth-child(2)").text()))
        }
    }

    //선택했을때 텍스트뷰 컬러변경
    private fun change(textView: TextView) {
        home_alert.setTextColor(resources.getColor(R.color.grey))
        letter_alert.setTextColor(resources.getColor(R.color.grey))
        praise_alert.setTextColor(resources.getColor(R.color.grey))
        textView.setTextColor(resources.getColor(R.color.colorPrimaryDark))
    }
}