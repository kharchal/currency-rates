# Getting Started

### There are two available profiles:
### set JVM option for it accordingly:

#### dev     -Dspring.profiles.active=dev

#### mock    -Dspring.profiles.active=mock
#### mock files are stored: /resources/mocks/

### Api has three endpoints:

* GET /rates/fortoday  - fetches rates for current day
* GET /rates/fordate/{date} -fetches rates for specified date
* DELETE, GET /rates/delete/{date} (Get is used for test purpose)

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
