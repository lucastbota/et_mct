var http = require('http');
const host = 'localhost';
const path = '/produto';
const port = 8084;
const header = {
  'Content-Type': 'application/json'
};

function get(callback) {
  var options = {
    hostname: host,
    port: port,
    path: path,
    headers: header,
    method: 'GET'
  };
 
  http.get(options, function(res) {
    var body = '';
    res.on('data', function(chunk) {
      body += chunk;
    });
    res.on('end', function() {
      callback(body);
    });
  }).on('error', function(e) {
    console.log("Got error: " + e.message);
  });
}


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
module.exports.get = get;