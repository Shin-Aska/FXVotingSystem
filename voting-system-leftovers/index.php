<!DOCTYPE HTML>
<html>
<head>
	<script type="text/javascript">
		window.onload = function () {
			


			setInterval(function(){
				var xml = get_xml_http();
				xml.onreadystatechange = function () {

					if (xml.readyState == 4 && xml.status == 200) {

						var dp = JSON.parse(xml.responseText);
						var chart = new CanvasJS.Chart("chartContainer",
						{
							title:{
								text: "Number of votes"
							},

							data: [
							{
								type: "bar",

								dataPoints: dp
							}
							]
						});

						chart.render();
					}
				}

				xml.open("POST", "loadcandidates.php", true);
				xml.setRequestHeader("Content-type","application/x-www-form-urlencoded")
				xml.send();
			}, 2000);
		}
	</script>
	<script src="canvasjs.min.js"></script>
	<script src="utility.js"></script>
	<title>Real-time view voting system</title>
</head>
<body>
	<div id="chartContainer" style="height: 400px; width: 100%;">
	</div>
</body>
</html>