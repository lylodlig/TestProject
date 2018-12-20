package com.lzy.testproject.saunfa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.lzy.testproject.R;
import com.lzy.testproject.saunfa.ercha.Method;
import com.lzy.testproject.saunfa.ercha.Node;

import java.util.Random;

public class SuanfaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suanfa);
    }

    public void bt(View view) {
        Node node = new Node(10);
        Method.insert(node, 1);
        Method.insert(node, 3);
        Method.insert(node, 2);
        Method.insert(node, 12);
        Method.insert(node, 14);
        Method.insert(node, 11);

        Method.bianli(node);
    }


}
