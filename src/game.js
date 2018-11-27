function httpPost(url, payload, callback) {
  let request = new XMLHttpRequest();
  request.onreadystatechange = function() {
    if (request.readyState == 4) {
      if (request.status == 200) callback(request.responseText);
      else {
        if (request.status == 0 && request.statusText.length == 0) {
          console.log("Connection failed");
        }
      }
    }
  };
  request.open("post", url, true);
  request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
  request.send(payload);
}


let GameId = 0;
let GameName = "";

class Game {
  constructor () {
    this.model = new Model();
    this.view = new View(this.model);
    this.controller = new Controller(this.model);
    window.onload = this.start.bind(this);
    httpPost("ajax_handler.html", JSON.stringify({
      action: "start"
    })  , (payload) => {
      GameId = JSON.parse(payload).data.id;
      const name = JSON.parse(payload).data.name;
      if (name == "Mario") {
        this.model.mario.setName("Mario");
        this.model.luigi.setName("Luigi");
      } else if (name == "Luigi") {
        this.model.mario.setName("Luigi");
        this.model.luigi.setName("Mario");
      }
    })
  }

  start() {
    this.clock = window.setInterval(this.loop.bind(this), 10);
  }

  loop() {
    this.model.update();
    this.view.update();
    this.controller.update();
    httpPost("ajax_handler.html", JSON.stringify({
      action: "getPos"
    }), (payload) => {
      const data = JSON.parse(payload);
      for (const person of Object.values(data)) {
        if (person.name !== this.model.mario.name) {
          this.model.luigi.x = person.x;
          this.model.luigi.y = person.y;
        }
      }
    })
    httpPost("http://localhost:1234/ajax_handler.html", JSON.stringify({
      action: "updatePos",
      x: Math.floor(this.model.mario.x),
      y: Math.floor(this.model.mario.y),
      id: GameId
    }), () => {})
  }
}

const game = new Game();