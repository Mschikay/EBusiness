var info = document.getElementById("info");	

$("#check").click(function(){
	var name = document.getElementById("name").value;
    var state = document.getElementById("state").value;
    var data = {
		name: name,
		state: state
		};
    var string = "";
    
    if (name == null || state == null || name == "Name" || state == "likes?"){
    	string = "Please select your pet name or pet state first."
    	var para = document.createElement('li');
    	para.className = "list-group-item";
    	para.innerHTML = string
		info.appendChild(para);
    }else{
    	$.ajax({
    		url:"showAnimal",
    		type: "post",
    	    data: JSON.stringify(data),
    		contentType:"application/json",
    		success: [
    			function (result, status, xhr) {
    				console.log(xhr.status, result);
    				
    				string = name + " is a " + result.species + " that " +
        			state + " " + result.food;
        			infoItem = document.createElement('li');
        			infoItem.className = "list-group-item";
        			infoItem.innerHTML = string;
       				info.appendChild(infoItem);    							
    		}],
    		error: [function (res) {
    			$("#info").empty();
    			string = "Sorry. Error occurred in server.";
    			var para = document.createElement('li');
    			para.className = "list-group-item";
    			para.innerHTML = string;
    			info.appendChild(para);
    		}]
    	});
    }
    
});


$("#clear").click(function(){
	$("#info").empty();
});