package com.example.classtask;



import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etEmail, etSubject, etMessage;
    private Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = findViewById(R.id.etEmail);
        etSubject = findViewById(R.id.etSubject);
        etMessage = findViewById(R.id.etMessage);
        btnSend = findViewById(R.id.btnSend);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail();
            }
        });
    }

    private void sendEmail() {
        String recipient = etEmail.getText().toString();
        String subject = etSubject.getText().toString();
        String message = etMessage.getText().toString();

        if (recipient.isEmpty() || subject.isEmpty() || message.isEmpty()) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{recipient});
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        try {
            startActivity(Intent.createChooser(intent, "Choose an email client"));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "No email client found", Toast.LENGTH_SHORT).show();
        }
    }
}
