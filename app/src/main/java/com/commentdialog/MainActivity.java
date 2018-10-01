package com.commentdialog;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
    CommentView commentView;
    CommentDialog commentDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        commentView = (CommentView) findViewById(R.id.comment_view);
        commentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commentDialog = new CommentDialog(MainActivity.this).setListener(new CommentDialog.Listener() {
                    @Override
                    public void commentContent(String content) {

                    }
                });
                commentDialog.show();
            }
        });
    }
}
