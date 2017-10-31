	import java.io.IOException;
	import java.util.StringTokenizer;
	import org.apache.hadoop.io.IntWritable;
	import org.apache.hadoop.io.Text;
	import org.apache.hadoop.mapreduce.Mapper;

	public class HashtagsMapper extends Mapper<Object, Text, Text, IntWritable> {

		private final IntWritable one = new IntWritable(1);
		private TweetsParser parser = new TweetsParser();
    private Text data = new Text();

    public void map(Object key, Text tweet, Context context) throws IOException, InterruptedException {
    		//1469453965000;757570957502394369;Over 30 million women footballers in the world. Most of us would https://t.co/Mu5miVJAWx;<a href="http://twitter.com/download/iphone" rel="nofollow">Twitter for iPhone</a>
	    	//epoch_time;tweetId;tweet(including #hashtags);device
    		parser.parse(tweet);

    		if (parser.isValidTweet() && parser.getTweetHour() == 2) {
    			// hour == 2 based on our previous mapReduce job to get the hour with the highest count of tweets.
    			String[] tweet_words = parser.getTweetBody().split("\\s");
    			for (int t = 0; t < tweet_words.length; t++)
    				try {
		          if (tweet_words[t].charAt(0) == '#') {
		          	data.set(tweet_words[t].toLowerCase().replaceAll("[^\\p{ASCII}]", ""));
		          	//here I deliberately don't want to have hashtags with weird emojis and stuff.
		          	//ideally it should get me a more concise list of hashtags
		          	context.write(data, one);
		          }
		        } catch(Exception e) {
            System.out.println("Some invalid tweet body");
        	}
    		}

    }

	}
