var exec = require("cordova/exec");
module.exports = {
	startSmartLinker: function(content){
		exec(
		function(message){
			console.log(message);
			alert(errorMessage)
		},
		function(errorMessage){
			console.log(errorMessage);
			alert(errorMessage);
		},
		"SmartLinker",
		"startSmartLinker",
		[content]);
	},
	getSSID: function(content){
        exec(
        function(message){
            console.log(message);
            alert(message);
        },
        function(errorMessage){
            console.log(errorMessage);
            alert(errorMessage);
        },
        "SmartLinker",
        "getSSID",
        [content]);
    }
}
