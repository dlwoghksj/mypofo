package com.example.capstone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class NoticeListActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<NoticeItem> data = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        /* xml과 연결 */
        setContentView(R.layout.notice_list);

        ListView listView = (ListView) findViewById(R.id.lv_notice);

        /* 서버와 연동했닫면 값을 받아서 띄울 수 있지만,
         * 연동이 되어있지 않으므로
         * 하드코딩으로 값을 집어넣는다.
         */
        data = new ArrayList<>();

        //NoticeItem no0 = new NoticeItem( , , );
        NoticeItem no0 = new NoticeItem(R.drawable.no0, "OPEN" , "2022-06-10", "WATTO가 정식 오픈하였습니다!\n\n" +
                "WATTO는 OTT스트리밍 서비스를 더치페이하여 좀 더 합리적인 가격으로 이용할 수 있게 해주는 서비스입니다.\n" +
                "NETFLIX, WATCHA, DISNEY+등의 많은 OTT서비스들을 저렴하게 이용하세요!\n\n" +
                "WATTO 이용법\n" +
                "1. 좌측 상단의 메뉴버튼을 클릭하고 [계정설정]을 클릭한다.\n" +
                "2. 카드를 등록한다.\n" +
                "3. 홈화면에서 원하는 OTT서비스를 클릭한다.\n" +
                "4. 결제후 즐겁게 스트리밍 서비스를 즐긴다!");
        NoticeItem no1 = new NoticeItem(R.drawable.no1, "공지 사항", "2022-06-12", "WATTO가 버전업되었습니다.\n\n" +
                "v0.1.1f로 업데이트 해주세요.");
        NoticeItem no2 = new NoticeItem(R.drawable.no2, "넷플릭스 신규 독점작! <우리들의 블루스>", "2022-06-14", "넷플릭스에서 TVN의 인기 드라마 <우리들의 블루스>가 독점스트리밍" +
                "을 시작합니다.\n\n" +
                "이병헌, 신민아, 차승원, 이정은, 한지민, 김우빈, 엄정화 등의 화려한 캐스팅!\n\n" +
                "지금 넷플릭스를 구독하시고 시즌 최종화를 시청하세요.");

        data.add(0,no0);
        data.add(0,no1);
        data.add(0,no2);


        /* 리스트 속의 아이템 연결 */
        NoticeAdapter adapter = new NoticeAdapter(this, R.layout.notice_item, data);
        listView.setAdapter(adapter);

        /* 아이템 클릭시 작동 */
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), NoticeDetailActivity.class);
                /* putExtra의 첫 값은 식별 태그, 뒤에는 다음 화면에 넘길 값 */
                intent.putExtra("notice_img", Integer.toString(data.get(position).getIv_notice_list_img()));
                intent.putExtra("notice_title", data.get(position).getTv_notice_list_title());
                intent.putExtra("notice_date", data.get(position).getTv_notice_list_date());
                intent.putExtra("notice_info", data.get(position).getNotice_info());
                startActivity(intent);
            }
        });
        //뒤로가기 툴바 배치
        Toolbar toolbar = findViewById(R.id.backspace_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public void onClick(View v) {
    }

    //뒤로가기 툴바 눌렀을 때
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:{
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}