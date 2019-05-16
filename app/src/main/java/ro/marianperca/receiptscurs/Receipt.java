package ro.marianperca.receiptscurs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class Receipt {
    Date date;
    double value;
    String store;

    public Receipt(Date date, double value, String store) {
        this.date = date;
        this.value = value;
        this.store = store;
    }

    public static Receipt generateFakeReceipt() {
        ArrayList<String> stores = new ArrayList<>(
                Arrays.asList("Carrefour", "Kaufland", "Penny", "La 2 pasi")
        );

        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 1);
        double receiptValue = ThreadLocalRandom.current().nextDouble(5, 101);
        int storeIndex = ThreadLocalRandom.current().nextInt(stores.size());

        return new Receipt(c.getTime(), Math.round(receiptValue * 100) / 100d, stores.get(storeIndex));
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "date=" + date +
                ", value=" + value +
                ", store='" + store + '\'' +
                '}';
    }
}
