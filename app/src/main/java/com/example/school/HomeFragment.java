package com.example.school;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class HomeFragment extends Fragment {
    private TextView mainNotice;
    private LinearLayout mainNoticeFrame;
    private static View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        mainNotice = view.findViewById(R.id.homeFragment_mainNotice);
        mainNoticeFrame = view.findViewById(R.id.homeFragment_mainNoticeFrame);

        HomeInitAsyncTask task = new HomeInitAsyncTask();
        task.execute();

        return view;
    }

    class HomeInitAsyncTask extends AsyncTask<Void, Void, Void> {
        String noticeText = "네트워크 연결 없음";
        String lunchKalText;
        String lunchText = "네트워크 연결 없음";
        String link = "no";
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                String url = "http://school.busanedu.net/daesin-m/main.do";
                Document document = Jsoup.connect(url).get();

                Elements notices = document.select("div[class=dis_board_box] div ul li");
                for (Element e: notices) {
                    noticeText = e.text();
                    String[] temp = e.html().split("\"");
                    link =  "http://school.busanedu.net" + temp[1];
                    System.out.println(link);
                }

                Elements lunchsKal = document.select("div[class=MC_box4] dt");
                Elements lunchs = document.select("div[class=MC_box4] dd");
                for (Element e: lunchsKal) {
                    lunchKalText = e.text();
                }
                for (Element e: lunchs) {
                    lunchText = e.text();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            TextView mainNotice = view.findViewById(R.id.homeFragment_mainNotice);
            TextView mainLunch = view.findViewById(R.id.homeFragment_mainLunch);
            mainNotice.setText(noticeText);
            if (lunchKalText == null) {
                mainLunch.setText(lunchText);
            } else {
                mainLunch.setText(lunchKalText +"\n\n"+ lunchText);
            }

            mainNotice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity)getActivity()).openUrl(link);
                }
            });
        }
    }
}
