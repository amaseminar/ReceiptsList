package ro.marianperca.receiptscurs;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    ReceiptsListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.receipts_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new ReceiptsListAdapter();
        recyclerView.setAdapter(mAdapter);
        mAdapter.setClickListener(new ReceiptsListAdapter.OnReceiptClickListener() {
            @Override
            public void onClick(Receipt receipt) {
                Log.d("###", receipt.toString());
            }
        });

        FloatingActionButton fab = findViewById(R.id.add_receipt);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAdapter.addReceipt(Receipt.generateFakeReceipt());
            }
        });

        addTestReceipts();
    }

    private void addTestReceipts() {
        ArrayList<Receipt> list = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            list.add(Receipt.generateFakeReceipt());
        }

        mAdapter.addReceipts(list);
    }
}
