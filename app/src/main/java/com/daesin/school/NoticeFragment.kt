package com.daesin.school

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.daesin.school.noticeAdapter.NoticeData
import com.daesin.school.noticeAdapter.NoticeAdapater
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jsoup.Jsoup

class NoticeFragment : Fragment() {
    //공지사항 객체 리스트
    val noticeList: ArrayList<NoticeData> = ArrayList()

    var NoticeTitleString = arrayOf("네트워크 연결 없음", "네트워크 연결 없음", "네트워크 연결 없음", "네트워크 연결 없음", "네트워크 연결 없음", "네트워크 연결 없음", "네트워크 연결 없음", "네트워크 연결 없음", "네트워크 연결 없음", "네트워크 연결 없음")
    var NoticeWriterString = arrayOf("NO NETWORK", "NO NETWORK", "NO NETWORK", "NO NETWORK", "NO NETWORK", "NO NETWORK", "NO NETWORK", "NO NETWORK", "NO NETWORK", "NO NETWORK")
    var NoticeDateString = arrayOf("", "", "", "", "", "", "", "", "", "")
    var NoticeUrlString = arrayOf("no", "no", "no", "no", "no", "no", "no", "no", "no", "no")
    var NoticeFileExists = arrayOf(false, false, false, false, false, false, false, false, false, false)
    var NoticeImportant = arrayOf(false, false, false, false, false, false, false, false, false, false)

    lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val holder = inflater.inflate(R.layout.fragment_notice, container, false)

        recyclerView = holder.findViewById(R.id.notice_view)
        parse()
        return holder
    }

    private fun parse() {
        GlobalScope.launch(Dispatchers.IO) {
            val jsoup = Jsoup.connect("http://school.busanedu.net/daesin-m/na/ntt/selectNttList.do?mi=618566&bbsId=1011229").get()
            val document = jsoup.select("tbody tr")
            for (tr in document) {
                noticeList.add(NoticeData(tr.select("td:nth-child(2)").text(),tr.select("td:nth-child(3)").text(), tr.select("td:nth-child(4)").text(), false))
            }

            activity?.runOnUiThread {
                recyclerView.layoutManager = LinearLayoutManager(requireContext())
                recyclerView.adapter = NoticeAdapater(noticeList)
            }
        }
    }

//    internal inner class NoticeAsyncTask : AsyncTask<Void?, Void?, Void?>() {
//        protected override fun doInBackground(vararg voids: Void): Void? {
//            try {
//                val response = Jsoup.connect("http://school.busanedu.net/daesin-m/na/ntt/selectNttList.do?mi=618566&bbsId=1011229")
//                        .method(Connection.Method.GET)
//                        .execute()
//                val document = response.parse()
//                val NoticeTitle = document.select("td.ta_l a")
//                val NoticeWriterDate = document.select(".board-text table tbody tr td")
//                for (i in 0..9) {
//                    /** Notice Title  */
//                    val notice = NoticeTitle[i]
//                    val TitleRes1 = notice.toString().split(">".toRegex()).toTypedArray()
//                    val TitleRes2 = TitleRes1[1].split("<".toRegex()).toTypedArray()
//                    if (TitleRes2[0].contains("&nbsp;")) {
//                        val TitleRes3 = TitleRes2[0].split("&nbsp;".toRegex()).toTypedArray()
//                        NoticeTitleString[i] = TitleRes3[1]
//                        NoticeImportant[i] = true
//                    } else {
//                        NoticeTitleString[i] = TitleRes2[0]
//                    }
//
//                    val TitleRes4 = TitleRes1[0].split("\"".toRegex()).toTypedArray()
//                    NoticeUrlString[i] = "http://school.busanedu.net" + TitleRes4[1]
//                    /** Notice Writer  */
//                    NoticeWriterString[i] = NoticeWriterDate[6 * i + 2].text()
//                    /** Notice Date  */
//                    NoticeDateString[i] = NoticeWriterDate[6 * i + 3].text()
//                    val temp = NoticeWriterDate[6 * i + 5].html()
//                    if (temp.contains("img src")) {
//                        NoticeFileExists[i] = true
//                    }
//                    println(i + NoticeTitleString[i] + NoticeUrlString[i] + NoticeWriterString[i] + NoticeDateString[i])
//                    println(NoticeFileExists[i])
//                }
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//            return null
//        }
//
//        override fun onPostExecute(aVoid: Void?) {
//            notice_title1!!.text = NoticeTitleString[0]
//            notice_title2!!.text = NoticeTitleString[1]
//            notice_title3!!.text = NoticeTitleString[2]
//            notice_title4!!.text = NoticeTitleString[3]
//            notice_title5!!.text = NoticeTitleString[4]
//            notice_title6!!.text = NoticeTitleString[5]
//            notice_title7!!.text = NoticeTitleString[6]
//            notice_title8!!.text = NoticeTitleString[7]
//            notice_title9!!.text = NoticeTitleString[8]
//            notice_title10!!.text = NoticeTitleString[9]
//            notice_writer1!!.text = NoticeWriterString[0]
//            notice_writer2!!.text = NoticeWriterString[1]
//            notice_writer3!!.text = NoticeWriterString[2]
//            notice_writer4!!.text = NoticeWriterString[3]
//            notice_writer5!!.text = NoticeWriterString[4]
//            notice_writer6!!.text = NoticeWriterString[5]
//            notice_writer7!!.text = NoticeWriterString[6]
//            notice_writer8!!.text = NoticeWriterString[7]
//            notice_writer9!!.text = NoticeWriterString[8]
//            notice_writer10!!.text = NoticeWriterString[9]
//            notice_date1!!.text = NoticeDateString[0]
//            notice_date2!!.text = NoticeDateString[1]
//            notice_date3!!.text = NoticeDateString[2]
//            notice_date4!!.text = NoticeDateString[3]
//            notice_date5!!.text = NoticeDateString[4]
//            notice_date6!!.text = NoticeDateString[5]
//            notice_date7!!.text = NoticeDateString[6]
//            notice_date8!!.text = NoticeDateString[7]
//            notice_date9!!.text = NoticeDateString[8]
//            notice_date10!!.text = NoticeDateString[9]
//            if (NoticeFileExists[0] == false) notice_file1!!.visibility = View.GONE
//            if (NoticeFileExists[1] == false) notice_file2!!.visibility = View.GONE
//            if (NoticeFileExists[2] == false) notice_file3!!.visibility = View.GONE
//            if (NoticeFileExists[3] == false) notice_file4!!.visibility = View.GONE
//            if (NoticeFileExists[4] == false) notice_file5!!.visibility = View.GONE
//            if (NoticeFileExists[5] == false) notice_file6!!.visibility = View.GONE
//            if (NoticeFileExists[6] == false) notice_file7!!.visibility = View.GONE
//            if (NoticeFileExists[7] == false) notice_file8!!.visibility = View.GONE
//            if (NoticeFileExists[8] == false) notice_file9!!.visibility = View.GONE
//            if (NoticeFileExists[9] == false) notice_file10!!.visibility = View.GONE
//            notice1!!.setOnClickListener { (activity as MainActivity?)!!.openUrl(NoticeUrlString[0]) }
//            notice2!!.setOnClickListener { (activity as MainActivity?)!!.openUrl(NoticeUrlString[1]) }
//            notice3!!.setOnClickListener { (activity as MainActivity?)!!.openUrl(NoticeUrlString[2]) }
//            notice4!!.setOnClickListener { (activity as MainActivity?)!!.openUrl(NoticeUrlString[3]) }
//            notice5!!.setOnClickListener { (activity as MainActivity?)!!.openUrl(NoticeUrlString[4]) }
//            notice6!!.setOnClickListener { (activity as MainActivity?)!!.openUrl(NoticeUrlString[5]) }
//            notice7!!.setOnClickListener { (activity as MainActivity?)!!.openUrl(NoticeUrlString[6]) }
//            notice8!!.setOnClickListener { (activity as MainActivity?)!!.openUrl(NoticeUrlString[7]) }
//            notice9!!.setOnClickListener { (activity as MainActivity?)!!.openUrl(NoticeUrlString[8]) }
//            notice10!!.setOnClickListener { (activity as MainActivity?)!!.openUrl(NoticeUrlString[9]) }
//            val sdk = Build.VERSION.SDK_INT
//            if (sdk < Build.VERSION_CODES.JELLY_BEAN) {
//                if (!NoticeImportant[0]) notice1!!.setBackgroundDrawable(ContextCompat.getDrawable(context!!, R.drawable.noticeimportant))
//                if (!NoticeImportant[1]) notice2!!.setBackgroundDrawable(ContextCompat.getDrawable(context!!, R.drawable.noticeimportant))
//                if (!NoticeImportant[2]) notice3!!.setBackgroundDrawable(ContextCompat.getDrawable(context!!, R.drawable.noticeimportant))
//                if (!NoticeImportant[3]) notice4!!.setBackgroundDrawable(ContextCompat.getDrawable(context!!, R.drawable.noticeimportant))
//                if (!NoticeImportant[4]) notice5!!.setBackgroundDrawable(ContextCompat.getDrawable(context!!, R.drawable.noticeimportant))
//                if (!NoticeImportant[5]) notice6!!.setBackgroundDrawable(ContextCompat.getDrawable(context!!, R.drawable.noticeimportant))
//                if (!NoticeImportant[6]) notice7!!.setBackgroundDrawable(ContextCompat.getDrawable(context!!, R.drawable.noticeimportant))
//                if (!NoticeImportant[7]) notice8!!.setBackgroundDrawable(ContextCompat.getDrawable(context!!, R.drawable.noticeimportant))
//                if (!NoticeImportant[8]) notice9!!.setBackgroundDrawable(ContextCompat.getDrawable(context!!, R.drawable.noticeimportant))
//                if (!NoticeImportant[9]) notice10!!.setBackgroundDrawable(ContextCompat.getDrawable(context!!, R.drawable.noticeimportant))
//            } else {
//                if (!NoticeImportant[0]) notice1!!.background = ContextCompat.getDrawable(context!!, R.drawable.noticeimportant)
//                if (!NoticeImportant[1]) notice2!!.background = ContextCompat.getDrawable(context!!, R.drawable.noticeimportant)
//                if (!NoticeImportant[2]) notice3!!.background = ContextCompat.getDrawable(context!!, R.drawable.noticeimportant)
//                if (!NoticeImportant[3]) notice4!!.background = ContextCompat.getDrawable(context!!, R.drawable.noticeimportant)
//                if (!NoticeImportant[4]) notice5!!.background = ContextCompat.getDrawable(context!!, R.drawable.noticeimportant)
//                if (!NoticeImportant[5]) notice6!!.background = ContextCompat.getDrawable(context!!, R.drawable.noticeimportant)
//                if (!NoticeImportant[6]) notice7!!.background = ContextCompat.getDrawable(context!!, R.drawable.noticeimportant)
//                if (!NoticeImportant[7]) notice8!!.background = ContextCompat.getDrawable(context!!, R.drawable.noticeimportant)
//                if (!NoticeImportant[8]) notice9!!.background = ContextCompat.getDrawable(context!!, R.drawable.noticeimportant)
//                if (!NoticeImportant[9]) notice10!!.background = ContextCompat.getDrawable(context!!, R.drawable.noticeimportant)
//            }
//        }
//    }
}