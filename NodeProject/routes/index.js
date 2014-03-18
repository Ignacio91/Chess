
/*
 * Array for filtering words
 */
 var array = new Array("pussy", "fuck", "dick", "blowjob", "hoe", "Mutherfucker", "asshole", "porn");

exports.index = function(req, res){
  res.render('index', { title: 'Express' });
};
/*
 * Connect with the database
 */
var mongoose = require( 'mongoose' );
var Comment = mongoose.model( 'Comment' );

/*
 * exports the existing comments of the db
 */
exports.index = function ( req, res ){
  Comment.find( function ( err, comments, count ){
    res.render( 'index', {
        title : 'Comment System With MongoDB',
        comments : comments
    });
  });
}; 
/*
 * Creates the comment
 */
exports.create = function ( req, res )
{
  new Comment({
    username : req.body.username,
    content : check(req.body.comment),
    created : Date.now()
  }).save( function( err, comment, count ){
    res.redirect( '/' );
  });
};
function check(word)
{
  for(var i=0;i<array.length;i++)
  {
    if(word == array[i])
    {
      return '*******';
    }

  }
  return word;

}
/*
 * sets up the proper date
 */
function date()
{
  var date = new Date(Date.now());
  var properlyFormatted = date.getFullYear() + "/" + ("0" + (date.getMonth() + 1)).slice(-2) + "/"+ ("0" + date.getDate()).slice(-2);
  var hour = date.getHours() + ":" +date.getMinutes();

  return "\tDate : " + properlyFormatted + "\tTime : " +hour;;
}