# house-scraper

Finding a house was not that easy. 
This scraper finds houses on some of the most important real estate websites in my area. 
Each 15 minutes there will be a search on these websites. If new houses are found a webhook is send to IFTTT.

### Supported real estate websites 
* Zimmo
* Immoweb
* Immoscoop
* Immovlan
* Realo
* 2dehands


## Usage
#### Update properties
Webhook id & name in `application-docker.yml`
#### Change URLs for each website
`provider/*`
#### Build image
`mvn clean install`
#### Run docker-compose
`docker-compose up --build`
