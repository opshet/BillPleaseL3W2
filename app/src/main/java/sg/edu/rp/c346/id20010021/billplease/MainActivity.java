package sg.edu.rp.c346.id20010021.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    EditText amount;
    EditText numofpax;
    ToggleButton svs;
    ToggleButton gst;
    EditText discountamt;
    Button split;
    Button reset;
    TextView totalbill;
    TextView eachpay;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amount=findViewById(R.id.amount);
        numofpax=findViewById(R.id.numofpax);
        svs=findViewById(R.id.svs);
        gst=findViewById(R.id.gst);
        discountamt=findViewById(R.id.discountamt);
        split=findViewById(R.id.split);
        reset=findViewById(R.id.reset);
        totalbill=findViewById(R.id.totalbill);
        eachpay=findViewById(R.id.eachpay);


        split.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(amount.getText().toString().trim().length()!=0 && numofpax.getText().toString().trim().length()!=0){
                    double amt=0;

                    if (discountamt.getText().toString().trim().length() != 0) {
                        amt *= 1 - Double.parseDouble(discountamt.getText().toString()) / 100;
                    }
                    if(!svs.isChecked() && !gst.isChecked()){
                        amt= Double.parseDouble(amount.getText().toString());
                    } else if (svs.isChecked() && !gst.isChecked()){
                        amt= Double.parseDouble(amount.getText().toString())*1.1;
                    } else if (!svs.isChecked() && gst.isChecked()){
                        amt= Double.parseDouble(amount.getText().toString())*1.07;
                    } else if (!svs.isChecked() && !gst.isChecked()){
                        amt= Double.parseDouble(amount.getText().toString())*1.17;
                    }

                    totalbill.setText("Total Bill: $" + String.format("%.2f", amt));
                    int numPerson = Integer.parseInt(numofpax.getText().toString());
                    if (numPerson != 1)
                        eachpay.setText("Each Pays: $" + String.format("%.2f", amt / numPerson));
                    else
                        eachpay.setText("Each Pays: $" + amt);
                }
            }

        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount.setText("");
                numofpax.setText("");
                svs.setChecked(false);
                gst.setChecked(false);
                discountamt.setText("");
            }
        });







    }
}