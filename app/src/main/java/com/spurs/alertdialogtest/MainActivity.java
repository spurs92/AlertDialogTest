package com.spurs.alertdialogtest;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String[] items=new String[]{"RED","GREEN","BLUE"};
    boolean[] checkeds=new boolean[]{true,false,true};

    EditText edit_id;
    EditText edit_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void clickBtn(View v){
        //AlertDialog 생성 및 보이기

        //AlertDialog를 생성할 수 있는 건축가(Builder)객체 생성
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("dialog");//제목
        builder.setIcon(R.mipmap.ic_launcher);
        //builder.setMessage("Do you wanna exit?");

/*        //리스트형태로 보이기
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this,items[which],Toast.LENGTH_SHORT).show();
            }
        });*/

/*        //라디오버튼 형태
        builder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this,items[which],Toast.LENGTH_SHORT).show();
            }
        });*/

        //체크박스 형태
        builder.setMultiChoiceItems(items, checkeds, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                checkeds[which]=isChecked;
            }
        });


        builder.setPositiveButton("완료", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String s="";
                if(checkeds[0]) s+=items[0];
                if(checkeds[1]) s+=items[1];
                if(checkeds[2]) s+=items[2];

                Toast.makeText(MainActivity.this,s,Toast.LENGTH_SHORT).show();
            }
        });
/*        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this,"Cancel",Toast.LENGTH_SHORT).show();
            }
        });*/


        //건축가에게 AlertDialog를 생성해 주세요..
        AlertDialog dialog=builder.create();

        //다이얼로그의 바깥쪽을 클릭했을때 dismiss(사라지지) 않도록
        dialog.setCanceledOnTouchOutside(false);

        dialog.show();
    }

    public void clickBtn2(View v){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("login");
        //커스텀 뷰로 컨텐트 영역을 설정..
        //layout폴더 안에 있는 alert.xml문서를 뷰객체로 생성(부풀리기)
        LayoutInflater inflater=getLayoutInflater();
        View layout =inflater.inflate(R.layout.alert,null);

        edit_id=(EditText) layout.findViewById(R.id.edit_id);
        edit_pass=(EditText) layout.findViewById(R.id.edit_pass);

        builder.setView(layout);

        builder.setPositiveButton("login", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //에디트텍스트에 써있는 글씨 얻어오기
                String id;
                String pass;

                id=edit_id.getText().toString();
                pass=edit_pass.getText().toString();

                //얻어온 글씨 Toast에 보이기..
                Toast.makeText(MainActivity.this,id+" / "+pass,Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog dialog=builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

    }
}
