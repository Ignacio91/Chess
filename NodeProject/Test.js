/**
 * Mocha Test File
 */
var expect = require("chai").expect;
var search = require("../lib/search.js");
var fs = require("fs");
 
/**
 * Function all the required files are in the folder
 */
it("should retrieve the files from a directory", function(done) {
    search.scan(".public", 0, function(err, flist){
        expect(flist).to.deep.equal([
            ".public/test",
            ".public/test2",
            ".public/svn_list",
            ".public/lvn_log"
        ]);
        done();
    });
});
/**
 * Function to test the test file is working properly
 */
describe('Looking for parsing correctly',function()
{
    var json;
    json = parseXmlToJson("test.xml")
    before(function(done)
    {
    	json = parseXmlToJson("test.xml");
   
    });
    it('Object cannot be null',function(){
    	json!= null;
    });

  });
function parseXmltoJson(file)
{
	var xmlhttp=new XMLHttpRequest();
	xmlhttp.open("GET",file,false);
	xmlhttp.send();
	var xmlDoc=xmlhttp.responseXML;
	//we parse the document
	var parse_xml = (new XMLSerializer()).serializeToString(xmlDoc);
	return xmlToJson(xmlDoc);

}
function checkForMaxRevision(json, assigment)
{
	var max = 0;
	for (var key in json.lists.list.entry) 
	{
		  if (json.lists.list.entry.hasOwnProperty(key)) 
		  {
			  if(json.lists.list.entry[key].name["#text"] == assigment)
			  {
				  var possible_max = json.lists.list.entry[key].commit["@attributes"].revision;
				  if(max < possible_max)
					  max = possible_max;
			  }
			  
		  }
		}
	return max;
}