/**
 * Parsing and Listeners
 */
document.getElementById("ass1").addEventListener('click',function()
{
	//document.getElementById("text1").style.visibility="visible";
	 document.getElementById("text1").value = "";
	 parse("test.xml", "test2.xml", "Assigment1");
  
  }
, false);
/**
 * Listener for assigment 2
 */
document.getElementById("ass2").addEventListener('click',function()
		{
			document.getElementById("text1").value = "";
			parse("test.xml", "test2.xml", "Assigment1.1");
		  
		 }
, false); 
/**
 * Listener for assigment 3
 */
document.getElementById("ass3").addEventListener('click',function()
		{
			document.getElementById("text1").value = "";
			parse("test.xml", "test2.xml", "Assigment1.2");
		 }
, false);  
/**
 * Listener for the button
 */
document.getElementById("ass4").addEventListener('click',function()
{
	document.getElementById("text1").value = "";
}
, false);

/**
 * Parse all the files exportable to other topics
 */
function parse(file1, file2, assigment)
{
	  //parsing to json
	  var json_file1 = parseXmltoJson(file1);
	  var json_file2 = parseXmltoJson(file2);
	  
	  //document.getElementById("text1").value += JSON.stringify(json_file1);
	  //document.getElementById("text1").value += JSON.stringify(json_file2);
	  //get Latest Commit
	  var revision = checkForMaxRevision(json_file1, assigment);
	  //Header
	  document.getElementById("text1").value += assigment+"\n" + "Latest Commit\t" + revision + "\t";
	  //date and hour parsing
	  var date = new Date(json_file1.lists.list.entry[0].commit.date["#text"]);
	  var properlyFormatted = date.getFullYear() + "/" + ("0" + (date.getMonth() + 1)).slice(-2) + "/"+ ("0" + date.getDate()).slice(-2);
	  var hour = date.getHours() + ":" +date.getMinutes();
	  
	  document.getElementById("text1").value += "\tDate : " + properlyFormatted + "\tTime : " +hour;
	  
	  //print files and all the info  of latest commit
	  printInfoFromFiles(json_file2, revision);
	  //document.getElementById("text1").value += json_file2.log.logentry[0].paths.path
}
/**
 * Print all information from the files
 */
function printInfoFromFiles(json, revision)
{
	for (var key in json.log.logentry) 
	{
		  if (json.log.logentry.hasOwnProperty(key)) 
		  {
			  
			  if(json.log.logentry[key]["@attributes"].revision == revision)
			  {
				  
				  document.getElementById("text1").value += "\n\nHere Are the Files for latest commit\n";
				  document.getElementById("text1").value += "\n Message :  " +json.log.logentry[key].msg["#text"] + "\n";
				  
				  for (var key2 in json.log.logentry[key].paths.path) 
				  {
					  
					  if (json.log.logentry[key].paths.path.hasOwnProperty(key2)) 
					  {
						   var file = json.log.logentry[key].paths.path[key2]["@attributes"];
						   var res = json.log.logentry[key].paths.path[key2]["#text"].split("/");
						   var name = res[res.length-1].split(".")[0];
						   var type = res[res.length-1].split(".")[1];
						  document.getElementById("text1").value += "\n"  + "Kind :" +file.kind 
						  + "  Name :\t" + name+ "  Type: \t"+type
						  + "  Type of action : \t"+file.action ;
					  }
					  
				  }
			  }
			  
		  }
		}
	}
/**
 * Get the XMl file and parse it to a JSon Object
 */
function parseXmltoJson(file)
{
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.open("GET",file,false);
	xmlhttp.send();
	var xmlDoc = xmlhttp.responseXML;
	//we parse the document
	var parse_xml = (new XMLSerializer()).serializeToString(xmlDoc);
	return xmlToJson(xmlDoc);

}

/**
 * Get the latest commit done to the project
 */
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
/**
 * Function that changes the XML to JSON
 */
function xmlToJson(xml) {
	
	// Create the return object
	var obj = {};

	if (xml.nodeType == 1) { // element
		// do attributes
		if (xml.attributes.length > 0) {
		obj["@attributes"] = {};
			for (var j = 0; j < xml.attributes.length; j++) {
				var attribute = xml.attributes.item(j);
				obj["@attributes"][attribute.nodeName] = attribute.nodeValue;
			}
		}
	} else if (xml.nodeType == 3) { // text
		obj = xml.nodeValue;
	}

	// do children
	if (xml.hasChildNodes()) {
		for(var i = 0; i < xml.childNodes.length; i++) {
			var item = xml.childNodes.item(i);
			var nodeName = item.nodeName;
			if (typeof(obj[nodeName]) == "undefined") {
				obj[nodeName] = xmlToJson(item);
			} else {
				if (typeof(obj[nodeName].push) == "undefined") {
					var old = obj[nodeName];
					obj[nodeName] = [];
					obj[nodeName].push(old);
				}
				obj[nodeName].push(xmlToJson(item));
			}
		}
	}
	return obj;
};