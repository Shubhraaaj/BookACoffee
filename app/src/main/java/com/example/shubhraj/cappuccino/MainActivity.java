package com.example.shubhraj.cappuccino;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    int count=1;
    private Button plus,minus,order;
    private EditText name;
    TextView amount,number;
    CheckBox cream,choco;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        plus=(Button) findViewById(R.id.plus);
        minus=(Button) findViewById(R.id.minus);
        order=(Button) findViewById(R.id.order);
        name=(EditText) findViewById(R.id.name);
        amount=(TextView) findViewById(R.id.details);
        cream=(CheckBox) findViewById(R.id.cream);
        choco=(CheckBox) findViewById(R.id.choco);
        number=(TextView) findViewById(R.id.number);
        plus.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                increment();
                number.setText(""+count);
                amount.setText(orderDetails());
            }
        });

        minus.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                decrement();
                number.setText(""+count);
                amount.setText(orderDetails());
            }
        });

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount.setText("Email to SP's Coffee Shop");
                Intent intent=new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:ashu.shubhraj@gmail.com"));
                intent.putExtra(Intent.EXTRA_SUBJECT,"Coffe Order");
                intent.putExtra(Intent.EXTRA_TEXT,orderDetails());
                if(intent.resolveActivity(getPackageManager())!=null)
                    startActivity(intent);
            }
        });
    }

    public void increment()
    {
        if(count==100)
            return;
        count++;
    }

    public void decrement()
    {
        if(count==1)
            return;
        count--;
    }

    public String topping()
    {
        if(cream.isChecked())
            return "Whip Cream";
        else
            return "Chocolate";
    }

    public int getAmount()
    {
        int amount=50;
        if(topping()=="Whip Cream")
            amount=(50 * count)+(30 * count);
        else
            amount=(50 * count)+(50 * count);
        return amount;
    }

    public String orderDetails()
    {

        return ("Name:"+(name.getText().toString())+"\nQuantity:"+count+"\n"+"Topping:"+topping()+"\nAmount:Rs."+getAmount()+"\nThank You");
    }
}
