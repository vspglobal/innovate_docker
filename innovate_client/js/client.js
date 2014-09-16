"use strict";

function doAllTheWork() {

	console.log("doing all the work");

	<!-- Get and display/log the version of etcd being accessed. -->
	<!-- http://127.0.0.1:4001/version -->

	<!-- if we fail to find etcd pop up a dialog asking for the server -->

	<!-- Call the long polling function to process service instances. -->
	queryServiceList(false);
}

function queryServiceList(wait) {

	<!-- http://127.0.0.1:4001/v2/keys/services?wait=true -->
	$.get( "http://localhost:8080/services", function( data ) {
		  var svcArray = data.node.value;
		  if (svcArray == null) return;
		  var svcCount = svcArray.length;
			for (var i = 0; i < svcCount; i++) {
				// TODO query the service to get the state
				var state = {};
		  	buildSvcDisplay(svcArray[i].name, svcArray[i].url, state);
		  }
		}, "json" );
	
}

function buildSvcDisplay(name, url, state) {
	$("#all_the_things").append( "Name: " + name + " is at " + url);
}