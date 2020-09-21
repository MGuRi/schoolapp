package com.daesin.school;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.daesin.school.R;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class NoticeFragment extends Fragment {
    String NoticeTitleString[] = {"네트워크 연결 없음","네트워크 연결 없음","네트워크 연결 없음","네트워크 연결 없음","네트워크 연결 없음","네트워크 연결 없음","네트워크 연결 없음","네트워크 연결 없음","네트워크 연결 없음","네트워크 연결 없음"};
    String NoticeWriterString[] = {"NO NETWORK","NO NETWORK","NO NETWORK","NO NETWORK","NO NETWORK","NO NETWORK","NO NETWORK","NO NETWORK","NO NETWORK","NO NETWORK"};
    String NoticeDateString[] = {"","","","","","","","","",""};
    String NoticeUrlString[] = {"no","no","no","no","no","no","no","no","no","no"};
    Boolean NoticeFileExists[] = {false, false, false, false, false, false, false, false, false, false};
    Boolean NoticeImportant[] = {false, false, false, false, false, false, false, false, false, false};

    LinearLayout notice1, notice2, notice3, notice4, notice5, notice6, notice7, notice8, notice9, notice10;
    TextView notice_title1, notice_title2, notice_title3, notice_title4, notice_title5, notice_title6, notice_title7, notice_title8, notice_title9, notice_title10;
    TextView notice_writer1, notice_writer2, notice_writer3, notice_writer4, notice_writer5, notice_writer6, notice_writer7, notice_writer8, notice_writer9, notice_writer10;
    TextView notice_date1, notice_date2, notice_date3, notice_date4, notice_date5, notice_date6, notice_date7, notice_date8, notice_date9, notice_date10;
    ImageView notice_file1, notice_file2, notice_file3, notice_file4, notice_file5, notice_file6, notice_file7, notice_file8, notice_file9, notice_file10;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notice, container, false);

        notice1 = view.findViewById(R.id.noticeFragment_notice1);
        notice2 = view.findViewById(R.id.noticeFragment_notice2);
        notice3 = view.findViewById(R.id.noticeFragment_notice3);
        notice4 = view.findViewById(R.id.noticeFragment_notice4);
        notice5 = view.findViewById(R.id.noticeFragment_notice5);
        notice6 = view.findViewById(R.id.noticeFragment_notice6);
        notice7 = view.findViewById(R.id.noticeFragment_notice7);
        notice8 = view.findViewById(R.id.noticeFragment_notice8);
        notice9 = view.findViewById(R.id.noticeFragment_notice9);
        notice10 = view.findViewById(R.id.noticeFragment_notice10);

        notice_title1 = view.findViewById(R.id.noticeFragment_notice1_title);
        notice_title2 = view.findViewById(R.id.noticeFragment_notice2_title);
        notice_title3 = view.findViewById(R.id.noticeFragment_notice3_title);
        notice_title4 = view.findViewById(R.id.noticeFragment_notice4_title);
        notice_title5 = view.findViewById(R.id.noticeFragment_notice5_title);
        notice_title6 = view.findViewById(R.id.noticeFragment_notice6_title);
        notice_title7 = view.findViewById(R.id.noticeFragment_notice7_title);
        notice_title8 = view.findViewById(R.id.noticeFragment_notice8_title);
        notice_title9 = view.findViewById(R.id.noticeFragment_notice9_title);
        notice_title10 = view.findViewById(R.id.noticeFragment_notice10_title);

        notice_writer1 = view.findViewById(R.id.noticeFragment_notice1_writer);
        notice_writer2 = view.findViewById(R.id.noticeFragment_notice2_writer);
        notice_writer3 = view.findViewById(R.id.noticeFragment_notice3_writer);
        notice_writer4 = view.findViewById(R.id.noticeFragment_notice4_writer);
        notice_writer5 = view.findViewById(R.id.noticeFragment_notice5_writer);
        notice_writer6 = view.findViewById(R.id.noticeFragment_notice6_writer);
        notice_writer7 = view.findViewById(R.id.noticeFragment_notice7_writer);
        notice_writer8 = view.findViewById(R.id.noticeFragment_notice8_writer);
        notice_writer9 = view.findViewById(R.id.noticeFragment_notice9_writer);
        notice_writer10 = view.findViewById(R.id.noticeFragment_notice10_writer);

        notice_date1 = view.findViewById(R.id.noticeFragment_notice1_date);
        notice_date2 = view.findViewById(R.id.noticeFragment_notice2_date);
        notice_date3 = view.findViewById(R.id.noticeFragment_notice3_date);
        notice_date4 = view.findViewById(R.id.noticeFragment_notice4_date);
        notice_date5 = view.findViewById(R.id.noticeFragment_notice5_date);
        notice_date6 = view.findViewById(R.id.noticeFragment_notice6_date);
        notice_date7 = view.findViewById(R.id.noticeFragment_notice7_date);
        notice_date8 = view.findViewById(R.id.noticeFragment_notice8_date);
        notice_date9 = view.findViewById(R.id.noticeFragment_notice9_date);
        notice_date10 = view.findViewById(R.id.noticeFragment_notice10_date);

        notice_file1 = view.findViewById(R.id.noticeFragment_notice1_file);
        notice_file2 = view.findViewById(R.id.noticeFragment_notice2_file);
        notice_file3 = view.findViewById(R.id.noticeFragment_notice3_file);
        notice_file4 = view.findViewById(R.id.noticeFragment_notice4_file);
        notice_file5 = view.findViewById(R.id.noticeFragment_notice5_file);
        notice_file6 = view.findViewById(R.id.noticeFragment_notice6_file);
        notice_file7 = view.findViewById(R.id.noticeFragment_notice7_file);
        notice_file8 = view.findViewById(R.id.noticeFragment_notice8_file);
        notice_file9 = view.findViewById(R.id.noticeFragment_notice9_file);
        notice_file10 = view.findViewById(R.id.noticeFragment_notice10_file);

        NoticeAsyncTask task = new NoticeAsyncTask();
        task.execute();

        return view;
    }

    class NoticeAsyncTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Connection.Response response = Jsoup.connect("http://school.busanedu.net/daesin-m/na/ntt/selectNttList.do?mi=618566&bbsId=1011229")
                        .method(Connection.Method.GET)
                        .execute();
                Document document = response.parse();

                Elements NoticeTitle = document.select("td.ta_l a");
                Elements NoticeWriterDate = document.select(".board-text table tbody tr td");

                for (int i = 0; i < 10; i++) {
                    /** Notice Title **/
                    Element notice = NoticeTitle.get(i);
                    String[] TitleRes1 = notice.toString().split(">");
                    String[] TitleRes2 = TitleRes1[1].split("<");
                    if (TitleRes2[0].contains("&nbsp;")) {
                        String[] TitleRes3 = TitleRes2[0].split("&nbsp;");
                        NoticeTitleString[i] = TitleRes3[1];
                        NoticeImportant[i] = true;
                    } else {
                        NoticeTitleString[i] = TitleRes2[0];
                    }
//                    if (NoticeTitleString[i].length() >= 30) {
//                        NoticeTitleString[i] = NoticeTitleString[i].substring(0, 27);
//                        NoticeTitleString[i] += "...";
//                    }
                    /** Notice Link **/
                    String[] TitleRes4 = TitleRes1[0].split("\"");
                    NoticeUrlString[i] = "http://school.busanedu.net" + TitleRes4[1];

                    /** Notice Writer **/
                    NoticeWriterString[i] = NoticeWriterDate.get(6 * i + 2).text();

                    /** Notice Date **/
                    NoticeDateString[i] = NoticeWriterDate.get(6 * i + 3).text();

                    String temp = NoticeWriterDate.get(6 * i + 5).html();
                    if (temp.contains("img src")) {
                        NoticeFileExists[i] = true;
                    }
                    System.out.println(i + NoticeTitleString[i] + NoticeUrlString[i] + NoticeWriterString[i] + NoticeDateString[i]);
                    System.out.println(NoticeFileExists[i]);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            notice_title1.setText(NoticeTitleString[0]);
            notice_title2.setText(NoticeTitleString[1]);
            notice_title3.setText(NoticeTitleString[2]);
            notice_title4.setText(NoticeTitleString[3]);
            notice_title5.setText(NoticeTitleString[4]);
            notice_title6.setText(NoticeTitleString[5]);
            notice_title7.setText(NoticeTitleString[6]);
            notice_title8.setText(NoticeTitleString[7]);
            notice_title9.setText(NoticeTitleString[8]);
            notice_title10.setText(NoticeTitleString[9]);

            notice_writer1.setText(NoticeWriterString[0]);
            notice_writer2.setText(NoticeWriterString[1]);
            notice_writer3.setText(NoticeWriterString[2]);
            notice_writer4.setText(NoticeWriterString[3]);
            notice_writer5.setText(NoticeWriterString[4]);
            notice_writer6.setText(NoticeWriterString[5]);
            notice_writer7.setText(NoticeWriterString[6]);
            notice_writer8.setText(NoticeWriterString[7]);
            notice_writer9.setText(NoticeWriterString[8]);
            notice_writer10.setText(NoticeWriterString[9]);

            notice_date1.setText(NoticeDateString[0]);
            notice_date2.setText(NoticeDateString[1]);
            notice_date3.setText(NoticeDateString[2]);
            notice_date4.setText(NoticeDateString[3]);
            notice_date5.setText(NoticeDateString[4]);
            notice_date6.setText(NoticeDateString[5]);
            notice_date7.setText(NoticeDateString[6]);
            notice_date8.setText(NoticeDateString[7]);
            notice_date9.setText(NoticeDateString[8]);
            notice_date10.setText(NoticeDateString[9]);

            if (NoticeFileExists[0] == false) notice_file1.setVisibility(View.GONE);
            if (NoticeFileExists[1] == false) notice_file2.setVisibility(View.GONE);
            if (NoticeFileExists[2] == false) notice_file3.setVisibility(View.GONE);
            if (NoticeFileExists[3] == false) notice_file4.setVisibility(View.GONE);
            if (NoticeFileExists[4] == false) notice_file5.setVisibility(View.GONE);
            if (NoticeFileExists[5] == false) notice_file6.setVisibility(View.GONE);
            if (NoticeFileExists[6] == false) notice_file7.setVisibility(View.GONE);
            if (NoticeFileExists[7] == false) notice_file8.setVisibility(View.GONE);
            if (NoticeFileExists[8] == false) notice_file9.setVisibility(View.GONE);
            if (NoticeFileExists[9] == false) notice_file10.setVisibility(View.GONE);

            notice1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {((MainActivity)getActivity()).openUrl(NoticeUrlString[0]);}
            });
            notice2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {((MainActivity)getActivity()).openUrl(NoticeUrlString[1]);}
            });
            notice3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {((MainActivity)getActivity()).openUrl(NoticeUrlString[2]);}
            });
            notice4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {((MainActivity)getActivity()).openUrl(NoticeUrlString[3]);}
            });
            notice5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {((MainActivity)getActivity()).openUrl(NoticeUrlString[4]);}
            });
            notice6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {((MainActivity)getActivity()).openUrl(NoticeUrlString[5]);}
            });
            notice7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {((MainActivity)getActivity()).openUrl(NoticeUrlString[6]);}
            });
            notice8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {((MainActivity)getActivity()).openUrl(NoticeUrlString[7]);}
            });
            notice9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {((MainActivity)getActivity()).openUrl(NoticeUrlString[8]);}
            });
            notice10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {((MainActivity)getActivity()).openUrl(NoticeUrlString[9]);}
            });

            final int sdk = android.os.Build.VERSION.SDK_INT;
            if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                if (!NoticeImportant[0]) notice1.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.noticeimportant) );
                if (!NoticeImportant[1]) notice2.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.noticeimportant) );
                if (!NoticeImportant[2]) notice3.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.noticeimportant) );
                if (!NoticeImportant[3]) notice4.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.noticeimportant) );
                if (!NoticeImportant[4]) notice5.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.noticeimportant) );
                if (!NoticeImportant[5]) notice6.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.noticeimportant) );
                if (!NoticeImportant[6]) notice7.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.noticeimportant) );
                if (!NoticeImportant[7]) notice8.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.noticeimportant) );
                if (!NoticeImportant[8]) notice9.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.noticeimportant) );
                if (!NoticeImportant[9]) notice10.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.noticeimportant) );
            } else {
                if (!NoticeImportant[0]) notice1.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.noticeimportant));
                if (!NoticeImportant[1]) notice2.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.noticeimportant));
                if (!NoticeImportant[2]) notice3.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.noticeimportant));
                if (!NoticeImportant[3]) notice4.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.noticeimportant));
                if (!NoticeImportant[4]) notice5.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.noticeimportant));
                if (!NoticeImportant[5]) notice6.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.noticeimportant));
                if (!NoticeImportant[6]) notice7.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.noticeimportant));
                if (!NoticeImportant[7]) notice8.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.noticeimportant));
                if (!NoticeImportant[8]) notice9.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.noticeimportant));
                if (!NoticeImportant[9]) notice10.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.noticeimportant));
            }
        }
    }
}
