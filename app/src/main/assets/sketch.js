let t = 0
let path = []
mags.forEach(()=>{
    path.push([])
})


function setup() {
  createCanvas(600, 800)
}

function draw() {
    background(0);
    for(let i=0;i<mags.length;i++){
        let Vector = plotFourier(width/2,height/2,0,i)

        path[i].unshift(Vector);
    }


      // Drawing path
    for(let i=0;i<mags.length;i++){
        beginShape()
        for(let index = 0;index<path[i].length;index++){
            noFill()
            vertex(path[i][index].x,path[i][index].y)
        }
        endShape()
    }

      const dt = TWO_PI/500
      t += dt
}