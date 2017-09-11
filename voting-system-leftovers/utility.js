/*
	Some of the basic functions, objects and variables that 
	will be used by base.js will be defined here.

	Quick rule of thumb, always remember that each elements
	to be used in the program should be registered first and
	can be deleted by doing the same thing.
*/

////////////////////////////////////////////////////////////

/*
  * User Object -> This object is what
  * represents the User defined within
  * our database.
*/

function User (UID, UNAME, STATUS) {

	this.UID = UID;
	this.UNAME = UNAME;
	this.STATUS = parseInt(STATUS);
}

 /*
  * Element Object -> This object is
  * what represents the elements in
  * the current webpage that can be
  * utilized by other programs.
  */

function Element (e_name, e_type) {

	this.ID   = e_name;	
	this.Type = e_type;

}

/*
 * MessageEntry Object -> It represents
 * the messagelogs within the client.
 */

function Message (UID, UNAME, MSG, TIME, TEMP, MSGID)
{
	this.UID = UID;
	this.UNAME = UNAME;
	this.MSG = MSG;
	this.TIME = TIME;
	this.TEMP = TEMP;
	this.RENDERED = false;
	this.MSGID = MSGID;
}

 /*
  * Converts current PHP string to JS
  * implementation of Date. Handy for
  * long polling events implemented on
  * base.js
  */

function Date_php (STR) {

	if (STR != 0) {

		php_tmp = STR.split(" ");
		date = php_tmp[0];
		time = php_tmp[1];
		date = date.split("-");
		time = time.split(":");

		this.DATE = new Date();
		
		var YEAR  = parseInt(date[0]);
		var MONTH = parseInt(date[1]);
		var DAY   = parseInt(date[2]);

		var HOUR  = parseInt(time[0]);
		var MINUTE= parseInt(time[1]);
		var SECOND= parseInt(time[2]);

		this.DATE.setFullYear(YEAR);
		this.DATE.setMonth(MONTH);
		this.DATE.setDate(DAY);
		this.DATE.setHours(HOUR);
		this.DATE.setMinutes(MINUTE);
		this.DATE.setSeconds(SECOND);
	}
}

function Date_str () {

	var currDate = new Date();
	var monthStr = new Array("January", "February", "March", "April", "May", "June", "July", "August", "September",
							 "October", "November", "December");
	var merid    = new Array("AM", "PM");
	var month 	 = parseInt(currDate.getMonth());
	var date 	 = parseInt(currDate.getDate());
	var year     = parseInt(currDate.getFullYear());
	var hours    = parseInt(currDate.getHours());
	var minutes  = parseInt(currDate.getMinutes());
	var seconds  = parseInt(currDate.getSeconds());
	var ampm     = 0;

	if (hours >= 12) {

		ampm    = 1;

		if (hours > 12)
			hours   -= 12;
	}
	
	
	if (date < 10) {
		date  = "0" + date;
	}

	if (minutes < 10) {

		minutes = "0" + minutes;
	}

	if (seconds < 10) {
		seconds = "0" + seconds;
	}

	var curr_str = monthStr[month] + " " + date + ", " + year + " " + hours + ":" + minutes + " " + merid[ampm];
	return curr_str;	
}

function Date_stamp (sYear) {

	var currDate = new Date();

	if (typeof(sYear) === "number") {
		currDate.setYear(sYear);
	}

	var month 	 = parseInt(currDate.getMonth()) + 1;
	var date 	 = parseInt(currDate.getDate());
	var year     = parseInt(currDate.getFullYear());
	var hours    = parseInt(currDate.getHours());
	var minutes  = parseInt(currDate.getMinutes());
	var seconds  = parseInt(currDate.getSeconds());

	

	if (parseInt(hours) < 10)
		hours = "0" + hours;
	if (parseInt(minutes) < 10)
		minutes = "0" + minutes;
	if (parseInt(seconds) < 10)
		seconds = "0" + seconds;

	return year + "-" + month + "-" + date + " " + hours + ":" + minutes + ":" + seconds;
}

/*
 * Compares current local date
 * and Date converted in Date_php
 */


function COMPARE  (DATE_PHP) {

	var today = new Date();
	var DIFF = today - DATE_PHP;
	var resu = new Date(DIFF);
	return resu.getMinutes();

}

function COMPARE_PRESENT_DATE (enteredDate) {
	return Math.round(Math.abs(new Date() - enteredDate) / 1000);
}


///////////////////////////////////////////////////////////



//Global Variables


//Global Functions

function isalpha (s) {
	return ((s >= 'a' && s <= 'z') || (s >= 'A' && s <= 'Z'));
}

function isdigit (s) {
	return (s >= '0' || s <= '9');
}

function isalnum(s) {
	return (isalpha(s) || isNumber(s));
}

// Get Element Shortcut

function get_e (id_of_object) {

	return document.getElementById(id_of_object);

}

function getElemSelectText(elem) { 
	if(elem.tagName === "TEXTAREA" ||
		(elem.tagName === "INPUT" && elem.type === "text")) {
		return elem.value.substring(elem.selectionStart,
									elem.selectionEnd);
    }
	return null;
}

function getScrollTop(){
    if(typeof pageYOffset!= 'undefined'){
        //most browsers except IE before #9
        return pageYOffset;
    }
    else{
        var B= document.body; //IE 'quirks'
        var D= document.documentElement; //IE with doctype
        D= (D.clientHeight)? D: B;
        return D.scrollTop;
    }
}

//Old Library Functions

function toUpperCase(str) {
	return str.toUpperCase();
}

function setOpacity(eID, opacityLevel) {
	var eStyle = get_e(eID).style;
	eStyle.opacity = opacityLevel / 100;
	eStyle.filter = 'alpha(opacity='+opacityLevel+')';
}

function fade(eID, startOpacity, stopOpacity, duration) {
	var speed = Math.round(duration / 100);
	var timer = 0;
	if (startOpacity < stopOpacity){
		for (var i=startOpacity; i<=stopOpacity; i++) {
			setTimeout("setOpacity('"+eID+"',"+i+")", timer * speed);
			timer++;
		} return;
	}
	for (var i=startOpacity; i>=stopOpacity; i--) {
		setTimeout("setOpacity('"+eID+"',"+i+")", timer * speed);
		timer++;
	}
}

function terminate(eID)
{
	var eStyle = get_e(eID).style;
	eStyle.display = "none";
}

function fade_terminate(eID, startOpacity, stopOpacity, duration) {
	var eStyle = get_e(eID).style;
	var speed = Math.round(duration / 100);
	var timer = 0;
	if (startOpacity < stopOpacity){
		for (var i=startOpacity; i<=stopOpacity; i++) {
			setTimeout("setOpacity('"+eID+"',"+i+")", timer * speed);
			timer++;
		} return;
	}
	for (var i=startOpacity; i>=stopOpacity; i--) {
		setTimeout("setOpacity('"+eID+"',"+i+")", timer * speed);
		timer++;
	}

	setTimeout("terminate('"+eID+"')", timer * speed);
}



// Checks if the string is empty

function isEmpty(str) {
	
    return (0 == str.length);

}

// Check if the string is blank

function isBlank(str) {

    return (!str || /^\s*$/.test(str));

}

// Evaluates if the user is just pressing enter
// all over again, this will prevent the server
// from sending at fast rates.

function evaluate_if_onechar (txt_str) {

	if (txt_str == '\n')
		return false;
	return true;

}

/*
 * Checks if the variable is a valid number
 */

function isNumber(n) {

	return !isNaN(parseFloat(n)) && isFinite(n);
}

/*	Converts all the numerical strings in an array
 *	to it's integer equivalents 
 *	NOTE: This only works for one dimensional array.
 */


function Convert_ArrayInt (array) {

	if (array instanceof Array) {

		for (var i = 0; i < array.length; i++) {

			if (isNumber(array[i])) {

				array[i] = parseInt(array[i]);
			}
		}

	}
	else {
		throw "Variable is not an array exception occured.";
	}

}


/*
 * PHP ONLY [REQUIRES LOGIN.PHP ON THE SAME DIRECTORY]
 * Checks if the current user session is enabled
 * if not, the user will be redirect to url1 
 * (Leave it blank if you don't want to redirect)
 *
 * else the user will be redirected to url2
 * (Again, leave it blank if you don't want to redirect)
*/

function redirect_if_session_notfound (type, url1, url2) {
	var xml = get_xml_http();
	xml.onreadystatechange = function () {

		if (xml.readyState == 4 && xml.status == 200) {

			var status = xml.responseText;
			status = "{" + status + "}";
			var dataJS = JSON.parse(status);
			status = dataJS.sessionStatus.ID;

			if (isNumber(status)) { 
				var id  = parseInt(status);

				if (id == 1 && (!isBlank(url1) && !isEmpty(url1))) {
					window.location = url1;
				}
				else if (id == 0 && (!isBlank(url2) && !isEmpty(url2))) {
					window.location = url2;
				}
			}
			else {
				
				throw "Server thrown an invalid status exception occured";
			}
		}

	}

	xml.open("POST", "../scripts/PHP/login.php?type=" + type, true);
	xml.setRequestHeader("Content-type","application/x-www-form-urlencoded")
	xml.send("status=1");
}

function redirect (status, url1, url2) {

	if (isNumber(status)) {

		status = parseInt(status);

		if (status == 1 && (!isBlank(url1) && !isEmpty(url1))) {

			window.location = url1;
		}
		else if (status == 0 && (!isBlank(url2) && !isEmpty(url2))) {
			
			window.location = url2;
		}
	}
	else {

		throw "Variable :status: is not a valid status exception occured " + status;
	}
}

// GetXMLHttp() -> 
// 
//

function get_xml_http ()
{
	var x;
	
	/******************************************
	 * For Firefox, Opera 8.0+, Chrome, Safari*
	 ******************************************/
	try
	{
		x = new XMLHttpRequest();
	}
	catch (exception)
	{
		/*
		 * ActiveX check using Microsoft xml2 and xmlhttp engine
		 * If it isn't compatible with both, then the RIA will
		 * terminate automatically. 
		 */

		try
		{
			x = new ActiveXObject("Msxml2.XMLHTTP");
		}
		catch (exception2)
		{
			try
			{
				x = new ActiveXObject("Microsoft.XMLHTTP");
			}
			catch (exception3)
			{
				alert("Your browser is incompatible with this program. Please try revisiting the " +
				"this website using the latest versions of these browsers.\n\n1.) Microsoft Internet Explorer"+
				"\n2.) Google Chrome\n3.) Mozilla Firefox\n4.) Opera");
				document.write("<html>" +
							 		"<title>Error</title>" + 
							 		"<body>" + 
							 			"<h1>Your browser is not compatible with this program</h1>" + 
							 		"</body>" + 
							 	"</html>");
			}
		}
	}
	
	return x;
}

//Global Objects

// Element objects holds the list of elements
// registered in the program

var element = {

	list: new Array(),

	add: function(e_name, e_type){

		var NewElement = new Element(e_name, e_type);
		this.list.push(NewElement);

	},

	find: function (e_name) {
		
		var index = -1;
		for (var i = 0; i < this.list.length; i++) {

			if (this.list[i].ID == e_name) {
				index = i;
				break;
			}

		}

		return index;

	},

	access: function (e_name) {


		var index = element.find(e_name);
		if (index != -1) {
			
			var obj = get_e(e_name);

			if (obj == null)
				throw "Element undefined exception occured";

			return obj;

		}

		throw "Not Found exception occured";

	}

}

/*
 * EventHandler class attaches and removes functions in any DOM events that happens
 * on the browser window.
 *
 */

var EventHandler = {

	isWindowActive: false,

	functionType: function(funcName, funcVar) {

		this.Name = funcName;
		this.Event = funcVar;
	},

	scrollEvent: {

		functionHolder: new Array(),

		Function: function() {

			for (var i = 0; i < this.functionHolder.length; i++) {
				
				this.functionHolder[i].Event();
			}
		},

		attachFunction: function(funcName, funcVar) {

			this.functionHolder.push(new EventHandler.functionType(funcName, funcVar));
		},

		removeFunction: function(funcName) {

			for (var i = 0; i < this.functionHolder.length; i++) {
				
				if (this.functionHolder[i].Name == funcName) {

					this.functionHolder.splice(i, 1);
					return true;
				}
			}

			return false;
		}
	},

	resizeEvent: {

		functionHolder: new Array(),

		Function: function() {

			for (var i = 0; i < this.functionHolder.length; i++) {
				
				this.functionHolder[i].Event();
			}
		},

		attachFunction: function(funcName, funcVar) {
			
			this.functionHolder.push(new EventHandler.functionType(funcName, funcVar));
		},

		removeFunction: function(funcName) {

			for (var i = 0; i < this.functionHolder.length; i++) {
				
				if (this.functionHolder[i].Name == funcName) {

					this.functionHolder.splice(i, 1);
					return true;
				}
			}

			return false;
		}
	},
}

var Evaluator = {

	evaluate: function(msg) {

		var processed = msg;
		var url       = "";
		var urls      = [];
		var hasURI    = [];
		processed = htmlspecialchars_decode(processed);

		// I tried to create an object by object basis, but it almost always reaches the timeout value
		// Or memory corruption, so I had to just use an arrays of strings instead.

		for (var i = 0; i < processed.length; i++) {

			if (processed[i].toLowerCase() == 'h') {

				if (processed.substr(i, 7).toLowerCase() == "http://") {

					var endIndex = i;
					for (var j = i + 7; j < processed.length; j++) {
						if (!isalnum(processed[j]) && processed[j] != "/" && processed[j] != "." && processed[j] != "?" && processed[j] != "-") {
							endIndex = j;
							break;
						}
					}

					if (i != endIndex) {
						url = processed.substring(i, endIndex);
					}
					else {
						url = processed.substring(i);
					}

					urls.push(url);
					hasURI.push(true);
					i = url.length + processed.search(url);
				}
			}
		}

		for (var i = 0; i < processed.length; i++) {

			if (processed[i].toLowerCase() == 'w') {

				if (processed.substr(i, 3).toLowerCase() == "www") {

					var endIndex = i;
					for (var j = i + 3; j < processed.length; j++) {
						if (!isalnum(processed[j]) && processed[j] != "/" && processed[j] != "." && processed[j] != "?") {
							endIndex = j;
							break;
						}
					}

					if (i != endIndex) {
						url = processed.substring(i, endIndex);
					}
					else {
						url = processed.substring(i);
					}

					urls.push(url);
					hasURI.push(false);
					i = url.length + processed.search(url);
				}
			}
		}


		processed = htmlspecialchars(processed);
		for (var i = 0; i < urls.length; i++) {
			var found = false;
			url = urls[i];
			uriPresent = hasURI[i];

			if (!uriPresent) {
				url = "http://" + url;
			}
			
			for (var j = 0; j < i; j++) {
				if (urls[i].search(urls[j]) != -1 || urls[j].search(urls[i]) != -1) {
					found = true;
				}
			}

			if (!found) {
				processed = processed.replace(urls[i], "<a href='" + url + "'>" + urls[i] + "</a>");
			}
		}
		
		return processed;
	}
}

window.onscroll = function() {

	EventHandler.scrollEvent.Function();
};

window.onresize = function() {

	EventHandler.resizeEvent.Function();
}

window.onfocus = function() {
	EventHandler.isWindowActive = true;
}

window.onblur = function() {
	EventHandler.isWindowActive = false;
}

function htmlspecialchars(string, quoteStyle, charset, doubleEncode) {
    //       discuss at: http://locutus.io/php/htmlspecialchars/
    //      original by: Mirek Slugen
    //      improved by: Kevin van Zonneveld (http://kvz.io)
    //      bugfixed by: Nathan
    //      bugfixed by: Arno
    //      bugfixed by: Brett Zamir (http://brett-zamir.me)
    //      bugfixed by: Brett Zamir (http://brett-zamir.me)
    //       revised by: Kevin van Zonneveld (http://kvz.io)
    //         input by: Ratheous
    //         input by: Mailfaker (http://www.weedem.fr/)
    //         input by: felix
    // reimplemented by: Brett Zamir (http://brett-zamir.me)
    //           note 1: charset argument not supported
    //        example 1: htmlspecialchars("<a href='test'>Test</a>", 'ENT_QUOTES')
    //        returns 1: '&lt;a href=&#039;test&#039;&gt;Test&lt;/a&gt;'
    //        example 2: htmlspecialchars("ab\"c'd", ['ENT_NOQUOTES', 'ENT_QUOTES'])
    //        returns 2: 'ab"c&#039;d'
    //        example 3: htmlspecialchars('my "&entity;" is still here', null, null, false)
    //        returns 3: 'my &quot;&entity;&quot; is still here'

    var optTemp = 0
    var i = 0
    var noquotes = false
    if (typeof quoteStyle === 'undefined' || quoteStyle === null) {
        quoteStyle = 2
    }
    string = string || ''
    string = string.toString()

    if (doubleEncode !== false) {
        // Put this first to avoid double-encoding
        string = string.replace(/&/g, '&amp;')
    }

    string = string
        .replace(/</g, '&lt;')
        .replace(/>/g, '&gt;')

    var OPTS = {
        'ENT_NOQUOTES': 0,
        'ENT_HTML_QUOTE_SINGLE': 1,
        'ENT_HTML_QUOTE_DOUBLE': 2,
        'ENT_COMPAT': 2,
        'ENT_QUOTES': 3,
        'ENT_IGNORE': 4
    }
    if (quoteStyle === 0) {
        noquotes = true
    }
    if (typeof quoteStyle !== 'number') {
        // Allow for a single string or an array of string flags
        quoteStyle = [].concat(quoteStyle)
        for (i = 0; i < quoteStyle.length; i++) {
            // Resolve string input to bitwise e.g. 'ENT_IGNORE' becomes 4
            if (OPTS[quoteStyle[i]] === 0) {
                noquotes = true
            } else if (OPTS[quoteStyle[i]]) {
                optTemp = optTemp | OPTS[quoteStyle[i]]
            }
        }
        quoteStyle = optTemp
    }
    if (quoteStyle & OPTS.ENT_HTML_QUOTE_SINGLE) {
        string = string.replace(/'/g, '&#039;')
    }
    if (!noquotes) {
        string = string.replace(/"/g, '&quot;')
    }

    return string
}

function htmlspecialchars_decode(string, quoteStyle, charset, doubleEncode) {
    // Modified from htmlspecialchars from locutos.io

    var optTemp = 0
    var i = 0
    var noquotes = false
    if (typeof quoteStyle === 'undefined' || quoteStyle === null) {
        quoteStyle = 2
    }
    string = string || ''
    string = string.toString()

    if (doubleEncode !== false) {
        // Put this first to avoid double-encoding
        string = string.replace(/&amp;/g, '&')
    }

    string = string
        .replace(/&lt;/g, '<')
        .replace(/&gt;/g, '>')

    var OPTS = {
        'ENT_NOQUOTES': 0,
        'ENT_HTML_QUOTE_SINGLE': 1,
        'ENT_HTML_QUOTE_DOUBLE': 2,
        'ENT_COMPAT': 2,
        'ENT_QUOTES': 3,
        'ENT_IGNORE': 4
    }
    if (quoteStyle === 0) {
        noquotes = true
    }
    if (typeof quoteStyle !== 'number') {
        // Allow for a single string or an array of string flags
        quoteStyle = [].concat(quoteStyle)
        for (i = 0; i < quoteStyle.length; i++) {
            // Resolve string input to bitwise e.g. 'ENT_IGNORE' becomes 4
            if (OPTS[quoteStyle[i]] === 0) {
                noquotes = true
            } else if (OPTS[quoteStyle[i]]) {
                optTemp = optTemp | OPTS[quoteStyle[i]]
            }
        }
        quoteStyle = optTemp
    }
    if (quoteStyle & OPTS.ENT_HTML_QUOTE_SINGLE) {
        string = string.replace(/&#039;/g, '\'')
    }
    if (!noquotes) {
        string = string.replace(/&quot;/g, '"')
    }

    return string
}
