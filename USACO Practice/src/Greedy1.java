// Code Review: Money Transfer

// Since our systems process billions of dollars in trades per week, it’s
// important that trade processing and money transfer are error-free.

// Suppose the code below comes to you for code review. The responsibility of
// this code block is to transfer money from one account to another
// and send emails to both account owners when the transfer is complete.

// This code is logically correct. However, given that we operate in the real
// world on a distributed system with millions of people transferring money in
// and out, what could go wrong? How would you change the code below to
// accommodate these failure scenarios?

// Don't focus on code style or refactoring the code.

// def bad_transfer(src_account, dst_account, amount):
//     src_account.Lock()
//     src_cash = src_account.cash # DB read
//     dst_account.Lock()
//     dst_cash = dst_account.cash # DB read
//     if src_cash < amount:
//         raise InsufficientFunds
//         src_account.Unlock()
//     src_account.cash = src_cash - amount # DB write
//     src_account.Unlock()
//     src_account.send_src_transfer_email()
//     dst_account.cash = dst_cash + amount # DB write
//     dst_account.Unlock()
//     dst_account.send_dst_transfer_email()


// - The read for the src_cash can be stale by the time we do the Insufficient Funds check
// - Lock the row or account every time you want to make edits



// Portfolio Value Optimization
// ======

// You have some securities available to buy that each has a price Pi.
// Your friend predicts for each security the stock price will be Si at some future date.
// But based on volatility of each share, you only want to buy up to Ai shares of each security i.
// Given M dollars to spend, calculate the maximum value you could potentially
// achieve based on the predicted prices Si (and including any cash you have remaining).
// You can buy fractions of a share.

//  * Pi = Current Price 
//  * Si = Expected Future Price
//  * Ai = Maximum units you are willing to purchase
//  * M = Dollars available to invest

// Example Input:
// M = $140 available

// - P1=15, S1=45, A1=3 (AAPL)
// - P2=40, S2=50, A2=3 (BYND)
// - P3=25, S3=35, A3=3 (SNAP)
// - P4=30, S4=25, A4=4 (TSLA)

// Example Output: $265 (no cash remaining)

import java.util.*; 

// Main class should be named 'Solution'
class Greedy1 {
    
    class Stock {
        
        public double currentPrice;
        public double futurePrice;
        public int maxUnits;
        public double marginalGain;
        
        public Stock(double currentPrice, double futurePrice, int maxUnits) {
            this.currentPrice = currentPrice;
            this.futurePrice = futurePrice;
            this.maxUnits = maxUnits;
            this.marginalGain = (futurePrice) / currentPrice;
        }
        
        public String toString() {
            return "" + marginalGain;
        }
        
    }
    
    class MarginalGain implements Comparator<Stock> {
        
        public int compare(Stock s1, Stock s2) {
            if (s1.marginalGain > s2.marginalGain) return -1;
            return 1;
        }
        
    }
    
   public static double finalValue(double[] currentPrices, double[] futurePrices, int[] maxUnits, int balance) {
        ArrayList<Stock> stockListings = new ArrayList<Stock>();
       
        // sort stockListings in order of decreasing marginalGain
        Greedy1 s = new Greedy1();
        for (int i = 0; i < currentPrices.length; i++) {
            stockListings.add(s.new Stock(currentPrices[i], futurePrices[i], maxUnits[i]));
        }
        Collections.sort(stockListings, s.new MarginalGain());
        System.out.println(stockListings);
        double finalBalance = 0;
        int currentStock = 0;
        Stock curr;
       
        while (balance > 0 && currentStock < stockListings.size() && (curr = stockListings.get(currentStock)).marginalGain > 0) {
            double moneySpent = Math.min(balance, curr.maxUnits * curr.currentPrice);
            balance -= moneySpent;
            finalBalance += moneySpent * curr.marginalGain;
            currentStock ++;
        }
        return finalBalance;
    }
    
    public static void main(String[] args) {
        double[] currentPrices = {15.0, 40.0, 25.0, 30.0};
        double[] futurePrices = {45.0, 50.0, 35.0, 25.0};
        int[] maxUnits = {3, 3, 3, 4};
        int budget = 140;
        System.out.println(finalValue(currentPrices, futurePrices, maxUnits, budget));
    }
}
