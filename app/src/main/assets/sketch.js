let t = 0
let path = []
let screenScale = 0.5;
let previousDistance = 0;
mags.forEach(()=>{
    path.push([])
})

function setup() {
  createCanvas(windowWidth+500, windowHeight+500);
  window.scrollTo(windowWidth-100, windowHeight-200);
}

function touchMoved() {
  if (touches.length == 2) {
    let currentDistance = dist(touches[0].x, touches[0].y, touches[1].x, touches[1].y);
    if (previousDistance != 0) {
      if (currentDistance > previousDistance) {
        screenScale *= 1.02;
      } else {
        screenScale *= 0.98;
      }
    }
    previousDistance = currentDistance;
  }
}

function touchEnded() {
  previousDistance = 0;
}

function draw() {
    scale(screenScale);
    background(0);
    for(let i=0;i<mags.length;i++){
        let Vector = plotFourier(width+200,height+200,0,i)

        path[i].unshift(Vector);
    }


      // Drawing path
    strokeWeight(4);
    stroke(255);
    for(let i=0;i<mags.length;i++){
        beginShape()
        for(let index = 0;index<path[i].length;index++){
            noFill()
            vertex(path[i][index].x,path[i][index].y)
        }
        endShape()
    }

      const dt = TWO_PI/1000;
      t += dt
}