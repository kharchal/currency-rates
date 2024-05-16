# Getting Started

### There are two available profiles:

#### dev, mock     

#### mock files are stored: /resources/mocks/

### Api has three endpoints:

* GET /rates/fortoday  - fetches rates for current day
* GET /rates/fordate/{date} -fetches rates for specified date
* GET /rates/delete/{date} 

get methods are chosen for compability with any browsers

  (date format for all endpoints is following: YYYY-MM-DD)

### Dto format is as received from NBU:

"r030": int "currency code"

"cc": string "short name",

"rate": float "rate"

"txt": string "local full currency name",

"exchangedate": string "date",

"updated": string "timestamp"


### H2 credentials:

#### url: http://localhost:8080/h2-console
#### username: rates
#### password: rates
