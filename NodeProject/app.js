
/**
 * Module dependencies.
 */

var express = require('express');
var routes = require('./routes');
var user = require('./routes/user');
var http = require('http');
var path = require('path');

var app = express();
var fs = require('fs'), xml2js = require('xml2js');



// all environments
app.set('port', process.env.PORT || 3000);
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'jade');


app.use(express.favicon());
app.use(express.logger('dev'));
app.use(express.json());
app.use(express.urlencoded());
app.use(express.methodOverride());
app.use(app.router);
app.use(express.static(path.join(__dirname, 'public')));

console.log(__dirname);
// development only
if ('development' == app.get('env')) 
{
  app.use(express.errorHandler());
}

app.get('/', routes.index);
app.get('/users', user.list);

http.createServer(app).listen(app.get('port'), function(req, res)
{
	var parser = new xml2js.Parser();
	fs.readFile( __dirname +'/XML/svn_list.xml',  'utf8', function(err, data) 
			{console.log(data);
		    parser.parseString(data, function (err, result) 
		    {
		    	
		        console.dir(JSON.stringify(result));
		        console.log('Done');
		        
		    });
	});
  console.log('Express server listening on port ' + app.get('port'));
});



