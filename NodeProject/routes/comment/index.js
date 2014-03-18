
exports.comment = function(req, res){
  res.render('comment', { title: 'Express' });
};
var mongoose = require( 'mongoose' );
var Comment = mongoose.model( 'Comment' );

exports.comment = function ( req, res ){
  Comment.find( function ( err, comments, count ){
    res.render( 'comment', {
        title : 'Comment System With MongoDB',
        comments : comments
    });
  });
}; 
