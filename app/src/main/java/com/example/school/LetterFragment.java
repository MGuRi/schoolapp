package com.example.school;

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

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class LetterFragment extends Fragment {
    String letterTitleString[] = {"네트워크 연결 없음","네트워크 연결 없음","네트워크 연결 없음","네트워크 연결 없음","네트워크 연결 없음","네트워크 연결 없음","네트워크 연결 없음","네트워크 연결 없음","네트워크 연결 없음","네트워크 연결 없음"};
    String letterWriterString[] = {"NO NETWORK","NO NETWORK","NO NETWORK","NO NETWORK","NO NETWORK","NO NETWORK","NO NETWORK","NO NETWORK","NO NETWORK","NO NETWORK"};
    String letterDateString[] = {"","","","","","","","","",""};
    String letterUrlString[] = {"no","no","no","no","no","no","no","no","no","no"};
    Boolean letterFileExists[] = {false, false, false, false, false, false, false, false, false, false};
    Boolean letterImportant[] = {false, false, false, false, false, false, false, false, false, false};
    String letterViews[] = {"","","","","","","","","",""};

    LinearLayout letter1, letter2, letter3, letter4, letter5, letter6, letter7, letter8, letter9, letter10;
    TextView letter_title1, letter_title2, letter_title3, letter_title4, letter_title5, letter_title6, letter_title7, letter_title8, letter_title9, letter_title10;
    TextView letter_writer1, letter_writer2, letter_writer3, letter_writer4, letter_writer5, letter_writer6, letter_writer7, letter_writer8, letter_writer9, letter_writer10;
    TextView letter_date1, letter_date2, letter_date3, letter_date4, letter_date5, letter_date6, letter_date7, letter_date8, letter_date9, letter_date10;
    TextView letter_view1, letter_view2, letter_view3, letter_view4, letter_view5, letter_view6, letter_view7, letter_view8, letter_view9, letter_view10;
    ImageView letter_file1, letter_file2, letter_file3, letter_file4, letter_file5, letter_file6, letter_file7, letter_file8, letter_file9, letter_file10;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_letter, container, false);

        letter1 = view.findViewById(R.id.letterFragment_letter1);
        letter2 = view.findViewById(R.id.letterFragment_letter2);
        letter3 = view.findViewById(R.id.letterFragment_letter3);
        letter4 = view.findViewById(R.id.letterFragment_letter4);
        letter5 = view.findViewById(R.id.letterFragment_letter5);
        letter6 = view.findViewById(R.id.letterFragment_letter6);
        letter7 = view.findViewById(R.id.letterFragment_letter7);
        letter8 = view.findViewById(R.id.letterFragment_letter8);
        letter9 = view.findViewById(R.id.letterFragment_letter9);
        letter10 = view.findViewById(R.id.letterFragment_letter10);

        letter_title1 = view.findViewById(R.id.letterFragment_letter1_title);
        letter_title2 = view.findViewById(R.id.letterFragment_letter2_title);
        letter_title3 = view.findViewById(R.id.letterFragment_letter3_title);
        letter_title4 = view.findViewById(R.id.letterFragment_letter4_title);
        letter_title5 = view.findViewById(R.id.letterFragment_letter5_title);
        letter_title6 = view.findViewById(R.id.letterFragment_letter6_title);
        letter_title7 = view.findViewById(R.id.letterFragment_letter7_title);
        letter_title8 = view.findViewById(R.id.letterFragment_letter8_title);
        letter_title9 = view.findViewById(R.id.letterFragment_letter9_title);
        letter_title10 = view.findViewById(R.id.letterFragment_letter10_title);

        letter_writer1 = view.findViewById(R.id.letterFragment_letter1_writer);
        letter_writer2 = view.findViewById(R.id.letterFragment_letter2_writer);
        letter_writer3 = view.findViewById(R.id.letterFragment_letter3_writer);
        letter_writer4 = view.findViewById(R.id.letterFragment_letter4_writer);
        letter_writer5 = view.findViewById(R.id.letterFragment_letter5_writer);
        letter_writer6 = view.findViewById(R.id.letterFragment_letter6_writer);
        letter_writer7 = view.findViewById(R.id.letterFragment_letter7_writer);
        letter_writer8 = view.findViewById(R.id.letterFragment_letter8_writer);
        letter_writer9 = view.findViewById(R.id.letterFragment_letter9_writer);
        letter_writer10 = view.findViewById(R.id.letterFragment_letter10_writer);

        letter_date1 = view.findViewById(R.id.letterFragment_letter1_date);
        letter_date2 = view.findViewById(R.id.letterFragment_letter2_date);
        letter_date3 = view.findViewById(R.id.letterFragment_letter3_date);
        letter_date4 = view.findViewById(R.id.letterFragment_letter4_date);
        letter_date5 = view.findViewById(R.id.letterFragment_letter5_date);
        letter_date6 = view.findViewById(R.id.letterFragment_letter6_date);
        letter_date7 = view.findViewById(R.id.letterFragment_letter7_date);
        letter_date8 = view.findViewById(R.id.letterFragment_letter8_date);
        letter_date9 = view.findViewById(R.id.letterFragment_letter9_date);
        letter_date10 = view.findViewById(R.id.letterFragment_letter10_date);

        letter_view1 = view.findViewById(R.id.letterFragment_letter1_view);
        letter_view2 = view.findViewById(R.id.letterFragment_letter2_view);
        letter_view3 = view.findViewById(R.id.letterFragment_letter3_view);
        letter_view4 = view.findViewById(R.id.letterFragment_letter4_view);
        letter_view5 = view.findViewById(R.id.letterFragment_letter5_view);
        letter_view6 = view.findViewById(R.id.letterFragment_letter6_view);
        letter_view7 = view.findViewById(R.id.letterFragment_letter7_view);
        letter_view8 = view.findViewById(R.id.letterFragment_letter8_view);
        letter_view9 = view.findViewById(R.id.letterFragment_letter9_view);
        letter_view10 = view.findViewById(R.id.letterFragment_letter10_view);

        letter_file1 = view.findViewById(R.id.letterFragment_letter1_file);
        letter_file2 = view.findViewById(R.id.letterFragment_letter2_file);
        letter_file3 = view.findViewById(R.id.letterFragment_letter3_file);
        letter_file4 = view.findViewById(R.id.letterFragment_letter4_file);
        letter_file5 = view.findViewById(R.id.letterFragment_letter5_file);
        letter_file6 = view.findViewById(R.id.letterFragment_letter6_file);
        letter_file7 = view.findViewById(R.id.letterFragment_letter7_file);
        letter_file8 = view.findViewById(R.id.letterFragment_letter8_file);
        letter_file9 = view.findViewById(R.id.letterFragment_letter9_file);
        letter_file10 = view.findViewById(R.id.letterFragment_letter10_file);

        letterAsyncTask task = new letterAsyncTask();
        task.execute();

        return view;
    }

    class letterAsyncTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Connection.Response response = Jsoup.connect("http://school.busanedu.net/daesin-m/na/ntt/selectNttList.do?mi=618567&bbsId=1011230")
                        .method(Connection.Method.GET)
                        .execute();
                Document document = response.parse();

                Elements letterTitle = document.select("td.ta_l a");
                Elements letterWriterDate = document.select(".board-text table tbody tr td");

                for (int i = 0; i < 10; i++) {
                    /** letter Title **/
                    Element letter = letterTitle.get(i);
                    String[] TitleRes1 = letter.toString().split(">");
                    String[] TitleRes2 = TitleRes1[1].split("<");
                    if (TitleRes2[0].contains("&nbsp;")) {
                        String[] TitleRes3 = TitleRes2[0].split("&nbsp;");
                        letterTitleString[i] = TitleRes3[1];
                        letterImportant[i] = true;
                    } else {
                        letterTitleString[i] = TitleRes2[0];
                    }
//                    if (letterTitleString[i].length() >= 30) {
//                        letterTitleString[i] = letterTitleString[i].substring(0, 27);
//                        letterTitleString[i] += "...";
//                    }
                    /** letter Link **/
                    String[] TitleRes4 = TitleRes1[0].split("\"");
                    letterUrlString[i] = "http://school.busanedu.net" + TitleRes4[1];

                    /** letter Writer **/
                    letterWriterString[i] = letterWriterDate.get(6 * i + 2).text();

                    /** letter Date **/
                    letterDateString[i] = letterWriterDate.get(6 * i + 3).text();

                    /** letter Views **/
                    letterViews[i] = "조회 " + letterWriterDate.get(6*i+4).text() + "회";

                    String temp = letterWriterDate.get(6 * i + 5).html();
                    if (temp.contains("img src")) {
                        letterFileExists[i] = true;
                    }
                    System.out.println(i + letterTitleString[i] + letterUrlString[i] + letterWriterString[i] + letterDateString[i]);
                    System.out.println(letterFileExists[i]);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            letter_title1.setText(letterTitleString[0]);
            letter_title2.setText(letterTitleString[1]);
            letter_title3.setText(letterTitleString[2]);
            letter_title4.setText(letterTitleString[3]);
            letter_title5.setText(letterTitleString[4]);
            letter_title6.setText(letterTitleString[5]);
            letter_title7.setText(letterTitleString[6]);
            letter_title8.setText(letterTitleString[7]);
            letter_title9.setText(letterTitleString[8]);
            letter_title10.setText(letterTitleString[9]);

            letter_writer1.setText(letterWriterString[0]);
            letter_writer2.setText(letterWriterString[1]);
            letter_writer3.setText(letterWriterString[2]);
            letter_writer4.setText(letterWriterString[3]);
            letter_writer5.setText(letterWriterString[4]);
            letter_writer6.setText(letterWriterString[5]);
            letter_writer7.setText(letterWriterString[6]);
            letter_writer8.setText(letterWriterString[7]);
            letter_writer9.setText(letterWriterString[8]);
            letter_writer10.setText(letterWriterString[9]);

            letter_date1.setText(letterDateString[0]);
            letter_date2.setText(letterDateString[1]);
            letter_date3.setText(letterDateString[2]);
            letter_date4.setText(letterDateString[3]);
            letter_date5.setText(letterDateString[4]);
            letter_date6.setText(letterDateString[5]);
            letter_date7.setText(letterDateString[6]);
            letter_date8.setText(letterDateString[7]);
            letter_date9.setText(letterDateString[8]);
            letter_date10.setText(letterDateString[9]);

            letter_view1.setText(letterViews[0]);
            letter_view2.setText(letterViews[1]);
            letter_view3.setText(letterViews[2]);
            letter_view4.setText(letterViews[3]);
            letter_view5.setText(letterViews[4]);
            letter_view6.setText(letterViews[5]);
            letter_view7.setText(letterViews[6]);
            letter_view8.setText(letterViews[7]);
            letter_view9.setText(letterViews[8]);
            letter_view10.setText(letterViews[9]);

            if (letterFileExists[0] == false) letter_file1.setVisibility(View.GONE);
            if (letterFileExists[1] == false) letter_file2.setVisibility(View.GONE);
            if (letterFileExists[2] == false) letter_file3.setVisibility(View.GONE);
            if (letterFileExists[3] == false) letter_file4.setVisibility(View.GONE);
            if (letterFileExists[4] == false) letter_file5.setVisibility(View.GONE);
            if (letterFileExists[5] == false) letter_file6.setVisibility(View.GONE);
            if (letterFileExists[6] == false) letter_file7.setVisibility(View.GONE);
            if (letterFileExists[7] == false) letter_file8.setVisibility(View.GONE);
            if (letterFileExists[8] == false) letter_file9.setVisibility(View.GONE);
            if (letterFileExists[9] == false) letter_file10.setVisibility(View.GONE);

            letter1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {((MainActivity)getActivity()).openUrl(letterUrlString[0]);}
            });
            letter2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {((MainActivity)getActivity()).openUrl(letterUrlString[1]);}
            });
            letter3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {((MainActivity)getActivity()).openUrl(letterUrlString[2]);}
            });
            letter4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {((MainActivity)getActivity()).openUrl(letterUrlString[3]);}
            });
            letter5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {((MainActivity)getActivity()).openUrl(letterUrlString[4]);}
            });
            letter6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {((MainActivity)getActivity()).openUrl(letterUrlString[5]);}
            });
            letter7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {((MainActivity)getActivity()).openUrl(letterUrlString[6]);}
            });
            letter8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {((MainActivity)getActivity()).openUrl(letterUrlString[7]);}
            });
            letter9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {((MainActivity)getActivity()).openUrl(letterUrlString[8]);}
            });
            letter10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {((MainActivity)getActivity()).openUrl(letterUrlString[9]);}
            });
        }
    }
}
