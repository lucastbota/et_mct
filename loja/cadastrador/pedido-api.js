var http = require('http');
const host = 'localhost';
const path = '/pedido';
const port = 8080;
const header = {
  'Content-Type': 'application/json'
};

 


function create(data) {
  var options = {
    hostname: host,
    port: port,
    path: path,
    headers: header,
    method: 'POST'
  };

  const req = http.request(options, res => {
    console.log(`statusCode: ${res.statusCode}`);
  });

  req.on('error', error => {
    console.error(error)
  })

  req.write(data)
  req.end()
}

module.exports.create = create;