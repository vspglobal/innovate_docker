"use strict";

function doAllTheWork() {
	<!-- Call the long polling function to process service instances. -->
	queryServiceList(false);
}

function queryServiceList(wait) {

	$.get( "http://54.244.91.57:8000/services", function( data ) {

		var svcArray = data.node.nodes;
		if (svcArray == null) return;

		var nodeMap = {};
		var svcCount = svcArray.length;
		for (var i = 0; i < svcCount; i++) {
			var nodeEntry = JSON.parse( svcArray[i].value );

			var k = nodeEntry.host + ":" + nodeEntry.port + ":" + nodeEntry.version;

			nodeMap[k] = nodeEntry;
		}

		var keyArray = Object.keys(nodeMap).sort();

	//	console.log( keyArray );

		var p = d3.select("#all_the_things")
			.selectAll("div")
			.data( keyArray, function(key) {return key} );

		// Enter…
		p.enter()
			.append("div")
			.attr("class", "dockerNode")
			.attr("id",
				function( key ) {
					return key;
				}
			)
			.html(
				function( key ) {
					var n = nodeMap[key];
					buildOneNode(key, n, this);
					return "<img style='background-color: #ffffff' src='https://s3-us-west-2.amazonaws.com/innovate-day/docker/loading.gif' height='120' width='120'/>";
				}
			)
			.style("opacity", 0)
			.transition()
			.duration(1000)
			.style("opacity", 1)
			;

		p.exit()
			.html('<img height="120" width="120" style="background-color: #ffffff" src="fire.gif">')
			.transition()
			.delay(1000)
			.duration(1000)
			.style("opacity", 0)
			.remove()
			;

	}, "json" );

}

function buildOneNode(key, nodeEntry, dockerNode) {

	$.get( nodeEntry.url, function( data ) {

		dockerNode.innerHTML = "<img height='120' width='120' src='" + data.imageUrl + "'/><br>" + data.ip + "<br>v" + data.version;

	}, "json" ).fail( function() {
		setTimeout( function() {
			//console.log("Failing to find: " + key + " at: " + nodeEntry.url);
			buildOneNode(key, nodeEntry, dockerNode);
		},300);
	});
}
