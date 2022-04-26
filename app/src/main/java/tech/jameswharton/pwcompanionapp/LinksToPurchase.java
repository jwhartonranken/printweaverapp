package tech.jameswharton.pwcompanionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class LinksToPurchase extends AppCompatActivity {
    Button btnPhysical;
    TextView tvDigital;
    ImageView ivDigital;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_links_to_purchase);

        btnPhysical = findViewById(R.id.btnPhysical);
        tvDigital = findViewById(R.id.tvDigital);
        ivDigital = findViewById(R.id.ivDigital);

        btnPhysical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.exaltedfuneral.com/products/print-weaver-pdf"));
                startActivity(browserIntent);
            }
        });

        tvDigital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linkToDigital();
            }
        });

        ivDigital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linkToDigital();
            }
        });
    }

    private void linkToDigital() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://nlmorrison.itch.io/print-weaver"));
        startActivity(browserIntent);
    }

}