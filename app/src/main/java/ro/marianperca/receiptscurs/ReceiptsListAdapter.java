package ro.marianperca.receiptscurs;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class ReceiptsListAdapter extends RecyclerView.Adapter<ReceiptsListAdapter.ViewHolder> {
    private SimpleDateFormat formatDate = new SimpleDateFormat("EEEE, MMM d", Locale.ENGLISH);
    private LinkedList<Receipt> mDataset = new LinkedList<>();
    private OnReceiptClickListener mClickListener;

    public void addReceipt(Receipt r) {
        mDataset.addFirst(r);
        notifyDataSetChanged();
    }

    public void addReceipts(List<Receipt> receipts) {
        mDataset.addAll(receipts);
        notifyDataSetChanged();
    }

    public void setClickListener(OnReceiptClickListener callback) {
        mClickListener = callback;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.receipt_list_item, parent, false);
        return new ViewHolder(cv);
    }

    // apelata de fiecare data cand este populat un rand
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Receipt receipt = mDataset.get(position);
        holder.bind(receipt, mClickListener);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface OnReceiptClickListener {
        void onClick(Receipt receipt);
    }

    // aceasta clasa va contine layoutul nostru
    class ViewHolder extends RecyclerView.ViewHolder {
        View rootView; //card view
        TextView mValue;
        TextView mDate;
        TextView mStore;

        ViewHolder(View v) {
            super(v);
            rootView = v;

            mValue = v.findViewById(R.id.value);
            mDate = v.findViewById(R.id.date);
            mStore = v.findViewById(R.id.store);
        }

        void bind(final Receipt receipt, final OnReceiptClickListener listener) {
            mValue.setText(receipt.value + "");
            mDate.setText(formatDate.format(receipt.date));
            mStore.setText(receipt.store);
            rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(receipt);
                }
            });
        }
    }
}
