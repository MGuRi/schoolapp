/**temp**/
//package com.example.school;
//
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.core.content.ContextCompat;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.school.item.NoticeItemModel;
//
//import org.jsoup.Connection;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class NoticeFragment extends Fragment {
//    View view;
//    List<NoticeItemModel> noticeModels;
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        view = inflater.inflate(R.layout.fragment_notice, container, false);
//
//        noticeAsyncTask task = new noticeAsyncTask();
//        task.execute();
//
//        return view;
//    }
//
//    private class noticeAsyncTask extends AsyncTask {
//
//        @Override
//        protected Object doInBackground(Object[] objects) {
//            new Thread() {
//                public void run() {
//                    try {
//                        noticeModels.clear();
//                        Connection.Response response = Jsoup.connect("http://school.busanedu.net/daesin-m/na/ntt/selectNttList.do?mi=618566&bbsId=1011229&&listCo=20")
//                                .method(Connection.Method.GET)
//                                .execute();
//                        Document document = response.parse();
//
//                        Elements noticeItems = document.select("tbody tr");
//                        for (Element noticeItem : noticeItems) {
//                            NoticeItemModel noticeItemModel = new NoticeItemModel();
//
//                            noticeItemModel.title = noticeItem.select("td:nth-child(2)").text();
//                            noticeItemModel.writer = noticeItem.select("td:nth-child(3)").text();
//                            noticeItemModel.date = noticeItem.select("td:nth-child(4)").text();
//                            noticeItemModel.url = noticeItem.select("a").attr("href");
//                            noticeItemModel.file_exists = noticeItem.select("img").hasAttr("src");
//                            noticeItemModel.isimportant = noticeItem.select("td:nth-child(1)").text().equals("공지");
//                            noticeModels.add(noticeItemModel);
//
//                            System.out.println(noticeItemModel.title);
//                            System.out.println(noticeItemModel.writer);
//                            System.out.println(noticeItemModel.date);
//                            System.out.println(noticeItemModel.url);
//                            System.out.println(noticeItemModel.file_exists);
//                            System.out.println(noticeItemModel.isimportant + "\n");
//                        }
////                        notifyDataSetChanged();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                        System.out.println("===============IOException Error================");
//                    }
//                }
//            }.start();
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Object o) {
//            final RecyclerView recyclerView = view.findViewById(R.id.noticeFragment_recyclerview);
//            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//            recyclerView.setAdapter(new NoticeFragmentRecyclerView());
//        }
//    }
//
//    class NoticeFragmentRecyclerView extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
//        @NonNull
//        @Override
//        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notice, parent, false);
//
//            return new CustomViewHolder(view);
//        }
//
//        @Override
//        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
//            System.out.println("onBindViewHolder");
////            ((CustomViewHolder) holder).noticeTitle.setText(noticeModels.get(position).title);
//            ((CustomViewHolder) holder).noticeTitle.setText("TEST TITLE");
////            ((CustomViewHolder) holder).noticeWriter.setText(noticeModels.get(position).writer);
////            ((CustomViewHolder) holder).noticeDate.setText(noticeModels.get(position).date);
////            if (noticeModels.get(position).file_exists) {
////                ((CustomViewHolder) holder).noticeFileExists.setVisibility(View.VISIBLE);
////            } else {
////                ((CustomViewHolder) holder).noticeFileExists.setVisibility(View.GONE);
////            }
//            System.out.println("onBindViewHolder Close");
//        }
//
//        @Override
//        public int getItemCount() {
//            System.out.println("NoticeFragment : getItemCount()");
//            return noticeModels.size();
////            return 0;
//        }
//
//        private class CustomViewHolder extends RecyclerView.ViewHolder {
//            public TextView noticeTitle;
//            public TextView noticeWriter;
//            public TextView noticeDate;
////            public ImageView noticeFileExists;
//
//            public CustomViewHolder(View view) {
//                super(view);
//                noticeTitle = view.findViewById(R.id.itemNotice_title);
//                noticeWriter = view.findViewById(R.id.itemNotice_writer);
//                noticeDate = view.findViewById(R.id.itemNotice_date);
////                noticeFileExists = view.findViewById(R.id.itemNotice_fileExists);
//
//            }
//        }
//    }
//
//}