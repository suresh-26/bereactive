package sentiment_analyzer;

import rx.Observable;
import twitter4j.FilterQuery;
import twitter4j.Status;
import twitter4j.StatusAdapter;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

public final class TweetObservable {

    public static Observable<Status> tweetObservable(final String[] searchKeywords) {
        return Observable.create(subscriber -> {
            final TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
            twitterStream.addListener(new StatusAdapter() {
                public void onStatus(Status status) {
                    subscriber.onNext(status);
                }

                public void onException(Exception ex) {
                    subscriber.onError(ex);
                }
            });
            FilterQuery query = new FilterQuery();
            query.language(new String[] { "en" });
            query.track(searchKeywords);
            twitterStream.filter(query);
        });

    }
}
