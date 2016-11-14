from TwitterAPI import TwitterAPI, TwitterOAuth, TwitterRestPager
import re
import pymysql
import configparser
from collections import Counter

#o = TwitterOAuth.read_file('Credentials.txt')
#twitter = TwitterAPI(o.consumer_key, o.consumer_secret,o.access_token_key, o.access_token_secret)

config = configparser.ConfigParser()
config.read('Credentials.txt')
twitter = TwitterAPI(
                   config.get('twitter', 'consumer_key'),
                   config.get('twitter', 'consumer_secret'),
                   config.get('twitter', 'access_token'),
                   config.get('twitter', 'access_token_secret'))
                 
screen_name = 'BestBuy_Deals'
timeline = [tweet for tweet in twitter.request('statuses/user_timeline',
                                                {'screen_name': screen_name,
                                                 'count': 200})]
print ('got %d tweets for user %s' % (len(timeline), screen_name))

cnx = pymysql.connect(user='root', password='root', host='127.0.0.1', database='BestDealDatabase')
cursor = cnx.cursor()

query = ("select productID, productName from products")
cursor.execute(query)

dealMatchGauranteed=[]
idList=[]
for product in cursor:
    for tweet in timeline:
        deal = (tweet['text']).encode('ascii','ignore')
        #if (len(re.findall(r'\s'+product[0]+'\s', str(deal))) >= 1):
        if product[1] in str(deal):
            dealMatchGauranteed.append(str(deal.decode()))
            idList.append(product[0])
            
#print (dealMatchGauranteed)

dealMatchFile = open('DealMatches.txt', 'w')
idMatchFile = open('IDMatches.txt', 'w')

for deal in dealMatchGauranteed:
  dealMatchFile.write("%s\n" % deal)
  
for id in idList:
  idMatchFile.write("%s\n" % id)

dealMatchFile.close()
idMatchFile.close()