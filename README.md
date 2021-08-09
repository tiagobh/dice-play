Dice Games

Host: localhost:8080/play

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