<html>
<head>
        <style>
            .ticket {
                    border: 1px solid black;
                    width: 100px;
                    display: inline-block;
                    margin: 2px;
            }

            .ticketID {
                    
                    text-align: center;
                    margin: 0px;
                    margin-top: 5px;
            }

            .ticketValue {
                text-align: center;
                font-size: 18pt;
                font-weight: bold;
                margin: 0px;
            }
        </style>

        <link rel="stylesheet" media="print" type="text/css" href="print.css" />

	<script type="text/javascript">
		window.onload = function () {
			


			setInterval(function(){
				var xml = get_xml_http();
				xml.onreadystatechange = function () {

					if (xml.readyState == 4 && xml.status == 200) {

						var tickets = JSON.parse(xml.responseText);
                                                var htmlContent = "";
						for (var i = 0; i < tickets.length; i++) {
                                                    
                                                    var ticket = tickets[i];
                                                    htmlContent += '<div class="ticket">' +
                                                        '<p class="ticketID">'            +
                                                            ticket.id                     +
                                                        '</p>'                            +

                                                        '<p class="ticketValue">'         +
                                                            ticket.value                  +
                                                        '</p>'                            +
                                                    '</div>'
                                                    
                                                }

                                                get_e("contents").innerHTML = htmlContent;
					}
				}

				xml.open("POST", "tickets.php", true);
				xml.setRequestHeader("Content-type","application/x-www-form-urlencoded")
				xml.send();
			}, 2000);
		}
	</script>
	<script src="utility.js"></script>
	<title>Real-time view voting system</title>
</head>
<body>
	<div id="contents">
	</div>

        
</body>
</html>