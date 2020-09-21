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
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.daesin.school.R;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class PhotoFragment extends Fragment {
    String[] imgURL = {"", "", "", "", "", "", "", ""};
    String[] imgTitle = {"NO NETWORK", "NO NETWORK", "NO NETWORK", "NO NETWORK", "NO NETWORK", "NO NETWORK", "NO NETWORK", "NO NETWORK"};
    String[] imgDate = {"", "", "", "", "", "", "", ""};
    String[] PhotoUrl = {"no", "no", "no", "no", "no", "no", "no", "no"};

    LinearLayout photo1, photo2, photo3, photo4, photo5, photo6, photo7, photo8;
    ImageView photo1_photo, photo2_photo, photo3_photo, photo4_photo, photo5_photo, photo6_photo, photo7_photo, photo8_photo;
    TextView photo1_title, photo2_title, photo3_title, photo4_title, photo5_title, photo6_title, photo7_title, photo8_title;
    TextView photo1_date, photo2_date, photo3_date, photo4_date, photo5_date, photo6_date, photo7_date, photo8_date;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo, container, false);

        photo1 = view.findViewById(R.id.photoFragment_photo1);
        photo2 = view.findViewById(R.id.photoFragment_photo2);
        photo3 = view.findViewById(R.id.photoFragment_photo3);
        photo4 = view.findViewById(R.id.photoFragment_photo4);
        photo5 = view.findViewById(R.id.photoFragment_photo5);
        photo6 = view.findViewById(R.id.photoFragment_photo6);
        photo7 = view.findViewById(R.id.photoFragment_photo7);
        photo8 = view.findViewById(R.id.photoFragment_photo8);

        photo1_photo = view.findViewById(R.id.photoFragment_photo1_imageView);
        photo2_photo = view.findViewById(R.id.photoFragment_photo2_imageView);
        photo3_photo = view.findViewById(R.id.photoFragment_photo3_imageView);
        photo4_photo = view.findViewById(R.id.photoFragment_photo4_imageView);
        photo5_photo = view.findViewById(R.id.photoFragment_photo5_imageView);
        photo6_photo = view.findViewById(R.id.photoFragment_photo6_imageView);
        photo7_photo = view.findViewById(R.id.photoFragment_photo7_imageView);
        photo8_photo = view.findViewById(R.id.photoFragment_photo8_imageView);

        photo1_title = view.findViewById(R.id.photoFragment_photo1_title);
        photo2_title = view.findViewById(R.id.photoFragment_photo2_title);
        photo3_title = view.findViewById(R.id.photoFragment_photo3_title);
        photo4_title = view.findViewById(R.id.photoFragment_photo4_title);
        photo5_title = view.findViewById(R.id.photoFragment_photo5_title);
        photo6_title = view.findViewById(R.id.photoFragment_photo6_title);
        photo7_title = view.findViewById(R.id.photoFragment_photo7_title);
        photo8_title = view.findViewById(R.id.photoFragment_photo8_title);

        photo1_date = view.findViewById(R.id.photoFragment_photo1_date);
        photo2_date = view.findViewById(R.id.photoFragment_photo2_date);
        photo3_date = view.findViewById(R.id.photoFragment_photo3_date);
        photo4_date = view.findViewById(R.id.photoFragment_photo4_date);
        photo5_date = view.findViewById(R.id.photoFragment_photo5_date);
        photo6_date = view.findViewById(R.id.photoFragment_photo6_date);
        photo7_date = view.findViewById(R.id.photoFragment_photo7_date);
        photo8_date = view.findViewById(R.id.photoFragment_photo8_date);

        PhotoAsyncTask task = new PhotoAsyncTask();
        task.execute();

        return view;
    }

    class PhotoAsyncTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Connection.Response response = Jsoup.connect("http://school.busanedu.net/daesin-m/na/ntt/selectNttList.do?mi=618572&bbsId=1011232")
                        .method(Connection.Method.GET)
                        .execute();
                Document document = response.parse();
                Elements imgurl = document.select("div.photo_list ul li a img");
                Elements imgtitle = document.select("div.photo_list ul li a p");
                Elements imgdate = document.select("div.photo_list ul li a span");
                Elements photourl = document.select("div.photo_list ul li a");

                for(int i = 0; i < 8; i++) {
                    String[] img_res1 = imgurl.get(i).toString().split("\"");
                    String img_url = img_res1[1];
                    if(img_url.isEmpty()) {
                        imgURL[i] = "http://school.busanedu.net/images/co/na/noImg.gif";
                    } else {
                        imgURL[i] = "http://school.busanedu.net" + img_res1[1];
                    }

                    /** 이미지 타이틀 **/
                    imgTitle[i] = imgtitle.get(i).text();

                    /** 이미지 날짜 **/
                    imgDate[i] = imgdate.get(i).text();

                    String[] photo_url = photourl.get(i).toString().split("\"");
                    PhotoUrl[i] = "http://school.busanedu.net" + photo_url[1];
                    System.out.println(photo_url[1]);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            photo1_title.setText(imgTitle[0]);
            photo2_title.setText(imgTitle[1]);
            photo3_title.setText(imgTitle[2]);
            photo4_title.setText(imgTitle[3]);
            photo5_title.setText(imgTitle[4]);
            photo6_title.setText(imgTitle[5]);
            photo7_title.setText(imgTitle[6]);
            photo8_title.setText(imgTitle[7]);

            photo1_date.setText(imgDate[0]);
            photo2_date.setText(imgDate[1]);
            photo3_date.setText(imgDate[2]);
            photo4_date.setText(imgDate[3]);
            photo5_date.setText(imgDate[4]);
            photo6_date.setText(imgDate[5]);
            photo7_date.setText(imgDate[6]);
            photo8_date.setText(imgDate[7]);

            Glide.with(getActivity()).load(imgURL[0]).into(photo1_photo);
            Glide.with(getActivity()).load(imgURL[1]).into(photo2_photo);
            Glide.with(getActivity()).load(imgURL[2]).into(photo3_photo);
            Glide.with(getActivity()).load(imgURL[3]).into(photo4_photo);
            Glide.with(getActivity()).load(imgURL[4]).into(photo5_photo);
            Glide.with(getActivity()).load(imgURL[5]).into(photo6_photo);
            Glide.with(getActivity()).load(imgURL[6]).into(photo7_photo);
            Glide.with(getActivity()).load(imgURL[7]).into(photo8_photo);

            photo1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity)getActivity()).openUrl(PhotoUrl[0]);
                }
            });
            photo2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity)getActivity()).openUrl(PhotoUrl[1]);
                }
            });
            photo3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity)getActivity()).openUrl(PhotoUrl[2]);
                }
            });
            photo4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity)getActivity()).openUrl(PhotoUrl[3]);
                }
            });
            photo5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity)getActivity()).openUrl(PhotoUrl[4]);
                }
            });
            photo6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity)getActivity()).openUrl(PhotoUrl[5]);
                }
            });
            photo7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity)getActivity()).openUrl(PhotoUrl[6]);
                }
            });
            photo8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity)getActivity()).openUrl(PhotoUrl[7]);
                }
            });
        }
    }
}
