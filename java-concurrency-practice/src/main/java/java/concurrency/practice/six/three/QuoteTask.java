package java.concurrency.practice.six.three;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;

public class QuoteTask implements Callable<TravelQuote> {
    private final TravelCompany company = null;
    private final TravelInfo travelInfo = null;

    public QuoteTask() {
    }

    public QuoteTask(TravelCompany company, TravelInfo travelInfo) {
    }

    public TravelQuote call() throws Exception {
        return company.solicitQuote(travelInfo);
    }

    public TravelQuote getFailureQuote(Throwable cause) {
    }

    public TravelQuote getTimeoutQuote(CancellationException e) {
    }
}
