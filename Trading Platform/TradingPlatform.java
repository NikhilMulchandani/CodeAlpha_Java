import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

class Stock {
    private String tickerSymbol;
    private String companyName;
    private double currentPrice;

    public Stock(String tickerSymbol, String companyName, double initialPrice) {
        this.tickerSymbol = tickerSymbol;
        this.companyName = companyName;
        this.currentPrice = initialPrice;
    }

    public String getTickerSymbol() {
        return tickerSymbol;
    }

    public String getCompanyName() {
        return companyName;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void updatePrice(double newPrice) {
        this.currentPrice = newPrice;
    }

    @Override
    public String toString() {
        return tickerSymbol + " (" + companyName + ") - $" + currentPrice;
    }
}

class Market {
    private Map<String, Stock> stocks;
    private Random random;

    public Market() {
        stocks = new HashMap<>();
        random = new Random();
        initializeMarket();
    }

    private void initializeMarket() {
        stocks.put("AAPL", new Stock("AAPL", "Apple Inc.", 150.00));
        stocks.put("GOOGL", new Stock("GOOGL", "Alphabet Inc.", 2800.00));
        stocks.put("AMZN", new Stock("AMZN", "Amazon.com Inc.", 3400.00));
        stocks.put("TSLA", new Stock("TSLA", "Tesla Inc.", 700.00));
    }

    public Stock getStock(String tickerSymbol) {
        return stocks.get(tickerSymbol);
    }

    public void simulatePriceChanges() {
        for (Stock stock : stocks.values()) {
            double changePercent = (random.nextDouble() * 2 - 1) * 0.05;  // -5% to +5%
            double newPrice = stock.getCurrentPrice() * (1 + changePercent);
            stock.updatePrice(Math.round(newPrice * 100.0) / 100.0);  // round to 2 decimal places
        }
    }

    public void displayMarketData() {
        System.out.println("Market Data:");
        for (Stock stock : stocks.values()) {
            System.out.println(stock);
        }
    }
}

class Portfolio {
    private Map<String, Integer> holdings;  // tickerSymbol -> quantity
    private double cashBalance;

    public Portfolio(double initialBalance) {
        holdings = new HashMap<>();
        cashBalance = initialBalance;
    }

    public void buyStock(String tickerSymbol, int quantity, double price) {
        double cost = quantity * price;
        if (cost > cashBalance) {
            System.out.println("Insufficient funds to buy " + quantity + " shares of " + tickerSymbol);
            return;
        }
        cashBalance -= cost;
        holdings.put(tickerSymbol, holdings.getOrDefault(tickerSymbol, 0) + quantity);
        System.out.println("Bought " + quantity + " shares of " + tickerSymbol);
    }

    public void sellStock(String tickerSymbol, int quantity, double price) {
        if (!holdings.containsKey(tickerSymbol) || holdings.get(tickerSymbol) < quantity) {
            System.out.println("Not enough shares to sell " + quantity + " shares of " + tickerSymbol);
            return;
        }
        cashBalance += quantity * price;
        holdings.put(tickerSymbol, holdings.get(tickerSymbol) - quantity);
        if (holdings.get(tickerSymbol) == 0) {
            holdings.remove(tickerSymbol);
        }
        System.out.println("Sold " + quantity + " shares of " + tickerSymbol);
    }

    public double getPortfolioValue(Market market) {
        double totalValue = cashBalance;
        for (Map.Entry<String, Integer> entry : holdings.entrySet()) {
            Stock stock = market.getStock(entry.getKey());
            totalValue += entry.getValue() * stock.getCurrentPrice();
        }
        return totalValue;
    }

    public void displayPortfolio(Market market) {
        System.out.println("Portfolio:");
        for (Map.Entry<String, Integer> entry : holdings.entrySet()) {
            Stock stock = market.getStock(entry.getKey());
            System.out.println(entry.getKey() + ": " + entry.getValue() + " shares @ $" + stock.getCurrentPrice());
        }
        System.out.println("Cash Balance: $" + cashBalance);
        System.out.println("Total Portfolio Value: $" + getPortfolioValue(market));
    }
}

public class TradingPlatform {
    private Market market;
    private Portfolio portfolio;

    public TradingPlatform() {
        market = new Market();
        portfolio = new Portfolio(10000.00);  // Initial balance of $10,000
    }

    public void displayMarketData() {
        market.displayMarketData();
    }

    public void executeBuy(String tickerSymbol, int quantity) {
        Stock stock = market.getStock(tickerSymbol);
        if (stock != null) {
            portfolio.buyStock(tickerSymbol, quantity, stock.getCurrentPrice());
        } else {
            System.out.println("Invalid ticker symbol.");
        }
    }

    public void executeSell(String tickerSymbol, int quantity) {
        Stock stock = market.getStock(tickerSymbol);
        if (stock != null) {
            portfolio.sellStock(tickerSymbol, quantity, stock.getCurrentPrice());
        } else {
            System.out.println("Invalid ticker symbol.");
        }
    }

    public void displayPortfolio() {
        portfolio.displayPortfolio(market);
    }

    public void mainMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Trading Platform Menu ---");
            System.out.println("1. Display Market Data");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. Display Portfolio");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    market.simulatePriceChanges();
                    displayMarketData();
                    break;
                case 2:
                    System.out.print("Enter ticker symbol: ");
                    String buySymbol = scanner.next().toUpperCase();
                    System.out.print("Enter quantity: ");
                    int buyQuantity = scanner.nextInt();
                    executeBuy(buySymbol, buyQuantity);
                    break;
                case 3:
                    System.out.print("Enter ticker symbol: ");
                    String sellSymbol = scanner.next().toUpperCase();
                    System.out.print("Enter quantity: ");
                    int sellQuantity = scanner.nextInt();
                    executeSell(sellSymbol, sellQuantity);
                    break;
                case 4:
                    displayPortfolio();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        TradingPlatform platform = new TradingPlatform();
        platform.mainMenu();
    }
}
