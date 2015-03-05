package com.example.root.exam2program;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class LogInActivity extends Activity {
    private UserDB DB;

    private static int LOG_IN_ATTEMPTS = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        DB = UserDB.getInstance(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.log_in, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void buttonHandler(View v)
    {
        SQLiteDatabase db = DB.getReadableDatabase();
        Cursor c = getUserAndPass(db);
        c.moveToFirst();
        EditText usertxt = (EditText) findViewById(R.id.userName);
        EditText passtxt = (EditText) findViewById(R.id.password);
        String userEntered = usertxt.getText().toString();
        String passEntered = passtxt.getText().toString();
        String pulledPassword = "";
        try{
            if (c.moveToFirst()) {// Here we try to move to the first record
                pulledPassword = c.getString(0); // Only assign string value if we moved to first record
                System.out.print("");
            }
        }finally {
            System.out.print("");
            c.close();
        }
        boolean passwordSame = pulledPassword.equals(passEntered);
        if(passwordSame)
        {
            System.out.print("");
            Intent i = new Intent(this, LoggedIn.class);
            i.putExtra("user", userEntered);
            System.out.print("");
            startActivity(i);
        }
        else
        {
            System.out.print("");
            usertxt.setText("");
            passtxt.setText("");
            LOG_IN_ATTEMPTS += 1;
            String remainingAttempts = "" + (3-LOG_IN_ATTEMPTS);
            TextView txtInvalid = (TextView) findViewById(R.id.textView2);
            if(LOG_IN_ATTEMPTS < 3) {
                txtInvalid.setText("You have " + remainingAttempts + " attempts left.");
                txtInvalid.setVisibility(View.VISIBLE);
            }
            else
            {
                finish();
            }
        }
    }

    public Cursor getUserAndPass(SQLiteDatabase db)
    {
        String[] columns = {"password"};
        EditText userText = (EditText) findViewById(R.id.userName);
        String userEntered = userText.getText().toString();
        Cursor c = db.query("user", columns, "username='" + userEntered + "'", null, null, null, null);
        return c;
    }
}
