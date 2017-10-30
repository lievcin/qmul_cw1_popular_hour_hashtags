	import java.io.IOException;
	// import java.util.Calendar;
	import java.util.StringTokenizer;
	import org.apache.hadoop.io.IntWritable;
	import org.apache.hadoop.io.Text;
	// import org.apache.hadoop.io.LongWritable;
	// import org.apache.hadoop.io.DoubleWritable;
	import org.apache.hadoop.mapreduce.Mapper;
	// import tweets_parser.java;

	public class HashtagsMapper extends Mapper<Object, Text, Text, IntWritable> {

		private final IntWritable one = new IntWritable(1);
		private TweetsParser parser = new TweetsParser();
    private Text data = new Text();

    public void map(Object key, Text tweet, Context context) throws IOException, InterruptedException {
    		//1469453965000;757570957502394369;Over 30 million women footballers in the world. Most of us would https://t.co/Mu5miVJAWx;<a href="http://twitter.com/download/iphone" rel="nofollow">Twitter for iPhone</a>
	    	//epoch_time;tweetId;tweet(including #hashtags);device
    		parser.parse(tweet);

    		if (parser.isValidTweet() && parser.getTweetHour() == 2) {
    			String[] tweet_words = parser.getTweetBody().split("\\s");
    			// StringTokenizer tweet_words = new StringTokenizer(parser.getTweetBody(), "-- \t\n\r\f,.:;?![]'\"");
    			for (int t = 0; t < tweet_words.length; t++)
    				try {
		          if (tweet_words[t].charAt(0) == '#') {
		          	data.set(tweet_words[t]);
		          	context.write(data, one);
		          }
		        } catch(Exception e) {
            System.out.println("Some empty bodies...");
        	}

	        // while (tweet_words.hasMoreTokens()) {

	          // if (tweet_words.nextToken().charAt(0) == '#') {
	          // 	data.set(tweet_words.nextToken());
	          // 	context.write(data, one);
	          // }
	        // }
    			// data.set(parser.getTweetHour());
    			// context.write(data, one);
    		}

    }

	}
