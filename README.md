# directoryAPI

####Framework: Spring MVC 3 + Spring data Redis

####To Run: mvn jetty:run  -Djetty.port= \<port number\>

####EndPoints:

POST /add

body: json
{
  "name": "Almo",
  "addresses": [
    "here",
    "there",
    "everywhere"
  ]}

return: 

success: Person object in Json with assigned Uid

error: null or exception


GET /get?id=\<uid\>

POST /update

body: json

{
  "name": "piggy",
  "addresses": [
    "here",
    "everywhere"
  ],
  "uid": "19"}


GET /delete?id=\<uid\>

POST /addFamily

body: json
{
 "family" :[ { "name": "kermit",
  "addresses": [
    "here",
    "there",
    "everywhere"
  ]
 },
  { "name": "piggy",
  "addresses": [
    "here",
    "there",
    "everywhere"
  ]
 }]
  
}

####To-Do: logging, exception handling