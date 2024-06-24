package com.example.capstone;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class FragmentMysubs extends Fragment {
    ListView listView;
    TextView empty_listview;
    private static MysubsAdapter adapter;
    private Object MysubsListActivity;

    private ArrayList<MysubsItem> data = null;
    String userEmail; String userName; String joinDate; String userPw; String groupCode; String ottCode;
    String subscription; String ottName; String waitingOttCode; String waitingUserPaymentDate;
    String new_first;String waitingCount;String userPaymentDate;
    String startDate;String endDate;String ottID;String ottPW;String createDate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mysubs, container, false);

        Bundle extra = getArguments();
        if(extra != null){
            userEmail = extra.getString("userEmail");
            userName = extra.getString("userName");
            joinDate = extra.getString("joinDate");
            userPw = extra.getString("userPw");
            groupCode = extra.getString("groupCode");
            Log.v("groupCode", groupCode);
            ottCode = extra.getString("ottCode");
            subscription = extra.getString("subscription");
            ottName = extra.getString("ottName");
            waitingOttCode = extra.getString("waitingOttCode");
            waitingUserPaymentDate = extra.getString("waitingUserPaymentDate");
            new_first = extra.getString("new_first");
            waitingCount = extra.getString("waitingCount");
            userPaymentDate = extra.getString("userPaymentDate");
            startDate = extra.getString("startDate");
            endDate = extra.getString("endDate");
            ottID = extra.getString("ottID");
            ottPW = extra.getString("ottPW");
            createDate = extra.getString("createDate");
        }
        String[] str_groupCode = groupCode.split(",");
        String[] str_ottCode = ottCode.split(",");
        String[] str_subscription = subscription.split(",");
        String[] str_ottName = ottName.split(",");
        String[] w_ottCode = waitingOttCode.split(",");
        String[] w_userPaymentDate = waitingUserPaymentDate.split(",");
        String[] w_new_first = new_first.split(",");
        String[] w_count = waitingCount.split(",");
        String[] str_userPaymentDate = userPaymentDate.split(",");
        String[] str_startDate = startDate.split(",");
        String[] str_endDate = endDate.split(",");
        String[] str_ottID = ottID.split(",");
        String[] str_ottPW = ottPW.split(",");
        String[] str_createDate = createDate.split(",");

        data = new ArrayList<>();

        for (int i = 0; i < str_ottCode.length;  i++){
            boolean waiting = false;
            if(str_ottCode[i].equals("1")){
                MysubsItem siNetflix = new MysubsItem(R.drawable.netflix, "NETFLIX", str_startDate[i] + "~" + str_endDate[i], waiting,
                        str_groupCode[i],  str_groupCode[i], str_subscription[i], str_userPaymentDate[i],
                        str_ottID[i], str_ottPW[i], str_createDate[i], " ", "", "", "");

                data.add(siNetflix);
            } else if (str_ottCode[i].equals("2")){
                MysubsItem siWavve= new MysubsItem(R.drawable.wavve,  "Wavve", str_startDate[i] + "~" + str_endDate[i], waiting,
                        str_groupCode[i],  str_groupCode[i], str_subscription[i], str_userPaymentDate[i],
                        str_ottID[i], str_ottPW[i], str_createDate[i], " ", "", "", "");
                data.add(siWavve);
            }else if (str_ottCode[i].equals("3")){
                MysubsItem siDisney = new MysubsItem(R.drawable.disney,  "Disney+", str_startDate[i] + "~" + str_endDate[i], waiting,
                        str_groupCode[i],  str_groupCode[i], str_subscription[i], str_userPaymentDate[i],
                        str_ottID[i], str_ottPW[i], str_createDate[i], " ", "", "", "");
                data.add(siDisney);
            }else if (str_ottCode[i].equals("4")){
                MysubsItem siWatcha  = new MysubsItem(R.drawable.watcha,  "Watcha", str_startDate[i] + "~" + str_endDate[i], waiting,
                        str_groupCode[i],  str_groupCode[i], str_subscription[i], str_userPaymentDate[i],
                        str_ottID[i], str_ottPW[i], str_createDate[i], "", "", "", "");
                data.add(siWatcha);
            }
        }

        listView = (ListView) view.findViewById(R.id.lv_subs);
        empty_listview = (TextView) view.findViewById(R.id.empty_listview);
        adapter = new MysubsAdapter(getContext(), R.layout.mysubs_item, data);
        listView.setAdapter(adapter);
        listView.setEmptyView(empty_listview);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), MysubsDetailActivity.class);
                intent.putExtra("subsicon", Integer.toString(data.get(position).getIv_subs_icon()));
                intent.putExtra("subsname", data.get(position).getTv_subs_name());
                intent.putExtra("subsdate", data.get(position).getTv_subs_date());
                intent.putExtra("waiting", data.get(position).getWaiting());

                intent.putExtra("groupCode", data.get(position).getGroupCode());
                intent.putExtra("ottCode", data.get(position).getOttCode());
                intent.putExtra("subscription", data.get(position).getSubscription());
                intent.putExtra("ottID", data.get(position).getOttID());
                intent.putExtra("ottPW", data.get(position).getOttPW());
                intent.putExtra("userPaymentDate", data.get(position).getUserPaymentDate());
                intent.putExtra("createDate", data.get(position).getCreateDate());

                intent.putExtra("userEmail", userEmail);
                intent.putExtra("userPw", userPw);

                intent.putExtra("waitingOttCode", data.get(position).getWaitingOttCode());
                intent.putExtra("waitingUserPaymentDate", data.get(position).getWaitingUserPaymentDate());
                intent.putExtra("new_first", data.get(position).getNew_first());
                intent.putExtra("waitingSubscription", data.get(position).getSubscription());

                startActivity(intent);
            }
        });


        return view;
    }
}
