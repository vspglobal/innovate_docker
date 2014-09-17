"use strict";

function doAllTheWork() {

//	console.log("doing all the work");

	<!-- Get and display/log the version of etcd being accessed. -->
	<!-- http://127.0.0.1:4001/version -->

	<!-- if we fail to find etcd pop up a dialog asking for the server -->

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
			);

		// Exit…
		var exitCount = 0;
		p.exit()
			.transition()
			.delay(function(d){ return 750*exitCount++; })
			.duration(1000)
			.style("opacity", 0)
			.remove();


	}, "json" );

}

function buildOneNode(key, nodeEntry, dockerNode) {

	$.get( nodeEntry.url, function( data ) {

		dockerNode.innerHTML = "<img height='120' width='120' src='" + data.imageUrl + "'/><br>" + data.ip + "<br>v" + data.version;

	}, "json" ).fail( function() {
		setTimeout( function() {
			buildOneNode(key, nodeEntry, dockerNode);
		},300);
	});
}
