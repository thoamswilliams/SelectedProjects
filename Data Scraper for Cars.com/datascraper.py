from bs4 import BeautifulSoup as BS
from requests import get
import numpy as np
import pandas as pd

#web header to prevent request rejections (many websites have anti-scraping countermeasures)
headers = ({'User-Agent':
            'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit\
/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36'})

#url with search results, leave page number blank
base_url = "https://www.cars.com/shopping/results/?list_price_max=&makes[]\
=toyota&maximum_distance=all&models[]=toyota-prius&page={}&page_size=30\
&stock_type=used&zip=94404"

#convert string to int, tolerant of $ and other characters
def parse_int(input):
    temp = ''
    for i in input:
        if(i.isdigit()):
            temp += i
    if(len(temp) > 0):
        temp = int(temp)
    else: 
        temp = np.NaN

    return temp

#add on to existing file, else create new one
try:
    data = pd.read_csv('carPrices.csv')
except FileNotFoundError:
    data = pd.DataFrame(columns = ['age','miles','price'])

for pagenum in range(1,87):
    #request HTML page from website
    url = base_url.format(pagenum)
    response = get(url, headers=headers)

    if(str(response) == "<Response [200]>"): #successful response
        html_soup = BS(response.text, 'html.parser')
        car_list = html_soup.find_all('div', attrs={'class': 'vehicle-details'}) #find divs containing desired info

        for car in car_list:
            year = int((car.find_all('h2', attrs={'class': 'title'})[0].text)[:4])

            mileage = car.find_all('div', attrs={'class': 'mileage'})[0].text
            mileage = parse_int(mileage)

            price = car.find_all('span', attrs={'class': 'primary-price'})[0].text
            price = parse_int(price)

            row = pd.DataFrame(np.reshape(np.asarray([year, mileage, price]), (-1,3)), columns = data.columns)
            data = data.append(row, ignore_index = True)

        print("Successfully scraped page " + str(pagenum))

        if(pagenum % 10 == 0):
            data.to_csv('carPrices.csv', index=False)
            print('Checkpoint saved at page ' + str(pagenum))
    else: #webpage has refused request, may require changing ip address
        print("Error, retry starting page " + str(pagenum))
        break
else:
    print('Success, all pages scraped')

data = data.dropna(how = 'any')
data = data.reset_index(drop = True)

for i in range(len(data.loc[:,'age'])): #change year of release to age
    data.loc[i,'age'] = int(2022 - data.loc[i,'age'])

for i in range(len(data.loc[:,'miles'])): #in 1000s, round to 1 sig fig
    data.loc[i,'miles'] = np.round(data.loc[i,'miles']/1000,1)

for i in range(len(data.loc[:,'price'])):
    data.loc[i,'price'] = np.round(data.loc[i,'price']/1000,1)

data.to_csv('carPrices.csv', index=False)