# README #

### What is this repository for? ###

Part B-ii of the Coursework1 for Big Data Processing module

### Task ###

For the most popular hour of the games, compute the top 10 hashtags that were emitted during that hour. Hashtags are words contained inside the tweet, 
starting with the hashcode (#) character. Does that information provide you any hint on the main events/activities that took place at peak time? [5 marks)]

Hashtags are words contained inside the tweet, starting with the hashcode (#) character. Does that information provide you any hint on the 
main events/activities that took place at peak time? [5 marks)

### How do I get set up? ###

To build the project run: 
# ant clean dist #
To run the task on the server:
# hadoop jar dist/TopTenPopularHashtags.jar TopTenPopularHashtags /data/olympictweets2016rio out #
to merge results
# hadoop fs -getmerge out top_ten_hashtags_at_most_popular_hour.txt #

# chart at https://cloud.highcharts.com/charts/asefimu/ #