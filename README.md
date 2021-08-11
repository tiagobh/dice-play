Dice Games

The solution has the following content

1) Producer(Port: 8081): Is the main entrypoint of the game,split and send games to Kafka queue. 
   1) A requestTraceId is returned to find the result in the API.
2) Consumer(Port: 8082): Execute the game and statistics and put in database
3) API(Port:8084): Get the final GameResults from database, filtering by requestTraceId.
4) A simple domain module, just to share classes
5) Kafka
6) Zookeeper
7) MongoDB

Running:
1) Run the docker-compose.yml and wait for:
   1) zookeeper
   2) kafka
   3) mongodb
2) Run all applications (Consumer, Producer, API)

Using:
1) Send a Post request with a body to: localhost:8081/play
2) To get result, send a GET to localhost:8084/result/[givenRequestTraceId] 

Body example:

````json
{
  "games": [
    {
      "name": "You win if you get one six in four rolls of one dice",
      "lockNumber": 6,
      "attempts": 4,
      "bets": 1,
      "dice": [
        {
          "name": "First die"
        }
      ]
    },
    {
      "name": "You win if you get double sixes in twenty four rolls of two dice.",
      "lockNumber": 6,
      "attempts": 24,
      "bets": 1000000,
      "dice": [
        {
          "name": "One die"
        },
        {
          "name": "Other die"
        }
      ]
    }
  ]
}
````